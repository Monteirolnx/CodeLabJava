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
            byte[] wsqImageBytes = decodeBase64ToBytes(base64WsqImage);

            BufferedImage image = decodeWsqToBufferedImage(wsqImageBytes);

            byte[] ansiTemplateBytes = generateAnsiTemplate(image);

            if (!isValidAnsiTemplate(ansiTemplateBytes)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return ResponseEntity
                    .ok()
                    .header("Content-Type", "application/octet-stream")
                    .body(ansiTemplateBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private byte[] decodeBase64ToBytes(String base64WsqImage) throws IllegalArgumentException {
        return Base64.getDecoder().decode(base64WsqImage);
    }

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

    private byte[] generateAnsiTemplate(BufferedImage image) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "bmp", baos);
        byte[] imageBytes = baos.toByteArray();

        FingerprintImage fingerprintImage = new FingerprintImage(imageBytes);
        FingerprintTemplate template = new FingerprintTemplate(fingerprintImage);

        return template.toByteArray();
    }

    private boolean isValidAnsiTemplate(byte[] ansiTemplateBytes) {
        try {
            new FingerprintTemplate(ansiTemplateBytes);

            return true;
        } catch (Exception e) {

            return false;
        }
    }
}
