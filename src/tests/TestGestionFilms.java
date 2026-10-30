package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import service.GestionFilms;

/**
 * Tests JUnit de la classe {@link service.GestionFilms}.
 * 
 * <p>Cette classe vérifie le bon fonctionnement des méthodes de gestion 
 * des films, telles que l'ajout, la suppression, la recherche, et la 
 * manipulation des évaluations et des genres associés.</p>
 *
 * @author Alexandre De Brito
 * @see service.GestionFilms
 */
public class TestGestionFilms {

  /**
   * Instance du gestionnaire de films testé.
   */
  private GestionFilms gestion;
  
  /**
   * Premier film utilisé comme donnée de test.
   */
  private Film film1;
  
  /**
   * Second film utilisé comme donnée de test.
   */
  private Film film2;
  
  /**
   * Utilisateur servant à la création des évaluations de test.
   */
  private Utilisateur testUser;

  /**
   * Initialise le gestionnaire et les données de test avant chaque méthode.
   *
   * @throws Exception si une erreur survient lors de l'initialisation
   */
  @BeforeEach
  void setUp() throws Exception {
    gestion = new GestionFilms();

    Artiste realisateur = new Artiste("Van damme", "Jean-claude", "Belge");

    film1 = new Film("Avatar", realisateur, 2020, 10);
    film2 = new Film("Matrix", realisateur, 2021, 12);
    
    film1.setGenre(Genre.Action);

    InformationPersonnelle info = new InformationPersonnelle("Doe", "John");
    testUser = new Utilisateur("jdoe", "mdp123", info);
  }

  /**
   * Vérifie que la méthode setFilm ajoute correctement un film au gestionnaire.
   */
  @Test
  void testSetAndGetFilm() {
    gestion.setFilm(film1);
    assertTrue(gestion.getSetFilm().contains(film1));
  }

  /**
   * Vérifie que la modification de l'ensemble entier des films fonctionne et ignore les valeurs nulles.
   */
  @Test
  void testSetSetFilm() {
    Set<Film> films = new HashSet<>();
    films.add(film1);
    
    gestion.setSetFilm(films);
    assertTrue(gestion.getSetFilm().contains(film1));
    
    gestion.setSetFilm(null);
    assertTrue(gestion.getSetFilm().contains(film1));
  }

  /**
   * Vérifie que la méthode removeFilm supprime bien le film spécifié.
   */
  @Test
  void testRemoveFilm() {
    gestion.setFilm(film1);
    gestion.removeFilm(film1);
    assertFalse(gestion.getSetFilm().contains(film1));
  }

  /**
   * Vérifie la recherche d'un film à partir de son titre, y compris avec des paramètres invalides.
   */
  @Test
  void testGetFilm() {
    gestion.setFilm(film1);
    assertEquals(film1, gestion.getFilm("Avatar"));
    assertNull(gestion.getFilm("Inconnu"));
    assertNull(gestion.getFilm((String) null));
  }

  /**
   * Vérifie la récupération de l'ensemble des films présents dans le gestionnaire.
   */
  @Test
  void testEnsembleFilms() {
    assertNull(gestion.ensembleFilms());
    
    gestion.setFilm(film1);
    assertEquals(1, gestion.ensembleFilms().size());
    assertTrue(gestion.ensembleFilms().contains(film1));
  }

  /**
   * Vérifie la récupération des évaluations associées à un objet Film donné.
   */
  @Test
  void testEnsembleEvaluationsFilmParObjet() {
    assertNull(gestion.ensembleEvaluationsFilm(film1));
    
    Evaluation ev = new Evaluation(5, "Top", testUser, film1);
    film1.setEvaluation(ev);
    gestion.setFilm(film1);
    
    assertTrue(gestion.ensembleEvaluationsFilm(film1).contains(ev));
    assertNull(gestion.ensembleEvaluationsFilm((Film) null));
  }

  /**
   * Vérifie la récupération des évaluations d'un film en passant son titre en paramètre.
   */
  @Test
  void testEnsembleEvaluationsFilmParTitre() {
    assertNull(gestion.ensembleEvaluationsFilm("Avatar"));
    
    Evaluation ev = new Evaluation(5, "Top", testUser, film1);
    film1.setEvaluation(ev);
    gestion.setFilm(film1);
    
    assertTrue(gestion.ensembleEvaluationsFilm("Avatar").contains(ev));
    assertNull(gestion.ensembleEvaluationsFilm((String) null));
    assertNull(gestion.ensembleEvaluationsFilm("Inconnu"));
  }

  /**
   * Vérifie le filtrage de l'ensemble des films selon un genre donné.
   */
  @Test
  void testEnsembleFilmsGenre() {
    assertNull(gestion.ensembleFilmsGenre((Genre) null));
    assertNull(gestion.ensembleFilmsGenre(Genre.Drame));
    
    gestion.setFilm(film1);
    
    assertTrue(gestion.ensembleFilmsGenre(Genre.Action).contains(film1));
    assertNull(gestion.ensembleFilmsGenre(Genre.Drame));
  }

  /**
   * Vérifie le calcul et la récupération de la moyenne des notes d'un film.
   */
  @Test
  void testEvaluationMoyenne() {
    assertEquals(-2.0, gestion.evaluationMoyenne(null), 0.01);
    assertEquals(-1.0, gestion.evaluationMoyenne(film1), 0.01);
    
    film1.setEvaluation(new Evaluation(4, "Bon", testUser, film1));
    film1.calculeMoyenne();
    
    assertEquals(4.0, gestion.evaluationMoyenne(film1), 0.01);
  }

  /**
   * Vérifie la récupération globale de toutes les évaluations pour l'ensemble des films.
   */
  @Test
  void testGetSetEvaluation() {
    assertTrue(gestion.getSetEvaluation().isEmpty());
    
    film1.setEvaluation(new Evaluation(4, "Bon", testUser, film1));
    film2.setEvaluation(new Evaluation(5, "Parfait", testUser, film2));
    
    gestion.setFilm(film1);
    gestion.setFilm(film2);
    
    assertEquals(2, gestion.getSetEvaluation().size());
  }
}