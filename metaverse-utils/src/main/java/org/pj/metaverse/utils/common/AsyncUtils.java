package org.pj.metaverse.utils.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @ProjectName: dts
 * @Package: com.qiguliuxing.dts.service.system
 * @ClassName: AsyncService
 * @Author: yc
 * @Description: 异步Service
 * @Date: 2021/9/2 17:31
 */
@Slf4j
@Component
public class AsyncUtils {

    /**
     * 执行
     * @param runnable:
     * @return: void
     * @author: yc
     * @date: 2021/9/2 17:51
     */
    @Async
    public void exec(Runnable runnable) {
        runnable.run();
    }

    /**
     * 执行（返回值）
     * @param callable:
     * @return: java.util.concurrent.Future<org.apache.poi.ss.formula.functions.T>
     * @author: yc
     * @date: 2021/9/2 17:52
     */
    @Async
    public <T> Future<T> exec(Callable<T> callable) {
        try {
            return AsyncResult.forValue(callable.call());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return AsyncResult.forExecutionException(e);
        }
    }

}
