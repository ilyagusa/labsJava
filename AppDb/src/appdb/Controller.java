/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package appdb;

import java.sql.SQLException;
import javafx.stage.Stage;

/**
 *
 * @author ilyag
 */
public class Controller {
     static View view;

    public static void SetScene(Stage primaryStage) throws ClassNotFoundException, SQLException {
        view = new View(primaryStage);
    }
}
