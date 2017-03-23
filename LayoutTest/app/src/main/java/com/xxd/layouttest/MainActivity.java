package com.xxd.layouttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {


    private Intent intent;
    private Bundle bundle;
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)findViewById(R.id.back) ;
        textView=(TextView)findViewById(R.id.show_answer);

        bundle=this.getIntent().getExtras();
        String answer=bundle.getString("answer");

        String setText="";
        if(answer.equals("in")){
            setText="正确";
        }else{
            setText="错误";
        }
        textView.setText("您的答案"+setText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.setResult(RESULT_OK,intent);
                MainActivity.this.finish();
            }
        });
    }
}
