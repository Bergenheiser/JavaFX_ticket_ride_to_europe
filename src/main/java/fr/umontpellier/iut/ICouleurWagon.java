package fr.umontpellier.iut;

import fr.umontpellier.iut.rails.CouleurWagon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ICouleurWagon {
    static ICouleurWagon[] values() {
        return CouleurWagon.values();
    }


    static Map<CouleurWagon, Integer> compteur(List<CouleurWagon> couleurs) {
        return CouleurWagon.compteur(couleurs);
    }

}