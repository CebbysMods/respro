package lv.cebbys.mcmods.respro.imp.loggers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


public class ResproLogger {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final Level INFO = Level.INFO;
    private static final Level ERROR = Level.ERROR;
    private static final Level WARN = Level.WARN;
    private static final Level DEBUG = Level.INFO;
    private static final Level FAIL = Level.FATAL;
    private static final Level EXCEPT = Level.FATAL;

    public static void info(Object msg) {
        logMessage(INFO, msg);
    }

    public static void info(String format, Object... elements) {
        logMessage(INFO, String.format(format, elements));
    }

    public static void info(Throwable t, Object msg) {
        logThrowable(INFO, msg, t);
    }

    public static void info(Throwable t, String format, Object... elements) {
        logThrowable(INFO, String.format(format, elements), t);
    }

    public static void debug(Object msg) {
        logMessage(DEBUG, msg);
    }

    public static void debug(String format, Object... elements) {
        logMessage(DEBUG, String.format(format, elements));
    }

    public static void debug(Throwable t, Object msg) {
        logThrowable(DEBUG, msg, t);
    }

    public static void debug(Throwable t, String format, Object... elements) {
        logThrowable(DEBUG, String.format(format, elements), t);
    }

    public static void error(Object msg) {
        logMessage(ERROR, msg);
    }

    public static void error(String format, Object... elements) {
        logMessage(ERROR, String.format(format, elements));
    }

    public static void error(Throwable t, Object msg) {
        logThrowable(ERROR, msg, t);
    }

    public static void error(Throwable t, String format, Object... elements) {
        logThrowable(ERROR, String.format(format, elements), t);
    }

    public static void warn(Object msg) {
        logMessage(WARN, msg);
    }

    public static void warn(String format, Object... elements) {
        logMessage(WARN, String.format(format, elements));
    }

    public static void warn(Throwable t, Object msg) {
        logThrowable(WARN, msg, t);
    }

    public static void warn(Throwable t, String format, Object... elements) {
        logThrowable(WARN, String.format(format, elements), t);
    }

    public static void fail(Object msg) {
        failMessage(FAIL, msg);
    }

    public static void fail(String format, Object... elements) {
        failMessage(FAIL, String.format(format, elements));
    }

    public static void fail(Throwable t, Object msg) {
        failThrowable(FAIL, msg, t);
    }

    public static void fail(Throwable t, String format, Object... elements) {
        failThrowable(FAIL, String.format(format, elements), t);
    }

    public static void expect(Object msg) throws Exception {
        failMessage(EXCEPT, msg);
    }

    public static void expect(String format, Object... elements) throws Exception {
        failMessage(EXCEPT, String.format(format, elements));
    }

    public static void expect(Throwable t, Object msg) throws Exception {
        failThrowable(EXCEPT, msg, t);
    }

    public static void expect(Throwable t, String format, Object... elements) throws Exception {
        failThrowable(EXCEPT, String.format(format, elements), t);
    }

    private static void logMessage(Level level, Object msg) {
        LOGGER.log(level, formatMessage(msg));
    }

    private static void failMessage(Level level, Object msg) {
        String formatted = formatMessage(msg);
        LOGGER.log(level, formatted);
        throw new RuntimeException(msg.toString());
    }

    private static void logThrowable(Level level, Object msg, Throwable throwable) {
        LOGGER.log(level, formatMessage(msg) + "\n" + formatThrowable(throwable));
    }

    private static void failThrowable(Level level, Object msg, Throwable throwable) {
        String formatted = formatMessage(msg) + "\n" + formatThrowable(throwable);
        LOGGER.log(level, formatted);
        throw new RuntimeException(msg.toString(), throwable);
    }

    private static String formatMessage(Object msg) {
        StackTraceElement trace = Thread.currentThread().getStackTrace()[4];
        String className = trace.getClassName();
        className = className.substring(className.lastIndexOf(".") + 1);
        String methodName = trace.getMethodName();
        if (methodName.startsWith("redirect$")) {
            methodName = methodName.substring(methodName.lastIndexOf("$") + 1);
        } else if (methodName.startsWith("lambda$")) {
            methodName = methodName.substring(methodName.indexOf("$") + 1, methodName.lastIndexOf("$"));
        } else if (methodName.equals("<init>")) methodName = "constructor";
        return String.format("[%s.%s]: %s", className, methodName, msg);
    }

    private static String formatThrowable(Throwable throwable) {
        String t = "";
        try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            pw.flush();
            t = sw.toString();
        } catch (IOException ignored) {
        }
        return t;
    }
}
