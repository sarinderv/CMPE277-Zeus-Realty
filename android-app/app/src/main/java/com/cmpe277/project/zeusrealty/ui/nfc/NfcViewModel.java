package com.cmpe277.project.zeusrealty.ui.nfc;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NfcViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NfcViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Enter a string or URL below:");
    }

    public LiveData<String> getText() {
        return mText;
    }
}