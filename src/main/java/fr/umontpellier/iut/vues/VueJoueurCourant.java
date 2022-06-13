package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.ICouleurWagon;
import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import fr.umontpellier.iut.rails.CouleurWagon;
import fr.umontpellier.iut.rails.Destination;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.List;
import java.util.Map;


/**
 * Cette classe présente les éléments appartenant au joueur courant.
 * On y définit les bindings sur le joueur courant, ainsi que le listener à exécuter lorsque ce joueur change
 */
public class VueJoueurCourant extends VBox {

    private IJeu jeu;
    private ObjectProperty<IJoueur> joueurCourant;
    private Label nomJoueur;
    private Label scoreJoueur;
    private ObservableList<Destination> destinationsJoueur;
    private HBox destinationDeck;
    private ObservableList<CouleurWagon> cartesWagonsJoueur;
    private List<CouleurWagon> cartesWagonsPosees;
    private HBox cardDeck;
    private Map<CouleurWagon, Integer> frequency;
    Label instructions;


    VueJoueurCourant(IJeu jeu) {

        this.jeu = jeu;
        instructions = new Label(this.jeu.instructionProperty().getValue());
        instructions.textFillProperty().setValue(Paint.valueOf("#384454"));
        instructions.setStyle("-fx-font-size: 15");
        this.joueurCourant = new SimpleObjectProperty<>();
        this.cartesWagonsJoueur = new SimpleListProperty<>();
        this.nomJoueur = new Label();
        this.scoreJoueur = new Label();
        this.destinationDeck = new HBox();
        destinationDeck.setSpacing(5);
        destinationDeck.setAlignment(Pos.CENTER);
        this.cardDeck = new HBox();
        creerBindings();
        cardDeck.setMaxWidth(850);
        getChildren().addAll(instructions,cardDeck,destinationDeck);
        setSpacing(10);
        setPadding(new Insets(10, 0, 0, 0));
        setAlignment(Pos.CENTER);

    }

    private <T extends Pane> void makeCW(ICouleurWagon c, T n, Map<CouleurWagon, Integer> map) {
        Circle cercle = new Circle();
        cercle.setRadius(10);
        cercle.setFill(Color.web("#4b4b4b"));
        Label value = new Label(String.valueOf(map.get(c)));
        value.setId("value" + c.toString());
        value.textFillProperty().set(Paint.valueOf("white"));
        StackPane stack = new StackPane();
        stack.getChildren().addAll(cercle, value);
        VueCarteWagon carte = new VueCarteWagon(c);
        Pane overStack = new Pane(carte);
        overStack.setId(c.toString());
        overStack.getChildren().add(stack);
        stack.setLayoutX(overStack.getWidth() + 2);
        stack.setLayoutY(overStack.getHeight() + 5);
        n.getChildren().add(overStack);
    }


    public void creerBindings() {
        this.joueurCourant.bind(jeu.joueurCourantProperty());
        instructions.textProperty().bind(this.jeu.instructionProperty());
        ListChangeListener<CouleurWagon> updateCW = change -> Platform.runLater(() -> {
            while (change.next()) {
                    if (change.wasAdded()) {
                        for (CouleurWagon c : change.getAddedSubList()) {
                            Label value = (Label) cardDeck.lookup("#value" + c);
                            value.setText(String.valueOf(this.frequency.get(c) + 1));

                        }
                    } else if (change.wasRemoved()) {
                        for (CouleurWagon c : change.getRemoved()) {
                            getFrequency();
                            Label value = (Label) cardDeck.lookup("#value" + c);
                            value.setText(String.valueOf(this.frequency.get(c)));
                        }

                    }
            }
        });

        this.joueurCourant.addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                //Nettoyage de l'interface
                cardDeck.getChildren().clear();
                destinationDeck.getChildren().clear();
                //Changement des valeurs.
                this.nomJoueur.textProperty().setValue(newValue.getNom());
                this.scoreJoueur.textProperty().setValue(newValue.scoreProperty().getValue().toString());
                this.destinationsJoueur = newValue.destinationsProperty();
                this.cartesWagonsPosees = newValue.getCartesWagonPosees();
                this.cartesWagonsJoueur = newValue.cartesWagonProperty();
                this.cartesWagonsJoueur.addListener(updateCW);
                getFrequency();
                for (CouleurWagon c : frequency.keySet()) {
                    if (!c.equals(CouleurWagon.GRIS)) { //frequency.get(c) != 0)
                        makeCW(c, cardDeck, this.frequency);
                    }
                }
                for (Destination d : destinationsJoueur) {
                    Label dest = new Label(d.getNom());
                    dest.setStyle("-fx-border-style: solid; -fx-border-radius: 30");
                    dest.setPadding(new Insets(5));
                    destinationDeck.getChildren().add(dest);
                }
            });
        });
    }
        private void getFrequency () {
            this.frequency = ICouleurWagon.compteur(cartesWagonsJoueur);
        }


    }
