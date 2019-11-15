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
        System.out.println(dataPh);

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
        table.getColumns().addAll(typeCol, numberCol);

        Scene scene = new Scene(new Group());
        Stage stage = new Stage();
        stage.setTitle("Table View Sample");
        stage.setWidth(286);
        stage.setHeight(500);

        Label label = new Label("");
        label.setFont(new Font("Arial", 20));

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(5, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

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
        TableColumn<Human, Number> indexColumn = new TableColumn<Human, Number>("#");
        indexColumn.setMinWidth(50);
        indexColumn.setSortable(false);
        indexColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue()) + 1));

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

/////////////////////////////
        table.setItems(data);
        table.getColumns().addAll(idCol, nameCol, surnameCol, addressCol, emailCol, categoryCol, actionCol);

        Scene scene = new Scene(new Group());
        Stage stage = new Stage();
        stage.setTitle("Table View Sample");
        stage.setWidth(820);
        stage.setHeight(550);

        Label label = new Label("Organizations List");
        label.setFont(new Font("Arial", 20));

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

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
        TableColumn<Human, Number> indexColumn = new TableColumn<Human, Number>("#");
        indexColumn.setMinWidth(50);
        indexColumn.setSortable(false);
        indexColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue()) + 1));
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
        actionCol.setMinWidth(175);

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
        stage.setWidth(820);
        stage.setHeight(260);

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
        actionCol.setMinWidth(175);

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
        stage.setTitle("Результат");
        stage.setWidth(820);
        stage.setHeight(520);

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

    private void createWindowFindParams() {
        TextField strFind;
        Stage stage = new Stage();
        stage.setTitle("Поиск контакта по параметру");
        Scene scene = new Scene(new GridPane(), 720, 85);
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
        Button btnOk = new Button("Найти");
        btnOk.setOnAction((ActionEvent e) -> {
            if (strFind.getText() != null) {
                try {
                    createTableHuman(param.getValue().toString(),strFind.getText(),paramCat.getValue().toString());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Data entry error");
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

    private void createWindowFind() {
        TextField nameEdit;
        Stage stage = new Stage();
        stage.setTitle("Поиск контакта по номеру");
        Scene scene = new Scene(new HBox(20), 470, 65);
        HBox box = (HBox) scene.getRoot();
        box.setPadding(new Insets(5, 5, 5, 5));
        final Text text = new Text("Введите номер!");
        text.setFont(Font.font("Verdana", 20));

        nameEdit = new TextField();
        nameEdit.setText("79610248569");

        Button btnOk = new Button("Поиск");
        btnOk.setOnAction((ActionEvent e) -> {
            if (nameEdit.getText().matches("[0-9]+") == true) {
                try {
                    createTableHuman(nameEdit.getText());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Data entry error");
                alert.setHeaderText("Некорректно введённый номер");
                alert.setContentText("В номере должны быть только цифры");
                alert.showAndWait();
            }
        });
        btnOk.setDefaultButton(true);//enter work 
        box.getChildren().addAll(text, nameEdit, btnOk);
        stage.setScene(scene);
        stage.show();
    }

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
        Menu menuFile = new Menu("#");
        MenuItem playPeople = new MenuItem("Поиск по номеру телефона");
        playPeople.setOnAction((ActionEvent t) -> {
            createWindowFind();
            grid.setVisible(true);
        });
        MenuItem playComp = new MenuItem("#2");
        playComp.setOnAction((ActionEvent t) -> {
            createWindowFindParams();
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
