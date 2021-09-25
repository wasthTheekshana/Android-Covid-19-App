package com.theekshana.testcovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText searchBox;
    ListView listView;

    public static List<CountryModel> countryModelslist = new ArrayList<>();
    CountryModel countryModel;
    CustomAdapter  customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBox = findViewById(R.id.edtSearch);
        listView = findViewById(R.id.listView);
        
        fetchData();



        Runnable r =  new Runnable() {
                    @Override
                    public void run() {
                        for(int i =0; i<=10; i++){

                            int finalI = i;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            startActivity(new Intent(getApplicationContext(),Deatil_Country.class).putExtra("position",position));
                                        }
                                    });
                                }
                            });

                            try {
                                Thread.sleep(1000); //do not the ui thread
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    }
                };
                Thread t = new Thread(r);
                t.start();;

    }

    private void fetchData() {
        String url = "https://corona.lmao.ninja/v2/countries";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray =  new JSONArray(response);

                    for (int i= 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String countryName = jsonObject.getString("country");
                        String cases = jsonObject.getString("cases");
                        String todayCases = jsonObject.getString("todayCases");
                        String deaths = jsonObject.getString("deaths");
                        String todayDeaths = jsonObject.getString("todayDeaths");
                        String recovered = jsonObject.getString("recovered");
                        String active = jsonObject.getString("active");
                        String critical = jsonObject.getString("critical");

                        JSONObject object = jsonObject.getJSONObject("countryInfo");
                        String flagUrl = object.getString("flag");

                            countryModel = new CountryModel(flagUrl,countryName,cases,todayCases,deaths,todayDeaths,recovered,active,critical);
                            countryModelslist.add(countryModel);

                    }

                    customAdapter = new CustomAdapter(MainActivity.this,countryModelslist);
                    listView.setAdapter(customAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    }
