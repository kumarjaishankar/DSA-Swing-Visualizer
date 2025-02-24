package com.kodnest.dsimplementations;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class StackFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField sizeField;
    private JTextArea stackDisplayArea;
    private Stack<Integer> stack;

    public StackFrame() {
        setTitle("Stack Module");
        setBounds(100, 100, 750, 650); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 750, 50); 
        headerPanel.setBackground(new Color(63, 81, 181));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        JLabel lblTitle = new JLabel("Stack Module");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle);
        getContentPane().add(headerPanel);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(20, 70, 700, 120); 
        inputPanel.setLayout(new GridLayout(2, 3, 20, 20));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Stack Operations"));

        JLabel lblSize = new JLabel("Enter Stack Size:");
        lblSize.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputPanel.add(lblSize);

        sizeField = new JTextField();
        inputPanel.add(sizeField);

        JButton btnCreateStack = createStyledButton("Create Stack");
        inputPanel.add(btnCreateStack);

        JLabel lblElements = new JLabel("Enter Element:");
        lblElements.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputPanel.add(lblElements);

        JTextField elementField = new JTextField();
        inputPanel.add(elementField);

        JButton btnPushElement = createStyledButton("Push Element");
        inputPanel.add(btnPushElement);

        getContentPane().add(inputPanel);

        // Action Buttons
        JPanel actionPanel = new JPanel();
        actionPanel.setBounds(20, 200, 700, 60); 
        actionPanel.setLayout(new GridLayout(1, 3, 30, 0));

        JButton btnPopElement = createStyledButton("Pop Element");
        actionPanel.add(btnPopElement);

        JButton btnDisplayStack = createStyledButton("Display Stack");
        actionPanel.add(btnDisplayStack);

        JButton btnBack = createStyledButton("Back");
        actionPanel.add(btnBack);

        getContentPane().add(actionPanel);

        // Text Area for Display
        stackDisplayArea = new JTextArea();
        stackDisplayArea.setEditable(false);
        stackDisplayArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        stackDisplayArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JScrollPane scrollPane = new JScrollPane(stackDisplayArea);
        scrollPane.setBounds(20, 270, 700, 250); 
        getContentPane().add(scrollPane);

        // Action Listeners
        stack = new Stack<>();

        btnCreateStack.addActionListener(e -> {
            try {
                int size = Integer.parseInt(sizeField.getText());
                if (size <= 0) {
                    JOptionPane.showMessageDialog(null, "Size must be greater than 0.");
                } else {
                    stack.clear();
                    JOptionPane.showMessageDialog(null, "Stack of size " + size + " created.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        });

        btnPushElement.addActionListener(e -> {
            try {
                int element = Integer.parseInt(elementField.getText());
                stack.push(element);
                JOptionPane.showMessageDialog(null, "Element " + element + " pushed to the stack.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
            }
        });

        btnPopElement.addActionListener(e -> {
            if (stack.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Stack is empty. Cannot pop elements.");
            } else {
                int poppedElement = stack.pop();
                JOptionPane.showMessageDialog(null, "Element " + poppedElement + " popped from the stack.");
            }
        });

        btnDisplayStack.addActionListener(e -> displayStack());

        // Redirect to Home Page on Back Button Click
        btnBack.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Redirecting to Home Page...");
            new HomePage().setVisible(true); // Redirect to HomePage
            dispose(); // Close current StackFrame
        });
    }

    private void displayStack() {
        if (stack.isEmpty()) {
            stackDisplayArea.setText("Stack is empty.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Integer element : stack) {
                sb.append(element).append(" -> ");
            }
            sb.setLength(sb.length() - 4); 
            stackDisplayArea.setText(sb.toString());
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
                StackFrame frame = new StackFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}