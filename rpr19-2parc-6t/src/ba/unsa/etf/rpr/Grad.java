package ba.unsa.etf.rpr;

import java.util.Objects;
import java.util.TreeSet;

public class Grad implements Comparable {
    private int id;
    private String naziv;
    private int brojStanovnika;
    private Drzava drzava;
    private TreeSet<Grad> letovi;

    public Grad(int id, String naziv, int brojStanovnika, Drzava drzava) {
        this.id = id;
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.drzava = drzava;
        letovi = new TreeSet<>();
    }

    public Grad(int id, String naziv, int brojStanovnika, Drzava drzava, TreeSet<Grad> letovi) {
        this.id = id;
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.drzava = drzava;
        this.letovi = letovi;
    }

    public Grad() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    public TreeSet<Grad> getLetovi() {
        return letovi;
    }

    public void setLetovi(TreeSet<Grad> letovi) {
        this.letovi = letovi;
    }

    @Override
    public String toString() { return naziv; }

    @Override
    public int compareTo(Object o) {
       return  (((Grad)o).getNaziv().compareTo(this.getNaziv()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grad grad = (Grad) o;
        return Objects.equals(naziv, grad.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv);
    }
}
