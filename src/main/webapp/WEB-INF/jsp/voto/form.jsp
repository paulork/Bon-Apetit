
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="form-group">
    <form id="frmVotar">
        <label for="restaurantes" class="form-control-label">Escolha um restaurante</label>
        <br/>
        <select id="restaurantes" class="form-control">
        <c:forEach items="${restaurantes}" var="restaurante">
            <option id="${restaurante.id}">${restaurante.nome}</option>
        </c:forEach>
        </select>
        <br/>
        <button id="btnVotar" class="btn btn-lg btn-primary btn-block">Votar</button>
    </form>
</div>

<script>
    $('#btnVotar').click(function (event) {
        var x = document.getElementById("restaurantes").selectedIndex;
        var y = document.getElementById("restaurantes").options;
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: './voto/save',
            data: {id: y[x].id},
            success: function (result, status, xhr) {
                console.log('Result:'+result);
                console.log('Status:'+status);
                console.log(xhr);
                if(result === 'votou'){
                    toastr.warning('Você já votou hoje! É permitido apenas 1 voto por dia!', {timeOut: 3000});
                } else if (result === 'escolhido'){
                    toastr.warning('Esse restaurante já foi escolhido na semana! Tente outro!', {timeOut: 3000});
                } else {
                    toastr.success('Seu voto foi registrado!', {timeOut: 2000});
                    $('#content').html(result);
                }
            }
        });
    });
</script>

