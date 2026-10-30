package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Artiste;
import model.Evaluation;
import model.Film;
import model.Genre;
import model.InformationPersonnelle;
import model.Utilisateur;

/**
 * Tests JUnit de la classe {@link model.Film}.
 *
 * @author Alexandre De Brito
 * @see model.Film
 */
public class TestFilm {

  /**
   * Un film avec des informations de base : titre et année de réalisation.
   */
  private Film filmBasique;

  /**
   * Un film complet avec titre, genre, artistes, évaluations et autres informations.
   */
  private Film filmComplet;
  
  /**
   * Utilisateur par défaut pour les tests.
   */
  private Utilisateur testUser;
  
  /**
   * Second utilisateur pour tester les évaluations multiples.
   */
  private Utilisateur testUser2;
  
  /**
   * Réalisateur des films de test.
   */
  private Artiste realisateur;

  /**
   * Instancie un film basique et un film complet pour les tests.
   *
   * @throws Exception ne peut pas être levée ici.
   */
  @BeforeEach
  void setUp() throws Exception {
    realisateur = new Artiste("Van damme", "Jean-claude", "Belge");

    filmBasique = new Film("Film Basique", realisateur, 2024, 18);
    filmComplet = new Film("Film Complet", realisateur, 2024, 18);
    
    filmComplet.setGenre(Genre.Action);

    InformationPersonnelle info = new InformationPersonnelle("Doe", "John");
    testUser = new Utilisateur("jdoe", "mdp123", info);

    InformationPersonnelle info2 = new InformationPersonnelle("Smith", "Jane");
    testUser2 = new Utilisateur("jsmith", "mdp456", info2);

    filmBasique.setEvaluation(new Evaluation(5, "Super film !", testUser, filmBasique));
    filmComplet.setEvaluation(new Evaluation(3, "Complet mais pas fou !", testUser, filmComplet));
  }

  /**
   * Vérifie que l'on peut positionner un âge minimal sur un film.
   */
  @Test
  void testAgeMin() {
    filmBasique.setAgeMin(25);
    assertEquals(25, filmBasique.getAgeMin());
  }

  /**
   * Vérifie que l'on peut modifier l'année de réalisation d'un film.
   */
  @Test
  void testAnneeRealisation() {
    filmBasique.setAnneeReal(2025);
    assertEquals(2025, filmBasique.getAnneeReal());
  }

  /**
   * Vérifie que la moyenne des évaluations est correcte.
   */
  @Test
  void testCalculMoyenne() {
    filmBasique.setEvaluation(new Evaluation(4, "Bon film", testUser2, filmBasique));
    assertEquals(4.5, filmBasique.getNoteMoyenne(), 0.01);
  }

  /**
   * Vérifie que la moyenne d'un film sans évaluation est de 0.0.
   */
  @Test
  void testCalculMoyenneVide() {
    Film filmVide = new Film("Vide", realisateur, 2024, 0);
    filmVide.calculeMoyenne();
    assertEquals(0.0, filmVide.getNoteMoyenne(), 0.01);
  }

  /**
   * Vérifie qu'on ne peut pas définir une évaluation null sur un film.
   */
  @Test
  void testSetterEvaluationNull() {
    int tailleInitiale = filmComplet.getSetEvaluations().size();
    filmComplet.setEvaluation(null);
    assertEquals(tailleInitiale, filmComplet.getSetEvaluations().size());
  }

  /**
   * Vérifie le remplacement complet du set d'évaluations.
   */
  @Test
  void testSetSetEvaluations() {
    Set<Evaluation> evaluations = new HashSet<>();
    evaluations.add(new Evaluation(4, "Bien", testUser, filmBasique));
    evaluations.add(new Evaluation(2, "Moyen", testUser2, filmBasique));
    
    filmBasique.setSetEvaluations(evaluations);
    assertEquals(2, filmBasique.getSetEvaluations().size());
    assertEquals(3.0, filmBasique.getNoteMoyenne(), 0.01);
  }

  /**
   * Vérifie la suppression et le vidage des évaluations.
   */
  @Test
  void testClearAndRemoveEvaluations() {
    Evaluation eval = new Evaluation(1, "Nul", testUser2, filmBasique);
    filmBasique.setEvaluation(eval);
    assertTrue(filmBasique.getSetEvaluations().contains(eval));
    
    filmBasique.removeEvaluation(eval);
    assertFalse(filmBasique.getSetEvaluations().contains(eval));
    
    filmBasique.clearSetEvaluations();
    assertTrue(filmBasique.getSetEvaluations().isEmpty());
  }

  /**
   * Vérifie que l'on peut ajouter un genre à un film.
   */
  @Test
  void testAjouterGenre() {
    filmBasique.setGenre(Genre.Drame);
    assertTrue(filmBasique.getSetGenre().contains(Genre.Drame));
  }

  /**
   * Vérifie le remplacement du set de genres.
   */
  @Test
  void testSetSetGenre() {
    Set<Genre> genres = new HashSet<>();
    genres.add(Genre.Comedie);
    filmBasique.setSetGenre(genres);
    assertTrue(filmBasique.getSetGenre().contains(Genre.Comedie));
  }

  /**
   * Vérifie que l'on peut ajouter un acteur à un film.
   */
  @Test
  void testAjouterActeur() {
    Artiste acteur = new Artiste("Reichman", "Jean luc", "Français");
    filmBasique.setActeur(acteur);
    assertTrue(filmBasique.getSetActeur().contains(acteur));
  }

  /**
   * Vérifie le remplacement du set d'acteurs.
   */
  @Test
  void testSetSetActeur() {
    Set<Artiste> acteurs = new HashSet<>();
    acteurs.add(new Artiste("Reichman", "Jean luc", "Français"));
    filmBasique.setsetActeur(acteurs);
    assertEquals(1, filmBasique.getSetActeur().size());
  }

  /**
   * Vérifie qu'une location est bien activée.
   */
  @Test
  void testLocationActive() {
    filmBasique.setLocation(true);
    assertTrue(filmBasique.getLocation());
  }

  /**
   * Vérifie la gestion du nombre de locations (incrémentation et décrémentation).
   */
  @Test
  void testGestionNbLoc() {
    assertEquals(0, filmBasique.getNbLoc());
    
    filmBasique.incremLoc();
    assertEquals(1, filmBasique.getNbLoc());
    
    filmBasique.decremLoc();
    assertEquals(0, filmBasique.getNbLoc());
    
    filmBasique.setNbLoc(5);
    assertEquals(5, filmBasique.getNbLoc());
  }
  
  /**
   * Vérifie le chemin de l'affiche.
   */
  @Test
  void testAffiche() {
    filmBasique.setAffiche("/chemin/vers/affiche.jpg");
    assertEquals("/chemin/vers/affiche.jpg", filmBasique.getAffiche());
  }

  /**
   * Vérifie la récupération du réalisateur et du titre.
   */
  @Test
  void testGetRealisateurEtTitre() {
    assertEquals(realisateur, filmBasique.getRealisateur());
    assertEquals("Film Basique", filmBasique.getTitre());
  }

  /**
   * Vérifie la méthode toString.
   */
  @Test
  void testToString() {
    assertEquals("Film Basique", filmBasique.toString());
  }

  /**
   * Vérifie l'égalité et le hachage entre deux films.
   */
  @Test
  void testEqualsAndHashCode() {
    Film copieBasique = new Film("Film Basique", realisateur, 2024, 18);
    Film different = new Film("Film Différent", realisateur, 2024, 18);
    
    assertEquals(filmBasique, copieBasique);
    assertEquals(filmBasique.hashCode(), copieBasique.hashCode());
    
    assertNotEquals(filmBasique, different);
    assertNotEquals(filmBasique, null);
    assertNotEquals(filmBasique, new Object());
  }
}