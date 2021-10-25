package comparer;

import java.util.ArrayList;
import java.util.Collections;

public class HPFScheduler {
    public static Statistics simulate(ArrayList<Process> processes) {
        int cumulativeWait = 0;
        int cumulativeTurnaround = 0;
        float averageWait = 0;
        float averageTurnaround = 0;

        Collections.sort(processes);

        for (Process process : processes) {
            process.waitTime = cumulativeWait;
            cumulativeWait += process.time;
            cumulativeTurnaround += process.time;
            process.turnAroundTime = cumulativeTurnaround;

            averageWait += process.waitTime;
            averageTurnaround += process.turnAroundTime;
        }
        averageWait = averageWait / processes.size();
        averageTurnaround = averageTurnaround / processes.size();
        float throughput = (float) processes.size() / (float) cumulativeWait;

        return new Statistics(averageWait, averageTurnaround, throughput);
    }
}
