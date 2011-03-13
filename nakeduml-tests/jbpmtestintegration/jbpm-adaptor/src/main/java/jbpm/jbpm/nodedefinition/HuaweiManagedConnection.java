package jbpm.jbpm.nodedefinition;

import java.io.IOException;
import java.io.PrintStream;

import jbpm.jbpm.rip.NodeDefinition;

import org.apache.commons.net.telnet.TelnetClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HuaweiManagedConnection implements EISConnection {
	Logger logger = LoggerFactory.getLogger(HuaweiManagedConnection.class);

    private static final String LGI = "LGI:OP=%s,PWD=%s;";
    private static final String LGO = "LGO:OP=%s;";
    private static final String REG_NE = "REG NE:NAME=%s;";
    private static final String UNREG_NE = "UNREG NE:NAME=%s;";
    private static final String SHK_HAND = "SHK HAND:;";
    private static final String STOP_PROMPT = "---    END";
    private static final String[] SKIP_PROMPT = {"To be continued...", "%%LST CMCTRL:;%%", "%%Report command actively%%"};
	
	private static final int MAX_CHARS = 6097152;
	private static final String COMMAND_PROMPT = "Enter command:";
	private final String[] invalidStrings = {};
	private TelnetClient telnetClient;
	private NodeDefinition nodeDefinition;
	private int skipStopStack;

	public HuaweiManagedConnection(NodeDefinition nodeDefinition) {
		this.nodeDefinition = nodeDefinition;
		telnetClient = new TelnetClient();
		telnetClient.setConnectTimeout(this.nodeDefinition.getTimeout());
		try {
			if (this.nodeDefinition.getRequiresSshTunnel()) {
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
		write(String.format(LGI, this.nodeDefinition.getUsername(), this.nodeDefinition.getPassword()));
        receive(true, SKIP_PROMPT, STOP_PROMPT);
        this.registerNetworkElement(String.format(REG_NE, this.nodeDefinition.getName()));
        this.shakeHand();        
		logger.info("telnet client logged in");
	}
	
    protected void shakeHand() {
        this.execute(SHK_HAND);
    }
    
    protected void registerNetworkElement(String regNeCommand) {
        this.execute(regNeCommand);
    }

    protected void unregisterNetworkElement(String unregNeCommand) {
        this.execute(unregNeCommand);
    }	

	public void destroy() {
		try {
			this.unregisterNetworkElement(String.format(UNREG_NE, this.nodeDefinition.getName()));
			this.execute(String.format(LGO, this.nodeDefinition.getUsername()));
			this.telnetClient.disconnect();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

    public String execute(String string) {
        this.write(string);
        return this.receive(true, SKIP_PROMPT, STOP_PROMPT);
    }	

	private void write(String command) {
		PrintStream out = new PrintStream(this.telnetClient.getOutputStream());
		out.println(command);
		out.flush();
	}

	public String receive(boolean retainMatchedStrings, String[] skips, String... stops) {
		final StringBuilder received = new StringBuilder();

		int character = -1;
        this.skipStopStack = 0;

		try {
			while ((character = this.telnetClient.getInputStream().read()) != -1) {
				received.append((char) character);

				if (received.length() > MAX_CHARS) {
					throw new RuntimeException("Maximum length of " + MAX_CHARS + " characters exceeded");
				}

                if (this.findSkips(received, skips) == null) {
                    String stopped = this.findStops(received, stops);
                    if (this.skipStopStack < 0 && stopped != null) {
                        return this.finalText(received, stopped, retainMatchedStrings);
                    }
                }
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		throw new RuntimeException("Unexpected end of stream: received =" + received.toString());
	}
	
    private String findSkips(StringBuilder stringBuilder, String ... skips) {
        for (String skip : skips) {
            if (stringBuilderEndsWith(stringBuilder, skip)) {
                ++this.skipStopStack;
                return skip;
            }
        }
        return null;
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

	public boolean isValid() {
		try {
			execute("");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
