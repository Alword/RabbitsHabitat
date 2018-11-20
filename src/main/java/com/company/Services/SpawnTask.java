package com.company.Services;

import com.company.AppSettings;
import com.company.interfaces.IBehaviour;
import com.company.models.AlbinoRabbit;
import com.company.models.BaseRabbit;
import com.company.models.OrdinaryRabbit;

import java.io.IOException;
import java.util.Random;

public class SpawnTask implements Runnable {

    public boolean iSimulated = true;
    int currentTick = 0;

    ///Настройки для кроликов
    ///Обычный кролик
    static int N1 = 1;
    static float P = 0.2F;

    ///Кролик альбинос
    static int N2 = 2;
    static float K = 0.1F;

    static Random generator = new Random();

    public boolean IsOrdinaryRabbitAppear() {
        float generated = generator.nextFloat();
        return generated > P && currentTick % N1 == 0;
    }

    public boolean IsAlbinoRabbitAppear() {

        try {
            if (OrdinaryRabbit.OrdinaryRabbits != null) {
                float ocount = OrdinaryRabbit.OrdinaryRabbits.size();
                int albcount = AlbinoRabbit.AlbinoCount;

                float generated = (float) albcount / (ocount - albcount);
                boolean isSpawn = generated < K && currentTick % N2 == 0;
                return isSpawn;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }


    @Override
    public void run() {
        while (true) {
            sleep(100);
            if (iSimulated) {
                try {
                    SimulateRabbits();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void SimulateRabbits() throws InterruptedException {
        currentTick = currentTick + 1 % 100;
        if (BaseRabbit.Rabbits == null || BaseRabbit.Rabbits.size() < AppSettings.MaxRabbits) {
            try {
                if (IsOrdinaryRabbitAppear()) {
                    new OrdinaryRabbit();
                }
                if (IsAlbinoRabbitAppear()) {
                    new AlbinoRabbit();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BaseRabbit.Rabbits.removeIf(r -> !((IBehaviour) r).isAppear());
        Thread.sleep(500);
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
