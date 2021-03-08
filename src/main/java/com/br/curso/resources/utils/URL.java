package com.br.curso.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static List<Integer> decodeIntList(String s){
		String[] vet = s.split(",");
		List<Integer> list = new ArrayList<>();
		for(int i=0;i<vet.length;i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		
		return list;
		
		//LAMBDA, FAZ A MESMA COISA QUE O CÓDIGO ACIMA
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
	
	public static String decodeParam(String param) {
		
		try {
			return URLDecoder.decode(param, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	
}
