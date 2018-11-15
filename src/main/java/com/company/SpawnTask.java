package com.company;

import java.util.Random;

public class SpawnTask implements Runnable {

    public boolean iSsimulated = true;
    int currentTick = 0;

    ///Настройки для кроликов
    ///Обычный кролик
    static int N1 = 1;
    static float P = 0.5F;

    ///Кролик альбинос
    static int N2 = 2;
    static float K = 0.1F;

    static Random generator = new Random();

    public boolean IsOrdinaryRabbitAppear() {
        float generated = generator.nextFloat();
        return true; //generated > P && currentTick % N1 == 0;
    }

    public boolean IsAlbinoRabbitAppear() {

        try {
            float ocount = OrdinaryRabbit.OrdinaryCount;
            int albcount = AlbinoRabbit.AlbinoCount;

            float generated = (float) albcount / (ocount - albcount);
            boolean isSpawn = generated < K && currentTick % N2 == 0;
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


    @Override
    public void run() {
        while (true) {
            sleep(100);
            System.out.println("SpawnTask");
            if (iSsimulated) {
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
            if (IsOrdinaryRabbitAppear()) {
                new OrdinaryRabbit();
            }
            if (IsAlbinoRabbitAppear()) {
                new AlbinoRabbit();
            }
        }
        BaseRabbit.Rabbits.removeIf(r -> !((IBehaviour) r).isAppear());
        Thread.sleep(1000);
    }

    private void sleep(int mili) {
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
