package be.condorcet.alanb.entities;

import jakarta.persistence.*; // Important : Jakarta, pas Javax !
import lombok.*; // Génère les Getters/Setters automatiquement

import java.util.List;

import jakarta.json.bind.annotation.JsonbTransient;

@Entity // Dit à JPA : "Ceci est une table SQL"
@Table(name = "utilisateur") // Nom de la table dans la BDD
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Utilisateur {

    @Id // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrément (1, 2, 3...)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse; // On fera simple pour l'instant

    private boolean isAdmin; // Pour le rôle Administrateur demandé dans le sujet

    // Relation 1 : Un utilisateur possède plusieurs outils
    // "mappedBy" signifie : "Ce n'est pas moi qui porte la clé étrangère, va voir l'attribut 'proprietaire' dans Outil"
    //
    @OneToMany(mappedBy = "proprietaire", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude // Évite les boucles infinies lors de l'affichage
    @JsonbTransient
    private List<Outil> outilsProposes;

    // Relation 2 : Un utilisateur a fait plusieurs demandes d'emprunt
    @OneToMany(mappedBy = "emprunteur", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonbTransient
    private List<Demande> mesDemandes;
}