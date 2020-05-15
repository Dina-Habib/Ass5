/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch5assq2;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author dinahabib
 */
public class Ch5AssQ2 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane JPAPane = FXMLLoader.load(getClass().getResource("Q2.fxml"));
        Scene scene=new Scene(JPAPane);
        primaryStage.setTitle("JPA APP");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
