<%-- 
    Document   : adiciona-pessoa
    Created on : 23 de abr. de 2023, 20:58:14
    Author     : vitor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cadastrar Pessoa</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="Imagens/loja.svg"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    </head>

    <body class="navbar bg-dark" data-bs-theme="dark" style="padding-bottom: 100%;">
        <header>
            <nav class="navbar navbar-expand-lg bg-body-tertiary" style="padding-right: 1288px;">
                <div class="container-fluid">
                    <img class="navbar-brand" src="Imagens/loja.svg" width="10%" height="10%"/>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div class="navbar-nav">
                            <a class="nav-link" aria-current="page" href="home">Home</a>
                            <a class="nav-link" href="cadastraPessoa">Cadastrar Pessoas</a>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="index" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Consultar
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="consultaCliente">Clientes</a></li>
                                    <li><a class="dropdown-item" href="consultaFuncionario">Funcionários</a></li>
                                </ul>
                            </li>
                            <a class="nav-link" href="vendeProduto">Vender</a>
                        </div>
                    </div>
                </div>
            </nav>      
        </header>
        <div style="border: solid; border-width: thin; border-color: #495057; padding: 1%; text-align: center; margin: auto; margin-top: 1%;">
            <form class="was-validated" action="cadastraPessoaBD" method="post" style="text-align: left; display: flex; flex-wrap: wrap;">
                <div style="margin-right: 20px; flex-basis: 46%;">
                    <div class="col-md-3 position-relative" style="width: auto; margin-bottom: 6%;">
                        <label for="validationTooltip04" class="form-label">Tipo de cadastro</label>
                        <select class="form-select" id="validationTooltip04" name="tipoCadastro" required onclick="mostrarCampos(), validarCampos()">
                            <option selected disabled value="">Selecione</option>
                            <option value="cliente">Cliente</option>
                            <option value="funcionario">Funcionário</option>
                        </select>
                    </div>

                    <div class="col-md-4 position-relative nome" style="margin-bottom: 6%; width: auto;">
                        <label for="validationTooltip01" class="form-label">Nome Completo</label>
                        <input type="text" class="form-control" id="validationTooltip01" placeholder="Seu nome" name="nome" required>
                    </div>

                    <div class="col-md-4 position-relative form-group"  style="margin-bottom: 6%; width: auto;">
                        <label for="cpf">CPF</label>
                        <input type="text" class="form-control" id="cpf" placeholder="000.000.000-00" pattern="\d{11}" name="cpf" required>
                    </div>

                    <label for="validationTooltip01" class="form-label">Sexo</label>
                    <div class="col-md-4 position-relative genero" style="margin-bottom: 6%;">           
                        <div class="form-check">
                            <input type="radio" class="form-check-input" id="validationFormCheck2" name="genero" required>
                            <label class="form-check-label" for="validationFormCheck2">Masculino</label>
                        </div>
                        <div class="form-check mb-3">
                            <input type="radio" class="form-check-input" id="validationFormCheck3" name="genero" required>
                            <label class="form-check-label" for="validationFormCheck3">Feminino</label>
                        </div>
                    </div>

                    <div class="form-group email" style="margin-bottom: 6%; display: none;">
                        <label for="exampleInputEmail1">E-mail</label>
                        <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Seu e-mail" name="email" required>
                    </div>

                    <div class="form-group senha" style="margin-bottom: 6%; display: none;">
                        <label for="exampleInputPassword1">Senha</label>
                        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Digite sua senha" name="senha" required>
                    </div>
                </div>
                <div style="margin-left: 20px; flex-basis: 46%; margin-top: 7.99px;">
                    <div class="form-group" style="margin-bottom: 6%;">
                        <label for="cep">CEP</label>
                        <input type="text" class="form-control" id="cep" name="cep" placeholder="00000-000" pattern="\d{8}" required>
                    </div>

                    <div class="col-md-4 position-relative endereco" style="margin-bottom: 6%; width: auto;">
                        <label for="validationTooltip01" class="form-label">Endereço</label>
                        <input type="text" class="form-control" id="validationTooltip01" name="endereco" required>
                    </div>

                    <div class="col-md-4 position-relative bairro" style="margin-bottom: 6%; width: auto;">
                        <label for="validationTooltip01" class="form-label">Bairro</label>
                        <input type="text" class="form-control" id="validationTooltip01" name="bairro" required>
                    </div>

                    <div class="col-md-4 position-relative numero" style="margin-bottom: 6%; width: auto;">
                        <label for="validationTooltip01" class="form-label">Número</label>
                        <input type="number" class="form-control" id="number-input" name="numero" required>
                    </div>
                </div>
                <div class="mb-3" style="flex-basis: 44%;">
                    <button class="btn btn-primary" type="submit" disabled>Criar cadastro</button>
                </div>
            </form>
        </div>
    </body>
    <footer>
        <script>
            function mostrarCampos() {
                var select = document.getElementById("validationTooltip04");
                var selectEmail = document.getElementById("exampleInputEmail1");
                var selectSenha = document.getElementById("exampleInputPassword1");
                var opcaoSelecionada = select.options[select.selectedIndex].value;
                var divEmail = document.querySelector(".email");
                var divSenha = document.querySelector(".senha");

                if (opcaoSelecionada == "funcionario") {
                    selectEmail.setAttribute('required', true);
                    selectSenha.setAttribute('required', true);
                    divEmail.style.display = "block";
                    divSenha.style.display = "block";
                } else {
                    divEmail.style.display = "none";
                    divSenha.style.display = "none";
                    selectEmail.removeAttribute('required');
                    selectSenha.removeAttribute('required');
                }
                
            }
        </script>
        <script>
            function validarCampos() {
                // Selecionando todos os campos que são obrigatórios
                var requiredInputs = document.querySelectorAll('input[required], select[required]');

                // Habilitando ou desabilitando o botão de acordo com os campos preenchidos
                requiredInputs.forEach(function (input) {
                    input.addEventListener('input', function () {
                        var allFieldsFilled = true;
                        requiredInputs.forEach(function (input) {
                            if (!input.value) {
                                allFieldsFilled = false;
                            }
                        });

                        if (allFieldsFilled) {
                            document.querySelector('.btn-primary').removeAttribute('disabled');
                        } else {
                            document.querySelector('.btn-primary').setAttribute('disabled', true);
                        }
                    });
                });
            }
        </script>
    </footer>
</html>
