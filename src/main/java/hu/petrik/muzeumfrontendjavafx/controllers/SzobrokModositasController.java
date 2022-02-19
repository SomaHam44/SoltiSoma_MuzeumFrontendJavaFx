package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.MuzeumApi;
import hu.petrik.muzeumfrontendjavafx.Szobor;
import javafx.event.ActionEvent;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SzobrokModositasController extends Controller {
    public TextField inputSzemely;
    public Spinner<Integer> inputAr;
    public Spinner<Integer> inputMagassag;
    private Szobor modositando;

    public Szobor getModositando() {
        return modositando;
    }

    public void setModositando(Szobor modositando) {
        this.modositando = modositando;
        ertekekBeallitasa();
    }

    private void ertekekBeallitasa() {
        inputSzemely.setText(modositando.getPerson());
        inputMagassag.getValueFactory().setValue(modositando.getHeight());
        inputAr.getValueFactory().setValue(modositando.getPrice());

    }

    public void onModositasButtonClick(ActionEvent actionEvent) {
        String szemely = inputSzemely.getText().trim();
        int magassag = 0;
        int ar = 0;
        if (szemely.isEmpty()) {
            alert("Személy megadása kötelező!");
            return;
        }

        if (szemely.length() < 3) {
            alert("A személy  neve 3 karakternél hosszabb kell, hogy legyen!");
            return;
        }


        try {
            magassag = inputMagassag.getValue();
        } catch (NullPointerException ex) {
            alert("A magasság megadása kötelező");
            return;
        } catch (Exception e) {
            alert("A magasság csak 1 és 999 közötti szám lehet");
            return;
        }

        if (magassag < 1 || magassag > 999) {
            alert("A magasság csak 1 és 999 közötti szám lehet");
            return;
        }

        try {
            ar = inputAr.getValue();
        } catch (NullPointerException ex) {
            alert("Az ár megadása kötelező");
            return;
        } catch (Exception e) {
            alert("Az á2 csak 1 és 999999 közötti szám lehet");
            return;
        }

        if (ar < 1 || ar > 999999) {
            alert("Az ár csak 1 és 999 közötti szám lehet");
            return;
        }

        modositando.setPerson(szemely);
        modositando.setHeight(magassag);
        modositando.setPrice(ar);

        try {
            Szobor modositott = MuzeumApi.szoborModositasa(modositando);
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
