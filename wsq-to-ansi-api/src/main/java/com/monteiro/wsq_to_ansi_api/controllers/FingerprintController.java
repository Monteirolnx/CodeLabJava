package com.monteiro.wsq_to_ansi_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import javax.imageio.ImageIO;

import com.machinezoo.sourceafis.FingerprintTemplate;
import com.machinezoo.sourceafis.FingerprintImage;

import org.jnbis.api.Jnbis;
import org.jnbis.api.model.Bitmap;

@RestController
@RequestMapping("/api/fingerprint")
public class FingerprintController {

    @PostMapping("/convert")
    public ResponseEntity<byte[]> convertWsqToAnsiTemplate(@RequestBody String base64WsqImage) {
        try {
            // Decodifica a string Base64 para um array de bytes
            byte[] wsqImageBytes = decodeBase64ToBytes(base64WsqImage);

            // Decodifica WSQ e obtém um BufferedImage
            BufferedImage image = decodeWsqToBufferedImage(wsqImageBytes);

            // Gera o template ANSI 378
            byte[] ansiTemplateBytes = generateAnsiTemplate(image);

            // Valida o template gerado
            if (!isValidAnsiTemplate(ansiTemplateBytes)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // Retorna o template ANSI 378 como resposta
            return ResponseEntity
                    .ok()
                    .header("Content-Type", "application/octet-stream")
                    .body(ansiTemplateBytes);

        } catch (Exception e) {
            e.printStackTrace(); // Para fins de depuração
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para decodificar a string Base64 para array de bytes
    private byte[] decodeBase64ToBytes(String base64WsqImage) throws IllegalArgumentException {
        return Base64.getDecoder().decode(base64WsqImage);
    }

    // Método para decodificar o WSQ para BufferedImage usando jnbis
    private BufferedImage decodeWsqToBufferedImage(byte[] wsqImageBytes) {
        Bitmap bitmap = Jnbis.wsq()
                .decode(wsqImageBytes)
                .asBitmap();

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        byte[] pixels = bitmap.getPixels();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster raster = image.getRaster();
        raster.setDataElements(0, 0, width, height, pixels);

        return image;
    }

    // Método para gerar o template ANSI 378 usando SourceAFIS
    private byte[] generateAnsiTemplate(BufferedImage image) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "bmp", baos);
        byte[] imageBytes = baos.toByteArray();

        FingerprintImage fingerprintImage = new FingerprintImage(imageBytes);
        FingerprintTemplate template = new FingerprintTemplate(fingerprintImage);

        return template.toByteArray();
    }

    // Método para validar o template ANSI 378
    private boolean isValidAnsiTemplate(byte[] ansiTemplateBytes) {
        try {
            new FingerprintTemplate(ansiTemplateBytes);

            return true;
        } catch (Exception e) {

            return false;
        }
    }
}
