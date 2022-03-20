package br.com.pocs3aws.service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;

import br.com.pocs3aws.exceptions.ExceptionBucketComArquivos;
import br.com.pocs3aws.exceptions.ExceptionExisteBucket;
import br.com.pocs3aws.exceptions.ExceptionNaoExisteBucket;

@Service
public class BucketService {

	@Autowired
	private AmazonS3 amazonS3;
	
	public void criarBucket(final String nomeBucket) {
		if(amazonS3.doesBucketExistV2(nomeBucket)) {
			throw new ExceptionExisteBucket();
		}else
			amazonS3.createBucket(nomeBucket);
	}
	
	public List<String> listarBucket(){
		return amazonS3.listBuckets()
				.stream()
				.map(bucket -> bucket.getName())
				.collect(Collectors.toList());
	}
	
	public void deletarBucket(final String nomeBucket) {
		if(!amazonS3.doesBucketExistV2(nomeBucket)) {
			throw new ExceptionNaoExisteBucket();
		}else {
			
			var listaObjetos = amazonS3.listObjects(nomeBucket);
			
			Iterator<?> iterator = listaObjetos.getObjectSummaries().iterator();
			
			if(!iterator.hasNext()) {
				amazonS3.deleteBucket(nomeBucket);
			}else {
				throw new ExceptionBucketComArquivos();
			}
			
		}
			
	}
	
}
