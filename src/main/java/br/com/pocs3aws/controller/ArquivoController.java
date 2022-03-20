package br.com.pocs3aws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pocs3aws.domain.Arquivo;
import br.com.pocs3aws.service.ArquivoService;

@RestController
public class ArquivoController {

	
	@Autowired
	private ArquivoService arquivoService;
	
	@PostMapping("/arquivos/{nomeBucket}")
	@ResponseStatus(code = HttpStatus.CREATED, reason = "Arquivo enviado com sucesso!")
	public void criarArquivo(@PathVariable String nomeBucket, @RequestBody Arquivo arquivo) {

		arquivoService.enviarArquivos(nomeBucket, 
									  arquivo.getDiretorioArquivoS3(), 
									  arquivo.getDiretorioArquivocomputador());
	}
	
	@GetMapping("/arquivos/{nomeBucket}")
	public List<String> listar(@PathVariable String nomeBucket){
		return arquivoService.listarArquivos(nomeBucket);
	}
	
	@DeleteMapping("/arquivos/{nomeBucket}")
	@ResponseStatus(code = HttpStatus.OK, reason = "Arquivo excluído com sucesso!")
	public void excluirArquivo(@PathVariable String nomeBucket, @RequestBody Arquivo arquivo) {
		arquivoService.deletarArquivo(nomeBucket, arquivo.getDiretorioArquivoS3());
	}
}
