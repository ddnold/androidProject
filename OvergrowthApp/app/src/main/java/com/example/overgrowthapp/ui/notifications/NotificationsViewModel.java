package com.example.overgrowthapp.ui.notifications;

import android.widget.TableLayout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private static MutableLiveData<String> mText = null;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public static LiveData<String> getText() {
        return mText;
    }
}