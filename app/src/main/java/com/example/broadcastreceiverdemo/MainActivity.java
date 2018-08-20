package com.example.broadcastreceiverdemo;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    private TextView msgTextView;
    private TextView contactTextView;
    private Bundle mBundle;
    TextView mText;
	


    SmsBroadcastReceiver mReceiver = new SmsBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        this.registerReceiver(mReceiver, filter);

        msgTextView = (TextView)findViewById(R.id.msgTextview);
        contactTextView=(TextView)findViewById(R.id.contactTextview);

        mBundle = getIntent().getExtras();
        if (mBundle != null)
        {
            msgTextView.setText(mBundle.getString("message"));
            contactTextView.setText(mBundle.getString("phoneNo"));
        }

    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
