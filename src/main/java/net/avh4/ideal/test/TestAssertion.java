package net.avh4.ideal.test;

import com.google.common.base.Function;

public class TestAssertion {
    private final String description;
    private final TestContext<?> context;
    private final TestAction<?, ?> action;
    private final Function<?, TestResult> assertion;

    public <A, B> TestAssertion(String description, TestContext<A> context, TestAction<A, B> action, Function<B, TestResult> assertion) {
        this.description = description;
        this.context = context;
        this.action = action;
        this.assertion = assertion;
    }

    public TestContext getContext() {
        return context;
    }

    public TestAction getAction() {
        return action;
    }

    public String getDescription() {
        return description;
    }
}
