package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Carteira;


public class CarteiraDAO {

    private Connection con;
    private PreparedStatement cmd;

    public int inserirDinheiro(Carteira c) {
        try {
            String sql = "INSERT INTO carteira " + //adicionando dados
                    "(id,id_usuario,id_categoria,valor,comentario)"
                    + " VALUES (?,?,?,?,?) ";

            con = Conexao.Conectar();//abre a conexão
            cmd = con.prepareStatement(sql);//aqui as informações são pré compiladas para evitar invasões

            cmd.setInt(1, c.getId());
            cmd.setInt(2, c.getId_usuario());
            cmd.setInt(3, c.getId_categaria());

            cmd.setDouble(4, c.getValor());
            cmd.setString(5, c.getComentario());

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

    public ArrayList consultarCarteira(int id_usuario) {
        try {
            String sql = "SELECT * FROM carteira WHERE id_usuario=?"; //está selecionando a tabela
            con = Conexao.Conectar();
            cmd = con.prepareStatement(sql);

            cmd.setInt(1, id_usuario);

            ResultSet rs = cmd.executeQuery();
            ArrayList<model.Carteira> resultado = new ArrayList<>();
            while (rs.next()) {

                model.Carteira c = new model.Carteira();

                c.setId(rs.getInt("id"));
                c.setId_usuario(rs.getInt("id_usuario"));
                c.setId_categaria(rs.getInt("id_categoria"));

                c.setValor(rs.getDouble("valor"));
                c.setComentario(rs.getString("comentario"));

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
    
        public int removerCarteira(int id) {
        try {
            String sql = "DELETE FROM carteira WHERE id=?"; //está selecionando a tabela
            con = Conexao.Conectar();
            cmd = con.prepareStatement(sql);

            cmd.setInt(1, id);

            if (cmd.executeUpdate() > 0) {
                return 1;
            } else {
                return -1;
            }
            

        } catch (Exception e) {
            System.out.println("ERRO: " + e);
            return -1;
        } finally {
            Conexao.Desconectar(con);
        }
    }

    public int UltimoId() {// Retorna ultimo identificador na tabela.
        try {
            String sql = "SELECT MAX(ID) as id FROM carteira"; //está selecionando no banco de dados
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
