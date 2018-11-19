package com.company.actions;

import com.company.models.AlbinoRabbit;
import com.company.models.BaseRabbit;
import com.company.models.OrdinaryRabbit;
import com.company.models.RabbitsStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class LoadFileActionListener implements ActionListener {

    private Frame frame = null;

    public LoadFileActionListener(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File file = openFileDialog();
        try {
            deserialize(file.getPath());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private File openFileDialog() {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(frame);
        File selFile = fc.getSelectedFile();
        return selFile;
    }

    private void deserialize(String path) throws IOException, ClassNotFoundException {
        //This is to load rabbits from file
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        RabbitsStorage loadedStorage = (RabbitsStorage) ois.readObject();
        ois.close();

        OrdinaryRabbit.OrdinaryRabbits = loadedStorage.OrdinaryRabbits;
        AlbinoRabbit.AlbinoRabbits = loadedStorage.AlbinoRabbits;
        BaseRabbit.Rabbits = loadedStorage.BaseRabbits;
    }
}
