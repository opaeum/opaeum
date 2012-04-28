package org.opaeum.rap.runtime.cubetree;

import java.util.List;

import org.olap4j.OlapConnection;
import org.olap4j.Position;
import org.olap4j.metadata.Cube;
import org.olap4j.metadata.Member;

public class CubeRowTree{
	public CubeRow row;
	public Member member;
	public Member measure;
	public List<CubeRowTree> children;
	public Object value;
}
