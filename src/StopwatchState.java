public interface StopwatchState {

    /** This App has the State Pattern implemented.

     Since the core Stopwatch class can only be resumed and paused,
     it becomes important that the observables using it provide the cover of
     start, pause, resume and stop. This is why we require the states.

     When user pressed each button the states, the application transitions
     from one state to other.

     This interface has the common methods which every state should have with
     a default implementation of the sequence of execution.
     */


    // Providing default execution of methods: Template Pattern
    default void execute()
    {
        handleStopwatch();
        handleUI();
        changeState();
    }

    public void handleStopwatch();
    public void handleUI();
    public void changeState();
}
