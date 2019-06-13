package com.example.soccerregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersonalInformation extends AppCompatActivity{
    Button button_next2;
    Button button_previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

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

}
