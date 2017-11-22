package com.company;

import java.util.ArrayList;

/**
 * Created by Alexey on 21.11.2017.
 */
public class CSVListSort  {
    private long dateUnix;
    private int average;
    private ArrayList<ArrayList<String>> list;
    public CSVListSort(ArrayList<ArrayList<String>> list){

        ArrayList<ArrayList<String>> sortedList = new ArrayList<>();

        while (sortedList!=list){
            sortedList = list;
            sortList(list);
        }
        setList(list);
    }
    //////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////GET

    public ArrayList<ArrayList<String>> getList() {
        return list;
    }
    public long getDateUnix() {
        return dateUnix;
    }

    public int getAverage() {
        return average;
    }

    public long getAveragePart(long time, int average) {
        time = average - ((time+average)%86400);
        return time;
    }
    //////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////SET

    public void setList(ArrayList<ArrayList<String>> list) {
        this.list = list;
    }

    public void setDateUnix(long time) {
        this.dateUnix = time;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    //////////////////////////////////////////////////////////
    //////////////////////////////////////////////////OPERATE

    public boolean compareDay(long time, int average){
        long timeWithAverage =time+average;

        if (time % 86400 > timeWithAverage % 86400) return true;//If true -> new day
        return false;

    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public  void  sortList(ArrayList<ArrayList<String>> list){
        ArrayList<ArrayList<String>> sortedList = new ArrayList<>();
        ArrayList<ArrayList<String>> newDayList = new ArrayList<>();
        ArrayList<String> innerUserList = new ArrayList<>();
        StringBuilder value;

        for (ArrayList<String> aList : list) {
            setDateUnix(Long.parseLong(aList.get(0)));
            setAverage(Integer.parseInt(aList.get(3)));
            String user = aList.get(1);
            String url = aList.get(2);
            value = new StringBuilder(getDateUnix() + "," + user + "," + url + ","+getAverage());
            innerUserList.add(String.valueOf(value));


            if (!compareDay(getDateUnix(), getAverage())) {
                sortedList.add(innerUserList);
            } else {
                value = new StringBuilder(getDateUnix() + "," + user + "," + url + "," + getAveragePart(getDateUnix(), getAverage()));
                innerUserList.clear();
                innerUserList.add(String.valueOf(value));
                sortedList.add(innerUserList);

                value = new StringBuilder(getDateUnix() + "," + user + "," + url + "," + (getAverage() - getAveragePart(getDateUnix(), getAverage())));
                innerUserList.clear();
                innerUserList.add(String.valueOf(value));
                newDayList.add(innerUserList);
            }
        }
        sortedList.addAll(newDayList);
    }
}
