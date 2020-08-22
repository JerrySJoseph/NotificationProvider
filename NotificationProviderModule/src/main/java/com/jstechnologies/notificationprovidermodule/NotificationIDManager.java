package com.jstechnologies.notificationprovidermodule;

public class NotificationIDManager {

    static int lastId=100;
    public static int getnotificationID()
    {
        return lastId++;
    }
}
