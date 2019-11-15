/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdb;

import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author ilyag
 */
public class AppDb extends Application {

    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {

        Controller.SetScene(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}

