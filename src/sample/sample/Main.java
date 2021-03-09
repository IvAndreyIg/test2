package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    public Stage stage; //Определяет главное окно модуля
    public Desktop desktop = Desktop.getDesktop();
    public Pane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader=new FXMLLoader();

        loader.setLocation(ControllerWelcome.class.getResource("Welcome.fxml"));


       // Parent root = FXMLLoader.load(Controller.class.getResource("Welcome.fxml"));
      //  primaryStage.setTitle("Hello World");
      //  primaryStage.setScene(new Scene(root, 300, 275));
      //  primaryStage.show();

        rootLayout=(Pane)loader.load();
        ControllerWelcome controllerWelcomeTableForm = loader.getController();

        controllerWelcomeTableForm.Init(this);
        stage=new Stage();
      //  controllerTableForm.setPstage(stage);
       // controllerTableForm.Init();
        stage.setTitle("Char Welcome");



        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);
        //scene.setCursor(new ImageCursor(a));

        stage.show();



        System.out.println("fuck");



       /* Thread.sleep(3000);
        try {
            controllerTableForm.send();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*while(true){

              *//*  comandSendler.send('i');
                comandSendler.send('n');
                comandSendler.send('g');*//*

            System.out.println("2");
        }
*/
    }


    public static void main(String[] args) {
        launch(args);
    }
}
