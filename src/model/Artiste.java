package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Représente un artiste avec un nom, un prénom, un ensemble de films associés
 * et des rôles (acteur, réalisateur, etc.).
 *
 * @author Alexandre De Brito
 */
public class Artiste implements Serializable {
  
  private static final long serialVersionUID = 1L;
  private String nom;
  private String prenom;
  private String nationalite;
  private Set<Film> films;
  
  /**
   * Constructeur de la classe Artiste.
   *
   * @param nom le nom de l'artiste
   * @param prenom le prénom de l'artiste
   */
  
  public Artiste(String nom, String prenom, String nationalite) {
    this.nom = nom;
    this.prenom = prenom;
    this.films = new HashSet<>();
    this.nationalite = nationalite;
  }
  
  /**
   * Supprime un film de la collection de films.
   *
   * @param film Le film à supprimer de la collection. Si le film n'est pas dans
   *        la collection, il ne sera pas supprimé.
   */
  public void removeFilm(Film film) {
    this.films.remove(film);
  }
  
  
  /**
   * Ajoute un film à l'ensemble des films de l'artiste.
   *
   * @param film le film à ajouter
   */
  public void addFilm(Film film) {
    films.add(film);
  }
  
  /**
   * Ajoute un film à l'ensemble des films de l'artiste.
   *
   * @param film le film à ajouter
   */
  public void addFilm(Set<Film> film) {
    this.films = film;
  }
  
  /**
   * Retourne le nom de l'artiste.
   *
   * @return le nom de l'artiste
   */
  public String getNom() {
    return this.nom;
  }
  
  /**
   * Retourne le prénom de l'artiste.
   *
   * @return le prénom de l'artiste
   */
  public String getPrenom() {
    return this.prenom;
  }
  
  /**
   * Retourne la nationalite de l'artiste.
   *
   * @return la nationalite de l'artiste
   */
  public String getNationalite() {
    return this.nationalite;
  }
  
  
  /**
   * Retourne l'ensemble des films associés à l'artiste.
   *
   * @return un ensemble de films
   */
  public Set<Film> getFilms() {
    return this.films;
  }
  
  /**
   * Renvoie une représentation textuelle de cet artiste.
   *
   * @return une chaîne de caractères contenant le nom et le prénom de
   *         l'artiste.
   */
  @Override
  public String toString() {
    return this.prenom + "-" + this.nom + "-" + this.nationalite;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Artiste other = (Artiste) obj;
    return nom.equals(other.nom) && prenom.equals(other.prenom)
        && nationalite.equals(other.nationalite);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(nom, prenom, nationalite);
    
  }
  
}
