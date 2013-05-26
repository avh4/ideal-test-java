package net.avh4.ideal.test;

public class TestAction<A, B> {
    private String description;

    public TestAction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
