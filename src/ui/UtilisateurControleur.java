package ui;

import exception.LocationException;
import exception.NonConnecteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.Artiste;
import model.Evaluation;
import model.Film;
import model.Genre;
import model.InformationPersonnelle;
import model.Utilisateur;
import service.GestionUtilisateur;

/**
 * Controleur JavaFX de la fenêtre utilisateur.
 *
 * @author Alexandre De Brito
 *
 */
public class UtilisateurControleur {
  
  GestionUtilisateur foncUtilisateur;
  
  Film filmSelectionne;
  
  @FXML
  private CheckBox checkFilmLouable;
  
  @FXML
  private TextField entreeAdresseUtilisateur;
  
  @FXML
  private TextField entreeAgeLimiteFilm;
  
  @FXML
  private TextField entreeAgeUtilisateur;
  
  @FXML
  private TextField entreeAnneeFilm;
  
  @FXML
  private TextField entreeAuteurEvaluation;
  
  @FXML
  private TextField entreeEvaluationMoyenne;
  
  @FXML
  private TextField entreeGenresFilm;
  
  @FXML
  private TextField entreeMotDePasseUtilisateur;
  
  @FXML
  private TextField entreeNationaliteArtiste;
  
  @FXML
  private TextField entreeNomArtiste;
  
  @FXML
  private TextField entreeNomPrenomRealisateurFilm;
  
  @FXML
  private TextField entreeNomUtilisateur;
  
  @FXML
  private TextField entreePrenomArtiste;
  
  @FXML
  private TextField entreePrenomUtilisateur;
  
  @FXML
  private TextField entreePseudoUtilisateur;
  
  @FXML
  private TextField entreeTitreFilm;
  
  @FXML
  private Label labelListeFilms;
  
  @FXML
  private Label labelListeArtistes;
  
  @FXML
  private ListView<String> listeArtistes;
  
  @FXML
  private ListView<String> listeEvaluations;
  
  @FXML
  private ListView<String> listeFilms;
  
  @FXML
  private ListView<String> listeFilmsEnLocation;
  
  @FXML
  private ListView<String> listeGenresFilm;
  
  @FXML
  private ChoiceBox<Integer> listeNoteEvaluation;
  
  @FXML
  private TextArea texteCommentaire;
  
  @FXML
  private StackPane paneAffiche;
  
  @FXML
  void actionBoutonAfficherActeursFilmSelectionne(ActionEvent event) {
    this.listeArtistes.getItems().clear();
    this.labelListeArtistes.setText("Liste d'acteur pour le film selectionné");
    
    if (this.listeFilms.getSelectionModel().getSelectedItem() == null) {
      System.out.println("Erreur de selection de film");
      return;
    }
    
    String titre = this.listeFilms.getSelectionModel().getSelectedItem();
    
    Film film = this.foncUtilisateur.foncFilm.getFilm(titre);
    
    this.listeArtistes.getItems().clear();
    for (Artiste acteur : film.getSetActeur()) {
      this.listeArtistes.getItems().add(acteur.toString());
    }
  }
  
  @FXML
  void actionBoutonAfficherArtistesActeurs(ActionEvent event) {
    this.listeArtistes.getItems().clear();
    this.labelListeArtistes.setText("Les acteurs");
    for (Artiste real : this.foncUtilisateur.foncArtiste.getSetActeur()) {
      this.listeArtistes.getItems().add(real.toString());
    }
  }
  
  @FXML
  void actionBoutonAfficherArtistesRealisateurs(ActionEvent event) {
    this.listeArtistes.getItems().clear();
    this.labelListeArtistes.setText("Les realisateurs");
    for (Artiste real : this.foncUtilisateur.foncArtiste.getSetRealisateur()) {
      this.listeArtistes.getItems().add(real.toString());
    }
  }
  
  @FXML
  void actionBoutonAfficherFilmLoue(ActionEvent event) {
    String filmSelectionne =
        this.listeFilmsEnLocation.getSelectionModel().getSelectedItem();
    
    if (filmSelectionne == null || filmSelectionne.isEmpty()) {
      System.out.println("Erreur de sélection.");
      return;
    }
    
    Film film = this.foncUtilisateur.foncFilm.getFilm(filmSelectionne);
    String nom = film.getRealisateur().getNom().trim();
    String prenom = film.getRealisateur().getPrenom().trim();
    String annee = String.valueOf(film.getAnneeReal());
    
    this.entreeNomPrenomRealisateurFilm.setText(prenom + "-" + nom);
    this.entreeAnneeFilm.setText(annee);
    this.entreeTitreFilm.setText(film.getTitre().trim());
    this.checkFilmLouable.setSelected(film.getLocation());
    this.entreeAgeLimiteFilm.setText(String.valueOf(film.getAgeMin()));
    String listeGenre = "";
    for (Genre genre : film.getSetGenre()) {
      listeGenre += genre.toString() + " ";
    }
    this.entreeGenresFilm.setText(listeGenre);
    
    this.listeEvaluations.getItems().clear();
    
    this.entreeEvaluationMoyenne.setText(String.valueOf(film.getNoteMoyenne()));
    for (Evaluation eval : film.getSetEvaluations()) {
      this.listeEvaluations.getItems().add(eval.toString());
    }
    
    this.labelListeFilms.setText("Détail du film loué selectionné");
    this.listeFilms.getItems().clear();
    this.listeFilms.getItems().add(film.toString());
    
    Image image = new Image("file:" + film.getAffiche());
    
    ImageView imageView = new ImageView(image);
    
    imageView.setFitWidth(150);
    imageView.setPreserveRatio(true);
    
    paneAffiche.getChildren().clear();
    paneAffiche.getChildren().add(imageView);
    
  }
  
  @FXML
  void actionBoutonAfficherFilmRealisateurSelectionne(ActionEvent event) {
    this.listeFilms.getItems().clear();
    this.labelListeFilms.setText("Films du réalisateur selectionné");
    
    String artisteSelectionne =
        this.listeArtistes.getSelectionModel().getSelectedItem();
    
    if (artisteSelectionne == null || artisteSelectionne.isEmpty()
        || !artisteSelectionne.contains("-")) {
      System.out.println("Erreur de sélection ");
      return;
    }
    
    // Séparer la nationalité, le nom et le prénom
    int premierTiret = artisteSelectionne.indexOf("-");
    int dernierTiret = artisteSelectionne.lastIndexOf("-");
    
    if (premierTiret == dernierTiret || premierTiret == -1
        || dernierTiret == -1) {
      System.out
          .println("Erreur : Format inattendu pour 'artisteSelectionne'.");
      return;
    }
    
    String prenom = artisteSelectionne.substring(0, premierTiret).trim();
    String nom =
        artisteSelectionne.substring(premierTiret + 1, dernierTiret).trim();
    
    
    Artiste realisateur =
        this.foncUtilisateur.foncArtiste.getRealisateur(nom, prenom);
    if (realisateur == null) {
      return;
    }
    
    this.listeFilms.getItems().clear();
    
    for (Film film : realisateur.getFilms()) {
      if (film.getRealisateur() == realisateur) {
        this.listeFilms.getItems().add(film.toString());
      }
    }
  }
  
  @FXML
  void actionBoutonAfficherFilmsActeurSelectionne(ActionEvent event) {
    this.listeFilms.getItems().clear();
    this.labelListeFilms.setText("Films de l'acteur selectionné");
    
    String artisteSelectionne =
        this.listeArtistes.getSelectionModel().getSelectedItem();
    
    if (artisteSelectionne == null || artisteSelectionne.isEmpty()
        || !artisteSelectionne.contains("-")) {
      System.out.println("Erreur de sélection ");
      return;
    }
    
    // Séparer la nationalité, le nom et le prénom
    int premierTiret = artisteSelectionne.indexOf("-");
    int dernierTiret = artisteSelectionne.lastIndexOf("-");
    
    if (premierTiret == dernierTiret || premierTiret == -1
        || dernierTiret == -1) {
      System.out
          .println("Erreur : Format inattendu pour 'artisteSelectionne'.");
      return;
    }
    
    String prenom = artisteSelectionne.substring(0, premierTiret).trim();
    String nom =
        artisteSelectionne.substring(premierTiret + 1, dernierTiret).trim();
    
    Artiste acteur = this.foncUtilisateur.foncArtiste.getActeur(nom, prenom);
    if (acteur == null) {
      return;
    }
    this.listeFilms.getItems().clear();
    
    for (Film film : acteur.getFilms()) {
      if (film.getSetActeur().contains(acteur)) {
        this.listeFilms.getItems().add(film.toString());
      }
    }
  }
  
  @FXML
  void actionBoutonAfficherFilmsGenre(ActionEvent event) {
    this.listeFilms.getItems().clear();
    this.labelListeFilms
        .setText("Liste des films qui contiennent le genre sélectionné");
    
    Genre genre = Genre
        .valueOf(this.listeGenresFilm.getSelectionModel().getSelectedItem());
    
    for (Film film : this.foncUtilisateur.foncFilm.getSetFilm()) {
      if (film.getSetGenre().contains(genre)) {
        this.listeFilms.getItems().add(film.toString());
      }
    }
  }
  
  @FXML
  void actionBoutonAfficherFilmsRealisateurSelectionne(ActionEvent event) {
    this.listeFilms.getItems().clear();
    this.labelListeFilms.setText("Films du réalisateur selectionné");
    
    String artisteSelectionne =
        this.listeArtistes.getSelectionModel().getSelectedItem();
    
    if (artisteSelectionne == null || artisteSelectionne.isEmpty()
        || !artisteSelectionne.contains("-")) {
      System.out.println("Erreur de sélection ");
      return;
    }
    
    // Séparer la nationalité, le nom et le prénom
    int premierTiret = artisteSelectionne.indexOf("-");
    int dernierTiret = artisteSelectionne.lastIndexOf("-");
    
    if (premierTiret == dernierTiret || premierTiret == -1
        || dernierTiret == -1) {
      System.out
          .println("Erreur : Format inattendu pour 'artisteSelectionne'.");
      return;
    }
    
    String prenom = artisteSelectionne.substring(0, premierTiret).trim();
    String nom =
        artisteSelectionne.substring(premierTiret + 1, dernierTiret).trim();
    
    
    Artiste realisateur =
        this.foncUtilisateur.foncArtiste.getRealisateur(nom, prenom);
    if (realisateur == null) {
      return;
    }
    
    this.listeFilms.getItems().clear();
    
    for (Film film : realisateur.getFilms()) {
      if (film.getRealisateur() == realisateur) {
        this.listeFilms.getItems().add(film.toString());
      }
    }
  }
  
  @FXML
  void actionBoutonAfficherMonEvaluation(ActionEvent event) {
    
    if (this.filmSelectionne == null) {
      System.out.println("Erreur de sélection ou de recherche.");
      return;
    }
    
    if (this.foncUtilisateur.getUtilisateurConnecte() == null) {
      System.out.println("Utilisateur pas connecté.");
      return;
    }
    
    this.listeEvaluations.getItems().clear();
    
    for (Evaluation eval : this.filmSelectionne.getSetEvaluations()) {
      if (eval.getUtilisateur().toString()
          .equals(this.foncUtilisateur.getUtilisateurConnecte().toString())) {
        this.listeEvaluations.getItems().add(eval.toString());
        this.listeNoteEvaluation.setValue(eval.getNote());
        this.texteCommentaire.setText(eval.getCommentaire());
        this.entreeAuteurEvaluation.setText(eval.getUtilisateur().toString());
      }
    }
  }
  
  @FXML
  void actionBoutonAfficherTousArtistes(ActionEvent event) {
    this.listeArtistes.getItems().clear();
    this.labelListeArtistes.setText("Les artistes");
    for (Artiste artiste : this.foncUtilisateur.foncArtiste.getSetArtiste()) {
      this.listeArtistes.getItems().add(artiste.toString());
    }
  }
  
  @FXML
  void actionBoutonAfficherTousFilms(ActionEvent event) {
    this.listeFilms.getItems().clear();
    this.labelListeFilms.setText("Liste de tous les films");
    
    for (Film film : this.foncUtilisateur.foncFilm.getSetFilm()) {
      this.listeFilms.getItems().add(film.toString());
    }
  }
  
  @FXML
  void actionBoutonChercherActeur(ActionEvent event) {
    this.listeArtistes.getItems().clear();
    this.labelListeArtistes.setText("Recherche d'un acteur");
    // Boucle if pour éviter le nullPointerException à cause du toString si
    // jamais getActeur renvoie null
    if (this.foncUtilisateur.foncArtiste.getActeur(
        this.entreeNomArtiste.getText().trim(),
        this.entreePrenomArtiste.getText().trim()) != null) {
      this.listeArtistes.getItems()
          .add((this.foncUtilisateur.foncArtiste.getActeur(
              this.entreeNomArtiste.getText().trim(),
              this.entreePrenomArtiste.getText().trim())).toString());
      this.entreeNationaliteArtiste
          .setText(
              this.foncUtilisateur.foncArtiste
                  .getActeur(this.entreeNomArtiste.getText().trim(),
                      this.entreePrenomArtiste.getText().trim())
                  .getNationalite());
    }
  }
  
  @FXML
  void actionBoutonChercherFilm(ActionEvent event) {
    this.listeFilms.getItems().clear();
    this.listeEvaluations.getItems().clear();
    this.entreeGenresFilm.setText(null);
    this.checkFilmLouable.setSelected(false);
    this.entreeAgeLimiteFilm.setText(null);
    this.entreeAnneeFilm.setText(null);
    this.entreeNomPrenomRealisateurFilm.setText(null);
    
    this.entreeEvaluationMoyenne.setText(null);
    this.listeEvaluations.getItems().clear();
    this.entreeAuteurEvaluation.setText(null);
    this.listeNoteEvaluation.setValue(null);
    this.texteCommentaire.setText(null);
    
    this.filmSelectionne = null;
    
    paneAffiche.getChildren().clear();
    
    this.labelListeFilms.setText("Recherche de film");
    if (this.foncUtilisateur.foncFilm
        .getFilm(this.entreeTitreFilm.getText()) != null) {
      
      Image image = new Image("file:" + this.foncUtilisateur.foncFilm
          .getFilm(this.entreeTitreFilm.getText()).getAffiche());
      
      ImageView imageView = new ImageView(image);
      
      imageView.setFitWidth(150);
      imageView.setPreserveRatio(true);
      
      paneAffiche.getChildren().clear();
      paneAffiche.getChildren().add(imageView);
      
      this.filmSelectionne =
          this.foncUtilisateur.foncFilm.getFilm(this.entreeTitreFilm.getText());
      
      this.listeFilms.getItems().add((this.foncUtilisateur.foncFilm
          .getFilm(this.entreeTitreFilm.getText()).toString()));
      this.entreeNomPrenomRealisateurFilm.setText(this.foncUtilisateur.foncFilm
          .getFilm(this.entreeTitreFilm.getText()).getRealisateur().getNom()
          + "-"
          + this.foncUtilisateur.foncFilm
              .getFilm(this.entreeTitreFilm.getText()).getRealisateur()
              .getPrenom());
      this.entreeAnneeFilm.setText(String.valueOf(this.foncUtilisateur.foncFilm
          .getFilm(this.entreeTitreFilm.getText()).getAnneeReal()));
      this.checkFilmLouable.setSelected(this.foncUtilisateur.foncFilm
          .getFilm(this.entreeTitreFilm.getText()).getLocation());
      this.entreeAgeLimiteFilm
          .setText(String.valueOf(this.foncUtilisateur.foncFilm
              .getFilm(this.entreeTitreFilm.getText()).getAgeMin()));
      String listeGenre = "";
      for (Genre genre : this.foncUtilisateur.foncFilm
          .getFilm(this.entreeTitreFilm.getText()).getSetGenre()) {
        listeGenre += genre.toString() + " ";
      }
      this.entreeGenresFilm.setText(listeGenre);
      
      this.entreeEvaluationMoyenne
          .setText(String.valueOf(this.foncUtilisateur.foncFilm
              .getFilm(this.entreeTitreFilm.getText()).getNoteMoyenne()));
      for (Evaluation eval : this.foncUtilisateur.foncFilm
          .getFilm(this.entreeTitreFilm.getText()).getSetEvaluations()) {
        this.listeEvaluations.getItems().add(eval.toString());
      }
    } else {
      System.out.println("Recherche de film : échouée");
    }
  }
  
  @FXML
  void actionBoutonChercherRealisateur(ActionEvent event) {
    this.listeArtistes.getItems().clear();
    this.labelListeArtistes.setText("Recherche d'un realisateur");
    // Boucle if pour éviter le nullPointerException à cause du toString si
    // jamais getRealisateur renvoie null
    if (this.foncUtilisateur.foncArtiste.getRealisateur(
        this.entreeNomArtiste.getText(),
        this.entreePrenomArtiste.getText()) != null) {
      this.listeArtistes.getItems()
          .add((this.foncUtilisateur.foncArtiste.getRealisateur(
              this.entreeNomArtiste.getText(),
              this.entreePrenomArtiste.getText())).toString());
      this.entreeNationaliteArtiste.setText(this.foncUtilisateur.foncArtiste
          .getRealisateur(this.entreeNomArtiste.getText(),
              this.entreePrenomArtiste.getText())
          .getNationalite());
    }
  }
  
  @FXML
  void actionBoutonConnexion(ActionEvent event) {
    if (this.entreePseudoUtilisateur.getText().isEmpty()
        || this.entreeMotDePasseUtilisateur.getText().isEmpty()) {
      afficherPopup("ECHEC", "Pseudo ou mot de passe vide");
      return;
    }
    
    for (Utilisateur util : this.foncUtilisateur.getSetUtilisateur()) {
      if (util.getPseudo().equals(this.entreePseudoUtilisateur.getText().trim())
          && util.getMdp()
              .equals(this.entreeMotDePasseUtilisateur.getText().trim())) {
        this.entreeNomUtilisateur.setText(util.getInfo().getNom());
        this.entreePrenomUtilisateur.setText(util.getInfo().getPrenom());
        this.entreeAdresseUtilisateur.setText(util.getInfo().getAdresse());
        this.entreeAgeUtilisateur
            .setText(String.valueOf(util.getInfo().getAge()));
        this.foncUtilisateur.setUtilisateurConnecte(util);
        
        this.listeFilmsEnLocation.getItems().clear();
        for (Film film : util.getLocFilm()) {
          this.listeFilmsEnLocation.getItems().add(film.toString());
        }
        afficherPopup("REUSSITE", "Vous êtes maintenant connecté ! ");
        return;
      }
    }
    
    afficherPopup("ECHEC", "Pseudo et/ou mot de passe ne correspondent pas");
    
  }
  
  @FXML
  void actionBoutonCreerMonEvaluation(ActionEvent event) {
    
    String filmSelectionne =
        this.listeFilms.getSelectionModel().getSelectedItem();
    
    if (filmSelectionne == null || filmSelectionne.isEmpty()) {
      System.out.println("Erreur de sélection du film.");
      return;
    }
    
    Integer note = this.listeNoteEvaluation.getValue();
    String commentaire = this.texteCommentaire.getText();
    
    if (this.listeNoteEvaluation.getValue() == null || commentaire == null
        || commentaire.isEmpty()) {
      System.out.println("Pas de note ou pas de commentaire");
      return;
    }
    
    Film film = this.foncUtilisateur.foncFilm.getFilm(filmSelectionne);
    
    Evaluation eval = new Evaluation(note, commentaire.trim(),
        this.foncUtilisateur.getUtilisateurConnecte(), film);
    
    try {
      this.foncUtilisateur.ajouterEvaluation(film, eval);
    } catch (LocationException | NonConnecteException e) {
      System.out.println(e);
    }
    
    this.listeEvaluations.getItems().clear();
    for (Evaluation evalIt : film.getSetEvaluations()) {
      this.listeEvaluations.getItems().add(evalIt.toString());
    }
    this.entreeEvaluationMoyenne.setText(String.valueOf(film.getNoteMoyenne()));
  }
  
  @FXML
  void actionBoutonDeconnexion(ActionEvent event) {
    try {
      this.foncUtilisateur.deconnexion();
    } catch (NonConnecteException e) {
      afficherPopup("ECHEC", "Aucun utilisateur connecté");
      return;
    }
    
    this.entreeNomUtilisateur.setText("");
    this.entreePrenomUtilisateur.setText("");
    this.entreeAdresseUtilisateur.setText("");
    this.entreeAgeUtilisateur.setText("");
    this.entreePseudoUtilisateur.setText("");
    this.entreeMotDePasseUtilisateur.setText("");
    this.listeFilmsEnLocation.getItems().clear();
  }
  
  @FXML
  void actionBoutonFinLocation(ActionEvent event) {
    if (this.foncUtilisateur.getUtilisateurConnecte() == null) {
      System.out.println("Utilisateur non connectee");
      return;
    }
    
    if (this.foncUtilisateur.getUtilisateurConnecte().getNbLocFilm() >= 3) {
      System.out.println("L'utilisateur connecte a déjà loué 3 films");
    }
    
    String filmSelectionne =
        this.listeFilmsEnLocation.getSelectionModel().getSelectedItem();
    
    if (filmSelectionne == null || filmSelectionne.isEmpty()) {
      System.out.println("Erreur de sélection ");
      return;
    }
    
    Film film = this.foncUtilisateur.foncFilm.getFilm(filmSelectionne);
    
    if (!this.foncUtilisateur.getUtilisateurConnecte().getLocFilm()
        .contains(film)) {
      System.out.println(
          "L'utilisateur connecte ne loue actuellement pas le film selectionné");
      return;
    }
    
    this.foncUtilisateur.getUtilisateurConnecte().removeFilm(film);
    film.decremLoc();
    
    this.listeFilmsEnLocation.getItems().clear();
    for (Film filmIt : this.foncUtilisateur.getUtilisateurConnecte()
        .getLocFilm()) {
      this.listeFilmsEnLocation.getItems().add(filmIt.toString());
    }
  }
  
  @FXML
  void actionBoutonInscription(ActionEvent event) {
    if (this.entreeNomUtilisateur.getText().isEmpty()
        || this.entreePrenomUtilisateur.getText().isEmpty()
        || this.entreeAdresseUtilisateur.getText().isEmpty()
        || this.entreeAgeUtilisateur.getText().isEmpty()) {
      
      // Afficher une popup pour alerter l'utilisateur
      afficherPopup("ECHEC", "Information personnelle pas bien précisé");
      return;
    }
    
    try {
      Integer.parseInt(this.entreeAgeUtilisateur.getText().trim());
    } catch (NumberFormatException e) {
      afficherPopup("ECHEC", "Information personnelle pas bien précisé");
      return;
    }
    
    if (this.entreePseudoUtilisateur.getText().isEmpty()
        || this.entreeMotDePasseUtilisateur.getText().isEmpty()) {
      afficherPopup("ECHEC", "Pseudo ou mot de passe vide.");
      return;
    }
    
    InformationPersonnelle infoPerso =
        new InformationPersonnelle(this.entreeNomUtilisateur.getText().trim(),
            this.entreePrenomUtilisateur.getText().trim(),
            this.entreeAdresseUtilisateur.getText().trim(),
            Integer.parseInt(this.entreeAgeUtilisateur.getText().trim()));
    
    int res = this.foncUtilisateur.inscription(
        this.entreePseudoUtilisateur.getText().trim(),
        this.entreeMotDePasseUtilisateur.getText().trim(), infoPerso);
    if (res == 0) {
      afficherPopup("SUCCES", "Inscription réalisé avec succès.");
    } else if (res == 1) {
      afficherPopup("ECHEC", "Pseudo déjà utilisé.");
    } else if (res == 2) {
      afficherPopup("ECHEC", "Pseudo ou mot de passe vide.");
    }
    
  }
  
  @FXML
  void actionBoutonLouerFilmSelectionne(ActionEvent event) {
    
    String filmSelectionne =
        this.listeFilms.getSelectionModel().getSelectedItem();
    
    if (filmSelectionne == null || filmSelectionne.isEmpty()) {
      System.out.println("Erreur de sélection ");
      return;
    }
    
    Film film = this.foncUtilisateur.foncFilm.getFilm(filmSelectionne);
    
    try {
      this.foncUtilisateur.louerFilm(film);
    } catch (NonConnecteException | LocationException e) {
      System.out.println(e);
      return;
    }
    
    this.listeFilmsEnLocation.getItems().clear();
    for (Film filmIt : this.foncUtilisateur.getUtilisateurConnecte()
        .getLocFilm()) {
      this.listeFilmsEnLocation.getItems().add(filmIt.toString());
    }
    
  }
  
  @FXML
  void actionBoutonModifierMonEvaluation(ActionEvent event) {
    if (this.filmSelectionne == null) {
      System.out.println("Erreur de sélection ou de recherche.");
      return;
    }
    
    if (this.foncUtilisateur.getUtilisateurConnecte() == null) {
      System.out.println("Utilisateur pas connecté.");
      return;
    }
    
    if (this.texteCommentaire.getText() == null
        || this.texteCommentaire.getText().isEmpty()
        || this.listeNoteEvaluation.getValue() == null) {
      System.out.println("Commentaire ou note invalide.");
      return;
    }
    
    this.listeEvaluations.getItems().clear();
    
    for (Evaluation eval : this.filmSelectionne.getSetEvaluations()) {
      if (eval.getUtilisateur().toString()
          .equals(this.foncUtilisateur.getUtilisateurConnecte().toString())) {
        eval.setNote(this.listeNoteEvaluation.getValue());
        eval.setCommentaire(this.texteCommentaire.getText());
        this.listeEvaluations.getItems().add(eval.toString());
        this.filmSelectionne.calculeMoyenne();
        this.entreeEvaluationMoyenne
            .setText(String.valueOf(this.filmSelectionne.getNoteMoyenne()));
      }
    }
    
  }
  
  @FXML
  void actionSelectionArtiste(MouseEvent event) {
    String artisteSelectionne =
        this.listeArtistes.getSelectionModel().getSelectedItem();
    
    if (artisteSelectionne == null || artisteSelectionne.isEmpty()
        || !artisteSelectionne.contains("-")) {
      System.out.println("Erreur de sélection ");
      return;
    }
    
    // Séparer la nationalité, le nom et le prénom
    int premierTiret = artisteSelectionne.indexOf("-");
    int dernierTiret = artisteSelectionne.lastIndexOf("-");
    
    if (premierTiret == dernierTiret || premierTiret == -1
        || dernierTiret == -1) {
      System.out
          .println("Erreur : Format inattendu pour 'artisteSelectionne'.");
      return;
    }
    
    String prenom = artisteSelectionne.substring(0, premierTiret).trim();
    String nom =
        artisteSelectionne.substring(premierTiret + 1, dernierTiret).trim();
    String nationalite = artisteSelectionne.substring(dernierTiret + 1).trim();
    
    // Remplir les champs de texte
    this.entreeNationaliteArtiste.setText(nationalite);
    this.entreeNomArtiste.setText(nom);
    this.entreePrenomArtiste.setText(prenom);
  }
  
  
  @FXML
  void actionSelectionEvaluation(MouseEvent event) {
    if (this.filmSelectionne == null) {
      System.out.println("Erreur de sélection ou de recherche.");
      return;
    }
    
    String evaluationSelectionne =
        this.listeEvaluations.getSelectionModel().getSelectedItem();
    
    if (evaluationSelectionne == null || !evaluationSelectionne.contains(":")) {
      System.out.println("Erreur de sélection'");
      return;
    }
    
    int positionDeuxPoints = evaluationSelectionne.indexOf(":");
    
    String utilisateur =
        evaluationSelectionne.substring(0, positionDeuxPoints).trim();
    
    for (Evaluation eval : this.filmSelectionne.getSetEvaluations()) {
      if (eval.getUtilisateur().getPseudo().equals(utilisateur)) {
        this.entreeAuteurEvaluation.setText(utilisateur);
        this.listeNoteEvaluation.setValue(eval.getNote());
        this.texteCommentaire.setText(eval.getCommentaire());
        return;
      }
    }
  }
  
  @FXML
  void actionSelectionFilm(MouseEvent event) {
    String filmSelectionne =
        this.listeFilms.getSelectionModel().getSelectedItem();
    
    if (filmSelectionne == null || filmSelectionne.isEmpty()) {
      System.out.println("Erreur de sélection ");
      this.filmSelectionne = null;
      return;
    }
    
    Film film = this.foncUtilisateur.foncFilm.getFilm(filmSelectionne);
    this.filmSelectionne = film;
    String nom = film.getRealisateur().getNom().trim();
    String prenom = film.getRealisateur().getPrenom().trim();
    String annee = String.valueOf(film.getAnneeReal());
    
    this.entreeNomPrenomRealisateurFilm.setText(prenom + "-" + nom);
    this.entreeAnneeFilm.setText(annee);
    this.entreeTitreFilm.setText(film.getTitre().trim());
    this.checkFilmLouable.setSelected(film.getLocation());
    this.entreeAgeLimiteFilm.setText(String.valueOf(film.getAgeMin()));
    this.checkFilmLouable.setSelected(film.getLocation());
    String listeGenre = "";
    for (Genre genre : film.getSetGenre()) {
      listeGenre += genre.toString() + " ";
    }
    this.entreeGenresFilm.setText(listeGenre);
    
    this.listeEvaluations.getItems().clear();
    
    this.entreeEvaluationMoyenne.setText(String.valueOf(film.getNoteMoyenne()));
    for (Evaluation eval : film.getSetEvaluations()) {
      this.listeEvaluations.getItems().add(eval.toString());
    }
    
    Image image = new Image("file:" + film.getAffiche());
    
    ImageView imageView = new ImageView(image);
    
    imageView.setFitWidth(150);
    imageView.setPreserveRatio(true);
    
    paneAffiche.getChildren().clear();
    paneAffiche.getChildren().add(imageView);
  }
  
  @FXML
  void initialize() {
    // Mettre ici le code d'initialisation du contenu de la fenêtre
    this.listeNoteEvaluation.getItems().addAll(0, 1, 2, 3, 4, 5);
    
    for (Genre genre : Genre.values()) {
      this.listeGenresFilm.getItems().add(genre.toString());
    }
    
  }
  
  public void setFonctionsUtilisateur(GestionUtilisateur foncUtilisateur) {
    this.foncUtilisateur = foncUtilisateur;
  }
  
  private void afficherPopup(String titre, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(titre);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
  
}
