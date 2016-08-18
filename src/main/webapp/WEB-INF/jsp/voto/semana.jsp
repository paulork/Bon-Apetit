
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${votos != null && votos.size() > 0}">
        <table class="table table-striped" id="tblListagem">
            <thead>
                <tr>
                    <th>Data</th>
                    <th>Nome do restaurante</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${votos}" var="voto">
                    <tr>
                        <td>${voto.key}</td>
                        <td>${voto.value.nome}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <div class="alert alert-warning" role="alert">Ninguém votou ainda! Seja o primeiro, clique em "Votar" e dê sua contribuição.</div>
    </c:otherwise>
</c:choose>

