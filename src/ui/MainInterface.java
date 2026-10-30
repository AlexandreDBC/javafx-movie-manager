package ui;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.GestionAdministrateur;
import service.GestionUtilisateur;

/**
 * Classe qui lance l'interface graphique de l'application.
 *
 * @author Alexandre De Brito
 */
public final class MainInterface extends Application {
  
  /**
   * Affiche la fenêtre de l'utilisateur pour louer des films.
   */
  GestionAdministrateur foncAdmin;
  GestionUtilisateur foncUtilisateur;
  
  @Override
  public void start(Stage primaryStage) {
    // Créer les objets nécessaires pour l'initialisation
    this.foncAdmin = new GestionAdministrateur();
    
    this.foncUtilisateur =
        new GestionUtilisateur(foncAdmin.foncFilm, foncAdmin.foncArtiste);
    
    this.foncAdmin.setFoncUtilisateur(this.foncUtilisateur);
    
    // Lancer la fenêtre d'administration
    this.startFenetreFormation(primaryStage);
    
    // Lancer la fenêtre utilisateur en passant l'instance de
    // GestionUtilisateur
    this.startFenetreEtudiants();
  }
  
  private void startFenetreEtudiants() {
    try {
      URL url = getClass().getResource("utilisateur.fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(url);
      VBox root = (VBox) fxmlLoader.load();
      
      // Passer l'instance de GestionUtilisateur au contrôleur de la vue
      // utilisateur
      UtilisateurControleur utilisateurControleur = fxmlLoader.getController();
      utilisateurControleur.setFonctionsUtilisateur(this.foncUtilisateur);
      
      Scene scene = new Scene(root, 1200, 650);
      Stage stage = new Stage();
      stage.setResizable(true);
      stage.setTitle("Location de films");
      
      stage.setScene(scene);
      stage.show();
      
    } catch (IOException e) {
      System.err
          .println("Erreur au chargement de la fenêtre utilisateur : " + e);
    }
  }
  
  private void startFenetreFormation(Stage primaryStage) {
    try {
      URL url = getClass().getResource("administration.fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(url);
      VBox root = (VBox) fxmlLoader.load();
      
      Scene scene = new Scene(root, 615, 725);
      
      primaryStage.setScene(scene);
      primaryStage.setResizable(true);
      primaryStage.setTitle("Administration des films");
      primaryStage.show();
      
      // Passer l'instance de GestionUtilisateur au contrôleur de la vue
      // utilisateur
      AdministrationControleur administrationControleur =
          fxmlLoader.getController();
      administrationControleur.setFonctions(this.foncAdmin,
          this.foncUtilisateur);
      
      
    } catch (IOException e) {
      System.err
          .println("Erreur au chargement de la fenêtre administration : " + e);
    }
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}
