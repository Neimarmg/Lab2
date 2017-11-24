/*Pesquisar 15ap, hanna, vora*/
package App;



import C.Controlador;
import M.Negocio.Globais;
import V.Utilitarios.Dia;
import V.View;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {
  @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaInicial.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("MY MONEY - MENU INICIAL");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    /*
    public static void main(String[] args) throws Exception {

           View.msgcr("\n\n                           PROGRAMA DE VENDAS\n\n" );
           Dia.defineSaudacao();
           View.msgl();
           new Controlador().selecionaMenu(true);
           new Controlador().carregaApp();           
               }
    */
}
