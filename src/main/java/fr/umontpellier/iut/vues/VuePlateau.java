package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.DonneesPlateau;
import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.rails.Joueur;
import fr.umontpellier.iut.rails.Route;
import fr.umontpellier.iut.rails.Ville;
import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

/**
 * Cette classe présente les routes et les villes sur le plateau.
 * <p>
 * On y définit le listener à exécuter lorsque qu'un élément du plateau a été choisi par l'utilisateur
 * ainsi que les bindings qui mettront ?à jour le plateau après la prise d'une route ou d'une ville par un joueur
 */
public class VuePlateau extends Pane {
    IJeu jeu;
    @FXML
    ImageView image;
    @FXML
    private Group villes;
    @FXML
    private Group routes;

    @FXML
    public void initialize() {
//set a clip to apply rounded border to the original image.
        Rectangle clip = new Rectangle(
                image.getFitWidth(), image.getFitHeight()
        );
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        image.setClip(clip);
//snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image1 = image.snapshot(parameters, null);
//remove the rounding clip so that our effect can show through.
        image.setClip(null);
//apply a shadow effect.
        image.setEffect(new DropShadow(40, Color.BLACK));
//store the rounded image in the imageView.
        image.setImage(image1);
    }


    public VuePlateau(IJeu jeu) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/plateau.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.jeu = jeu;
        for (Object o : jeu.getRoutes()) {
            Route r = (Route) o;
            r.proprietaireProperty().addListener(proprietaireRouteListener);
        }
        for(Object h : jeu.getVilles()){
            Ville v = (Ville) h;
            v.proprietaireProperty().addListener(proprietaireGareListener);
        }
    }

    @FXML
    public void choixRouteOuVille(MouseEvent e) {
        if (e.getSource().getClass().equals(Circle.class)) {
            jeu.uneVilleOuUneRouteAEteChoisie(e.getPickResult().getIntersectedNode().getId());
        } else if (e.getSource().getClass().equals(Group.class)) {
            Group here = (Group) e.getSource();
            jeu.uneVilleOuUneRouteAEteChoisie(here.getId());
        }

    }



    public void creerBindings() {
        bindRedimensionPlateau();
    }

    private void bindRedimensionPlateau() {
        bindRoutes();
        bindVilles();
        maxWidthProperty().bind(image.fitWidthProperty());
        maxHeightProperty().bind(image.fitHeightProperty());
    }

    private void bindRectangle(Rectangle rect, double layoutX, double layoutY) {

        rect.layoutXProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }

            @Override
            protected double computeValue() {
                return layoutX * image.getLayoutBounds().getWidth() / DonneesPlateau.largeurInitialePlateau;
            }
        });
        rect.layoutYProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }

            @Override
            protected double computeValue() {
                return layoutY * image.getLayoutBounds().getHeight() / DonneesPlateau.hauteurInitialePlateau;
            }
        });
        rect.widthProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }

            @Override
            protected double computeValue() {
                return DonneesPlateau.largeurRectangle * image.getLayoutBounds().getWidth() / DonneesPlateau.largeurInitialePlateau;
            }
        });
        rect.heightProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }

            @Override
            protected double computeValue() {
                return DonneesPlateau.hauteurRectangle * image.getLayoutBounds().getHeight() / DonneesPlateau.hauteurInitialePlateau;
            }
        });
        rect.xProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }

            @Override
            protected double computeValue() {
                return DonneesPlateau.xInitial * image.getLayoutBounds().getWidth() / DonneesPlateau.largeurInitialePlateau;
            }
        });
        rect.yProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }

            @Override
            protected double computeValue() {
                return DonneesPlateau.yInitial * image.getLayoutBounds().getHeight() / DonneesPlateau.hauteurInitialePlateau;
            }
        });
    }

    private void bindRoutes() {
        for (Node nRoute : routes.getChildren()) {
            Group gRoute = (Group) nRoute;
            int numRect = 0;
            for (Node nRect : gRoute.getChildren()) {
                Rectangle rect = (Rectangle) nRect;
                bindRectangle(rect, DonneesPlateau.getRoute(nRoute.getId()).get(numRect).getLayoutX(), DonneesPlateau.getRoute(nRoute.getId()).get(numRect).getLayoutY());
                numRect++;
            }
        }
    }

    private void bindVilles() {
        for (Node nVille : villes.getChildren()) {
            Circle ville = (Circle) nVille;
            bindVille(ville, DonneesPlateau.getVille(ville.getId()).getLayoutX(), DonneesPlateau.getVille(ville.getId()).getLayoutY());
        }
    }

    private void bindVille(Circle ville, double layoutX, double layoutY) {
        ville.layoutXProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }

            @Override
            protected double computeValue() {
                return layoutX * image.getLayoutBounds().getWidth() / DonneesPlateau.largeurInitialePlateau;
            }
        });
        ville.layoutYProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }

            @Override
            protected double computeValue() {
                return layoutY * image.getLayoutBounds().getHeight() / DonneesPlateau.hauteurInitialePlateau;
            }
        });
        ville.radiusProperty().bind(new DoubleBinding() {
            {
                super.bind(image.fitWidthProperty(), image.fitHeightProperty());
            }

            @Override
            protected double computeValue() {
                return DonneesPlateau.rayonInitial * image.getLayoutBounds().getWidth() / DonneesPlateau.largeurInitialePlateau;
            }
        });
    }

    ChangeListener proprietaireRouteListener = (observable, oldValue, newValue) -> {

        Joueur j = (Joueur) newValue;
        for (Object o : jeu.getRoutes()) {
            Route r = (Route) o;
            if (r.getProprietaire() != null && r.getProprietaire().equals(jeu.joueurCourantProperty().get())) {
                for (Node nRoute : routes.getChildren()) {
                    if (nRoute.getId().equals(r.getNom())) {
                        Group gRoute = (Group) nRoute;
                        for (int i =0;i<gRoute.getChildren().size();i++){
                            Node nRect = gRoute.getChildren().get(i);
                            Rectangle rect = (Rectangle) nRect;
                            ImageView image = new ImageView("images/wagons/image-wagon-" + j.getCouleur() + ".png");
                            image.layoutXProperty().bind(rect.layoutXProperty());
                            image.layoutYProperty().bind(rect.layoutYProperty());
                            image.rotateProperty().bind(rect.rotateProperty());
                            image.xProperty().bind(rect.xProperty());
                            image.yProperty().bind(rect.yProperty());
                            image.fitHeightProperty().bind(rect.heightProperty().multiply(1.2));
                            image.fitWidthProperty().bind(rect.widthProperty().multiply(1.2));
                            Platform.runLater(()->{ this.getChildren().add(image);});
                        }
                    }


                }
            }
        }

    };
    ChangeListener proprietaireGareListener = (observable, oldValue, newValue) -> {

        Joueur j = (Joueur) newValue;
        for (Object o : jeu.getVilles()) {
            Ville r = (Ville) o;
            if (r.getProprietaire() != null && r.getProprietaire().equals(jeu.joueurCourantProperty().get())) {
                for (Node nRoute : villes.getChildren()) {
                    if (nRoute.getId().equals(r.getNom())) {
                            Circle circle = (Circle) nRoute;
                            ImageView image = new ImageView("images/gares/gare-" + j.getCouleur() + ".png");
                            image.layoutXProperty().bind(circle.layoutXProperty().subtract(circle.radiusProperty().multiply(1.5)));
                            image.layoutYProperty().bind(circle.layoutYProperty().subtract(circle.radiusProperty().multiply(1.5)));
                            image.fitHeightProperty().bind(circle.radiusProperty().multiply(3));
                            image.fitWidthProperty().bind(circle.radiusProperty().multiply(3));
                            Platform.runLater(()->{ this.getChildren().add(image);});
                        }
                    }


                }
            }
        };

}


