package jbpm.jbpm.nodedefinition;

import java.io.IOException;
import java.io.PrintStream;

import jbpm.jbpm.rip.NodeDefinition;

import org.apache.commons.net.telnet.TelnetClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EricssonManagedConnection implements EISConnection {

	Logger logger = LoggerFactory.getLogger(EricssonManagedConnection.class);
	private static final int MAX_CHARS = 6097152;
	private static final String BLANK = "";
	private static final String COMMAND_PROMPT = "Enter command:";
	private static final String PROMPT = "~$";
	private static final String DEF_PROMPT = "<";
	private static final String ALT_PROMPT = ">";
	private static final String TERM_PROMPT = "):";
	private final String[] invalidStrings = {};
	private TelnetClient telnetClient;
	private IODeviceType type = IODeviceType.IOG;
	private NodeDefinition nodeDefinition;

	private enum IODeviceType {
		APG, IOG
	}

	public EricssonManagedConnection(NodeDefinition nodeDefinitionX) {
		this.nodeDefinition = nodeDefinitionX;
		telnetClient = new TelnetClient();
		telnetClient.setConnectTimeout(this.nodeDefinition.getTimeout());
		try {
			if (nodeDefinition.getRequiresSshTunnel()) {
				logger.info("creating telnet connection via ssh tunnel on localhost and port #0 " + this.nodeDefinition.getSshTunnelSpec().getLport());
				telnetClient.connect("localhost", this.nodeDefinition.getSshTunnelSpec().getLport());
			} else {
				logger.info("creating telnet connection on host " + this.nodeDefinition.getHost() + " and port " + this.nodeDefinition.getSshTunnelSpec().getLport());
				telnetClient.connect(this.nodeDefinition.getHost(), this.nodeDefinition.getPort());
			}
			telnetClient.setSoTimeout(this.nodeDefinition.getSoTimeout());
			login();
		} catch (Exception e) {
			try {
				destroy();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}
	}

	private void login() {
		logger.info("telnet client logging in");
		receive(true, "login name: ", "USERCODE:");
		write(this.nodeDefinition.getUsername());
		receive(true, "password: ", "PASSWORD:");
		write(this.nodeDefinition.getPassword());

		String result = this.receive(true, "Domain:", "DOMAIN:", PROMPT, ALT_PROMPT, TERM_PROMPT, DEF_PROMPT, "password has expired.", "bad password.",
				"AUTHORIZATION FAILURE", "Login incorrect", "The referenced account is currently locked out and may not be logged on to.", "USERCODE:");

		if (result.endsWith("Domain:") || result.endsWith("DOMAIN:")) {
			handleDomain();
		} else {
			handlePrompts(result);
		}

		handleDomain();
		logger.info("telnet client logged in");
	}

	public void destroy() {
		try {
			this.write("EXIT;");
			if (this.type == IODeviceType.APG) {
				this.receive(false, ALT_PROMPT);
				this.write("exit");
			} else {
				this.receive(false, "EXIT;", "LOGGED OFF");
			}
			this.telnetClient.disconnect();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String execute(String command) {
		write(command);
		return receive(false, DEF_PROMPT);
	}

	private void write(String command) {
		PrintStream out = new PrintStream(this.telnetClient.getOutputStream());
		out.println(command);
		out.flush();
	}

	public String receive(boolean retainMatchedStrings, String... stops) {
		final StringBuilder received = new StringBuilder();

		int character = -1;

		try {
			while ((character = this.telnetClient.getInputStream().read()) != -1) {
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
				logger.info("telnet client found stop = " + stop);
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

	private void handleDomain() {
		this.write(BLANK);
		this.handlePrompts(this.receive(true, ALT_PROMPT, TERM_PROMPT, DEF_PROMPT, "password has expired.", "bad password.", "AUTHORIZATION FAILURE"));
	}

	private void handlePrompts(String options) {
		if (options.endsWith(ALT_PROMPT)) {
			handleAlternativePrompt();
		} else if (options.endsWith(TERM_PROMPT)) {
			handleTerminalPrompt();
		} else if (options.endsWith(DEF_PROMPT)) {
			handleDefaultPrompt();
		} else {
			throw new RuntimeException("The user name and password combination is invalid or has expired");
		}
	}

	private void handleAlternativePrompt() {
		this.type = IODeviceType.APG;
		this.write("mml");
		this.receive(false, DEF_PROMPT);
	}

	private void handleTerminalPrompt() {
		this.write("1");

		String options = this.receive(true, ALT_PROMPT, DEF_PROMPT);

		if (options.endsWith(ALT_PROMPT)) {
			this.handleAlternativePrompt();
		} else {
			this.handleDefaultPrompt();
		}
	}

	private void handleDefaultPrompt() {
		this.type = IODeviceType.IOG;
	}

	public boolean isValid() {
		try {
			execute("");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
