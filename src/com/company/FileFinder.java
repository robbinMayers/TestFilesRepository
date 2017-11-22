package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Alexey on 21.11.2017.
 */
public class FileFinder {
    private ArrayList<ArrayList<String>> list;

    public FileFinder(File folder) throws Exception {
        ArrayList<ArrayList<String>> usersList = new ArrayList<>();
        findFileInFolder(folder, usersList);

    }

    ///////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////GET

    public ArrayList<ArrayList<String>> getList() {
        return list;
    }

    ///////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////SET

    public void setList(ArrayList<ArrayList<String>> list) {
        this.list = list;
    }

    ///////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////OPERATE

    public void findFileInFolder(File folder, ArrayList<ArrayList<String>> usersList) {
        File[] folderEntries = folder.listFiles();

        if (folderEntries != null) {
            for (File entry : folderEntries) {
                if (entry.isDirectory()) {
                    findFileInFolder(entry, usersList);
                    continue;
                } //We catch file here.
                try {


                    FileInputStream inputStream = new FileInputStream(entry);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String userData;
                    CSVList userList;

                    // Создаем список строк из файла
                    while (reader.ready()) {
                        userData = reader.readLine();
                        userList = new CSVList(userData);
                        usersList.add(userList.getUsers());

                    }
                    inputStream.close();
                    reader.close();

                } catch (Exception e) {

                    System.out.println("Some Problems(");
                    System.out.println(e);
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
                setList(usersList);
            }
            System.out.println("Folder is Empty!");
        }
    }
}
