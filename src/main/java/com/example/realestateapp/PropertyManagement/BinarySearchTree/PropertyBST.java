package com.example.realestateapp.PropertyManagement.BinarySearchTree;


import com.example.realestateapp.PropertyManagement.model.Property;

import java.util.ArrayList;
import java.util.List;

public class PropertyBST {
    private BSTNode root;

    public void insert(Property property) {
        root = insertRecursive(root, property);
    }

    private BSTNode insertRecursive(BSTNode node, Property property) {
        if (node == null) return new BSTNode(property);
        if (property.getLocation().compareToIgnoreCase(node.property.getLocation()) < 0)
            node.left = insertRecursive(node.left, property);
        else
            node.right = insertRecursive(node.right, property);
        return node;
    }

    public List<Property> inOrderTraversal() {
        List<Property> result = new ArrayList<>();
        inOrderRecursive(root, result);
        return result;
    }

    public List<Property> searchByLocation(String targetLocation) {
        List<Property> result = new ArrayList<>();
        searchRecursive(root, targetLocation.toLowerCase(), result);
        return result;
    }

    private void searchRecursive(BSTNode node, String target, List<Property> result) {
        if (node == null) return;
        String nodeLocation = node.property.getLocation().toLowerCase();
        if (nodeLocation.equals(target)) result.add(node.property);

        // Traverse both sides since duplicates or similar names may exist
        searchRecursive(node.left, target, result);
        searchRecursive(node.right, target, result);
    }




    private void inOrderRecursive(BSTNode node, List<Property> result) {
        if (node != null) {
            inOrderRecursive(node.left, result);
            result.add(node.property);
            inOrderRecursive(node.right, result);
        }
    }
}

