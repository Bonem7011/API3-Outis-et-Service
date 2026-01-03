package be.condorcet.alanb.controllers;

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

@WebServlet("/emprunter")
public class EmpruntServlet extends HttpServlet {

    @Inject
    private DemandeService demandeService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Sécurité
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login");
            return;
        }
        Utilisateur user = (Utilisateur) session.getAttribute("user");

        // 2. Récupérer l'ID de l'outil envoyé par le formulaire
        String outilIdStr = req.getParameter("outilId");

        try {
            Long outilId = Long.parseLong(outilIdStr);
            // 3. Appeler le service
            demandeService.createDemande(user, outilId);

            // 4. Succès : Retour au catalogue avec un message
            // On peut passer un paramètre dans l'URL pour afficher une alerte
            resp.sendRedirect("catalogue?success=true");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("catalogue?error=" + e.getMessage());
        }
    }
}