package com.ualr.recyclerviewassignment.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InboxViewModel extends ViewModel {

    MutableLiveData<Inbox> inboxData;

    public InboxViewModel() {
        inboxData = new MutableLiveData<>();
    }

    public void setInboxData(String from, String email, String message, String date, boolean selected) {
        Inbox currentInboxData = inboxData.getValue();

        if (currentInboxData == null) currentInboxData = new Inbox();

        currentInboxData.setData(from, email, message, date, selected);
        inboxData.setValue(currentInboxData);
    }

    public LiveData<Inbox> getInbox() {
        return this.inboxData;
    }
}
