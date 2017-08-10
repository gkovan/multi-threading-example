package hello;

import java.util.concurrent.Callable;
import org.slf4j.Logger;

public class MyCallable implements Callable<String> {

  private final String threadId;
  private final Logger log;

  public MyCallable(String threadId, Logger log) {
      this.threadId = threadId;
      this.log = log;
  }

  @Override
  public String call() throws Exception {
      log.info("GK:Executing thread id: " + threadId);
      return "Finished executing thread id: " + threadId;
  }

}
