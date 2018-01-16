package com.eebbk.reflectiondemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.eebbk.reflectiondemo.util.ReflectionUtil;

public class MainActivity extends AppCompatActivity {

    private Button mClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mClick = findViewById(R.id.click);
        mClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "设置了普通的onClick事件...", Toast.LENGTH_SHORT).show();
            }
        });
        ReflectionUtil.invokeWithCallback("android.view.View", "setOnClickListener",
                new String[]{"android.view.View$OnClickListener"}, mClick, null, new ClickHandlerImpl(4));
    }

    public static final Handler mMainHandler = new Handler(){
        @Override
        public void handleMessage(final Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.d("invoke_click", "invoke method...2222 ---> "+msg.arg1);
                    break;
                default:
                    break;
            }
        }
    };


}
