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

import br.com.pocs3aws.domain.Bucket;
import br.com.pocs3aws.service.BucketService;

@RestController
public class BucketController {

	@Autowired
	private BucketService bucketService;
	
	@PostMapping("/buckets")
	@ResponseStatus(code = HttpStatus.CREATED, reason = "Bucket criado com sucesso!")
	public void criarBucket(@RequestBody Bucket bucket) {
		bucketService.criarBucket(bucket.getNome());
	}
	
	@GetMapping("/buckets")
	public List<String> listar(){
		return bucketService.listarBucket();
	}
	
	@DeleteMapping("/buckets/{nomeBucket}")
	@ResponseStatus(code = HttpStatus.OK, reason = "Bucket excluído com sucesso!")
	public void excluirBucket(@PathVariable String nomeBucket) {
		bucketService.deletarBucket(nomeBucket);
	}
}
