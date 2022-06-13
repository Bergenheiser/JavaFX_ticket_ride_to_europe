package fr.umontpellier.iut;

import fr.umontpellier.iut.rails.CouleurWagon;
import fr.umontpellier.iut.rails.Destination;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IJoueur {

    ObservableList<CouleurWagon> cartesWagonProperty();

    public static enum Couleur {
        BLEU, ROUGE, VERT, JAUNE, ROSE;
    }

    List<CouleurWagon> getCartesWagonPosees();
    List<CouleurWagon> getCartesWagon();
    String getNom();
    Couleur getCouleur();
    int getNbGares();
    public ObservableList<Destination> destinationsProperty();
    public IntegerProperty nbWagonsProperty();
    public IntegerProperty scoreProperty();



}