package br.com.pocs3aws.service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import br.com.pocs3aws.exceptions.ExceptionNaoExisteArquivo;
import br.com.pocs3aws.exceptions.ExceptionNaoExisteBucket;

@Service
public class ArquivoService {

	@Autowired
	private AmazonS3 amazonS3;
	
	public void enviarArquivos(String nomeBucket, String destinoBucket, String origemArquivo) {
		if(!amazonS3.doesBucketExistV2(nomeBucket)) {
			throw new ExceptionNaoExisteBucket();
		}else
			amazonS3.putObject(nomeBucket,destinoBucket,new File(origemArquivo));
	}
	
	public List<S3ObjectSummary>listarArquivos(String nomeBucket){
		ObjectListing listaObjetos = null;
		if(!amazonS3.doesBucketExistV2(nomeBucket)) {
			throw new ExceptionNaoExisteBucket();
		}else {
			listaObjetos = amazonS3.listObjects(nomeBucket);
		}
		
		return listaObjetos.getObjectSummaries()
				.stream()
				.map(sumario -> sumario)
				.collect(Collectors.toList());
	}
	
	public void deletarArquivo(String nomeBucket, String chaveArquivo) {
		if(!amazonS3.doesBucketExistV2(nomeBucket)) {
			throw new ExceptionNaoExisteBucket();
		}else if(!amazonS3.doesObjectExist(nomeBucket, chaveArquivo)) {
			throw new ExceptionNaoExisteArquivo();
		}else
			amazonS3.deleteObject(nomeBucket,chaveArquivo);
	}
}
