package com.xxd.layouttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by j on 2017/3/22.
 */

public class Problem extends Activity{

    private RadioGroup radio_group;
    private Button bt_ok;
    private Button bt_cancel;
    private RadioButton rb_on;
    private RadioButton rb_at;
    private RadioButton rb_of;
    private RadioButton rb_in;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.layout);

        radio_group=(RadioGroup)findViewById(R.id.radio_group);
        rb_on=(RadioButton)findViewById(R.id.rb_on);
        rb_at=(RadioButton)findViewById(R.id.rb_at);
        rb_of=(RadioButton)findViewById(R.id.rb_of);
        rb_in=(RadioButton)findViewById(R.id.rb_in);
        bt_ok=(Button)findViewById(R.id.bt_ok);
        bt_cancel=(Button)findViewById(R.id.bt_cancel);

        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer="";
                if(rb_on.isChecked())
                    answer="on";
                else if(rb_at.isChecked())
                    answer="at";
                else if(rb_of.isChecked())
                    answer="of";
                else if(rb_in.isChecked())
                    answer="in";

                Intent intent=new Intent();
                intent.setClass(Problem.this,MainActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("answer",answer);
                intent.putExtras(bundle);
                startActivityForResult(intent,0); //0是请求码
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio_group.clearCheck();
                setTitle("");
            }
        });

    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==0 && resultCode==0){
            Bundle bundle=data.getExtras();
            String answer=bundle.getString("answer");
        }
    }

}
