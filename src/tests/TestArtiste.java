package tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Artiste;
import model.Film;

/**
 * Classe de tests JUnit pour la classe {@link model.Artiste}.
 *
 * <p>Cette classe teste les méthodes principales de la classe Artiste, notamment
 * la gestion des films et des attributs personnels.
 * </p>
 *
 * @author Alexandre De Brito
 * @see model.Artiste
 */
public class TestArtiste {
  
  /** Un artiste de test. */
  private Artiste artiste;
  
  /** Un film utilisé pour tester l'association avec un artiste. */
  private Film film;
  
  /**
   * Prépare les objets nécessaires aux tests avant chaque exécution.
   *
   * @throws Exception si une erreur se produit (non applicable ici).
   */
  @BeforeEach
  void setUp() throws Exception {
    artiste = new Artiste("Skywalker", "Luke", "Tatooinien");
    film = new Film("OBI WAN GOAT", null, 2023, 18);
  }
  
  /**
   * Vérifie que le constructeur initialise correctement les attributs.
   */
  @Test
  void testConstructor() {
    assertEquals("Skywalker", artiste.getNom(),
        "Le nom de l'artiste devrait être 'Skywalker'.");
    assertEquals("Luke", artiste.getPrenom(),
        "Le prénom de l'artiste devrait être 'Luke'.");
    assertEquals("Tatooinien", artiste.getNationalite(),
        "La nationalité de l'artiste devrait être 'Tatooinien'.");
    assertTrue(artiste.getFilms().isEmpty(),
        "L'ensemble des films devrait être initialement vide.");
  }
  
  /**
   * Vérifie que la méthode addFilm ajoute un film à l'ensemble des films.
   */
  @Test
  void testAddFilm() {
    artiste.addFilm(film);
    assertTrue(artiste.getFilms().contains(film),
        "Le film devrait être ajouté à l'ensemble des films de l'artiste.");
  }
  
  /**
   * Vérifie que la méthode addFilm remplace l'ensemble des films lorsqu'un
   * ensemble est passé en paramètre.
   */
  @Test
  void testAddFilmSet() {
    Set<Film> nouveauxFilms = new HashSet<>();
    Film nouveauFilm =
        new Film("L'empire contre attaque", null, 2023, 18);
    nouveauxFilms.add(nouveauFilm);
    
    artiste.addFilm(nouveauxFilms);
    assertEquals(nouveauxFilms, artiste.getFilms(),
        "L'ensemble des films devrait être remplacé par le nouvel ensemble.");
  }
  
  /**
   * Vérifie que la méthode getNom retourne le nom de l'artiste.
   */
  @Test
  void testGetNom() {
    assertEquals("Skywalker", artiste.getNom(),
        "Le nom de l'artiste devrait être 'Skywalker'.");
  }
  
  /**
   * Vérifie que la méthode getPrenom retourne le prénom de l'artiste.
   */
  @Test
  void testGetPrenom() {
    assertEquals("Luke", artiste.getPrenom(),
        "Le prénom de l'artiste devrait être 'Luke'.");
  }
  
  /**
   * Vérifie que la méthode getNationalite retourne la nationalité de l'artiste.
   */
  @Test
  void testGetNationalite() {
    assertEquals("Tatooinien", artiste.getNationalite(),
        "La nationalité de l'artiste devrait être 'Tatooinien'.");
  }
  
  /**
   * Vérifie que la méthode getFilms retourne l'ensemble des films associés à
   * l'artiste.
   */
  @Test
  void testGetFilms() {
    assertTrue(artiste.getFilms().isEmpty(),
        "L'ensemble des films devrait être initialement vide.");
    artiste.addFilm(film);
    assertTrue(artiste.getFilms().contains(film),
        "Le film devrait être présent dans l'ensemble des films de l'artiste.");
  }
}
