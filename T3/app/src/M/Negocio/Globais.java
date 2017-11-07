/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M.Negocio;

/**
 *
 * @author neimarmoises
 */
public class Globais {    
    private static int contador=0;
    
    public static int getContador ( boolean  contar, boolean limparAntes) {
        
        if (contar ==  true ) {
            if(limparAntes == true){
               Globais.contador = 0 ;
               Globais.contador = 1 ;
            }else{
                Globais.contador ++ ; // Conta ações em tempo de execução
            }        
        } else {
                 Globais.contador =  0 ; // Limpa variável contadora
        }        
      
        return Globais.contador;
    }

    public static int getContador() {
        return contador;
    }
    
    
    
}
