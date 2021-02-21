package com.alveo.logreader.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LogFileGenerator {
    public static void main(final String args[]){
        File logFile = new File("D:\\workpaces\\logs\\log-reader\\log-random.log");
        List<String> logLevelStrList = Arrays.asList("INFO", "WARNING", "ERROR");;
        try {
            logFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PrintWriter logFileWriter = new PrintWriter (logFile);
            for(int i = 0; i <500; i++) {
//                Thread.sleep(1000);
                logFileWriter.println(LocalDate.now() + " " + LocalTime.now() + " " + logLevelStrList.get(ThreadLocalRandom.current().nextInt(0, 3)));
            }
            logFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } /*catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

}
