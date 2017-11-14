
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author neimarmoises
 */
public class Util {
   protected static int pk;
   private static Connection con = ConnectionFactory.getConnection();
           
   public static void setPk(String sqlId, boolean pesquisarId){
        try{       
            if (pesquisarId == true){
                PreparedStatement prepara = con.prepareStatement(sqlId);            
                ResultSet resultado = prepara.executeQuery(); //retorna resultado da consulta da query -> tipo ResultSet
                while(resultado.next()){ //buscando valor das colunas, registro por registro
                    Util.pk = resultado.getInt("id");
                } 
                ConnectionFactory.fechaConexao(con, prepara, true );
            
            }else{
                Util.pk = Integer.parseInt(sqlId);
            }
                    
        } catch(SQLException e){ 
                //se comando sql nao estiver correto ira imprimir o erro gerado
                e.printStackTrace();
        }
    }

   
    public static int getPk() {
        return pk;
    }
}
