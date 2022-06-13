package fr.umontpellier.iut;

import fr.umontpellier.iut.rails.ServiceDuJeu;
import fr.umontpellier.iut.vues.VueCarteWagon;
import fr.umontpellier.iut.vues.VueChoixJoueurs;
import fr.umontpellier.iut.vues.VueDuJeu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RailsIHM extends Application {

    private VueDuJeu vueDuJeu;
    private VueChoixJoueurs vueChoixJoueurs;
    private Stage primaryStage;
    private ServiceDuJeu serviceDuJeu;
    private VueCarteWagon vueCarteWagon;
    private boolean avecVueChoixJoueurs = true;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        if (avecVueChoixJoueurs) {
            this.vueChoixJoueurs = new VueChoixJoueurs(this);
            Scene scene = new Scene(vueChoixJoueurs);
            //vueChoixJoueurs.setNomsDesJoueursDefinisListener(quandLesNomsJoueursSontDefinis);
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
          //  demarrerPartie();
        }
    }

    public void  demarrerPartie() throws FileNotFoundException {
        List<String> nomsJoueurs;
        if (avecVueChoixJoueurs)
            nomsJoueurs = vueChoixJoueurs.getNomsJoueurs();
        else {
            nomsJoueurs = new ArrayList<>();
            nomsJoueurs.add("Guybrush");
            nomsJoueurs.add("Largo");
            nomsJoueurs.add("LeChuck");
            nomsJoueurs.add("Elaine");
        }

        serviceDuJeu = new ServiceDuJeu(nomsJoueurs.toArray(new String[0]),vueChoixJoueurs.imgdu);
        vueDuJeu = new VueDuJeu(serviceDuJeu.getJeu());
        Scene scene = new Scene(vueDuJeu);// la scene doit être créée avant de mettre en place les bindings
        vueDuJeu.creerBindings();
        demarrerServiceJeu(); // le service doit être démarré après que les bindings ont été mis en place
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Les aventuriers du rails - Europe");
        primaryStage.centerOnScreen();
        primaryStage.setOnCloseRequest(event -> {
            this.onStopGame();
            event.consume();
        });
        primaryStage.show();
    }

    private void demarrerServiceJeu() {
        if (serviceDuJeu.getState() == Worker.State.READY) {
            serviceDuJeu.start();
        }
        primaryStage.show();
    }

    private final ListChangeListener<String> quandLesNomsJoueursSontDefinis = change -> {
        if (!vueChoixJoueurs.getNomsJoueurs().isEmpty()) {
            try {
                demarrerPartie();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    };

    public void onStopGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("On arrête de jouer ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            serviceDuJeu.getJeu().cancel();
            Platform.exit();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}