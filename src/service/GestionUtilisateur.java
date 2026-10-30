package service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import model.Evaluation;
import model.Film;
import model.Utilisateur;

import exception.LocationException;
import exception.NonConnecteException;

import model.InformationPersonnelle;

/**
 * Implémentation de l'interface Utilisateur (InterAdministration) Définition
 * des différentes fonction de l'interface utilisateur.
 *
 * @author Alexandre De Brito
 */
public class GestionUtilisateur implements Serializable {
  
  /**
   * ID Serial.
   */
  private static final long serialVersionUID = 1L;
  
  public GestionArtistes foncArtiste;
  public GestionFilms foncFilm;
  private Set<Utilisateur> setUtilisateur;
  private Utilisateur utilConnecte;
  
  /**
   * Constructeur de la classe GestionUtilisateur. Permet de définir les
   * instances nécessaires pour accéder aux fonctions de gestion des films et
   * des artistes, ainsi que d'initialiser l'ensemble des utilisateurs.
   *
   * @param foncFilms instance de GestionFilms permettant l'accès aux
   *        fonctionnalités liées aux films
   * @param foncArtistes instance de GestionArtistes permettant l'accès aux
   *        fonctionnalités liées aux artistes
   */
  public GestionUtilisateur(GestionFilms foncFilms,
      GestionArtistes foncArtistes) {
    this.foncFilm = foncFilms;
    this.foncArtiste = foncArtistes;
    this.setUtilisateur = new HashSet<>();
  }
  
  /**
   * Ajoute un utilisateur à l'ensemble des utilisateurs.
   *
   * @param utilisateur l'utilisateur à ajouter
   */
  public void setUtilisateur(Utilisateur utilisateur) {
    this.setUtilisateur.add(utilisateur);
  }
  
  
  /**
   * Définit l'ensemble d'utilisateurs.
   *
   * @param setUtilisateur le nouvel ensemble d'utilisateurs à assigner
   */
  public void setSetUtilisateur(Set<Utilisateur> setUtilisateur) {
    this.setUtilisateur = setUtilisateur;
  }
  
  /**
   * Renvoie l'ensemble d'utilisateurs actuellement stocké.
   *
   * @return l'ensemble d'utilisateurs
   */
  public Set<Utilisateur> getSetUtilisateur() {
    return this.setUtilisateur;
  }
  
  /**
   * Définit l'utilisateur actuellement connecté.
   *
   * @param utilisateur l'utilisateur à définir comme connecté
   */
  public void setUtilisateurConnecte(Utilisateur utilisateur) {
    this.utilConnecte = utilisateur;
  }
  
  /**
   * Renvoie l'utilisateur actuellement connecté.
   *
   * @return l'utilisateur connecté
   */
  public Utilisateur getUtilisateurConnecte() {
    return this.utilConnecte;
  }
  
  
  /**
   * Inscription d'un utilisateur. Le pseudo choisi ne doit pas déjà exister
   * parmi les utilisateurs déjà inscrits.
   *
   * @param pseudo le pseudo (unique) de l'utilisateur
   * @param mdp le mot de passe de l'utilisateur (ne pas doit pas être vide ou
   *        <code>null</code>)
   * @param info les informations personnelles sur l'utilisateur
   * @return un code précisant le résultat de l'inscription : 0 si l'inscription
   *         s'est bien déroulée, 1 si le pseudo était déjà utilisé, 2 si le
   *         pseudo ou le mot de passe était vide, 3 si les informations
   *         personnelles ne sont pas bien précisées
   */
  public int inscription(String pseudo, String mdp,
      InformationPersonnelle info) {
    if (pseudo == null || mdp == null || pseudo.isEmpty() || mdp.isEmpty()) {
      return 2;
    }
    
    if (info == null || info.getNom() == null || info.getNom().isEmpty()
        || info.getPrenom() == null || info.getPrenom().isEmpty()
        || info.getAdresse() == null || info.getAdresse().isEmpty()
        || info.getAge() <= 0) {
      return 3;
    }
    
    for (Utilisateur util : this.setUtilisateur) {
      if (util.getPseudo().equals(pseudo)) {
        return 1;
      }
    }
    
    Utilisateur util = new Utilisateur(pseudo, mdp, info);
    
    this.setUtilisateur.add(util);
    
    return 0;
  }
  
  /**
   * Connexion de l'utilisateur. Une fois connecté, l'utilisateur pourra accéder
   * aux services de location et déposer des commentaires sur les films qu'il a
   * loués.
   *
   * @param pseudo le pseudo de l'utilisateur
   * @param mdp le mot de passe de l'utilsateur
   * @return <code>true</code> si la connexion s'est bien déroulée,
   *         <code>false</code> en cas de couple pseudo/mot de passe invalide
   */
  public boolean connexion(String pseudo, String mdp) {
    if (pseudo == null || mdp == null || pseudo.isEmpty() || mdp.isEmpty()) {
      return false;
    }
    for (Utilisateur utilisateur : this.setUtilisateur) {
      if (utilisateur.getPseudo().equals(pseudo)
          && utilisateur.getMdp().equals(mdp)) {
        this.utilConnecte = utilisateur;
        return true;
      }
    }
    return false;
  }
  
  /**
   * Déconnecte l'utilisateur actuellement connecté.
   *
   * @throws NonConnecteException si aucun utilisateur n'est connecté.
   */
  public void deconnexion() throws NonConnecteException {
    if (this.utilConnecte == null) {
      throw new NonConnecteException();
    } else {
      this.utilConnecte = null;
    }
  }
  
  /**
   * L'utilisateur connecté loue un film. Il peut le louer s'il a moins de 3
   * films en cours de location et s'il a l'âge suffisant pour voir le film.
   *
   * @param film le film à louer
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   * @throws LocationException en cas de refus de location, l'exception
   *         contiendra un message précisant le problème (déjà 3 films loués,
   *         âge insuffisant ou autre)
   */
  public void louerFilm(Film film)
      throws NonConnecteException, LocationException {
    
    // Vérification de la connexion d'un utilisateur
    if (this.utilConnecte == null) {
      throw new NonConnecteException();
    }
    
    if (!film.getLocation()) {
      throw new LocationException("Film non louable");
    }
    
    // Vérification du nombre de films déjà loués
    if (this.utilConnecte.getNbLocFilm() >= 3) {
      throw new LocationException("Vous avez déjà loué 3 films.");
    }
    
    // Vérification de l'âge de l'utilisateur par rapport à l'âge limite du film
    if (this.utilConnecte.getInfo().getAge() < film.getAgeMin()) {
      throw new LocationException("Âge insuffisant pour louer ce film.");
    }
    
    // Ajout du film dans la liste des films loués de l'utilisateur
    this.utilConnecte.addFilm(film);
    film.incremLoc();
  }
  
  
  /**
   * Termine la location d'un film.
   *
   * @param film le film dont la location est terminée
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   * @throws LocationException en cas de problème, notamment si l'utilisateur
   *         n'avait pas ce film en location, l'exception contiendra un message
   *         précisant le problème
   */
  public void finLocationFilm(Film film)
      throws NonConnecteException, LocationException {
    
    // Vérification de la connexion d'un utilisateur
    if (this.utilConnecte == null) {
      throw new NonConnecteException();
    }
    
    // Vérification si le film est en location
    if (!film.getLocation()) {
      throw new LocationException("Ce film n'est pas en location.");
    }
    
    // Vérification si le film est en location
    if (!this.utilConnecte.getLocFilm().contains(film)) {
      throw new LocationException("L'utilisateur ne louait pas ce film.");
    }
    
    // Retrait du film de la liste des films loués de l'utilisateur
    this.utilConnecte.removeFilm(film);
    film.decremLoc();
  }
  
  
  /**
   * Information sur le fait qu'un film est ouvert à la location.
   *
   * @param film le film dont on veut vérifier la possibilité de location
   * @return <code>true</code> si le film est ouvert à la location,
   *         <code>false</code> sinon
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  public boolean estLouable(Film film) throws NonConnecteException {
    if (this.utilConnecte == null) {
      throw new NonConnecteException();
    }
    
    return film.getLocation();
  }
  
  /**
   * Renvoie l'ensemble des films actuellement en location par l'utilisateur
   * connecté.
   *
   * @return les films en location par l'utilisateur connecté ou
   *         <code>null</code> si aucun film actuellement en location
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  public Set<Film> filmsEnLocation() throws NonConnecteException {
    if (this.utilConnecte == null) {
      throw new NonConnecteException();
    }
    
    if (this.utilConnecte.getLocFilm().isEmpty()) {
      return null;
    }
    
    return this.utilConnecte.getLocFilm();
  }
  
  /**
   * Ajoute à un film une évaluation de la part de l'utilisateur connecté.
   * L'utilisateur doit avoir loué le film pour le commenter (que le film soit
   * actuellement en sa location ou qu'il ait été loué puis retourné
   * préalablement). L'utilisateur ne doit pas déjà avoir déposé une évaluation
   * pour ce film.
   *
   * @param film le film à évaluer
   * @param eval l'évaluation du film
   * @throws NonConnecteException si aucun utilisateur n'était connecté
   * @throws LocationException en cas d'erreur pour ajouter l'évaluation,
   *         l'exception contiendra un message précisant le problème
   */
  public void ajouterEvaluation(Film film, Evaluation eval)
      throws NonConnecteException, LocationException {
    
    // Vérification de la connexion de l'utilisateur
    if (this.utilConnecte == null) {
      throw new NonConnecteException();
    }
    
    // Vérification si l'utilisateur a loué ce film (actuellement ou auparavant)
    if (!this.utilConnecte.getHistoFilm().contains(film)) {
      throw new LocationException("L'utilisateur n'a jamais loué ce film.");
    }
    
    // Vérification si l'utilisateur a déjà donné une évaluation pour ce film
    for (Evaluation evaluation : film.getSetEvaluations()) {
      if (evaluation.getUtilisateur() == this.utilConnecte) {
        throw new LocationException("L'utilisateur a déjà évalué ce film.");
      }
    }
    
    film.setEvaluation(eval);
    film.calculeMoyenne();
  }
  
  
  /**
   * Modifie l'évaluation que l'utilisateur connecté avait déjà déposée sur un
   * film. Ne peut se faire que si l'utilisateur avait déjà évalué le film.
   *
   * @param film le film dont l'utilisateur modifie l'évaluation
   * @param eval la nouvelle évaluation qui remplace la précédente
   * @throws NonConnecteException si aucun utilisateur n'était connecté
   * @throws LocationException en cas d'erreur pour modifier l'évaluation,
   *         l'exception contiendra un message précisant le problème
   */
  public void modifierEvaluation(Film film, Evaluation eval)
      throws NonConnecteException, LocationException {
    
    // Vérification de la connexion de l'utilisateur
    if (this.utilConnecte == null) {
      throw new NonConnecteException();
    }
    
    // Vérification si l'utilisateur a déjà donné une évaluation pour ce film
    for (Evaluation evaluation : film.getSetEvaluations()) {
      if (evaluation.getUtilisateur() == this.utilConnecte) {
        film.removeEvaluation(evaluation);
        film.setEvaluation(eval);
        return;
      }
    }
    
    throw new LocationException("L'utilisateur n'a jamais évalué ce film.");
  }
  
  
  @Override
  public String toString() {
    return this.foncArtiste.toString() + this.foncFilm.toString();
  }
}
