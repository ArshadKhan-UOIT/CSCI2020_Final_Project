package main.java.CourseContent.Chat;

import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class server{
    String messagesSent;
    int messageCounter = 0;

    //Called to start the server. It will start by making a connection to the client, after which it will make a new
    //thread making a new HandleClient when a connection is made, so that it can keep looking for new clients.
    public void startServer(){
        new Thread(()->{
            try{
                ServerSocket serverSocket = new ServerSocket(4000);

                //Will keep looking for connections, and when one is made will send it to it's own thread
                while(true){
                    Socket socket = serverSocket.accept();
                    new Thread(new HandleAClient(socket)).start();
                }
            } catch (IOException e) {
            }
        }).start();
    }

    //A HandleAClient class, it takes in a socket connection, and has a function .start() that when called will
    //make a input/output stream, and keep looking for any incoming messages, when a message is found it will output
    //the message to all clients.
    class HandleAClient implements Runnable{
        private Socket socket;
        private int NmessagesSent;

        public HandleAClient(Socket socket){
            this.socket=socket;
        }

        @Override
        public void run() {
            try{
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                new Thread(()->{
                    try{
                        while(true){
                            TimeUnit.SECONDS.sleep(1);
                            if(messageCounter != NmessagesSent ){
                                out.writeUTF(messagesSent);
                                NmessagesSent=messageCounter;
                            }
                        }
                    } catch (IOException | InterruptedException e) {
                    }
                }).start();

                while (true){
                    String message = in.readUTF();

                    Platform.runLater(()->{
                        messagesSent = message;
                        messageCounter +=1;
                        NmessagesSent = messageCounter;
                        try {
                            out.writeUTF(messagesSent);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
