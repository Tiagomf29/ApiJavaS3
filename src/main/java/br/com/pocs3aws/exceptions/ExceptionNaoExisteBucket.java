package br.com.pocs3aws.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bucket não existe e por isso a operação não pode ser concluído.")
public class ExceptionNaoExisteBucket extends RuntimeException{

	private static final long serialVersionUID = 1L;

}
