package com.example.weatherapiapp;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {
    //https://openweathermap.org/current
    public static final String API_WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    //https://openweathermap.org/forecast5
    public static final String API_FORECAST_URL = "https://api.openweathermap.org/data/2.5/forecast?q=";
    public static final String API_KEY = "&appid=8d5e9ad19165eb94a0849441293c02a0";

    Context context;
    String cityID;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    //Implementing async callback method
    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String cityID);
    }

    public void getCityID(String cityName, final VolleyResponseListener volleyResponseListener) {
        // Instantiate the RequestQueue.
        ////////////USE SINGLETON TO GET A REQUESTQUEUE///////////////////////
        //if no sinlgeton is used use the line below with queue.add(request);
        //RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = API_WEATHER_URL + cityName + API_KEY;
        //////////////////////////RESPONSE FROM JSON OBJECT REQUEST//////////////////////////////////
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
                    int cityID = response.getInt("id");
                    String name = response.getString("name");
                    int cod = response.getInt("cod");
                    //This is worked. but it didnt return the id number to MainActivity
                    //Toast.makeText(context, "ID: " + cityID + ", Name: " + name + ", Cod: " + cod, Toast.LENGTH_SHORT).show();

                    // Get a reference to the TextView
                    ////TextView successTextView = findViewById(R.id.et_dataInput);
                    // Clear the text of the TextView
                    ////successTextView.setText("");
                    // Set the text of the TextView to the success message
                    ////successTextView.setText("ID: " + id + ", Name: " + name + ", Cod: " + cod);
                    volleyResponseListener.onResponse(String.valueOf(cityID));
///////////////////////////////////////////////////////////////////////////////////////////////////////////
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //volleyResponseListener.onResponse(String.valueOf(cityID));
                //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                //volleyResponseListener.onResponse(cityID);

                // Show an success on the console
                Log.e("MyActivity", "SUCCESS!!: " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("Something wrong");
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
                    ////TextView errorTextView = findViewById(R.id.et_dataInput);
                    // Set the text of the TextView to the error message
                    ////errorTextView.setText(errorMessage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        ////////////USE SINGLETON TO GET A REQUESTQUEUE/////////////////////////////////////
        //https://google.github.io/volley/requestqueue.html
        // Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(request);
        //queue.add(request);

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
        //returned a NULL. problem!
        //return cityID;

    }

    //Implementing async callback method
    public interface ForeCastByIDResponse {
        void onError(String message);

        void onResponse(List<WeatherReportModel> weatherReportModels);
    }

    //TODO
    public void getCityForecastByID(String cityID, ForeCastByIDResponse foreCastByIDResponse) {
        List<WeatherReportModel> weatherReportModels = new ArrayList<>();

        String url = API_FORECAST_URL + cityID + API_KEY;
        // get the json object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    JSONArray listArray = response.getJSONArray("list");

                    //String dt_txt = firstObject.getString("dt_txt");
                    //Toast.makeText(MainActivity.this, "dt_txt= " + dt_txt.toString(), Toast.LENGTH_SHORT).show();
                    //JSONArray weatherArray = response.getJSONArray("weather");
                    //JSONObject weather = weatherArray.getJSONObject(0);
//                    JSONArray listArray = response.getJSONArray("list");
//                    JSONObject firstUpdate = listArray.getJSONObject(0);
//                    String firstUpdate.dt_txt


                    //WeatherReportModel one_day_weather = new WeatherReportModel();

                    for (int i = 0; i < listArray.length(); i++) {
                    WeatherReportModel one_day_weather = new WeatherReportModel();

                    JSONObject firstObject = listArray.getJSONObject(i);
                    JSONObject mainObject = firstObject.getJSONObject("main");
//                    //Get the first item in the array
//                    WeatherReportModel first_day = new WeatherReportModel();
//
//                    JSONObject first_day_list = (JSONObject) list.get(0);
//
//                    first_day.setCity_id(first_day_list.getInt("id"));
//                    first_day.setCity_name(first_day_list.getString("name"));
//                    first_day.setList_weather_main(first_day_list.getString("main"));
//                    first_day.setList_weather_description(first_day_list.getString("description"));
                    one_day_weather.setList_dt_txt(firstObject.getString("dt_txt"));
                    one_day_weather.setList_main_temp(mainObject.getLong("temp"));
//                    first_day.setList_main_temp_min(first_day_list.getLong("temp_min"));
//                    first_day.setList_main_temp_max(first_day_list.getLong("temp_max"));
//                    first_day.setList_main_humidity(first_day_list.getLong("humidity"));
//                    first_day.setList_wind_speed(first_day_list.getLong("speed"));
                        weatherReportModels.add(one_day_weather);
                    }
                    foreCastByIDResponse.onResponse(weatherReportModels);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
        // get the property called "list" which is an array

        // get each item in the array and assign it to a new WeatherReportModel
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

}
