package net.avh4.ideal.test;

public interface TestCollection {
    Iterable<? extends TestAssertion> getTests();
}
