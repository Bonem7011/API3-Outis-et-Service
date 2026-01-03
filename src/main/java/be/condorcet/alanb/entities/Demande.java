package be.condorcet.alanb.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    private String statut; // "EN_ATTENTE", "ACCEPTEE", "REFUSEE"

    // Qui veut emprunter ?
    @ManyToOne
    @JoinColumn(name = "emprunteur_id")
    private Utilisateur emprunteur;

    // Quel outil ?
    @ManyToOne
    @JoinColumn(name = "outil_id")
    private Outil outilConcerne;
}
