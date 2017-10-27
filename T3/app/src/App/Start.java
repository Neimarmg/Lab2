/*Pesquisar 15ap, hanna, vora*/
package App;



import C.Controlador;
import V.Utilitarios.Dia;
import V.View;

public class Start {
   
    public static void main(String[] args) throws Exception {

           View.msgcr("\n\n                           PROGRAMA DE VENDAS\n\n" );
           Dia.defineSaudacao();
           View.msgl();
           new Controlador().selecionaMenu(true);
           new Controlador().carregaApp();
    }
}
