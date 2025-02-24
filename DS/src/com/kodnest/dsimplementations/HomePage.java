package com.kodnest.dsimplementations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {

    private static final long serialVersionUID = 1L;

    public HomePage() {
        setTitle("Data Structures Home Page");
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Gradient panel for the background
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color bgColor = new Color(173, 216, 230);
                Color gradientColor = new Color(100, 149, 237);
                GradientPaint gp = new GradientPaint(0, 0, bgColor, 0, getHeight(), gradientColor);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Title label
        JLabel lblTitle = new JLabel("Welcome to Data Structures");
        lblTitle.setFont(new Font("Roboto", Font.BOLD, 36));
        lblTitle.setForeground(new Color(25, 42, 86));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(0, 20, 800, 50);
        contentPane.add(lblTitle);

        // Panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(3, 2, 30, 30));
        buttonPanel.setBounds(150, 120, 500, 350);
        contentPane.add(buttonPanel);

        // Add buttons to the panel
        buttonPanel.add(createStyledButton("Array", e -> {
            new ArrayJFrame().setVisible(true);
            dispose();
        }));
        buttonPanel.add(createStyledButton("Stack", e -> {
            new StackFrame().setVisible(true);
            dispose();
        }));
        buttonPanel.add(createStyledButton("Queue", e -> {
            new QueueFrame().setVisible(true);
            dispose();
        }));
        buttonPanel.add(createStyledButton("Circular Queue", e -> {
            new CircularQueueFrame().setVisible(true);
            dispose();
        }));
        buttonPanel.add(createStyledButton("Singly Linked List", e -> {
            new SinglyLinkedListFrame().setVisible(true);
            dispose();
        }));
        buttonPanel.add(createStyledButton("Doubly Linked List", e -> {
            new DoublyLinkedListFrame().setVisible(true);
            dispose();
        }));

        // Footer back button
        JButton btnBack = createStyledButton("Exit", e -> onBackPressed());
        btnBack.setBounds(350, 500, 100, 40);
        contentPane.add(btnBack);
    }

    // Method to handle the exit confirmation
    private void onBackPressed() {
        int confirm = JOptionPane.showOptionDialog(this, "Are you sure you want to exit?", "Exit Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    // Method to create styled buttons with hover effects
    private JButton createStyledButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add action listener
        button.addActionListener(action);

        // Hover effects
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });

        return button;
    }

    // Main method to launch the frame
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                HomePage frame = new HomePage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}