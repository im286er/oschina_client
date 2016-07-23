package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/6/12.
 */
public class ThreadPool {
    private ExecutorService mExecutorService;

    public ThreadPool() {
        mExecutorService = Executors.newFixedThreadPool(5);
    }
    public void execute(Runnable runnable) {
        mExecutorService.execute(runnable);
    }
}
