<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Les Outils du Quartier</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-light bg-light mb-4 shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home">⬅ Retour au Dashboard</a>
        <span class="navbar-text">Les outils du quartier</span>
    </div>
</nav>

<div class="container mt-3">
    <%-- Message de succès si on vient de faire une demande --%>
    <c:if test="${param.success}">
        <div class="alert alert-success alert-dismissible fade show">
            ✅ Demande d'emprunt envoyée avec succès !
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>
    <%-- Message d'erreur --%>
    <c:if test="${not empty param.error}">
        <div class="alert alert-danger">
            ❌ Erreur : <c:out value="${param.error}"/>
        </div>
    </c:if>
</div>

<div class="container">
    <h2 class="mb-4">Disponibles en ce moment</h2>

    <c:if test="${empty listeOutils}">
        <div class="alert alert-info">Aucun outil n'est proposé pour l'instant. Soyez le premier !</div>
    </c:if>

    <div class="row">
        <c:forEach items="${listeOutils}" var="outil">
            <div class="col-md-4 mb-4">
                <div class="card h-100">
                    <div class="card-header bg-primary text-white">
                            ${outil.categorie}
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">${outil.nom}</h5>
                        <p class="card-text text-muted">${outil.description}</p>
                        <p>
                            <small>Proposé par : <strong>${outil.proprietaire.prenom}</strong></small>
                        </p>
                    </div>
                    <div class="card-footer bg-white border-top-0">
                        <c:choose>
                            <c:when test="${outil.disponible}">
                                <form action="${pageContext.request.contextPath}/emprunter" method="post">
                                    <input type="hidden" name="outilId" value="${outil.id}">
                                    <button type="submit" class="btn btn-success w-100">Emprunter cet outil</button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <button class="btn btn-secondary w-100" disabled>Déjà emprunté</button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>