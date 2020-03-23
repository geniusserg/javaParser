package com.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class Main {
    public final static int threadsValue = 8; // const value of parse threads
    public static long lines = 0; // lines proceeded by all threads, needed to prgoress thread
    public static HashMap<String, Long> dictMain; // all threads merge local results here
    public static Scan file; // file we work

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        file = new Scan();
        dictMain = new HashMap<>();
        ArrayList<Thread> threads = new ArrayList<>();
        Thread progressBar = new Thread(new Progress(threads));

        for (int i = 0; i < threadsValue; i++) {
            threads.add(new Thread(new Parser()));
        }
        progressBar.start();
        for (int i = 0; i < threadsValue; i++) {
            threads.get(i).start();
        }
        try{progressBar.join();} catch (Exception ignored){}

        file.close();

        if (dictMain != null) {
            System.out.println("\nFinished successfully! Result:");
            for (String i : dictMain.keySet()){
                System.out.println(i + " : " + (dictMain.get(i)));
            }
        }

        System.out.print("Enter file name to write result: ");
        //String outputFileName = in.nextLine();
        String outputFileName = "output.txt";
        FileWriter output = new FileWriter(outputFileName);

        for (String i: dictMain.keySet()) {
            output.write(i+ " : "+ dictMain.get(i)+ "\n");
        }
        output.close();
    }
}
