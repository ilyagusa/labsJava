/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Илья
 */
public class View extends Group {

    private GridPane grid;
    private Text nameOrg;
    private Text nameEdit;
    private String str;
    private String str1;
    private TextField nameDia;
    private int chislo;
    private Font font;
    private GridPane rootDia;
    private GridPane resultGrid;
    private BorderPane border;
    private Number a;
    private Number b;
    private Number tmp;
    private Stage dialog;
    private Stage result;
    private TextField text;
    private Image picBullandCow;
    private ImageView pic;
    private Image picBull;
    private ImageView picImgBull;
    private Image picCow;
    private ImageView picImgCow;
    private Text pravilo;
    private Text resultTextCow;
    private Text resultTextBull;

    private void createPane() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setVisible(true);
        nameEdit = new Text();
        grid.setAlignment(Pos.CENTER);
        nameEdit.setStyle("-fx-font: 44 arial;");
        grid.setAlignment(Pos.CENTER);
        nameEdit.setText("ИГРА\nБыки\n  &  \nКоровы");
        picBullandCow = new Image("file:bullcow.jpg");
        pic = new ImageView();
        pic.setFitHeight(200);
        pic.setPreserveRatio(true);
        pic.setImage(picBullandCow);
        grid.add(nameEdit, 2, 1);
        grid.add(pic, 3, 1);
        grid.setAlignment(Pos.CENTER);
        Button btnPravila = new Button();
        btnPravila.setText("Узнать правила");
        btnPravila.setOnAction((ActionEvent event) -> {
            pravila();
        });
        grid.add(btnPravila, 3, 3);
    }

    private void setElements(Stage primaryStage) {
        border = new BorderPane();
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(createFileMenu());
        border.setTop(menuBar);
        createPane();
        border.setCenter(grid);
        border.setBackground(new Background(new BackgroundFill(Color.TAN, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(border, 580, 400);
        primaryStage.setTitle("Игра быки и коровы");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void pravila() {
        dialog = new Stage();
        dialog.setTitle("Ввод числа первого игрока");
        rootDia = new GridPane();
        rootDia.setAlignment(Pos.CENTER);
        rootDia.setHgap(10);
        rootDia.setVgap(10);
        pravilo = new Text();
        rootDia.setPadding(new Insets(25, 25, 25, 25));
        pravilo.setText("Правила игры быки и коровы:\n\n"
                + "1.Режим игры человек с человеком\n"
                + "1.1 Два человека загадывают четырехзначное число,после чего\n"
                + "они по очереди отгадывают число соперника\n"
                + "Если угадал цифру в числе на правильной позиции - это бык\n"
                + "Если угадал цифру , но она не на своей позиции  - это корова\n"
                + "1.2 В загадываемом/отгадываемом числе не должны повторяться цифры\n"
                + "1.3 Загадываемое/отгадываемоe число не должно начинаться с 0\n"
                + "1.4 Победа=4 быка\n\n"
                + "2.Режим игры с компьютером\n"
                + "2.1 Человек отгадывает загаданное компьютером число\n"
                + "2.2 На этот режим действуют правила 1.2-1.4\n\n"
                + "ЧТОБЫ НАЧАТЬ ИГРУ НАЖМИТЕ НАЧАТЬ ИГРУ В ВЕРХНЕМ МЕНЮ!");
        rootDia.add(pravilo, 0, 0);
        Scene scene = new Scene(rootDia, 450, 300);
        Button btnOFC = new Button();
        btnOFC.setText("Понял");
        btnOFC.setOnAction((ActionEvent event) -> {
            dialog.close();
        });
        rootDia.add(btnOFC, 0, 1);
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private void playPeople() {
        Controller.resetBulAndCow(a, b);
        inputFirst();
        while (Controller.checkNumber(a) == false) {
            allert();
            inputFirst();
        }
        inputSecond();
        while (Controller.checkNumber(b) == false) {
            allert();
            inputSecond();
        }
        //игра
        while ((a.getBull() != 4) && (b.getBull() != 4)) {
            while (true) {
                ModelDialog dia1 = new ModelDialog(tmp, "Отгадывает игрок 1","Отгадывает первый игрок");
                if (Controller.checkNumber(tmp) == true) {
                    break;
                } else {
                    allert();
                    //dia1.getDialog().showAndWait();
                }
            }
            b.countBullAndCow(tmp);
            goResult(b, "Результат для первого игрока", "");
            while (true) {
                ModelDialog dia2 = new ModelDialog(tmp, "Отгадывает игрок 2","Отгадывает второй игрок");
                if (Controller.checkNumber(tmp) == true) {
                    break;
                } else {
                    allert();
                    //dia2.getDialog().showAndWait();
                }
            }
            a.countBullAndCow(tmp);
            goResult(a, "Результат для второго игрока", "");
        }

        if (a.getBull() == 4 && b.getBull() != 4) {
            goResult(a, "Победа второго игрока!", "Победил 2 игрок");
        }
        if (b.getBull() == 4 && a.getBull() != 4) {
            goResult(b, "Победа первого игрока", "Победил 1 игрок");
        } else if (b.getBull() == 4 && a.getBull() == 4) {
            goResult(a, "Ничья!", "Победила дружба");
        }
    }

    private void playComputer() {
        Controller.rand(a);
        System.out.println(tmp);
        while (true) {
            while (true) {
                ModelDialog dia2 = new ModelDialog(tmp, "Отгадывай","Отгадывай");
                if (Controller.checkNumber(tmp) == true) {
                    break;
                } else {
                    allert();
                }
            }
            a.countBullAndCow(tmp);
            goResult(a, "Результат", "");
            if (a.getBull() == 4) {
                break;
            }
        }
        goResult(a, "Результат", "ПОБЕДА!");
    }

    private void inputFirst() {
        ModelDialog dia1 = new ModelDialog(this.a, "Ввод числа первым игроком","Загадывает игрок 1");
    }

    private void goResult(Number temp, String str, String restext) {
        result = new Stage();
        result.setTitle(str);
        resultGrid = new GridPane();
        resultGrid.setAlignment(Pos.CENTER);
        resultGrid.setHgap(10);
        resultGrid.setVgap(10);
        resultTextCow = new Text();
        resultGrid.setPadding(new Insets(25, 25, 25, 25));
        resultTextCow.setText("" + temp.getCow());
        resultTextBull = new Text();
        resultTextBull.setText("" + temp.getBull());
        Text res = new Text(restext);
        res.setStyle("-fx-font: 34 arial;");
        picBull = new Image("file:bull.jpg");
        picCow = new Image("file:cow.png");
        picImgBull = new ImageView();
        picImgCow = new ImageView();
        picImgBull.setImage(picBull);
        picImgCow.setImage(picCow);
        picImgCow.setFitHeight(150);
        picImgBull.setFitHeight(150);
        /////
        resultGrid.add(resultTextCow, 0, 0);
        resultGrid.add(picImgCow, 1, 0);
        resultGrid.add(resultTextBull, 2, 0);
        resultGrid.add(picImgBull, 3, 0);
        resultGrid.add(res, 1, 2);
        resultGrid.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(resultGrid, 650, 500);
        Button btnNext = new Button();
        btnNext.setText("Далее");
        btnNext.setOnAction((ActionEvent event) -> {
            result.close();
        });
        btnNext.setStyle("-fx-font: 16 arial;");
        btnNext.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        btnNext.setTextFill(Color.BLACK);
        resultGrid.add(btnNext, 1, 3);
        result.setScene(scene);
        result.showAndWait();
    }

    private void inputSecond() {
        ModelDialog dia2 = new ModelDialog(this.b, "Ввод числа вторым игроком","Загадывает игрок 2");
    }

    private void allert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибочка");
        alert.setHeaderText("Ввод корректного числа");
        alert.setContentText("\"В числе не должны повторяться цифры и оно не должно начинаться с нуля!!!\\n \"");
        alert.showAndWait();
    }

    private Menu createFileMenu() {
        Menu menuFile = new Menu("Начать игру");
        MenuItem playPeople = new MenuItem("Игра с человеком");
        playPeople.setOnAction((ActionEvent t) -> {
            playPeople();
            grid.setVisible(true);
//            dataChanged();
        });
        MenuItem playComp = new MenuItem("Игра с компьютером");
        playComp.setOnAction((ActionEvent t) -> {
            playComputer();
        });
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction((ActionEvent t) -> {
            Platform.exit();
        });
        menuFile.getItems().addAll(playPeople, playComp, new SeparatorMenuItem(), exit);
        return menuFile;
    }

    public View(Number a, Number b, Number tmp, Stage primaryStage) {
        this.a = a;
        this.b = b;
        this.tmp = tmp;
        setElements(primaryStage);
    }

}
