package com.parser;

/*
This is thread which parses string.
It uses synchronized method get() of file.
 */


public class Parser implements Runnable{
    String[] arrayWords;
    String line;
    Dict dict = new Dict();

    @Override
    public void run() {
        try {
            while (true) {
                line = Main.file.get();
                if (line.equals("")) {
                    break;
                }
                Main.lines += 1;
                arrayWords = line.split(" ");
                for (String arrayWord : arrayWords) {
                    dict.add(arrayWord);
                }
            }
        }
        catch (Exception e){
            System.out.println(" error in thread");
        }
        dict.merge();
        dict = null;
    }
}
