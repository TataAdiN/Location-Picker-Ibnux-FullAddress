package com.tataadin.my.id.locationpickeribnux.views;

import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.tataadin.my.id.locationpickeribnux.controllers.Main;
import com.tataadin.my.locationpickeribnux.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private Main controlMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        controlMain = new Main(this, binding);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
            if (requestCode == 4268){
                controlMain.setInformasi(data);
            }
        }
    }
}
