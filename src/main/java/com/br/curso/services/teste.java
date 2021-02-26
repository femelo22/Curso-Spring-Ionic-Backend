package com.br.curso.services;

public class teste {

	public static void main(String[] args) {
		int[] vt = {3,5,1,2,6,7,9};
		
		int i = 0;
		
		int aux = 0;
		
		for(i=0;i<vt.length;i++) {
			for(int j=0; j<vt.length-1;j++) {
				if(vt[j]>vt[j+1]) {
					aux = vt[j];
					vt[j] = vt[j+1];
					vt[j+1] = aux;
				}
			}
		}
		
		System.out.println("vetor");
		
		for(i=0;i<vt.length;i++) {
			System.out.println(vt[i]);
		}
	}
}
