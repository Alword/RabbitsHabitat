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
import java.util.Vector;

public class SaveFileActionListener implements ActionListener {

    private Frame frame = null;

    public SaveFileActionListener(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File saveFile = openFileDialog();
        try {
            serializeRabbits(saveFile.getPath());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private File openFileDialog() {
        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(frame);
        File selFile = fc.getSelectedFile();
        return selFile;
    }

    private void serializeRabbits(String path) throws IOException, ClassNotFoundException, InterruptedException {

        //This is to serialize and store out rabbits in file.
        RabbitsStorage rabbitsStorage = new RabbitsStorage();
        rabbitsStorage.OrdinaryRabbits = OrdinaryRabbit.OrdinaryRabbits;
        rabbitsStorage.AlbinoRabbits = AlbinoRabbit.AlbinoRabbits;
        rabbitsStorage.BaseRabbits = BaseRabbit.Rabbits;

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        oos.writeObject(rabbitsStorage);
        oos.flush();
        oos.close();


        //This is to load rabbits from file
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        RabbitsStorage loadedStorage = (RabbitsStorage) ois.readObject();
        ois.close();
    }
}
