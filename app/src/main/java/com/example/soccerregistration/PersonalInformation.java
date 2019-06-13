package com.example.soccerregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PersonalInformation extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button button_next2;
    Button button_previous;
    String[] relationship = { "Self", "Brother", "Sister", "Mother" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        //spinner country
        Spinner relation =findViewById(R.id.relationship_player);
        ArrayAdapter<String> adaptercountry = new ArrayAdapter<String>(PersonalInformation.this, android.R.layout.simple_spinner_item, relationship);
        adaptercountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        relation.setAdapter(adaptercountry);
        relation.setOnItemSelectedListener(this);

        button_next2=findViewById(R.id.btn_next2);
        button_previous=findViewById(R.id.btn_previous);

        button_next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent medical_info = new Intent(getApplicationContext(), MedicalInformation.class);
                startActivity(medical_info);
            }
        });


        button_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
