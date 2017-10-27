package Dao.Jdbc;

import Dao.Jdbc.bdUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lhries
 */
public class ConnectionFactory extends  bdUtil{
    
    public static Connection getConnection(){
        Connection conexao = null;
        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(getURL(), getUSUARIO(), getSENHA());
            
        } catch (ClassNotFoundException ex) {
            System.err.println("Erro de Sistema - Classe do Driver Nao Encontrada!");
            throw new BDException(ex);
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema na conexão do banco de dados");
            throw new BDException(ex);
        }
        return(conexao);
    }
    
}
