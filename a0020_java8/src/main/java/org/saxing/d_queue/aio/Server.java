package org.saxing.d_queue.aio;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.*;

/**
 * Server
 *
 * @author saxing 2020/5/6 23:34
 */
public class Server {

    //线程池
    private ExecutorService executorService;
    //线程组
    private AsynchronousChannelGroup threadGroup;
    //服务器通道
    public AsynchronousServerSocketChannel assc;

    public Server(int port) {

        try {
            ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                    .setNameFormat("server-pool-%d").build();

//            //创建一个缓存池
//            executorService = new ThreadPoolExecutor(2, 2,
//                    0L, TimeUnit.MILLISECONDS,
//                    new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

//            executorService = Executors.newCachedThreadPool();
            executorService = Executors.newFixedThreadPool(3);
//            executorService = Executors.newSingleThreadExecutor();

            //创建线程组
            threadGroup = AsynchronousChannelGroup.withThreadPool(executorService);
//            threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
            //创建服务器通道
            assc = AsynchronousServerSocketChannel.open(threadGroup);
            //进行绑定
            assc.bind(new InetSocketAddress(port));

            System.out.println("server start , port : " + port);
            //进行阻塞
            assc.accept(this, new ServerCompletionHandler());

            //一直阻塞 不让服务器停止
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8765);
    }
}
