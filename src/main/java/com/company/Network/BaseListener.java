package com.company.Network;

import com.company.Network.Interfaces.IObjectListener;

public abstract class BaseListener implements IObjectListener{

    @Override
    public void onMessageReceived(Object sender, String msg) {
        //msg from initiator
    }
}
