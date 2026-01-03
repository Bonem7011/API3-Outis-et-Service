package be.condorcet.alanb.repositories;

import be.condorcet.alanb.entities.Demande;
import be.condorcet.alanb.entities.Utilisateur;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class DemandeRepositoryImpl implements DemandeRepository {

    @PersistenceContext(unitName = "AlanBPU")
    private EntityManager em;

    @Override
    public Demande save(Demande entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Demande findById(Long id) {
        return em.find(Demande.class, id);
    }

    @Override
    public List<Demande> findAll() {
        return em.createQuery("SELECT d FROM Demande d", Demande.class).getResultList();
    }

    @Override
    public Demande update(Demande entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(Long id) {
        Demande d = findById(id);
        if(d != null) em.remove(d);
    }

    @Override
    public List<Demande> findByEmprunteur(Utilisateur emprunteur) {
        return em.createQuery("SELECT d FROM Demande d WHERE d.emprunteur = :user", Demande.class)
                .setParameter("user", emprunteur)
                .getResultList();
    }
}