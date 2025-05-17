package com.example.realestateapp.BookingManagement.service;


import com.example.realestateapp.BookingManagement.model.ContactRequest;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class ContactRequestService {
    private final String contactFile = "data/contact.txt";

    public void saveContactRequest(ContactRequest request) throws IOException {
        File file = new File(contactFile);
        file.getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(
                    request.getVisitorName() + "," +
                            request.getDate() + "," +
                            request.getTimeSlot() + "," +
                            request.getContactNumber() + "," +
                            request.getMessage().replace(",", ";") + "," +
                            request.getPropertyLocation() + "," +  // ✅ added
                            request.getOwner() + "," +
                            request.isRead()
            );
            writer.newLine();
        }
    }

    public List<ContactRequest> getRequestsForOwner(String owner) {
        List<ContactRequest> requests = new ArrayList<>();
        File file = new File(contactFile);
        if (!file.exists()) return requests;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 8); // ✅ expect 8 parts now
                if (parts.length == 8 && parts[6].equals(owner)) {
                    requests.add(new ContactRequest(
                            parts[0], parts[1], parts[2], parts[3], parts[4],
                            parts[5], parts[6], Boolean.parseBoolean(parts[7])
                    ));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public boolean hasUnreadRequests(String owner) {
        return getRequestsForOwner(owner).stream().anyMatch(r -> !r.isRead());
    }

    public void markSingleRequestAsRead(String name, String date, String timeSlot, String owner) throws IOException {
        File file = new File(contactFile);
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 8);
                if (parts.length == 8 && parts[0].equals(name) && parts[1].equals(date)
                        && parts[2].equals(timeSlot) && parts[6].equals(owner)) {
                    parts[7] = "true";
                }
                updatedLines.add(String.join(",", parts));
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String l : updatedLines) {
                writer.write(l);
                writer.newLine();
            }
        }
    }
}
