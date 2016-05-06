package controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.GridPane;
import application.Main;

public class View2Controller extends GridPane implements Initializable {

    @FXML
    CheckBox checkBox = new CheckBox();

    @FXML
    ScrollPane scrollPane = new ScrollPane();

    @FXML
    TreeTableView<String> treeTableView = new TreeTableView<String>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // do nothing
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        final TreeItem<String> root = new TreeItem<>("Root node");
        root.setExpanded(true);
        TreeTableColumn<String, String> column1 = new TreeTableColumn<>(
                "Column1");
        TreeTableColumn<String, String> column2 = new TreeTableColumn<>(
                "Column2");
        ObservableList<TreeItem<String>> data = FXCollections
                .observableArrayList(new TreeItem<String>("Item 1"),
                        new TreeItem<String>("Item 2"));
        treeTableView.setRoot(root);
        treeTableView.getColumns().add(column1);
        treeTableView.getColumns().add(column2);
        root.getChildren().addAll(data);

    }

    @FXML
    public void checked(ActionEvent e) {
        System.out.print(checkBox.getId() + " ");
        if (checkBox.isSelected()) {
            System.out.println("is selected");
        } else {
            System.out.println("is not selected");
        }
    }

    @FXML
    public void back(ActionEvent e) throws MalformedURLException {
        Main.getInstance().sendPage1Controller();
    }
}
