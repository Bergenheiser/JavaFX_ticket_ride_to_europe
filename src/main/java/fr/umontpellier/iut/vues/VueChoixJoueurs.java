package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.RailsIHM;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Cette classe correspond à une nouvelle fenêtre permettant de choisir le nombre et les noms des joueurs de la partie.
 *
 * Sa présentation graphique peut automatiquement être actualisée chaque fois que le nombre de joueurs change.
 * Lorsque l'utilisateur a fini de saisir les noms de joueurs, il demandera à démarrer la partie.
 */
public class VueChoixJoueurs extends Pane {
    @FXML
    private ObservableList<String> nomsJoueurs;

    @FXML
    public ObservableList<String> nomsJoueursProperty() {
        return nomsJoueurs;
    }

    public List<String> getNomsJoueurs() {
        return nomsJoueurs;
    }

        @FXML
        private TextField nomsJoueurs1=new TextField();

        @FXML
        private TextField nomsJoueurs11=new TextField();

        @FXML
        private TextField nomsJoueurs2=new TextField();

        @FXML
        private TextField nomsJoueurs22=new TextField();

        @FXML
        private TextField nomsJoueurs3=new TextField();

        @FXML
        private TextField nomsJoueurs33=new TextField();

        @FXML
        private TextField nomsJoueurs4=new TextField();

        @FXML
        private TextField nomsJoueurs44=new TextField();

        @FXML
        private TextField nomsJoueurs5=new TextField();

        @FXML
        private TextField nomsJoueurs55=new TextField();

        @FXML
        private ImageView img1=new ImageView();

        @FXML
        private ImageView img2=new ImageView();

        @FXML
        private ImageView img3=new ImageView();

        @FXML
        private ImageView img4=new ImageView();

        @FXML
        private ImageView img5=new ImageView();

        @FXML
        private RadioButton b1;

        @FXML
        private RadioButton b2;

        @FXML
        private RadioButton b3;

        @FXML
        private RadioButton b4;

        int nbrJ=5;
        private RailsIHM RailsIHM ;

        @FXML
        private Button p1 = new Button();

        @FXML
        private Button p2= new Button();

        @FXML
        private Button p3= new Button();

        @FXML
        private Button p4= new Button();

        @FXML
        private Button p5= new Button();

        @FXML
        private Button m1= new Button();

        @FXML
        private Button m2= new Button();

        @FXML
        private Button m3= new Button();

        @FXML
        private Button m4= new Button();

        @FXML
        private Button m5= new Button();

    @FXML
        public void PLAYClick(ActionEvent event) throws FileNotFoundException {
        setListeDesNomsDeJoueurs();
        RailsIHM.demarrerPartie();
        }

        @FXML
        public void br(ActionEvent event) {
            b1.setOnMouseClicked(actionEvent -> {
                Platform.runLater(()->{
                b2.setSelected(false);
                b3.setSelected(false);
                b4.setSelected(false);
                b1.setSelected(true);

                nomsJoueurs3.setVisible(false);
                nomsJoueurs33.setVisible(false);
                img3.setVisible(false);
                p3.setVisible(false);
                m3.setVisible(false);

                nomsJoueurs4.setVisible(false);
                nomsJoueurs44.setVisible(false);
                img4.setVisible(false);
                p4.setVisible(false);
                m4.setVisible(false);

                nomsJoueurs5.setVisible(false);
                nomsJoueurs55.setVisible(false);
                img5.setVisible(false);
                p5.setVisible(false);
                m5.setVisible(false);

                nbrJ=2;
                });
            });
            b2.setOnMouseClicked(actionEvent -> {
                Platform.runLater(()->{
                    b1.setSelected(false);
                    b3.setSelected(false);
                    b4.setSelected(false);
                    b2.setSelected(true);
                    nomsJoueurs4.setVisible(false);
                    nomsJoueurs44.setVisible(false);
                    img4.setVisible(false);
                    p4.setVisible(false);
                    m4.setVisible(false);
                    nomsJoueurs5.setVisible(false);
                    nomsJoueurs55.setVisible(false);
                    img5.setVisible(false);
                    p5.setVisible(false);
                    m5.setVisible(false);

                    p3.setVisible(true);
                    m3.setVisible(true);
                    nomsJoueurs3.setVisible(true);
                    nomsJoueurs33.setVisible(true);
                    img3.setVisible(true);

                    nbrJ=3;
                });
            });
            b3.setOnMouseClicked(actionEvent -> {
                Platform.runLater(()->{
                    b1.setSelected(false);
                    b2.setSelected(false);
                    b4.setSelected(false);
                    b3.setSelected(true);
                    nomsJoueurs5.setVisible(false);
                    nomsJoueurs55.setVisible(false);
                    img5.setVisible(false);
                    p5.setVisible(false);
                    m5.setVisible(false);

                    p3.setVisible(true);
                    m3.setVisible(true);
                    p4.setVisible(true);
                    m4.setVisible(true);
                    nomsJoueurs3.setVisible(true);
                    nomsJoueurs33.setVisible(true);
                    img3.setVisible(true);
                    nomsJoueurs4.setVisible(true);
                    nomsJoueurs44.setVisible(true);
                    img4.setVisible(true);

                    nbrJ=4;
                });
            });

            b4.setOnMouseClicked(actionEvent -> {
                Platform.runLater(()->{
                    b2.setSelected(false);
                    b3.setSelected(false);
                    b1.setSelected(false);
                    b4.setSelected(true);
                nomsJoueurs3.setVisible(true);
                nomsJoueurs33.setVisible(true);
                img3.setVisible(true);
                nomsJoueurs4.setVisible(true);
                nomsJoueurs44.setVisible(true);
                img4.setVisible(true);
                nomsJoueurs5.setVisible(true);
                nomsJoueurs55.setVisible(true);
                img5.setVisible(true);
                p3.setVisible(true);
                m3.setVisible(true);
                p4.setVisible(true);
                m4.setVisible(true);
                p5.setVisible(true);
                m5.setVisible(true);

                nbrJ=5;
                });
            });

        }
        int count1= 8;
    private final EventHandler<MouseEvent> mouseEventHandler11 = event -> {
           Platform.runLater(()->{
               if (count1<=40) {
                   count1++;
                   nomsJoueurs11.setText(String.valueOf(count1));
               }
           });
    };
    private final EventHandler<MouseEvent> mouseEventHandler12 = event -> {
        Platform.runLater(()->{
            if (count1>0) {
                count1--;
                nomsJoueurs11.setText(String.valueOf(count1));
            }
        });
    };
    ///
    int count2=9;
    private final EventHandler<MouseEvent> mouseEventHandler21 = event -> {
        Platform.runLater(()->{
            if (count2<=40) {
                count2++;
                nomsJoueurs22.setText(String.valueOf(count2));
            }
        });
    };
    private final EventHandler<MouseEvent> mouseEventHandler22 = event -> {
        Platform.runLater(()->{
            if (count2>0) {
                count2--;
                nomsJoueurs22.setText(String.valueOf(count2));
            }
        });
    };
    ///
    int count3=10;
    private final EventHandler<MouseEvent> mouseEventHandler31 = event -> {
        Platform.runLater(()->{
            if (count3<=40) {
                count3++;
                nomsJoueurs33.setText(String.valueOf(count3));
            }
        });
    };
    private final EventHandler<MouseEvent> mouseEventHandler32 = event -> {
        Platform.runLater(()->{
            if (count3>0) {
                count3--;
                nomsJoueurs33.setText(String.valueOf(count3));
            }
        });
    };
    ///
    int count4=11;
    private final EventHandler<MouseEvent> mouseEventHandler41 = event -> {
        Platform.runLater(()->{
            if (count4<=40) {
                count4++;
                nomsJoueurs44.setText(String.valueOf(count4));
            }
        });
    };
    private final EventHandler<MouseEvent> mouseEventHandler42 = event -> {
        Platform.runLater(()->{
            if (count4>0) {
                count4--;
                nomsJoueurs44.setText(String.valueOf(count4));
            }
        });
    };
    ///
    int count5=12;
    private final EventHandler<MouseEvent> mouseEventHandler51 = event -> {
        Platform.runLater(()->{
            if (count5<=40) {
                count5++;
                nomsJoueurs55.setText(String.valueOf(count5));
            }
        });
    };
    private final EventHandler<MouseEvent> mouseEventHandler52 = event -> {
        Platform.runLater(()->{
            if (count5>0) {
                count5--;
                nomsJoueurs55.setText(String.valueOf(count5));
            }
        });
    };
    public VueChoixJoueurs(RailsIHM railsIHM) {
        this.RailsIHM=railsIHM;
        nomsJoueurs = FXCollections.observableArrayList();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/lancementDuJeu.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
            p1.setOnMouseClicked(mouseEventHandler11);
            m1.setOnMouseClicked(mouseEventHandler12);
            p2.setOnMouseClicked(mouseEventHandler21);
            m2.setOnMouseClicked(mouseEventHandler22);
            p3.setOnMouseClicked(mouseEventHandler31);
            m3.setOnMouseClicked(mouseEventHandler32);
            p4.setOnMouseClicked(mouseEventHandler41);
            m4.setOnMouseClicked(mouseEventHandler42);
            p5.setOnMouseClicked(mouseEventHandler51);
            m5.setOnMouseClicked(mouseEventHandler52);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Définit l'action à exécuter lorsque la liste des participants est correctement initialisée
     */
    public void setNomsDesJoueursDefinisListener(ListChangeListener<String> quandLesNomsDesJoueursSontDefinis) {

    }

    /**
     * Définit l'action à exécuter lorsque le nombre de participants change
     */
    protected void setChangementDuNombreDeJoueursListener(ChangeListener<Integer> quandLeNombreDeJoueursChange) {}

    public int [] imgdu=new int [5];
    /**
     * Vérifie que tous les noms des participants sont renseignés
     * et affecte la liste définitive des participants
     */
    protected void setListeDesNomsDeJoueurs() {
        List<String> tempNamesList = new ArrayList<>();
        String [] tab= new String[getNombreDeJoueurs()];
        ArrayList listenum=new ArrayList<>();
        int aux=1;
        for (int i = 1; i <= getNombreDeJoueurs() ; i++) {
            String name = getJoueurParNumero(i);
            int nbr = getNbrPaysJoueurParNumero(i);
            int pos=0;
            for (int x=1;x<=getNombreDeJoueurs();x++){
                if (nbr<getNbrPaysJoueurParNumero(x)){
                    listenum.add(nbr);
                    pos++;
                }
            }
            if (name == null || name.equals("")) {
                tempNamesList.clear();
                break;
            }
            else {
                if(tab[pos]==null) {
                    tab[pos] = name;
                    imgdu[pos] += i ;
                }
                else {
                    tab[pos + aux] = name;
                    imgdu[pos + aux] += i ;
                    aux++;
                }
            }

        }
        tempNamesList.addAll(Arrays.asList(tab));
        if (!tempNamesList.isEmpty()) {
            //hide();
            nomsJoueurs.clear();
            nomsJoueurs.addAll(tempNamesList);
        }
    }

    protected int getNbrPaysJoueurParNumero(int playerNumber) {
        int nbr=0;
        if (playerNumber==1 ){
            if(nomsJoueurs11.getText()!=null || nomsJoueurs11.equals("")) {
                nbr = 0;
                nbr = Integer.parseInt(nomsJoueurs11.getText());
            }
        }else if (playerNumber==2){
            if(nomsJoueurs22.getText()!=null || nomsJoueurs22.equals("")) {
                nbr = 0;
                nbr = Integer.parseInt(nomsJoueurs22.getText());
            }
        }else if (playerNumber==3 || nomsJoueurs33.equals("")){
            if(nomsJoueurs33.getText()!=null){
                nbr=0;
                nbr= Integer.parseInt(nomsJoueurs33.getText());
            }
        }else if (playerNumber==4 || nomsJoueurs44.equals("")){
            if(nomsJoueurs44.getText()!=null) {
                nbr = 0;
                nbr = Integer.parseInt(nomsJoueurs44.getText());
            }
        }else if (playerNumber==5 || nomsJoueurs55.equals("")){
            if(nomsJoueurs55.getText()!=null){
                nbr=0;
                nbr= Integer.parseInt(nomsJoueurs55.getText());
            }
        }
        return nbr;
    }

    /**
     * Retourne le nombre de participants à la partie que l'utilisateur a renseigné
     */
    protected int getNombreDeJoueurs() {
        return nbrJ;
    }

    /**
     * Retourne le nom que l'utilisateur a renseigné pour le ième participant à la partie
     * @param playerNumber : le numéro du participant
     */
    protected String getJoueurParNumero(int playerNumber) {
        String nom=null;
        if (playerNumber==1){
            if(nomsJoueurs1.getText()!=null) {
                nom = "elein";
                nom = nomsJoueurs1.getText();
            }
        }else if (playerNumber==2){
            if(nomsJoueurs2.getText()!=null) {
                nom = "guybush";
                nom = nomsJoueurs2.getText();
            }
        }else if (playerNumber==3 ){
            if(nomsJoueurs3.getText()!=null) {
                nom = "elpatron";
                nom = nomsJoueurs3.getText();
            }
        }else if (playerNumber==4 ){
            if (nomsJoueurs4.getText()!=null) {
                nom = "sylia";
                nom = nomsJoueurs4.getText();
            }
        }else if (playerNumber==5 ){
            if(nomsJoueurs5.getText()!=null) {
                nom = "sarah";
                nom = nomsJoueurs5.getText();
            }
        }
        return nom;
    }



}
