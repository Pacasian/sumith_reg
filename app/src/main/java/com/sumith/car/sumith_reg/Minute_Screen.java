package com.sumith.car.sumith_reg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class Minute_Screen extends AppCompatActivity {
    private TextSwitcher textSwitcher;
    private final String[] service={"Welcome to Sumith"," Sign in and Sign out pages "};
    private int mposition=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minute__screen);
        textSwitcher=findViewById(R.id.tw);
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView=new TextView(getApplicationContext());
                textView.setTextSize(19);
                return textView;
            }
        });
        thread();
    }
    public void thread(){
    Thread splashThread= new Thread()
    {
        @Override
        public void run()
        {
            try
            {
                int wait=0;
                while(wait<3000)
                {
                    sleep(100);
                    wait+=100;
                    /*if (wait<=1500){
                        textSwitcher.setText(service[0]);
                    }else if(wait>1500){
                        textSwitcher.setText(service[1]);
                    }*/
                }
            } catch (InterruptedException e) {

            }finally {
                finish();
                Intent n=new Intent(Minute_Screen.this,MainActivity.class);
                startActivity(n);
            }
        }
    };
        splashThread.start();
}
}

