/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procuratio.controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import procuratio.negocio.Endereco;
import procuratio.negocio.Pessoa;
import procuratio.persistencia.EnderecoDAO;
import procuratio.persistencia.PessoaDAO;

/**
 *
 * @author vitor
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller", "/home", "/cadastraPessoa", "/consultaCliente", "/consultaFuncionario", "/vendeProduto", "/cadastraPessoaBD"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controller</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controller at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/home")) {
            response.sendRedirect("index.html");
        }
        if (action.equals("/cadastraPessoa")) {
            response.sendRedirect("cadastro-pessoa.jsp");
        }
        if (action.equals("/consultaCliente")) {
            response.sendRedirect("lista-pessoa.jsp");
        }
        if (action.equals("/consultaFuncionario")) {
            response.sendRedirect("lista-pessoa.jsp");
        }
        if (action.equals("/vendeProduto")) {
            response.sendRedirect("venda-produto.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();
        PessoaDAO pesDAO = new PessoaDAO();
        EnderecoDAO endDAO = new EnderecoDAO();
        Pessoa pessoa = new Pessoa();
        Endereco endereco = new Endereco();

        if (action.equals("/cadastraPessoaBD")) {
            endereco.setCep(request.getParameter("cep"));
            endereco.setEndereco(request.getParameter("endereco"));
            endereco.setBairro(request.getParameter("bairro"));
            endereco.setNumero(request.getParameter("numero"));
            endDAO.salvar(endereco);

            if (request.getParameter("tipoCadastro").equals("cliente")) {
                pessoa.setEnderecoId(endDAO.consultarUltimoId());
                pessoa.setNome(request.getParameter("nome"));
                pessoa.setCpf(request.getParameter("cpf"));
                pessoa.setGenero(request.getParameter("genero"));
                pessoa.setTipo(true);
                if (pesDAO.salvar(pessoa)) {
                    response.sendRedirect("index.html");
                } else {
                    response.sendRedirect("index.html");
                }
            } else {
                pessoa.setEnderecoId(endDAO.consultarUltimoId());
                pessoa.setNome(request.getParameter("nome"));
                pessoa.setCpf(request.getParameter("cpf"));
                pessoa.setGenero(request.getParameter("genero"));
                pessoa.setTipo(false);
                pessoa.setEmail(request.getParameter("email"));
                pessoa.setSenha(request.getParameter("senha"));
                if (pesDAO.salvar(pessoa)) {
                    response.sendRedirect("index.html");
                } else {
                    response.sendRedirect("index.html");
                }
            }
        }
    }
}
