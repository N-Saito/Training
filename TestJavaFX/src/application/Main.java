package application;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import util.UIPropertiesUtil;
import util.UIPropertiesUtil.UIPropertieKey;
import controller.View1Controller;
import controller.View2Controller;

public class Main extends Application {

    private static Main instance;

    ResourceBundle bundle;

    View1Controller controller1 = new View1Controller();

    View2Controller controller2 = new View2Controller();

    private Stage primaryStage;

    Boolean cssFlag = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            instance = this;
            this.primaryStage = primaryStage;
            this.primaryStage.sizeToScene();
            this.primaryStage.getIcons().add(
                    new Image(getClass().getResourceAsStream("icon.png")));
            this.primaryStage.setTitle(UIPropertiesUtil
                    .get(UIPropertieKey.WIZARD_TITLE));
            File propertiesFile = new File("src\\ui.properties");
            URL propertiesURL = propertiesFile.toURI().toURL();
            InputStream inputStream = propertiesURL.openStream();
            bundle = new PropertyResourceBundle(inputStream);
            initView();

            // Page1に遷移する準備
            sendPage1Controller();

            // CSSファイルを適用
            if (cssFlag) {
                File cssFile = new File("src\\application\\application.css");
                URL cssURL = cssFile.toURI().toURL();
                primaryStage.getScene().getStylesheets()
                        .add(cssURL.toExternalForm());
            }
            this.primaryStage.show();
            this.primaryStage.setOnCloseRequest(req -> Platform.exit());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * fxmlファイルの読み込み
     * @param controller
     * @param path
     */
    private void initView() {
        initView(controller1, "src\\view\\View1.fxml");
        initView(controller2, "src\\view\\View2.fxml");
    }

    private void initView(Parent controller, String viewPath) {
        File viewFile = new File(viewPath);
        try {
            URL fxmlURL = viewFile.toURI().toURL();
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL, bundle);
            fxmlLoader.setRoot(controller);
            fxmlLoader.setController(controller);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPage1Controller() throws MalformedURLException {
        this.replaceSceneContent(controller1);
    }

    public void sendPage2Controller() throws MalformedURLException {
        this.replaceSceneContent(controller2);
    }

    /**
     * シーンの変更
     * @param controller
     * @throws MalformedURLException
     */
    public void replaceSceneContent(Parent controller)
                                                      throws MalformedURLException {
        Scene scene = primaryStage.getScene();
        if (scene == null) {
            scene = new Scene(controller);
            primaryStage.setScene(scene);
        } else {
            primaryStage.getScene().setRoot(controller);
        }
    }

    public static Main getInstance() {
        return instance;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
