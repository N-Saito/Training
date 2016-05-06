package controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import util.UIUtil;
import application.Main;

public class View1Controller extends GridPane implements Initializable {

    /**
     * Tab1
     */

    @FXML
    Button outFolderButton = new Button();

    @FXML
    Button logButton = new Button();

    @FXML
    Button genponXmlButton = new Button();

    @FXML
    Button initDBButton = new Button();

    @FXML
    Button inputXmlButton = new Button();

    @FXML
    Button mainViSCButton = new Button();

    @FXML
    Button diffReportXmlButton = new Button();

    @FXML
    TextField outFolderText = new TextField();

    @FXML
    TextField genponXmlText = new TextField();

    @FXML
    TextField initDBText = new TextField();

    @FXML
    TextField inputXmlText = new TextField();

    @FXML
    TextField mainViSCText = new TextField();

    @FXML
    TextField diffReportXmlText = new TextField();

    /**
     * Tab2
     */

    @FXML
    Button srAddButton = new Button();

    @FXML
    Button srDelButton = new Button();

    @FXML
    ListView<String> srListView = new ListView<String>();

    @FXML
    Button stubAddButton = new Button();

    @FXML
    Button stubDelButton = new Button();

    @FXML
    ListView<String> stubListView = new ListView<String>();

    @FXML
    Button constAddButton = new Button();

    @FXML
    Button constDelButton = new Button();

    @FXML
    ListView<String> constListView = new ListView<String>();

    @FXML
    Button validationAddButton = new Button();

    @FXML
    Button validationDelButton = new Button();

    @FXML
    ListView<String> validationListView = new ListView<String>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // デフォルトの出力先を設定
        outFolderText.setText(getDefaultOutputPath());

        // [参照]ボタンを押下後、ファイル選択ダイアログを開く
        FileChooser fileChooser = new FileChooser();
        fileChooser
                .setInitialDirectory(new File(System.getProperty("user.dir")));
        FileChooser.ExtensionFilter extFilter_xml = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        // FileChooser.ExtensionFilter extFilter_xml = new FileChooser.ExtensionFilter(
        // "XML files (*.xml)", "*.xml");
        // fileChooser.getExtensionFilters().add(extFilter_xml);
        fileChooser.setSelectedExtensionFilter(extFilter_xml);
        settingRefButton(fileChooser, extFilter_xml, genponXmlButton,
                genponXmlText);
        settingRefButton(fileChooser, extFilter_xml, initDBButton, initDBText);
        // genponXmlButton.setOnAction(new EventHandler<ActionEvent>() {
        // @Override
        // public void handle(ActionEvent event) {
        // File importFile = fileChooser.showOpenDialog(Main.getInstance()
        // .getPrimaryStage());
        // if (importFile != null) {
        // genponXmlText.setText(importFile.getPath().toString());
        // }
        // }
        // });

        // [追加]ボタンの設定
        srAddButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<File> importFiles = fileChooser
                        .showOpenMultipleDialog(Main.getInstance()
                                .getPrimaryStage());
                ObservableList<String> listRecords = srListView.getItems();
                if (importFiles != null) {
                    for (File file : importFiles) {
                        if (!listRecords.contains(file.getAbsolutePath())) {
                            listRecords.add(file.getAbsolutePath());
                        }
                    }
                    srListView.setItems(listRecords);
                }
            }
        });

        // [削除]ボタンの設定
        srDelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<String> listRecords = srListView.getItems();
                listRecords.removeAll(srListView.getSelectionModel()
                        .getSelectedItems());
                srListView.setItems(listRecords);
            }
        });

    }

    private void settingRefButton(FileChooser fileChooser,
            FileChooser.ExtensionFilter extFilter, Button button, TextField text) {
        fileChooser.getExtensionFilters().add(extFilter);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File importFile = fileChooser.showOpenDialog(Main.getInstance()
                        .getPrimaryStage());
                if (importFile != null) {
                    text.setText(importFile.getPath().toString());
                }
            }
        });
    }

    /**
     * デフォルトの出力先を返却する
     * @return
     */
    private String getDefaultOutputPath() {
        File curr = new File(UIUtil.getCurrentDir());
        File out = new File(curr, "out");
        if (!out.exists()) {
            out.mkdirs();
        }
        return out.getAbsolutePath() + File.separator;
    }

    @FXML
    public void next(ActionEvent e) throws MalformedURLException {
        Main.getInstance().sendPage2Controller();
    }

    @FXML
    public void dragOverTextField(DragEvent event) {
        event.acceptTransferModes(TransferMode.COPY);
        event.consume();
    }

    @FXML
    public void dragDroppedTextField(DragEvent event) {
        Dragboard dragBoard = event.getDragboard();
        if (dragBoard.hasFiles()) {
            File targetFile = dragBoard.getFiles().get(0);
            if (targetFile.isFile() && targetFile.getName().endsWith(".xml")) {
                genponXmlText.setText(targetFile.getAbsolutePath());
            }
        }
        event.consume();
    }
}
