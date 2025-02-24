package com.kodnest.dsimplementations;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class CircularQueueFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField sizeField;
    private JTextArea queueDisplayArea;
    private LinkedList<Integer> circularQueue;
    private int size;
    private int front;
    private int rear;

    public CircularQueueFrame() {
        setTitle("Circular Queue Module");
        setBounds(100, 100, 600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 600, 50);
        headerPanel.setBackground(new Color(63, 81, 181));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        JLabel lblTitle = new JLabel("Circular Queue Module");
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

        JButton btnDisplayQueue = createStyledButton("Display Queue");
        actionPanel.add(btnDisplayQueue);

        JButton btnBack = createStyledButton("Back");
        actionPanel.add(btnBack);

        getContentPane().add(actionPanel);

        // Text Area for Display
        queueDisplayArea = new JTextArea();
        queueDisplayArea.setEditable(false);
        queueDisplayArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        queueDisplayArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JScrollPane scrollPane = new JScrollPane(queueDisplayArea);
        scrollPane.setBounds(20, 230, 550, 200);
        getContentPane().add(scrollPane);

        // Action Listeners
        circularQueue = new LinkedList<>();
        front = -1;
        rear = -1;

        btnCreateQueue.addActionListener(e -> {
            try {
                size = Integer.parseInt(sizeField.getText());
                if (size <= 0) {
                    JOptionPane.showMessageDialog(null, "Size must be greater than 0.");
                } else {
                    circularQueue.clear();
                    front = -1;
                    rear = -1;
                    JOptionPane.showMessageDialog(null, "Circular Queue of size " + size + " created.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        });

        btnAddElement.addActionListener(e -> {
            if ((rear + 1) % size == front) {
                JOptionPane.showMessageDialog(null, "Circular Queue is full. Cannot add more elements.");
            } else {
                try {
                    int element = Integer.parseInt(elementField.getText());
                    if (front == -1) {
                        front = 0;
                    }
                    rear = (rear + 1) % size;
                    circularQueue.add(rear, element);
                    JOptionPane.showMessageDialog(null, "Element " + element + " added to the circular queue.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
                }
            }
        });

        btnRemoveElement.addActionListener(e -> {
            if (front == -1) {
                JOptionPane.showMessageDialog(null, "Circular Queue is empty. Cannot remove elements.");
            } else {
                int removedElement = circularQueue.remove(front);
                if (front == rear) {
                    front = -1;
                    rear = -1;
                } else {
                    front = (front + 1) % size;
                }
                JOptionPane.showMessageDialog(null, "Element " + removedElement + " removed from the queue.");
            }
        });

        btnDisplayQueue.addActionListener(e -> displayQueue());

        btnBack.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Redirecting to Home Page...", "Redirect", JOptionPane.INFORMATION_MESSAGE);
            new HomePage().setVisible(true); // Redirect to HomePage
            dispose(); // Close the CircularQueueFrame
        });
    }

    private void displayQueue() {
        if (front == -1) {
            queueDisplayArea.setText("Queue is empty.");
        } else {
            StringBuilder sb = new StringBuilder();
            int i = front;
            while (i != rear) {
                sb.append(circularQueue.get(i)).append(" ");
                i = (i + 1) % size;
            }
            sb.append(circularQueue.get(rear)).append(" ");
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
                CircularQueueFrame frame = new CircularQueueFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}