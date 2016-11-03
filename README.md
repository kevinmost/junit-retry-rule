@Retry for JUnit 4
==================
[![](https://jitpack.io/v/kevinmost/junit-retry-rule.svg)](https://jitpack.io/#kevinmost/junit-retry-rule)
[![Build Status](https://travis-ci.org/kevinmost/junit-retry-rule.svg?branch=master)](https://travis-ci.org/kevinmost/junit-retry-rule)

A simple `@Rule` that adds retrying logic to any JUnit 4 tests.


Installation
------------

Install via JitPack. Instructions available [here](https://jitpack.io/#kevinmost/junit-retry-rule/)

Usage
-----

In your test's class, add the `RetryRule` as a test rule, by creating
an instance of it as a public field, annotated with `@Rule`.

```java
@Rule public final RetryRule retry = new RetryRule();
```

Then, for any test that needs to implement retrying logic, annotate
the method with `@Retry`. Optional parameters on this annotation let
you change defaults such as the number of retries, the timeout length,
etc.

```java
@Test
@Retry(times = 5) // runs test up to 5 times, instead of the default 3 times
public void myFlakyTest throws Exception {
    obj.doUnreliableThing();
}
```

Any test that still fails after its maximum number of tries will throw
a `RetryException`, which also contains an array of all of the exceptions
that were thrown by each attempt to run this test-case.
