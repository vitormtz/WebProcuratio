/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procuratio.suporte;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    private static ConexaoBD instancia = null;
    private Connection conn = null;

    private ConexaoBD() {
        try {
            // Registrar o driver JDBC do PostgreSQL
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver do banco de dados não encontrado", e);
        }
    }

    // Retorna instância
    public static synchronized ConexaoBD getInstance() {
        if (instancia == null) {
            instancia = new ConexaoBD();
        }
        return instancia;
    }

    // Retorna conexão
    public synchronized Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            // Estabelecer a conexão com o banco de dados
            String url = "jdbc:postgresql://localhost:5432/procuratio";
            String user = "postgres";
            String password = "postgres";
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }

    // Efetua fechamento da conexão
    public synchronized void shutDown() {
        try {
            if (conn != null) {
                conn.close();
            }
            instancia = null;
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão com banco de dados: " + e.getMessage());
        }
    }
}
