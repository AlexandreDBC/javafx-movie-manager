package service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import model.Artiste;
import model.Film;

/**
 * Classe gérant les fonctionnalités liées aux artistes. Elle permet de gérer
 * des ensembles d'acteurs, de réalisateurs et d'autres artistes, ainsi que leur
 * association avec des films.
 *
 * @author Alexandre De Brito
 */
public class GestionArtistes implements Serializable {
  
  /**
   * ID de serial.
   */
  private static final long serialVersionUID = 1L;
  
  /** Gestionnaire des fonctionnalités liées aux films. */
  private GestionFilms foncFilms;
  
  /** Ensemble de tous les artistes. */
  private Set<Artiste> setArtiste;
  
  /** Ensemble des acteurs. */
  private Set<Artiste> setActeur;
  
  /** Ensemble des réalisateurs. */
  private Set<Artiste> setRealisateur;
  
  /**
   * Constructeur de la classe GestionArtistes. Initialise les ensembles
   * d'artistes, d'acteurs et de réalisateurs, et lie l'objet GestionFilms
   * fourni.
   *
   * @param foncFilms le gestionnaire des fonctionnalités des films
   */
  public GestionArtistes(GestionFilms foncFilms) {
    this.setArtiste = new HashSet<>();
    this.setActeur = new HashSet<>();
    this.setRealisateur = new HashSet<>();
    this.foncFilms = foncFilms;
  }
  
  /**
   * Cherche un artiste à partir de son nom et son prénom.
   *
   * @param nom le nom de l'artiste
   * @param prenom le prénom de l'artiste
   * @return l'artiste s'il a été trouvé ou <code>null</code> sinon
   */
  public Artiste getArtiste(String nom, String prenom) {
    if (nom == null || prenom == null) {
      return null;
    }
    
    for (Artiste artiste : this.setArtiste) {
      if (artiste.getNom().equals(nom) && artiste.getPrenom().equals(prenom)) {
        return artiste;
      }
    }
    
    return null;
  }
  
  /**
   * Cherche un acteur à partir de son nom et son prénom.
   *
   * @param nom le nom de l'acteur
   * @param prenom le prénom de l'acteur
   * @return l'acteur s'il a été trouvé ou <code>null</code> sinon
   */
  public Artiste getActeur(String nom, String prenom) {
    if (nom == null || prenom == null) {
      return null;
    }
    
    for (Artiste acteur : this.setActeur) {
      if (acteur.getNom().equals(nom) && acteur.getPrenom().equals(prenom)) {
        return acteur;
      }
    }
    
    return null;
  }
  
  /**
   * Cherche un réalisateur à partir de son nom et son prénom.
   *
   * @param nom le nom du réalisateur
   * @param prenom le prénom du réalisateur
   * @return le réalisateur s'il a été trouvé ou <code>null</code> sinon
   */
  public Artiste getRealisateur(String nom, String prenom) {
    if (nom == null || prenom == null) {
      return null;
    }
    
    for (Artiste realisateur : this.setRealisateur) {
      if (realisateur.getNom().equals(nom)
          && realisateur.getPrenom().equals(prenom)) {
        return realisateur;
      }
    }
    return null;
  }
  
  /**
   * Renvoie l'ensemble des artistes.
   *
   * @return l'ensemble des artistes
   */
  public Set<Artiste> getSetArtiste() {
    return this.setArtiste;
  }
  
  /**
   * Renvoie l'ensemble des acteurs.
   *
   * @return l'ensemble des acteurs
   */
  public Set<Artiste> getSetActeur() {
    return this.setActeur;
  }
  
  /**
   * Renvoie l'ensemble des realisateurs.
   *
   * @return l'ensemble des realisateurs
   */
  public Set<Artiste> getSetRealisateur() {
    return this.setRealisateur;
  }
  
  
  /**
   * Renvoie l'ensemble des acteurs.
   *
   * @return l'ensemble des acteurs
   */
  public Set<Artiste> ensembleActeurs() {
    return this.setActeur;
  }
  
  /**
   * Renvoie l'ensemble des films d'un certain acteur.
   *
   * @param acteur l'acteur
   * @return l'ensemble des films de l'acteur ou <code>null</code> si aucun film
   *         n'a été trouvé ou que le paramètre était invalide
   */
  public Set<Film> ensembleFilmsActeur(Artiste acteur) {
    if (acteur == null || !this.setActeur.contains(acteur)) {
      return null;
    }
    
    Set<Film> filmsActeur = new HashSet<>();
    
    for (Film film : foncFilms.getSetFilm()) {
      if (film.getSetActeur() != null && film.getSetActeur().contains(acteur)) {
        filmsActeur.add(film);
      }
    }
    
    return filmsActeur.isEmpty() ? null : filmsActeur;
  }
  
  /**
   * Renvoie l'ensemble des réalisateurs.
   *
   * @return l'ensemble des réalisateurs
   */
  public Set<Artiste> ensembleRealisateurs() {
    return this.setRealisateur;
  }
  
  /**
   * Renvoie l'ensemble des films d'un réalisateur.
   *
   * @param realisateur le réalisateur dont on veut les films
   * @return l'ensemble des films du réalisateur ou <code>null</code> s'il n'en
   *         existe pas
   */
  public Set<Film> ensembleFilmsRealisateur(Artiste realisateur) {
    if (realisateur == null || !this.setRealisateur.contains(realisateur)) {
      return null;
    }
    
    Set<Film> filmsRealisateur = new HashSet<>();
    
    for (Film film : foncFilms.getSetFilm()) {
      if (film.getRealisateur() != null
          && film.getRealisateur() == realisateur) {
        filmsRealisateur.add(film);
      }
    }
    
    return filmsRealisateur.isEmpty() ? null : filmsRealisateur;
  }
  
  /**
   * Supprime un acteur du set des acteurs.
   *
   * @param artiste artiste à supprimer
   * @return <code>true</code> si l'acteur a été supprimé, <code>false</code> si
   *         l'acteur n'était pas présent ou si le paramètre est
   *         <code>null</code>
   */
  public boolean removeArtiste(Artiste artiste) {
    if (artiste == null) {
      return false;
    }
    
    return this.setArtiste.remove(artiste);
  }
  
  /**
   * Supprime un acteur du set des acteurs.
   *
   * @param acteur l'acteur à supprimer
   * @return <code>true</code> si l'acteur a été supprimé, <code>false</code> si
   *         l'acteur n'était pas présent ou si le paramètre est
   *         <code>null</code>
   */
  public boolean removeActeur(Artiste acteur) {
    if (acteur == null) {
      return false;
    }
    return this.setActeur.remove(acteur);
  }
  
  /**
   * Supprime un réalisateur du set des réalisateurs.
   *
   * @param realisateur le réalisateur à supprimer
   * @return <code>true</code> si le réalisateur a été supprimé,
   *         <code>false</code> si le réalisateur n'était pas présent ou si le
   *         paramètre est <code>null</code>
   */
  public boolean removeRealisateur(Artiste realisateur) {
    if (realisateur == null) {
      return false;
    }
    return this.setRealisateur.remove(realisateur);
  }
  
  /**
   * Ajoute un acteur au set des acteurs.
   *
   * @param acteur l'acteur à ajouter
   * @return <code>true</code> si l'acteur a été ajouté, <code>false</code> si
   *         l'acteur était déjà présent ou si le paramètre est
   *         <code>null</code>
   */
  public boolean addActeur(Artiste acteur) {
    if (acteur == null) {
      return false;
    }
    return this.setActeur.add(acteur);
  }
  
  /**
   * Ajoute un réalisateur au set des réalisateurs.
   *
   * @param realisateur le réalisateur à ajouter
   * @return <code>true</code> si le réalisateur a été ajouté,
   *         <code>false</code> si le réalisateur était déjà présent ou si le
   *         paramètre est <code>null</code>
   */
  public boolean addRealisateur(Artiste realisateur) {
    if (realisateur == null) {
      return false;
    }
    return this.setRealisateur.add(realisateur);
  }
  
  /**
   * Ajoute un réalisateur au set des réalisateurs.
   *
   * @param artiste l'artiste à ajouter
   * @return <code>true</code> si le réalisateur a été ajouté,
   *         <code>false</code> si le réalisateur était déjà présent ou si le
   *         paramètre est <code>null</code>
   */
  public boolean addArtiste(Artiste artiste) {
    if (artiste == null) {
      return false;
    }
    return this.setArtiste.add(artiste);
  }
  
  /**
   * Renvoie une représentation textuelle de tous les artistes contenus dans
   * l'ensemble des artistes de cette instance.
   *
   * @return une chaîne de caractères contenant la liste des artistes avec leurs
   *         noms et prénoms, chaque artiste étant affiché sur une nouvelle
   *         ligne.
   */
  @Override
  public String toString() {
    String res = "";
    
    for (Artiste artiste : this.setArtiste) {
      res += artiste.toString() + " ";
    }
    
    return res;
  }
}
