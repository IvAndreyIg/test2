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
        //   System.out.print(LogField.getText());



        send();

    }

    public void Init(String ip,int port)   {
        socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(ip, port),2000);
            out = new PrintWriter(socket.getOutputStream(), true);
            in=new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        portLabel.setText(String.valueOf(port));

    }

    public void send()  {


    }
}
