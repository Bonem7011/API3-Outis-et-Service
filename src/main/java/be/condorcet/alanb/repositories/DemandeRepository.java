package be.condorcet.alanb.repositories;

import be.condorcet.alanb.entities.Demande;
import be.condorcet.alanb.entities.Utilisateur;
import java.util.List;

public interface DemandeRepository extends GenericDAO<Demande, Long> {
    // Trouver les demandes faites PAR un utilisateur
    List<Demande> findByEmprunteur(Utilisateur emprunteur);
}