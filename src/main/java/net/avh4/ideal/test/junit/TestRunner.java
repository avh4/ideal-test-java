package net.avh4.ideal.test.junit;

import net.avh4.ideal.test.TestAssertion;
import net.avh4.ideal.test.TestCollection;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import java.lang.reflect.InvocationTargetException;

public class TestRunner extends Runner {

    private final TestCollection testInstance;
    private final Class<? extends TestCollection> testClass;

    public TestRunner(Class<? extends TestCollection> testClass) {
        this.testClass = testClass;
        try {
            testInstance = testClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Test class does not have a public default constructor: " + testClass.getCanonicalName(), e);
        }
    }

    @Override
    public Description getDescription() {
        final Description classDescription = Description.createSuiteDescription(testClass);
        for (TestAssertion assertion : testInstance.getTests()) {
            final Description contextDescription = Description.createSuiteDescription(assertion.getContext().getDescription());
            final Description actionDescription = Description.createSuiteDescription(assertion.getAction().getDescription());
            final Description assertionDescription = Description.createTestDescription(testClass, assertion.getDescription());
            actionDescription.addChild(assertionDescription);
            contextDescription.addChild(actionDescription);
            classDescription.addChild(contextDescription);
        }
        return classDescription;
    }

    @Override
    public void run(RunNotifier notifier) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
