package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ControllerSimplyText {


    public TextArea HistoryArea;
    public TextArea OnlineArea;
    public Label portLabel;
    public TextField MessageField;
    Socket socket;
    //   Scanner Scanner;
    PrintWriter out;
    Scanner in;

    public void Click1(ActionEvent actionEvent) throws IOException {
        System.out.println("Sys");




        send();

    }

    public void Init(String ip,int port)   {
        socket = new Socket();
        try {
            System.out.println("dwd2"+ip+" "+port+"d");
            socket.connect(new InetSocketAddress(ip, port),2000);
            out = new PrintWriter(socket.getOutputStream(), true);
            in=new Scanner(socket.getInputStream());
            //socket.getInetAddress();
            System.out.println("socket.getInetAddress();"+socket.getInetAddress());
            out.println("Message:"+"MessageField.getText()");
        } catch (IOException e) {
            e.printStackTrace();
        }
        portLabel.setText("connection port:"+String.valueOf(port));

       Chat ch=new Chat(HistoryArea, OnlineArea, out, in);
        new Thread(ch).start();



        Thread thread = new Thread(){
            public void run(){
                while(true){




                    try {out.println("give:History");
                        Thread.sleep(800);
                        out.println("give:Online");
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread.start();


    }

    public void send()  {

        System.out.println("socket.getInetAddress();"+socket.getInetAddress());
        out.println("Message:"+MessageField.getText());
        MessageField.clear();
    }
}
