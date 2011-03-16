package jbpm.jbpm.nodedefinition.rip.ssh.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.wimpi.telnetd.io.BasicTerminalIO;
import net.wimpi.telnetd.net.Connection;
import net.wimpi.telnetd.net.ConnectionEvent;
import net.wimpi.telnetd.shell.Shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTelnetShell implements Shell {

	Logger logger = LoggerFactory.getLogger(getClass());
	protected Connection m_Connection;
	protected BasicTerminalIO m_IO;
	protected static final int MAX_CHARS = 6097152;
	protected boolean hasStartedConnection = false;

	public AbstractTelnetShell() {
		super();
	}

	@Override
	public void connectionTimedOut(ConnectionEvent ce) {
		try {
			m_IO.write("CONNECTION_TIMEDOUT");
			m_IO.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		m_Connection.close();
	}

	@Override
	public void connectionIdle(ConnectionEvent ce) {
		try {
			m_IO.write("CONNECTION_IDLE");
			m_IO.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void connectionLogoutRequest(ConnectionEvent ce) {
		try {
			m_IO.write("CONNECTION_LOGOUTREQUEST");
			m_IO.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void connectionSentBreak(ConnectionEvent ce) {
		try {
			m_IO.write("CONNECTION_BREAK");
			m_IO.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void doStartConnection() throws IOException {
		hasStartedConnection = true;
	}

	public void run(Connection con) {
		try {
			m_Connection = con;
			m_IO = m_Connection.getTerminalIO();
			// register the connection listener
			m_Connection.addConnectionListener(this);

			if (!hasStartedConnection) {
				doStartConnection();
			}
			read();
			m_IO.flush(); // flush the output to ensure it is sent
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected abstract void read() throws IOException;

	protected abstract void doResponseForStop(String stopped) throws IOException;

	protected String findStops(StringBuilder stringBuilder, String ... stops) {
		for (String stop : stops) {
			if (stringBuilder.toString().endsWith(stop)) {
				return stop;
			}
		}
		return null;
	}

}
