package br.com.pocs3aws.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Já existe um bucket na AWS criado com este nome. Operação não foi concluída!")
public class ExceptionExisteBucket extends RuntimeException{

	private static final long serialVersionUID = 1L;

}
