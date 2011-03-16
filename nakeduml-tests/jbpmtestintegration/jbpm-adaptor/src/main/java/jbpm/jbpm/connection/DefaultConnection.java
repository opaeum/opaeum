package jbpm.jbpm.connection;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultConnection {

	Logger logger = LoggerFactory.getLogger(getClass());
	private static final String COMMAND_PROMPT = "Enter command:";
	private static final int MAX_CHARS = 6097152;
	private final String[] invalidStrings = {};
	private InputStream inputStream;
	
	public String receive(boolean retainMatchedStrings, String... stops) {
		final StringBuilder received = new StringBuilder();

		int character = -1;

		try {
			while ((character = this.inputStream.read()) != -1) {
				received.append((char) character);

				if (received.length() > MAX_CHARS) {
					throw new RuntimeException("Maximum length of " + MAX_CHARS + " characters exceeded");
				}

				String stopped = this.findStops(received, stops);

				if (stopped != null) {
					return this.finalText(received, stopped, retainMatchedStrings);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		throw new RuntimeException("Unexpected end of stream: received =" + received.toString());
	}
	
	private String findStops(StringBuilder stringBuilder, String... stops) {
		for (String stop : stops) {
			if (stringBuilderEndsWith(stringBuilder, stop)) {
				logger.info("telnet client found stop = {}", stop);
				return stop;
			}
		}
		return null;
	}

	private String finalText(StringBuilder stringBuilder, String stop, boolean retainMatchedStrings) {
		String text = stringBuilder.toString();

		if (!retainMatchedStrings) {
			text = text.substring(0, text.length() - stop.length()).trim();

			if (text.startsWith(COMMAND_PROMPT)) {
				text = text.substring(COMMAND_PROMPT.length());
			}
		}

		for (String invalid : this.invalidStrings) {
			if (text.contains(invalid)) {
				throw new RuntimeException("Invalid response received [" + invalid + "]");
			}
		}

		return text.trim();
	}
	
	private boolean stringBuilderEndsWith(StringBuilder stringBuilder, String what) {
		if (stringBuilder.length() < what.length()) {
			return false;
		}
		return stringBuilder.substring(stringBuilder.length() - what.length()).equals(what);
	}
	
}
