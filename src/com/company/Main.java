package com.company;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class Main extends Exception {


            @SuppressWarnings("ThrowablePrintedToSystemOut")
            public static void main(String[] args) {


                File file = new File("res\\TestFolder\\data.properties");
                try {
                Properties properties = new Properties();
                properties.load(new FileReader(file));
                File folder = new File(properties.getProperty("folderInput"));


                    FileFinder finder = new FileFinder(folder);
                    CSVListSort listSort = new CSVListSort(finder.getList());
                    new CSVFileWriter(listSort.getList());
                } catch (Exception e) {
                    System.out.print(e);
                    System.out.print(StackTraceElement.class);
                }
            }
}



