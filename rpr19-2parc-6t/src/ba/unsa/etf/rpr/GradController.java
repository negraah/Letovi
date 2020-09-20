package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.TreeSet;

public class GradController {
    public TextField fieldNaziv;
    public TextField fieldBrojStanovnika;
    public ChoiceBox<Drzava> choiceDrzava;
    public ChoiceBox<Grad> choiceGrad;
    public ObservableList<Drzava> listDrzave;
    public ObservableList<Grad> listLetovi;
    public ObservableList<Grad> listGradovi;
    public ListView<Grad> lvLetovi;
    private GeografijaDAO dao;

    private Grad grad;

    public GradController(Grad grad, ArrayList<Drzava> drzave) {
        this.grad = grad;
        listDrzave = FXCollections.observableArrayList(drzave);
        if(grad!=null && grad.getLetovi()!=null) {
            listLetovi = FXCollections.observableArrayList(grad.getLetovi());
        }else
            listLetovi = FXCollections.observableArrayList(new ArrayList<>());
        listGradovi = FXCollections.observableArrayList(GeografijaDAO.getInstance().gradovi());

    }
    public GradController(Grad grad, ArrayList<Drzava> drzave, ArrayList<Grad> gradovi) {
        this.grad = grad;
        listDrzave = FXCollections.observableArrayList(drzave);
        //MOZDA JE GRESKAKAKAKAKAKAKAKAKAKA
        if(grad!=null && grad.getLetovi()!=null) {
            listLetovi = FXCollections.observableArrayList(grad.getLetovi());
        }else
            listLetovi = FXCollections.observableArrayList(new ArrayList<>());
        listGradovi = FXCollections.observableArrayList(gradovi);
    }

    @FXML
    public void initialize() {
        choiceDrzava.setItems(listDrzave);
        choiceGrad.setItems(listGradovi);
        lvLetovi.setItems(listLetovi);

        if (grad != null) {
            fieldNaziv.setText(grad.getNaziv());
            fieldBrojStanovnika.setText(Integer.toString(grad.getBrojStanovnika()));
            // choiceDrzava.getSelectionModel().select(grad.getDrzava());
            // ovo ne radi jer grad.getDrzava() nije identički jednak objekat kao član listDrzave
            for (Drzava drzava : listDrzave)
                if (drzava.getId() == grad.getDrzava().getId())
                    choiceDrzava.getSelectionModel().select(drzava);
        } else {
            choiceDrzava.getSelectionModel().selectFirst();
        }
    }

    public Grad getGrad() {
        return grad;
    }

    public void clickCancel(ActionEvent actionEvent) {
        grad = null;
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }

    public void clickDodajLet(ActionEvent actionEvent){
        Grad grad = choiceGrad.getSelectionModel().getSelectedItem();

        if(listLetovi.contains(grad)) {
            return;
        }
        //ako je grad jednak samom sebi
        //PAZI OVO NA ISPITUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU
        //this. grad moze biti null;
        //kad je null this.grad to znaci da se grad treba dodati

        if(grad==null || grad.equals(this.grad)){
            return;
        }

        listLetovi.add(grad);
        lvLetovi.setItems(listLetovi);

    }

    public void clickOk(ActionEvent actionEvent) {
        boolean sveOk = true;

        if (fieldNaziv.getText().trim().isEmpty()) {
            fieldNaziv.getStyleClass().removeAll("poljeIspravno");
            fieldNaziv.getStyleClass().add("poljeNijeIspravno");
            sveOk = false;
        } else {
            fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
            fieldNaziv.getStyleClass().add("poljeIspravno");
        }


        int brojStanovnika = 0;
        try {
            brojStanovnika = Integer.parseInt(fieldBrojStanovnika.getText());
        } catch (NumberFormatException e) {
            // ...
        }
        if (brojStanovnika <= 0) {
            fieldBrojStanovnika.getStyleClass().removeAll("poljeIspravno");
            fieldBrojStanovnika.getStyleClass().add("poljeNijeIspravno");
            sveOk = false;
        } else {
            fieldBrojStanovnika.getStyleClass().removeAll("poljeNijeIspravno");
            fieldBrojStanovnika.getStyleClass().add("poljeIspravno");
        }

        if (!sveOk) return;

        if (grad == null) grad = new Grad();
        grad.setNaziv(fieldNaziv.getText());
        grad.setBrojStanovnika(Integer.parseInt(fieldBrojStanovnika.getText()));
        grad.setDrzava(choiceDrzava.getValue());
        grad.setLetovi(new TreeSet<>(listLetovi));
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }
}
