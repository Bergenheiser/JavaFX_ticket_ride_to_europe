package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.rails.CouleurWagon;
import fr.umontpellier.iut.rails.Destination;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.util.ArrayList;

public class VueDuJeu extends GridPane {

    //TODO  règles?

    private HBox titleBox;
    private IJeu jeu;
    private VuePlateau plateau;
    private VBox choiceBox;
    private VBox cardBox;
    private VBox purchaseBox;
    private HBox actionBox;
    private VueJoueurCourant vueJoueurCourant;
    private VueAutresJoueurs vueAutresJoueurs;
    MediaPlayer mediaPlayer;

    public void music () {
        String s="src/main/resources/sound/ost.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer= new MediaPlayer(h);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public VueDuJeu(IJeu jeu) {
        this.jeu = jeu;
        this.plateau = new VuePlateau(jeu);
        this.vueJoueurCourant = new VueJoueurCourant(jeu);
        this.vueAutresJoueurs = new VueAutresJoueurs(jeu);
        music();
        /////////
        vueJoueurCourant.setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY,Insets.EMPTY)));

        /////////
        Image titleImg = new Image("/images/title.png",250,60,false,false);
        ImageView titre = new ImageView(titleImg);
        titre.setPreserveRatio(true);
        titre.setEffect(new DropShadow());
        this.titleBox = new HBox();
        titleBox.setAlignment(Pos.TOP_CENTER);
        titleBox.getChildren().add(titre);
        ///Instructions///
        vueJoueurCourant.instructions = new Label(this.jeu.instructionProperty().getValue());
        vueJoueurCourant.instructions.setAlignment(Pos.CENTER);
        vueJoueurCourant.instructions.setStyle("-fx-background-color: grey");
        purchaseBox = new VBox();
        purchaseBox.setId("purchaseBox");
        purchaseBox.setStyle("-fx-background-color: #D3D3D3; -fx-border-style:solid");
        ImageView piocheDest = new ImageView("images/piocheDestinations.png");
        piocheDest.setPreserveRatio(true);
        piocheDest.setOnMouseClicked(event -> {
            this.jeu.uneDestinationAEtePiochee();
        });
        piocheDest.setFitWidth(149);
        piocheDest.setFitHeight(96);
        ImageView piocheCarteWagon = new ImageView("images/piocheWagon.png");
        piocheCarteWagon.setPreserveRatio(true);
        piocheCarteWagon.setOnMouseClicked(event -> {
            this.jeu.uneCarteWagonAEtePiochee();
        });
        piocheCarteWagon.setFitWidth(149);
        piocheCarteWagon.setFitHeight(96);
        purchaseBox.getChildren().addAll(piocheDest, piocheCarteWagon);
        purchaseBox.setAlignment(Pos.CENTER);
        //////////////////////
        choiceBox = new VBox();
        choiceBox.setAlignment(Pos.TOP_LEFT);
        choiceBox.setSpacing(10);
        choiceBox.setStyle("-fx-border-style: solid;-fx-background-color: #D3D3D3");
        choiceBox.setPadding(new Insets(15.0));
        ////////////::
        cardBox = new VBox();
        cardBox.setAlignment(Pos.TOP_CENTER);
        cardBox.setPadding(new Insets(20));
        cardBox.setSpacing(5);
        /////////////
        vueJoueurCourant.setStyle("-fx-border-style: solid;-fx-background-color: #D3D3D3");
        //////////////
        actionBox = new HBox();
        actionBox.setPadding(new Insets(5));
        actionBox.setSpacing(10);
        actionBox.setStyle("-fx-background-color: #D3D3D3");
        actionBox.setAlignment(Pos.CENTER);
        Image content = new Image("images/icons/next.png", 50, 50, false, false);
        //////////////////
        Image regles = new Image("images/icons/rules.png",50,50,false,false);
        ImageView rules = new ImageView(regles);
        rules.setPickOnBounds(true);
        Stage popUp = new Stage();
        popUp.initModality(Modality.APPLICATION_MODAL);
        ImageView p1 = new ImageView(new Image("images/regles1.png"));
        p1.setFitHeight(900);
        p1.setPreserveRatio(true);
        ImageView p2 = new ImageView("images/regles2.png");
        p2.preserveRatioProperty().set(true);
        p2.setFitHeight(880);
        HBox frame = new HBox(p1);
        frame.setAlignment(Pos.CENTER);
        HBox frame2 = new HBox();
        frame2.setAlignment(Pos.CENTER);
        ImageView passerRegle = new ImageView(content);
        Image previous = new Image("images/icons/previous.png",50,50,false,false);
        ImageView revenir = new ImageView(previous);
        ImageView revenir2 = new ImageView(previous);
        ImageView next3 = new ImageView(content);
        revenir.setPickOnBounds(true);
        passerRegle.setPickOnBounds(true);
        frame.getChildren().add(passerRegle);
        frame.setSpacing(10);
        frame.setPadding(new Insets(20));
        frame2.getChildren().addAll(revenir,p2,next3);
        frame2.setPadding(new Insets(20));
        frame2.setSpacing(10);
        HBox frame3= new HBox();
        Image p3 = new Image("images/regles3.png");
        ImageView lastPage = new ImageView(p3);
        lastPage.setFitHeight(880);
        lastPage.preserveRatioProperty().set(true);
        frame3.getChildren().addAll(revenir2,lastPage);
        frame3.setPadding(new Insets(20));
        frame3.setSpacing(10);
        frame3.setAlignment(Pos.CENTER);
        Scene page2 = new Scene(frame2);
        Scene page1 = new Scene(frame);
        Scene page3 = new Scene(frame3);
        popUp.setScene(page1);

        revenir.setOnMouseClicked(event->{
            popUp.setScene(page1);
        });

        next3.setOnMouseClicked(event -> {
            popUp.setScene(page3);
        });

        passerRegle.setOnMouseClicked(event->{
            popUp.setScene(page2);
        });

        revenir2.setOnMouseClicked(event ->{
            popUp.setScene(page2);
        });

        rules.setOnMouseClicked(event -> {
            popUp.showAndWait();
        });
        ////////////
        ImageView passer = new ImageView(content);
        passer.preserveRatioProperty().set(true);
        passer.setFitHeight(50);
        passer.setOnMouseClicked(event -> {
            this.jeu.passerAEteChoisi();
        });
        //////////////
        Image isOFF = new Image("images/icons/volume_on.png");
        Image isON = new Image("images/icons/volume_off.png");
        ImageView muter = new ImageView(isON);
        muter.preserveRatioProperty().set(true);
        muter.pickOnBoundsProperty().set(true);
        muter.setOnMouseClicked(mouseEvent -> {
            if (mediaPlayer.isMute()) {
                Platform.runLater(()->{
                muter.setImage(isON);
                });
                mediaPlayer.setMute(false);
            } else {
                Platform.runLater(()->{
                muter.setImage(isOFF);
            });
                mediaPlayer.setMute(true);
            }
        });
        /////////////
        actionBox.getChildren().addAll(passer,muter,rules);
        choiceBox.getChildren().addAll(cardBox);
        getChildren().addAll(titleBox,choiceBox, plateau, vueAutresJoueurs, purchaseBox, vueJoueurCourant, actionBox);
        setConstraints(titleBox, 2, 0);
        setConstraints(choiceBox, 1, 1);
        setConstraints(plateau, 2, 1);
        setConstraints(vueAutresJoueurs, 3, 1);
        setConstraints(purchaseBox, 1, 6);
        setConstraints(vueJoueurCourant, 2, 6);
        setConstraints(actionBox,3,6);
        setBackground(new Background(new BackgroundFill(Color.web("#384454"),CornerRadii.EMPTY,Insets.EMPTY)));
        //////////////////

        setStyle("-fx-border-radius: 5;-fx-border-style: solid;-fx-border-width: 2;-fx-border-insets: 1");
    }


    public IJeu getJeu() {
        return jeu;
    }

    public void creerBindings() {
        //Binding cartesDestination initiales//
        ListChangeListener<Destination> listenerDestinations = change -> {
            Platform.runLater(() -> {
                while (change.next()) {
                    if (change.wasAdded()) {
                        for (Destination d : change.getAddedSubList()) {
                            VueDestination dest = new VueDestination(d);
                            this.cardBox.getChildren().add(dest);
                        }
                    }
                    if (change.wasRemoved()) {
                        for (Destination h : change.getRemoved()) {
                            VueDestination toRemove = new VueDestination(h);
                            VueDestination foundCard = new VueDestination(h);
                            for (Node d : this.cardBox.getChildren()) {
                                if (d.getClass() == VueDestination.class) {
                                    VueDestination node = (VueDestination) d;
                                    if (node.equals(toRemove)) {
                                        foundCard = node;
                                        break;
                                    }
                                }
                            }
                            this.cardBox.getChildren().remove(foundCard);
                        }
                    }
                }
            });
        };
        jeu.destinationsInitialesProperty().addListener(listenerDestinations);


        //Binding CartesWagons Visibles///
        ListChangeListener<CouleurWagon> listenerCartesWagons = change -> Platform.runLater(() -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (CouleurWagon c : change.getAddedSubList()) {
                        if (cardBox.getChildren().size() < 5) {
                            VueCarteWagon carte = new VueCarteWagon(c);
                            this.cardBox.getChildren().add(carte);
                        }
                    }
                } else if (change.wasRemoved()) {
                    VueCarteWagon curr = (VueCarteWagon) this.cardBox.getChildren().get(0);
                    for (Node n : this.cardBox.getChildren()) {
                        VueCarteWagon node = (VueCarteWagon) n;
                        if (node.getCouleurWagon().equals(change.getRemoved().get(0))) {
                            curr = node;
                            break;
                        }
                    }
                    this.cardBox.getChildren().remove(curr);
                }
            }
        });


        ///BINDINGS
        jeu.cartesWagonVisiblesProperty().addListener(listenerCartesWagons);
        plateau.creerBindings();
        vueAutresJoueurs.layoutXProperty().set(choiceBox.getWidth() + plateau.getWidth());
        vueAutresJoueurs.setMinWidth(250);
        vueAutresJoueurs.setFillWidth(true);
        choiceBox.minWidthProperty().bind(vueAutresJoueurs.widthProperty().subtract(50));
        choiceBox.maxHeight(getScene().getHeight() - vueJoueurCourant.getHeight());
        plateau.image.fitWidthProperty().bind(vueJoueurCourant.widthProperty());
        plateau.image.fitHeightProperty().bind(vueAutresJoueurs.heightProperty());
        vueJoueurCourant.minWidthProperty().bind(getScene().widthProperty().multiply(0.666));
        vueJoueurCourant.minHeightProperty().bind(actionBox.heightProperty());


    }
}
