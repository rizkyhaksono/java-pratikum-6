package com.rizky.pratikum6last;

import java.io.*;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Mahasiswa extends Application {

    private final BorderPane root = new BorderPane();

    // Vertical Box
    private final VBox rowBox = new VBox();
    private final VBox columnBox = new VBox();
    private final VBox verticalBox = new VBox();

    // Horizontal Box
    private final HBox inputBox = new HBox();

    // Vertical Box 2
    private final VBox rbox = new VBox();
    private final HBox verticalButton = new HBox();

    // Label
    private final Label listJadwal = new Label("List Jadwal");
    private final Label judulInput = new Label("Input Data");
    private final Label namadosenLabel = new Label("Nama Dosen: ");
    private final Label matkulLabel = new Label("Mata Kuliah: ");
    private final Label gkbLabel = new Label("GKB: ");
    private final Label waktuLabel = new Label("Waktu: ");
    private final Label ruanganLabel = new Label("Ruangan: ");

    // HBox matkul
    private final HBox namadosenBox = new HBox();
    private final HBox matkulBox = new HBox();
    private final HBox gkbBox = new HBox();
    private final HBox waktuBox = new HBox();
    private final HBox ruanganBox = new HBox();


    // Text Field
    private final TextField namadosenField = new TextField();
    private final TextField matkulField = new TextField();
    private final TextField gkbField = new TextField();
    private final TextField waktuField = new TextField();
    private final TextField ruanganField = new TextField();

    // Button
    private final Button defaultButton = new Button("Default");
    private final Button createButton = new Button("Create");
    private final Button updateButton = new Button("Update");
    private final Button deleteButton = new Button("Delete");
    private final Button submitButton = new Button("Submit Data");
    private final Button removeButton = new Button("Remove Data");

    // Button Box
    private final HBox submitBox = new HBox();
    private final HBox removeBox = new HBox();

    boolean functionDelete;

    // Table Column
    private final TableColumn<DataMahasiswa, String> namadosenCol = new TableColumn("Nama Dosen");
    private final TableColumn<DataMahasiswa, String> mapcol = new TableColumn("Mata Kuliah");
    private final TableColumn<DataMahasiswa, String> gkbCol = new TableColumn("GKB");
    private final TableColumn<DataMahasiswa, String> waktuCol = new TableColumn("Waktu");
    private final TableColumn<DataMahasiswa, String> ruanganCol = new TableColumn("Ruangan");

    // Table View
    private final TableView<DataMahasiswa> tabel = new TableView<>();

    // ObservableList
    public static ObservableList<DataMahasiswa> data = FXCollections.observableArrayList();

    // File Text
    private final String filePath = "src\\main\\java\\com\\rizky\\pratikum6last\\Data.txt";
    private final File file = new File(filePath);

    // tipe data untuk tiap inputan field
    String inputNamaDosen, inputMatkul, inputGKB, inputWaktu, inputRuang;
    private BufferedReader read;

    private void writeData() throws IOException {

        Writer writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(file));

            // judul baris
            String judl = namadosenCol.getText() + " | " + mapcol.getText() + " | " + gkbCol.getText() + " | " + waktuCol.getText() + " | " + ruanganCol.getText() + "\n";
            writer.write(judl);

            for (DataMahasiswa save : data) {
                inputNamaDosen = save.getNamaDosen();
                inputNamaDosen = inputNamaDosen.replace(" ", "_");

                inputMatkul = save.getMapel();
                inputMatkul = inputMatkul.replace(" ", "_");

                inputGKB = save.getGKB();
                inputGKB = inputGKB.replace(" ", "_");

                inputWaktu = save.getWaktu();
                inputWaktu = inputWaktu.replace(" ", "_");

                inputRuang = save.getRuangan();
                inputRuang = inputRuang.replace(" ", "_");

                String text = inputNamaDosen + " | " + inputMatkul + " | " + inputGKB + " | " + inputWaktu + " | " + inputRuang + "\n" ;

                writer.write(text);

            }

        } catch (Exception e) {
            System.out.println("Error : " + e);

        } finally {
            assert writer != null;
            writer.flush();
            writer.close();

        }

    }

    private void readData() throws IOException {

        try {

            read = new BufferedReader(new FileReader(file));
            read.readLine();

            Object[] ambil = read.lines().toArray();

            for (int i = 0; i < ambil.length; i++) {
                String baris = ambil[i].toString();
                baris = baris.replace(" |", "");
                String[] hasil = baris.split(" ");

                for (int j = 0; j < hasil.length; j++) {
                    if (hasil[j].equals("0")) {
                        hasil[j] = " ";
                    }

                    hasil[j] = hasil[j].replace("_", " ");

                }

                data.add(new DataMahasiswa(hasil[0], hasil[1], hasil[2], hasil[3], hasil[4]));

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            read.close();
        }
    }

    private void updateData() {

        verticalBox.getChildren().remove(removeBox);
        functionDelete = false;

        tabel.setEditable(true);
        updateButton.setDefaultButton(true);

        namadosenCol.setCellFactory(TextFieldTableCell.forTableColumn());
        namadosenCol.setOnEditCommit((TableColumn.CellEditEvent<DataMahasiswa, String> event) -> {
            DataMahasiswa dataM = event.getRowValue();
            dataM.setNamaDosen(event.getNewValue());

            try {
                writeData();
            } catch (IOException error) {
                System.out.println("Error : " + error);
            }
        });

        mapcol.setCellFactory(TextFieldTableCell.forTableColumn());
        mapcol.setOnEditCommit((TableColumn.CellEditEvent<DataMahasiswa, String> event) -> {
            DataMahasiswa dataM = event.getRowValue();
            dataM.setMapel(event.getNewValue());

            try {
                writeData();
            } catch (IOException error) {
                System.out.println("Error : " + error);
            }

        });

        gkbCol.setCellFactory(TextFieldTableCell.forTableColumn());
        gkbCol.setOnEditCommit((TableColumn.CellEditEvent<DataMahasiswa, String> event) -> {

            DataMahasiswa dataM = event.getRowValue();
            dataM.setGKB(event.getNewValue());

            try {
                writeData();
            } catch (IOException error) {
                System.out.println("Error : " + error);
            }
        });


        waktuCol.setCellFactory(TextFieldTableCell.forTableColumn());
        waktuCol.setOnEditCommit((TableColumn.CellEditEvent<DataMahasiswa, String> event) -> {
            DataMahasiswa dataM = event.getRowValue();
            dataM.setWaktu(event.getNewValue());

            try {
                writeData();
            } catch (IOException error) {
                System.out.println("Error : " + error);
            }
        });

        ruanganCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ruanganCol.setOnEditCommit((TableColumn.CellEditEvent<DataMahasiswa, String> event) -> {
            DataMahasiswa dataM = event.getRowValue();
            dataM.setRuangan(event.getNewValue());

            try {
                writeData();
            } catch (IOException error) {
                System.out.println("Error : " + error);
            }
        });

    }

    private void createData() {

        verticalBox.getChildren().remove(removeBox);
        functionDelete = false;

        submitButton.setOnAction((ActionEvent e) -> {

            boolean addingData = Pattern.matches("\\d+", gkbField.getText());
            boolean dosenBool = namadosenField.getText().isEmpty();
            boolean matkulBool = matkulField.getText().isEmpty();
            boolean gkbBool = gkbField.getText().isEmpty();
            boolean waktuBool = waktuField.getText().isEmpty();
            boolean ruangBool = ruanganField.getText().isEmpty();

            Alert err = new Alert(Alert.AlertType.ERROR);
            Alert info = new Alert(Alert.AlertType.INFORMATION);

            if (!addingData && !gkbBool){
                err.setContentText("Inputan GKB harus angka");
                err.show();
            } else if (gkbBool || dosenBool || matkulBool || waktuBool || ruangBool) {
                err.setContentText("Inputan tidak boleh kosong");
                err.show();
            } else {
                data.add(new DataMahasiswa(
                        namadosenField.getText(),
                        matkulField.getText(),
                        gkbField.getText(),
                        waktuField.getText(),
                        ruanganField.getText()
                    ));

                try {
                    writeData();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                info.setHeaderText("Data sudah benar");
                info.setContentText("Data anda sudah masuk ke dalam Database");
                info.show();

                namadosenField.clear();
                matkulField.clear();
                gkbField.clear();
                waktuField.clear();
                ruanganField.clear();

            }

        });
    }

    private void deleteData() {

        verticalBox.getChildren().add(removeBox);

        removeButton.setOnAction((ActionEvent e) -> {

            try {
                DataMahasiswa pilih = tabel.getSelectionModel().getSelectedItem();
                tabel.getItems().remove(pilih);
                writeData();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
    }

    @SuppressWarnings("unchecked")
    // connect data to DataMahasiswa.java
    public void dataColumn() {
        namadosenCol.setCellValueFactory(new PropertyValueFactory<>("namaDosen"));
        namadosenCol.setPrefWidth(110);

        mapcol.setCellValueFactory(new PropertyValueFactory<>("mape" +
                "l"));
        mapcol.setPrefWidth(100);

        gkbCol.setCellValueFactory(new PropertyValueFactory<>("GKB"));
        gkbCol.setPrefWidth(100);

        waktuCol.setCellValueFactory(new PropertyValueFactory<>("waktu"));
        waktuCol.setPrefWidth(110);

        ruanganCol.setCellValueFactory(new PropertyValueFactory<>("ruangan"));
        ruanganCol.setPrefWidth(110);

        tabel.setItems(data);
    }

    @Override
    @SuppressWarnings({"unchecked", "unchecked"})
    public void start(Stage stage) throws IOException {

            // mengoneksi ke DataMahasiswa.java
            dataColumn();

            // add judul dan tabel
            verticalBox.getChildren().addAll(listJadwal, tabel);

            // table width and height
            verticalBox.setPrefHeight(200);
            verticalBox.setPadding(new Insets(10, 30, 0, 30));
            verticalBox.setAlignment(Pos.CENTER_LEFT);

            // list jadwal ke tengah
            listJadwal.setMaxWidth(Double.MAX_VALUE);
            AnchorPane.setLeftAnchor(listJadwal, 0.0);
            AnchorPane.setRightAnchor(listJadwal, 0.0);
            listJadwal.setAlignment(Pos.CENTER);
            listJadwal.setFont(Font.font ("Times New Roman", FontWeight.BOLD, 20));

            listJadwal.setPadding(new Insets(0, 0, 10, 0));

            // judulInput
            judulInput.setMaxWidth(Double.MAX_VALUE);
            judulInput.setMaxHeight(Double.MAX_VALUE);

            AnchorPane.setLeftAnchor(judulInput, 0.0);
            AnchorPane.setRightAnchor(judulInput, 0.0);
            judulInput.setAlignment(Pos.CENTER);
            judulInput.setFont(Font.font ("Times New Roman", FontWeight.BOLD, 20));

            judulInput.setPadding(new Insets(10, 0, 0, 0));

            // menampilkan baris dan kolom table
            rowBox.getChildren().add(verticalBox);
            columnBox.getChildren().add(rowBox);

            // isi dalam tabel
            tabel.setPlaceholder(new Label("Tidak ada data di dalam database"));
            tabel.getItems().setAll(data);
            tabel.getSelectionModel().selectFirst();
            tabel.setItems(data);

            // menampilkan mapel di baris dan kolom
            tabel.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            tabel.getColumns().addAll(namadosenCol, mapcol, gkbCol, waktuCol, ruanganCol);

            // section bawah
            inputBox.getChildren().add(rbox);

            inputBox.setMaxWidth(Double.MAX_VALUE);
            AnchorPane.setLeftAnchor(inputBox, 0.0);
            AnchorPane.setRightAnchor(inputBox, 0.0);
            inputBox.setAlignment(Pos.CENTER);

            // tampilan ukuran input field
            namadosenField.setPrefWidth(540);
            namadosenBox.getChildren().add(namadosenField);
            namadosenBox.setPadding(new Insets(5, 0, 5, 0));

            matkulField.setPrefWidth(540);
            matkulBox.getChildren().add(matkulField);
            matkulBox.setPadding(new Insets(5, 0, 5, 0));

            gkbField.setPrefWidth(540);
            gkbBox.getChildren().add(gkbField);
            gkbBox.setPadding(new Insets(5, 0, 5, 0));

            waktuField.setPrefWidth(540);
            waktuBox.getChildren().add(waktuField);
            waktuBox.setPadding(new Insets(5, 0, 5, 0));

            ruanganField.setPrefWidth(540);
            ruanganBox.getChildren().add(ruanganField);
            ruanganBox.setPadding(new Insets(5, 0, 5, 0));

            // menampilkan judul, input section matkul
            rbox.getChildren().addAll(judulInput, namadosenLabel, namadosenBox, matkulLabel, matkulBox, waktuLabel, waktuBox, gkbLabel, gkbBox, ruanganLabel, ruanganBox);

            // font label
            namadosenLabel.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, 15));
            matkulLabel.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, 15));
            waktuLabel.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, 15));
            gkbLabel.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, 15));
            ruanganLabel.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, 15));

            // menampilkan button create, update, delete dan submit
            verticalButton.getChildren().addAll(createButton, updateButton, deleteButton, submitBox);

            createButton.setStyle("-fx-background-color: #96ceb4;");
            createButton.setTextFill(Color.BLACK);
            createButton.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, 15));

            updateButton.setStyle("-fx-background-color: #ffeead");
            updateButton.setTextFill(Color.BLACK);
            updateButton.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, 15));

            deleteButton.setStyle("-fx-background-color: #ff6f69");
            deleteButton.setTextFill(Color.BLACK);
            deleteButton.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, 15));

            submitButton.setStyle("-fx-background-color: #ffcc5c");
            submitButton.setTextFill(Color.BLACK);
            submitButton.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, 15));

            removeButton.setStyle("-fx-background-color: #ff6f69");
            removeButton.setTextFill(Color.BLACK);
            removeButton.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, 15));

            verticalButton.setSpacing(20);

            verticalButton.setMaxWidth(Double.MAX_VALUE);
            verticalButton.setMaxHeight(Double.MAX_VALUE);
            AnchorPane.setLeftAnchor(verticalButton, 0.0);
            AnchorPane.setRightAnchor(verticalButton, 0.0);
            verticalButton.setAlignment(Pos.TOP_CENTER);

            // button submit dan remove
            submitBox.getChildren().add(submitButton);
            removeBox.getChildren().add(removeButton);

            removeBox.setPadding(new Insets(10, 0, 0, 0));

            // default mode
            createButton.setDefaultButton(false);
            updateButton.setDefaultButton(false);
            deleteButton.setDefaultButton(false);
            tabel.setEditable(false);

            if (!defaultButton.isDefaultButton()) {
                readData();
            }

            // create database
            createButton.setOnAction((ActionEvent e) -> {
                createButton.setDefaultButton(true);
                updateButton.setDefaultButton(false);
                deleteButton.setDefaultButton(false);
                tabel.setEditable(false);

                createData();
            });

            // update database
            updateButton.setOnAction((ActionEvent e) -> {
                updateButton.setDefaultButton(true);

                createButton.setDefaultButton(false);
                deleteButton.setDefaultButton(false);
                tabel.setEditable(true);

                updateData();
            });

            // delete database
            deleteButton.setOnAction((ActionEvent e) -> {
                deleteButton.setDefaultButton(true);

                createButton.setDefaultButton(false);
                updateButton.setDefaultButton(false);
                submitButton.setDefaultButton(false);
                tabel.setEditable(false);

                deleteData();
            });

            // tampilan per section
            root.setTop(rowBox);
            rowBox.setStyle("-fx-background-color: #dfe3ee");

            root.setCenter(inputBox);
            inputBox.setStyle("-fx-background-color: #dfe3ee");

            root.setBottom(verticalButton);
            verticalButton.setStyle("-fx-background-color: #dfe3ee");
            verticalButton.setPadding(new Insets(0,0,50,0));

            // show GUI
            Scene scene = new Scene(root, 600, 600);
            stage.setScene(scene);

            stage.setTitle("Aplikasi Jadwal Kuliah Keren Banget");
            stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
