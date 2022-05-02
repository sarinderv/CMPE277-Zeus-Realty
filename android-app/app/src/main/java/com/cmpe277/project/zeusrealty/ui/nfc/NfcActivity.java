package com.cmpe277.project.zeusrealty.ui.nfc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cmpe277.project.zeusrealty.R;

import java.util.Date;

import pillownfc.PillowNfcManager;
import pillownfc.util.WriteTagHelper;

public class NfcActivity extends AppCompatActivity {
    PillowNfcManager nfcManager;
    WriteTagHelper writeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_nfc);

        nfcManager = new PillowNfcManager(this);
        nfcManager.onActivityCreate();
        nfcManager.setOnTagReadListener(new PillowNfcManager.TagReadListener() {
            @Override
            public void onTagRead(String tagRead) {
                Toast.makeText(NfcActivity.this, "tag read:"+tagRead, Toast.LENGTH_LONG).show();
            }
        });

        writeHelper= new WriteTagHelper(this, nfcManager);
        nfcManager.setOnTagWriteErrorListener(writeHelper);
        nfcManager.setOnTagWriteListener(writeHelper);

        Button writeButton = (Button) findViewById(R.id.write_button);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = new Date().toString();
                text += "\n yo! Breath of The Wild!!!";
                writeHelper.writeText(text);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        nfcManager.onActivityResume();
    }

    @Override
    protected void onPause() {
        nfcManager.onActivityPause();
        super.onPause();
    }

    @Override
    public void onNewIntent(Intent intent){
        nfcManager.onActivityNewIntent(intent);
    }
}
