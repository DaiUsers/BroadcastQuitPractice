package com.yt.broadcastquitpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private ForceOfflineReceiver offlineReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcastbestpractice.FORCE_OFFLINE");
        offlineReceiver = new ForceOfflineReceiver();
        registerReceiver(offlineReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (offlineReceiver != null) {
            unregisterReceiver(offlineReceiver);
            offlineReceiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    class ForceOfflineReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setTitle("Warning");

            builder.setMessage("You are forced to be offline. Please try to login again");

            builder.setCancelable(false);

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(context, LoginActivity.class);

                    ActivityController.finishAll();

                    startActivity(intent);
                }
            });

            builder.show();
        }
    }
}
