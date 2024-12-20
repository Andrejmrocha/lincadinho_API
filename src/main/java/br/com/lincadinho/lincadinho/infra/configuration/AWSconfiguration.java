package br.com.lincadinho.lincadinho.infra.configuration;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSconfiguration {

    @Value("${aws.region}")
    private String regiaoAWS;

    @Bean
    public AmazonS3 criarInstanciaS3(){
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(regiaoAWS)
                .build();
    }
}
