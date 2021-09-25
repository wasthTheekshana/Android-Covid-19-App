package com.theekshana.testcovid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class Deatil_Country extends AppCompatActivity {


    private int positionCountry;
    TextView detailCountryFullName, cofrmFull,tdconfrm,deathFull,tddeath,recoervdFull,tdrecorved,totalfull;
    ImageView imageView;
    PieChart pieChart;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatil__country);

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);
        Log.d("pos>>>>>", String.valueOf(positionCountry));


        detailCountryFullName = findViewById(R.id.detailCountryfullName);
        cofrmFull = findViewById(R.id.confrmfull);
        tdconfrm = findViewById(R.id.todyconfrm);
        deathFull = findViewById(R.id.deathfull);
        tddeath = findViewById(R.id.tddeath);
        recoervdFull = findViewById(R.id.rcvrdfull);
        tdrecorved = findViewById(R.id.tdrecorved);
        totalfull = findViewById(R.id.totalfull);
        pieChart = findViewById(R.id.piechart);

        detailCountryFullName.setText(MainActivity.countryModelslist.get(positionCountry).getCountry());
        cofrmFull.setText(MainActivity.countryModelslist.get(positionCountry).getCases());
        tdconfrm.setText("+" + MainActivity.countryModelslist.get(positionCountry).getTodayCases());
        deathFull.setText(MainActivity.countryModelslist.get(positionCountry).getDeaths());
        tddeath.setText("+" + MainActivity.countryModelslist.get(positionCountry).getTodayDeaths());
        recoervdFull.setText(MainActivity.countryModelslist.get(positionCountry).getRecoverd());
        tdrecorved.setText("+" + MainActivity.countryModelslist.get(positionCountry).getCritical());
        totalfull.setText(MainActivity.countryModelslist.get(positionCountry).getActive());

        pieChart.addPieSlice(new PieModel("Cases", Integer.parseInt(cofrmFull.getText().toString()), Color.parseColor("#003CBF")));
        pieChart.addPieSlice(new PieModel("Recoverd", Integer.parseInt(recoervdFull.getText().toString()), Color.parseColor("#06CAFD")));
        pieChart.addPieSlice(new PieModel("Deaths", Integer.parseInt(deathFull.getText().toString()), Color.parseColor("#FF5C4D")));
        pieChart.startAnimation();


    }
}