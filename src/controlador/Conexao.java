package controlador;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection Conectar() {
        try {

            String DRIVER = "org.sqlite.JDBC";
            String URL = "jdbc:sqlite:carteira.db";
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL);

        } catch (Exception e) {
            return null;
        }

    }

    public static void Desconectar(Connection con) {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }

    }

}
