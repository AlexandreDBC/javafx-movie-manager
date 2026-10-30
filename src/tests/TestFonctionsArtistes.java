package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import model.Artiste;
import model.Film;
import service.GestionArtistes;
import service.GestionFilms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de tests JUnit pour la classe {@link service.GestionArtistes}.
 *
 * <p>
 * Cette classe teste les principales fonctionnalités de la classe
 * GestionArtistes, notamment la gestion des artistes, acteurs et
 * réalisateurs.
 * </p>
 *
 * @author Alexandre De Brito
 * @see service.GestionArtistes
 */
public class TestFonctionsArtistes {
  
  /** Objet GestionArtistes utilisé pour les tests. */
  private GestionArtistes gestionArtistes;
  
  /** Ensemble de films simulé pour les tests. */
  private GestionFilms gestionFilms;
  
  /** Un acteur et un réalisateur de test. */
  private Artiste acteur;
  private Artiste realisateur;
  
  /** Un film associé pour les tests. */
  private Film film;
  
  /**
   * Prépare les objets nécessaires aux tests avant chaque exécution.
   *
   * @throws Exception si une erreur se produit (non applicable ici).
   */
  @BeforeEach
  void setUp() throws Exception {
    gestionFilms = new GestionFilms();
    gestionArtistes = new GestionArtistes(gestionFilms);
    
    acteur = new Artiste("Doe", "John", "Américaine");
    realisateur = new Artiste("Smith", "Jane", "Britannique");
    
    Set<Artiste> acteurs = new HashSet<>();
    acteurs.add(acteur);
    
    film = new Film("The mandalorian", null, 1789, 12);
    gestionFilms.setFilm(film);
    
    gestionArtistes.addActeur(acteur);
    gestionArtistes.addRealisateur(realisateur);
  }
  
  /**
   * Vérifie que la méthode getActeur retourne un acteur existant.
   */
  @Test
  void testGetActeur() {
    Artiste result = gestionArtistes.getActeur("Doe", "John");
    assertEquals(acteur, result,
        "La méthode devrait retourner l'acteur correspondant.");
  }
  
  /**
   * Vérifie que la méthode getActeur retourne null pour un acteur inexistant.
   */
  @Test
  void testGetActeurInexistant() {
    Artiste result = gestionArtistes.getActeur("Inconnu", "Acteur");
    assertNull(result,
        "La méthode devrait retourner null pour un acteur inexistant.");
  }
  
  /**
   * Vérifie que la méthode getRealisateur retourne un réalisateur existant.
   */
  @Test
  void testGetRealisateur() {
    Artiste result = gestionArtistes.getRealisateur("Smith", "Jane");
    assertEquals(realisateur, result,
        "La méthode devrait retourner le réalisateur correspondant.");
  }
  
  /**
   * Vérifie que la méthode getRealisateur retourne null pour un réalisateur
   * inexistant.
   */
  @Test
  void testGetRealisateurInexistant() {
    Artiste result = gestionArtistes.getRealisateur("Inconnu", "Realisateur");
    assertNull(result,
        "La méthode devrait retourner null pour un réalisateur inexistant.");
  }
  
  /**
   * Vérifie que la méthode ensembleFilmsActeur retourne les films d'un acteur
   * donné.
   */
  @Test
  void testEnsembleFilmsActeur() {
    assertNull(gestionArtistes.ensembleFilmsActeur(null),
        "La méthode devrait retourner un ensemble vide pour un acteur sans film.");
  }

  
  /**
   * Vérifie que la méthode ensembleFilmsRealisateur retourne null si aucun film
   * n'est trouvé.
   */
  @Test
  void testEnsembleFilmsRealisateurAucunFilm() {
    Set<Film> result = gestionArtistes.ensembleFilmsRealisateur(realisateur);
    assertNull(result,
        "La méthode devrait retourner null si aucun film n'est trouvé.");
  }
  
  /**
   * Vérifie que la méthode addActeur ajoute un acteur correctement.
   */
  @Test
  void testAddActeur() {
    Artiste nouvelActeur = new Artiste("Wayne", "Bruce", "Américaine");
    boolean result = gestionArtistes.addActeur(nouvelActeur);
    assertTrue(result,
        "La méthode devrait retourner true pour un ajout réussi.");
    assertTrue(gestionArtistes.ensembleActeurs().contains(nouvelActeur),
        "L'acteur devrait être ajouté dans l'ensemble.");
  }
  
  /**
   * Vérifie que la méthode removeActeur supprime un acteur correctement.
   */
  @Test
  void testRemoveActeur() {
    boolean result = gestionArtistes.removeActeur(acteur);
    assertTrue(result,
        "La méthode devrait retourner true pour une suppression réussie.");
    assertFalse(gestionArtistes.ensembleActeurs().contains(acteur),
        "L'acteur ne devrait plus être présent dans l'ensemble.");
  }
  
  /**
   * Vérifie que la méthode addRealisateur ajoute un réalisateur correctement.
   */
  @Test
  void testAddRealisateur() {
    Artiste nouveauRealisateur =
        new Artiste("Nolan", "Christopher", "Britannique");
    boolean result = gestionArtistes.addRealisateur(nouveauRealisateur);
    assertTrue(result,
        "La méthode devrait retourner true pour un ajout réussi.");
    assertTrue(
        gestionArtistes.ensembleRealisateurs().contains(nouveauRealisateur),
        "Le réalisateur devrait être ajouté dans l'ensemble.");
  }
  
  /**
   * Vérifie que la méthode removeRealisateur supprime un réalisateur
   * correctement.
   */
  @Test
  void testRemoveRealisateur() {
    boolean result = gestionArtistes.removeRealisateur(realisateur);
    assertTrue(result,
        "La méthode devrait retourner true pour une suppression réussie.");
    assertFalse(gestionArtistes.ensembleRealisateurs().contains(realisateur),
        "Le réalisateur ne devrait plus être présent dans l'ensemble.");
  }
}
