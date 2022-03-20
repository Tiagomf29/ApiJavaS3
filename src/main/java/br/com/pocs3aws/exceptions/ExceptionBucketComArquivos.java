package br.com.pocs3aws.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Bucket possui Arquivos e por isso não pode ser excluído. Operação não foi concluída!")
public class ExceptionBucketComArquivos extends RuntimeException{

	private static final long serialVersionUID = 1L;

}
