package main.java.CourseContent.Chat;

import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class server{
    //This is used to save the last message sent by any client.
    String messagesSent;
    //Will go up by one each time a message is sent, by any client.
    int messageCounter = 0;

    int clientNm;

    ServerSocket serverSocket;

    //Called to start the server. It will start by making a connection to the client, after which it will make a new
    //thread making a new HandleClient when a connection is made, so that it can keep looking for new clients.
    public void startServer(){
        new Thread(()->{
            try{
                serverSocket = new ServerSocket(4000);

                //Will keep looking for connections, and when one is made will send it to it's own thread
                while(true){
                    Socket socket = serverSocket.accept();
                    new Thread(new HandleAClient(socket)).start();
                    clientNm++;
                }
            } catch (Exception e) {
                System.out.println("Connection Failed");
            }
        }).start();
    }

    //A HandleAClient class, it takes in a socket connection, and has a function .start() that when called will
    //make a input/output stream, and keep looking for any incoming messages, when a message is found it will output
    //the message to all clients.
    class HandleAClient implements Runnable{
        private Socket socket;

        //This variable is needed to track how much messages this specific client has sent.
        private int NumberOfMessagesSent;

        //Constructor that just takes a socket and saves it in the class to use locally
        public HandleAClient(Socket socket){
            this.socket=socket;
        }

        @Override
        public void run() {
            try{
                //Makes input/output streams using the socket sent in the constructor.
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                //Makes a thread that will check every second if this client has the correct amount of messages.
                new Thread(()->{
                    try{
                        while(true){
                            //Stops this for 1 second
                            TimeUnit.SECONDS.sleep(1);

                            //This will check if the localMessages sent is different from the amount sent over the server
                            //If so it will write the last message. and make the local and global variables equal
                            if(messageCounter != NumberOfMessagesSent ){
                                out.writeUTF(messagesSent);
                                NumberOfMessagesSent=messageCounter;
                            }
                        }
                    } catch (IOException | InterruptedException e) {
                        System.out.println("Message failed to be sent");
                    }
                }).start();

                //Uses a thread to keep looking for messages from the client
                while (true){
                    String message = in.readUTF();

                    //If a message is found from the client, it will save it as the last message, and change the global
                    //counter, finally it will write the message to the client and add one to the local message counter
                    Platform.runLater(()->{
                        messagesSent = message;
                        messageCounter +=1;
                        NumberOfMessagesSent = messageCounter;
                        try {
                            out.writeUTF(messagesSent);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } catch (IOException e) {
                System.out.println("Message Failed to be sent");

                //This code is here so that if a user closes the window, and there are no users left
                //it will stop the server from running
                if(clientNm-1==0){
                    System.exit(1);
                } else {
                    clientNm--;
                }
            }
        }
    }
}
