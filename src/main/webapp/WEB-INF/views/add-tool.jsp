<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un outil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-header bg-success text-white">
                    <h4>➕ Prêter un nouvel outil</h4>
                </div>
                <div class="card-body">

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/add-tool" method="post">
                        <div class="mb-3">
                            <label>Nom de l'outil</label>
                            <input type="text" name="nom" class="form-control" required placeholder="Ex: Tondeuse électrique">
                        </div>

                        <div class="mb-3">
                            <label>Catégorie</label>
                            <select name="categorie" class="form-select">
                                <option value="Jardinage">Jardinage</option>
                                <option value="Bricolage">Bricolage</option>
                                <option value="Cuisine">Cuisine</option>
                                <option value="Autre">Autre</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label>Description</label>
                            <textarea name="description" class="form-control" rows="3" placeholder="État, marque, puissance..."></textarea>
                        </div>

                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-success">Mettre en ligne</button>
                            <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">Annuler</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>