package ui;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.Artiste;
import model.Film;
import model.Genre;
import service.GestionAdministrateur;
import service.GestionUtilisateur;



/**
 * Controleur JavaFX de la fenêtre d'administration.
 *
 * @author Alexandre De Brito
 *
 */
public class AdministrationControleur {
  
  private GestionAdministrateur foncAdmin;
  
  private GestionUtilisateur foncUtilisateur;
  
  @FXML
  private CheckBox checkBoxLocationFilm;
  
  @FXML
  private TextField entreeAffiche;
  
  @FXML
  private TextField entreeAnneeFilm;
  
  @FXML
  private TextField entreeNationaliteArtiste;
  
  @FXML
  private TextField entreeNomArtiste;
  
  @FXML
  private TextField entreeNomPrenomRealisateur;
  
  @FXML
  private TextField entreePrenomArtiste;
  
  @FXML
  private TextField entreeTitreFilm;
  
  @FXML
  private Label labelListeArtistes;
  
  @FXML
  private Label labelListeFilms;
  
  @FXML
  private ListView<String> listeArtistes;
  
  @FXML
  private ChoiceBox<String> listeChoixAgeLimite;
  
  @FXML
  private ListView<String> listeFilms;
  
  @FXML
  private ListView<String> listeGenresFilm;
  
  @FXML
  private ListView<String> listeTousGenres;
  
  @FXML
  void actionBoutonAfficherArtistesActeurs(ActionEvent event) {
    this.listeArtistes.getItems().clear();
    this.labelListeArtistes.setText("Les acteurs");
    for (Artiste real : this.foncAdmin.foncArtiste.getSetActeur()) {
      this.listeArtistes.getItems().add(real.toString());
    }
  }
  
  @FXML
  void actionBoutonAfficherArtistesRealisateurs(ActionEvent event) {
    this.listeArtistes.getItems().clear();
    this.labelListeArtistes.setText("Les realisateurs");
    for (Artiste real : this.foncAdmin.foncArtiste.getSetRealisateur()) {
      this.listeArtistes.getItems().add(real.toString());
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
      System.out.println("Erreur de sélection");
      return;
    }
    
    // Séparer la nationalité, le nom et le prénom
    int premierTiret = artisteSelectionne.indexOf("-");
    int dernierTiret = artisteSelectionne.lastIndexOf("-");
    
    if (premierTiret == dernierTiret || premierTiret == -1
        || dernierTiret == -1) {
      System.out.println("Erreur de sélection");
      return;
    }
    
    String prenom = artisteSelectionne.substring(0, premierTiret).trim();
    String nom =
        artisteSelectionne.substring(premierTiret + 1, dernierTiret).trim();
    
    Artiste acteur = this.foncAdmin.foncArtiste.getActeur(nom, prenom);
    if (acteur == null) {
      return;
    }
    
    for (Film film : acteur.getFilms()) {
      if (film.getSetActeur().contains(acteur)) {
        this.listeFilms.getItems().add(film.toString());
      }
    }
    
  }
  
  @FXML
  void actionBoutonAfficherFilmsDuRealisateur(ActionEvent event) {
    
    this.listeFilms.getItems().clear();
    
    this.labelListeFilms.setText("Film du réalisateur sélectionné");
    if (this.entreeNomPrenomRealisateur.getText() == null
        || this.entreeNomPrenomRealisateur.getText().isEmpty()) {
      System.out.println("Erreur de sélection ou format incorrect.");
      return;
    }
    int premierTiret = this.entreeNomPrenomRealisateur.getText().indexOf("-");
    
    String prenom = this.entreeNomPrenomRealisateur.getText()
        .substring(0, premierTiret).trim();
    String nom = this.entreeNomPrenomRealisateur.getText()
        .substring(premierTiret + 1).trim();
    
    
    
    for (Film film : this.foncAdmin.foncFilm.getSetFilm()) {
      if (film.getRealisateur()
          .equals(this.foncAdmin.foncArtiste.getRealisateur(nom, prenom))) {
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
      System.out.println("Erreur de sélection ou format incorrect.");
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
        this.foncAdmin.foncArtiste.getRealisateur(nom, prenom);
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
  void actionBoutonAfficherTousActeursFilm(ActionEvent event) {
    
    this.labelListeArtistes.setText("Liste d'acteur pour le film selectionné");
    
    if (this.listeFilms.getSelectionModel().getSelectedItem() == null) {
      System.out.println("Erreur de selection de film");
      return;
    }
    
    String titre = this.listeFilms.getSelectionModel().getSelectedItem();
    
    Film film = this.foncAdmin.foncFilm.getFilm(titre);
    
    this.listeArtistes.getItems().clear();
    for (Artiste acteur : film.getSetActeur()) {
      this.listeArtistes.getItems().add(acteur.toString());
    }
    
  }
  
  @FXML
  void actionBoutonAfficherTousArtistes(ActionEvent event) {
    this.listeArtistes.getItems().clear();
    this.labelListeArtistes.setText("Les artistes");
    for (Artiste artiste : this.foncAdmin.foncArtiste.getSetArtiste()) {
      this.listeArtistes.getItems().add(artiste.toString());
    }
  }
  
  
  @FXML
  void actionBoutonAjouterActeurFilm(ActionEvent event) {
    
    String artisteSelectionne =
        this.listeArtistes.getSelectionModel().getSelectedItem();
    
    if (artisteSelectionne == null || artisteSelectionne.isEmpty()
        || !artisteSelectionne.contains("-")) {
      System.out.println("Erreur de sélection.");
      return;
    }
    
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
    
    Artiste artiste = this.foncAdmin.foncArtiste.getArtiste(nom, prenom);
    
    String titre = this.entreeTitreFilm.getText();
    
    Film film = this.foncAdmin.foncFilm.getFilm(titre);
    
    if (artiste == null || film == null) {
      System.out.println("Ajout d'acteur échouée");
      return;
    }
    
    film.setActeur(artiste);
    artiste.addFilm(film);
    
    if (this.foncAdmin.foncArtiste.addActeur(artiste)) {
      System.out.println("Ajout d'acteur réussie");
      return;
    }
       
  }
  
  @FXML
  void actionBoutonAjouterGenreFilm(ActionEvent event) {
    String genreSelectionne =
        this.listeTousGenres.getSelectionModel().getSelectedItem();
    String filmSelectionne =
        this.listeFilms.getSelectionModel().getSelectedItem();
    
    if (genreSelectionne == null || filmSelectionne == null) {
      System.out.println("Genre ou film pas selectionné");
      return;
    }
    
    if (this.foncAdmin.ajouterGenres(
        this.foncAdmin.foncFilm.getFilm(filmSelectionne),
        Genre.valueOf(genreSelectionne))) {
      System.out.println("Ajout de genre réussie");
      this.listeGenresFilm.getItems().clear();
      for (Genre genre : this.foncAdmin.foncFilm.getFilm(filmSelectionne)
          .getSetGenre()) {
        this.listeGenresFilm.getItems().add(genre.toString());
      }
      return;
    }
    System.out.println("Ajout de genre échoué");
  }
  
  @FXML
  void actionBoutonChercherArtiste(ActionEvent event) {
    this.listeArtistes.getItems().clear();
    this.labelListeArtistes.setText("Recherche d'un artiste");
    // Boucle if pour éviter le nullPointerException à cause du toString si
    // jamais getArtiste renvoie null
    if (this.entreeNomArtiste.getText().isEmpty()
        || this.entreePrenomArtiste.getText().isEmpty()) {
      System.out.println("Entre nom ou prenom artiste est vide");
      return;
    }
    
    if (this.foncAdmin.foncArtiste.getArtiste(
        this.entreeNomArtiste.getText().trim(),
        this.entreePrenomArtiste.getText().trim()) != null) {
      this.listeArtistes.getItems()
          .add((this.foncAdmin.foncArtiste.getArtiste(
              this.entreeNomArtiste.getText().trim(),
              this.entreePrenomArtiste.getText().trim())).toString());
      this.entreeNationaliteArtiste
          .setText(
              this.foncAdmin.foncArtiste
                  .getArtiste(this.entreeNomArtiste.getText().trim(),
                      this.entreePrenomArtiste.getText().trim())
                  .getNationalite());
    }
  }
  
  @FXML
  void actionBoutonChercherFilm(ActionEvent event) {
    this.listeFilms.getItems().clear();
    this.listeGenresFilm.getItems().clear();
    this.checkBoxLocationFilm.setSelected(false);
    this.listeChoixAgeLimite.setValue(null);
    this.entreeAnneeFilm.setText(null);
    this.entreeNomPrenomRealisateur.setText(null);
    this.entreeAffiche.setText(null);
    
    this.labelListeFilms.setText("Recherche de film");
    if (this.foncAdmin.foncFilm
        .getFilm(this.entreeTitreFilm.getText()) != null) {
      this.listeFilms.getItems().add((this.foncAdmin.foncFilm
          .getFilm(this.entreeTitreFilm.getText()).toString()));
      this.entreeNomPrenomRealisateur.setText(
          this.foncAdmin.foncFilm.getFilm(this.entreeTitreFilm.getText())
              .getRealisateur().getNom() + "-"
              + this.foncAdmin.foncFilm.getFilm(this.entreeTitreFilm.getText())
                  .getRealisateur().getPrenom());
      this.entreeAnneeFilm.setText(String.valueOf(this.foncAdmin.foncFilm
          .getFilm(this.entreeTitreFilm.getText()).getAnneeReal()));
      this.checkBoxLocationFilm.setSelected(this.foncAdmin.foncFilm
          .getFilm(this.entreeTitreFilm.getText()).getLocation());
      this.listeChoixAgeLimite.setValue(String.valueOf(this.foncAdmin.foncFilm
          .getFilm(this.entreeTitreFilm.getText()).getAgeMin()));
      this.listeGenresFilm.getItems().clear();
      for (Genre genre : this.foncAdmin.foncFilm
          .getFilm(this.entreeTitreFilm.getText()).getSetGenre()) {
        this.listeGenresFilm.getItems().add(genre.toString());
      }
      this.entreeAffiche.setText(this.foncAdmin.foncFilm
          .getFilm(this.entreeTitreFilm.getText()).getAffiche());
    } else {
      System.out.println("Recherche de film : échouée");
    }
  }
  
  @FXML
  void actionBoutonChoisirArtisteSelectionneRealisateur(ActionEvent event) {
    String artisteSelectionne =
        this.listeArtistes.getSelectionModel().getSelectedItem();
    
    if (artisteSelectionne == null || artisteSelectionne.isEmpty()
        || !artisteSelectionne.contains("-")) {
      System.out.println("Erreur de sélection.");
      return;
    }
    
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
    
    this.entreeNomPrenomRealisateur.setText(prenom + "-" + nom);
  }
  
  @FXML
  void actionBoutonEnregistrerArtiste(ActionEvent event) {
    Artiste a =
        this.foncAdmin.creerArtiste(this.entreeNomArtiste.getText().trim(),
            this.entreePrenomArtiste.getText().trim(),
            this.entreeNationaliteArtiste.getText().trim());
    
    
    this.actionBoutonAfficherTousArtistes(event);
    if (a == null) {
      System.out.println("Erreur artiste pas créé");
    } else {
      System.out.println("Artiste créé");
    }
  }
  
  @FXML
  void actionBoutonEnregistrerFilm(ActionEvent event) {
    int anneeFilm;
    int ageLimite;
    try {
      ageLimite = Integer.parseInt(this.listeChoixAgeLimite.getValue());
      anneeFilm = Integer.parseInt(this.entreeAnneeFilm.getText().trim());
    } catch (NumberFormatException e) {
      System.out
          .println("Format annee incorrect ou age limite pas selectionne");
      return;
    }
    
    String artisteSelectionne = this.entreeNomPrenomRealisateur.getText();
    
    int tiret = artisteSelectionne.indexOf("-");
    
    if (tiret == -1) {
      System.out.println("Erreur de sélection de réalisateur");
      return;
    }
    
    String prenom = artisteSelectionne.substring(0, tiret).trim();
    String nom = artisteSelectionne.substring(tiret + 1).trim();
    
    boolean location = this.checkBoxLocationFilm.isSelected();
    
    Film f = this.foncAdmin.creerFilm(this.entreeTitreFilm.getText().trim(),
        this.foncAdmin.foncArtiste.getArtiste(nom, prenom), anneeFilm,
        ageLimite, location);
    
    if (f == null) {
      System.out.println("Création de film échouée");
      return;
    }
    
    System.out.println("Création de film réussie");
    this.labelListeFilms
        .setText("Film du réalisateur qui vient d'enregister un film");
    this.listeFilms.getItems().clear();
    for (Film film : this.foncAdmin.foncArtiste.getArtiste(nom, prenom)
        .getFilms()) {
      this.listeFilms.getItems().add(film.toString());
    }
  }
  
  @FXML
  void actionBoutonNouveauArtiste(ActionEvent event) {
    this.entreePrenomArtiste.clear();
    this.entreeNomArtiste.clear();
    this.entreeNationaliteArtiste.clear();
  }
  
  @FXML
  void actionBoutonNouveauFilm(ActionEvent event) {
    this.entreeNomPrenomRealisateur.clear();
    this.entreeTitreFilm.clear();
    this.listeChoixAgeLimite.setValue(null);
    this.entreeAnneeFilm.clear();
    this.checkBoxLocationFilm.setSelected(false);
    this.listeGenresFilm.getItems().clear();
    this.entreeAffiche.setText(null);
  }
  
  
  
  @FXML
  void actionBoutonParcourirAffiche(ActionEvent event) {
    
    String filmSelectionne =
        this.listeFilms.getSelectionModel().getSelectedItem();
    
    if (filmSelectionne == null || filmSelectionne.isEmpty()) {
      System.out.println("Erreur de sélection.");
      return;
    }
    
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Sélectionner l'affiche du film");
    
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(
        "Images", "*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif"));
    
    File fichierAffiche =
        fileChooser.showOpenDialog(this.listeFilms.getScene().getWindow());
    
    if (fichierAffiche != null) {
      Film film = this.foncAdmin.foncFilm.getFilm(filmSelectionne);
      film.setAffiche(fichierAffiche.getAbsolutePath());
      System.out.println(
          "Affiche sélectionnée : " + fichierAffiche.getAbsolutePath());
      this.entreeAffiche.setText(film.getAffiche());
    } else {
      System.out.println("Aucun fichier sélectionné.");
    }
  }
  
  @FXML
  void actionBoutonSupprimerArtiste(ActionEvent event) {
    String artisteSelectionne =
        this.listeArtistes.getSelectionModel().getSelectedItem();
    
    if (artisteSelectionne == null || artisteSelectionne.isEmpty()
        || !artisteSelectionne.contains("-")) {
      System.out.println("Erreur de sélection.");
      return;
    }
    
    // Séparer la nationalité, le nom et le prénom
    int premierTiret = artisteSelectionne.indexOf("-");
    int dernierTiret = artisteSelectionne.lastIndexOf("-");
    
    if (premierTiret == dernierTiret || premierTiret == -1
        || dernierTiret == -1) {
      System.out.println("Erreur de selection.");
      return;
    }
    
    String prenom = artisteSelectionne.substring(0, premierTiret).trim();
    String nom =
        artisteSelectionne.substring(premierTiret + 1, dernierTiret).trim();
    
    boolean suppression = this.foncAdmin
        .supprimerArtiste(this.foncAdmin.foncArtiste.getArtiste(nom, prenom));
    this.actionBoutonAfficherTousArtistes(event);
    
    if (suppression) {
      System.out.println("Suppresion artiste reussie");
    } else {
      System.out.println("Suppresion artiste echouée");
    }
    
  }
  
  
  @FXML
  void actionBoutonSupprimerFilm(ActionEvent event) {
    
    String filmSelectionne =
        this.listeFilms.getSelectionModel().getSelectedItem();
    
    if (filmSelectionne == null || filmSelectionne.isEmpty()) {
      System.out.println("Erreur de sélection.");
      return;
    }
    
    Film film = this.foncAdmin.foncFilm.getFilm(filmSelectionne);
    
    if (this.foncAdmin.supprimerFilm(film)) {
      this.listeFilms.getItems().clear();
      System.out.println("Film supprimer succès");
      this.labelListeFilms.setText("");
      return;
    }
    
    
  }
  
  @FXML
  void actionMenuApropos(ActionEvent event) {
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
    alert.setTitle("À propos");
    alert.setHeaderText("À propos de l'application");
    alert.setContentText(
        "Cette application permet de gérer des artistes et des films.\n"
            + "Version : 1.0\n" + "Développée par : Jaune");
    
    alert.showAndWait();
  }
  
  
  @FXML
  void actionMenuCharger(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir un fichier à charger");
    fileChooser.getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("Fichiers sérialisés", "*.ser"));
    
    // Récupérer la fenêtre principale à partir de l'événement
    Window window =
        ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
    
    // Ouvrir la boîte de dialogue pour sélectionner un fichier
    File selectedFile = fileChooser.showOpenDialog(window);
    
    if (selectedFile != null) {
      try {
        this.foncAdmin.chargerDonnees(selectedFile.getAbsolutePath());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Chargement réussi");
        alert.setHeaderText(null);
        alert.setContentText("Les données ont été chargées avec succès !");
        alert.showAndWait();
      } catch (IOException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de chargement");
        alert.setHeaderText("Impossible de charger les données.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
      }
    } else {
      System.out.println("Chargement annulé par l'utilisateur.");
    }
  }
  
  
  
  @FXML
  void actionMenuQuitter(ActionEvent event) {
    System.exit(0);
  }
  
  @FXML
  void actionMenuSauvegarder(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir un fichier de sauvegarde");
    fileChooser.getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("Fichiers sérialisés", "*.ser"));
    
    // Récupérer la fenêtre principale à partir de l'événement
    Window window =
        ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
    
    // Ouvrir la boîte de dialogue pour choisir ou créer un fichier
    File selectedFile = fileChooser.showSaveDialog(window);
    
    if (selectedFile != null) {
      try {
        // Vérifie si l'extension est déjà ".ser", sinon ajoute-la
        if (!selectedFile.getName().endsWith(".ser")) {
          selectedFile = new File(selectedFile.getAbsolutePath() + ".ser");
        }
        this.foncAdmin.sauvegarderDonnees(selectedFile.getAbsolutePath());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sauvegarde réussie");
        alert.setHeaderText(null);
        alert.setContentText("Les données ont été sauvegardées avec succès !");
        alert.showAndWait();
      } catch (IOException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de sauvegarde");
        alert.setHeaderText("Impossible de sauvegarder les données.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
      }
    } else {
      System.out.println("Sauvegarde annulée par l'utilisateur.");
    }
  }
  
  
  @FXML
  void actionListeSelectionArtiste(MouseEvent event) {
    String artisteSelectionne =
        this.listeArtistes.getSelectionModel().getSelectedItem();
    
    if (artisteSelectionne == null || artisteSelectionne.isEmpty()
        || !artisteSelectionne.contains("-")) {
      System.out.println("Erreur de sélection ou format incorrect.");
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
  void actionListeSelectionFilm(MouseEvent event) {
    String filmSelectionne =
        this.listeFilms.getSelectionModel().getSelectedItem();
    
    if (filmSelectionne == null || filmSelectionne.isEmpty()) {
      System.out.println("Erreur de sélection.");
      return;
    }
    
    Film film = this.foncAdmin.foncFilm.getFilm(filmSelectionne);
    String nom = film.getRealisateur().getNom().trim();
    String prenom = film.getRealisateur().getPrenom().trim();
    String annee = String.valueOf(film.getAnneeReal());
    
    this.entreeAffiche.setText(film.getAffiche());
    this.entreeNomPrenomRealisateur.setText(prenom + "-" + nom);
    this.entreeAnneeFilm.setText(annee);
    this.entreeTitreFilm.setText(film.getTitre().trim());
    this.checkBoxLocationFilm.setSelected(film.getLocation());
    this.listeChoixAgeLimite.setValue(String.valueOf(film.getAgeMin()));
    this.listeGenresFilm.getItems().clear();
    for (Genre genre : film.getSetGenre()) {
      this.listeGenresFilm.getItems().add(genre.toString());
    }
  }
  
  @FXML
  void actionCheckBoxChangerLocationFilm() {
    String filmSelectionne =
        this.listeFilms.getSelectionModel().getSelectedItem();
    
    if (filmSelectionne == null || filmSelectionne.isEmpty()) {
      System.out.println("Erreur de sélection.");
      return;
    }
    
    Film film = this.foncAdmin.foncFilm.getFilm(filmSelectionne);
    
    film.setLocation(this.checkBoxLocationFilm.isSelected());
    
    if (this.checkBoxLocationFilm.isSelected()) {
      System.out.println("Film est désormais louable");
    } else {
      System.out.println("Film n'est désormais plus louable");
    }
  }
  
  @FXML
  void initialize() {
    
    this.listeChoixAgeLimite.getItems().add("0");
    this.listeChoixAgeLimite.getItems().add("12");
    this.listeChoixAgeLimite.getItems().add("16");
    this.listeChoixAgeLimite.getItems().add("18");
    
    for (Genre genre : Genre.values()) {
      this.listeTousGenres.getItems().add(genre.toString());
    }
  }
  
  /**
   * Définit l'instance de fonctions Administrateur.
   */
  public void setFonctions(GestionAdministrateur foncAdmin,
      GestionUtilisateur foncUtilisateur) {
    this.foncAdmin = foncAdmin;
    this.foncUtilisateur = foncUtilisateur;
  }
  
}
