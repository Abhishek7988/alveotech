package com.alveo.logreader.service;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class LogReaderService {

    @Value("${file.path}")
    private String filePath;

    public Map<String, Integer> countLogLevelLogs(final String fileName, final Integer timeInSeconds) {
        final Map<String, Integer> logLevelCount = new HashMap<>();

        Integer infoCount = 0;
        Integer warnCount = 0;
        Integer errCount = 0;

        ReversedLinesFileReader fr = null;
        try {
            fr = new ReversedLinesFileReader(new File(filePath+fileName), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String ch = null;

        try {
            while (((ch = fr.readLine()) != null)) {
                LocalDate date = LocalDate.parse(ch.substring(0, 10));
                LocalTime time = LocalTime.parse(ch.substring(11, 23));
                String logLevelStr = ch.substring(24, ch.length());

                if(LocalDate.now().isEqual(date) && ChronoUnit.SECONDS.between(time, LocalTime.now()) <= timeInSeconds){
                    if(logLevelStr.startsWith("INFO")){
                        infoCount++;
                    } else if(logLevelStr.startsWith("WARNING")){
                        warnCount++;
                    } else if(logLevelStr.startsWith("ERROR")){
                        errCount++;
                    }
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("infoCount " + infoCount);
        System.out.println("warnCount " +warnCount);
        System.out.println("errCount " +errCount);
        logLevelCount.put("infoCount", infoCount);
        logLevelCount.put("warnCount", warnCount);
        logLevelCount.put("errCount", errCount);

        return logLevelCount;
    }

    /*public static void main(String[] args) {
        LogReaderService logReaderService = new LogReaderService();
        logReaderService.countLogLevelLogs("D:\\workpaces\\logs\\log-reader\\log-random.log", 40);
    }*/
}
