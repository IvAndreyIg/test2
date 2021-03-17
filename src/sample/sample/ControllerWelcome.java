package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ControllerWelcome {
    public TextField LogField;
    public TextField PassField;
    public TextField PortField;
    public TextField IpField;
    public Main parent;

    public boolean done=false;


    public Stage stage;
    public Desktop desktop = Desktop.getDesktop();
    public Pane rootLayout;
    public Label errorField;

    Socket socket;

    PrintWriter out;
    Scanner in;

    public void Click1(ActionEvent actionEvent) throws IOException {
        System.out.println("Sys");


            send();

    }

    public void Init(Main parent)   {
        this.parent=parent;

    }

    public void nextPage(String ip,int port){


        FXMLLoader loader=new FXMLLoader();

        loader.setLocation(ControllerSimplyText.class.getResource("SimplyText.fxml"));


        try {
            rootLayout=(Pane)loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControllerSimplyText controllerWelcomeTableForm = loader.getController();

        controllerWelcomeTableForm.Init(ip,port);
        stage=new Stage();

        stage.setTitle("Chat SimplyText");



        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);

        stage.show();

        parent.stage.close();


    }


    public void send()  {

        if(!done){

            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress(IpField.getText(), Integer.parseInt(PortField.getText())),2000);
                System.out.println("socket.getInetAddress();"+socket.getInetAddress());
                out = new PrintWriter(socket.getOutputStream(), true);
                in=new Scanner(socket.getInputStream());
                done=false;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("done"+done);
                errorField.setText("error: connect timed out");
                return;
            }

        }



        out.println("log:"+LogField.getText()+" pass:"+PassField.getText());

            if (in.hasNext()) {

                String inMes = in.nextLine();


                if (inMes.contains("port:")) {


                    nextPage(IpField.getText(), Integer.parseInt(inMes.substring(5)));
                }else {

                    errorField.setText(inMes);

                }




            }

    }



}
