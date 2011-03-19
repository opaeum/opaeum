package jbpm.jbpm.nodedefinition.rip.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.PublicKey;
import java.util.EnumSet;
import java.util.Properties;

import net.wimpi.telnetd.BootException;
import net.wimpi.telnetd.TelnetD;

import org.apache.sshd.SshServer;
import org.apache.sshd.server.ForwardingFilter;
import org.apache.sshd.server.PasswordAuthenticator;
import org.apache.sshd.server.PublickeyAuthenticator;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.session.ServerSession;
import org.apache.sshd.server.shell.ProcessShellFactory;
import org.apache.sshd.server.shell.ProcessShellFactory.TtyOptions;

public class SshTelnetUtil {

	private static SshServer sshd;
	private static TelnetD daemon;

	public static void startSshServer() throws IOException, BootException {
		sshd = SshServer.setUpDefaultServer();
		sshd.setPasswordAuthenticator(new PasswordAuthenticator() {
			public boolean authenticate(String username, String password, ServerSession session) {
				return true;
			}
		});
		sshd.setPublickeyAuthenticator(new PublickeyAuthenticator() {
			public boolean authenticate(String username, PublicKey key, ServerSession session) {
				return true;
			}
		});
		sshd.setPort(2222);
		sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider("hostkey.ser"));
		sshd.setShellFactory(new ProcessShellFactory(new String[] { "/bin/sh", "-i", "-l" }, EnumSet.allOf(TtyOptions.class)));

		sshd.setForwardingFilter(new ForwardingFilter() {
			@Override
			public boolean canListen(InetSocketAddress address, ServerSession session) {
				return true;
			}

			@Override
			public boolean canForwardX11(ServerSession session) {
				return true;
			}

			@Override
			public boolean canForwardAgent(ServerSession session) {
				return true;
			}

			@Override
			public boolean canConnect(InetSocketAddress address, ServerSession session) {
				return true;
			}
		});

		sshd.start();

		Properties p = new Properties();
		p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("telnetshell.properties"));
		daemon = TelnetD.getReference();
		if (daemon == null) {
			daemon = TelnetD.createTelnetD(p);
		}
		daemon.start();
	}

	public static void stopSshServer() throws InterruptedException {
		daemon.stop();
		sshd.stop();
	}
}
