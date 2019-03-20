/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enterage;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import javafx.scene.control.Spinner;
//import javafx.scene.control.SpinnerValueFactory;

/**
 *
 * @author nadezhda
 */
public class EnterAge extends Application {

    GridPane root;
    Text actiontarget;
    TextField nameTextField;
    TextField valueYear;
    Button btn;

    public void initRootLayout() {
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Calculation of age");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        root.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Name:");
        root.add(userName, 0, 1);

        nameTextField = new TextField();
        root.add(nameTextField, 1, 1);

        Label userYear = new Label("Year of birth:");
        root.add(userYear, 0, 2);
        valueYear = new TextField();
        root.add(valueYear, 1, 2);

        btn = new Button("Ok");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        root.add(hbBtn, 1, 4);

        actiontarget = new Text();

        root.add(actiontarget, 1, 6);
        btn.setOnAction((ActionEvent e) -> {
            calculateAge();
        });
    }

    private void calculateAge() {
        String year = valueYear.getText();
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        calendar.setTime(new Date());
        if (year.matches("[0-9]+")) {
            int age = calendar.get(Calendar.YEAR) - Integer.parseInt(year);
            actiontarget.setFill(Color.FIREBRICK);
            actiontarget.setText(nameTextField.getText() + " was " + Integer.toString(age) + " years old");
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Age");
        initRootLayout();
        Scene scene = new Scene(root, 400, 300);
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
