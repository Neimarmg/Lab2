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
   static int pk;
           
   public static void setPk(Connection connection,PreparedStatement prepara){
        try{       
            connection.prepareStatement(ConnectionFactory.getSql());
            
            ResultSet resultado = prepara.executeQuery(); //retorna resultado da consulta da query -> tipo ResultSet

            while(resultado.next()){ //buscando valor das colunas, registro por registro

                   Util.pk = resultado.getInt("id");

         }
        ConnectionFactory.fechaConexao(connection, prepara, true );


        } catch(SQLException e){ 
                //se comando sql nao estiver correto ira imprimir o erro gerado
                e.printStackTrace();
        } 
    }

    public static int getPk() {
        return pk;
    }
}
