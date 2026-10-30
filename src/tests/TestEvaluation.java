package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
 * Classe de tests JUnit pour la classe {@link model.Evaluation}.
 * 
 * <p>Cette classe teste les méthodes principales de la classe Evaluation,
 * notamment la gestion des notes, des commentaires et des films associés.
 * </p>
 *
 * @author Alexandre De Brito
 * @see model.Evaluation
 */
public class TestEvaluation {

  /** Une évaluation de test avec une note et un commentaire. */
  private Evaluation evaluation;

  /** Un film utilisé pour associer l'évaluation. */
  private Film film;

  /** Un utilisateur utilisé pour déposer l'évaluation. */
  private Utilisateur utilisateur;

  /** Un réalisateur utilisé pour construire les films. */
  private Artiste realisateur;

  /**
   * Prépare les objets nécessaires aux tests avant chaque exécution.
   *
   * @throws Exception si une erreur se produit (non applicable ici).
   */
  @BeforeEach
  void setUp() throws Exception {
    realisateur = new Artiste("Skywalker", "Luke", "Tatooine");
    
    InformationPersonnelle info = new InformationPersonnelle("Doe", "John");
    utilisateur = new Utilisateur("jdoe", "mdp123", info);

    film = new Film("Film de Test", realisateur, 2024, 12);
    
    Set<Genre> genres = new HashSet<>();
    genres.add(Genre.Action);
    film.setSetGenre(genres);

    evaluation = new Evaluation(4, "Excellent film.", utilisateur, film);
  }

  /**
   * Vérifie qu'on getNote renvoie la note.
   */
  @Test
  void testGetNote() {
    assertEquals(4, evaluation.getNote(), "La note de l'évaluation devrait être 4.");
  }

  /**
   * Vérifie que setNote met à jour la note.
   */
  @Test
  void testSetNoteValide() {
    evaluation.setNote(5);
    assertEquals(5, evaluation.getNote(), "La note de l'évaluation devrait être mise à jour à 5.");
  }

  /**
   * Vérifie que setNote vérifie si la limite est atteinte alors elle est réduite à 5.
   */
  @Test
  void testSetNoteSupLimite() {
    evaluation.setNote(6);
    assertEquals(5, evaluation.getNote(), "La note de l'évaluation ne devrait pas dépasser 5.");
  }

  /**
   * Vérifie que setNote vérifie si la limite est atteinte alors elle est augmentée à 0.
   */
  @Test
  void testSetNoteInfLimite() {
    evaluation.setNote(-1);
    assertEquals(0, evaluation.getNote(), 
        "La note de l'évaluation ne devrait pas être inférieure à 0.");
  }

  /**
   * Vérifie que la getCommentaire renvoie le commentaire.
   */
  @Test
  void testGetCommentaire() {
    assertEquals("Excellent film.", evaluation.getCommentaire(), 
         "Le commentaire devrait être 'Excellent film.'.");
  }

  /**
   * Vérifie que la setCommentaire met à jour le commentaire.
   */
  @Test
  void testSetCommentaire() {
    evaluation.setCommentaire("Film correct.");
    assertEquals("Film correct.", evaluation.getCommentaire(), 
        "Le commentaire devrait être 'Film correct.'.");
  }

  /**
   * Vérifie que la méthode getFilm renvoie le film.
   */
  @Test
  void testGetFilm() {
    assertEquals(film, evaluation.getFilm(), 
        "Le film associé à l'évaluation devrait être correct.");
  }

  /**
   * Vérifie que la méthode setFilm met à jour le film.
   */
  @Test
  void testSetFilm() {
    Film nouveauFilm = new Film("Nouveau Film", realisateur, 2023, 18);
    evaluation.setFilm(nouveauFilm);
    assertEquals(nouveauFilm, evaluation.getFilm(), 
        "Le film associé à l'évaluation devrait être mis à jour.");
  }
}