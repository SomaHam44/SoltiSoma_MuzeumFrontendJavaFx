package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.Festmeny;

public class FestmenyekModositasController extends Controller {

    private Festmeny modositando;

    public Festmeny getModositando() {
        return modositando;
    }

    public void setModositando(Festmeny modositando) {
        this.modositando = modositando;
    }
}
