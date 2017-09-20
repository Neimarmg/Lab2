/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package C;

import M.Pessoa;
import V.Utilitarios.View;
/**
 *
 * @author 181100053
 */
public class CadastroPessoa{
    
    public void CriaPessoa(){
        new Pessoa(
            View.digitaNumero("CodPessoa")
            ,View.digitaNumero("CodTipo")
            ,View.digitaNumero("CPF")
            ,View.digitaString("Nome"));      
    }
    

    
    

      
       
      

               
}
