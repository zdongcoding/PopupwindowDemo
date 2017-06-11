package com.github.zdongcoding.popupwindowdemo;

import android.os.Build;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowManager wm = this.getWindowManager();

        int screenWidth = wm.getDefaultDisplay().getWidth();
        final int screenHeight = wm.getDefaultDisplay().getHeight();
        final Button button = (Button) findViewById(R.id.btn);
        final View view = getLayoutInflater().inflate(R.layout.pop_layou, null);
        button.setText("api=" + Build.VERSION.SDK_INT);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);

                if (Build.VERSION.SDK_INT < 24) {
                    popupWindow.showAsDropDown(button);
                } else {
                    int[] location = new int[2];
                    // 获取控件在屏幕的位置
                    button.getLocationOnScreen(location);
                    if (Build.VERSION.SDK_INT == 25) {
                        int tempheight = popupWindow.getHeight();
                        if (tempheight == WindowManager.LayoutParams.MATCH_PARENT || screenHeight <= tempheight) {
                            popupWindow.setHeight(screenHeight - location[1] - button.getHeight());
                        }
                    }
                    popupWindow.showAtLocation(button, Gravity.NO_GRAVITY, location[0], location[1] + button.getHeight());
                }
            }
        });
    }
}
