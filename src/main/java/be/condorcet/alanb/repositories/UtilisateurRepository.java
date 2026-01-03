package be.condorcet.alanb.repositories;

import be.condorcet.alanb.entities.Utilisateur;
import java.util.List;

public interface UtilisateurRepository extends GenericDAO<Utilisateur, Long> {

    // Méthode spécifique : Trouver quelqu'un par son email
    Utilisateur findByEmail(String email);

    // Méthode spécifique : Trouver tous les admins
    List<Utilisateur> findAdmins();
}