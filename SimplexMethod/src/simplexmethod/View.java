/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexmethod;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Ilya
 */
public class View {

    private BorderPane border;
    private GridPane grid;
    private GridPane gridSimplex;
    private GridPane gridArtificial;
    private Text welcome;
    private HBox hbox;
    private ComboBox comboBoxX;
    private ComboBox comboBoxRestrictions;
    TextField[][] arr;

    public View(Stage primaryStage) throws ClassNotFoundException {
        setElements(primaryStage);
    }

    private void setElements(Stage primaryStage) {
        border = new BorderPane();
        border.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
        createPane();
        border.setCenter(grid);
        Scene scene = new Scene(border, 1100, 800);
        scene.getStylesheets().add(("style2.css"));
        primaryStage.setTitle("Simplex Method");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createPane() {
        grid = new GridPane();
        hbox = new HBox();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        welcome = new Text();
        welcome.setText("Приложение , позволяющее решать решать задачу\nлинейного программирования симплекс-методом.\n\nЧтобы выбрать режим нажмите на нужную кнопку");
        welcome.setStyle("-fx-font: 26 arial;");
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setVisible(true);
        Button simplexButton = new Button();
        simplexButton.getStyleClass().add("simplexButton");
        simplexButton.setText("Симплекс метод");
        simplexButton.setOnAction((ActionEvent event) -> {
            grid.setVisible(false);
            createGridSimplex();
            border.setCenter(gridSimplex);
        });
        Button artificialButton = new Button();
        artificialButton.getStyleClass().add("artificialButton");
        artificialButton.setText("Метод искусственного базиса");
        artificialButton.setOnAction((ActionEvent event) -> {
            grid.setVisible(false);
        });
        hbox.getChildren().addAll(simplexButton, new Text("\t\t\t\t\t\t\t\t\t\t\t\t\t"), artificialButton);
        grid.add(hbox, 1, 1);
        grid.add(welcome, 1, 0);
        grid.setAlignment(Pos.CENTER);
        grid.setAlignment(Pos.CENTER);
    }

    private void createGridSimplex() {
        gridSimplex = new GridPane();
        gridSimplex.setPadding(new Insets(10, 10, 10, 10));
        comboBoxX = new ComboBox();
        comboBoxX.getItems().addAll(
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"
        );
        comboBoxX.setValue("1");
        comboBoxRestrictions = new ComboBox();
        comboBoxRestrictions.getItems().addAll(
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"
        );
        comboBoxRestrictions.setValue("1");
        Button okButton = new Button("Ввод");
        Stage stage = new Stage();
        stage.setTitle("Ввод параметров");
        Scene scene = new Scene(new HBox(20), 587, 40);
        HBox box1 = (HBox) scene.getRoot();
        box1.setPadding(new Insets(5, 5, 5, 5));
        Button btnInput = new Button("Ввод");
        box1.getChildren().addAll(new Text("Кол-во переменных\t"), comboBoxX, new Text("\tКол-во ограничений\t"), comboBoxRestrictions, new Text("\t"), okButton);
        stage.setScene(scene);
        stage.show();
        okButton.setOnAction((ActionEvent event) -> {
            gridSimplex.setVisible(true);
            arr = new TextField[(Integer.parseInt((String) comboBoxRestrictions.getValue()) + 1)][(Integer.parseInt((String) comboBoxX.getValue()) + 1)];
            stage.close();
            int idi = 0;
            for (int i = 0; i < Integer.parseInt((String) comboBoxRestrictions.getValue()) + 1; i++) {
                int idj = 0;
                Text txName = new Text("Целевая Функция:");
                if (idi != 0) {
                    txName.setText("Ограничение №" + (i));
                }
                gridSimplex.add(txName, 0, idi);
                idi++;
                for (int j = 0; j < Integer.parseInt((String) comboBoxX.getValue()) + 1; j++) {
                    arr[i][j] = new TextField();
                    arr[i][j].setMinWidth(30);
                    if ((i != 0 || j != Integer.parseInt((String) comboBoxX.getValue()))) {
                        gridSimplex.add(arr[i][j], idj, idi);
                    } else {
                        gridSimplex.add(new Text("===> MIN"), idj, idi);
                    }
                    idj++;
                    Text tx = new Text("*x" + (j + 1) + "+");
                    if (j == Integer.parseInt((String) comboBoxX.getValue()) - 1) {
                        tx.setText("*x" + (j + 1) + "=");
                    }
                    if (j == Integer.parseInt((String) comboBoxX.getValue())) {
                        tx.setText("");
                    }
                    gridSimplex.add(tx, idj, idi);
                    idj++;
                }
                idi++;
            }
            gridSimplex.add(new Text(""), 0, (2 * Integer.parseInt((String) comboBoxRestrictions.getValue())) + 2);
            gridSimplex.add(btnInput, 0, (2 * Integer.parseInt((String) comboBoxRestrictions.getValue())) + 3);
        });
        btnInput.setOnAction((ActionEvent event) -> {
            try {
                InputInfo.readInput(arr, Integer.parseInt((String) comboBoxRestrictions.getValue()), Integer.parseInt((String) comboBoxX.getValue()));
            } catch (IOException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WrongNumException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnInput.getStyleClass().add("btnInput");
    }
    
    
    private void choiceBase(){
        
    }
}
