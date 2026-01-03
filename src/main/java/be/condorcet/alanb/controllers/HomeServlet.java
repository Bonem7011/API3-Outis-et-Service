package be.condorcet.alanb.controllers;

import be.condorcet.alanb.entities.Demande;
import be.condorcet.alanb.entities.Utilisateur;
import be.condorcet.alanb.services.DemandeService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Inject
    private DemandeService demandeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Vérification de sécurité (Est-ce que l'utilisateur est connecté ?)
        HttpSession session = req.getSession(false); // false = ne pas créer de session si elle n'existe pas

        if (session == null || session.getAttribute("user") == null) {
            // Pas connecté ? Dehors ! Direction le login.
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // 2. Récupérer l'utilisateur connecté pour l'afficher
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        req.setAttribute("pageTitle", "Tableau de bord");

        List<Demande> mesDemandes = demandeService.getDemandesOfUser(user);
        req.setAttribute("mesDemandes", mesDemandes);

        // 3. Afficher la vue
        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }
}