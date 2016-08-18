
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Home</title>
        <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="./assets/css/index.css" rel="stylesheet">
        <script src="./assets/js/jquery-3.1.0.min.js"></script>
        <script src="./assets/js/tether.min.js"></script>
        <script src="./assets/js/bootstrap.min.js"></script>
        <link href="./assets/css/toastr.min.css" rel="stylesheet"/>
        <script src="./assets/js/toastr.min.js"></script>
    </head>

    <body>
        <nav class="navbar navbar-fixed-top navbar-dark bg-inverse">
            <ul class="nav navbar-nav">
                <li class="nav-item active">
                    <a id="idInicio" class="nav-link" href="./">Inicio</a>
                </li>
                <li class="nav-item">
                    <a id="idForm" class="nav-link" href="javascript:void(0)">Votar</a>
                </li>
                <li class="nav-item">
                    <a id="idResultDia" class="nav-link" href="javascript:void(0)">Resultado (dia)</a>
                </li>
                <li class="nav-item">
                    <a id="idResultSemana" class="nav-link" href="javascript:void(0)">Resultado (semana)</a>
                </li>
                <li class="nav-item navbar-right">
                    <a id="idSair" class="nav-link" href="./logout">Sair</a>
                </li>
            </ul>
        </nav>

        <div class="container">
            <div id="content" class="starter-template">
                <br/>
                <br/>
                <h1>Olá! Seja bem-vindo!</h1>
                <br/>
                <h1>Onde pretendes almoçar hoje?</h1>
                <br/>
                <h3 style="font-style: italic;">*Clique em "Votar" e informe sua preferencia.</h3>
            </div>
        </div>

        <script>
            $('#idForm').click(function (event) {
                $.ajax({
                    url: './voto/form',
                    success: function (result) {
                        $('#content').html(result);
                    }
                });
            });
            
            $('#idResultDia').click(function (event) {
                $.ajax({
                    url: './voto/resultado',
                    success: function (result) {
                        $('#content').html(result);
                    }
                });
            });
            
            $('#idResultSemana').click(function (event) {
                $.ajax({
                    url: './voto/semana',
                    success: function (result) {
                        $('#content').html(result);
                    }
                });
            });
        </script>
    </body>
</html>