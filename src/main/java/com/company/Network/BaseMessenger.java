package com.company.Network;

import com.company.Network.Interfaces.IObjectListener;

import java.util.List;

public abstract class BaseMessenger implements IObjectListener {

    private List<IObjectListener> listeners = null;

    public BaseMessenger() {
    }

    @Override
    public void onMessageReceived(Object sender, String msg) {
        for (IObjectListener listener : listeners) {
            listener.onMessageReceived(sender, msg);
        }
    }

    public void addListener(IObjectListener toAdd) {
        listeners.add(toAdd);
    }
}