package ge.credo.testautomation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryMaker implements IRetryAnalyzer {

    private static final Logger log = LoggerFactory.getLogger(RetryMaker.class);
    private static final int MAX_RETRY_COUNT = 2;
    private final ThreadLocal<Integer> retryCount = ThreadLocal.withInitial(() -> 0);

    @Override
    public boolean retry(ITestResult result) {
        int count = retryCount.get();
        if (count < MAX_RETRY_COUNT) {
            retryCount.set(count + 1);
            log.warn("Retrying test [{}] — attempt {}/{}", result.getName(), count + 1, MAX_RETRY_COUNT);
            return true;
        }
        log.error("Test [{}] failed after {} retries — giving up", result.getName(), MAX_RETRY_COUNT);
        retryCount.remove();
        return false;
    }
}