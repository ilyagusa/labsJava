/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexmethod;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author nadezhda
 */
public class ChoiceBasisDialog {

    private SourceTask task;
    private Stage dialog;
    private TextField arr[];
    private Font font;
    private GridPane root;
    private Fractions pos;
    private ScrollPane scrollPane;
    private int idi;

    private void createNameText() {
        arr = new TextField[(task.sizeX())];
        idi = 0;
        System.out.println(task.sizeX());
        for (int i = 0; i < task.sizeX(); i++) {
            Text tx = new Text("x" + (i + 1) + " = ");
            arr[i] = new TextField();
            arr[i].setText("0");
            root.add(tx, 0, idi);
            idi++;
            root.add(arr[i], 0, idi);
            idi++;
        }
    }

    private void createButtons() {
        Button btnOk = new Button("Ok");
        root.add(btnOk, 0, idi);
        btnOk.setOnAction((ActionEvent e) -> {
            if (isInputValid() == true) {
                try {
                    handleOk();
                } catch (WrongNumException ex) {
                    Logger.getLogger(ChoiceBasisDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Ошибка с форматом ввода");
                alert.setContentText("\"На ввод должно поступать целое число, либо обыкновенная дробь \nФормат числа[1,213,-123...]\nФормат дроби[1/2,-1/3,-1/-3,0/2...]\"");
                alert.showAndWait();
            }
        });
        Button btnCancel = new Button("Продолжить используя искуственный базис");
        root.add(btnCancel, 0, idi + 1);
        btnCancel.setOnAction((ActionEvent e) -> {
            try {
                handleCancel();
            } catch (WrongNumException ex) {
                Logger.getLogger(ChoiceBasisDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public ChoiceBasisDialog(SourceTask task) {
        this.task = task;
        dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Edit organization");
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        font = Font.font("Tahoma", FontWeight.NORMAL, 20);
        scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        scrollPane.setContent(root);
        createNameText();
        createButtons();
        Scene scene = new Scene(scrollPane, 400, 400);
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private boolean isInputValid() {
        boolean result = true;
        for (TextField elm : arr) {
            //проверка числа/дроби
            if (!elm.getText().matches("-?[0-9]+(['/']{1}-?[1-9]{1}[0-9]*)?")) {
                result = false;
            }
        }
        return result;
    }

    private void handleOk() throws WrongNumException {
        pos = new Fractions();
        for (int i = 0; i < task.sizeX(); i++) {
            Fraction B = Methods.check(arr[i].getText());
            pos.add(B);
        }
        task.setX0(pos);
        dialog.close();
    }

    private void handleCancel() throws WrongNumException {
        pos = new Fractions();
        for (int i = 0; i < (task.sizeX()); i++) {
            Fraction B = new Fraction(0,1);
            pos.add(B);
        }
        for(int i = task.sizeX(); i<(task.sizeX()+task.sizeEq());i++){
            Fraction B = new Fraction(1,1);
            pos.add(B);
        }
        task.setX0(pos);
        dialog.close();
    }

}
