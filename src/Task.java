public abstract class Task {
    private final long id;

    public Task(final long id) {
        this.id = id;
    }

    public abstract void perform();

    @Override
    public final String toString() {
        return this.getClass().getName() + "[id = " + this.id + "]";
    }
}
