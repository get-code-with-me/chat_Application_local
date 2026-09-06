# 💬 Chat Application - Java Socket Programming

A simple and interactive **real-time desktop chat application** developed using **Java Socket Programming and Java Swing**.

This project demonstrates how multiple clients can connect to a central server and exchange messages in real time using TCP socket communication.

---

## 📌 Project Overview

The Chat Application follows a **Client-Server Architecture**.

The server listens for incoming client connections on **port 4000**. Multiple clients can connect to the server simultaneously. When a client sends a message, the server receives it and broadcasts the message to all other connected clients.

The client application provides a simple graphical user interface using **Java Swing**, allowing users to:

- Connect to the chat server
- Enter their name
- Send messages
- Receive messages from other users
- Disconnect from the chat

---

## ✨ Features

- 🔌 TCP Socket-based client-server communication
- 👥 Support for multiple clients
- 💬 Real-time message broadcasting
- 🖥️ User-friendly Java Swing GUI
- 👤 Username support
- 📩 Instant message receiving
- ❌ Client disconnect functionality
- 🧵 Multithreading for handling multiple clients
- 🔒 Localhost-based communication for testing

---

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Core programming language |
| Java Swing | Graphical User Interface |
| Java Socket Programming | Network communication |
| TCP/IP | Client-server communication |
| Multithreading | Handling multiple clients |
| Maven | Project and dependency management |
| IntelliJ IDEA | Development environment |

---

## 📂 Project Structure

```text
Chat_Application_Local
│
├── src
│   └── main
│       └── java
│           ├── ChatClientGUI.java
│           ├── Server.java
│           └── org.example
│
├── .gitignore
├── pom.xml
└── README.md
```
## 🏗️ Application Architecture
                ┌─────────────────┐
                │     SERVER      │
                │    Port: 4000   │
                └────────┬────────┘
                         │
              ┌──────────┼──────────┐
              │          │          │
              ▼          ▼          ▼
          Client 1    Client 2    Client 3
          (Swing)     (Swing)     (Swing)

  
## ⚙️ How to Run
1. Start the Server

Run:

Server.java

The server will start on:

localhost:4000
2. Start the Client

Run:

ChatClientGUI.java

Enter your name when prompted.

3. Connect Multiple Clients

Run ChatClientGUI.java multiple times to open multiple chat windows.

Example:

Client 1 → Rakhi
Client 2 → User2
Client 3 → User3

Messages sent by one client will be received by the other connected clients.

🧵 Multithreading

The server uses a separate ClientHandler thread for every connected client. This allows multiple users to communicate simultaneously.

new ClientHandler(socket).start();
🔄 Message Flow
Client
   │
   │ Send Message
   ▼
Server
   │
   │ Broadcast
   ▼
Other Connected Clients
🎯 Learning Outcomes

This project demonstrates practical understanding of:

Java Socket Programming
Client-Server Architecture
TCP communication
Java I/O Streams
Multithreading
Java Swing
Exception Handling
Multiple Client Management
🔮 Future Improvements
🔐 User authentication
💾 Database-based chat history
📨 Private messaging
📎 File and image sharing
🟢 Online/offline status
🔔 Notifications
🎨 Modern UI
🔒 Message encryption
👩‍💻 Author

Rakhi

Java Developer Learner 
