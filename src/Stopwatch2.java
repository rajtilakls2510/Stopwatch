public class Stopwatch2 {
    private long offset, currentStart;
    private boolean isStopped;
    public Stopwatch2()
    {
        offset = 0L;
        currentStart = System.currentTimeMillis();
        isStopped = true;
    }
    public void start()
    {
        if(isStopped)
            currentStart = System.currentTimeMillis() - offset;
        isStopped = false;
    }
    public void stop()
    {
        if(!isStopped)
            offset = System.currentTimeMillis() - currentStart;
        isStopped = true;
    }
    public long getTime()
    {
        if(!isStopped)
            return System.currentTimeMillis() - currentStart;
        else
            return offset;
    }
    public boolean isStopped(){
        return isStopped;
    }
}
