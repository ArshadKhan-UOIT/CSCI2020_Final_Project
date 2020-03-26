package Chat;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server extends Application {
    private int clientNo =0;
    @Override
    public void start(Stage stage) throws Exception {
        startServer();
    }

    public void startServer(){
        new Thread(()->{
            try{
                ServerSocket serverSocket = new ServerSocket(4000);

                while(true){
                    Socket socket = serverSocket.accept();
                    clientNo++;
                    new Thread(new HandleAClient(socket)).start();
                }
            } catch (IOException e) {
               ;
            }
        }).start();
    }

    class HandleAClient implements Runnable{
        private Socket socket;

        public HandleAClient(Socket socket){
            this.socket=socket;
        }

        @Override
        public void run() {
            try{
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                while (true){
                    String message = in.readUTF();

                    Platform.runLater(()->{
                        try {
                            out.writeUTF(message+"\n");
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
