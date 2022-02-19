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

        colCim.setCellValueFactory(new PropertyValueFactory<>("title"));
        colEv.setCellValueFactory(new PropertyValueFactory<>("year"));
        colKiallitva.setCellValueFactory(new PropertyValueFactory<>("on_display"));
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
                festmenyTable.getItems().add(festmeny);
            }
        } catch (IOException e) {
            hibaKiiro(e);
            System.out.println(e);
       }

   }

    @FXML
    public void onHozzadasButtonClick(ActionEvent actionEvent) {
        try {
            Controller hozzaadas = ujAblak("festmenyek-hozzaadas_view.fxml", "Festmény hozzáadása", 500, 400);
            hozzaadas.getStage().setOnCloseRequest(event -> festmenyListaFeltoltes());
            hozzaadas.getStage().show();
        }
        catch (Exception e) {
            hibaKiiro(e);
        }
    }
    @FXML
    public void onModositasButtonClick(ActionEvent actionEvent) {
        int kivalasztottIndex = festmenyTable.getSelectionModel().getSelectedIndex();
        if (kivalasztottIndex == -1) {
            alert("A módosításhoz előbb ki kell választani egy elemet a táblázatból!");
        }
        else {
            Festmeny modositando = festmenyTable.getSelectionModel().getSelectedItem();
            try {
                FestmenyekModositasController modositas = (FestmenyekModositasController) ujAblak("festmenyek-modositas_view.fxml","Festmény módosítása", 500, 400);
                modositas.setModositando(modositando);
                modositas.getStage().setOnHiding(event -> festmenyTable.refresh());
                modositas.getStage().show();
            } catch (IOException e) {
                hibaKiiro(e);
            }
        }

    }
    @FXML
    public void onTorlesButtonClick(ActionEvent actionEvent) {
        int kivalasztottIndex = festmenyTable.getSelectionModel().getSelectedIndex();
        if (kivalasztottIndex == -1) {
            alert("A törléshez előbb ki kell választani egy elemet a táblázatból!");
        }
        Festmeny torolhetoFestmeny = festmenyTable.getSelectionModel().getSelectedItem();
        if (!megerositoAblak("Biztos, hogy törölni szeretné az alábbi festményt: " + torolhetoFestmeny.getTitle())) {
            return;
        }
        try {
            boolean sikeres = MuzeumApi.festmenyTorlese(torolhetoFestmeny.getId());
            alert(sikeres? "Sikeres törlés " : "Sikertelen törlés");
            festmenyListaFeltoltes();
        } catch (IOException e) {
            hibaKiiro(e);
            e.printStackTrace();
            System.out.println(e);
        }

    }
}
