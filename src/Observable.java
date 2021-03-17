public interface Observable {
    public void registerObserver(Observer o);
    public void notifyObservers();
}
