package sn.gainde2000.userservice.utils;

import java.util.Locale;

public class ProjectConstants {

	// FIXME : Customize project constants for your application.

	public static final String DEFAULT_ENCODING = "UTF-8";

	public static final String PROJECT_BASE_PACKAGE = "sn.gainde2000.userservice";

	public static final Locale FRENCH_LOCALE = new Locale.Builder().setLanguage("fr").setRegion("FR").build();

	private ProjectConstants() {

		throw new UnsupportedOperationException();
	}
}
