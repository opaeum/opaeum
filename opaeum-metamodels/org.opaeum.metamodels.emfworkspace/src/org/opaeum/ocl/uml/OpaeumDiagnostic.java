package org.opaeum.ocl.uml;

import java.util.List;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;

public class OpaeumDiagnostic extends BasicDiagnostic{
	private int startLine;
	private int endLine;
	private int startPosition;
	private int endPosition;
	public int getStartLine(){
		return startLine;
	}
	public void setStartLine(int startLine){
		this.startLine = startLine;
	}
	public int getEndLine(){
		return endLine;
	}
	public void setEndLine(int endLine){
		this.endLine = endLine;
	}
	public int getStartPosition(){
		return startPosition;
	}
	public void setStartPosition(int startPosition){
		this.startPosition = startPosition;
	}
	public int getEndPosition(){
		return endPosition;
	}
	public void setEndPosition(int endPosition){
		this.endPosition = endPosition;
	}
	@Override
	public String toString(){
		return super.getMessage();
	}
	public OpaeumDiagnostic(){
		super();
	}
	public OpaeumDiagnostic(int severity,String source,int code,String message,Object[] data){
		super(severity, source, code, message, data);
	}
	public OpaeumDiagnostic(String source,int code,List<? extends Diagnostic> children,String message,Object[] data){
		super(source, code, children, message, data);
	}
	public OpaeumDiagnostic(String source,int code,String message,Object[] data){
		super(source, code, message, data);
	}
}
