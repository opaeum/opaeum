package org.nakeduml.runtime.domain;

import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ExceptionAnalyser{
	private Exception exception;
	private Throwable rootCause;
	public ExceptionAnalyser(Exception exception){
		this.exception = exception;
	}
	public String getStackTrace(Throwable e){
		CharArrayWriter w = new CharArrayWriter();
		e.printStackTrace(new PrintWriter(w));
		String stackTrace = new String(w.toCharArray());
		return stackTrace;
	}
	public boolean isResourceAllocationTimeout(){
		return occursIn("javax.resource.ResourceException: Interrupted while requesting permit", exception);
	}
	public boolean isStaleStateException(){
		return occursIn("org.hibernate.StaleObjectStateException", exception);
	}
	public Throwable getRootCause(){
		if(this.rootCause == null){
			this.rootCause = findCause(exception);
		}
		return rootCause;
	}
	public RuntimeException wrapRootCauseIfNecessary(){
		if(getRootCause() instanceof RuntimeException){
			return  (RuntimeException) getRootCause();
		}else if(getRootCause() instanceof Error){
			return new RuntimeException(getRootCause());
		}else{
			return  new RuntimeException(getRootCause());
		}
	}
	public void throwRootCause(){
		throw wrapRootCauseIfNecessary();
	}
	public boolean isDeadlockException(){
		Throwable se = findFirstCause(SQLException.class);
		if(se != null){
			return occursIn("org.postgresql.util.PSQLException: ERROR: deadlock detected", se);
		}
		return false;
	}
	public boolean stringOccurs(String string){
		return occursIn(string, exception);
	}
	private boolean occursIn(String string,Throwable se){
		boolean r = false;
		Set<Throwable> causes = new HashSet<Throwable>();
		do{
			if(getStackTrace(se).contains(string)){
				r = true;
				break;
			}else{
				se = getCause(se);
			}
		}while(!(se == null || causes.contains(se)));
		return r;
	}
	public <T extends Throwable>T findFirstCause(Class<T> class1){
		int i = 20;
		Throwable cause = exception;
		while(!(class1.isInstance(cause) || i-- == 0 || cause == null)){
			cause = getCause(cause);
		}
		return class1.cast(cause);
	}
	private Throwable findCause(Throwable e){
		Set<Throwable> causes = new HashSet<Throwable>();
		int i = 30;
		Throwable cause = e;
		while(!(i-- == 0 || getCause(cause) == null || causes.contains(cause))){
			cause = getCause(cause);
		}
		return cause;
	}
	private Throwable getCause(Throwable source){
		Throwable cause = null;
		if(source instanceof InvocationTargetException){
			InvocationTargetException e = (InvocationTargetException) source;
			cause = e.getTargetException();
		}else if(source instanceof UndeclaredThrowableException){
			UndeclaredThrowableException e = (UndeclaredThrowableException) source;
			cause = e.getUndeclaredThrowable();
		}else if(source instanceof SQLException){
			SQLException e = (SQLException) source;
			cause = e.getNextException();
		}
		if(cause == null){
			// Fallback case
			return source.getCause();
		}else{
			return cause;
		}
	}
}
