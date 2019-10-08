/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Илья
 */
public class ModelDialog {

    private Number a;
    private TextField nameEdit;
    private int chislo;
//    private Spinner<Integer> personnelEdit;
//    private ComboBox holidayEdit;
//    private DatePicker dateEdit;
//    private Spinner<Double> drinkVolumeEdit;
    private Font font;
    private GridPane rootDia;
    private GridPane root1;
    private Dialog<Number> dialog;

    private void createNameText() {
        Label nameOrg = new Label("Введите число:");
        nameOrg.setFont(font);
        rootDia.add(nameOrg, 0, 0);
        nameEdit = new TextField();
        nameEdit.setFont(Font.font(24));
        nameEdit.setText(a.getFirst() + "" + a.getSecond() + "" + a.getThird() + "" + a.getFourth());
        rootDia.add(nameEdit, 0, 1);
    }

    private void createButtons() {
        ButtonType buttonTypeOk = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeCancel);
        dialog.setResultConverter((ButtonType b) -> {
            if (b == buttonTypeOk) {
                if (isInputValid() == true) {
                    handleOk();
                    return this.a;
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибочка");
                    alert.setHeaderText("Вы ввели что-то непонятное");
                    alert.setContentText("\"В числе должны быть только цифры и ничего больше!!!\\n \"");
                    alert.showAndWait();
                }
            }
            return null;
        });
    }

    public ModelDialog(Number num, String title,String header) {
        this.a = num;
        dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        rootDia = new GridPane();
        rootDia.setAlignment(Pos.CENTER);
        rootDia.setHgap(10);
        rootDia.setVgap(10);
        font = Font.font("Tahoma", FontWeight.NORMAL, 20);
        createNameText();
        dialog.getDialogPane().setContent(rootDia);
        createButtons();
        dialog.showAndWait();
    }

    public Dialog<Number> getDialog() {
        return dialog;
    }

    public boolean isInputValid() {
        boolean val = false;
        if (nameEdit.getText().matches("[0-9]+") && this.a.getSize() == nameEdit.getText().length()) {
            val = true;
        }
        return val;
    }

    private void handleOk() {
        chislo = Integer.parseInt(nameEdit.getText());
        a.setFourth(chislo % 10);
        chislo = chislo / 10;
        a.setThird(chislo % 10);
        chislo = chislo / 10;
        a.setSecond(chislo % 10);
        chislo = chislo / 10;
        a.setFirst(chislo % 10);
//        org.setPersonnel(personnelEdit.getValue());
//        org.setHoliday(holidayEdit.getValue().toString());
//        org.setDate(dateEdit.getValue());
//        org.setDrinkVolume(drinkVolumeEdit.getValue());
    }
}
