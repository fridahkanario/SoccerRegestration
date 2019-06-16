package com.example.soccerregistration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PersonalInformation extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    JSONObject jsonObject;
    EditText birthday;
    RadioGroup gender;
    RadioButton radio_gender;
    EditText residence_name;
    EditText postalcode;
    EditText parent_name;
    EditText parent_phone;
    EditText parent_email;
    EditText parent2_mobile;
    EditText parent2_name;
    EditText parent2_home_phone;
    EditText parent2_email;
    EditText guardian1_name;
    EditText guardian1_phone;
    EditText guardian2_name;
    EditText guardian2_phone;
    EditText parent_home_phone;

    Button button_next2;
    Button button_previous;

    List<String> relationship_ = new ArrayList<>();
    String[] relationship = { "Self", "Brother", "Sister", "Mother" , "Guardian"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        String json = getIntent().getStringExtra("clubInfo");

        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        relationship_.add( "Self");
        relationship_.add( "Brother");
        relationship_.add( "Sister");
        relationship_.add( "Mother");
        relationship_.add( "Guardian");

        //spinner relationship
        final Spinner relationship =findViewById(R.id.relationship_player);
        ArrayAdapter<String> adapter_relationship = new ArrayAdapter<String>(PersonalInformation.this, android.R.layout.simple_spinner_item,  relationship_);
        adapter_relationship.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        relationship.setAdapter(adapter_relationship);
        relationship.setOnItemSelectedListener(this);

        birthday = findViewById(R.id.birthday);
        gender = findViewById(R.id.gender);
        residence_name = findViewById(R.id.residence_name);
        postalcode = findViewById(R.id.postalcode);
        parent_name = findViewById(R.id.parent_name);
        parent_phone = findViewById(R.id.parent_phone);
        parent_email = findViewById(R.id.parent_email);
        parent2_mobile = findViewById(R.id.parent2_mobile);
        parent2_name = findViewById(R.id.parent2_name);
        parent2_home_phone = findViewById(R.id.parent2_home_phone);
        parent2_email = findViewById(R.id.parent2_email);
        guardian1_name = findViewById(R.id.guardian1_name);
        guardian1_phone = findViewById(R.id.guardian1_phone);
        guardian2_name = findViewById(R.id.guardian2_name);
        guardian2_phone = findViewById(R.id.guardian2_phone);
        parent_home_phone = findViewById(R.id.parent_home_phone);



        button_next2=findViewById(R.id.btn_next2);
        button_previous=findViewById(R.id.btn_previous);


        //sharedpreferences = getSharedPreferences(com.example.soccerregistration.MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        button_next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = gender.getCheckedRadioButtonId();
                String birthday2= birthday.getText().toString();
                radio_gender = (RadioButton) findViewById(selectedId);
                String gender2= radio_gender.getText().toString();
               // String radioFemale2= radioFemale.getText().toString();
                String residence_name2=residence_name.getText().toString();
                String postalcode2= postalcode.getText().toString();
                String parent_name2=parent_name.getText().toString();
                String parent_phone2= parent_phone.getText().toString();
                String parent_home_phone2= parent_home_phone.getText().toString();
                String parent_email2=parent_email.getText().toString();
                String parent2_mobile2 = parent2_mobile.getText().toString();
                String parent2_name2=parent2_name.getText().toString();
                String parent2_home_phone2= parent2_home_phone.getText().toString();
                String parent2_email2=parent2_email.getText().toString();
                String guardian1_name2= guardian1_name.getText().toString();
                String guardian1_phone2=guardian1_phone.getText().toString();
                String guardian2_name2= guardian2_name.getText().toString();
                String guardian2_phone2=guardian2_phone.getText().toString();
                String relationship_player2= String.valueOf(relationship.getSelectedItem());



                try {
                    jsonObject.put("birthday",birthday2);
                    jsonObject.put("gender",gender2);
                    jsonObject.put("address",residence_name2);
                    jsonObject.put("postcode",postalcode2);
                    jsonObject.put("parent_name",parent_name2);
                    jsonObject.put("parent_mobile",parent_phone2);
                    jsonObject.put("parent_email",parent_email2);
                    jsonObject.put("parent2_mobile",parent2_mobile2);
                    jsonObject.put("parent2_name",parent2_name2);
                    jsonObject.put("parent2_home_phone",parent2_home_phone2);
                    jsonObject.put("parent2_email",parent2_email2);
                    jsonObject.put("guardian1_name",guardian1_name2);
                    jsonObject.put("guardian1_phone",guardian1_phone2);
                    jsonObject.put("guardian2_name",guardian2_name2);
                    jsonObject.put("guardian2_phone",guardian2_phone2);
                    jsonObject.put("parent_home_phone",parent_home_phone2);
                    jsonObject.put("relation_to_player",relationship_player2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent medical_info = new Intent(getApplicationContext(), MedicalInformation.class);
                medical_info.putExtra("personalInfo", String.valueOf(jsonObject));
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
