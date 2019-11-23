/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdb;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

/**
 *
 * @author ilyag
 */
class View extends Group {

    DataBaseHandler dbHandler = new DataBaseHandler();

    private GridPane grid;
    private Text nameEdit;
    private HBox editHbox;
    private BorderPane border;
    private Image phonebook;
    private ImageView pic;
    private ObservableList<Human> data = FXCollections.observableArrayList();
    private ObservableList<Phone> dataPh = FXCollections.observableArrayList();

    private void createTablePhone(int id) throws ClassNotFoundException, SQLException {
        dataPh.clear();
        TableColumn idCol = new TableColumn("id");
        TableColumn typeCol = new TableColumn("type");
        TableColumn numberCol = new TableColumn("number");
        TableView<Phone> table = new TableView<Phone>();
        table.setEditable(true);
        //коллекция которая получается при извлечении информации из базы данных
        dataPh = dbHandler.selectPhone(id);
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<Phone, Integer>("id"));
        idCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        idCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Phone, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Phone, Integer> t) {
                        ((Phone) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setId(t.getNewValue());
                    }
                }
        );

        typeCol.setMinWidth(100);
        typeCol.setCellValueFactory(
                new PropertyValueFactory<Phone, String>("type"));
        typeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        typeCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Phone, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Phone, String> t) {
                        ((Phone) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setType(t.getNewValue());
                    }
                }
        );

        numberCol.setMinWidth(100);
        numberCol.setCellValueFactory(
                new PropertyValueFactory<Phone, String>("number"));
        numberCol.setCellFactory(TextFieldTableCell.forTableColumn());
        numberCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Phone, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Phone, String> t) {
                        ((Phone) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setNumber(t.getNewValue());
                    }
                }
        );

        table.setItems(dataPh);
        table.getColumns().addAll(idCol, typeCol, numberCol);

        TextField addPhone = new TextField();
        addPhone.setMaxWidth(120);
        addPhone.setPromptText("PhoneNumber");

        ComboBox addType = new ComboBox();
        addType.getItems().addAll(
                "Рабочий",
                "Домашний",
                "Мобильный"
        );
        addType.setValue("Мобильный");

        HBox editPhone = new HBox();
        final Button addButton = new Button("Добавить");
        addButton.setOnAction((ActionEvent e) -> {
            try {
                if (addPhone.getText().matches("^[1-9]{1}[0-9]*") && !dbHandler.include(addPhone.getText())) {
                    dbHandler.insertPhone(id, addPhone.getText(), addType.getValue().toString());
                    //Добавление телефона в список и обновление информации для отображения в таблицу
                    dataPh = dbHandler.selectPhone(id);

                } else if (addPhone.getText().matches("^[1-9]{1}[0-9]*") == false) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Некорректно введённый номер");
                    alert.setContentText("В номере должны быть только цифры");
                    alert.showAndWait();
                } else if (dbHandler.include(addPhone.getText())) {
                    dataPh = dbHandler.selectPhone(id);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Номер занят");
                    alert.setContentText("Контакт с таким номером существует");
                    alert.showAndWait();
                }
                addPhone.clear();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        editPhone.getChildren().addAll(addType, addPhone, addButton);
        editPhone.setSpacing(3);

        Scene scene = new Scene(new Group());
        Stage stage = new Stage();
        stage.setTitle("Table View Sample");
        stage.setWidth(344);
        stage.setHeight(520);

        Label label = new Label("");
        label.setFont(new Font("Arial", 20));

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(5, 0, 0, 10));
        vbox.getChildren().addAll(label, table, editPhone);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();

    }

    private void createTableHuman() throws ClassNotFoundException, SQLException {
        data.clear();
        TableColumn idCol = new TableColumn("id");
        TableColumn nameCol = new TableColumn("name");
        TableColumn surnameCol = new TableColumn("surname");
        TableColumn addressCol = new TableColumn("address");
        TableColumn emailCol = new TableColumn("email");
        TableColumn categoryCol = new TableColumn("category");
        TableView<Human> table = new TableView<Human>();
        //коллекция которая получается при извлечении информации из базы данных
        data = dbHandler.selectHuman();
        table.setEditable(true);
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<Human, Integer>("id"));
        idCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        idCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, Integer> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setId(t.getNewValue());
                    }
                }
        );

        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Human, String>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, String> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setName(t.getNewValue());
                    }
                }
        );

        surnameCol.setMinWidth(100);
        surnameCol.setCellValueFactory(
                new PropertyValueFactory<Human, String>("surname"));
        surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, String> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setSurname(t.getNewValue());
                    }
                }
        );

        surnameCol.setMinWidth(100);
        surnameCol.setCellValueFactory(
                new PropertyValueFactory<Human, String>("surname"));
        surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, String> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setSurname(t.getNewValue());
                    }
                }
        );

        addressCol.setMinWidth(100);
        addressCol.setCellValueFactory(
                new PropertyValueFactory<Human, String>("address"));
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addressCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, String> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setAddress(t.getNewValue());
                    }
                }
        );

        emailCol.setMinWidth(100);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Human, String>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, String> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setEmail(t.getNewValue());
                    }
                }
        );

        categoryCol.setMinWidth(100);
        categoryCol.setCellValueFactory(
                new PropertyValueFactory<Human, String>("category"));
        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, String> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setCategory(t.getNewValue());
                    }
                }
        );

        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>(""));
        actionCol.setMinWidth(175);

        editHbox = new HBox();
        TextField addName = new TextField();
        addName.setPromptText("Name");
        addName.setMaxWidth(nameCol.getPrefWidth());
        TextField addSurname = new TextField();
        surnameCol.setMaxWidth(surnameCol.getPrefWidth());
        addSurname.setPromptText("Surname");
        TextField addAddress = new TextField();
        addAddress.setMaxWidth(addressCol.getPrefWidth());
        addAddress.setPromptText("Address");
        TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");

        ComboBox addCategory = new ComboBox();
        addCategory.getItems().addAll(
                "Друзья",
                "Деловой партнёр",
                "Родственники"
        );
        addCategory.setValue("Друзья");

        final Button addButton = new Button("Добавить");
        addButton.setOnAction((ActionEvent e) -> {
            data.clear();
            try {
                if (isInputValid(addName.getText(), addSurname.getText(), addAddress.getText(), addEmail.getText()) && !dbHandler.includeHuman(addEmail.getText())) {
                    dbHandler.insertHuman(addName.getText(), addSurname.getText(), addAddress.getText(), addEmail.getText(), addCategory.getValue().toString());
                    data = dbHandler.selectHuman();

                } else if (!isInputValid(addName.getText(), addSurname.getText(), addAddress.getText(), addEmail.getText())) {
                    data = dbHandler.selectHuman();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибочка");
                    alert.setHeaderText("Ошибка ввода");
                    alert.setContentText("\"В одном из введенных вами полей обнаружены недопустимые символы(Имя и фамилия состоят из русских букв и "
                            + "начинаются только с заглавной, а почта вводится в формате __@__.__)\"");
                    alert.showAndWait();
                } else if (dbHandler.includeHuman(addEmail.getText())) {
                    data = dbHandler.selectHuman();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибочка");
                    alert.setHeaderText("Почта занята");
                    alert.setContentText("\"Введенная вами почта уже занята\"");
                    alert.showAndWait();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
            addName.clear();
            addSurname.clear();
            addAddress.clear();
            addEmail.clear();
        });
        editHbox.getChildren().addAll(addName, addSurname, addAddress, addEmail, addCategory, addButton);
        editHbox.setSpacing(3);

        Callback<TableColumn<Human, String>, TableCell<Human, String>> showNum
                = //
                new Callback<TableColumn<Human, String>, TableCell<Human, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Human, String> param) {
                        final TableCell<Human, String> cell = new TableCell<Human, String>() {

                            final Button btn = new Button("Показать номера");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        try {
                                            createTablePhone(table.getItems().get(this.getIndex()).getId());
                                        } catch (ClassNotFoundException | SQLException ex) {
                                            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionCol.setCellFactory(showNum);
        actionCol.setMinWidth(132.5);

/////////////////////////////
        table.setItems(data);
        table.getColumns().addAll(idCol, nameCol, surnameCol, addressCol, emailCol, categoryCol, actionCol);

        Scene scene = new Scene(new Group());
        Stage stage = new Stage();
        stage.setTitle("Результат");
        stage.setWidth(771);
        stage.setHeight(550);

        Label label = new Label("Список контактов");
        label.setFont(new Font("Arial", 20));

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, editHbox);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
////azxczxcxcasascas/////

    public boolean isInputValid(String name, String surname, String address, String email) {
        boolean val = true;
        if ("".equals(name.trim()) || "".equals(surname.trim()) || "".equals(address.trim())
                || "".equals(email.trim()) || !surname.matches("^[А-Я]{1}[а-яё]{1,23}[-]?[А-ЯЁ]?[а-яё]{0,23}")
                || !name.matches("^[А-Я]{1}[а-яё]{1,23}[-]?[А-ЯЁ]?[а-яё]{0,23}") || !email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?"
                        + "^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\"
                        + "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])"
                        + "?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:"
                        + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
            val = false;
        }
        return val;
    }

////adsasaxacaacascaa/////
    private void createTableHuman(String number) throws ClassNotFoundException, SQLException {
        data.clear();
        TableColumn idCol = new TableColumn("id");
        TableColumn nameCol = new TableColumn("name");
        TableColumn surnameCol = new TableColumn("surname");
        TableColumn addressCol = new TableColumn("address");
        TableColumn emailCol = new TableColumn("email");
        TableColumn categoryCol = new TableColumn("category");
        TableView<Human> table = new TableView<Human>();
        //коллекция которая получается при извлечении информации из базы данных
        data = dbHandler.selectHuman(number);
        table.setEditable(true);
        table.setMaxWidth(780);
        table.setMaxHeight(150);
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<Human, Integer>("id"));
        idCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        idCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, Integer> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setId(t.getNewValue());
                    }
                }
        );

        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Human, String>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, String> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setName(t.getNewValue());
                    }
                }
        );

        surnameCol.setMinWidth(100);
        surnameCol.setCellValueFactory(
                new PropertyValueFactory<Human, String>("surname"));
        surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, String> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setSurname(t.getNewValue());
                    }
                }
        );

        addressCol.setMinWidth(100);
        addressCol.setCellValueFactory(
                new PropertyValueFactory<Human, String>("address"));
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addressCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, String> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setAddress(t.getNewValue());
                    }
                }
        );

        emailCol.setMinWidth(100);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Human, String>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, String> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setEmail(t.getNewValue());
                    }
                }
        );

        categoryCol.setMinWidth(100);
        categoryCol.setCellValueFactory(
                new PropertyValueFactory<Human, String>("category"));
        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, String> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setCategory(t.getNewValue());
                    }
                }
        );

        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>(""));
        actionCol.setMinWidth(117.5);
        Callback<TableColumn<Human, String>, TableCell<Human, String>> cellFactory
                = //
                new Callback<TableColumn<Human, String>, TableCell<Human, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Human, String> param) {
                        final TableCell<Human, String> cell = new TableCell<Human, String>() {

                            final Button btn = new Button("Показать номера");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        try {
                                            //shock
                                            createTablePhone(table.getItems().get(this.getIndex()).getId());
                                        } catch (ClassNotFoundException ex) {
                                            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (SQLException ex) {
                                            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        actionCol.setCellFactory(cellFactory);
        table.setItems(data);
        table.getColumns().addAll(idCol, nameCol, surnameCol, addressCol, emailCol, categoryCol, actionCol);

        Scene scene = new Scene(new Group());
        Stage stage = new Stage();
        stage.setTitle("Контакт");
        stage.setWidth(755);
        stage.setHeight(245);

        Label label = new Label("Найденный контакт");
        label.setFont(new Font("Arial", 20));

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    ///////////////
    private void createTableHuman(String paramFind, String paramColumn, String paramCat) throws ClassNotFoundException, SQLException {
        data.clear();
        TableColumn idCol = new TableColumn("id");
        TableColumn nameCol = new TableColumn("name");
        TableColumn surnameCol = new TableColumn("surname");
        TableColumn addressCol = new TableColumn("address");
        TableColumn emailCol = new TableColumn("email");
        TableColumn categoryCol = new TableColumn("category");
        TableView<Human> table = new TableView<Human>();
        //коллекция которая получается при извлечении информации из базы данных
        data = dbHandler.selectHuman(paramFind, paramColumn, paramCat);
        table.setEditable(true);
        TableColumn<Human, Number> indexColumn = new TableColumn<Human, Number>("#");
        indexColumn.setMinWidth(50);
        indexColumn.setSortable(false);
        indexColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue()) + 1));
        table.setMaxWidth(780);
        table.setMaxHeight(400);
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<Human, Integer>("id"));
        idCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        idCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, Integer> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setId(t.getNewValue());
                    }
                }
        );

        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Human, String>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, String> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setName(t.getNewValue());
                    }
                }
        );

        surnameCol.setMinWidth(100);
        surnameCol.setCellValueFactory(
                new PropertyValueFactory<Human, String>("surname"));
        surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, String> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setSurname(t.getNewValue());
                    }
                }
        );

        addressCol.setMinWidth(100);
        addressCol.setCellValueFactory(
                new PropertyValueFactory<Human, String>("address"));
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addressCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, String> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setAddress(t.getNewValue());
                    }
                }
        );

        emailCol.setMinWidth(100);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Human, String>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, String> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setEmail(t.getNewValue());
                    }
                }
        );

        categoryCol.setMinWidth(100);
        categoryCol.setCellValueFactory(
                new PropertyValueFactory<Human, String>("category"));
        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Human, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Human, String> t) {
                        ((Human) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setCategory(t.getNewValue());
                    }
                }
        );

        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>(""));
        actionCol.setMinWidth(117.5);

        Callback<TableColumn<Human, String>, TableCell<Human, String>> cellFactory
                = //
                new Callback<TableColumn<Human, String>, TableCell<Human, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Human, String> param) {
                        final TableCell<Human, String> cell = new TableCell<Human, String>() {

                            final Button btn = new Button("Показать номера");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        try {
                                            createTablePhone(table.getItems().get(this.getIndex()).getId());
                                        } catch (ClassNotFoundException ex) {
                                            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (SQLException ex) {
                                            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        actionCol.setCellFactory(cellFactory);
        table.setItems(data);
        table.getColumns().addAll(idCol, nameCol, surnameCol, addressCol, emailCol, categoryCol, actionCol);

        Scene scene = new Scene(new Group());
        Stage stage = new Stage();
        stage.setTitle("Результат");
        stage.setWidth(755.75);
        stage.setHeight(489);

        Label label = new Label("Результат поиска");
        label.setFont(new Font("Arial", 20));

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

//    private void dataEditGroup() {
//        TextField addName = new TextField();
//        addName.setPromptText("Name");
//        addName.setMaxWidth(nameCol.getPrefWidth());
//        TextField addBossName = new TextField();
//        addBossName.setMaxWidth(bossNameCol.getPrefWidth());
//        addBossName.setPromptText("Boss Name");
//        TextField addPersonnel = new TextField();
//        addPersonnel.setMaxWidth(personnelCol.getPrefWidth());
//        addPersonnel.setPromptText("Personnel");
//
//        final Button addButton = new Button("Add");
//        addButton.setOnAction((ActionEvent e) -> {
//            data.add(new Organization(
//                    addName.getText(),
//                    addBossName.getText(),
//                    Integer.parseInt(addPersonnel.getText())));
//            addName.clear();
//            addBossName.clear();
//            addPersonnel.clear();
//        });
//        hb.getChildren().addAll(addName, addBossName, addPersonnel, addButton);
//        hb.setSpacing(3);
//    }
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
        nameEdit.setText("ТЕЛЕФОННАЯ КНИГА");
        phonebook = new Image("file:ph.png");
        pic = new ImageView();
        pic.setFitHeight(500);
        pic.setPreserveRatio(true);
        pic.setImage(phonebook);
        grid.add(nameEdit, 0, 0);
        grid.add(pic, 0, 1);
        Label lab = new Label();
        lab.setText("Показать контакты");
        lab.setStyle("-fx-font: 34 arial;");
        grid.setAlignment(Pos.CENTER);
        Button HumanShow = new Button();
        HumanShow.setGraphic(lab);
        HumanShow.setOnAction((ActionEvent event) -> {
            try {
                createTableHuman();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        HumanShow.setMinSize(450, 80);
        grid.add(HumanShow, 0, 2);

    }

    private void createSearchBoxByParam() {
        TextField strFind;
        Stage stage = new Stage();
        stage.setTitle("Поиск контакта по параметру");
        Scene scene = new Scene(new GridPane(), 540, 70);
        HBox box = new HBox();
        HBox box1 = new HBox();
        GridPane gr = (GridPane) scene.getRoot();

        box.setPadding(new Insets(5, 5, 5, 5));
        box1.setPadding(new Insets(5, 5, 5, 5));
        final Text text = new Text("Выберите параметр поиска    ");
        text.setFont(Font.font("Verdana", 20));
        final Text textCat = new Text("Выберите категорию поиска  ");
        textCat.setFont(Font.font("Verdana", 20));
        Label nameFind = new Label("Введите параметр    ");
        nameFind.setFont(Font.font("Verdana", 20));
        strFind = new TextField();
        strFind.setText("Илья");
        ComboBox param = new ComboBox();
        param.getItems().addAll(
                "Фамилия",
                "Имя",
                "Email",
                "Город"
        );

        ComboBox paramCat = new ComboBox();
        paramCat.getItems().addAll(
                "Друзья",
                "Родственники",
                "Деловой партнёр",
                "Все"
        );
        param.setValue("Имя");
        paramCat.setValue("Все");
        Button btnOk = new Button("Найти");
        btnOk.setOnAction((ActionEvent e) -> {
            if (!"".equals(strFind.getText().trim())) {
                try {
                    createTableHuman(param.getValue().toString(), strFind.getText(), paramCat.getValue().toString());
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Введена пустая строка");
                alert.setContentText("Строка не должна быть пустой");
                alert.showAndWait();
            }
        });
        btnOk.setDefaultButton(true);//enter work 
        btnOk.setMinWidth(130);

        box1.getChildren().addAll(textCat, paramCat, new Text("  "), btnOk);
        box.getChildren().addAll(text, param, new Text("  "), strFind);
        gr.add(box, 0, 0);
        gr.add(box1, 0, 1);
        stage.setScene(scene);
        stage.show();
    }

    private void createSearchBoxByPhone() {
        TextField phoneEdit;
        Stage stage = new Stage();
        stage.setTitle("Поиск контакта по номеру");
        Scene scene = new Scene(new HBox(20), 410, 34.5);
        HBox box = (HBox) scene.getRoot();
        box.setPadding(new Insets(5, 5, 5, 5));
        final Text text = new Text("Введите номер!");
        text.setFont(Font.font("Verdana", 20));

        phoneEdit = new TextField();
        phoneEdit.setText("79610248569");

        Button btnOk = new Button("Поиск");
        btnOk.setOnAction((ActionEvent e) -> {
            if (phoneEdit.getText().matches("^[1-9]{1}[0-9]*") == true && dbHandler.include(phoneEdit.getText())) {
                try {
                    createTableHuman(phoneEdit.getText());
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
                stage.close();
            } else if (phoneEdit.getText().matches("^[1-9]{1}[0-9]*") == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Некорректно введённый номер");
                alert.setContentText("В номере должны быть только цифры");
                alert.showAndWait();
            } else if (!dbHandler.include(phoneEdit.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Номера нет");
                alert.setContentText("Контакта с введенным номером не существует");
                alert.showAndWait();
            }
        });
        btnOk.setDefaultButton(true);//enter work 
        btnOk.setMinWidth(70);
        box.getChildren().addAll(text, phoneEdit, btnOk);
        stage.setScene(scene);
        stage.show();
    }

    /////////////////////////////////////////////////
    private void setElements(Stage primaryStage) {
        border = new BorderPane();
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(createFileMenu());
        border.setTop(menuBar);
        createPane();
        border.setCenter(grid);
        border.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(border, 500, 750);
        primaryStage.setTitle("Телефонная книга");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Menu createFileMenu() {
        Menu menuFile = new Menu("Поиск");
        MenuItem playPeople = new MenuItem("Поиск по номеру телефона");
        playPeople.setOnAction((ActionEvent t) -> {
            createSearchBoxByPhone();
            grid.setVisible(true);
        });
        MenuItem playComp = new MenuItem("Поиск по параметру в разл. категориях");
        playComp.setOnAction((ActionEvent t) -> {
            createSearchBoxByParam();
            grid.setVisible(true);

        });
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction((ActionEvent t) -> {
            Platform.exit();
        });
        menuFile.getItems().addAll(playPeople, playComp, new SeparatorMenuItem(), exit);
        return menuFile;
    }

    public View(Stage primaryStage) throws ClassNotFoundException, SQLException {
        DataBaseHandler dbHandler = new DataBaseHandler();
        setElements(primaryStage);
    }

}
