<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Accueil - Outils et services</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-dark bg-primary mb-4">
    <div class="container">
        <span class="navbar-brand mb-0 h1">Outils et services 🛠️</span>
        <div class="d-flex align-items-center text-white">
            <span class="me-3">Bonjour, <strong>${user.prenom}</strong> !</span>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-sm btn-light text-primary">Se déconnecter</a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-body text-center">
                    <h4>Mon Profil</h4>
                    <p class="text-muted">${user.email}</p>
                    <span class="badge bg-${user.admin ? 'danger' : 'success'}">
                        ${user.admin ? 'Administrateur' : 'Voisin'}
                    </span>
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h4>Que voulez-vous faire ?</h4>
                    <div class="d-grid gap-2 mt-3">
                        <a href="${pageContext.request.contextPath}/catalogue" class="btn btn-outline-primary">
                            🔍 Rechercher un outil
                        </a>

                        <a href="${pageContext.request.contextPath}/add-tool" class="btn btn-outline-success">
                            ➕ Prêter un nouvel outil
                        </a>

                        <a href="#mes-demandes" class="btn btn-outline-secondary">
                            📂 Voir mes demandes
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container mt-5">
    <h3 id="mes-demandes" class="mt-5">📂 Mes demandes d'emprunt</h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Outil</th>
            <th>Dates</th>
            <th>Statut</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${mesDemandes}" var="demande">
            <tr>
                <td>${demande.outilConcerne.nom}</td>
                <td>Du ${demande.dateDebut} au ${demande.dateFin}</td>
                <td>
                    <span class="badge bg-warning text-dark">${demande.statut}</span>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty mesDemandes}">
            <tr><td colspan="3" class="text-center">Aucune demande en cours.</td></tr>
        </c:if>
        </tbody>
    </table>
</div>

</body>
</html>