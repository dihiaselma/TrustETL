package com.kaifacun.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static com.kaifacun.javafx.QueryExtractor.*;

public class Main extends Application { //validateBTN
    @Override
    public void start(Stage primaryStage) throws Exception{
        double a=compute("hello","salut");
        String s= "Trust ETL tool";
        Parent root = FXMLLoader.load(getClass().getResource("GUI_TETL.fxml"));
        primaryStage.setTitle(s);
        Image icon = new Image(getClass().getResourceAsStream("images/logo.JPG"));
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 1380, 700));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
