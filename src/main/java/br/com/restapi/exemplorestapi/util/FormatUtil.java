package br.com.restapi.exemplorestapi.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class FormatUtil {

	public static enum Mascara {
		CPF("###.###.###-##"), CNPJ("##.###.###/####-##");

		private String mascara;

		Mascara(String mascara) {
			this.mascara = mascara;
		}

		public String getMascara() {
			return mascara;
		}
	}

	public static String formatNumber(String value, Mascara mascara) throws ParseException {
		MaskFormatter mask = new MaskFormatter(mascara.getMascara());
		mask.setValueContainsLiteralCharacters(false);
		return mask.valueToString(value);
	}
}
