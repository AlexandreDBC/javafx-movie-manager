package service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import model.Evaluation;
import model.Film;
import model.Genre;

/**
 * Classe permettant de gérer un ensemble de films.
 * 
 * <p>Cette classe fournit des méthodes pour accéder et manipuler un ensemble de
 * films. Elle permet d'ajouter un film, de définir un nouvel ensemble de films,
 * ou de récupérer l'ensemble existant.
 * </p>
 *
 * @author Alexandre De Brito
 */
public class GestionFilms implements Serializable {
  
  /**
   * ID de serial.
   */
  private static final long serialVersionUID = 1L;
  
  /** Ensemble des films gérés par cette classe. */
  private Set<Film> setFilm;
  
  /**
   * Constructeur par défaut qui initialise l'ensemble de films comme étant
   * vide.
   */
  public GestionFilms() {
    this.setFilm = new HashSet<>();
  }
  
  /**
   * Récupère l'ensemble des films.
   *
   * @return l'ensemble des films
   */
  public Set<Film> getSetFilm() {
    return this.setFilm;
  }
  
  /**
   * Définit un nouvel ensemble de films.
   *
   * @param setFilm le nouvel ensemble de films à définir
   */
  public void setSetFilm(Set<Film> setFilm) {
    if (setFilm != null) {
      this.setFilm = setFilm;
    }
  }
  
  /**
   * Ajoute un film à l'ensemble des films.
   *
   * @param film le film à ajouter
   */
  public void setFilm(Film film) {
    if (film != null) {
      this.setFilm.add(film);
    }
  }
  
  /**
   * Supprime un film de la collection de films.
   *
   * @param film Le film à supprimer de la collection. Si le film n'est pas dans
   *        la collection, il ne sera pas supprimé.
   */
  public void removeFilm(Film film) {
    this.setFilm.remove(film);
  }
  
  
  /**
   * Cherche un film à partir de son titre.
   *
   * @param titre le titre du film
   * @return le film s'il a été trouvé ou <code>null</code> sinon
   */
  public Film getFilm(String titre) {
    if (titre == null || getSetFilm() == null) {
      return null;
    }
    
    for (Film film : getSetFilm()) {
      if (film.getTitre() != null && film.getTitre().equals(titre)) {
        return film;
      }
    }
    return null;
  }
  
  /**
   * Renvoie l'ensemble des films.
   *
   * @return l'ensemble des films ou <code>null</code> si aucun film n'existe
   */
  public Set<Film> ensembleFilms() {
    if (getSetFilm() == null || getSetFilm().isEmpty()) {
      return null;
    }
    return getSetFilm();
  }
  
  /**
   * Renvoie l'ensemble des évaluations d'un film.
   *
   * @param film le film dont on veut les évaluations
   * @return toutes les évaluations d'un film ou <code>null</code> si aucune
   *         évaluation n'existe ou si le film était invalide (valeur
   *         <code>null</code> par exemple)
   */
  public Set<Evaluation> ensembleEvaluationsFilm(Film film) {
    if (film == null || film.getSetEvaluations().isEmpty()) {
      return null;
    } else {
      return film.getSetEvaluations();
    }
  }
  
  /**
   * Renvoie l'ensemble des évaluations d'un film.
   *
   * @param titre le titre du film dont on veut les évaluations
   * @return toutes les évaluations d'un film ou <code>null</code> si aucune
   *         évaluation n'existe ou si le titre du film était invalide
   */
  public Set<Evaluation> ensembleEvaluationsFilm(String titre) {
    if (titre == null) {
      return null;
    }
    
    Film bonFilm = null;
    for (Film film : getSetFilm()) {
      if (film.getTitre().equals(titre)) {
        bonFilm = film;
        break;
      }
    }
    
    return ensembleEvaluationsFilm(bonFilm);
  }
  
  /**
   * Renvoie l'ensemble des films d'un certain genre.
   *
   * @param genre le genre du film
   * @return l'ensemble des films du genre ou <code>null</code> si aucun film
   *         n'a été trouvé
   */
  public Set<Film> ensembleFilmsGenre(Genre genre) {
    if (genre == null) {
      return null;
    }
    
    Set<Film> setFilmsGenre = new HashSet<>();
    
    for (Film film : getSetFilm()) {
      if (film.getSetGenre() != null && film.getSetGenre().contains(genre)) {
        setFilmsGenre.add(film);
      }
    }
    
    return setFilmsGenre.isEmpty() ? null : setFilmsGenre;
  }
  
  /**
   * Renvoie l'évaluation moyenne d'un film.
   *
   * @param film le film dont on récupère l'évaluation moyenne
   * @return l'évaluation moyenne du film ou -1 si aucune évaluation n'existe,
   *         ou -2 en cas de film invalide (valeur <code>null</code>)
   */
  public double evaluationMoyenne(Film film) {
    if (film == null) {
      return -2;
    } else if (film.getSetEvaluations().isEmpty()) {
      return -1;
    } else {
      return film.getNoteMoyenne();
    }
  }
  
  /**
   * Renvoie l'ensemble des évaluations associées à tous les film. Cette méthode
   * est utilisée pour récupérer toutes les évaluations déjà déposées.
   *
   * @return l'ensemble d'évaluations de tous les films
   */
  public Set<Evaluation> getSetEvaluation() {
    Set<Evaluation> setEvaluation = new HashSet<>();
    for (Film film : this.setFilm) {
      for (Evaluation eval : film.getSetEvaluations()) {
        setEvaluation.add(eval);
      }
    }
    return setEvaluation;
  }
}
