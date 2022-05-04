package com.cmpe277.project.zeusrealty.ui.nfc;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cmpe277.project.zeusrealty.databinding.FragmentNfcBinding;

import java.util.Date;

import pillownfc.PillowNfcManager;
import pillownfc.util.WriteTagHelper;

public class NfcFragment extends Fragment {
    PillowNfcManager nfcManager;
    WriteTagHelper writeHelper;

    private FragmentNfcBinding binding;
    private NfcAdapter mAdapter;
    private PendingIntent pendingIntent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NfcViewModel nfcViewModel =
                new ViewModelProvider(this).get(NfcViewModel.class);

        binding = FragmentNfcBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mAdapter = NfcAdapter.getDefaultAdapter(getContext());
        pendingIntent = PendingIntent.getActivity(
                getContext(), 0, new Intent(getContext(), getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        final TextView textView = binding.textNfc;
        nfcViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        nfcManager = new PillowNfcManager(getActivity());
        nfcManager.onActivityCreate();
        nfcManager.setOnTagReadListener(tagRead -> {
            Toast.makeText(getContext(), "tag read:" + tagRead, Toast.LENGTH_LONG).show();
        });
        writeHelper= new WriteTagHelper(getContext(), nfcManager);
        nfcManager.setOnTagWriteErrorListener(writeHelper);
        nfcManager.setOnTagWriteListener(writeHelper);

        Button writeButton = binding.writeButton;
        writeButton.setOnClickListener(v -> {
            String text = new Date().toString();
            text += "\n NFC tag for Zeus Realty";
            writeHelper.writeText(text);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        mAdapter.enableForegroundDispatch(getActivity(), pendingIntent, null, null);
        super.onResume();
    }

    @Override
    public void onPause() {
        mAdapter.disableForegroundDispatch(getActivity());
        super.onPause();
    }
}