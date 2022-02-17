package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.Festmeny;
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
            Controller hozzaadas = ujAblak("szobrok-hozzaadas_view.fxml", "Szobor hozzáadása", 500, 400);
            hozzaadas.getStage().setOnCloseRequest(event -> szoborListaFeltoltes());
            hozzaadas.getStage().show();
        }
        catch (Exception e) {
            hibaKiiro(e);
        }
    }

    @FXML
    public void onModositasButtonClick(ActionEvent actionEvent) {
        int kivalasztottIndex = szoborTable.getSelectionModel().getSelectedIndex();
        if (kivalasztottIndex == -1) {
            alert("A módosításhoz előbb ki kell választani egy elemet a táblázatból!");
        }
        else {
            Szobor modositando = szoborTable.getSelectionModel().getSelectedItem();
            try {
                SzobrokModositasController modositas = (SzobrokModositasController) ujAblak("szobrok-modositas_view.fxml","Szobor módosítása", 500, 400);
                modositas.setModositando(modositando);
                modositas.getStage().setOnHiding(event -> szoborTable.refresh());
                modositas.getStage().show();
            } catch (IOException e) {
                hibaKiiro(e);
            }
        }

    }

    @FXML
    public void onTorlesButtonClick(ActionEvent actionEvent) {
        int kivalasztottIndex = szoborTable.getSelectionModel().getSelectedIndex();
        if (kivalasztottIndex == -1) {
            alert("A törléshez előbb ki kell választani egy elemet a táblázatból!");
        }
        Szobor torolhetoSzobor = szoborTable.getSelectionModel().getSelectedItem();
        if (!megerositoAblak("Biztos, hogy törölni szeretné az alábbi szobrot: " + torolhetoSzobor.getPerson())) {
            return;
        }
        try {
            boolean sikeres = MuzeumApi.szoborTorlese(torolhetoSzobor.getId());
            alert(sikeres? "Sikeres törlés " : "Sikertelen törlés");
            szoborListaFeltoltes();
        } catch (IOException e) {
            hibaKiiro(e);
        }
    }
}
