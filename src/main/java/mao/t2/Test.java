package mao.t2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Project name(项目名称)：java并发编程_异步模式之工作线程
 * Package(包名): mao.t2
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/9
 * Time(创建时间)： 13:54
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{
    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args)
    {
        ExecutorService threadPool1 = Executors.newFixedThreadPool(1);
        ExecutorService threadPool2 = Executors.newFixedThreadPool(1);

        extracted(threadPool1, threadPool2, "西红柿炒番茄");
        extracted(threadPool1, threadPool2, "土豆炒马铃薯");
    }


    /**
     * 提取
     *
     * @param threadPool1 线程pool1
     * @param threadPool2 线程pool2
     * @param order       订单
     */
    private static void extracted(ExecutorService threadPool1, ExecutorService threadPool2, String order)
    {
        threadPool1.execute(new Runnable()
        {
            @Override
            public void run()
            {
                log.debug("点餐：" + order);
                Future<String> future = threadPool2.submit(new Callable<String>()
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
