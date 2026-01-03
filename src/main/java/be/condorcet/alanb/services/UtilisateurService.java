package be.condorcet.alanb.services;

import be.condorcet.alanb.entities.Utilisateur;
import be.condorcet.alanb.repositories.UtilisateurRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped // C'est un Singleton (une seule instance en mémoire)
public class UtilisateurService {

    @Inject // CDI : On demande au serveur de nous donner le DAO
    private UtilisateurRepository utilisateurRepository;

    @Transactional // Si ça plante au milieu, on annule tout (Rollback)
    public Utilisateur createUtilisateur(Utilisateur u) throws Exception {
        // 1. Validation (Règle métier)
        if (u.getNom() == null || u.getNom().trim().isEmpty()) {
            throw new Exception("Le nom est obligatoire !");
        }
        if (u.getEmail() == null || !u.getEmail().contains("@")) {
            throw new Exception("L'email est invalide !");
        }

        // 2. Vérifier si l'email existe déjà (Optionnel mais recommandé)
        if (utilisateurRepository.findByEmail(u.getEmail()) != null) {
            throw new Exception("Cet email est déjà utilisé !");
        }

        // 3. Appel au DAO pour sauvegarder
        return utilisateurRepository.save(u);
    }

    public Utilisateur getById(Long id) {
        return utilisateurRepository.findById(id);
    }

    public List<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
    }

    // Pas besoin de @Transactional pour la lecture, mais indispensable pour l'écriture
    @Transactional
    public void delete(Long id) {
        utilisateurRepository.delete(id);
    }
}