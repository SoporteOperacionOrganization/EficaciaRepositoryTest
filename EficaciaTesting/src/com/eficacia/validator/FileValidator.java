package com.eficacia.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.eficacia.model.File;

@Component
public class FileValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return File.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		File file = (File) obj;
		
		if(file.getArchivo() != null){
            if (file.getArchivo().getSize() == 0) {
                errors.rejectValue("archivo", "NotEmpty.file.archivo");
            }
        }
		
	}

}
