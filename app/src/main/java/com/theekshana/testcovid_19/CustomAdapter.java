package com.theekshana.testcovid_19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<CountryModel> {
    private Context context;
    private List<CountryModel> countryModelList;

    public CustomAdapter(Context context, List<CountryModel> countryModelList) {
        super(context, R.layout.list_custom_itrm, countryModelList);

        this.context = context;
        this.countryModelList = countryModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_itrm, null, true);
        TextView tvCountryname = view.findViewById(R.id.tvCountryName);
        ImageView imageView = view.findViewById(R.id.detailImgFlag);

        tvCountryname.setText(countryModelList.get(position).getCountry());
        Glide.with(context).load(countryModelList.get(position).getFlag()).into(imageView);
        return view;
    }


}
