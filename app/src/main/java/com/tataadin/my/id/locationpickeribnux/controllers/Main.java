package com.tataadin.my.id.locationpickeribnux.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.View;
import com.ibnux.locationpicker.LocationPickerActivity;
import com.tataadin.my.id.locationpickeribnux.databinding.ActivityMainBinding;
import com.tataadin.my.id.locationpickeribnux.models.BaseValue;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Main implements View.OnClickListener{
    private final Context appContext;
    private final ActivityMainBinding binding;
    private Activity appActivity;

    public Main(Context appContext, ActivityMainBinding binding){
        this.appContext = appContext;
        this.binding = binding;
        init();
    }

    private void init(){
        appActivity = (Activity)appContext;
        binding.btnCariLokasi.setOnClickListener(this);
    }

    public void setInformasi(Intent data){
        String lat = data.getStringExtra("lat");
        String lon = data.getStringExtra("lon");
        //to double
        double latitude = Double.parseDouble(lat);
        double longitude = Double.parseDouble(lon);

        Geocoder geocoder = new Geocoder(appContext, Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                bindView(address);
            }
        } catch (IOException e) {
            Log.e("Location Address Loader", "Unable connect to Geocoder", e);
        }
    }

    private void bindView(Address address){
        binding.etAlamat.setText(address.getAddressLine(0));
        binding.etJalan.setText(address.getThoroughfare());
        binding.etDesa.setText(address.getSubLocality());
        binding.etKecamatan.setText(address.getLocality());
        binding.etKabupaten.setText(address.getSubAdminArea());
        binding.etProvinsi.setText(address.getAdminArea());
        binding.etNegara.setText(address.getCountryName());
    }

    @Override
    public void onClick(View view) {
        if(view == binding.btnCariLokasi){
            Intent locationPickerIntent = new Intent(appContext, LocationPickerActivity.class);
            appActivity.startActivityForResult(locationPickerIntent, BaseValue.LocationPicker);
        }
    }
}
