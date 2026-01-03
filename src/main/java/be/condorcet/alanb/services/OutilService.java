package be.condorcet.alanb.services;

import be.condorcet.alanb.entities.Outil;
import be.condorcet.alanb.entities.Utilisateur;
import be.condorcet.alanb.repositories.OutilRepository;
import be.condorcet.alanb.repositories.UtilisateurRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class OutilService {

    @Inject
    private OutilRepository outilRepository;

    @Inject
    private UtilisateurRepository utilisateurRepository;

    /**
     * Ajoute un outil pour un utilisateur donné
     */
    @Transactional
    public Outil addOutilToUtilisateur(Outil outil, Long utilisateurId) throws Exception {
        // 1. Vérifier si l'outil a un nom
        if (outil.getNom() == null || outil.getNom().isEmpty()) {
            throw new Exception("L'outil doit avoir un nom");
        }

        // 2. Récupérer le propriétaire
        Utilisateur proprio = utilisateurRepository.findById(utilisateurId);
        if (proprio == null) {
            throw new Exception("Utilisateur introuvable avec l'ID " + utilisateurId);
        }

        // 3. Lier les deux (Important pour JPA !)
        outil.setProprietaire(proprio);
        // Note : On pourrait aussi faire proprio.getOutilsProposes().add(outil),
        // mais c'est le côté "Outil" qui porte la clé étrangère, donc c'est le plus important.

        // 4. Sauvegarder
        return outilRepository.save(outil);
    }

    public List<Outil> getAllOutils() {
        return outilRepository.findAll();
    }

    public List<Outil> getOutilsByCategorie(String categorie) {
        return outilRepository.findByCategorie(categorie);
    }
}