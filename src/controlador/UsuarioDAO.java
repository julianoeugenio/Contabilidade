package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Usuario;

public class UsuarioDAO {

    private Connection con;
    private PreparedStatement cmd;

    public int inserirUsuario(Usuario u) {
        try {
            String sql = "INSERT INTO usuario " + //adicionando dados
                    "(id,nome,senha)"
                    + " VALUES (?,?,?) ";

            con = Conexao.Conectar();//abre a conexão
            cmd = con.prepareStatement(sql);//aqui as informações são pré compiladas para evitar invasões

            cmd.setInt(1, u.getId());
            cmd.setString(2, u.getNome());
            cmd.setString(3, u.getSenha());

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

    public model.Usuario consultarUsuario(int id) {
        try {
            String sql = "SELECT * FROM usuario WHERE id=?"; //está selecionando a tabela
            con = Conexao.Conectar();
            cmd = con.prepareStatement(sql);

            cmd.setInt(1, id);

            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                model.Usuario u = new model.Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                return u;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("ERRO: " + e);
            return null;
        } finally {
            Conexao.Desconectar(con);
        }
    }
    
        public model.Usuario ValidaUsuario(String nome, String senha) {
        try {
            String sql = "SELECT * FROM usuario WHERE nome=? AND senha=?"; //está selecionando a tabela
            con = Conexao.Conectar();
            cmd = con.prepareStatement(sql);

            cmd.setString(1, nome);
            cmd.setString(2, senha);

            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                model.Usuario u = new model.Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                return u;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("ERRO: " + e);
            return null;
        } finally {
            Conexao.Desconectar(con);
        }
    }

    public int UltimoId() {// Retorna ultimo identificador na tabela.
        try {
            String sql = "SELECT MAX(ID) as id FROM usuario"; //está selecionando no banco de dados
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
