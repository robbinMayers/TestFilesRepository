package com.company;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Alexey on 21.11.2017.
 */
public class CSVList {
    private long unixTime;
    private String userID;
    private String url;
    private int averageTime;
    private ArrayList users = new ArrayList();

    public CSVList (String users) {
        String[] userList = users.split(",");
        setUnixTime(userList[0]);
        setUserID(userList[1]);
        setUrl(userList[2]);
        setAverageTime(userList[3]);
        String[] listFormat = new String[4];
        listFormat[0] = Long.toString(getUnixTime());
        listFormat[1] = getUserID();
        listFormat[2] = getUrl();
        listFormat[3] = Integer.toString(getAverageTime());
        setUsers(listFormat);

    }

    //////////////////////////////////////////
    ///////////////////////////////////////GET

    public ArrayList getUsers() {return  users;}

    public long getUnixTime() {
        return unixTime;
    }

    public String getUserID() {
        return userID;
    }

    public String getUrl() {
        return url;
    }

    public int getAverageTime() {
        return averageTime;
    }

    ///////////////////////////////////////////
    ////////////////////////////////////////SET

    public void setUsers(String[] user) {Collections.addAll(this.users, user);}

    public void setUnixTime(String unixTime) {
        this.unixTime = Long.parseLong(unixTime);
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAverageTime(String averageTime) {
        this.averageTime = Integer.parseInt(averageTime);
    }

}

