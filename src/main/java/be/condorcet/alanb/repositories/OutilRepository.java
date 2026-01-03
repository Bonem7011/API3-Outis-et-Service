package be.condorcet.alanb.repositories;

import be.condorcet.alanb.entities.Outil;
import java.util.List;

public interface OutilRepository extends GenericDAO<Outil, Long> {

    // Trouver des outils par catégorie (Bricolage, Jardinage...)
    List<Outil> findByCategorie(String categorie);

    // Trouver les outils d'un voisin spécifique
    List<Outil> findByProprietaireId(Long proprietaireId);
}