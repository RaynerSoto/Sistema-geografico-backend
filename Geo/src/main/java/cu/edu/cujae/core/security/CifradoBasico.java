package cu.edu.cujae.core.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CifradoBasico {
        public String cifrarBase64(String texto) {
            Base64.Encoder encoder = Base64.getEncoder();
            String cifrado = encoder.encodeToString(texto.getBytes(StandardCharsets.UTF_8));
            return cifrado;
        }

        public String descifrarBase64(String cifrado) {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] bytesDecodificados = decoder.decode(cifrado);
            String descifrado = new String(bytesDecodificados);
            return descifrado;
        }
}
