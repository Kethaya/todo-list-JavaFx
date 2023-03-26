package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Data.OtherData;
import sample.Data.TodoData;

import java.io.IOException;

public class Main extends Application {
    double x,y = 0;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/sample.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        //move around here
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        try {
            TodoData.getInstance().storeTodoItems();
            OtherData.getInstance().storeOtherItems();

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void init() throws Exception {
        try {
            
            TodoData.getInstance().loadTodoItems();
            OtherData.getInstance().loadOtherItems();

        } catch(IOException e) {
            throw e;
        }
    }
}
