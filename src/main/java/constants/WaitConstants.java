package constants;

public final class WaitConstants {

    // Time constants in milliseconds
    public static final long LONG_IMPLICIT_WAIT = 15000L;
    public static final long SHORT_IMPLICIT_WAIT = 10000L;
    public static final long LONG_EXPLICIT_WAIT = 20000L;
    public static final long EXPLICIT_WAIT = 10000L;
    public static final long SHORT_EXPLICIT_WAIT = 7000L;

    public static final long SHORT_FLUENT_WAIT = 5000L;
    public static final long POLLING_EVERY = 100L;

    // Private constructor to prevent instantiation
    private WaitConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

}
