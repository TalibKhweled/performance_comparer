package comparer;

public class Process implements Comparable<Process> {
    public int id;
    public int time;
    public int priority;
    public int waitTime = 0;
    public int turnAroundTime = 0;
    public int timeSlicesNeeded = 0;

    public Process(int id, int time, int priority) {
        this.id = id;
        this.time = time;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return String.format("%d %d %d", id, time, priority);
    }

    @Override
    public int compareTo(Process p) {
        if (this.priority < (p.priority))
            return -1;
        else if (this.priority == (p.priority))
            return 0;
        else
            return 1;
    }
}
