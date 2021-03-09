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


    public Stage stage; //Определяет главное окно модуля
    public Desktop desktop = Desktop.getDesktop();
    public Pane rootLayout;
    public Label errorField;

    Socket socket;
 //   Scanner Scanner;
    PrintWriter out;
    Scanner in;

    public void Click1(ActionEvent actionEvent) throws IOException {
        System.out.println("Sys");
     //   System.out.print(LogField.getText());



            send();

    }

    public void Init(Main parent) throws IOException {
        this.parent=parent;
        socket = new Socket();
        socket.connect(new InetSocketAddress(IpField.getText(), Integer.parseInt(PortField.getText())),2000);
        out = new PrintWriter(socket.getOutputStream(), true);
        in=new Scanner(socket.getInputStream());

    }

    public void nextPage(String ip,int port){


        FXMLLoader loader=new FXMLLoader();

        loader.setLocation(ControllerSimplyText.class.getResource("SimplyText.fxml"));


        // Parent root = FXMLLoader.load(Controller.class.getResource("Welcome.fxml"));
        //  primaryStage.setTitle("Hello World");
        //  primaryStage.setScene(new Scene(root, 300, 275));
        //  primaryStage.show();

        try {
            rootLayout=(Pane)loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControllerSimplyText controllerWelcomeTableForm = loader.getController();

        controllerWelcomeTableForm.Init(ip,port);
        stage=new Stage();
        //  controllerTableForm.setPstage(stage);
        // controllerTableForm.Init();
        stage.setTitle("Chat SimplyText");



        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);
        //scene.setCursor(new ImageCursor(a));

        stage.show();

        parent.stage.close();


        System.out.println("fuck");
    }


    public void send()  {


        out.println("log:"+LogField.getText()+" pass:"+PassField.getText());
       // while (true) {
            if (in.hasNext()) {
                // считываем его
                String inMes = in.nextLine();

                System.out.println("inMes " + inMes);

                if (inMes.contains("port:")) {

                    nextPage(IpField.getText(), Integer.parseInt(inMes.substring(5)));
                }else {

                    errorField.setText(inMes);

                }



                //String clientsInChat = "Клиентов в чате = ";
            /*if (inMes.indexOf(clientsInChat) == 0) {
                jlNumberOfClients.setText(inMes);
            } else {
                // выводим сообщение
                jtaTextAreaMessage.append(inMes);
                // добавляем строку перехода
                jtaTextAreaMessage.append("\n");
            }*/
            }
           // System.out.println("nomes ");
      //  }

        //System.out.println("Log"+clientMessage.indexOf("log:")+"passs:"+clientMessage.indexOf("pass:"));
    }



}
