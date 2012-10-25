package org.opaeum.ocl.uml;

import lpg.runtime.IPrsStream;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.ocl.lpg.AbstractParser;
import org.eclipse.ocl.parser.OCLProblemHandler;

public class OpaeumOclProblemHandler extends OCLProblemHandler{
	BasicDiagnostic diagnostics;
	OpaeumOclProblemHandler(AbstractParser parser){
		super(parser);
	}
	@Override
	public void handleProblem(Severity problemSeverity,Phase processingPhase,String problemMessage,String processingContext,
			int startOffset,int endOffset){
		IPrsStream prsStream = getIPrsStream();
		int leftToken = prsStream.getTokenIndexAtCharacter(startOffset);
		int rightToken = prsStream.getTokenIndexAtCharacter(endOffset);
		int leftTokenLoc = (leftToken > rightToken ? rightToken : leftToken);
		int rightTokenLoc = rightToken;
		int line = prsStream.getLine(leftTokenLoc) + getErrorReportLineOffset();
		OpaeumDiagnostic diagnostic = new OpaeumDiagnostic(problemSeverity.getDiagnosticSeverity(), "org.eclipse.ocl", 1, problemMessage,
				new Object[]{processingContext});
		if(line > 0){
			diagnostic.setStartLine(prsStream.getLine(leftTokenLoc) + getErrorReportLineOffset());
			diagnostic.setStartPosition(prsStream.getColumn(leftTokenLoc));
			diagnostic.setEndPosition(prsStream.getEndColumn(rightTokenLoc));
			diagnostic.setEndLine(prsStream.getEndLine(rightTokenLoc) + getErrorReportLineOffset());
		}
		if(diagnostics == null){
			diagnostics = diagnostic;
		}else{
			diagnostics.add(diagnostic);
		}
	}
	@Override
	public void clearDiagnostic(){
		diagnostics=null;
		super.clearDiagnostic();
	}
	public BasicDiagnostic getDiagnostic(){
		return diagnostics;
	}
	@Override
	public void parserProblem(Severity problemSeverity,String problemMessage,String processingContext,int startOffset,int endOffset){
		IPrsStream prsStream = getIPrsStream();
		int leftToken = prsStream.getTokenIndexAtCharacter(startOffset);
		int rightToken = prsStream.getTokenIndexAtCharacter(endOffset);
		int leftTokenLoc = (leftToken > rightToken ? rightToken : leftToken);
		int rightTokenLoc = rightToken;
		int line = prsStream.getLine(leftTokenLoc) + getErrorReportLineOffset();
		OpaeumDiagnostic diagnostic = new OpaeumDiagnostic(problemSeverity.getDiagnosticSeverity(), "org.eclipse.ocl", 1, problemMessage,
				new Object[]{processingContext});
		if(line > 0){
			diagnostic.setStartLine(prsStream.getLine(leftTokenLoc) + getErrorReportLineOffset());
			diagnostic.setStartPosition(prsStream.getColumn(leftTokenLoc));
			diagnostic.setEndPosition(prsStream.getEndColumn(rightTokenLoc));
			diagnostic.setEndLine(prsStream.getEndLine(rightTokenLoc) + getErrorReportLineOffset());
		}
		if(diagnostics == null){
			diagnostics = diagnostic;
		}else{
			diagnostics.add(diagnostic);
		}
		
	}
}