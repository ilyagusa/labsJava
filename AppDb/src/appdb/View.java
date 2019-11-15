/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdb;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
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
    private Image picBullandCow;
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
        stage.setWidth(820);
        stage.setHeight(550);

        Label label = new Label("");
        label.setFont(new Font("Arial", 20));

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
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
        TableColumn ButtonPhone = new TableColumn("find");
        TableView<Human> table = new TableView<Human>();

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
                                            createTablePhone(1);
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
        picBullandCow = new Image("file:ph.png");
        pic = new ImageView();
        pic.setFitHeight(500);
        pic.setPreserveRatio(true);
        pic.setImage(picBullandCow);
        grid.add(nameEdit, 0, 0);
        grid.add(pic, 0, 1);
        grid.setAlignment(Pos.CENTER);
        Button btnPravila = new Button();
        btnPravila.setText("Показать контакты");
        btnPravila.setOnAction((ActionEvent event) -> {
            try {
                createTableHuman();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        grid.add(btnPravila, 0, 2);

    }

    private void setElements(Stage primaryStage) {
        border = new BorderPane();
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(createFileMenu());
        border.setTop(menuBar);
        createPane();
        border.setCenter(grid);
        border.setBackground(new Background(new BackgroundFill(Color.TAN, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(border, 500, 750);
        primaryStage.setTitle("Телефонная книга");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Menu createFileMenu() {
        Menu menuFile = new Menu("#");
        MenuItem playPeople = new MenuItem("#1");
        playPeople.setOnAction((ActionEvent t) -> {
            grid.setVisible(true);
        });
        MenuItem playComp = new MenuItem("#2");
        playComp.setOnAction((ActionEvent t) -> {
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
