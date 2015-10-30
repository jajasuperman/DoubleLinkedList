package org.minakdev.aktoreak;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Aktoreak {

	private static Aktoreak nireAktoreak;
	private AktoreZerrenda aktoreZerrenda;

	private Aktoreak() {
		aktoreZerrenda = new AktoreZerrenda();
	}

	public static Aktoreak getNireAktoreak() {
		if (nireAktoreak == null) {
			nireAktoreak = new Aktoreak();
		}
		return nireAktoreak;
	}
	
	public ArrayList<Aktorea> getAktoreZerrenda() {
		return this.aktoreZerrenda.getZerrenda();
	}
	
	public void sortuAktorea(Scanner pFitxategi) {
		HashMap<String, Pelikula> hashTable = new HashMap<String, Pelikula>();
		int k=0;
		while(pFitxategi.hasNext()){
			System.out.println(k++);
			String pAktoreaPelikulak = pFitxategi.nextLine();
			String [] atalak = pAktoreaPelikulak.split("\\s*###\\s*");
			
			Aktorea aktoreBerria = new Aktorea(atalak[0]);
			
			for (int i = 1; i < atalak.length; i++) {
				String pelikulaIzena = atalak[i];
				Pelikula pelikula = hashTable.get(pelikulaIzena);
			
				if(pelikula == null) {
					pelikula = new Pelikula(pelikulaIzena);
					Pelikulak.getNirePelikulak().sartuPelikula(pelikula);
					hashTable.put(pelikulaIzena, pelikula);
				}
				
				aktoreBerria.sartuPelikula(pelikula);
				pelikula.sartuAktorea(aktoreBerria);
			}
			
			aktoreZerrenda.aktoreaSartu(aktoreBerria);
		}
	}
	
	public void aktoreaSartu(Aktorea pAktorea) {
		aktoreZerrenda.aktoreaSartu(pAktorea);
	}
	
	public Aktorea aktoreaBilatu (String izena) {
		return aktoreZerrenda.aktoreaBilatu(izena);
		
	}
	
	public void aktoreakOrdenatu() {
		 this.aktoreZerrenda.aktoreakOrdenatu();
	}
	
	public void aktoreaEzabatu(String izena){
		Aktorea aktorea=this.aktoreaBilatu(izena);
		if (aktorea==null){
			System.out.println("Ezin da aktorea ezabatu ez baitago.");
		}
		else{
			aktorea.pelikuletatikEzabatu2();
			aktoreZerrenda.aktoreaEzabatu(aktorea);
		}
		
	}
	
	public void aktoreaTxertatu(String izena){
		aktoreZerrenda.aktoreaTxertatu(izena);
	}
	
	//Junit-ak probatzeko
	
	public void erreseteatu(){
		this.aktoreZerrenda.erreseteatu();
	}
	
	public int luzera(){
		return this.aktoreZerrenda.luzera();
	}
}
