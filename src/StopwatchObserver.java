public class StopwatchObserver implements Observer{


    @Override
    public void update(long time) {
        System.out.print("\rTime: "+time);
    }
}
