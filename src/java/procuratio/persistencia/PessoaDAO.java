/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procuratio.persistencia;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import procuratio.negocio.Pessoa;
import procuratio.suporte.ConexaoBD;
import procuratio.suporte.IDAOT;

/**
 *
 * @author vitor
 */
public class PessoaDAO implements IDAOT<Pessoa> {

    ResultSet resultadoQ = null;

    @Override
    public boolean salvar(Pessoa o) {

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "";

            if (o.getId() == 0 && o.isTipo()) {

                sql = "INSERT INTO pessoa VALUES ("
                        + "(SELECT COALESCE(MAX(id), 0) + 1 FROM pessoa), "
                        + "" + o.getEnderecoId() + ", "
                        + "'" + o.getNome() + "', "
                        + "'" + o.getCpf() + "', "
                        + "'" + o.getGenero() + "', "
                        + "'" + o.isTipo() + "')";
            } else {
                sql = "INSERT INTO pessoa VALUES ("
                        + "(SELECT COALESCE(MAX(id), 0) + 1 FROM pessoa), "
                        + "" + o.getEnderecoId() + ", "
                        + "'" + o.getNome() + "', "
                        + "'" + o.getCpf() + "', "
                        + "'" + o.getGenero() + "', "
                        + "'" + o.isTipo() + "', "
                        + "'" + o.getEmail() + "', "
                        + "'" + o.getSenha() + "')";
            }
            System.out.println("SQL: " + sql + "\n");

            st.executeUpdate(sql);

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar pessoa: " + e + "\n");
            return false;
        }
    }

    @Override
    public boolean excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE pessoa SET "
                    + "situacao = 'I' "
                    + "WHERE id = " + id;

            System.out.println("SQL: " + sql + "\n");

            st.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Pessoa consultar(int id) {
        Pessoa pessoa = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM pessoa "
                    + "WHERE id = " + id;

            System.out.println("SQL: " + sql + "\n");

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                pessoa = new Pessoa();

                pessoa.setId(resultadoQ.getInt("id"));
                pessoa.setEnderecoId(resultadoQ.getInt("endereco_id"));
                pessoa.setNome(resultadoQ.getString("nome"));
                pessoa.setCpf(resultadoQ.getString("cpf"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar pessoa: " + e + "\n");
        }
        return pessoa;
    }

    public boolean autentificarCpf(String cpf, int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT EXISTS("
                    + "SELECT * "
                    + "FROM pessoa "
                    + "WHERE cpf = '" + cpf + "' "
                    + "AND id != " + id + ")";

            System.out.println("SQL: " + sql + "\n");

            resultadoQ = st.executeQuery(sql);

            resultadoQ.next();

            return resultadoQ.getBoolean("exists");
        } catch (Exception e) {
            return false;
        }
    }

    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[5];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Valor de Cobrança";
        cabecalho[3] = "Tipo";
        cabecalho[4] = "Situação";

        // cria matriz de acordo com nº de registros da tabela
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT count(*) "
                    + "FROM pessoa p LEFT JOIN funcionario f ON p.funcionario_id=f.id "
                    + "LEFT JOIN cliente c ON p.cliente_id=c.id "
                    + "WHERE nome ILIKE '%" + criterio + "%'";

            System.out.println("SQL: " + sql + "\n");

            resultadoQ = st.executeQuery(sql);

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][5];

        } catch (Exception e) {
            System.out.println("Erro ao consultar tabela pessoa: " + e + "\n");
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT p.id, p.nome, c.valor_cobranca, p.funcionario_id, p.situacao "
                    + "FROM pessoa p LEFT JOIN funcionario f ON p.funcionario_id=f.id "
                    + "LEFT JOIN cliente c ON p.cliente_id=c.id "
                    + "WHERE p.nome ILIKE '%" + criterio + "%' "
                    + "ORDER BY p.nome";

            System.out.println("SQL: " + sql + "\n");

            resultadoQ = st.executeQuery(sql);

            while (resultadoQ.next()) {
                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("nome");
                dadosTabela[lin][2] = String.valueOf(resultadoQ.getDouble("valor_cobranca")).replace('.', ',');
                if (resultadoQ.getInt("funcionario_id") == 0) {
                    dadosTabela[lin][3] = "Cliente";
                } else {
                    dadosTabela[lin][3] = "Funcionario";
                }
                dadosTabela[lin][4] = resultadoQ.getString("situacao");

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela pessoa...\n");
        }

        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tabela.setSelectionMode(0);
    }

    @Override
    public Integer consultarUltimoId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
