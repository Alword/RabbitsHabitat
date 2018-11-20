package com.company.Services;

import com.company.models.BaseRabbit;

public class MoveTask implements Runnable {

    public boolean isCancel = true;

    @Override
    public void run() {
        while (true) {
            sleep(1000/45);
            //System.out.println("MoveTask");
            if (!isCancel) {
                int count = BaseRabbit.Rabbits.size();
                for (int i = 0; i < count; i++) {
                    try {
                        BaseRabbit.Rabbits.get(i).move();
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    private void sleep(int mili) {
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
