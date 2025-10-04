package com.gudang.controller;

import com.gudang.model.Supplier;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditSupplierController {

    @FXML
    private TextField txtKode;

    @FXML
    private TextField txtNama;

    @FXML
    private TextField txtAlamat;

    @FXML
    private TextField txtTelepon;

    private Supplier supplier;

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;

        // isi field dengan data lama
        txtKode.setText(supplier.getKode());
        txtNama.setText(supplier.getNama());
        txtAlamat.setText(supplier.getAlamat());
        txtTelepon.setText(supplier.getTelepon());
    }

    @FXML
    public void updateSupplier() {
        if (supplier != null) {
            supplier.setNama(txtNama.getText());
            supplier.setAlamat(txtAlamat.getText());
            supplier.setTelepon(txtTelepon.getText());
        }
        closeWindow();
    }

    @FXML
    public void cancelEdit() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) txtKode.getScene().getWindow();
        stage.close();
    }
}
