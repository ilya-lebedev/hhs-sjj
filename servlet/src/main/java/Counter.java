public class Counter {

    private Counter() {}

    private static long COUNTER = 0;

    public static long get() {
        return COUNTER;
    }

    public static void increment() {
        COUNTER++;
    }

    public static void decrement(long value) {
        COUNTER -= value;
    }

    public static void clear() {
        COUNTER = 0;
    }

}
