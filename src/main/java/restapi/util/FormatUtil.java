package restapi.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

/**
 * @autor: Felipe Sulzbach
 */
public class FormatUtil {

    public static enum EnumMask {
        CPF(
                "###.###.###-##"
        ), CNPJ(
                "##.###.###/####-##"
        );

        private String mask;

        EnumMask(String mask) {
            this.mask = mask;
        }

        public String getMask() {
            return mask;
        }
    }

    public static String formatNumber(String value, EnumMask mask) throws ParseException {
        MaskFormatter maskk = new MaskFormatter(mask.getMask());
        maskk.setValueContainsLiteralCharacters(false);
        return maskk.valueToString(value);
    }
}
