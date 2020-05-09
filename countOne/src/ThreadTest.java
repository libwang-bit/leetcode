import java.util.concurrent.FutureTask;

/**
 * ThreadTest
 *
 * @author likaiqiang@focusmedia.cn
 */
public class ThreadTest {
    public static void main(String args[]) throws Exception {
        Object lock = new Object();
        final boolean[] flag = {true};//交替执行标志位。true-线程1运行，false-线程2运行
        FutureTask task1 = new FutureTask(() -> {
            for (int i = 0; i < 50; i++) {
                synchronized (lock) {
                    while (true) {
                        if (flag[0]) {
                            System.out.print(i * 2 + 1 + ",");
                            flag[0] = false;
                            lock.notify();
                            if (i < 49) {//打印完最后一个，不需要再wait了。
                                lock.wait();
                            }
                            break;
                        }
                    }
                }
            }
            return null;
        });
        Thread t1 = new Thread(task1);

        FutureTask task2 = new FutureTask(() -> {
            for (int i = 0; i < 50; i++) {
                synchronized (lock) {
                    while (true) {
                        if (!flag[0]) {
                            System.out.print(i * 2 + 2 + ",");
                            flag[0] = true;
                            lock.notify();
                            if (i < 49) {//打印完最后一个，不需要再wait了。
                                lock.wait();
                            }
                            break;
                        } else {
                            lock.wait();
                        }
                    }
                }
            }
            return null;
        });
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.print("finished");
    }
}
