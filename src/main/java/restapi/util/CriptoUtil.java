package restapi.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import restapi.models.resources.vo.ServiceException;

/**
 * @autor: Felipe Sulzbach
 */
public class CriptoUtil {

    private static Logger LOG = LoggerFactory.getLogger(CriptoUtil.class.getSimpleName());

    public static enum EnumHash {
        MD5(
                "MD5"
        ), SHA_256(
                "SHA-256"
        ), SHA_1(
                "SHA-1"
        );

        private String value;

        EnumHash(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static String encript(final String password, EnumHash hash) throws ServiceException {
        StringBuilder hexPassword = new StringBuilder();
        try {
            MessageDigest algoritmo = MessageDigest.getInstance(hash.getValue());
            byte digestMessage[] = algoritmo.digest(password.getBytes("UTF-8"));
            for (byte aByte : digestMessage) {
                hexPassword.append(String.format("%02X", 0xFF & aByte));
            }
        } catch (NoSuchAlgorithmException ex) {
            LOG.error("Cripto - Error: ", ex);
            throw ServiceException.get("INTERNAL_ERROR");
        } catch (UnsupportedEncodingException ex) {
            LOG.error("Cripto - Error: ", ex);
            throw ServiceException.get("INTERNAL_ERROR");
        }
        return hexPassword.toString();
    }
}
