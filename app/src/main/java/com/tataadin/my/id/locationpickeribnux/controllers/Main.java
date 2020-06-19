package com.tataadin.my.id.locationpickeribnux.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.View;
import com.ibnux.locationpicker.LocationPickerActivity;
import com.tataadin.my.id.locationpickeribnux.models.BaseValue;
import com.tataadin.my.locationpickeribnux.databinding.ActivityMainBinding;

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
        String result = null;
        try {
            List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                    sb.append("\n");
                    sb.append(address.getAddressLine(i)).append(" : Alamat Full\n");
                }
                sb.append(address.getLocality()).append(" : Kecamatan\n");
                sb.append(address.getPostalCode()).append(" : Kode Pos\n");
                sb.append(address.getAdminArea()).append(" : Provinsi\n");
                sb.append(address.getSubAdminArea()).append(" : Kabupaten\n");
                sb.append(address.getCountryName()).append(" : Negara\n");
                sb.append(address.getSubLocality()).append(" : Desa/Keluarahan\n");
                sb.append(address.getThoroughfare()).append(" : Jalan\n");
                result = sb.toString();
            }
        } catch (IOException e) {
            Log.e("Location Address Loader", "Unable connect to Geocoder", e);
        } finally {
            Log.d("LOKASI", result);
        }
    }

    @Override
    public void onClick(View view) {
        if(view == binding.btnCariLokasi){
            Intent locationPickerIntent = new Intent(appContext, LocationPickerActivity.class);
            appActivity.startActivityForResult(locationPickerIntent, BaseValue.LocationPicker);
        }
    }
}
