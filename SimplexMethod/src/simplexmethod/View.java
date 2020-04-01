/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexmethod;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    TextField[] x0;

    public View(Stage primaryStage) throws ClassNotFoundException, NullPointerException {
        setElements(primaryStage);
    }

    private void setElements(Stage primaryStage) {
        border = new BorderPane();
        border.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
        createPane();
        border.setCenter(grid);
        System.out.println(String.valueOf(12 / 7));
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
        box1.getChildren().addAll(new Text("Кол-во переменных\t"), comboBoxX, new Text("\tКол-во ограничений\t"), comboBoxRestrictions, new Text("\t"), okButton);
        stage.setScene(scene);
        stage.show();
        okButton.setOnAction((ActionEvent event) -> {
            if (Integer.parseInt((String) comboBoxX.getValue()) >= Integer.parseInt((String) comboBoxRestrictions.getValue())) {
                stage.close();
                createInputTable();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Ошибка с размерами");
                alert.setContentText("\"Количество переменных должно быть >= количества ограничений\"");
                alert.showAndWait();
            }
        });
    }

    public void createInputTable() {
        x0 = new TextField[(Integer.parseInt((String) comboBoxX.getValue()))];
        arr = new TextField[(Integer.parseInt((String) comboBoxRestrictions.getValue()) + 1)][(Integer.parseInt((String) comboBoxX.getValue()) + 1)];
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
        arr[0][Integer.parseInt((String) comboBoxX.getValue())].setText("0");
        gridSimplex.add(new Text(""), 0, (2 * Integer.parseInt((String) comboBoxRestrictions.getValue())) + 3);
        Button btnInput = new Button("Ввод");
        btnInput.setOnAction((ActionEvent event) -> {
            try {
                if (checkInputValue()) {
                    InputInfo.readInput(arr, Integer.parseInt((String) comboBoxRestrictions.getValue()), Integer.parseInt((String) comboBoxX.getValue()));
                    ChoiceBasisDialog a = new ChoiceBasisDialog(InputInfo.sTable);
                    System.out.println(InputInfo.sTable);
                    createSimplexStepTable();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Ошибка с форматом ввода");
                    alert.setContentText("\"На ввод должно поступать целое число, либо обыкновенная дробь \nФормат числа[1,213,-123...]\nФормат дроби[1/2,-1/3,-1/-3,0/2...]\"");
                    alert.showAndWait();
                }
            } catch (IOException | WrongNumException | NullPointerException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnInput.getStyleClass().add("btnInput");
        gridSimplex.add(btnInput, 0, (2 * Integer.parseInt((String) comboBoxRestrictions.getValue())) + 4);
    }

    public void createSimplexStepTable() {
        StackPane root = new StackPane();
        String[][] staffArray = InputInfo.sTable.getSimplexTable();
        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(staffArray));
        data.remove(0);//remove titles from data
        TableView<String[]> table = new TableView<>();
        for (int i = 0; i < staffArray[0].length; i++) {
            TableColumn tc = new TableColumn(staffArray[0][i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setPrefWidth(90);
            table.getColumns().add(tc);
        }
        table.setItems(data);
        root.getChildren().add(table);
        Stage stage = new Stage();
        stage.setTitle("TableView (o7planning.org)");
        Scene scene = new Scene(root, 450, 300);
        stage.setScene(scene);
        stage.show();
    }

    private boolean checkInputValue() {
        boolean result = true;
        for (TextField[] elm : arr) {
            for (TextField input : elm) {
                //проверка числа/дроби
                if (!input.getText().matches("-?[0-9]+(['/']{1}-?[1-9]{1}[0-9]*)?")) {
                    result = false;
                }
            }
        }
        return result;
    }
}
