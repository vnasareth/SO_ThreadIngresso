package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadIngresso extends Thread {

	Random ger = new Random();
	private int idCliente;
	private Semaphore aguardo;
	private static int quantIngressos = 100;
	int compraIngresso = ger.nextInt(4) + 1;
	
	public ThreadIngresso() {
		
	}
	
	public ThreadIngresso(int idComprador, Semaphore espera) {
		this.idCliente = idComprador;
		this.aguardo = espera;
		
	}
	
	@Override
	public void run() {
		login();
		try {
			aguardo.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			aguardo.release();
		}
	}
	
	private void login() {
//		System.out.println("===== Comprador Novo =====");
		int tempo = (int) ((ger.nextInt(2000) + 50));
		if(tempo < 1000) {
			try {
				sleep(tempo);
				comprandoIngresso();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Compra não realizada");
		}
	}
	
	private void comprandoIngresso() {
		System.out.println("#" + idCliente + " Comprando Ingresso");
		int tempo = (int) ((ger.nextInt(3000) + 1000));
		if(tempo < 2500) {
			try {
				sleep(tempo);
				validacaoCompra();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}else {
			System.out.println("Compra não realizada");
		}
	}
	
	private void validacaoCompra() {
		if(quantIngressos > 0 && compraIngresso <= quantIngressos) {
			quantIngressos -= compraIngresso;
			System.out.println("Compra realizada com sucesso! de " + compraIngresso + " ingressos");
			System.out.println("Ingressos Disponíveis: " + quantIngressos);
		}
	}
}
