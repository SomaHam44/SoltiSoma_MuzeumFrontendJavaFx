package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MainController extends Controller {

    public void onFestmenyek(ActionEvent actionEvent) {
        try {
            Controller festmenyListazo = ujAblak("festmenyek_view.fxml","Festmények", 540, 500);
            festmenyListazo.getStage().show();
        } catch (IOException e) {
            hibaKiiro(e);
        }


    }

    public void onSzobrokButtonClick(ActionEvent actionEvent) {
        try {
            Controller szoborListazo = ujAblak("szobrok_view.fxml","Szobrok", 540, 500);
            szoborListazo.getStage().show();
        } catch (IOException e) {
            hibaKiiro(e);
        }


    }
}