package com.parser;

import java.util.ArrayList;

/*
This thread shows progress every second
*/

public class Progress implements Runnable{
    final ArrayList<Thread> threads;
    Progress(ArrayList<Thread> threads){
        this.threads = threads;
    }

    @Override
    public void run(){
        boolean stop = false;
        System.out.println("Start!");
        while (!stop){
            // HOW could I smartly implement threads viability check???
            stop = true;
            for (int i = 0; i < threads.size(); i++) {
                stop = stop & !threads.get(0).isAlive();
            }
            try {
                System.out.println(Main.lines +" lines ");
                Thread.sleep(1000);
            }
            catch (Exception e){
                System.out.println("process terminated abnormally");
            }
        }
    }
}
