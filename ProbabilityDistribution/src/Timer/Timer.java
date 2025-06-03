package Timer;

public class Timer extends Thread {

    private final int time;
    private final Runnable callback;

    public Timer(int time, Runnable callback) {
        this.time = time;
        this.callback = callback;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(time);
            callback.run();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
