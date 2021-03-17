

public class StopwatchTest {
    public static void main(String[] args) {
        Stopwatch sw = new Stopwatch();

        Thread observerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    System.out.print("\rTime: "+ sw.getTime());
                    sleep(10);
                }

            }
        });
        observerThread.start();


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
