package C.Automeveis;

import M.Triangulo;
import V.View;

public class TrianguloCalculos extends Triangulo{
	
	
	public static void insereMedidaArea(boolean imprimir){
		Triangulo.setLado1(View.digitaFloat("Lado 1 triangulo"));
		Triangulo.setLado2(View.digitaFloat("Lado 2 triangulo"));
		Triangulo.setLado3(View.digitaFloat("Lado 3 triangulo"));		
	}
	
	
	public static void totalizaArea(){
		setArea(getLado1()+getLado2()+getLado3());
	}
	
	
	public static void calculaPerimentro(){		
		setPerimetro((float) 
				(Math.sqrt((
					(getArea()- getLado1())*
					(getArea()- getLado2())*
					(getArea()- getLado2()))
				))
			/3);
	}
	
	
	public static void calculaSemiperimetro(){
		setSemiperimetro(getPerimetro()/3); 
	}
	
	
	public static void calculaTriangulo(boolean imprimir){
		insereMedidaArea(imprimir);
		totalizaArea();
		calculaPerimentro();
		calculaSemiperimetro();
	}

	
	public static void imprimir(boolean imprimir){
		calculaTriangulo(imprimir);
		if(imprimir =true){
			View.msgcr("Area "+ getArea());
			View.msgr("\nPerimetro "+ getPerimetro());
			View.msgr("\nSemiPerimetro "+ getSemiperimetro());
		}
	}
}
