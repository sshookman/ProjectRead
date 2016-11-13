package com.codepoet.enchiridion.view;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewManager {

	private static final Logger LOGGER = Logger.getLogger(ViewManager.class.getName());
	private final WelcomeView welcomeView = new WelcomeView();

	public View resolve(String view) {

		try {
			switch (view) {
				case "welcome":
					return welcomeView;
				default:
					return null;
			}
		} catch (Exception exception) {
			LOGGER.log(Level.WARNING, "Failed to Resolve View: {0}", exception.getMessage());
			return null;
		}
	}
}
