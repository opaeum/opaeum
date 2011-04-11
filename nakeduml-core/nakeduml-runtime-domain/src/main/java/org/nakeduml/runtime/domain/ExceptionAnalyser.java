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
	private Exception rootCause;
	public ExceptionAnalyser(Exception exception){
		this.exception = exception;
	}
	public String getStackTrace(Exception e){
		CharArrayWriter w = new CharArrayWriter();
		e.printStackTrace(new PrintWriter(w));
		String stackTrace = new String(w.toCharArray());
		return stackTrace;
	}
	public boolean isStaleStateException(){
		return occursIn("org.hibernate.StaleObjectStateException", exception);
	}
	public Exception getRootCause(){
		if(this.rootCause == null){
			this.rootCause = findCause(exception);
		}
		return rootCause;
	}
	public void throwRootCause(){
		if(getRootCause() instanceof RuntimeException){
			throw (RuntimeException) getRootCause();
		}else{
			throw new RuntimeException(getRootCause());
		}
	}
	public boolean isDeadlockException(){
		Exception se = findFirstCause(SQLException.class);
		if(se != null){
			return occursIn("org.postgresql.util.PSQLException: ERROR: deadlock detected", se);
		}
		return false;
	}
	public boolean stringOccurs(String string){
		return occursIn(string, exception);
	}
	private boolean occursIn(String string,Exception se){
		boolean r = false;
		Set<Exception> causes = new HashSet<Exception>();
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
	public <T extends Exception>T findFirstCause(Class<T> class1){
		int i = 20;
		Exception cause = exception;
		while(!(class1.isInstance(cause) || i-- == 0 || cause == null)){
			cause = getCause(cause);
		}
		return class1.cast(cause);
	}
	private Exception findCause(Exception e){
		Set<Exception> causes = new HashSet<Exception>();
		int i = 30;
		Exception cause = e;
		while(!(i-- == 0 || getCause(cause) == null || causes.contains(cause))){
			cause = getCause(cause);
		}
		return cause;
	}
	private Exception getCause(Exception source){
		Exception cause = null;
		if(source instanceof InvocationTargetException){
			InvocationTargetException e = (InvocationTargetException) source;
			cause = (Exception) e.getTargetException();
		}else if(source instanceof UndeclaredThrowableException){
			UndeclaredThrowableException e = (UndeclaredThrowableException) source;
			cause = (Exception) e.getUndeclaredThrowable();
		}else if(source instanceof SQLException){
			SQLException e = (SQLException) source;
			cause = (Exception) e.getNextException();
		}
		if(cause == null){
			// Fallback case
			return (Exception) source.getCause();
		}else{
			return cause;
		}
	}
}
