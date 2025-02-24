package com.kodnest.dsimplementations;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class DoublyLinkedListFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField sizeField;
    private JTextArea linkedListDisplayArea;
    private LinkedList<Integer> doublyLinkedList;
    private int listSize = 0;

    public DoublyLinkedListFrame() {
        setTitle("Doubly Linked List Module");
        setBounds(100, 100, 600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 600, 50);
        headerPanel.setBackground(new Color(63, 81, 181));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        JLabel lblTitle = new JLabel("Doubly Linked List Module");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle);
        getContentPane().add(headerPanel);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(20, 70, 550, 70);
        inputPanel.setLayout(new GridLayout(2, 3, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("List Operations"));

        JLabel lblSize = new JLabel("Enter List Size:");
        lblSize.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputPanel.add(lblSize);

        sizeField = new JTextField();
        inputPanel.add(sizeField);

        JButton btnCreateList = createStyledButton("Create List");
        inputPanel.add(btnCreateList);

        JLabel lblElements = new JLabel("Enter Element:");
        lblElements.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputPanel.add(lblElements);

        JTextField elementField = new JTextField();
        inputPanel.add(elementField);

        JButton btnAddElement = createStyledButton("Add Element");
        inputPanel.add(btnAddElement);

        getContentPane().add(inputPanel);

        // Action Buttons
        JPanel actionPanel = new JPanel();
        actionPanel.setBounds(20, 160, 550, 50);
        actionPanel.setLayout(new GridLayout(1, 3, 20, 0));

        JButton btnRemoveElement = createStyledButton("Remove Element");
        actionPanel.add(btnRemoveElement);

        JButton btnDisplayList = createStyledButton("Display List");
        actionPanel.add(btnDisplayList);

        JButton btnBack = createStyledButton("Back");
        actionPanel.add(btnBack);

        getContentPane().add(actionPanel);

        // Text Area for Display
        linkedListDisplayArea = new JTextArea();
        linkedListDisplayArea.setEditable(false);
        linkedListDisplayArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        linkedListDisplayArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JScrollPane scrollPane = new JScrollPane(linkedListDisplayArea);
        scrollPane.setBounds(20, 230, 550, 200);
        getContentPane().add(scrollPane);

        // Action Listeners
        doublyLinkedList = new LinkedList<>();

        btnCreateList.addActionListener(e -> {
            try {
                listSize = Integer.parseInt(sizeField.getText());
                if (listSize <= 0) {
                    JOptionPane.showMessageDialog(null, "Size must be greater than 0.");
                } else {
                    doublyLinkedList.clear();
                    JOptionPane.showMessageDialog(null, "Doubly Linked List of size " + listSize + " created.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        });

        btnAddElement.addActionListener(e -> {
            if (doublyLinkedList.size() < listSize) {
                try {
                    int element = Integer.parseInt(elementField.getText());
                    doublyLinkedList.add(element);
                    JOptionPane.showMessageDialog(null, "Element " + element + " added to the doubly linked list.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cannot add more elements. List size reached.");
            }
        });

        btnRemoveElement.addActionListener(e -> {
            if (doublyLinkedList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Doubly Linked List is empty. Cannot remove elements.");
            } else {
                int removedElement = doublyLinkedList.removeFirst();
                JOptionPane.showMessageDialog(null, "Element " + removedElement + " removed from the doubly linked list.");
            }
        });

        btnDisplayList.addActionListener(e -> {
            if (doublyLinkedList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Doubly Linked List is empty.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Integer element : doublyLinkedList) {
                    sb.append(element).append(" -> ");
                }
                sb.setLength(sb.length() - 4); // Remove the last " -> "
                linkedListDisplayArea.setText("Doubly Linked List: " + sb.toString());
            }
        });

        btnBack.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Redirecting to Home Page...");
            dispose();
            new HomePage().setVisible(true);
        });
    }

    // Method to create styled buttons with hover effects
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBackground(new Color(63, 81, 181));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(63, 81, 181));
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                DoublyLinkedListFrame frame = new DoublyLinkedListFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
