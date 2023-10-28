import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

public final class LeafTask extends Task {
    private final long secondDuration;
    private final CyclicBarrier cyclicBarrier;

    public LeafTask(final long id, final long secondDuration, final CyclicBarrier cyclicBarrier) {
        super(id);
        this.secondDuration = secondDuration;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void perform() {
        try {
            out.printf("%s is starting\n", this);
            SECONDS.sleep(this.secondDuration);
            out.printf("%s has finished\n", this);
            this.cyclicBarrier.await();
        } catch (final InterruptedException exception) {
            currentThread().interrupt();
        } catch (final BrokenBarrierException cause) {
            throw new RuntimeException(cause);
        }
    }
}
