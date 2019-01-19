package org.saxing.halfsynchalfasync;

import java.util.concurrent.*;

public class AsynchronousService {

    /*
     * This represents the queuing layer as well as synchronous layer of the pattern. The thread pool
     * contains worker threads which execute the tasks in blocking/synchronous manner. Long running
     * tasks should be performed in the background which does not affect the performance of main
     * thread.
     */
    private ExecutorService service;

    /**
     * Creates an asynchronous service using {@code workQueue} as communication channel between
     * asynchronous layer and synchronous layer. Different types of queues such as Priority queue, can
     * be used to control the pattern of communication between the layers.
     */
    public AsynchronousService(BlockingQueue<Runnable> workQueue) {
        service = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, workQueue);
    }

    /**
     * A non-blocking method which performs the task provided in background and returns immediately.
     * <p>
     * On successful completion of task the result is posted back using callback method
     * {@link AsyncTask#onPostCall(Object)}, if task execution is unable to complete normally due to
     * some exception then the reason for error is posted back using callback method
     * {@link AsyncTask#onError(Throwable)}.
     * <p>
     * NOTE: The results are posted back in the context of background thread in this implementation.
     */
    public <T> void execute(final AsyncTask<T> task){
        try {
            task.onPreCall();
        } catch (Exception e){
            task.onError(e);
            return;
        }

        service.submit(new FutureTask<T>(task){
            @Override
            protected void done() {
                super.done();
                try {
                    task.onPostCall(get());
                } catch (InterruptedException e) {
                    // e.printStackTrace();
                    // should not occur
                } catch (ExecutionException e) {
                    task.onError(e.getCause());
                }
            }
        });
    }


}
