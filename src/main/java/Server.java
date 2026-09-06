import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    private static final List<Socket> clientSockets =
            Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(4000)) {

            System.out.println("✅ Server started on port 4000...");

            while (true) {

                Socket socket = serverSocket.accept();

                System.out.println("👤 New client connected: " + socket);

                clientSockets.add(socket);

                new ClientHandler(socket).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler extends Thread {

        private Socket socket;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            try {

                in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );

                String message;

                while ((message = in.readLine()) != null) {

                    System.out.println("📩 " + message);

                    broadcast(message, socket);
                }

            } catch (IOException e) {

                System.out.println("❌ Client disconnected: " + socket);

            } finally {

                try {
                    socket.close();
                    clientSockets.remove(socket);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void broadcast(String message, Socket sender) {

            synchronized (clientSockets) {

                for (Socket client : clientSockets) {

                    if (!client.equals(sender)) {

                        try {

                            PrintWriter out =
                                    new PrintWriter(
                                            client.getOutputStream(),
                                            true
                                    );

                            out.println(message);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}