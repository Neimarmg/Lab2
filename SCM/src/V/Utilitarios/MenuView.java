/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package V.Utilitarios;

import C.Pessoas.Pessoas.Cadastro;
import M.Menu;

/**
 *
 * @author 181100053
 */
public class MenuView {
     Menu menu = new Menu();
            
    public  void menuPrincipal(){
        menu.setDescMenu(
                 "\n 1 - Cadastro paciente"
                +"\n 2 - Cadastro de medicamento"
                +"\n 3 - Agenda de consultas"
                +"\n 4 - Registro de consultas"
                +"\n 5 - Histórico de consultas"
                +"\n 6 - Sair\n"        
        );
        View.msgr(menu.getDescMenu());
    }   
    
  
    public  void menuCadastroPacientes(){
        menu.setDescMenu(
                 "\n 1 - Adiconaonar paciente"
                +"\n 2 - Visualizar pacientes"
                +"\n 3 - Voltar"
                +"\n 4 - Sair\n"        
        );
        View.msgr(menu.getDescMenu());
    }   
         
    
    public  void menuCadastroMedicamentos(){
        menu.setDescMenu(
                 "\n 1 - Adicionar medicamentos"
                +"\n 2 - Visualizar Medicamentos"
                +"\n 3 - Voltar"
                +"\n 4 - Sair\n" 
                
        );
        View.msgr(menu.getDescMenu());
    }   
    
    
    public  void menuAgendamentosConsultas(){
        menu.setDescMenu(
                 "\n 1 - Agendar Consultas"
                +"\n 2 - Visualizar consultas"
                +"\n 3 - Voltar"
                +"\n 4 - Sair\n" 
                
        );
        View.msgr(menu.getDescMenu());
    }  
    
    
    public void carregaMenu(){      
        
        switch (menu.getCod()) {
            case 1:
                menuCadastroPacientes();
                break;
            
            case 2:
                menuCadastroMedicamentos();
                break;
 
            case 3:
                menuAgendamentosConsultas();
                break;
            
            case 6:
                View.sair();
                break;
                
            default:
                View.opcaoInvalida();
                carregaMenu();
                break;
            }
    }
    
}
