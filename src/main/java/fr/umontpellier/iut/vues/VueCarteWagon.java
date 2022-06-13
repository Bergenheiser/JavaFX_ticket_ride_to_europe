package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.ICouleurWagon;
import fr.umontpellier.iut.IJeu;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


/**
 * Cette classe représente la vue d'une carte Wagon.
 * <p>
 * On y définit le listener à exécuter lorsque cette carte a été choisie par l'utilisateur
 */
public class VueCarteWagon extends Pane implements ICouleurWagon {

    private ICouleurWagon couleurWagon;

    private EventHandler<MouseEvent> piocherOuPoser = event -> {
        IJeu jeu = ((VueDuJeu) getScene().getRoot()).getJeu();
        VueCarteWagon source = (VueCarteWagon) event.getSource();
        jeu.uneCarteWagonAEteChoisie(source.getCouleurWagon());
    };


    public VueCarteWagon(ICouleurWagon couleurWagon) {
        this.couleurWagon = couleurWagon;
        ImageView img = new ImageView("images/cartesWagons/carte-wagon-" + couleurWagon.toString().toUpperCase() + ".png");
        img.setFitWidth(img.getImage().getWidth() * 0.5);
        img.setFitHeight(img.getImage().getHeight() * 0.5);
        img.setPickOnBounds(true);
        getChildren().add(img);
        setOnMouseClicked(piocherOuPoser);

    };

    public ICouleurWagon getCouleurWagon() {
        return couleurWagon;
    };

}
