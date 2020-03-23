package com.parser;

import java.util.HashMap;

/*
This class contains dictionary and methods for it. ever parser has its own dictionary, when
thread is to close, it call merge() to put results of thread into main HashMap "Main.dictMain"
 */


public class Dict {
    public final HashMap<String, Long> dictionary = new HashMap<>();
    public void add(String word){
        if (word.matches("^[A-Za-z]+$")) {
            try {
                if (dictionary.containsKey(word)) {
                    dictionary.put(word, dictionary.get(word) + 1);
                } else {
                    dictionary.put(word, 1L);
                }
            }
            catch (Exception e){
                System.out.print("Error when add record to local thread`s HashMap");
            }
        }
    }

    public void merge(){
        synchronized (Main.dictMain) {
            for (String i : this.dictionary.keySet()) {
                try {
                    if (Main.dictMain.containsKey(i)) {
                        Main.dictMain.put(i, dictionary.get(i) + Main.dictMain.get(i));
                    } else {
                        Main.dictMain.put(i, dictionary.get(i));
                    }
                }
                catch (Exception e){
                    System.out.print("Error when merge in common HashMap! ");
                }
            }
        }
    }
}
