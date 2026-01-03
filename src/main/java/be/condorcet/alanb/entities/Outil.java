package be.condorcet.alanb.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Outil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private String categorie; // Ex: "Jardinage", "Bricolage"
    private boolean disponible; // Vrai si l'outil est chez le propriétaire

    // Relation : Un outil appartient à UN seul utilisateur
    @ManyToOne // Plusieurs outils pour un seul user
    @JoinColumn(name = "proprietaire_id") // C'est la clé étrangère dans la table SQL
    private Utilisateur proprietaire;
}
