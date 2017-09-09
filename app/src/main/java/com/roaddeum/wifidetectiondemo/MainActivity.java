package com.roaddeum.wifidetectiondemo;

import java.util.List;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wifidemoexample.R;

public class MainActivity extends Activity implements OnClickListener {
    private static final String TAG="WiFiDemo";
    WifiManager wifi;
    BroadcastReceiver receiver;
    TextView text;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView)findViewById(R.id.text);
        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(this);
//		get wifi status
        wifi=(WifiManager)getSystemService(Context.WIFI_SERVICE);
        WifiInfo info=wifi.getConnectionInfo();
        text.append("\n\nwifi status :"+info.toString());

//	list available network

        List<WifiConfiguration> configurations=wifi.getConfiguredNetworks();
        for(WifiConfiguration configuration :configurations){
            text.append("\n\n" +configuration.toString());
        }
//	register broadcast receiver
        if(receiver==null)
            receiver=new WifiScanner(this);
        registerReceiver(receiver, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        Log.d(TAG, "onCreate()");
    }
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        unregisterReceiver(receiver);
        super.onStop();
    }
    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        Toast.makeText(getApplicationContext(), "All Network seached !!",0).show();
        if(view.getId()==R.id.btn){
            Log.d(TAG, "onCreate() wifi.startScan()");
            wifi.startScan();
        }
    }
}
