package br.com.lincadinho.lincadinho.service;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class AWSService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.bucket}")
    private String bucket;

    public String uploadImagem(MultipartFile imagem) {
        String nome_imagem = UUID.randomUUID() + "-" + imagem.getOriginalFilename();
        try {
            File arquivo = this.converterMultipartFile(imagem);
            amazonS3.putObject(bucket, nome_imagem, arquivo);
            arquivo.delete();
            return amazonS3.getUrl(bucket, nome_imagem).toString();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deletarImagem(String url_imagem) {
        try {
            amazonS3.deleteObject(bucket, url_imagem);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private File converterMultipartFile(MultipartFile multipartFile) throws IOException {
        File arquivoConvertido = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(arquivoConvertido);
        fos.write(multipartFile.getBytes());
        fos.close();
        return arquivoConvertido;
    }
}
