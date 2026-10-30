package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Représente un film avec ses attributs : genres, acteurs, realisateur
 * évaluations, titre, âge minimum, année de réalisation, nombre de locations et
 * la possibilité de location. Cette classe permet de gérer les informations
 * liées à un film.
 *
 * @author Alexandre De Brito
 */
public class Film implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * Ensemble des genres associés au film.
   */
  private Set<Genre> setGenre;
  
  /**
   * Ensemble des acteurs associés au film.
   */
  private Set<Artiste> setActeur;
  
  /*
   * Réalisateur du film
   */
  private Artiste realisateur;
  
  /**
   * Ensemble des évaluations du film.
   */
  private Set<Evaluation> setEvaluations;
  
  /**
   * Le titre du film.
   */
  private String titre;
  
  /**
   * Indique si le film est disponible en location (true si le film est en
   * location, false sinon).
   */
  private boolean location;
  
  /**
   * La note moyenne des évaluations du film.
   */
  private double noteMoyenne;
  
  /**
   * L'âge minimum recommandé pour regarder le film.
   */
  private int ageMin;
  
  /**
   * L'année de réalisation du film.
   */
  private int anneeReal;
  
  /**
   * Le nombre de fois que le film a été loué.
   */
  private int nbLoc;
  
  
  /**
   * Chemin vers l'affiche du film.
   */
  private String affiche;
  
  
  /**
   * Constructeur.
   */
  public Film(String titre, Artiste realisateur, int annee, int ageLimite) {
    this.titre = titre;
    this.realisateur = realisateur;
    this.anneeReal = annee;
    this.ageMin = ageLimite;
    this.setGenre = new HashSet<>();
    this.setActeur = new HashSet<>();
    this.setEvaluations = new HashSet<>();
    this.location = false;
    this.nbLoc = 0;
    this.noteMoyenne = 0.0;
  }
  
  // Section des Getters
  
  /*
   * Retourne le réalisateur du film
   * 
   * @return le réalisateur du film
   */
  public Artiste getRealisateur() {
    return this.realisateur;
  }
  
  /**
   * Retourne le titre du film.
   *
   * @return le titre du film
   */
  public String getTitre() {
    return this.titre;
  }
  
  /**
   * Retourne l'âge minimum pour regarder le film.
   *
   * @return l'âge minimum du film
   */
  public int getAgeMin() {
    return this.ageMin;
  }
  
  /**
   * Retourne l'année de réalisation du film.
   *
   * @return l'année de réalisation
   */
  public int getAnneeReal() {
    return this.anneeReal;
  }
  
  /**
   * Retourne l'état de la location du film (vrai si le film est disponible en
   * location).
   *
   * @return true si le film est disponible à la location, false sinon
   */
  public boolean getLocation() {
    return this.location;
  }
  
  /**
   * Retourne la note moyenne des évaluations du film.
   *
   * @return la note moyenne des évaluations
   */
  public double getNoteMoyenne() {
    return this.noteMoyenne;
  }
  
  /**
   * Retourne le nombre de locations effectuées pour ce film.
   *
   * @return le nombre de locations
   */
  public int getNbLoc() {
    return this.nbLoc;
  }
  
  /**
   * Retourne l'ensemble des genres associés au film.
   *
   * @return l'ensemble des genres du film
   */
  public Set<Genre> getSetGenre() {
    return this.setGenre;
  }
  
  /**
   * Retourne l'ensemble des acteurs associés au film.
   *
   * @return l'ensemble des acteurs du film
   */
  public Set<Artiste> getSetActeur() {
    return this.setActeur;
  }
  
  /**
   * Retourne l'ensemble des évaluations du film.
   *
   * @return l'ensemble des évaluations du film
   */
  public Set<Evaluation> getSetEvaluations() {
    return this.setEvaluations;
  }
  
  /**
   * Retourne le chemin absolu du chemin du film.
   *
   * @return le chemin absolu du chemin du film
   */
  public String getAffiche() {
    return this.affiche;
  }
  
  
  // Section des Setters
  
  /**
   * Définit l'âge minimum du film.
   *
   * @param ageMin l'âge minimum pour regarder le film
   */
  public void setAgeMin(int ageMin) {
    this.ageMin = ageMin;
  }
  
  /**
   * Définit l'année de réalisation du film.
   *
   * @param anneeReal l'année de réalisation
   */
  public void setAnneeReal(int anneeReal) {
    this.anneeReal = anneeReal;
  }
  
  /**
   * Définit l'état de la location du film.
   *
   * @param location l'état de la location du film
   */
  public void setLocation(boolean location) {
    this.location = location;
  }
  
  /**
   * Définit le nombre de locations du film.
   *
   * @param nbLoc le nombre de locations
   */
  public void setNbLoc(int nbLoc) {
    this.nbLoc = nbLoc;
  }
  
  /**
   * Définit le chemin absolu de l'affiche du film.
   *
   * @param affiche le chemin absolu de l'affiche
   */
  public void setAffiche(String affiche) {
    this.affiche = affiche;
  }
  
  /**
   * Incrémente le nombre de location.
   *
   */
  public void incremLoc() {
    this.nbLoc++;
  }
  
  /**
   * Décrémente le nombre de location.
   *
   */
  public void decremLoc() {
    this.nbLoc--;
  }
  
  /**
   * Définit l'ensemble des genres associés au film.
   *
   * @param setGenre l'ensemble des genres du film
   */
  public void setSetGenre(Set<Genre> setGenre) {
    if (setGenre != null) {
      this.setGenre = setGenre;
    }
  }
  
  /**
   * Ajoute un genre à l'ensemble des genres du film.
   *
   * @param genre le genre à ajouter
   */
  public void setGenre(Genre genre) {
    if (genre != null) {
      this.setGenre.add(genre);
    }
  }
  
  /**
   * Définit l'ensemble des acteurs associés au film.
   *
   * @param setActeur l'ensemble des acteurs du film
   */
  public void setsetActeur(Set<Artiste> setActeur) {
    if (setActeur != null) {
      this.setActeur = setActeur;
    }
  }
  
  /**
   * Ajoute un acteur à l'ensemble des acteurs du film.
   *
   * @param acteur l'acteur à ajouter
   */
  public void setActeur(Artiste acteur) {
    if (acteur != null) {
      this.setActeur.add(acteur);
    }
  }
  
  /**
   * Définit l'ensemble des évaluations du film.
   *
   * @param setEvaluations l'ensemble des évaluations
   */
  public void setSetEvaluations(Set<Evaluation> setEvaluations) {
    if (setEvaluations != null) {
      this.setEvaluations = setEvaluations;
      this.calculeMoyenne(); // Recalcule la moyenne des notes
    }
  }
  
  /**
   * Ajoute une évaluation à l'ensemble des évaluations du film.
   *
   * @param evaluation l'évaluation à ajouter
   */
  public void setEvaluation(Evaluation evaluation) {
    if (evaluation != null) {
      this.setEvaluations.add(evaluation);
      this.calculeMoyenne(); // Recalcule la moyenne des notes
    }
  }
  
  /**
   * Calcule la moyenne des notes à partir des évaluations. Si aucune évaluation
   * n'existe, la note moyenne est définie à 0.0.
   */
  public void calculeMoyenne() {
    if (setEvaluations.isEmpty()) {
      this.noteMoyenne = 0.0;
      return;
    }
    
    // Calcule la somme des notes
    double somme = 0.0;
    
    for (Evaluation eval : setEvaluations) {
      somme += eval.getNote(); // Ajoute la note de chaque évaluation à la somme
    }
    
    // Calcule la moyenne
    this.noteMoyenne = somme / setEvaluations.size();
  }
  
  /**
   * Vide le set d'evaluation.
   */
  public void clearSetEvaluations() {
    this.setEvaluations.clear();
  }
  
  /**
   * Supprime une évaluation du set d'évaluation.
   *
   * @param eval l'évaluation à supprimer du set d'évaluation
   */
  public void removeEvaluation(Evaluation eval) {
    this.setEvaluations.remove(eval);
  }
  
  /**
   * Renvoie une représentation textuelle de ce film.
   *
   * @return une chaîne de caractères contenant le nom du film.
   */
  @Override
  public String toString() {
    return this.titre;
  }
  
  /**
   * Compare ce film avec un autre objet pour vérifier leur égalité.
   * Deux films sont considérés comme égaux s'ils ont le même titre, 
   * le même réalisateur et la même année de réalisation.
   *
   * @param obj l'objet à comparer avec ce film
   * @return true si l'objet spécifié est un film identique, false sinon
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Film film = (Film) obj;
    return anneeReal == film.anneeReal &&
           Objects.equals(titre, film.titre) &&
           Objects.equals(realisateur, film.realisateur);
  }

  /**
   * Retourne un code de hachage pour ce film.
   * Ce code est calculé à partir du titre, du réalisateur et de 
   * l'année de réalisation, garantissant ainsi la cohérence avec la méthode equals().
   *
   * @return le code de hachage de ce film
   */
  @Override
  public int hashCode() {
    return Objects.hash(titre, realisateur, anneeReal);
  }
}
