package com.example.realestateapp.BookingManagement.model;

public class ContactRequest {
    private String visitorName;
    private String date;
    private String timeSlot;
    private String contactNumber;
    private String message;
    private String propertyLocation;
    private String owner;
    private boolean isRead;

    public ContactRequest(String visitorName, String date, String timeSlot, String contactNumber, String message, String propertyLocation, String owner, boolean isRead) {
        this.visitorName = visitorName;
        this.date = date;
        this.timeSlot = timeSlot;
        this.contactNumber = contactNumber;
        this.message = message;
        this.propertyLocation = propertyLocation;
        this.owner = owner;
        this.isRead = isRead;
    }


    public String getVisitorName() { return visitorName; }
    public String getPropertyLocation() { return propertyLocation; }
    public String getDate() { return date; }
    public String getTimeSlot() { return timeSlot; }
    public String getContactNumber() { return contactNumber; }
    public String getMessage() { return message; }
    public String getOwner() { return owner; }
    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { isRead = read; }

}
