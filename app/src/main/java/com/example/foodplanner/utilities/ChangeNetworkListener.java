package com.example.foodplanner.utilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;

import com.example.foodplanner.R;

public class ChangeNetworkListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!NetworkChecker.isConnectedToInternet(context)){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View layout_dialog = LayoutInflater.from(context).inflate(R.layout.check_internet_dialog,null);
            builder.setView(layout_dialog);

          AppCompatButton btnRetry = layout_dialog.findViewById(R.id.btnRetry);

            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setCancelable(false);

            dialog.getWindow().setGravity(Gravity.CENTER);

            btnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  dialog.dismiss();
                    dialog.cancel();
                    //onReceive(context, intent);
                }
            });
        }
    }
}
