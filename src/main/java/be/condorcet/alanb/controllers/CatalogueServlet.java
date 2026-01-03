package be.condorcet.alanb.controllers;

import be.condorcet.alanb.entities.Outil;
import be.condorcet.alanb.services.OutilService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/catalogue")
public class CatalogueServlet extends HttpServlet {

    @Inject
    private OutilService outilService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Récupérer tous les outils depuis la BDD
        List<Outil> outils = outilService.getAllOutils();

        // 2. Envoyer la liste à la JSP
        req.setAttribute("listeOutils", outils);

        // 3. Afficher la page
        req.getRequestDispatcher("/WEB-INF/views/catalogue.jsp").forward(req, resp);
    }
}