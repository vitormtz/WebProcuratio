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
import procuratio.negocio.Endereco;
import procuratio.suporte.ConexaoBD;
import procuratio.suporte.IDAOT;

/**
 *
 * @author vitor
 */
public class EnderecoDAO implements IDAOT<Endereco> {

    ResultSet resultadoQ = null;

    @Override
    public boolean salvar(Endereco o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "";

            if (o.getId() == 0) {
                sql = "INSERT INTO endereco VALUES ("
                        + "(SELECT COALESCE(MAX(id), 0) + 1 FROM endereco), "
                        + "'" + o.getCep() + "', "
                        + "'" + o.getEndereco() + "', "
                        + "'" + o.getBairro() + "', "
                        + "'" + o.getNumero() + "')";
            } else {
                sql = "UPDATE endereco SET "
                        + "cep = '" + o.getCep() + "', "
                        + "endereco = '" + o.getEndereco() + "', "
                        + "bairro = '" + o.getBairro() + "', "
                        + "numero = '" + o.getNumero() + "', "
                        + "WHERE id = " + o.getId();
            }

            System.out.println("SQL: " + sql + "\n");

            st.executeUpdate(sql);

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar endereço: " + e + "\n");
            return false;
        }
    }

    @Override
    public boolean excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "DELETE FROM endereco "
                    + "WHERE id = " + id;

            System.out.println("SQL: " + sql + "\n");

            st.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Endereco consultar(int id) {
        Endereco endereco = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM endereco "
                    + "WHERE id = " + id;

            System.out.println("SQL: " + sql + "\n");

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                endereco = new Endereco();

                endereco.setId(resultadoQ.getInt("id"));
                endereco.setCep(resultadoQ.getString("cep"));
                endereco.setEndereco(resultadoQ.getString("endereco"));
                endereco.setBairro(resultadoQ.getString("bairro"));
                endereco.setNumero(resultadoQ.getString("numero"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar endereço: " + e + "\n");
        }
        return endereco;
    }

    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;
        // cabecalho da tabela
        Object[] cabecalho = new Object[7];
        cabecalho[0] = "Código";
        cabecalho[1] = "CEP";
        cabecalho[2] = "Cidade";
        cabecalho[3] = "Endereço";
        cabecalho[4] = "Bairro";
        cabecalho[5] = "Número";
        cabecalho[6] = "Complemento";

        // cria matriz de acordo com nº de registros da tabela
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT COUNT(*) "
                    + "FROM endereco e INNER JOIN cidade c ON e.cidade_id=c.id  "
                    + "WHERE c.nome ILIKE '%" + criterio + "%'";

            System.out.println("SQL: " + sql + "\n");

            resultadoQ = st.executeQuery(sql);

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][7];

        } catch (Exception e) {
            System.out.println("Erro ao consultar tabela endereço: " + e + "\n");
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT e.id, e.cep, c.nome, e.endereco, e.bairro, e.numero, e.complemento "
                    + "FROM endereco e INNER JOIN cidade c ON e.cidade_id=c.id "
                    + "WHERE c.nome ILIKE '%" + criterio + "%' "
                    + "ORDER BY c.nome";

            System.out.println("SQL: " + sql + "\n");

            resultadoQ = st.executeQuery(sql);

            while (resultadoQ.next()) {
                dadosTabela[lin][0] = resultadoQ.getString("id");
                dadosTabela[lin][1] = resultadoQ.getString("cep");
                dadosTabela[lin][2] = resultadoQ.getString("nome");
                dadosTabela[lin][3] = resultadoQ.getString("endereco");
                dadosTabela[lin][4] = resultadoQ.getString("bairro");
                dadosTabela[lin][5] = resultadoQ.getString("numero");
                dadosTabela[lin][6] = resultadoQ.getString("complemento");

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela endereço...\n");
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
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT MAX(id) "
                    + "FROM endereco";

            System.out.println("SQL: " + sql + "\n");

            resultadoQ = st.executeQuery(sql);

            resultadoQ.next();

            return resultadoQ.getInt(1);
        } catch (Exception e) {
            System.out.println("Erro ao consultar funcionario: " + e + "\n");
            return null;
        }
    }
}
