
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${votos != null && votos.size() > 0}">
        <table class="table table-striped" id="tblListagem">
            <thead>
                <tr>
                    <th>Nome do restaurante</th>
                    <th>Votos (hoje)</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${votos}" var="voto">
                    <tr>
                        <td>${voto.nome}</td>
                        <td>${voto.counting}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <div class="alert alert-warning" role="alert">Ninguém votou ainda! Seja o primeiro, clique em "Votar" e dê sua contribuição.</div>
    </c:otherwise>
</c:choose>


