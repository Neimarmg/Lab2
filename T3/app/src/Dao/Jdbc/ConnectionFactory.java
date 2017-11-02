package Dao.Jdbc;

import static Dao.Jdbc.bdUtil.getSENHA;
import static Dao.Jdbc.bdUtil.getURL;
import static Dao.Jdbc.bdUtil.getUSUARIO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lhries
 */
public class ConnectionFactory {
    
    public static Connection getConnection(){
        Connection conexao = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
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
