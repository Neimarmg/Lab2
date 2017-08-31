/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package C;

import M.Pessoa;
import V.Utilitarios.View;
import java.util.ArrayList;
/**
 *
 * @author 181100053
 */
public class PessoaCadastro{
    ArrayList<Pessoa> pessoa = new ArrayList<Pessoa>();
    
    public void CriaPessoa(){
        Pessoa pessoa = new Pessoa(
            View.digitaNumero("CodPessoa")
            ,View.digitaNumero("CodTipo")
            ,View.digitaNumero("CodProfissão")
            ,View.digitaNumero("CPF")
            ,View.digitaString("Nome")); 
        
    }
    
    public void insereListaPessoa(){
        pessoa.add();
    }
    

      
       
      

               
}
