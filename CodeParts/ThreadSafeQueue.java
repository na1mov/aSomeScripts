package CodeParts;

import java.util.ArrayList;

public class ThreadSafeQueue {
    ArrayList<Runnable> jobs = new ArrayList<>();

    public synchronized void put(Runnable job) {
        jobs.add(job);
        this.notifyAll();
        // notifyAll тоже можно вызвать только внутри блока synchronized у объекта-мютекса(this)
        // В данном случе: просыпаются все нити, которые заснули на этом же объекте-мютексе и как только текущая нить
        // выйдет из блока synchronized, одна из проснувшихся нитей захватит мютекс и продолжит свою работу.
        // Когда она освободит мютекс, другая проснувшаяся нить захватит мютекс и т.д.
        // Notify обычно советуют использовать ради оптимизации.
        // Во всех остальных случаях рекомендуют использовать метод notifyAll
    }

    public synchronized Runnable getJob() throws InterruptedException {
        while (jobs.size() == 0) {
            // всегда в цикле, т.к. иногда нити просыпаются сами
            this.wait();
            // wait вызывается только внутри блока synchronized, у объекта-мютекса(this)
            // В данном случае: нить засыпает и временно освобождает мютекс (пока не проснется)
            // После этого другие нити могут входить в блок synchronized и занимать этот же мютекс.
        }
        return jobs.remove(0);
    }
}
