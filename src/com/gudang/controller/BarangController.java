package com.gudang.controller;

import com.gudang.model.Barang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BarangController {

    @FXML
    private Label lblStatus;

    @FXML
    private TableColumn<Barang, Integer> colJumlah;

    @FXML
    private TableColumn<Barang, String> colNama;

    @FXML
    private TableView<Barang> tblBarang;

    @FXML
    private TextField txtJumlah;

    @FXML
    private TextField txtNama;

    private ObservableList<Barang> data = FXCollections.observableArrayList();

    // flag untuk tahu kita sedang edit atau tidak
    private boolean editMode = false;

    @FXML
    public void initialize() {
        colNama.setCellValueFactory(cell -> cell.getValue().namaProperty());
        colJumlah.setCellValueFactory(cell -> cell.getValue().jumlahProperty().asObject());
        tblBarang.setItems(data);
    }

    @FXML
    public void editBarang() {
        Barang selected = tblBarang.getSelectionModel().getSelectedItem();

        if (selected == null) {
             lblStatus.setText("Pilih yang mau diedit!");
        }

        if (!editMode) {
            // Klik Edit → isi textfield dengan data row
            txtNama.setText(selected.getNama());
            txtJumlah.setText(String.valueOf(selected.getJumlah()));
            editMode = true;
        } else {
            // Klik Edit → simpan perubahan ke tabel
            selected.setNama(txtNama.getText());
            try {
                selected.setJumlah(Integer.parseInt(txtJumlah.getText()));
            } catch (NumberFormatException e) {
                System.out.println("Jumlah harus berupa angka!");
                return;
            }
            tblBarang.refresh();

            // reset mode edit
            txtNama.clear();
            txtJumlah.clear();
            editMode = false;
        }
    }

    @FXML
    public void hapusBarang() {
        Barang selected = tblBarang.getSelectionModel().getSelectedItem();
        if (selected != null) {
            data.remove(selected);
            txtNama.clear();
            txtJumlah.clear();
            editMode = false;
        }
    }

    @FXML
    public void tambahBarang() {
        if (!txtNama.getText().isEmpty() && !txtJumlah.getText().isEmpty()) {
            try {
                data.add(new Barang(txtNama.getText(), Integer.parseInt(txtJumlah.getText())));
                txtNama.clear();
                txtJumlah.clear();
            } catch (NumberFormatException e) {
                System.out.println("Jumlah harus berupa angka!");
            }
        }
    }
}
