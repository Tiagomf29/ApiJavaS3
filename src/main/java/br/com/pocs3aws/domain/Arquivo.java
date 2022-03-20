package br.com.pocs3aws.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Arquivo {

	private String nome;
	private String diretorioArquivoS3;
	private String diretorioArquivocomputador;
}
