package com.eficacia.model;

import org.springframework.web.multipart.MultipartFile;

public class File {

	private MultipartFile archivo;
	
	public MultipartFile getArchivo(){
		return archivo;
	}
	public void setArchivo(MultipartFile archivo){
		this.archivo = archivo;
	}
}
