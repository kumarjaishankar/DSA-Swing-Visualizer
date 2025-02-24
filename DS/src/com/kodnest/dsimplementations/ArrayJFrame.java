package com.kodnest.dsimplementations;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ArrayJFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField sizeField;
    private JTextArea arrayDisplayArea;
    private ArrayList<Integer> arrayList;

    public ArrayJFrame() {
        setTitle("Array Module");
        setBounds(100, 100, 750, 650); // Increased window size to 750x650
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 750, 50); // Adjusted for new window size
        headerPanel.setBackground(new Color(63, 81, 181));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        JLabel lblTitle = new JLabel("Array Module");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle);
        getContentPane().add(headerPanel);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(20, 70, 700, 120); // Increased width for larger window
        inputPanel.setLayout(new GridLayout(2, 3, 20, 20)); // Adjusted spacing and layout
        inputPanel.setBorder(BorderFactory.createTitledBorder("Array Operations"));

        JLabel lblSize = new JLabel("Enter Array Size:");
        lblSize.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputPanel.add(lblSize);

        sizeField = new JTextField();
        inputPanel.add(sizeField);

        JButton btnCreateArray = createStyledButton("Create Array");
        inputPanel.add(btnCreateArray);

        JLabel lblElements = new JLabel("Enter Element:");
        lblElements.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputPanel.add(lblElements);

        JTextField elementField = new JTextField();
        inputPanel.add(elementField);

        JButton btnInsertElement = createStyledButton("Insert Element");
        inputPanel.add(btnInsertElement);

        getContentPane().add(inputPanel);

        // Action Buttons
        JPanel actionPanel = new JPanel();
        actionPanel.setBounds(20, 200, 700, 60); // Adjusted for new window size
        actionPanel.setLayout(new GridLayout(1, 3, 30, 0)); // More space between buttons

        JButton btnDeleteElement = createStyledButton("Delete Element");
        actionPanel.add(btnDeleteElement);

        JButton btnDisplayArray = createStyledButton("Display Array");
        actionPanel.add(btnDisplayArray);

        JButton btnBack = createStyledButton("Back");
        actionPanel.add(btnBack);

        getContentPane().add(actionPanel);

        // Text Area for Display
        arrayDisplayArea = new JTextArea();
        arrayDisplayArea.setEditable(false);
        arrayDisplayArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        arrayDisplayArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JScrollPane scrollPane = new JScrollPane(arrayDisplayArea);
        scrollPane.setBounds(20, 270, 700, 250); // Adjusted for new window size
        getContentPane().add(scrollPane);

        // Action Listeners
        arrayList = new ArrayList<>();

        btnCreateArray.addActionListener(e -> {
            try {
                int size = Integer.parseInt(sizeField.getText());
                if (size <= 0) {
                    JOptionPane.showMessageDialog(null, "Size must be greater than 0.");
                } else {
                    arrayList.clear();
                    for (int i = 0; i < size; i++) {
                        arrayList.add(0);
                    }
                    JOptionPane.showMessageDialog(null, "Array of size " + size + " created.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        });

        btnInsertElement.addActionListener(e -> {
            try {
                int element = Integer.parseInt(elementField.getText());
                int position = Integer.parseInt(JOptionPane.showInputDialog("Enter Position:"));
                if (position >= 0 && position < arrayList.size()) {
                    arrayList.set(position, element);
                    JOptionPane.showMessageDialog(null, "Element " + element + " inserted at position " + position + ".");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid position!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid inputs.");
            }
        });

        btnDeleteElement.addActionListener(e -> {
            try {
                int position = Integer.parseInt(JOptionPane.showInputDialog("Enter Position:"));
                if (position >= 0 && position < arrayList.size()) {
                    arrayList.set(position, 0);
                    JOptionPane.showMessageDialog(null, "Element at position " + position + " deleted.");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid position!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid position.");
            }
        });

        btnDisplayArray.addActionListener(e -> displayArray());

        btnBack.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Redirecting to Home Page...");
            new HomePage().setVisible(true);
            dispose();
        });
    }

    private void displayArray() {
        if (arrayList.isEmpty()) {
            arrayDisplayArea.setText("Array is empty.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arrayList.size(); i++) {
                sb.append("Index ").append(i).append(": ").append(arrayList.get(i)).append("\n");
            }
            arrayDisplayArea.setText(sb.toString());
        }
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setBackground(new Color(63, 81, 181));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                ArrayJFrame frame = new ArrayJFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}