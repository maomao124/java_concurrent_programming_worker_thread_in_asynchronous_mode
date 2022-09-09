package mao.t1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Project name(项目名称)：java并发编程_异步模式之工作线程
 * Package(包名): mao.t1
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/9
 * Time(创建时间)： 13:38
 * Version(版本): 1.0
 * Description(描述)： 饥饿现象
 */

public class Test
{
    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args)
    {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        extracted(threadPool, "西红柿炒番茄");
        extracted(threadPool, "土豆炒马铃薯");
    }

    /**
     * 提取
     *
     * @param threadPool 线程池
     * @param order      订单，菜名
     */
    private static void extracted(ExecutorService threadPool, String order)
    {
        threadPool.execute(new Runnable()
        {
            @Override
            public void run()
            {
                log.debug("点餐：" + order);
                Future<String> future = threadPool.submit(new Callable<String>()
                {
                    @Override
                    public String call() throws Exception
                    {
                        log.debug("做菜：" + order);
                        return order;
                    }
                });
                try
                {
                    log.debug("上菜：" + future.get());
                }
                catch (InterruptedException | ExecutionException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
