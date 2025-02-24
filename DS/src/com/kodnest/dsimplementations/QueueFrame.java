package com.kodnest.dsimplementations;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class QueueFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField sizeField, elementField;
    private JTextArea queueDisplayArea;
    private LinkedList<Integer> queue;
    private int size;

    public QueueFrame() {
        setTitle("Queue Module");
        setBounds(100, 100, 600, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 600, 50);
        headerPanel.setBackground(new Color(63, 81, 181));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        JLabel lblTitle = new JLabel("Queue Module");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle);
        getContentPane().add(headerPanel);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(20, 70, 550, 70);
        inputPanel.setLayout(new GridLayout(2, 3, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Queue Operations"));

        JLabel lblSize = new JLabel("Enter Queue Size:");
        lblSize.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputPanel.add(lblSize);

        sizeField = new JTextField();
        inputPanel.add(sizeField);

        JButton btnCreateQueue = createStyledButton("Create Queue");
        inputPanel.add(btnCreateQueue);

        JLabel lblElement = new JLabel("Enter Element:");
        lblElement.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputPanel.add(lblElement);

        elementField = new JTextField();
        inputPanel.add(elementField);

        JButton btnAddElement = createStyledButton("Add Element");
        inputPanel.add(btnAddElement);

        getContentPane().add(inputPanel);

        // Action Buttons
        JPanel actionPanel = new JPanel();
        actionPanel.setBounds(20, 160, 550, 50);
        actionPanel.setLayout(new GridLayout(1, 2, 20, 0));

        JButton btnRemoveElement = createStyledButton("Remove Element");
        actionPanel.add(btnRemoveElement);

        JButton btnDisplayQueue = createStyledButton("Display Queue");
        actionPanel.add(btnDisplayQueue);

        getContentPane().add(actionPanel);

        // Text Area for Display
        queueDisplayArea = new JTextArea();
        queueDisplayArea.setEditable(false);
        queueDisplayArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        queueDisplayArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JScrollPane scrollPane = new JScrollPane(queueDisplayArea);
        scrollPane.setBounds(20, 230, 550, 200);
        getContentPane().add(scrollPane);

        // Back Button
        JButton btnBack = createStyledButton("Back");
        btnBack.setBounds(250, 450, 100, 40);
        btnBack.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Redirecting to Home Page...");
            new HomePage().setVisible(true);
            dispose();
        });
        getContentPane().add(btnBack);

        // Action Listeners
        queue = new LinkedList<>();

        btnCreateQueue.addActionListener(e -> {
            try {
                size = Integer.parseInt(sizeField.getText());
                if (size <= 0) {
                    JOptionPane.showMessageDialog(null, "Size must be greater than 0.");
                } else {
                    queue.clear();
                    JOptionPane.showMessageDialog(null, "Queue of size " + size + " created.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        });

        btnAddElement.addActionListener(e -> {
            if (queue.size() < size) {
                try {
                    int element = Integer.parseInt(elementField.getText());
                    queue.addLast(element);
                    JOptionPane.showMessageDialog(null, "Element " + element + " added to the queue.");
                    elementField.setText(""); // Clear the input field after adding
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Queue is full. Cannot add more elements.");
            }
        });

        btnRemoveElement.addActionListener(e -> {
            if (!queue.isEmpty()) {
                int removedElement = queue.poll();
                JOptionPane.showMessageDialog(null, "Deleted element: " + removedElement);
                displayQueue();
            } else {
                JOptionPane.showMessageDialog(null, "Queue is empty. Cannot remove elements.");
            }
        });

        btnDisplayQueue.addActionListener(e -> displayQueue());
    }

    private void displayQueue() {
        if (queue.isEmpty()) {
            queueDisplayArea.setText("Queue is empty.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Elements in the queue:\n");
            for (Integer element : queue) {
                sb.append(element).append("\n");
            }
            queueDisplayArea.setText(sb.toString());
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
                QueueFrame frame = new QueueFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}