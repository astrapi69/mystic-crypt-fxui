package de.alpharogroup.mysticcryptfxui;

import de.alpharogroup.lang.ClassExtensions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URL;

import java.util.Locale;

import org.controlsfx.control.Notifications;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

@SpringBootApplication
public class MysticCryptFxuiApplication extends Application {

    private ConfigurableApplicationContext context;
    private Parent rootNode;

    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(MysticCryptFxuiApplication.class);
        context = builder.run(getParameters().getRaw().toArray(new String[0]));
        URL url = ClassExtensions.getResource(MysticCryptFxuiApplication.class, "main.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        loader.setControllerFactory(context::getBean);
        rootNode = loader.load();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        double width = visualBounds.getWidth();
        double height = visualBounds.getHeight();

        primaryStage.setScene(new Scene(rootNode, width, height));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Platform.exit();
        context.close();
    }

}
