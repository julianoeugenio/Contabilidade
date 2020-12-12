package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Categoria;

public class CategoriaDAO {

    private Connection con;
    private PreparedStatement cmd;

    public int inserirCategoria(Categoria c) {
        try {
            String sql = "INSERT INTO categoria " + //adicionando dados
                    "(id,nome)"
                    + " VALUES (?,?) ";

            con = Conexao.Conectar();//abre a conexão
            cmd = con.prepareStatement(sql);//aqui as informações são pré compiladas para evitar invasões

            cmd.setInt(1, c.getId());
            cmd.setString(2, c.getNome());

            if (cmd.executeUpdate() > 0) {//Executa inserção no SGBD
                return 1;
            } else {
                return -1;
            }

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            return -1;

        } finally {//desconecta a conexão
            Conexao.Desconectar(con);
        }
    }

    public ArrayList consultarTodasCategorias(int id) {
        try {
            String sql = "SELECT * FROM categoria WHERE id=?"; //está selecionando a tabela
            con = Conexao.Conectar();
            cmd = con.prepareStatement(sql);

            cmd.setInt(1, id);

            ResultSet rs = cmd.executeQuery();
            ArrayList<model.Categoria> resultado = new ArrayList<>();
            while (rs.next()) {

                model.Categoria c = new model.Categoria();

                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));

                resultado.add(c);

            }
            return resultado;

        } catch (Exception e) {
            System.out.println("ERRO: " + e);
            return null;
        } finally {
            Conexao.Desconectar(con);
        }
    }

    public String consultarCategoria(int id) {
        try {
            String sql = "SELECT * FROM categoria WHERE id=?"; //está selecionando a tabela
            con = Conexao.Conectar();
            cmd = con.prepareStatement(sql);

            cmd.setInt(1, id);

            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {

                return rs.getString("nome");
            }
            return null;

        } catch (Exception e) {
            System.out.println("ERRO: " + e);
            return null;
        } finally {
            Conexao.Desconectar(con);
        }
    }

    public ArrayList consultarTodasCategorias() {
        try {
            String sql = "SELECT * FROM categoria"; //está selecionando a tabela
            con = Conexao.Conectar();
            cmd = con.prepareStatement(sql);

            ResultSet rs = cmd.executeQuery();
            ArrayList<model.Categoria> resultado = new ArrayList<>();
            while (rs.next()) {

                model.Categoria c = new model.Categoria();

                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));

                resultado.add(c);

            }
            return resultado;

        } catch (Exception e) {
            System.out.println("ERRO: " + e);
            return null;
        } finally {
            Conexao.Desconectar(con);
        }
    }

    public int UltimoId() {// Retorna ultimo identificador na tabela.
        try {
            String sql = "SELECT MAX(ID) as id FROM categoria"; //está selecionando no banco de dados
            con = Conexao.Conectar();
            cmd = con.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            rs.next();
            return rs.getInt("id");
        } catch (Exception e) {
            System.out.println("ERRO: " + e);
            return -1;
        } finally {
            Conexao.Desconectar(con);
        }

    }

}
