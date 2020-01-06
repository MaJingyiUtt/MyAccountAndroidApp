package fr.utt.if26.myaccount;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class SettingsActivity extends AppCompatActivity {
    private TextView exportTv;
    private TextView deleteTv;
    private LineViewModel mLineViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        exportTv = findViewById(R.id.settings_export_tv);
        deleteTv = findViewById(R.id.settings_delete_tv);
        mLineViewModel = ViewModelProviders.of(this).get(LineViewModel.class);

        exportTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, AllDataActivity.class);
                startActivity(intent);
            }
        });

        deleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SettingsActivity.this)
                        .setTitle("WARNING ! ")
                        .setMessage("Are you sure that you want to delete all data ?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mLineViewModel.deleteAll();
                                Toast.makeText(SettingsActivity.this, "All your data deleted", Toast.LENGTH_LONG).show();
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
    }
}
