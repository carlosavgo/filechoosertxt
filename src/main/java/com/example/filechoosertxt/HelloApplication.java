package com.example.filechoosertxt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class HelloApplication extends Application {
    private final TextArea textArea = new TextArea();

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        textArea.setWrapText(true);
        root.setCenter(textArea);

        //----Botones----
        Button botonAbrir = new Button("Abrir archivo .txt");
        Button botonGuardar = new Button("Guardar como...");
        Button botonBorrar = new Button("Borrar texto");

        botonAbrir.setOnAction(e -> botonAbrir(stage));
        botonGuardar.setOnAction(e -> botonGuardar(stage));
        botonBorrar.setOnAction(e -> textArea.clear());

        BorderPane zonaBotones = new BorderPane();
        zonaBotones.setLeft(botonAbrir);
        zonaBotones.setRight(botonGuardar);
        zonaBotones.setCenter(botonBorrar);

        root.setBottom(zonaBotones);

        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("FileChooser - Editor de texto");
        stage.show();
    }

    private void botonGuardar(Stage stage) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Guardar archivo de texto");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo de texto", "*.txt"));
        File file = chooser.showSaveDialog(stage);
        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                textArea.setText("Error al guardar el archivo: " + e.getMessage());
            }
        }
    }

    private void botonAbrir(Stage stage) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Abrir archivo de texto");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo", "*.txt"));
        File file = chooser.showOpenDialog(stage);
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                textArea.setText(content.toString());
            } catch (IOException ex) {
                textArea.setText("Error al abrir archivo: " + ex.getMessage());
            }
        }
    }

}
