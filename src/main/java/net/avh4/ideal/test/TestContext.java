package net.avh4.ideal.test;

public class TestContext<T> {
    private String description;

    public TestContext(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
