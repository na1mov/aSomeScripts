package codeNotes;

/*
MyUncaughtExceptionHandler перехватывает исключения, ждёт половину секунды, а затем выводит на экран secretKey,
имя трэда и сообщение возникшего исключения.
*/

public class UncaughtExceptionHandlerEx {

    public static void main(String[] args) {
        MyThread myThread = new UncaughtExceptionHandlerEx().new MyThread("super secret key");
        myThread.start();
    }

    public class MyThread extends Thread {
        private String secretKey;

        public MyThread(String secretKey) {
            this.secretKey = secretKey;
            setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            setDaemon(false);
        }

        @Override
        public void run() {
            throw new NullPointerException("it's an example");
        }

        private class MyUncaughtExceptionHandler implements UncaughtExceptionHandler{
            public MyUncaughtExceptionHandler() {
            }

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                try {
                    Thread.sleep(500);

                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                System.out.println(String.format("%s, %s, %s", secretKey, t.getName(), e.getMessage()));
            }
        }
    }
}
