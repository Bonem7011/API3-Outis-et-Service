package be.condorcet.alanb.services;

import be.condorcet.alanb.entities.Demande;
import be.condorcet.alanb.entities.Outil;
import be.condorcet.alanb.entities.Utilisateur;
import be.condorcet.alanb.repositories.DemandeRepository;
import be.condorcet.alanb.repositories.OutilRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class DemandeService {

    @Inject
    private DemandeRepository demandeRepository;

    @Inject
    private OutilRepository outilRepository;

    @Transactional
    public Demande createDemande(Utilisateur emprunteur, Long outilId) throws Exception {
        // 1. Vérifier l'outil
        Outil outil = outilRepository.findById(outilId);
        if (outil == null) {
            throw new Exception("Outil introuvable");
        }
        if (!outil.isDisponible()) {
            throw new Exception("Cet outil n'est plus disponible !");
        }
        if (outil.getProprietaire().getId().equals(emprunteur.getId())) {
            throw new Exception("Vous ne pouvez pas emprunter votre propre outil !");
        }

        // 2. Créer la demande
        Demande demande = new Demande();
        demande.setEmprunteur(emprunteur);
        demande.setOutilConcerne(outil);
        demande.setDateDebut(LocalDate.now()); // Commence aujourd'hui
        demande.setDateFin(LocalDate.now().plusDays(7)); // Pour 1 semaine par défaut
        demande.setStatut("EN_ATTENTE");

        // 3. (Optionnel) On pourrait rendre l'outil indisponible tout de suite,
        // mais gardons-le dispo tant que le propriétaire n'a pas accepté.

        return demandeRepository.save(demande);
    }

    public List<Demande> getDemandesOfUser(Utilisateur user) {
        return demandeRepository.findByEmprunteur(user);
    }
}