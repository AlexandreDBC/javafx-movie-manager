package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.InformationPersonnelle;

/**
 * Tests JUnit de la classe {@link model.InformationPersonnelle
 * InformationPersonnelle}.
 *
 * @author Alexandre De Brito
 * @see model.InformationPersonnelle
 */
public class TestInformationPersonnelle {
  
  /**
   * Une information basique : prénom et nom.
   */
  private InformationPersonnelle infoBasique;
  /**
   * Une information complète : prénom, nom, adresse et âge.
   */
  private InformationPersonnelle infoComplete;
  
  /**
   * Instancie une information basique et une complète pour les tests.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @BeforeEach
  void setUp() throws Exception {
    infoBasique = new InformationPersonnelle("Skywalker", "Luke");
    infoComplete =
        new InformationPersonnelle("Skywalker", "Luke", "Planète Tatooine", 20);
  }
  
  /**
   * Ne fait rien après les tests : à modifier au besoin.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  /**
   * Vérifie que l'on peut positionner un âge de 25 ans.
   */
  @Test
  void testAge25Basique() {
    infoBasique.setAge(25);
    assertEquals(25, infoBasique.getAge());
  }
  
  /**
   * Vérifie qu'on ne peut pas positionner un âge négatif sur une information
   * basique.
   */
  @Test
  void testAgeNegatifBasique() {
    infoBasique.setAge(-20);
    assertNotEquals(-20, infoBasique.getAge());
  }
  
  /**
   * Vérifie qu'on ne peut pas positionner un age négatif sur une information
   * complète : l'âge reste le même qu'avant.
   */
  @Test
  void testAgeNegatifComplet() {
    int age = infoComplete.getAge();
    infoComplete.setAge(-20);
    assertEquals(age, infoComplete.getAge());
  }
  
  /**
   * Vérifie qu'une adresse n'est pas null quand on crée une information
   * personnelle.
   */
  @Test
  void testAdresseNonNull() {
    assertNotNull(infoBasique.getAdresse());
    assertNotNull(infoComplete.getAdresse());
  }
  
  /**
   * Vérifie qu'on ne peut pas positionner une adresse null sur une information
   * existante.
   */
  @Test
  void testSetterAdresseNull() {
    infoComplete.setAdresse(null);
    assertNotNull(infoComplete.getAdresse());
  }
  
  /**
   * Vérifie que les paramètres des constructeurs sont correctement gérés.
   */
  @Test
  void testConstructeur() {
    InformationPersonnelle inf =
        new InformationPersonnelle("Vador", "Dark", null, -30);
    assertEquals("Vador", inf.getNom());
    assertEquals("Dark", inf.getPrenom());
    assertNotNull(inf.getAdresse());
    assertTrue(inf.getAge() >= 0);
  }
}