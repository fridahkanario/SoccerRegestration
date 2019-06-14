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
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String url = "http://localhost:8080/register";
    private static final String TAG = "MainActivity";


    String[] countries = { "Kenya", "Uganda", "Ethiopia", "Rwanda", "Tanzania" };
    String[] counties = { "Nairobi", "Kiambu", "Machakos", "Mombasa", "Nakuru" };
    Button button_next;
    EditText club_name;
    EditText player_name;
    //RequestQueue requestQueue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        club_name=findViewById(R.id.club_name);
        player_name=findViewById(R.id.player_name);


        button_next=findViewById(R.id.btn_next);

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register();
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


    //send data to the database
    public void register(){

        Log.d(TAG, "register");
        club_name.getText().toString();
        player_name.getText().toString();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getBaseContext(), "Login success", Toast.LENGTH_LONG).show();
                        //onLoginSuccess();
                        // response
                        Log.d("Response", String.valueOf(response));
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
                        }
                ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("country", String.valueOf(countries));
                params.put("county", String.valueOf(counties));
                params.put("club_name", String.valueOf(club_name));
                params.put("player_name", String.valueOf(player_name));

                return params;
            }
        };
        // MySingleton.getInstance(this).addToRequestQueue(postRequest);
        Volley.newRequestQueue(this).add(jsonObjectRequest);

    }
}

