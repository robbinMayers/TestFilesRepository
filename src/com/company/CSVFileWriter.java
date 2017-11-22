package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by Alexey on 21.11.2017.
 */
public class CSVFileWriter {
    public CSVFileWriter(ArrayList<ArrayList<String>> list) throws IOException {

        File file = new File("res\\TestFolder\\data.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        FileWriter writer = new FileWriter(properties.getProperty("fileOutput"));
        csvFileWriter(list , writer);
        writer.flush();
        writer.close();
    }

    ///////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////OPERATE

    public void csvFileWriter(ArrayList<ArrayList<String>> list, FileWriter writer) {
        try {

            ArrayList<ArrayList<String>> listNewDay = new ArrayList<>();

            for (int i = 0; i < list.size(); i++) {

                long dateUnix = Long.parseLong(list.get(i).get(0));
                int average = Integer.parseInt(list.get(i).get(3));
                String user = list.get(i).get(1);
                String url = list.get(i).get(2);

                Date date = new Date(dateUnix * 1000 + average);
                SimpleDateFormat df2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                String dateText = (df2.format(date));
                StringBuilder value = new StringBuilder(user + "," + url + "," + average);

                if (!compareDay(dateUnix, average)) {
                    if (i < 1) {
                        writer.write(dateText);
                        writer.write("\n");
                        writer.write("\n");
                    }
                    writer.write(String.valueOf(value));
                    writer.write("\n");
                } else {
                    value = new StringBuilder(user + "," + url + "," + getAveragePart(dateUnix, average));
                    writer.write(String.valueOf(value));
                    long newDate = Long.parseLong(list.get(i).get(0))+getAveragePart(dateUnix, average);
                    writer.write("\n");
                    value = new StringBuilder(Long.toString(newDate)+","+user + "," + url + "," + Long.toString(average - getAveragePart(dateUnix, average)));
                    CSVList newList = new CSVList(String.valueOf(value));
                    listNewDay.add(newList.getUsers());
                }
            }
            list = listNewDay;

            if(list.size()>0){
                writer.write("\n");
                csvFileWriter(list, writer);
            }
        } catch (Exception e){
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            String message = "";
            if (stackTraceElements.length >= 3) {
                StackTraceElement element = stackTraceElements[2];
                String className = element.getClassName();
                String methodName = element.getMethodName();
                int line = element.getLineNumber();
                String fileName = element.getFileName();
                message = className + ": " + methodName + " line : " + line + " File name : " + fileName;
            }
            System.out.println(message);
        }
    }

    public boolean compareDay(long time, int average){
        long timeWithAverage =time+average;

        if(time%86400>timeWithAverage%86400)return true;//if "time" is greater -> a new day comes;
        return false;
    }
    public long getAveragePart(long time, int average) {
        time= average - ((time+average)%86400);//get average for old day
        return time;
    }

}

