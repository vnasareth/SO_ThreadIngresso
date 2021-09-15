package Principal;

import java.util.concurrent.Semaphore;

import controller.ThreadIngresso;


public class SistemaCompraIngresso {

	public static void main(String[] args) {
		
	       Semaphore aguardo = new Semaphore (1);
			
		for(int idCliente = 1; idCliente <= 300; idCliente++) {
			Thread ingressos = new ThreadIngresso(idCliente, aguardo);
			ingressos.start();
			
		}

	}

}