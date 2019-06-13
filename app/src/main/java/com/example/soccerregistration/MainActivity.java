package com.example.soccerregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] countries = { "Kenya", "Uganda", "Ethiopia", "Rwanda", "Tanzania" };
    String[] counties = { "Nairobi", "Kiambu", "Machakos", "Mombasa", "Nakuru" };
    Button button_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_next=findViewById(R.id.btn_next);

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent personal_info = new Intent(getApplicationContext(), PersonalInformation.class);
                startActivity(personal_info);
            }
        });

        //spinner country
        Spinner country =findViewById(R.id.country);
        ArrayAdapter<String> adaptercountry = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, countries);
        adaptercountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(adaptercountry);
        country.setOnItemSelectedListener(this);

        //spinner county
        Spinner county =findViewById(R.id.county);
        ArrayAdapter<String> adaptercounty = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, counties);
        adaptercounty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        county.setAdapter(adaptercounty);
        county.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

