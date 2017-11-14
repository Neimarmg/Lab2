/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.Jdbc;

/**
 *
 * @author neimarmoises
 */
public class bdConect {
    private final static String HOST = "localhost";
    private final static String PORT = "3306";
    private final static String BD = "bdT3";
    private final static String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+BD;
    private final static String USUARIO = "root";
    private final static String SENHA = "";
    
    
    /*private final static String HOST = "mysql.pep.kinghost.net";
    private final static String PORT = "3306";
    private final static String BD = "pep";
    private final static String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+BD;
    private final static String USUARIO = "pep";
    private final static String SENHA = "84d235g4r8h";*/


    public static String getHOST() {
        return HOST;
    }

    public static String getPORT() {
        return PORT;
    }

    public static String getBD() {
        return BD;
    }

    public static String getURL() {
        return URL;
    }

    public static String getUSUARIO() {
        return USUARIO;
    }

    public static String getSENHA() {
        return SENHA;
    }
    
    
}
