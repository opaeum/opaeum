package org.jbpm.graph.exe;


public class StateExitAction /*implements ActionHandler*/ {
//	static class NodeComparator implements Comparator<Node> {
//		public int compare(Node n1, Node n2) {
//			if (n1 instanceof SuperState) {
//				SuperState ss1 = (SuperState) n1;
//				if (ss1.containsNode(n2)) {
//					return 1;
//				}
//			}
//			if (n2 instanceof SuperState) {
//				SuperState ss2 = (SuperState) n2;
//				if (ss2.containsNode(n2)) {
//					return -1;
//				}
//			}
//			return n1.getName().compareTo(n2.getName());
//		}
//	}
//
//	public void execute(ExecutionContext executionContext) throws Exception {
//		exitFromNodes(executionContext);
//		executionContext.getTransition().fireEvent("effext", executionContext);
//	}
//
//	private void exitFromNodes(ExecutionContext executionContext) {
//		Token token = executionContext.getToken();
//		boolean wasNull = false;
//		if (wasNull = token.getNode() == null) {
//			token.setNode(executionContext.getTransitionSource());
//		}
//		Transition transition = executionContext.getTransition();
//		Node effectiveFromNode = getEffectiveFromNode(transition);
//		Token effectiveFromToken = getEffectiveFromToken(token,
//				effectiveFromNode);
//		SortedSet<Node> nodes = new TreeSet<Node>(new NodeComparator());
//		addAllChildNodes(effectiveFromNode, effectiveFromToken, nodes);
//		fireExitActions(executionContext, nodes);
//		if (crossesStateBoundary(effectiveFromNode, transition.getTo())) {
//			endChildTokens(effectiveFromToken);
//			effectiveFromNode.fireEvent("state-exit", executionContext);
//		}
//		if (wasNull) {
//			token.setNode(null);
//		}
//		if (!effectiveFromToken.equals(token)) {
//			// Fake
//			executionContext.token = effectiveFromToken;
//		}
//	}
//
//	private void addAllChildNodes(Node effectiveFromNode, Token token,
//			SortedSet<Node> nodes) {
//		for (Token t : (Collection<Token>) token.getActiveChildren().values()) {
//			addAllChildNodes(effectiveFromNode, t, nodes);
//			Node n = t.getNode();
//			Node parentNode = getNearestSupserstate(effectiveFromNode, t);
//			while (n.getSuperState() != null) {
//				nodes.add(n);
//				if (n.getSuperState().equals(parentNode)) {
//					break;
//				} else {
//					n = n.getSuperState();
//				}
//			}
//		}
//	}
//
//	private Node getNearestSupserstate(Node effectiveFromNode, Token t) {
//		Node parentNode;
//		Token parentToken = t.getParent();
//		if (parentToken == null
//				|| parentToken.getNode().getSuperState() == null) {
//			parentNode = effectiveFromNode;
//		} else {
//			parentNode = parentToken.getNode().getSuperState();
//		}
//		return parentNode;
//	}
//
//	private Node getEffectiveFromNode(Transition transition) {
//		Node result = transition.getFrom();
//		while (result.getSuperState() != null) {
//			if (result.getSuperState().containsNode(transition.getTo())) {
//				break;
//			} else {
//				result = result.getSuperState();
//			}
//		}
//		return result;
//	}
//
//	private Token getEffectiveFromToken(Token token, Node fromNode) {
//		Token result = token;
//		while (result.getParent() != null) {
//			if (result.getParent().getNode().getParentChain()
//					.contains(fromNode)) {
//				result = result.getParent();
//			} else {
//				break;
//			}
//		}
//		return result;
//	}
//
//	void endChildTokens(Token token) {
//		Collection<Token> activeChildren = token
//				.getActiveChildren().values();
//		for (Token t : activeChildren) {
//			t.end(false);
//		}
//	}
//
//	void fireExitActions(ExecutionContext executionContext,
//			SortedSet<Node> nodes) {
//		Node to = executionContext.getTransition().getTo();
//		for (Node node : nodes) {
//			if (crossesStateBoundary(node, to)) {
//				node.fireEvent("state-exit", executionContext);
//			}
//		}
//	}
//
//	protected boolean crossesStateBoundary(Node from, Node to) {
//		if (from instanceof SuperState) {
//			SuperState fromsuperState = (SuperState) from;
//			if (fromsuperState == to || fromsuperState.containsNode(to)) {
//				return false;
//			}
//		}
//		return true;
//	}
}
