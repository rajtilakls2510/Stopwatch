import java.util.ArrayList;
import java.util.Iterator;

public class Stopwatch implements Runnable, Observable
{

    /** Stopwatch:
     This is the core Stopwatch class which runs a stopwatch on a different thread.
     The accessor methods are only start() and stop() methods.

     This stopwatch can only be resumed (by start()) and paused (by stop()).

     This stopwatch is made with the Observer Design Pattern. Multiple observers can register
     to this stopwatch (they should implement Observer) and their displays will be updated
     every 10ms.

     */


    private long offset, currentStart;
    private boolean isStopped;
    private Thread th;

    // Storing Observers: Observer Pattern implemented
    private ArrayList<Observer> observers;

    public Stopwatch()
    {
        offset = 0L;
        currentStart = System.currentTimeMillis();
        isStopped = true;
        observers = new ArrayList<>();
    }

    // <---------------- Stopwatch methods ---------------->

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

    // <---------------- Observer methods ---------------->
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        Iterator<Observer> iter = observers.iterator();
        while(iter.hasNext())
        {
            Observer o = iter.next();
            o.update(getTime());
        }
    }

    // <---------------- Thread methods ---------------->

    @Override
    public void run() {
        while(!isStopped)
        {
            notifyObservers();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // <---------------- Accessor methods ---------------->

    public long getTime()
    {
        if(!isStopped)
            return System.currentTimeMillis() - currentStart;
        else
            return offset;
    }

    public boolean isStopped()
    {
        return isStopped;
    }
}