package jbpm.jbpm.nodedefinition.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jbpm.jbpm.rip.NodeDefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class SessionManager {

	Logger logger = LoggerFactory.getLogger(SessionManager.class);
	private Map<String, Session> sessionMap = new ConcurrentHashMap<String, Session>();
	private Map<String, List<NodeDefinitionCount>> sessionPortCount = new ConcurrentHashMap<String, List<NodeDefinitionCount>>();

	public Map<String, List<NodeDefinitionCount>> getSessionPortCount() {
		return sessionPortCount;
	}

	public void setSessionPortCount(Map<String, List<NodeDefinitionCount>> sessionPortCount) {
		this.sessionPortCount = sessionPortCount;
	}

	public boolean containsSession(String sshHost) {
		return sessionMap.containsKey(sshHost);
	}

	@SuppressWarnings("static-access")
	private Session createSession(String sshHost, Integer timeout, String sshUsername, final String sshPassword) {
		logger.info("ssh session attempt for host " + sshHost);
		JSch sch = new JSch();
		sch.setLogger(new com.jcraft.jsch.Logger() {
			@Override
			public void log(int level, String message) {
				logger.info("Log(jsch," + level + "): " + message);
			}

			@Override
			public boolean isEnabled(int level) {
				return true;
			}
		});
		Session session = null;
		try {
			session = sch.getSession(sshUsername, sshHost, 2222);
			session.setTimeout(timeout);
			session.setServerAliveCountMax(3);
			session.setUserInfo(new UserInfo() {
				public String getPassphrase() {
					return null;
				}

				public String getPassword() {
					return sshPassword;
				}

				public boolean promptPassword(String message) {
					return true;
				}

				public boolean promptPassphrase(String message) {
					return false;
				}

				public boolean promptYesNo(String message) {
					return true;
				}

				public void showMessage(String message) {
				}
			});
			session.connect();
			logger.info("ssh session connected for host " + sshHost);
		} catch (JSchException e) {
			throw new RuntimeException(e);
		}
		return session;
	}

	public void setPortForwardingL(NodeDefinition nodeDefinition) {
		logger.info("AAAAAAAAAAAAAAA setPortForwardingL " + nodeDefinition.getHost() + " " + nodeDefinition.getSshTunnelSpec().getLport());
		Session session = sessionMap.get(nodeDefinition.getSshTunnelSpec().getHost());
		try {
			session.setPortForwardingL(nodeDefinition.getSshTunnelSpec().getLport(), nodeDefinition.getHost(), nodeDefinition.getPort());
			List<NodeDefinitionCount> lportCount = sessionPortCount.get(nodeDefinition.getSshTunnelSpec().getHost());
			lportCount.add(new NodeDefinitionCount(nodeDefinition.getSshTunnelSpec().getLport(), new Integer(1), nodeDefinition.getName()));
		} catch (JSchException e) {
			throw new RuntimeException(e);
		}
	}

	public void createSessionAndPortForward(NodeDefinition nodeDefinition) {
		logger.info("XXXXXXXXXXXXXXXXXXXXX createSessionAndPortForward " + nodeDefinition.getHost() + " " + nodeDefinition.getSshTunnelSpec().getLport());
		try {
			Session session = createSession(nodeDefinition.getSshTunnelSpec().getHost(), nodeDefinition.getSshTunnelSpec().getTimeout(), nodeDefinition.getSshTunnelSpec().getUsername(),
					nodeDefinition.getSshTunnelSpec().getPassword());
			session.setPortForwardingL(nodeDefinition.getSshTunnelSpec().getLport(), nodeDefinition.getHost(), nodeDefinition.getPort());
			sessionMap.put(nodeDefinition.getSshTunnelSpec().getHost(), session);
			List<NodeDefinitionCount> lportCount = new ArrayList<NodeDefinitionCount>();
			lportCount.add(new NodeDefinitionCount(nodeDefinition.getSshTunnelSpec().getLport(), new Integer(1), nodeDefinition.getName()));
			sessionPortCount.put(nodeDefinition.getSshTunnelSpec().getHost(), lportCount);
			List<Integer> lports = new ArrayList<Integer>();
			lports.add(nodeDefinition.getSshTunnelSpec().getLport());
		} catch (JSchException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean containsPortForward(NodeDefinition nodeDefinition) {
		List<NodeDefinitionCount> lportCount = sessionPortCount.get(nodeDefinition.getSshTunnelSpec().getHost());
		return lportCount.contains(new NodeDefinitionCount(nodeDefinition.getSshTunnelSpec().getLport(), 0, nodeDefinition.getName()));
	}

	public void increment(NodeDefinition nodeDefinition) {
		List<NodeDefinitionCount> lportCount = sessionPortCount.get(nodeDefinition.getSshTunnelSpec().getHost());
		int indexOf = lportCount.indexOf(new NodeDefinitionCount(nodeDefinition.getSshTunnelSpec().getLport(), 0, nodeDefinition.getName()));
		NodeDefinitionCount nodeConnectionSpecCount = lportCount.get(indexOf);
		nodeConnectionSpecCount.increment();
	}

	public void destroy(NodeDefinition nodeDefinition) throws JSchException {
		logger.info("YYYYYYYYYYYYYYYYYYY destroy " + nodeDefinition.getHost() + " " + nodeDefinition.getSshTunnelSpec().getLport());

		List<NodeDefinitionCount> lportCount = sessionPortCount.get(nodeDefinition.getSshTunnelSpec().getHost());
		int indexOf = lportCount.indexOf(new NodeDefinitionCount(nodeDefinition.getSshTunnelSpec().getLport(), 0, nodeDefinition.getName()));
		NodeDefinitionCount nodeConnectionSpecCount = lportCount.get(indexOf);
		nodeConnectionSpecCount.decrement();
		if (nodeConnectionSpecCount.getCount() == 0) {
			logger.info("BBBBBBBBBBBBBBBBBBBBB destroy " + nodeDefinition.getHost() + " " + nodeDefinition.getSshTunnelSpec().getLport());
			// close port
			lportCount.remove(new NodeDefinitionCount(nodeDefinition.getSshTunnelSpec().getLport(), 0, nodeDefinition.getName()));
			Session session = sessionMap.get(nodeDefinition.getSshTunnelSpec().getHost());
			try {
				session.delPortForwardingL(nodeDefinition.getSshTunnelSpec().getLport());
			} finally {
				if (lportCount.isEmpty()) {
					logger.info("ZZZZZZZZZZZZZZZZZZZZZZZ destroy " + nodeDefinition.getHost() + " " + nodeDefinition.getSshTunnelSpec().getLport());
					sessionMap.remove(nodeDefinition.getSshTunnelSpec().getHost());
					sessionPortCount.remove(nodeDefinition.getSshTunnelSpec().getHost());
					session.disconnect();
				}
			}
		}
	}

	public Map<String, Session> getSessionMap() {
		return sessionMap;
	}
	
	public class NodeDefinitionCount {
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + lport;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			NodeDefinitionCount other = (NodeDefinitionCount) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (lport != other.lport)
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		private int lport;
		private int count;
		private String name;
		public NodeDefinitionCount(int lport, int count, String name) {
			super();
			this.lport = lport;
			this.count = count;
			this.name = name;
		}
		public int getLport() {
			return lport;
		}
		public void setLport(int lport) {
			this.lport = lport;
		}
		public int getCount() {
			return count;
		}
		public void decrement() {
			count--;
		}
		public void increment() {
			count++;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		private SessionManager getOuterType() {
			return SessionManager.this;
		}
	}

}
