package api.modules.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public final class FormattingService {

	private static DecimalFormat decimalFormat = new DecimalFormat("#.#######");
	
	/**
	 * Round up to four decimal places.
	 * @param number
	 * @return
	 */
	public static Double formatDecimal(Double number) {
		decimalFormat.setRoundingMode(RoundingMode.CEILING);
		return Double.parseDouble(decimalFormat.format(number));		
	}
}
