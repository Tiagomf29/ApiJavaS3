package br.com.pocs3aws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import br.com.pocs3aws.access.Credenciais;

@Configuration
public class AmazonS3Config {

	private AmazonS3 clienteS3;
	
	@Bean
	public AmazonS3 s3Injection() {
		
		var credenciais = new BasicAWSCredentials(Credenciais.ACCESS_KEY, Credenciais.SECRET_KEY);
		
		clienteS3 = AmazonS3ClientBuilder
					.standard()
					.withCredentials(new AWSStaticCredentialsProvider(credenciais))
					.withRegion(Regions.US_EAST_1)
					.enableForceGlobalBucketAccess()
					.build();	
		
		return clienteS3;
	}
	
	
	
}
