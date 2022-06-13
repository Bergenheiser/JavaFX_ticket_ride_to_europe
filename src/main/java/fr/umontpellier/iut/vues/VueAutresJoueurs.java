package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.IJoueur;
import fr.umontpellier.iut.rails.Joueur;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe présente les éléments des joueurs autres que le joueur courant,
 * en cachant ceux que le joueur courant n'a pas à connaitre.
 * <p>
 * On y définit les bindings sur le joueur courant, ainsi que le listener à exécuter lorsque ce joueur change
 */
public class VueAutresJoueurs extends VBox {
    List<VueJoueur> joueurs;
    IJeu jeu;
    private HBox titleBox;

    VueAutresJoueurs(IJeu jeu) {
        this.jeu = jeu;
        this.joueurs = new ArrayList<VueJoueur>();
        for (Joueur j : jeu.getJoueurs()) {
            IJoueur joueur = j;
            VueJoueur vue = new VueJoueur(joueur);
            this.joueurs.add(vue);
        }
        for (VueJoueur v : joueurs) {
            getChildren().add(v);
        }

        jeu.joueurCourantProperty().addListener((observable, oldValue, newValue) -> {
            for(VueJoueur v : joueurs){
                if(v.getJoueur().equals(oldValue)){
                    v.getProfile().setFitHeight(100);
                    v.getProfileWrapper().setStyle("");
                }
                if(v.getJoueur().equals(newValue)){
                    v.getProfile().setFitHeight(110);
                    v.getProfileWrapper().setStyle("-fx-border-color: green ; -fx-border-radius: 100 ; -fx-border-width: 4");
                }

            }
        });
        setAlignment(Pos.CENTER);
        setSpacing(5);



    }


    static class VueJoueur extends HBox {
        IJoueur player;
        Label score;
        Label nbWagons;
        Label name;
        VBox data;
        ImageView profile;
        BorderPane profileWrapper;

        VueJoueur(IJoueur joueur) {
            this.player = joueur;
            this.score = new Label("Score : "+joueur.scoreProperty().getValue().toString());
            this.nbWagons = new Label("Wagons : "+joueur.nbWagonsProperty().getValue().toString());
            this.name = new Label(joueur.getNom());
            name.setStyle("-fx-font-weight: bold ; -fx-font-size: 20; -fx-text-fill:WHITE");
            name.setFont(Font.font("Calibri"));
            name.setPadding(new Insets(10));
            Image icon = new Image("images/joueurs/avatar-"+this.player.getCouleur()+".png",100,100,false,false);
            this.profile = new ImageView(icon);
            this.profile.preserveRatioProperty().setValue(true);
            this.profileWrapper = new BorderPane(this.profile);
            this.profileWrapper.setCenterShape(true);
            this.profileWrapper.setEffect(new DropShadow(5.00,3.00,3.00,Color.BLACK));
            this.data = new VBox();
            score.setStyle("-fx-text-fill: #CEDAFF ");
            nbWagons.setStyle("-fx-text-fill:#CEDAFF");
            data.getChildren().addAll(name,score,nbWagons);
            data.setAlignment(Pos.CENTER);
            data.setSpacing(8);
            getChildren().addAll(profileWrapper,data);
            joueur.scoreProperty().addListener((observable, oldValue, newValue) -> {
                    Platform.runLater(() -> {
                    this.score.textProperty().set("Score : "+newValue.toString());});
            });
            joueur.nbWagonsProperty().addListener((observable, oldValue, newValue) -> {
                    Platform.runLater(()->{
                    this.nbWagons.textProperty().set("Wagons : "+newValue.toString());});
                });

            setAlignment(Pos.CENTER);


        }
        public ImageView getProfile(){
            return profile;
        }
        public BorderPane getProfileWrapper(){
            return profileWrapper;
        }
        public IJoueur getJoueur() {
            return this.player;
        }
    }


}

