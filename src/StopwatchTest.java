

public class StopwatchTest {
    public static void main(String[] args) {
        Stopwatch sw = new Stopwatch();
        StopwatchObserver ob = new StopwatchObserver();
        sw.registerObserver(ob);

        System.out.println("\nStarting Stopwatch: ");
        sw.start();
        sleep(2000);
        sw.stop();
        System.out.println("\nStopwatch Stopped. ");

        sleep(2000);

        System.out.println("\nStarting Stopwatch: ");
        sw.start();
        sleep(2000);
        sw.stop();
        System.out.println("\nStopwatch Stopped. ");

    }

    public static void sleep(int time)
    {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
