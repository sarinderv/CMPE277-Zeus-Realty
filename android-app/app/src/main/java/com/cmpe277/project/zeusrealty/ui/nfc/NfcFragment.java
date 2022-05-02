package com.cmpe277.project.zeusrealty.ui.nfc;

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NfcViewModel nfcViewModel =
                new ViewModelProvider(this).get(NfcViewModel.class);

        binding = FragmentNfcBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNfc;
        nfcViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        nfcManager = new PillowNfcManager(getActivity());
        nfcManager.onActivityCreate();
        nfcManager.setOnTagReadListener(tagRead -> Toast.makeText(getContext(), "tag read:"+tagRead, Toast.LENGTH_LONG).show());
        writeHelper= new WriteTagHelper(getContext(), nfcManager);
        nfcManager.setOnTagWriteErrorListener(writeHelper);
        nfcManager.setOnTagWriteListener(writeHelper);

        Button writeButton = binding.writeButton;
        writeButton.setOnClickListener(v -> {
            String text = new Date().toString();
            text += "\n yo! Breath of The Wild!!!";
            writeHelper.writeText(text);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}