
public class Stopwatch implements Runnable
{
    private long offset, currentStart;
    private boolean isStopped;
    private Thread th;

    public Stopwatch()
    {
        offset = 0L;
        currentStart = System.currentTimeMillis();
        isStopped = true;
    }

    public void start()
    {
        if(isStopped)
        {
            th = new Thread(this);
            th.start();
            currentStart = System.currentTimeMillis() - offset;
        }

        isStopped = false;
    }

    public void stop()
    {
        if(!isStopped)
        {
            th = null;
            offset = System.currentTimeMillis() - currentStart;
        }
        isStopped = true;
    }

    public long getTime()
    {
        if(!isStopped)
            return System.currentTimeMillis() - currentStart;
        else
            return offset;
    }

    @Override
    public void run() {
        while(!isStopped)
        {
            System.out.print("\rTime: "+ getTime());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}