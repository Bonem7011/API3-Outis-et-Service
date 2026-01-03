package be.condorcet.alanb.resources;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api") // Toutes nos URLs commenceront par /api/...
public class RestConfiguration extends Application {
    // Rien à mettre dedans, l'annotation suffit à activer JAX-RS !
}