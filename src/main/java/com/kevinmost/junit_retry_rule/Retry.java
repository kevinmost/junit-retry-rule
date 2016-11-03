package com.kevinmost.junit_retry_rule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Retries a unit-test according to the attributes set here
 *
 * The class containing the test(s) decorated with this annotation must have a public field of type {@link RetryRule}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Retry {
  /**
   * @return the number of times to try this method before the failure is propagated through
   */
  int times() default 3;

  /**
   * @return how long to sleep between invocations of the unit tests, in milliseconds
   */
  long timeout() default 0;
}
