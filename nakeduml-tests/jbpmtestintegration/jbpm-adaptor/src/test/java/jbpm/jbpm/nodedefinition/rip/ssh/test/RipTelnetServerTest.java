package jbpm.jbpm.nodedefinition.rip.ssh.test;

import java.io.IOException;
import java.util.Properties;

import net.wimpi.telnetd.BootException;
import net.wimpi.telnetd.TelnetD;

import org.junit.Test;

public class RipTelnetServerTest {

	@Test
	public void test() throws InterruptedException, IOException, BootException {
		Properties p = new Properties();
		p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("telnetshell.properties"));
		TelnetD daemon = TelnetD.createTelnetD(p);
		//2.start serving
		daemon.start();

		
		System.out.println("");
		while (true) {
			Thread.sleep(2000);
		}
	}
}
