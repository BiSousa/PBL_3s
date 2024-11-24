package Models;

// Importando as bibliotecas necessárias
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=DadosDoProjeto;encrypt=true;trustServerCertificate=true";
    private static final String USER = "userapp";
    private static final String PASSWORD = "Santos2011.";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao estabelecer conexão com o banco de dados: " + e.getMessage());
        }
        return connection;
    }
}
