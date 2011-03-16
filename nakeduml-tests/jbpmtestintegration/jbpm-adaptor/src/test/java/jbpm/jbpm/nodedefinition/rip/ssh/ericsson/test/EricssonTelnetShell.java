package jbpm.jbpm.nodedefinition.rip.ssh.ericsson.test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jbpm.jbpm.nodedefinition.rip.ssh.test.AbstractTelnetShell;
import net.wimpi.telnetd.shell.Shell;

import com.google.common.io.Resources;

public class EricssonTelnetShell extends AbstractTelnetShell {

	private static final String COMMAND_RESPONSE = "command-response/";
	private ERICSSON_STATE state = ERICSSON_STATE.PROMPT_USERNAME;
	protected static Map<String, List<String>> responseMap = new HashMap<String, List<String>>();

	private enum ERICSSON_STATE {
		PROMPT_USERNAME, PROMPT_PASSWORD, AUTHENTICATE, HANDLE_DOMAIN, AWAIT_COMMANDS;
	}

	public static Shell createShell() {
		return new EricssonTelnetShell();
	}

	public EricssonTelnetShell() {
		super();
		try {
			List<String> commands = Resources.readLines(Thread.currentThread().getContextClassLoader().getResource(COMMAND_RESPONSE + "commands"),
					Charset.defaultCharset());
			responseMap.clear();
			populateResponse(commands);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void populateResponse(List<String> commands) throws IOException {
		for (String command : commands) {
			List<String> reponse = Resources.readLines(Thread.currentThread().getContextClassLoader().getResource(COMMAND_RESPONSE + command),
					Charset.defaultCharset());
			responseMap.put(command, reponse);
		}
	}

	protected void read() throws IOException {
		
		final StringBuilder received = new StringBuilder();
		int character = -1;
		while ((character = this.m_IO.read()) != -1) {
			received.append((char) character);
			m_IO.write((char) character);
			m_IO.flush();
			if (received.length() > MAX_CHARS) {
				throw new RuntimeException("Maximum length of " + MAX_CHARS + " characters exceeded");
			}
			String stopped = this.findStops(received, "\n");
			if (stopped != null) {
				doResponseForStop(stopped);
			}
		}		
		
	}
	
	@Override
	protected void doStartConnection() throws IOException {
		hasStartedConnection = true;
		m_IO.write("login name: ");
		state = ERICSSON_STATE.PROMPT_PASSWORD;
	}

	@Override
	protected void doResponseForStop(String stopped) throws IOException {
		m_IO.write("\n");
		switch (state) {
		case PROMPT_PASSWORD:
			m_IO.write("password: ");
			state = ERICSSON_STATE.AUTHENTICATE;
			break;
		case AUTHENTICATE:
			m_IO.write("Domain:");
			state = ERICSSON_STATE.HANDLE_DOMAIN;
			break;
		case HANDLE_DOMAIN:
			m_IO.write("<");
			state = ERICSSON_STATE.AWAIT_COMMANDS;
			break;
		case AWAIT_COMMANDS:
			for (String responseLine : responseMap.get(stopped)) {
				m_IO.write(responseLine);
				m_IO.write("\n");
			}
		default:
			throw new IllegalStateException();
		}
	}

}
