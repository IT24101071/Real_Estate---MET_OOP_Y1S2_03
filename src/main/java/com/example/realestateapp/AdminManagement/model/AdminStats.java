package com.example.realestateapp.AdminManagement.model;


public class AdminStats {
    private int userCount;
    private int propertyCount;

    public AdminStats(int userCount, int propertyCount) {
        this.userCount = userCount;
        this.propertyCount = propertyCount;
    }

    public int getUserCount() {
        return userCount;
    }

    public int getPropertyCount() {
        return propertyCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public void setPropertyCount(int propertyCount) {
        this.propertyCount = propertyCount;
    }
}
