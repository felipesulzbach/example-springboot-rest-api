package restapi.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

/**
 * @autor: Felipe Sulzbach
 */
public class FormatUtil {

    public static enum Mask {
        CPF(
                "###.###.###-##"
        ), CNPJ(
                "##.###.###/####-##"
        );

        private String mask;

        Mask(String mask) {
            this.mask = mask;
        }

        public String getMask() {
            return mask;
        }
    }

    public static String formatNumber(String value, Mask mask) throws ParseException {
        MaskFormatter maskk = new MaskFormatter(mask.getMask());
        maskk.setValueContainsLiteralCharacters(false);
        return maskk.valueToString(value);
    }
}
