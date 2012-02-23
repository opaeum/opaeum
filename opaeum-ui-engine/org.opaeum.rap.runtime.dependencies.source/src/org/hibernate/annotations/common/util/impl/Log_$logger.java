
package org.hibernate.annotations.common.util.impl;

import java.io.Serializable;
import javax.annotation.Generated;
import org.jboss.logging.BasicLogger;
import org.jboss.logging.Logger;


/**
 * Warning this class consists of generated code.
 * 
 */
@Generated(value = "org.jboss.logging.generator.model.MessageLoggerImplementor", date = "2011-11-29T18:17:12+0100")
public class Log_$logger
    implements Serializable, Log, BasicLogger
{

    private final static long serialVersionUID = 1L;
    private final static String projectCode = "HCANN";
    private final static String FQCN = Log_$logger.class.getName();
    protected final Logger log;
    private final static String assertionFailure = "An assertion failure occurred (this may indicate a bug in Hibernate)";
    private final static String version = "Hibernate Commons Annotations {%1$s}";

    public Log_$logger(final Logger log) {
        this.log = log;
    }

    @Override
    public final boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    @Override
    public final void trace(Object message) {
        log.trace(FQCN, message, null);
    }

    @Override
    public final void trace(Object message, Throwable t) {
        log.trace(FQCN, message, t);
    }

    @Override
    public final void trace(String loggerFqcn, Object message, Throwable t) {
        log.trace(loggerFqcn, message, t);
    }

    @Override
    public final void trace(String loggerFqcn, Object message, Object[] params, Throwable t) {
        log.trace(loggerFqcn, message, params, t);
    }

    @Override
    public final void tracev(String format, Object... params) {
        log.logv(FQCN, Logger.Level.TRACE, null, format, params);
    }

    @Override
    public final void tracev(String format, Object param1) {
        log.logv(FQCN, Logger.Level.TRACE, null, format, param1);
    }

    @Override
    public final void tracev(String format, Object param1, Object param2) {
        log.logv(FQCN, Logger.Level.TRACE, null, format, param1, param2);
    }

    @Override
    public final void tracev(String format, Object param1, Object param2, Object param3) {
        log.logv(FQCN, Logger.Level.TRACE, null, format, param1, param2, param3);
    }

    @Override
    public final void tracev(Throwable t, String format, Object... params) {
        log.logv(FQCN, Logger.Level.TRACE, t, format, params);
    }

    @Override
    public final void tracev(Throwable t, String format, Object param1) {
        log.logv(FQCN, Logger.Level.TRACE, t, format, param1);
    }

    @Override
    public final void tracev(Throwable t, String format, Object param1, Object param2) {
        log.logv(FQCN, Logger.Level.TRACE, t, format, param1, param2);
    }

    @Override
    public final void tracev(Throwable t, String format, Object param1, Object param2, Object param3) {
        log.logv(FQCN, Logger.Level.TRACE, t, format, param1, param2, param3);
    }

    @Override
    public final void tracef(String format, Object... params) {
        log.logf(FQCN, Logger.Level.TRACE, null, format, params);
    }

    @Override
    public final void tracef(String format, Object param1) {
        log.logf(FQCN, Logger.Level.TRACE, null, format, param1);
    }

    @Override
    public final void tracef(String format, Object param1, Object param2) {
        log.logf(FQCN, Logger.Level.TRACE, null, format, param1, param2);
    }

    @Override
    public final void tracef(String format, Object param1, Object param2, Object param3) {
        log.logf(FQCN, Logger.Level.TRACE, null, format, param1, param2, param3);
    }

    @Override
    public final void tracef(Throwable t, String format, Object... params) {
        log.logf(FQCN, Logger.Level.TRACE, t, format, params);
    }

    @Override
    public final void tracef(Throwable t, String format, Object param1) {
        log.logf(FQCN, Logger.Level.TRACE, t, format, param1);
    }

    @Override
    public final void tracef(Throwable t, String format, Object param1, Object param2) {
        log.logf(FQCN, Logger.Level.TRACE, t, format, param1, param2);
    }

    @Override
    public final void tracef(Throwable t, String format, Object param1, Object param2, Object param3) {
        log.logf(FQCN, Logger.Level.TRACE, t, format, param1, param2, param3);
    }

    @Override
    public final boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    public final void debug(Object message) {
        log.debug(FQCN, message, null);
    }

    @Override
    public final void debug(Object message, Throwable t) {
        log.debug(FQCN, message, t);
    }

    @Override
    public final void debug(String loggerFqcn, Object message, Throwable t) {
        log.debug(loggerFqcn, message, t);
    }

    @Override
    public final void debug(String loggerFqcn, Object message, Object[] params, Throwable t) {
        log.debug(loggerFqcn, message, params, t);
    }

    @Override
    public final void debugv(String format, Object... params) {
        log.logv(FQCN, Logger.Level.DEBUG, null, format, params);
    }

    @Override
    public final void debugv(String format, Object param1) {
        log.logv(FQCN, Logger.Level.DEBUG, null, format, param1);
    }

    @Override
    public final void debugv(String format, Object param1, Object param2) {
        log.logv(FQCN, Logger.Level.DEBUG, null, format, param1, param2);
    }

    @Override
    public final void debugv(String format, Object param1, Object param2, Object param3) {
        log.logv(FQCN, Logger.Level.DEBUG, null, format, param1, param2, param3);
    }

    @Override
    public final void debugv(Throwable t, String format, Object... params) {
        log.logv(FQCN, Logger.Level.DEBUG, t, format, params);
    }

    @Override
    public final void debugv(Throwable t, String format, Object param1) {
        log.logv(FQCN, Logger.Level.DEBUG, t, format, param1);
    }

    @Override
    public final void debugv(Throwable t, String format, Object param1, Object param2) {
        log.logv(FQCN, Logger.Level.DEBUG, t, format, param1, param2);
    }

    @Override
    public final void debugv(Throwable t, String format, Object param1, Object param2, Object param3) {
        log.logv(FQCN, Logger.Level.DEBUG, t, format, param1, param2, param3);
    }

    @Override
    public final void debugf(String format, Object... params) {
        log.logf(FQCN, Logger.Level.DEBUG, null, format, params);
    }

    @Override
    public final void debugf(String format, Object param1) {
        log.logf(FQCN, Logger.Level.DEBUG, null, format, param1);
    }

    @Override
    public final void debugf(String format, Object param1, Object param2) {
        log.logf(FQCN, Logger.Level.DEBUG, null, format, param1, param2);
    }

    @Override
    public final void debugf(String format, Object param1, Object param2, Object param3) {
        log.logf(FQCN, Logger.Level.DEBUG, null, format, param1, param2, param3);
    }

    @Override
    public final void debugf(Throwable t, String format, Object... params) {
        log.logf(FQCN, Logger.Level.DEBUG, t, format, params);
    }

    @Override
    public final void debugf(Throwable t, String format, Object param1) {
        log.logf(FQCN, Logger.Level.DEBUG, t, format, param1);
    }

    @Override
    public final void debugf(Throwable t, String format, Object param1, Object param2) {
        log.logf(FQCN, Logger.Level.DEBUG, t, format, param1, param2);
    }

    @Override
    public final void debugf(Throwable t, String format, Object param1, Object param2, Object param3) {
        log.logf(FQCN, Logger.Level.DEBUG, t, format, param1, param2, param3);
    }

    @Override
    public final boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    @Override
    public final void info(Object message) {
        log.info(FQCN, message, null);
    }

    @Override
    public final void info(Object message, Throwable t) {
        log.info(FQCN, message, t);
    }

    @Override
    public final void info(String loggerFqcn, Object message, Throwable t) {
        log.info(loggerFqcn, message, t);
    }

    @Override
    public final void info(String loggerFqcn, Object message, Object[] params, Throwable t) {
        log.info(loggerFqcn, message, params, t);
    }

    @Override
    public final void infov(String format, Object... params) {
        log.logv(FQCN, Logger.Level.INFO, null, format, params);
    }

    @Override
    public final void infov(String format, Object param1) {
        log.logv(FQCN, Logger.Level.INFO, null, format, param1);
    }

    @Override
    public final void infov(String format, Object param1, Object param2) {
        log.logv(FQCN, Logger.Level.INFO, null, format, param1, param2);
    }

    @Override
    public final void infov(String format, Object param1, Object param2, Object param3) {
        log.logv(FQCN, Logger.Level.INFO, null, format, param1, param2, param3);
    }

    @Override
    public final void infov(Throwable t, String format, Object... params) {
        log.logv(FQCN, Logger.Level.INFO, t, format, params);
    }

    @Override
    public final void infov(Throwable t, String format, Object param1) {
        log.logv(FQCN, Logger.Level.INFO, t, format, param1);
    }

    @Override
    public final void infov(Throwable t, String format, Object param1, Object param2) {
        log.logv(FQCN, Logger.Level.INFO, t, format, param1, param2);
    }

    @Override
    public final void infov(Throwable t, String format, Object param1, Object param2, Object param3) {
        log.logv(FQCN, Logger.Level.INFO, t, format, param1, param2, param3);
    }

    @Override
    public final void infof(String format, Object... params) {
        log.logf(FQCN, Logger.Level.INFO, null, format, params);
    }

    @Override
    public final void infof(String format, Object param1) {
        log.logf(FQCN, Logger.Level.INFO, null, format, param1);
    }

    @Override
    public final void infof(String format, Object param1, Object param2) {
        log.logf(FQCN, Logger.Level.INFO, null, format, param1, param2);
    }

    @Override
    public final void infof(String format, Object param1, Object param2, Object param3) {
        log.logf(FQCN, Logger.Level.INFO, null, format, param1, param2, param3);
    }

    @Override
    public final void infof(Throwable t, String format, Object... params) {
        log.logf(FQCN, Logger.Level.INFO, t, format, params);
    }

    @Override
    public final void infof(Throwable t, String format, Object param1) {
        log.logf(FQCN, Logger.Level.INFO, t, format, param1);
    }

    @Override
    public final void infof(Throwable t, String format, Object param1, Object param2) {
        log.logf(FQCN, Logger.Level.INFO, t, format, param1, param2);
    }

    @Override
    public final void infof(Throwable t, String format, Object param1, Object param2, Object param3) {
        log.logf(FQCN, Logger.Level.INFO, t, format, param1, param2, param3);
    }

    @Override
    public final void warn(Object message) {
        log.warn(FQCN, message, null);
    }

    @Override
    public final void warn(Object message, Throwable t) {
        log.warn(FQCN, message, t);
    }

    @Override
    public final void warn(String loggerFqcn, Object message, Throwable t) {
        log.warn(loggerFqcn, message, t);
    }

    @Override
    public final void warn(String loggerFqcn, Object message, Object[] params, Throwable t) {
        log.warn(loggerFqcn, message, params, t);
    }

    @Override
    public final void warnv(String format, Object... params) {
        log.logv(FQCN, Logger.Level.WARN, null, format, params);
    }

    @Override
    public final void warnv(String format, Object param1) {
        log.logv(FQCN, Logger.Level.WARN, null, format, param1);
    }

    @Override
    public final void warnv(String format, Object param1, Object param2) {
        log.logv(FQCN, Logger.Level.WARN, null, format, param1, param2);
    }

    @Override
    public final void warnv(String format, Object param1, Object param2, Object param3) {
        log.logv(FQCN, Logger.Level.WARN, null, format, param1, param2, param3);
    }

    @Override
    public final void warnv(Throwable t, String format, Object... params) {
        log.logv(FQCN, Logger.Level.WARN, t, format, params);
    }

    @Override
    public final void warnv(Throwable t, String format, Object param1) {
        log.logv(FQCN, Logger.Level.WARN, t, format, param1);
    }

    @Override
    public final void warnv(Throwable t, String format, Object param1, Object param2) {
        log.logv(FQCN, Logger.Level.WARN, t, format, param1, param2);
    }

    @Override
    public final void warnv(Throwable t, String format, Object param1, Object param2, Object param3) {
        log.logv(FQCN, Logger.Level.WARN, t, format, param1, param2, param3);
    }

    @Override
    public final void warnf(String format, Object... params) {
        log.logf(FQCN, Logger.Level.WARN, null, format, params);
    }

    @Override
    public final void warnf(String format, Object param1) {
        log.logf(FQCN, Logger.Level.WARN, null, format, param1);
    }

    @Override
    public final void warnf(String format, Object param1, Object param2) {
        log.logf(FQCN, Logger.Level.WARN, null, format, param1, param2);
    }

    @Override
    public final void warnf(String format, Object param1, Object param2, Object param3) {
        log.logf(FQCN, Logger.Level.WARN, null, format, param1, param2, param3);
    }

    @Override
    public final void warnf(Throwable t, String format, Object... params) {
        log.logf(FQCN, Logger.Level.WARN, t, format, params);
    }

    @Override
    public final void warnf(Throwable t, String format, Object param1) {
        log.logf(FQCN, Logger.Level.WARN, t, format, param1);
    }

    @Override
    public final void warnf(Throwable t, String format, Object param1, Object param2) {
        log.logf(FQCN, Logger.Level.WARN, t, format, param1, param2);
    }

    @Override
    public final void warnf(Throwable t, String format, Object param1, Object param2, Object param3) {
        log.logf(FQCN, Logger.Level.WARN, t, format, param1, param2, param3);
    }

    @Override
    public final void error(Object message) {
        log.error(FQCN, message, null);
    }

    @Override
    public final void error(Object message, Throwable t) {
        log.error(FQCN, message, t);
    }

    @Override
    public final void error(String loggerFqcn, Object message, Throwable t) {
        log.error(loggerFqcn, message, t);
    }

    @Override
    public final void error(String loggerFqcn, Object message, Object[] params, Throwable t) {
        log.error(loggerFqcn, message, params, t);
    }

    @Override
    public final void errorv(String format, Object... params) {
        log.logv(FQCN, Logger.Level.ERROR, null, format, params);
    }

    @Override
    public final void errorv(String format, Object param1) {
        log.logv(FQCN, Logger.Level.ERROR, null, format, param1);
    }

    @Override
    public final void errorv(String format, Object param1, Object param2) {
        log.logv(FQCN, Logger.Level.ERROR, null, format, param1, param2);
    }

    @Override
    public final void errorv(String format, Object param1, Object param2, Object param3) {
        log.logv(FQCN, Logger.Level.ERROR, null, format, param1, param2, param3);
    }

    @Override
    public final void errorv(Throwable t, String format, Object... params) {
        log.logv(FQCN, Logger.Level.ERROR, t, format, params);
    }

    @Override
    public final void errorv(Throwable t, String format, Object param1) {
        log.logv(FQCN, Logger.Level.ERROR, t, format, param1);
    }

    @Override
    public final void errorv(Throwable t, String format, Object param1, Object param2) {
        log.logv(FQCN, Logger.Level.ERROR, t, format, param1, param2);
    }

    @Override
    public final void errorv(Throwable t, String format, Object param1, Object param2, Object param3) {
        log.logv(FQCN, Logger.Level.ERROR, t, format, param1, param2, param3);
    }

    @Override
    public final void errorf(String format, Object... params) {
        log.logf(FQCN, Logger.Level.ERROR, null, format, params);
    }

    @Override
    public final void errorf(String format, Object param1) {
        log.logf(FQCN, Logger.Level.ERROR, null, format, param1);
    }

    @Override
    public final void errorf(String format, Object param1, Object param2) {
        log.logf(FQCN, Logger.Level.ERROR, null, format, param1, param2);
    }

    @Override
    public final void errorf(String format, Object param1, Object param2, Object param3) {
        log.logf(FQCN, Logger.Level.ERROR, null, format, param1, param2, param3);
    }

    @Override
    public final void errorf(Throwable t, String format, Object... params) {
        log.logf(FQCN, Logger.Level.ERROR, t, format, params);
    }

    @Override
    public final void errorf(Throwable t, String format, Object param1) {
        log.logf(FQCN, Logger.Level.ERROR, t, format, param1);
    }

    @Override
    public final void errorf(Throwable t, String format, Object param1, Object param2) {
        log.logf(FQCN, Logger.Level.ERROR, t, format, param1, param2);
    }

    @Override
    public final void errorf(Throwable t, String format, Object param1, Object param2, Object param3) {
        log.logf(FQCN, Logger.Level.ERROR, t, format, param1, param2, param3);
    }

    @Override
    public final void fatal(Object message) {
        log.fatal(FQCN, message, null);
    }

    @Override
    public final void fatal(Object message, Throwable t) {
        log.fatal(FQCN, message, t);
    }

    @Override
    public final void fatal(String loggerFqcn, Object message, Throwable t) {
        log.fatal(loggerFqcn, message, t);
    }

    @Override
    public final void fatal(String loggerFqcn, Object message, Object[] params, Throwable t) {
        log.fatal(loggerFqcn, message, params, t);
    }

    @Override
    public final void fatalv(String format, Object... params) {
        log.logv(FQCN, Logger.Level.FATAL, null, format, params);
    }

    @Override
    public final void fatalv(String format, Object param1) {
        log.logv(FQCN, Logger.Level.FATAL, null, format, param1);
    }

    @Override
    public final void fatalv(String format, Object param1, Object param2) {
        log.logv(FQCN, Logger.Level.FATAL, null, format, param1, param2);
    }

    @Override
    public final void fatalv(String format, Object param1, Object param2, Object param3) {
        log.logv(FQCN, Logger.Level.FATAL, null, format, param1, param2, param3);
    }

    @Override
    public final void fatalv(Throwable t, String format, Object... params) {
        log.logv(FQCN, Logger.Level.FATAL, t, format, params);
    }

    @Override
    public final void fatalv(Throwable t, String format, Object param1) {
        log.logv(FQCN, Logger.Level.FATAL, t, format, param1);
    }

    @Override
    public final void fatalv(Throwable t, String format, Object param1, Object param2) {
        log.logv(FQCN, Logger.Level.FATAL, t, format, param1, param2);
    }

    @Override
    public final void fatalv(Throwable t, String format, Object param1, Object param2, Object param3) {
        log.logv(FQCN, Logger.Level.FATAL, t, format, param1, param2, param3);
    }

    @Override
    public final void fatalf(String format, Object... params) {
        log.logf(FQCN, Logger.Level.FATAL, null, format, params);
    }

    @Override
    public final void fatalf(String format, Object param1) {
        log.logf(FQCN, Logger.Level.FATAL, null, format, param1);
    }

    @Override
    public final void fatalf(String format, Object param1, Object param2) {
        log.logf(FQCN, Logger.Level.FATAL, null, format, param1, param2);
    }

    @Override
    public final void fatalf(String format, Object param1, Object param2, Object param3) {
        log.logf(FQCN, Logger.Level.FATAL, null, format, param1, param2, param3);
    }

    @Override
    public final void fatalf(Throwable t, String format, Object... params) {
        log.logf(FQCN, Logger.Level.FATAL, t, format, params);
    }

    @Override
    public final void fatalf(Throwable t, String format, Object param1) {
        log.logf(FQCN, Logger.Level.FATAL, t, format, param1);
    }

    @Override
    public final void fatalf(Throwable t, String format, Object param1, Object param2) {
        log.logf(FQCN, Logger.Level.FATAL, t, format, param1, param2);
    }

    @Override
    public final void fatalf(Throwable t, String format, Object param1, Object param2, Object param3) {
        log.logf(FQCN, Logger.Level.FATAL, t, format, param1, param2, param3);
    }

    public final boolean isEnabled(Logger.Level level) {
        return log.isEnabled(level);
    }

    @Override
    public final void log(Logger.Level level, Object message) {
        log.log(FQCN, level, message, null, null);
    }

    @Override
    public final void log(Logger.Level level, Object message, Throwable t) {
        log.log(FQCN, level, message, null, t);
    }

    @Override
    public final void log(Logger.Level level, String loggerFqcn, Object message, Throwable t) {
        log.log(level, loggerFqcn, message, t);
    }

    @Override
    public final void log(String loggerFqcn, Logger.Level level, Object message, Object[] params, Throwable t) {
        log.log(loggerFqcn, level, message, params, t);
    }

    @Override
    public final void logv(Logger.Level level, String format, Object... params) {
        log.logv(FQCN, level, null, format, params);
    }

    @Override
    public final void logv(Logger.Level level, String format, Object param1) {
        log.logv(FQCN, level, null, format, param1);
    }

    @Override
    public final void logv(Logger.Level level, String format, Object param1, Object param2) {
        log.logv(FQCN, level, null, format, param1, param2);
    }

    @Override
    public final void logv(Logger.Level level, String format, Object param1, Object param2, Object param3) {
        log.logv(FQCN, level, null, format, param1, param2, param3);
    }

    @Override
    public final void logv(Logger.Level level, Throwable t, String format, Object... params) {
        log.logv(FQCN, level, t, format, params);
    }

    @Override
    public final void logv(Logger.Level level, Throwable t, String format, Object param1) {
        log.logv(FQCN, level, t, format, param1);
    }

    @Override
    public final void logv(Logger.Level level, Throwable t, String format, Object param1, Object param2) {
        log.logv(FQCN, level, t, format, param1, param2);
    }

    @Override
    public final void logv(Logger.Level level, Throwable t, String format, Object param1, Object param2, Object param3) {
        log.logv(FQCN, level, t, format, param1, param2, param3);
    }

    @Override
    public final void logv(String loggerFqcn, Logger.Level level, Throwable t, String format, Object... params) {
        log.logv(loggerFqcn, level, t, format, params);
    }

    @Override
    public final void logv(String loggerFqcn, Logger.Level level, Throwable t, String format, Object param1) {
        log.logv(loggerFqcn, level, t, format, param1);
    }

    @Override
    public final void logv(String loggerFqcn, Logger.Level level, Throwable t, String format, Object param1, Object param2) {
        log.logv(loggerFqcn, level, t, format, param1, param2);
    }

    @Override
    public final void logv(String loggerFqcn, Logger.Level level, Throwable t, String format, Object param1, Object param2, Object param3) {
        log.logv(loggerFqcn, level, t, format, param1, param2, param3);
    }

    @Override
    public final void logf(Logger.Level level, String format, Object... params) {
        log.logf(FQCN, level, null, format, params);
    }

    @Override
    public final void logf(Logger.Level level, String format, Object param1) {
        log.logf(FQCN, level, null, format, param1);
    }

    @Override
    public final void logf(Logger.Level level, String format, Object param1, Object param2) {
        log.logf(FQCN, level, null, format, param1, param2);
    }

    @Override
    public final void logf(Logger.Level level, String format, Object param1, Object param2, Object param3) {
        log.logf(FQCN, level, null, format, param1, param2, param3);
    }

    @Override
    public final void logf(Logger.Level level, Throwable t, String format, Object... params) {
        log.logf(FQCN, level, t, format, params);
    }

    @Override
    public final void logf(Logger.Level level, Throwable t, String format, Object param1) {
        log.logf(FQCN, level, t, format, param1);
    }

    @Override
    public final void logf(Logger.Level level, Throwable t, String format, Object param1, Object param2) {
        log.logf(FQCN, level, t, format, param1, param2);
    }

    @Override
    public final void logf(Logger.Level level, Throwable t, String format, Object param1, Object param2, Object param3) {
        log.logf(FQCN, level, t, format, param1, param2, param3);
    }

    @Override
    public final void logf(String loggerFqcn, Logger.Level level, Throwable t, String format, Object... params) {
        log.logf(loggerFqcn, level, t, format, params);
    }

    @Override
    public final void logf(String loggerFqcn, Logger.Level level, Throwable t, String format, Object param1) {
        log.logf(loggerFqcn, level, t, format, param1);
    }

    @Override
    public final void logf(String loggerFqcn, Logger.Level level, Throwable t, String format, Object param1, Object param2) {
        log.logf(loggerFqcn, level, t, format, param1, param2);
    }

    @Override
    public final void logf(String loggerFqcn, Logger.Level level, Throwable t, String format, Object param1, Object param2, Object param3) {
        log.logf(loggerFqcn, level, t, format, param1, param2, param3);
    }

    @Override
    public final void assertionFailure(final Throwable t) {
        log.logf(FQCN, (Logger.Level.ERROR), (t), ((projectCode +"000002: ")+ assertionFailure$str()));
    }

    protected String assertionFailure$str() {
        return assertionFailure;
    }

    @Override
    public final void version(final String version) {
        log.logf(FQCN, (Logger.Level.INFO), null, ((projectCode +"000001: ")+ version$str()), version);
    }

    protected String version$str() {
        return version;
    }

}
