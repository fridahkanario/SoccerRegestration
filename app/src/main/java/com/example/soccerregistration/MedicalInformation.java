package com.example.soccerregistration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MedicalInformation extends AppCompatActivity{
    public static final String url = "http://192.168.254.86:8080/register";
    private static final String TAG = "MainActivity";
    JSONObject jsonObject = null;


    Button button_previous;
    Button button_next3;
    EditText challenges;
    EditText other_conditions;
    EditText physician_name;
    EditText physician_phone;
    EditText insurance_co;
    EditText medical_phone;
    EditText policy_no;
    EditText holder_name;
    EditText player_signature;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_information);

        String json = getIntent().getStringExtra("personalInfo");

        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        challenges = findViewById(R.id.challenges);
        other_conditions = findViewById(R.id.other_conditions);
        physician_name = findViewById(R.id.physician_name);
        physician_phone = findViewById(R.id.physician_phone);
        insurance_co = findViewById(R.id.insurance_co);
        medical_phone = findViewById(R.id.medical_phone);
        policy_no = findViewById(R.id.policy_no);
        holder_name = findViewById(R.id.holder_name);
        player_signature = findViewById(R.id.player_signature);




        button_previous=findViewById(R.id.btn_previous);
        button_next3 = findViewById(R.id.btn_next3);

        button_next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String challenges2= challenges.getText().toString();
                String other_conditions2=other_conditions.getText().toString();
                String physician_name2= physician_name.getText().toString();
                String physician_phone2=physician_phone.getText().toString();
                String insurance_co2= insurance_co.getText().toString();
                String medical_phone2=medical_phone.getText().toString();
                String policy_no2= policy_no.getText().toString();
                String holder_name2=holder_name.getText().toString();
                String player_signature2=player_signature.getText().toString();


                try {
                    jsonObject.put("allergies", challenges2);
                    jsonObject.put("other_medical_conditions", other_conditions2);
                    jsonObject.put("physician_name", physician_name2);
                    jsonObject.put("physician_phone", physician_phone2);
                    jsonObject.put("medical_insurance_company", insurance_co2);
                    jsonObject.put("medical_phone", medical_phone2);
                    jsonObject.put("policy_no", policy_no2);
                    jsonObject.put("policyholder_name", holder_name2);
                    jsonObject.put("player_signature", player_signature2);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    register();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent home= new Intent(getApplicationContext(), MainActivity.class);

                startActivity(home);


            }
        });


        button_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent personal_info_Activity= new Intent(getApplicationContext(), PersonalInformation.class);

                startActivity(personal_info_Activity);
            }
        });



    }


    //send data to the database
    public void register() throws JSONException {

        Log.d(TAG, String.valueOf(jsonObject));
        //club_name.getText().toString();
        //player_name.getText().toString();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getBaseContext(), "RegistrationModel successful", Toast.LENGTH_LONG).show();

                        Log.d("Response ftffftytgyy", String.valueOf(response));
                    }
                },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(getBaseContext(), "Register failed", Toast.LENGTH_LONG).show();
                                //onLoginFailed();
                                Log.d("Error.Response", "error");
                            }
                        })
//                ) {
//            @Override
//            protected Map<String, String> getParams()
//            {
//                Map<String, String>  params = new HashMap<>();
//                params.put("Content-Type", "application/json");
//                // params.put("country", String.valueOf(countries));
//                // params.put("county", String.valueOf(counties));
//                // params.put("club_name", String.valueOf(club_name));
//                // params.put("player_name", String.valueOf(player_name));
//
//                return params;
//            }
       //
                        ;
        // MySingleton.getInstance(this).addToRequestQueue(postRequest);
        Volley.newRequestQueue(this).add(jsonObjectRequest);

        Log.d(TAG, "jhjhjhjhh jhjkhkj");

    }

}
