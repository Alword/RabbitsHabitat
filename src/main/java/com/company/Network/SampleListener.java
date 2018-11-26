package com.company.Network;

import com.company.Network.Interfaces.IObjectListener;

import java.net.Socket;

public class SampleListener implements IObjectListener{
    @Override
    public void onMessageReceived(Object sender, String msg) {
        //msg from initiator
    }
}
