package com.hardik.mercury.utility;

import java.util.List;
import java.util.Random;

public class ShitQuoteUtility {

	private static final List<String> shitQuotes = List.of("Half of being smart is knowing what you are dumb about",
			"Follow github.com/hardikSinghBehl and your life will change", "It takes a smart man to play dumb.");

	public static String get() {
		return shitQuotes.stream().skip(new Random().nextInt(shitQuotes.size())).findFirst().get();
	}

}
