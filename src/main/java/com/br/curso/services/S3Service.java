package com.br.curso.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3client;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	private Logger LOG = LoggerFactory.getLogger(S3Service.class);
	
/*	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			InputStream is = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return uploadFile(is, fileName, contentType);
		} catch (IOException e) {
			throw new FileException("Erro de IO: " + e.getMessage());
		}
	}*/
	
	public void uploadFile(String localFilePath) {
		try {
			
			File file = new File(localFilePath);
			LOG.info("Iniciando upload");
			s3client.putObject(new PutObjectRequest(bucketName, "teste", file));
			LOG.info("Finalizando");
			
		} catch (AmazonServiceException e) {
			LOG.info("AmazonServiceException: " + e.getErrorMessage());
			LOG.info("Status Code: " + e.getStatusCode());
		}catch(AmazonClientException e) {
			LOG.info("AmazonClientException: " + e.getMessage());
		}
		
	}
}