package com.example.soccerregistration;

import android.content.Intent;
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

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//This is the register activity
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    JSONObject jsonBody = new JSONObject();

    List<String> countries = Arrays.asList("Kenya", "Uganda", "Ethiopia", "Rwanda", "Tanzania");
    List<String> counties = Arrays.asList("Nairobi", "Kiambu", "Machakos", "Mombasa", "Nakuru" );
    Button button_next;
    EditText club_name;
    EditText player_name;
    //RequestQueue requestQueue;
    Spinner county;
    Spinner country;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //spinner country
        county =findViewById(R.id.county);
        country =findViewById(R.id.country);
        ArrayAdapter<String> adaptercountry = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, countries);
        adaptercountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(adaptercountry);
        country.setOnItemSelectedListener(this);

        //spinner county

        ArrayAdapter<String> adaptercounty = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, counties);
        adaptercounty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        county.setAdapter(adaptercounty);
        county.setOnItemSelectedListener(this);

        club_name=findViewById(R.id.club_name);
        player_name=findViewById(R.id.player_name);


        button_next=findViewById(R.id.btn_next);

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    register();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent personal_info = new Intent(getApplicationContext(), PersonalInformation.class);
                personal_info.putExtra("clubInfo", String.valueOf(jsonBody));
                startActivity(personal_info);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    //send data to the database
    public void register() throws JSONException {

       // Log.d(TAG, "register");
        //club_name.getText().toString();
        //player_name.getText().toString();


        jsonBody.put("club_name",club_name.getText().toString());
        jsonBody.put("player_name",player_name.getText().toString());
        jsonBody.put("county",String.valueOf(county.getSelectedItem()));
        jsonBody.put("country",String.valueOf(country.getSelectedItem()));

    }

    @Override
    public void onBackPressed() {
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
    }
}

