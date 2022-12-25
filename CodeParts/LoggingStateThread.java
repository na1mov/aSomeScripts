package CodeParts;

/*
Мониторинг состояния нити
*/

public class LoggingStateThread extends Thread {
    Thread thread;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
        thread.setDaemon(true);
    }

    @Override
    public void run() {
        State state1 = thread.getState();
        System.out.println(state1);

        State state2;
        while (state1 != State.TERMINATED) {
            state2 = thread.getState();
            if (state1 != state2) {
                System.out.println(state2);
                state1 = state2;
            }
        }
    }
}

class AnonTest {
    public static void main(String[] args) throws InterruptedException {
        Thread target = new Thread();
        LoggingStateThread loggingStateThread = new LoggingStateThread(target);

        loggingStateThread.start(); //NEW
        Thread.sleep(100);
        target.start();  //RUNNABLE
        Thread.sleep(100);
        //TERMINATED
    }
}
