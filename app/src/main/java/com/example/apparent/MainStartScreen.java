package com.example.apparent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainStartScreen extends AppCompatActivity {
    Button btnTenants;
    Button btnHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        btnHouse = (Button) findViewById(R.id.idBtnGoToManagement) ;
        btnTenants =  (Button) findViewById(R.id.idBtnGoToAppartment);





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_start_screen);
    }
    public void goToApparment(View view){
        Intent intent = new Intent(getApplicationContext(), ExperimentViewScroll.class);
        startActivity(intent);
    }

    public void goToAddApartment(View view){
        Intent intent = new Intent(getApplicationContext(), AddApartmentView.class);
        startActivity(intent);
    }


}