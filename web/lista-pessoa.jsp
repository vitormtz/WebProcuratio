<%-- 
    Document   : lista-pessoa
    Created on : 23 de abr. de 2023, 21:54:26
    Author     : vitor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Pessoa</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="Imagens/loja.svg"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    </head>
    <body>
        <table border="1" width="90%">
            <tr>
                <th>Id</th>
                <th>endereço id</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Genero</th>
                <th>Tipo</th>
                <th>Email</th>
                <th>Senha</th>
            </tr>

            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <%@ page import="java.sql.*" %>

            <%
                // Define as credenciais de acesso ao banco de dados Postgres
                String url = "jdbc:postgresql://localhost:5432/procuratio";
                String usuario = "postgres";
                String senha = "postgres";

                // Faz a conexão com o banco de dados
                Connection conexao = DriverManager.getConnection(url, usuario, senha);

                // Cria uma consulta SQL para obter todas as pessoas na tabela "pessoas"
                String sql = "SELECT * FROM pessoa";
                PreparedStatement stmt = conexao.prepareStatement(sql);

                // Executa a consulta e armazena os resultados em um objeto ResultSet
                ResultSet rs = stmt.executeQuery();
            %>

            <!-- Itera sobre os resultados da consulta usando a tag "forEach" -->
            <c:forEach items="${rs}" var="pessoa">
                <p>id: ${pessoa.id}</p>
                <p>endereco_id: ${pessoa.endereco_id}</p>
                <p>nome ${pessoa.nome}</p>
                <p>cpf: ${pessoa.cpf}</p>
                <p>genero: ${pessoa.genero}</p>
                <p>tipo: ${pessoa.tipo}</p>
                <p>email: ${pessoa.email}</p>
                <p>senha: ${pessoa.senha}</p>
            </c:forEach>

            <%
                // Fecha o ResultSet, PreparedStatement e Connection para liberar recursos
                rs.close();
                stmt.close();
                conexao.close();
            %>
        </table>   
    </body>
</html>
