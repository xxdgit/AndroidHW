package com.xxd.weather.bean;

/**
 * Created by j on 2017/4/23.
 */

public class DayWeatherInfo {

    /* [{"fengxiang":"无持续风向","fengli":"微风级","high":"高温 15℃","type":"阴","low":"低温 9℃","date":"19日星期二"},*/

    private String fengxiang;  //风向
    private String fengli;    //风力
    private String high;
    private String type;
    private String low;
    private String date;

    @Override
    public String toString() {
        return "[fengxiang =" + fengxiang + ", " +
                "fengli =" + fengli + ", " +
                "high =" + high + ", " +
                "type =" + type + ", " +
                "low =" + low + ", " +
                "yesterday =" + date + "]";
    }

    public String getDate() {
        return date;
    }

    public String getFengli() {
        return fengli;
    }

    public String getFengxiang() {
        return fengxiang;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getType() {
        return type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }

    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public void setType(String type) {
        this.type = type;
    }
}
