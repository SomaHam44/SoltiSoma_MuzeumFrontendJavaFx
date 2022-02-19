package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.Szobor;
import javafx.event.ActionEvent;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class SzobrokModositasController extends Controller {
    public TextField inputSzemely;
    public Spinner inputAr;
    public Spinner inputMagassag;
    private Szobor modositando;

    public Szobor getModositando() {
        return modositando;
    }

    public void setModositando(Szobor modositando) {
        this.modositando = modositando;
    }

    public void onModositasButtonClick(ActionEvent actionEvent) {
    }
}
