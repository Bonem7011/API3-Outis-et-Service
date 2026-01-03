package be.condorcet.alanb.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // On tue la session
        if (req.getSession(false) != null) {
            req.getSession().invalidate();
        }
        // On redirige vers le login
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}