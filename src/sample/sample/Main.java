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



        rootLayout=(Pane)loader.load();
        ControllerWelcome controllerWelcomeTableForm = loader.getController();

        controllerWelcomeTableForm.Init(this);
        stage=new Stage();

        stage.setTitle("Chat Wellcum");



        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);
        //scene.setCursor(new ImageCursor(a));

        stage.show();







    }


    public static void main(String[] args) {
        launch(args);
    }
}
