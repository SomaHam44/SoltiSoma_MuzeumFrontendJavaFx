package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.MuzeumApi;
import hu.petrik.muzeumfrontendjavafx.Szobor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class SzobrokController extends Controller {
    @FXML
    public TableView<Szobor> szoborTable;
    @FXML
    public TableColumn<Szobor, String> colSzemely;
    @FXML
    public TableColumn<Szobor, Integer> colMagassag;
    @FXML
    public TableColumn<Szobor, Integer> colAr;

    public void initialize() {
        colSzemely.setCellValueFactory(new PropertyValueFactory<>("person"));
        colMagassag.setCellValueFactory(new PropertyValueFactory<>("height"));
        colAr.setCellValueFactory(new PropertyValueFactory<>("price"));
        try {
            szoborListaFeltoltes();
        }
        catch (Exception e) {
            hibaKiiro(e);
            System.out.println(e);
        }





    }




   private void szoborListaFeltoltes() {
        try {
            List<Szobor> szobrok = MuzeumApi.getSzobrok();
            szoborTable.getItems().clear();
            for (Szobor szobor : szobrok) {
                szoborTable.getItems().add(szobor);
            }
        } catch (IOException e) {
            hibaKiiro(e);
        }
    }



    @FXML
    public void onHozzadasButtonClick(ActionEvent actionEvent) {
        try {
            Controller hozzaadas = ujAblak("szobrok_hozzaadas_view.fxml", "Szobor hozzáadása", 500, 400);
            hozzaadas.getStage().setOnCloseRequest(event -> szoborListaFeltoltes());
            hozzaadas.getStage().show();
        }
        catch (Exception e) {
            hibaKiiro(e);
        }
    }

    @FXML
    public void onModositasButtonClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onTorlesButtonClick(ActionEvent actionEvent) {
    }
}
