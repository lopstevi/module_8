package module_8;

public class ConcurrencyCounters {

    public static void countUp() {
        for (int i = 0; i <= 20; i++) {
            System.out.println("Up: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public static void countDown() {
        for (int i = 20; i >= 0; i--) {
            System.out.println("Down: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ConcurrencyCounters.countUp();
            }
        });

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                ConcurrencyCounters.countDown();
            }
        });

        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
