package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.MuzeumApi;
import hu.petrik.muzeumfrontendjavafx.Szobor;
import javafx.event.ActionEvent;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class SzobrokHozzaadasController extends Controller {

    public TextField inputSzemely;
    public Spinner<Integer> inputMagassag;
    public Spinner<Integer> inputAr;

    public void onHozzaadButtonClick(ActionEvent actionEvent) {
        String szemely = inputSzemely.getText().trim();
        int magassag = 0;
        int ar = 0;
        if (szemely.isEmpty()) {
            alert("A személy megadása kötelező!");
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
            alert("A hossz csak 1 és 999 közötti szám lehet");
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



        try {
            Szobor ujSzobor = new Szobor(0, szemely, magassag, ar);
            Szobor letrehozottSzobor = MuzeumApi.szoborHozzaadasa(ujSzobor);
            if (letrehozottSzobor != null) {
                alert("A szobor hozzáadása sikeres");
            } else {
                alert("A szobor hozzáadása sikertelen");
            }
        } catch (Exception e) {
            hibaKiiro(e);
        }
    }
}
