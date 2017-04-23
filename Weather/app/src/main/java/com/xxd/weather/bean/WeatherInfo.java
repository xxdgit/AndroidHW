package com.xxd.weather.bean;

import java.util.List;

/**
 * Created by j on 2017/4/23.
 */

public class WeatherInfo {
    private String city; //城市
    private String aqi;  //城市id
    private String temp;  //温度
    private String ganmao;  //风向
    private List<DayWeatherInfo> forecast; //各星期数据
    private DayWeatherInfo yesterday; //昨天数据

    @Override
    public String toString(){
        return "city =" + city +", "+
                "aqi =" + aqi +", "+
                "wendu =" + temp +", "+
                "ganmao =" + ganmao +", "+
                "forecast =" + forecast +", "+
                "yesterday =" + yesterday;
    }

    public String getAqi() {
        return aqi;
    }

    public String getCity() {
        return city;
    }

    public String getGanmao() {
        return ganmao;
    }

    public List<DayWeatherInfo> getForecast() {
        return forecast;
    }

    public DayWeatherInfo getYesterday() {
        return yesterday;
    }

    public String gettemp() {
        return temp;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public void setForecast(List<DayWeatherInfo> forecast) {
        this.forecast = forecast;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public void settemp(String wendu) {
        this.temp = wendu;
    }

    public void setYesterday(DayWeatherInfo yesterday) {
        this.yesterday = yesterday;
    }
}
