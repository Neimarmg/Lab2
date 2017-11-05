package Dao.Jdbc;

import static Dao.Jdbc.bdUtil.getSENHA;
import static Dao.Jdbc.bdUtil.getURL;
import static Dao.Jdbc.bdUtil.getUSUARIO;
import V.View;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lhries
 */
public class ConnectionFactory {
    static String sql;
    
    public static Connection getConnection(){
        Connection conexao = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conexao = DriverManager.getConnection(getURL(), getUSUARIO(), getSENHA());
            
        } catch (ClassNotFoundException ex) {
            View.msg("Erro de Sistema - Classe do Driver Nao Encontrada!");
            throw new BDException(ex);
        } catch (SQLException ex) {
            View.msg("Erro de Sistema - Problema na conexão do banco de dados");
            throw new BDException(ex);
        }
        return(conexao);
    }
        
    
    public  static  void executaSql(boolean  status){
        if (status == false){
            View.msgcr("Registro salvo com sucesso");
        }else{
            View.msgcr("Não foi possivel salvar registro");
        }           
    }

    
    public static String getSql() {
        return sql;
    }

    
    public static void setSql(String sql) {
        ConnectionFactory.sql = sql;
    }  
}
