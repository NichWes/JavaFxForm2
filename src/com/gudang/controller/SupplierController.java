package com.gudang.controller;

import com.gudang.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class SupplierController {

    @FXML private TextField txtKode;
    @FXML private TextField txtNama;
    @FXML private TextField txtAlamat;
    @FXML private TextField txtTelepon;

    @FXML private TableView<Supplier> tblSupplier;
    @FXML private TableColumn<Supplier, String> colKode;
    @FXML private TableColumn<Supplier, String> colNama;
    @FXML private TableColumn<Supplier, String> colAlamat;
    @FXML private TableColumn<Supplier, String> colTelepon;

    private ObservableList<Supplier> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colKode.setCellValueFactory(cell -> cell.getValue().kodeProperty());
        colNama.setCellValueFactory(cell -> cell.getValue().namaProperty());
        colAlamat.setCellValueFactory(cell -> cell.getValue().alamatProperty());
        colTelepon.setCellValueFactory(cell -> cell.getValue().teleponProperty());
        tblSupplier.setItems(data);
    }

    @FXML
    public void tambahSupplier() {
        String kode = txtKode.getText();
        String nama = txtNama.getText();
        String alamat = txtAlamat.getText();
        String telepon = txtTelepon.getText();

        if (kode.isEmpty() || nama.isEmpty() || telepon.isEmpty()) {
            showAlert("Error", "Mandatory field harus diisi!");
            return;
        }

        // cek kode unik
        for (Supplier s : data) {
            if (s.getKode().equals(kode)) {
                showAlert("Error", "Kode supplier sudah ada!");
                return;
            }
        }

        data.add(new Supplier(kode, nama, alamat, telepon));
        clearForm();
    }

    @FXML
    public void editSupplier() {
        Supplier selected = tblSupplier.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Error", "Pilih supplier dulu!");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditSupplier.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);

            // kirim data ke form edit
            EditSupplierController controller = loader.getController();
            controller.setSupplier(selected);

            stage.setTitle("Edit Supplier");
            stage.showAndWait();
            tblSupplier.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void hapusSupplier() {
        Supplier selected = tblSupplier.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Error", "Pilih supplier dulu!");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Hapus supplier?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();
        if (confirm.getResult() == ButtonType.YES) {
            data.remove(selected);
        }
    }

    private void clearForm() {
        txtKode.clear();
        txtNama.clear();
        txtAlamat.clear();
        txtTelepon.clear();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
