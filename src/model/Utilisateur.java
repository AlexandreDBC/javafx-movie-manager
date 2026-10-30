package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Classe représentant un utilisateur d'un service de location de films.
 * 
 * <p>Un utilisateur possède un pseudo unique, un mot de passe, des informations
 * personnelles, un historique de films visionnés et une liste des films
 * actuellement en location.
 * </p>
 * 
 * <p>La classe inclut des méthodes pour récupérer et gérer ces informations, ainsi
 * que pour vérifier si un utilisateur peut louer de nouveaux films.
 * </p>
 *
 * @author Alexandre De Brito
 */
public class Utilisateur implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  /** Informations personnelles de l'utilisateur. */
  private InformationPersonnelle info;
  
  /** Historique des films visionnés par l'utilisateur. */
  private Set<Film> histFilm;
  
  /** Pseudo unique de l'utilisateur. */
  private String pseudo;
  
  /** Liste des films actuellement en location par l'utilisateur. */
  private Set<Film> locFilm;
  
  /** Mot de passe de l'utilisateur. */
  private String mdp;
  
  /**
   * Constructeur de la classe Utilisateur.
   *
   * @param pseudo le pseudo unique de l'utilisateur
   * @param mdp le mot de passe de l'utilisateur
   * @param info les informations personnelles de l'utilisateur
   * @throws IllegalArgumentException si le pseudo est déjà utilisé
   */
  public Utilisateur(String pseudo, String mdp, InformationPersonnelle info) {
    
    // Initialisation des attributs
    this.pseudo = pseudo;
    this.mdp = mdp;
    this.info = info;
    this.histFilm = new HashSet<Film>(); // Historique de films, initialisé vide
    this.locFilm = new HashSet<Film>(); // Films en location, initialisé vide
    
  }
  
  /**
   * Récupère les informations personnelles de l'utilisateur.
   *
   * @return les informations personnelles de l'utilisateur
   */
  public InformationPersonnelle getInfo() {
    return this.info;
  }
  
  /**
   * Récupère l'historique des films visionnés par l'utilisateur.
   *
   * @return l'ensemble des films visionnés
   */
  public Set<Film> getHistoFilm() {
    return this.histFilm;
  }
  
  /**
   * Récupère le pseudo de l'utilisateur.
   *
   * @return le pseudo unique de l'utilisateur
   */
  public String getPseudo() {
    return this.pseudo;
  }
  
  /**
   * Récupère les films actuellement en location par l'utilisateur.
   *
   * @return l'ensemble des films en location
   */
  public Set<Film> getLocFilm() {
    return this.locFilm;
  }
  
  /**
   * Ajoute un film à la liste des films loués par l'utilisateur.
   *
   * @param film le film à ajouter
   */
  public void addFilm(Film film) {
    if (film == null) {
      return;
    }
    this.histFilm.add(film);
    this.locFilm.add(film);
  }
  
  /**
   * Récupère le nombre de films actuellement en location.
   *
   * @return le nombre de films en location
   */
  public int getNbLocFilm() {
    return this.locFilm.size();
  }
  
  /**
   * Récupère le mot de passe de l'utilisateur.
   *
   * @return le mot de passe de l'utilisateur
   */
  public String getMdp() {
    return this.mdp;
  }
  
  /**
   * Vérifie si l'utilisateur peut louer de nouveaux films.
   *
   * @return <code>true</code> si l'utilisateur peut louer (moins de 3 films en
   *         location), <code>false</code> sinon
   */
  public boolean peutLouer() {
    if (this.locFilm.size() < 3) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * Supprime un film de la liste des films loués par l'utilisateur.
   *
   * @param film le film à retirer de la location
   */
  public void removeFilm(Film film) {
    this.locFilm.remove(film);
  }
  
  @Override
  public String toString() {
    return this.pseudo;
  }
  
  /**
   * Compare cet utilisateur avec un autre objet pour vérifier leur égalité.
   * Deux utilisateurs sont considérés comme égaux s'ils possèdent le même pseudo,
   * car le pseudo est défini comme unique pour chaque utilisateur.
   *
   * @param obj l'objet à comparer avec cet utilisateur
   * @return true si l'objet spécifié est un utilisateur avec le même pseudo, false sinon
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Utilisateur other = (Utilisateur) obj;
    return Objects.equals(pseudo, other.pseudo);
  }

  /**
   * Retourne un code de hachage pour cet utilisateur.
   * Ce code est calculé de manière unique à partir du pseudo,
   * garantissant ainsi la cohérence avec la méthode equals().
   *
   * @return le code de hachage de cet utilisateur
   */
  @Override
  public int hashCode() {
    return Objects.hash(pseudo);
  }
}
