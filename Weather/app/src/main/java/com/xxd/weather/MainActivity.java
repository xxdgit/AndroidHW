package com.xxd.weather;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xxd.weather.bean.DayWeatherInfo;
import com.xxd.weather.bean.WeatherInfo;
import com.xxd.weather.view.CircleViewPager;
import com.xxd.weather.view.MyScrollView;
import com.xxd.weather.view.MyViewPagerDots;
import com.xxd.weather.view.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    public static List<String> allCityNameList;

    private Map<String,Integer> typeMap = getTypeMap();

    private Map<String,Integer> getTypeMap(){
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("晴",R.drawable.ww0);
        map.put("多云",R.drawable.ww1);
        map.put("阴",R.drawable.ww2);
        map.put("阵雨",R.drawable.ww3);
        map.put("晴",R.drawable.ww4);
        map.put("晴",R.drawable.ww5);
        map.put("晴",R.drawable.ww6);
        map.put("小雨",R.drawable.ww7);
        map.put("中雨",R.drawable.ww8);
        map.put("大雨",R.drawable.ww9);
        map.put("暴雨",R.drawable.ww10);
        map.put("晴",R.drawable.ww13);
        map.put("小雪",R.drawable.ww14);
        map.put("晴",R.drawable.ww15);
        map.put("晴",R.drawable.ww16);
        map.put("晴",R.drawable.ww17);
        map.put("晴",R.drawable.ww18);
        map.put("晴",R.drawable.ww19);
        map.put("晴",R.drawable.ww20);
        map.put("晴",R.drawable.ww29);
        map.put("晴",R.drawable.ww30);
        map.put("晴",R.drawable.ww31);
        map.put("晴",R.drawable.ww32);
        map.put("晴",R.drawable.ww33);
        map.put("晴",R.drawable.ww34);
        map.put("晴",R.drawable.ww35);
        map.put("晴",R.drawable.ww36);
        map.put("晴",R.drawable.ww45);
        map.put("晴",R.drawable.ww0);

        return map;
    }

    private CircleViewPager myViewPager;
    private MyViewPagerDots myViewPagerDots;
    private ViewPagerAdapter myPagerAdapter;

    private ImageView optionImage;
    private ImageView shareImage;

    private MyScrollView myScrollView;

    private int myScrollViewPosY = 0;
    private int myScrollViewDownPosY = 0;
    private int myScrollViewUpPosY = 0;

    private TextView cityTemp;
    private TextView cityType;
    private TextView cityName;

    private LinearLayout scaleLL;
    private FrameLayout scaleAnimLL;

    private ImageView animCloud1;
    private ImageView animCloud2;

    private int scaleMaxHeight;
    private int scaleMinHeight = 250;

    private int scaleMaxTempSize;
    private int scaleMinTempSize = 80;

    private int scaleMaxTypeSize;
    private int scaleMinTypeSize = 0;

    private int scaleMaxCitySize;
    private int scaleMinCitySize = 40;

    private List<Map<String,String>> todayWeatherInfo;

    public static final String HOST_NAME = "http://wthrcdn.etouch.cn/weather_mini?citykey=";

    public static final int UPDATE = 0;
    public static final int FAIL_TO_CONNECTION = 1;
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == UPDATE){
                todayWeatherInfo = getTodayWeatherInfos((List<WeatherInfo>) msg.obj);//储存各城市今天的信息信息更新
                updateAllViews((List<WeatherInfo>) msg.obj);
                Toast.makeText(MainActivity.this,"刷新成功",Toast.LENGTH_SHORT).show();
            }else if(msg.what == FAIL_TO_CONNECTION){
                Toast.makeText(MainActivity.this,"网络异常",Toast.LENGTH_SHORT).show();
            }
        }
    };

    private List<Map<String,String>> getTodayWeatherInfos(List<WeatherInfo> weatherInfos){
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        for (WeatherInfo weatherInfo:weatherInfos){
            Map<String,String> map = new HashMap<String, String>();
            map.put("cityName",weatherInfo.getCity());
            map.put("cityTemp",weatherInfo.gettemp());
            DayWeatherInfo dayWeatherInfo = weatherInfo.getForecast().get(0);
            map.put("cityType",dayWeatherInfo.getType());
            list.add(map);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
