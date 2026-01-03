package be.condorcet.alanb.controllers;

import be.condorcet.alanb.entities.Outil;
import be.condorcet.alanb.entities.Utilisateur;
import be.condorcet.alanb.services.OutilService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/add-tool")
public class AddToolServlet extends HttpServlet {

    @Inject
    private OutilService outilService;

    // 1. Afficher le formulaire
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Sécurité de base
        if (req.getSession(false) == null || req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("login");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/views/add-tool.jsp").forward(req, resp);
    }

    // 2. Traiter le formulaire
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Utilisateur user = (Utilisateur) session.getAttribute("user");

        // Récupération des données
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        String categorie = req.getParameter("categorie");

        try {
            // Création de l'objet
            Outil newTool = new Outil();
            newTool.setNom(nom);
            newTool.setDescription(description);
            newTool.setCategorie(categorie);
            newTool.setDisponible(true); // Dispo par défaut

            // Appel au Service existant (qui gère déjà le lien avec l'utilisateur)
            outilService.addOutilToUtilisateur(newTool, user.getId());

            // Succès -> Retour au Dashboard
            resp.sendRedirect("home?msg=Outil ajoute avec succes");

        } catch (Exception e) {
            // Erreur -> On reste sur le formulaire
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/add-tool.jsp").forward(req, resp);
        }
    }
}