package com.example.broadcastreceiverdemo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsBroadcastReceiver extends BroadcastReceiver {

    private Bundle mBundle;
    private String msg=null;
    private String phone_no=null;

    @Override
    public void onReceive(Context context, Intent intent) {


        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {

            mBundle = intent.getExtras();

            if (mBundle != null) {
                Object[] pdus = (Object[]) mBundle.get("pdus");   //PDUs used to encapsulate an SMS message and its metadata
                SmsMessage[] messages = new SmsMessage[pdus.length];

                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);  //To convert each PDU byte array into SmsMessage
                }
                for (SmsMessage message : messages) {
                    msg = message.getMessageBody();              // Returns the message body as a String.
                    phone_no = message.getOriginatingAddress();   //Returns the originating address (sender) of this SMS message in String form
                }
            }
        }
        Intent displayIntent = new Intent(context,MainActivity.class);
        displayIntent.putExtra("message",msg);
        displayIntent.putExtra("phoneNo",phone_no);
        context.startActivity(displayIntent);
       }
    }

