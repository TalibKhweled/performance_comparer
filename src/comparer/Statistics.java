package comparer;

public class Statistics {
    public final float averageWait;
    public final float averageTurnaround;
    public final float throughput;

    public Statistics(float wait, float turnaround, float throughput) {
        averageWait = wait;
        averageTurnaround = turnaround;
        this.throughput = throughput;
    }
}
