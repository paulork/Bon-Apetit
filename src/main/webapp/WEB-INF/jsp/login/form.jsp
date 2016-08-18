<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link href="./assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="./assets/css/login.css" rel="stylesheet">
        <script src="./assets/js/jquery-3.1.0.min.js"></script>
        <link href="./assets/css/toastr.min.css" rel="stylesheet"/>
        <script src="./assets/js/toastr.min.js"></script>
    </head>
    <body>
        <div class="container form-group">
            <form id="frmLogin" class="form-signin">
                <h2 class="form-signin-heading">LOGIN</h2>
                <label for="inputEmail" class="sr-only">Email</label>
                <input name="usuario" type="email" id="inputEmail" class="form-control" placeholder="Email" required autofocus>
                <br/>
                <label for="inputPassword" class="sr-only">Senha</label>
                <input name="senha" type="password" id="inputPassword" class="form-control" placeholder="Senha" required>
                <button id="btnSubmit" class="btn btn-lg btn-primary btn-block">Entrar</button>
            </form>
        </div>
    </body>
    <script type="text/javascript">
        $('#btnSubmit').click(function (event) {
            event.preventDefault();
            $.ajax({
                type: "POST",
                data: $('#frmLogin').serialize(),
                url: '/bon_apetit/login/check',
                success: function (result) {
                    toastr.options.preventDuplicates = true;
                    if (result === 'ok') {
                        toastr.success('Login efetuado! Redirecionanto...', {timeOut: 1500});
                        setTimeout(redirect, 1500);
                    } else if (result === 'erro') {
                        toastr.error('Login ou senha inválidos!', {timeOut: 3000});
                    }
                }
            });
        });
        
        function redirect () {
              window.location.href = "/bon_apetit";
        }
    </script>
</html>
