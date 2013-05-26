package net.avh4.ideal.test;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import net.avh4.ideal.test.junit.TestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;

import static org.fest.assertions.Assertions.assertThat;

public class TestRunnerTest {

    private TestRunner subjectWithOneTest;

    @Before
    public void setUp() throws Exception {
        subjectWithOneTest = new TestRunner(TestClassWithOneTest.class);
    }

    @Test
    public void shouldHaveDescription() throws Exception {
        assertThat(subjectWithOneTest.getDescription()).isNotNull();
    }

    @Test
    public void getDescription_shouldHaveTheTestClassName() throws Exception {
        assertThat(subjectWithOneTest.getDescription().getDisplayName()).isEqualTo("net.avh4.ideal.test.TestRunnerTest$TestClassWithOneTest");
    }

    @Test
    public void withOneAssertion_getDescription_shouldHaveANodeForTheContext() throws Exception {
        assertThat(subjectWithOneTest.getDescription().getChildren()).isNotEmpty();
    }

    @Test
    public void getDescription_shouldNameTheContextNode() throws Exception {
        assertThat(getContextDescription().getDisplayName()).isEqualTo("initial state");
    }

    @Test
    public void withOneAssertion_getDescription_shouldHaveANodeForTheAction() throws Exception {
        assertThat(getContextDescription().getChildren()).isNotEmpty();
    }

    @Test
    public void getDescription_shouldNameTheActionNode() throws Exception {
        assertThat(getActionDescription().getDisplayName()).isEqualTo("toString");
    }

    @Test
    public void withOneAssertion_getDescription_shouldHaveANodeForTheAssertion() throws Exception {
        assertThat(getActionDescription().getChildren()).isNotEmpty();
    }

    @Test
    public void getDescription_shouldNameTheAssertionNode() throws Exception {
        assertThat(getActionDescription().getChildren().get(0).getDisplayName()).isEqualTo("should show a default name(net.avh4.ideal.test.TestRunnerTest$TestClassWithOneTest)");
    }

    private Description getContextDescription() {
        return subjectWithOneTest.getDescription().getChildren().get(0);
    }

    private Description getActionDescription() {
        return getContextDescription().getChildren().get(0);
    }

    public static class TestClassWithOneTest implements TestCollection {
        TestContext<String> initialState = new TestContext<>("initial state");
        TestAction<String, String> toString = new TestAction<String, String>("toString");

        @Override
        public Iterable<? extends TestAssertion> getTests() {
            return ImmutableList.of(
                    new TestAssertion("should show a default name", initialState, toString, new Function<String, TestResult>() {
                        @Override
                        public TestResult apply(java.lang.String input) {
                            return TestResult.PASS;
                        }
                    })
            );
        }
    }
}
