package com.kevinmost.junit_retry_rule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RetryTest {

  @Rule public final RetryRule retryRule = new RetryRule();
  @Rule public final ExpectedException thrown = ExpectedException.none();

  private FlakyNetworkRequest networkRequest;

  @Before
  public void setUpFlakyNetworkRequest() {
    if (retryRule.currentAttempt() == 0) { // only setup this object on our first try
      networkRequest = new FlakyNetworkRequest();
    }
  }

  @SuppressWarnings("DefaultAnnotationParam") @Retry(times = 3)
  @Test
  public void assert3TriesNotEnough() {
    // can't use the @Test annotation's `expected` param because RetryException is only thrown after the RetryRule
    // gives up; `expected` is for each individual run of the test-case, not in aggregate
    thrown.expect(RetryException.class);
    networkRequest.makeRequest();
  }

  @Retry(times = 4)
  @Test public void assertWorksAfter4Tries() {
    networkRequest.makeRequest();
  }
}
