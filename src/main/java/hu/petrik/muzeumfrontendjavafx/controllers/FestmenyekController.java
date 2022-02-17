package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.Festmeny;
import hu.petrik.muzeumfrontendjavafx.MuzeumApi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class FestmenyekController extends Controller {
    @FXML
    public TableView<Festmeny> festmenyTable;
    @FXML
    public TableColumn<Festmeny, String> colCim;
    @FXML
    public TableColumn<Festmeny, Integer> colEv;
    @FXML
    public TableColumn<Festmeny, String> colKiallitva;

    public void initialize() {

        colCim.setCellValueFactory(new PropertyValueFactory<>("cim"));
        colEv.setCellValueFactory(new PropertyValueFactory<>("ev"));
        colKiallitva.setCellValueFactory(new PropertyValueFactory<>("kiallitva"));
        try {
        festmenyListaFeltoltes();
    }
        catch (Exception e) {
        hibaKiiro(e);
        System.out.println(e.getMessage());
    }



}


    private void festmenyListaFeltoltes() {
        try {
            List<Festmeny> festmenyek = MuzeumApi.getFestmenyek();
            festmenyTable.getItems().clear();
            for (Festmeny festmeny : festmenyek) {
                System.out.println(festmeny.getEv());
                festmenyTable.getItems().add(festmeny);
            }
        } catch (IOException e) {
            hibaKiiro(e);
            System.out.println(e);
       }

   }

    @FXML
    public void onHozzadasButtonClick(ActionEvent actionEvent) {
    }
    @FXML
    public void onModositasButtonClick(ActionEvent actionEvent) {
    }
    @FXML
    public void onTorlesButtonClick(ActionEvent actionEvent) {

    }
}
