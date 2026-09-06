
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClientGUI extends JFrame {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private JButton declineButton;
    private String name;

    public ChatClientGUI(String serverAddress, int port) {
        setTitle("💬 Chat Application - " + serverAddress + ":" + port);
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 250));

        // Chat area styling
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setBackground(new Color(255, 255, 255));
        chatArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel (input + buttons)
        JPanel bottomPanel = new JPanel(new BorderLayout(8, 8));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        bottomPanel.setBackground(new Color(230, 230, 240));

        inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        bottomPanel.add(inputField, BorderLayout.CENTER);

        // Send button
        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sendButton.setBackground(new Color(76, 175, 80));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setBorderPainted(false);
        sendButton.addActionListener(e -> sendMessage());

        // Decline button
        declineButton = new JButton("Disconnect");
        declineButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        declineButton.setBackground(new Color(244, 67, 54));
        declineButton.setForeground(Color.WHITE);
        declineButton.setFocusPainted(false);
        declineButton.setBorderPainted(false);
        declineButton.addActionListener(e -> disconnect());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.add(sendButton);
        buttonPanel.add(declineButton);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        inputField.addActionListener(e -> sendMessage());
        setVisible(true);

        try {
            socket = new Socket(serverAddress, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            name = JOptionPane.showInputDialog(this, "Enter your name:");
            if (name == null || name.isEmpty()) {
                name = "Anonymous";
            }

            chatArea.append("✅ Connected as " + name + "\n");
            new Thread(this::receiveMessages).start();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "❌ Unable to connect to server", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void sendMessage() {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            out.println(name + ": " + message);
            chatArea.append("🟩 You: " + message + "\n");
            inputField.setText("");
        }
    }

    private void disconnect() {
        try {
            out.println(name + " has left the chat ❌");
            socket.close();
            dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveMessages() {
        try {
            String msg;
            while ((msg = in.readLine()) != null) {
                chatArea.append("💬 " + msg + "\n");
            }
        } catch (IOException e) {
            chatArea.append("⚠️ Disconnected from server.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatClientGUI("localhost", 4000));
    }
}
