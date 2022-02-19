package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.Festmeny;
import hu.petrik.muzeumfrontendjavafx.MuzeumApi;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.io.IOException;

public class FestmenyekModositasController extends Controller {

    public Spinner<Integer> inputEv;
    public CheckBox inputKiallitva;
    public TextField inputCim;
    private Festmeny modositando;

    public Festmeny getModositando() {
        return modositando;
    }

    public void setModositando(Festmeny modositando) {
        this.modositando = modositando;
        ertekekBeallitasa();
    }

    private void ertekekBeallitasa() {
        inputCim.setText(modositando.getTitle());
        inputEv.getValueFactory().setValue(modositando.getYear());
        inputKiallitva.setSelected(modositando.isOn_display());

    }

    public void onModositasButtonClick(ActionEvent actionEvent) {
        String cim = inputCim.getText().trim();
        int ev = 0;
        boolean kiallitva = inputKiallitva.isSelected();
        if (cim.isEmpty()) {
            alert("Cím megadása kötelező!");
            return;
        }

        if (cim.length() < 3) {
            alert("A cím  3 karakternél hosszabb kell, hogy legyen!");
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


        modositando.setTitle(cim);
        modositando.setYear(ev);
        modositando.setOn_display(kiallitva);

        try {
            Festmeny modositott = MuzeumApi.festmenyModositasa(modositando);
            if (modositott != null) {
                alertWait("Sikeres módosítás");
                this.stage.close();
            } else {
                alert("Sikertelen módosítás");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
