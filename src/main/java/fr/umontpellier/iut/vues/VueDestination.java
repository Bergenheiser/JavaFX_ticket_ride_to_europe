package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IDestination;
import fr.umontpellier.iut.IJeu;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.Locale;
import java.util.Objects;


public class VueDestination extends Pane implements IDestination {
    private IDestination destination;
    private EventHandler<MouseEvent> hasBeenSelected = new EventHandler<>() {
        @Override
        public void handle(MouseEvent event) {
           IJeu jeu = ((VueDuJeu) getScene().getRoot()).getJeu();
           VueDestination source = (VueDestination) event.getSource();
           jeu.uneDestinationAEteChoisie(source.getDestination().getNom());
        }
    };

    public VueDestination(IDestination destination) {
        this.destination = destination;
        ImageView carte = new ImageView(String.format("images/missions/eu-%s.png", formatName(destination.getNom())));
        carte.setFitWidth(124);
        carte.preserveRatioProperty().set(true);
        setId(destination.getNom());
        setPickOnBounds(true);
        setOnMouseClicked(hasBeenSelected);
        this.getChildren().add(carte);

    }
    private String formatName(String name) {
        name = name.toLowerCase(Locale.ROOT).trim().replaceAll("\s", "");
        return name.substring(0, name.indexOf("("));
    }

    public IDestination getDestination() {
        return destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VueDestination)) return false;
        VueDestination that = (VueDestination) o;
        return destination.equals(that.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination);
    }

    @Override
    public String getNom() {
        return null;
    }
}
