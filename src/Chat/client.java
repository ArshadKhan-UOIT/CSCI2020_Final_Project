package Chat;

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
    Socket socket;
    DataOutputStream out;
    TextArea ta;
    Button send;
    TextField message;

    public client(TextArea textPane, Button send, TextField enterText) {
        this.ta = textPane;
        this.send = send;
        this.message = enterText;

    }

    public void runChat() throws IOException {
        socket = new Socket(InetAddress.getLocalHost(),4000);
        out = new DataOutputStream(socket.getOutputStream());

        send.setOnAction(e-> {
            try {
                sendMessage(message.getText());
                message.clear();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        receiveMessages();
    }

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }

    public void receiveMessages(){
        new Thread(()->{
            try{
                DataInputStream in = new DataInputStream(socket.getInputStream());

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
