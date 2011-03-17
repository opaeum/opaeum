package jbpm.jbpm.nodedefinition.rip.ssh.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import jbpm.jbpm.Application;
import jbpm.jbpm.nodedefinition.RipHelperImpl;
import jbpm.jbpm.nodedefinition.pool.EisPool;
import jbpm.jbpm.nodedefinition.pool.SshTunnelKeyedConnectionFactory;
import jbpm.jbpm.nodedefinition.rip.test.RipHelperTest;
import jbpm.jbpm.rip.MMLCommand;
import jbpm.jbpm.rip.Network;
import jbpm.jbpm.rip.NetworkSoftwareVersion;
import jbpm.jbpm.rip.NodeDefinition;
import junit.framework.Assert;
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
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSshServer {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	private static SshServer sshd;
	private static TelnetD daemon;
	
	@BeforeClass
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
		daemon = TelnetD.createTelnetD(p);
		daemon.start();
	}
	
	@AfterClass
	public static void stopSshServer() throws InterruptedException {
		daemon.stop();
		sshd.stop();
	}
	
	@Test
	public void testRipPass() {
		Application application = RipHelperTest.createTestData();
		rip(application);
		for (Network network : application.getNetwork()) {
			for (NetworkSoftwareVersion networkSoftwareVersion : network.getNetworkSoftwareVersion()) {
				for (NodeDefinition nodedDefinition : networkSoftwareVersion.getNodeDefinition()) {
					Assert.assertNotNull(nodedDefinition.getLastSuccesfulRip());
					Assert.assertNull(nodedDefinition.getLastUnsuccesfulRip());
				}
			}
		}
	}	
	
	private void rip(Application application) {
		List<NodeDefinition> nodeDefinitions = new ArrayList<NodeDefinition>();
		for (Network network : application.getNetwork()) {
			for (NetworkSoftwareVersion networkSoftwareVersion : network.getNetworkSoftwareVersion()) {
				nodeDefinitions.addAll(networkSoftwareVersion.getNodeDefinition());
			}
		}
		Set<MMLCommand> mmlCommands = new HashSet<MMLCommand>();
		for (Network network : application.getNetwork()) {
			mmlCommands.addAll(network.getMMLCommand());
		}
		SshTunnelKeyedConnectionFactory sshTunnelKeyedConnectionFactory = new SshTunnelKeyedConnectionFactory();
		EisPool eisPool = new EisPool(nodeDefinitions, sshTunnelKeyedConnectionFactory);
		RipHelperImpl ripHelperImpl = new RipHelperImpl();
		ripHelperImpl.setPool(eisPool.getPool());
		for (NodeDefinition nodeDefinition : nodeDefinitions) {
			ripHelperImpl.rip(nodeDefinition, mmlCommands);
		}
	}	
}
