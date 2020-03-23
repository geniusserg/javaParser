package com.parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
The class provides synchronized access to the file
 */


public class Scan {
    FileReader file;
    Scanner sc;

    public Scan() {
        file = null;
        String filename = "test.txt";
        sc = new Scanner(System.in);
        //String filename;  // REMOVE SLASHES!! TO ENABLE INPUT
        while (file == null) {
            System.out.println("Enter filename");
            //filename = sc.nextLine();
            try {
                file = new FileReader(filename);
            } catch (Exception e) {
                System.out.printf("no file %s, try again", filename);
            }
        }
        sc = new Scanner(file);
    }

    public synchronized String get(){
        if (sc.hasNextLine()){
            return sc.nextLine();
        }
        return "";
    }

    public void close() throws IOException {
        file.close();
    }
}
