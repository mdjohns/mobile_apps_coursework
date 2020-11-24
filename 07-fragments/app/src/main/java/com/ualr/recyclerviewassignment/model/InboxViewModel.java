package com.ualr.recyclerviewassignment.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ualr.recyclerviewassignment.Utils.DataGenerator;

import java.util.List;

public class InboxViewModel extends ViewModel {
    private static final int NO_SELECTION = -1;
    private MutableLiveData<List<Inbox>> inboxList = new MutableLiveData<>();
    private MutableLiveData<Integer> selectedIndex = new MutableLiveData<>();

    public LiveData<List<Inbox>> getInboxList() {
        return inboxList;
    }

    public LiveData<Integer> getSelectedIndex() {
        return selectedIndex;
    }

    public InboxViewModel(List<Inbox> inboxItems) {
        this.inboxList.setValue(inboxItems);
        this.selectedIndex.setValue(NO_SELECTION);
    }

    public void addEmail(Inbox email) {
        List<Inbox> currentEmails = inboxList.getValue();

        if (currentEmails != null) {
            currentEmails.add(email);
            this.inboxList.setValue(currentEmails);
        }

    }
    public void deleteEmail(int position) {
        List<Inbox> currentEmails = inboxList.getValue();

        if (currentEmails != null) {
            currentEmails.remove(position);
            this.inboxList.setValue(currentEmails);
        }

    }

    public void toggleSelection(int position) {
        List<Inbox> currentEmails = inboxList.getValue();

        if (currentEmails != null) {
            currentEmails.get(position).toggleSelection();
            this.inboxList.setValue(currentEmails);
            this.selectedIndex.setValue(position);
        }
    }

    public void clearSelection() {
        List<Inbox> currentEmails = inboxList.getValue();
        if (currentEmails != null) {

            for (Inbox email: currentEmails) {
                email.setSelected(false);
            }
            this.inboxList.setValue(currentEmails);
            this.selectedIndex.setValue(NO_SELECTION);
        }

    }
}
