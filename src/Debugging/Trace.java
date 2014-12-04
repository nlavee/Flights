package Debugging;

/**
 * Trace includes static methods to make tracing the execution of Java program easier. 
 * 
 * COPYRIGHT 2014 Tom O'Connell All Rights Reserved 
 * 
 */
import java.io.PrintStream;
import java.util.ArrayList;

public class Trace {
    public static boolean debugOn = false;
    public static boolean traceAll = true;
    private static PrintStream out = System.out;

    public static ArrayList<String> traceMethods = new ArrayList<String>();

    /**
     * Set the output stream. (The default output stream is standard out.)
     * @param out the stream to which the output stream should be set.
     */
    public static void setOut(PrintStream out) {
        Trace.out = out;
    }

    /**
     * Add the specified method name to the list of method names that will be traced.
     * @param methodName
     */
    public static void addMethod(String methodName) {
        traceMethods.add(methodName);
    } // end addMethod()

    /**
     * Indicate whether we will trace all methods
     * @param value
     */
    public static void setTraceAll(boolean value) {
        traceAll = value;
    }

    /**
     * Turn tracing on or off. 
     * @param value
     */
    public static void setDebug(boolean value) {
        debugOn = value;
    } // end setDegug()

    /**
     * Flush the trace output stream.
     */
    public static void flush() {
    		out.flush();
    }
    /**
     * Print x to the trace output stream with a newline at the end. 
     * @param x
     */
    public static void println(Object x) {

        if (debugOn) {
            // StackTraceElement[] trace =
            // Thread.currentThread().getStackTrace();
            // StackTraceElement t = trace[2];
            // String methodName = parseTrace(t);
            // int index = methodName.indexOf('(');
            // String nameOnly = methodName.substring(0, index);
            // //System.out.println("nameOnly = " + nameOnly);
            // //System.out.println("traceAll = " + traceAll);
            // if (traceAll || traceMethods.contains(nameOnly)) {
            // System.out.println(methodName + ": " + x);
            // System.out.flush();
            // } // end if

            String outputString = getOutputString(x);
            if (outputString != null) {
                out.println(outputString);
                out.flush();
            } // end if
        } // endif
    } // end println()
    
    /**
     * Same as println but without the newline.
     * @param x
     */
    public static void print(Object x) {
        if (debugOn) {
            String outputString = getOutputString(x);
            if (outputString != null) {
                out.print(outputString);
                out.flush();
            } // end if
        } // end if
    } // end print()

    /**
     * Parses the call stack entry and returns the name of the method in the given stack entry 
     * @param t
     * @return
     */
    private static String parseTrace(StackTraceElement t) {
        String fullName = t.toString();
        // System.out.println("parseTrace(): fullName =  " + fullName);

        String result = null;
        boolean found = false;
        int index = fullName.indexOf('.');
        String sub = fullName;
        while (index >= 0 && !found) {
            sub = sub.substring(index + 1);
            if (sub.indexOf('(') < sub.indexOf('.')) {
                found = true;
                result = sub;
            }
            index = sub.indexOf('.');
        }

        return result;

    } // end parseTrace()

    /**
     * Finds the current method on the call stack, checks to see if this is 
     * a method that is to be traced, and returns the appropriate string.
     * 
     * Assumes there were 3 calls after the call that is to be traced.
     * (I think this is different for Android -- need to check.)
     * 
     * @param x object to be displayed after the method name in the trace entry
     * @return the string that is to be displayed 
     */
    private static String getOutputString(Object x) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        StackTraceElement t = trace[3];
        String result = null;
        String fullName = t.toString();
        String methodName = parseTrace(t);
        int index = methodName.indexOf('(');
        String nameOnly = methodName.substring(0, index);
        // System.out.println("nameOnly = " + nameOnly);
        // System.out.println("traceAll = " + traceAll);
        if (traceAll || traceMethods.contains(nameOnly)) {
            // result = methodName + ": " + x;
            result = fullName + ": " + x;
        } // end if
        return result;
    } // end getOutputString()


} // end class Trace 