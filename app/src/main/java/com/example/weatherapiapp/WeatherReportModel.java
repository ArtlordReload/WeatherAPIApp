package com.example.weatherapiapp;

public class WeatherReportModel {
    //    private int city_id;
//    private String city_name;
//
//    private String list_weather_main;
//    private String list_weather_description;
    private String list_dt_txt;
    private float list_main_temp;
//    private float list_main_temp_min;
//    private float list_main_temp_max;
//    private float list_main_humidity;
//    private float list_wind_speed;

    //Generated to toString()
    @Override
    public String toString() {
        return "Report" +
                "Date: " + list_dt_txt +
                ",Temperature: " + list_main_temp;
    }
    public WeatherReportModel() {

    }

    //Generated Constructor
    public WeatherReportModel(String list_dt_txt, float list_main_temp) {
        this.list_dt_txt = list_dt_txt;
        this.list_main_temp = list_main_temp;
    }

    //Generated Getters and Setters
    public String getList_dt_txt() {
        return list_dt_txt;
    }

    public void setList_dt_txt(String list_dt_txt) {
        this.list_dt_txt = list_dt_txt;
    }

    public float getList_main_temp() {
        return list_main_temp;
    }

    public void setList_main_temp(float list_main_temp) {
        this.list_main_temp = list_main_temp;
    }

    //    public WeatherReportModel() {
//        this.list_dt_txt = list_dt_txt;
//    }
//
//    public String getList_dt_txt() {
//        return list_dt_txt;
//    }
//
//    public void setList_dt_txt(String list_dt_txt) {
//        this.list_dt_txt = list_dt_txt;
//    }


    //Generated Constructor
//    public WeatherReportModel(int city_id, String city_name, String list_weather_main, String list_weather_description, String list_dt_txt, float list_main_temp, float list_main_temp_min, float list_main_temp_max, float list_main_humidity, float list_wind_speed) {
//        this.city_id = city_id;
//        this.city_name = city_name;
//        this.list_weather_main = list_weather_main;
//        this.list_weather_description = list_weather_description;
//        this.list_dt_txt = list_dt_txt;
//        this.list_main_temp = list_main_temp;
//        this.list_main_temp_min = list_main_temp_min;
//        this.list_main_temp_max = list_main_temp_max;
//        this.list_main_humidity = list_main_humidity;
//        this.list_wind_speed = list_wind_speed;
//    }
//
//    public WeatherReportModel() {
//    }
//
//    @Override
//    public String toString() {
//        return "WeatherReportModel{" +
//                "city_id=" + city_id +
//                ", city_name='" + city_name + '\'' +
//                ", list_weather_main='" + list_weather_main + '\'' +
//                ", list_weather_description='" + list_weather_description + '\'' +
//                ", list_dt_txt='" + list_dt_txt + '\'' +
//                ", list_main_temp=" + list_main_temp +
//                ", list_main_temp_min=" + list_main_temp_min +
//                ", list_main_temp_max=" + list_main_temp_max +
//                ", list_main_humidity=" + list_main_humidity +
//                ", list_wind_speed=" + list_wind_speed +
//                '}';
//    }
//
//    //Generated Getters and Setters
//
//    public int getCity_id() {
//        return city_id;
//    }
//
//    public void setCity_id(int city_id) {
//        this.city_id = city_id;
//    }
//
//    public String getCity_name() {
//        return city_name;
//    }
//
//    public void setCity_name(String city_name) {
//        this.city_name = city_name;
//    }
//
//    public String getList_weather_main() {
//        return list_weather_main;
//    }
//
//    public void setList_weather_main(String list_weather_main) {
//        this.list_weather_main = list_weather_main;
//    }
//
//    public String getList_weather_description() {
//        return list_weather_description;
//    }
//
//    public void setList_weather_description(String list_weather_description) {
//        this.list_weather_description = list_weather_description;
//    }
//
//    public float getList_main_temp() {
//        return list_main_temp;
//    }
//
//    public void setList_main_temp(float list_main_temp) {
//        this.list_main_temp = list_main_temp;
//    }
//
//    public float getList_main_temp_min() {
//        return list_main_temp_min;
//    }
//
//    public void setList_main_temp_min(float list_main_temp_min) {
//        this.list_main_temp_min = list_main_temp_min;
//    }
//
//    public float getList_main_temp_max() {
//        return list_main_temp_max;
//    }
//
//    public void setList_main_temp_max(float list_main_temp_max) {
//        this.list_main_temp_max = list_main_temp_max;
//    }
//
//    public float getList_main_humidity() {
//        return list_main_humidity;
//    }
//
//    public void setList_main_humidity(float list_main_humidity) {
//        this.list_main_humidity = list_main_humidity;
//    }
//
//    public float getList_wind_speed() {
//        return list_wind_speed;
//    }
//
//    public void setList_wind_speed(float list_wind_speed) {
//        this.list_wind_speed = list_wind_speed;
//    }
//
//    public String getList_dt_txt() {
//        return list_dt_txt;
//    }
//
//    public void setList_dt_txt(String list_dt_txt) {
//        this.list_dt_txt = list_dt_txt;
//    }
}