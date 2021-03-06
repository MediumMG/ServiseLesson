package com.trinkmobiles.serviseexample;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isServiceRunning(ServiceExample.class))
            startService(new Intent(MainActivity.this, ServiceExample.class));

        findViewById(R.id.activity_main_stop_service).setOnClickListener(new StopOnClick());

    }

    private boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private class StopOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (isServiceRunning(ServiceExample.class))
                stopService(new Intent(MainActivity.this, ServiceExample.class));
        }
    }

}
