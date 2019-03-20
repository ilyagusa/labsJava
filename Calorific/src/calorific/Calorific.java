/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calorific;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author gusev5
 */
public class Calorific extends Application {

    GridPane root;
    Text actiontarget;
    TextField nameTextField;
    TextField valueYear;
    Button btn;
    ComboBox comboBox;
    Map<String, Integer> hashMap;
    LinkedList a;

    public void initRootLayout() {

        hashMap = new HashMap<>();
        hashMap.put("Индейка", 198);
        hashMap.put("Омлет", 209);
        hashMap.put("Вафли", 543);
        hashMap.put("Вишня", 52);
        hashMap.put("Огурцы", 13);
        hashMap.put("Колбаса", 301);
        comboBox = new ComboBox<>();

        comboBox.getItems().addAll(hashMap.keySet());

        final Label label = new Label();
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setStyle(STYLESHEET_MODENA);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Считаем каллории в вашей колбаске");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        root.add(scenetitle, 0, 0, 2, 1);
        Label userName = new Label("Вес вашей колбаски:");
        root.add(userName, 0, 1);

        nameTextField = new TextField();
        root.add(nameTextField, 1, 1);
        btn = new Button("Вычислить");
        btn.setBackground(Background.EMPTY);
        btn.setStyle("-fx-backround-color: #0000FF");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(btn, comboBox, label);

        root.add(hbBtn, 0, 2);

        actiontarget = new Text();

        btn.setOnAction((ActionEvent e) -> {
            calculateAge();
        });

        actiontarget.setFont(Font.font("Arial", FontWeight.BLACK, 15));
        root.add(actiontarget, 1, 2);
    }

    private void calculateAge() {
        double weight = Double.parseDouble(nameTextField.getText());
        int calories = hashMap.get(comboBox.getValue().toString());
        double result = weight * calories / 100;
        actiontarget.setFill(Color.BLACK);
        actiontarget.setText("В " + nameTextField.getText() + " граmmаx  " + comboBox.getValue() + " " + result + " kалоpий");

    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("kek");

        initRootLayout();
        Scene scene = new Scene(root, 800, 500, Color.BLUE);
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
