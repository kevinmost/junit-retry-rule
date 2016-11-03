package com.kevinmost.junit_retry_rule;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A flaky network request that will only succeed after it's been invoked 3 times
 */
public final class FlakyNetworkRequest {

  private AtomicInteger currentNumTries = new AtomicInteger(0);

  public synchronized void makeRequest() {
    final int currentTry = currentNumTries.getAndIncrement();
    if (currentTry == 3) {
      return;
    }
    throw new RuntimeException(
        "This network request succeeds after 3 attempts. You're on attempt index " + currentTry + " right now"
    );
  }
}
