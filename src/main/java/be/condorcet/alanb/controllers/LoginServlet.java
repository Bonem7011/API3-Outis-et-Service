package be.condorcet.alanb.controllers;

import be.condorcet.alanb.entities.Utilisateur;
import be.condorcet.alanb.services.UtilisateurService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login") // L'URL pour accéder au login
public class LoginServlet extends HttpServlet {

    @Inject
    private UtilisateurService utilisateurService;

    // Affiche le formulaire
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    // Traite la soumission du formulaire
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // 1. On cherche l'utilisateur dans la BDD (via le Service)
        // Note: Tu devras ajouter cette méthode "findByEmail" dans ton Service si elle n'est pas publique !
        // Pour l'instant, on va tricher un peu en récupérant tout le monde et en filtrant (pas optimal mais simple)
        Utilisateur foundUser = utilisateurService.getAll().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        // 2. Vérification simple (Dans la vraie vie, on hache le mot de passe !)
        if (foundUser != null && foundUser.getMotDePasse().equals(password)) {
            // SUCCÈS : On crée une session
            HttpSession session = req.getSession();
            session.setAttribute("user", foundUser); // On sauvegarde l'objet user en mémoire

            // Redirection vers la page d'accueil (qu'on fera après)
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            // ÉCHEC : On renvoie vers le login avec un message
            req.setAttribute("error", "Email ou mot de passe incorrect");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}