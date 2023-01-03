package com.example.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button btn_cityID, btn_getWeatherByID, btn_getWeatherByName;
    EditText et_dataInput;
    ListView lv_weatherReport;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign values to each control on the layout.
        btn_cityID = findViewById(R.id.btn_getCityID);
        btn_getWeatherByID = findViewById(R.id.btn_getWeatherByCityID);
        btn_getWeatherByName = findViewById(R.id.btn_getWeatherByCityName);

        et_dataInput = findViewById(R.id.et_dataInput);
        lv_weatherReport = findViewById(R.id.lv_weatherReports);


/////////////////////RESPONSE FROM JSON ARRAY REQUEST///////////////////////////////////////
//        //click listeners for each button.
//        btn_cityID.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Instantiate the RequestQueue.
//                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//                String url = "https://api.openweathermap.org/data/2.5/weather?q=London&appid=8d5e9ad19165eb94a0849441293c02a0";
//
//                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        String cityID = "";
//                        try {
//                            JSONObject cityInfo = response.getJSONObject(0);
//                            cityID = cityInfo.getString('woeid');
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        Toast.makeText(MainActivity.this, "City ID= " + cityID.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this, "" + error, Toast.LENGTH_SHORT).show();
//                        // Get a reference to the TextView
//                        TextView errorTextView = findViewById(R.id.et_dataInput);
//                        // Set the text of the TextView to the error message
//                        errorTextView.setText(error.toString());
//                        // Show an error on the console
//                        Log.e("MyActivity", "An error occurred: " + error.toString());
//                    }
//                });
//                queue.add(request);
/////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////RESPONSE FROM JSON OBJECT REQUEST//////////////////////////////////
        btn_cityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Instantiate the RequestQueue.
                ////////////USE SINGLETON TO GET A REQUESTQUEUE///////////////////////
                //RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://api.openweathermap.org/data/2.5/weather?q="+ et_dataInput.getText().toString() + "&appid=8d5e9ad19165eb94a0849441293c02a0";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//////////////////////////////Show API Response lon and lat ///////////////////////////////////////////////
//                            JSONObject coord = response.getJSONObject("coord");
//                            double lon = coord.getDouble("lon");
//                            double lat = coord.getDouble("lat");
//                            Toast.makeText(MainActivity.this,"lon: "+lon+",lat: "+ lat, Toast.LENGTH_SHORT).show();
///////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////Show API Response for weather{}////////////////////////////////////////////////
//                            JSONArray weatherArray = response.getJSONArray("weather");
//                            JSONObject weather = weatherArray.getJSONObject(0);
//                            String main = weather.getString("main");
//                            String description = weather.getString("description");
//                            Toast.makeText(MainActivity.this, "Weather: " + main + ", " + description, Toast.LENGTH_SHORT).show();
///////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////Show {id name cod} from API response////////////////////////////////////////////////////
                            int id = response.getInt("id");
                            String name = response.getString("name");
                            int cod = response.getInt("cod");
                            Toast.makeText(MainActivity.this, "ID: " + id + ", Name: " + name + ", Cod: " + cod, Toast.LENGTH_SHORT).show();
                            // Get a reference to the TextView
                            TextView successTextView = findViewById(R.id.et_dataInput);
                            // Clear the text of the TextView
                            successTextView.setText("");
                            // Set the text of the TextView to the success message
                            successTextView.setText("ID: " + id + ", Name: " + name + ", Cod: " + cod);
///////////////////////////////////////////////////////////////////////////////////////////////////////////
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                        // Show an success on the console
                        Log.e("MyActivity", "SUCCESS!!: " + response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ////Volley Error
//                        Toast.makeText(MainActivity.this, "" + error, Toast.LENGTH_SHORT).show();
//                        // Get a reference to the TextView
//                        TextView errorTextView = findViewById(R.id.et_dataInput);
//                        // Set the text of the TextView to the error message
//                        errorTextView.setText(error.toString());
//                        // Show an error on the console
//                        Log.e("MyActivity", "ERROR!!: " + error.toString());

                        //// Parse the error response as a JSON object Error
                        try {
                            JSONObject errorResponse = new JSONObject(new String(error.networkResponse.data));
                            String errorMessage = errorResponse.getString("message");
                            // Get a reference to the TextView
                            TextView errorTextView = findViewById(R.id.et_dataInput);
                            // Set the text of the TextView to the error message
                            errorTextView.setText(errorMessage);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                ////////////USE SINGLETON TO GET A REQUESTQUEUE/////////////////////////////////////
                //https://google.github.io/volley/requestqueue.html
                // Add a request (in this example, called stringRequest) to your RequestQueue.
                MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);
                //queue.add(request);
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////


        // Toast.makeText(MainActivity.this, "You clicked me", Toast.LENGTH_SHORT).show();
        btn_getWeatherByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You clicked me 2", Toast.LENGTH_SHORT).show();
            }
        });
        btn_getWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You typed " + et_dataInput.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}