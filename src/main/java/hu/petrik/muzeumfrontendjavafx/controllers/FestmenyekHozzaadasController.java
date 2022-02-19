package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.Festmeny;
import hu.petrik.muzeumfrontendjavafx.MuzeumApi;
import hu.petrik.muzeumfrontendjavafx.Szobor;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class FestmenyekHozzaadasController extends Controller {
    public Spinner<Integer> inputEv;
    public CheckBox inputKiallitva;
    public TextField inputCim;

    public void onHozzaadButtonClick(ActionEvent actionEvent) {
        String cim = inputCim.getText().trim();
        boolean kiallitva = inputKiallitva.isSelected();
        int ev = 0;
        if (cim.isEmpty()) {
            alert("A cím megadása kötelező!");
            return;
        }

        if (cim.length() < 3) {
            alert("A cím  neve 3 karakternél hosszabb kell, hogy legyen!");
            return;
        }

        try {
            ev = inputEv.getValue();
        } catch (NullPointerException ex) {
            alert("Az év megadása kötelező");
            return;
        } catch (Exception e) {
            alert("Az év csak 100 és 3000 közötti szám lehet");
            return;
        }

        if (ev < 100 || ev > 3000) {
            alert("Az év csak 100 és 3000 közötti szám lehet");
            return;
        }



        try {
            Festmeny ujFestmeny = new Festmeny(0, cim, ev, kiallitva);
            Festmeny letrehozottFestmeny = MuzeumApi.festmenyHozzaadasa(ujFestmeny);
            if (letrehozottFestmeny != null) {
                alert("A festmény hozzáadása sikeres");
            } else {
                alert("A festmény hozzáadása sikertelen");
            }
        } catch (Exception e) {
            hibaKiiro(e);
        }

    }
}
