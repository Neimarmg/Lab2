/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplotableview;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ExemploTableView extends Application {
    private TableView table;
    
    @Override
    public void start(Stage primaryStage) {
        ObservableList<PacienteTableModel> lista = 
                FXCollections.observableArrayList(
                        new PacienteTableModel("Fulano","1010","20/01/2000"),
                        new PacienteTableModel("Sicrano","2020","20/02/2000"),
                        new PacienteTableModel("Beltrano","3030","20/04/2000")
                );
        
        table = new TableView();
        
        TableColumn colunaNome = new TableColumn("Nome");
        colunaNome.setMinWidth(100);
        colunaNome.setCellValueFactory(
                new PropertyValueFactory<PacienteTableModel,String>("nome")
        );
        
        TableColumn colunaRg = new TableColumn("RG");
        colunaRg.setMinWidth(100);
        colunaRg.setCellValueFactory(
                new PropertyValueFactory<PacienteTableModel,String>("rg")
        );
        TableColumn colunaDataNasc = new TableColumn("Data Nascimento");
        colunaDataNasc.setMinWidth(150);
        colunaDataNasc.setCellValueFactory(
                new PropertyValueFactory<PacienteTableModel,String>("dataNasc")
        );
        
        table.setItems(lista);
        
        table.getColumns().addAll(colunaNome, colunaRg, colunaDataNasc);
        
        VBox vbox = new VBox();
        vbox.getChildren().add(table);
        
        Scene scene = new Scene(vbox, 500, 250);
        
        primaryStage.setTitle("Exemplo Table View");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    

}
