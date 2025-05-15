//package com.example.realestateapp.PropertyManagement.service;
//
//import com.example.realestateapp.PropertyManagement.model.Property;
//import org.springframework.stereotype.Service;
//
//import java.io.*;
//import java.util.*;
//
//@Service
//public class PropertyService {
//    private final String filePath = "data/properties.txt";
//
//    public void saveProperty(Property property) throws IOException {
//        File file = new File(filePath);
//        file.getParentFile().mkdirs();
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
//            writer.write(property.getOwner() + "," + property.getLocation() + "," + property.getCountry() + "," +
//                    property.getPrice() + "," + property.getType() + "," + property.getSize() + "," + property.getImageUrl());
//            writer.newLine();
//        }
//    }
//
//    public List<Property> getAllProperties() {
//        List<Property> properties = new ArrayList<>();
//        File file = new File(filePath);
//        if (!file.exists()) return properties;
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(",", 7);
//                if (parts.length == 7) {
//                    Property p = new Property(parts[0], parts[1], parts[2],
//                            Double.parseDouble(parts[3]), parts[4], parts[5], parts[6]);
//                    properties.add(p);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return properties;
//    }
//
//    public void deleteProperty(String owner, String location) throws IOException {
//        File file = new File(filePath);
//        List<String> updatedLines = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(",", 7);
//                if (!(parts.length == 7 && parts[0].equals(owner) && parts[1].equals(location))) {
//                    updatedLines.add(line);
//                }
//            }
//        }
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//            for (String line : updatedLines) {
//                writer.write(line);
//                writer.newLine();
//            }
//        }
//    }
//
//    public void updateProperty(String owner, String originalLocation, Property updatedProperty) throws IOException {
//        File file = new File(filePath);
//        List<String> updatedLines = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(",", 7);
//                if (parts.length == 7 && parts[0].equals(owner) && parts[1].equals(originalLocation)) {
//                    updatedLines.add(updatedProperty.getOwner() + "," + updatedProperty.getLocation() + "," +
//                            updatedProperty.getCountry() + "," + updatedProperty.getPrice() + "," +
//                            updatedProperty.getType() + "," + updatedProperty.getSize() + "," + updatedProperty.getImageUrl());
//                } else {
//                    updatedLines.add(line);
//                }
//            }
//        }
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//            for (String line : updatedLines) {
//                writer.write(line);
//                writer.newLine();
//            }
//        }
//    }
//
//    public void transferOwnership(String oldUsername, String newUsername) throws IOException {
//        File file = new File(filePath);
//        List<String> updatedLines = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(",", 7);
//                if (parts.length == 7 && parts[0].equals(oldUsername)) {
//                    parts[0] = newUsername;
//                    updatedLines.add(String.join(",", parts));
//                } else {
//                    updatedLines.add(line);
//                }
//            }
//        }
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//            for (String line : updatedLines) {
//                writer.write(line);
//                writer.newLine();
//            }
//        }
//
//        System.out.println("Transferred property ownership from " + oldUsername + " to " + newUsername);
//    }
//
//    // ✅ New method to return total property count
//    public int getPropertyCount() throws IOException {
//        int count = 0;
//        File file = new File(filePath);
//        if (!file.exists()) return 0;
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                if (!line.trim().isEmpty()) count++;
//            }
//        }
//        return count;
//    }
//}
//
//
//
//








package com.example.realestateapp.PropertyManagement.service;

import com.example.realestateapp.PropertyManagement.model.Property;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class PropertyService {
    private final String filePath = "data/properties.txt";

    public void saveProperty(Property property) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(property.getOwner() + "," + property.getLocation() + "," + property.getCountry() + "," +
                    property.getPrice() + "," + property.getType() + "," + property.getSize() + "," + property.getImageUrl());
            writer.newLine();
        }
    }

    public List<Property> getAllProperties() {
        List<Property> properties = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return properties;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 7);
                if (parts.length == 7) {
                    Property p = new Property(parts[0], parts[1], parts[2],
                            Double.parseDouble(parts[3]), parts[4], parts[5], parts[6]);
                    properties.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    // ✅ New: Sort properties by price using Quick Sort
    public List<Property> getSortedPropertiesByPrice() {
        List<Property> properties = getAllProperties();
        quickSort(properties, 0, properties.size() - 1);
        return properties;
    }

    private void quickSort(List<Property> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private int partition(List<Property> list, int low, int high) {
        double pivot = list.get(high).getPrice();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getPrice() < pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    public void deleteProperty(String owner, String location) throws IOException {
        File file = new File(filePath);
        List<String> updatedLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 7);
                if (!(parts.length == 7 && parts[0].equals(owner) && parts[1].equals(location))) {
                    updatedLines.add(line);
                }
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : updatedLines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public void updateProperty(String owner, String originalLocation, Property updatedProperty) throws IOException {
        File file = new File(filePath);
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 7);
                if (parts.length == 7 && parts[0].equals(owner) && parts[1].equals(originalLocation)) {
                    updatedLines.add(updatedProperty.getOwner() + "," + updatedProperty.getLocation() + "," +
                            updatedProperty.getCountry() + "," + updatedProperty.getPrice() + "," +
                            updatedProperty.getType() + "," + updatedProperty.getSize() + "," + updatedProperty.getImageUrl());
                } else {
                    updatedLines.add(line);
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : updatedLines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public void transferOwnership(String oldUsername, String newUsername) throws IOException {
        File file = new File(filePath);
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 7);
                if (parts.length == 7 && parts[0].equals(oldUsername)) {
                    parts[0] = newUsername;
                    updatedLines.add(String.join(",", parts));
                } else {
                    updatedLines.add(line);
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : updatedLines) {
                writer.write(line);
                writer.newLine();
            }
        }

        System.out.println("Transferred property ownership from " + oldUsername + " to " + newUsername);
    }

    public int getPropertyCount() throws IOException {
        int count = 0;
        File file = new File(filePath);
        if (!file.exists()) return 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) count++;
            }
        }
        return count;
    }
}
