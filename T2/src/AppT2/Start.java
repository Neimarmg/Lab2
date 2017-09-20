/*Pesquisar 15ap, hanna, vora*/
package AppT2;



import C.Controlador;
import V.Utilitarios.Dia;
import V.Utilitarios.View;

public class Start {
   
    public static void main(String[] args) {

            View.msgcr("\n\nPrograma de consultório médico\n\n" );
            new Controlador().selecionaMenu(true);
            Dia.defineSaudacao();
            View.msgl();           

    }
}
