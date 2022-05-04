package com.cmpe277.project.zeusrealty.ui.nfc;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cmpe277.project.zeusrealty.R;

import pillownfc.PillowNfcManager;

public class NfcActivity extends AppCompatActivity {
    private PillowNfcManager nfcManager;
    private TextView textNfc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_nfc);
        Button writeButton = findViewById(R.id.write_button);
        writeButton.setVisibility(View.GONE); // hide Write button for intent-driven NFC activity
        textNfc = findViewById(R.id.text_nfc);

        nfcManager = new PillowNfcManager(this);
        nfcManager.onActivityCreate();
        nfcManager.setOnTagReadListener(tagRead -> textNfc.setText(tagRead));

        setTextNfcMsgFromIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        nfcManager.onActivityNewIntent(intent);
        setTextNfcMsgFromIntent(intent);
    }

    private void setTextNfcMsgFromIntent(Intent intent) {
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMsgs == null) {
                textNfc.setText("empty NFC");
            }
            else {
                NdefRecord[] records = ((NdefMessage) rawMsgs[0]).getRecords();
                String text = nfcManager.ndefRecordToString(records[0]);
                textNfc.setText(text);
            }
        }
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
}
