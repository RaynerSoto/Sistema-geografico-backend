package cu.edu.cujae.core.security;

import java.nio.charset.StandardCharsets;

public class Base64Cifrado {
        public String cifrarBase64(String texto) {
            java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
            String cifrado = encoder.encodeToString(texto.getBytes(StandardCharsets.UTF_8));
            return cifrado;
        }

        public String descifrarBase64(String cifrado) {
            java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
            byte[] bytesDecodificados = decoder.decode(cifrado);
            String descifrado = new String(bytesDecodificados);
            return descifrado;
        }
}
