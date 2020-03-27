package main.java.CourseContent.Chat;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class client{
    //Global Variables needed for functions
    Socket socket;
    DataOutputStream out;
    TextArea ta;
    Button send;
    TextField message;

    //This is a constructor that takes in a textpane, button, and textfield, we use to make client work
    public client(TextArea textPane, Button send, TextField enterText) {
        this.ta = textPane;
        this.send = send;
        this.message = enterText;
    }

    //This is called when to set up the chat/client it will start the connection to the server, and then call the
    //receiveMessages function
    public void runChat(String u) throws IOException {
        //Attempts to connect to the localHost server.
        socket = new Socket(InetAddress.getLocalHost(), 4000);
        out = new DataOutputStream(socket.getOutputStream());

        //Sets button sent in, to start sendMessage if clicked on
        send.setOnAction(e -> {
            try {
                sendMessage(message.getText(), u);
                message.clear();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        //Calls receiveMessages to keep checking for messages.
        receiveMessages();
    }

    //Used so that when called with a message sent in, it will send the message to the server, using the global
    //Variables, this assumes that the server is already connected
    public void sendMessage(String message, String u) throws IOException {
        //To make sure no blank message is sent.
        if (!(message.isBlank())){
            out.writeUTF(u + ": " + message);
            out.flush();
        }
    }

    //This function runs in the background so that it will open a input stream with the server and wait for any
    //messages to be sent from the server, it will then append any input to the global TextArea
    public void receiveMessages(){

        //Makes a thread to check for messages
        new Thread(()->{
            try{
                DataInputStream in = new DataInputStream(socket.getInputStream());

                //Reads in the message and adds it to the TextArea
                while(true){
                    String message = in.readUTF();

                    Platform.runLater(()->{
                        ta.appendText(message+"\n");
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
