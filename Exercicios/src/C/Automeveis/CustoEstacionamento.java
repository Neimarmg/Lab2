package C.Automeveis;


import M.CustosHorarios;
import V.View;

public class CustoEstacionamento extends CustosHorarios{
	
	public static void insereDados(){
		setValorHora(View.digitaFloat("Valor hora"));
		setCustoAdicional(View.digitaFloat("Custo adicional"));
		setPeriodoCustoAdicional(View.digitaFloat("Tempo de combrança adicional"));
		setTempoEstadia(View.digitaFloat("Tempo estadia"));
			
	}

	public static void calculaEstadia(){
		
		insereDados();
		
	}
	
	
	public static void imprimir(boolean imprimir){
		calculaEstadia();
		
		
	}
}
