package comparer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RRScheduler {
    public static void simulate(List<Process> processes, int timeQuantum, int overhead) {
        int elapsedTime = 0;
        int elapsedTimeSum = 0;

        Queue<Process> processQueue = new LinkedList<>();

        for (Process p : processes) {
            processQueue.add(new Process(p.id, p.time, p.priority));
        }

        Process activeProcess;
        while (!processQueue.isEmpty()) {
            activeProcess = processQueue.poll();
            if (activeProcess.time > timeQuantum) {
                activeProcess.time -= timeQuantum;
                activeProcess.turnAroundTime += timeQuantum;
                activeProcess.timeSlicesNeeded++;
                processQueue.add(activeProcess);
                elapsedTime += timeQuantum;
                elapsedTime += overhead;
            } else if (activeProcess.time <= timeQuantum) {
                elapsedTime += activeProcess.time;
                activeProcess.turnAroundTime += activeProcess.time;
                activeProcess.timeSlicesNeeded++;
                activeProcess.time = 0;
                elapsedTimeSum += elapsedTime;
                System.out.printf("RR TA time for finished p%d = %d, needed: %dms, and: %d time slices.\n",
                        activeProcess.id,
                        elapsedTime,
                        activeProcess.turnAroundTime,
                        activeProcess.timeSlicesNeeded);
                elapsedTime += overhead;
            }
        }

        System.out.printf("RR Throughput, %d p, with q: %d, o: %d, is: %f p/ms, or %f p/us\n",
                processes.size(),
                timeQuantum,
                overhead,
                (float) processes.size() / (float) elapsedTime,
                ((float) processes.size() / (float) elapsedTime) * 1000);
        System.out.printf("Average RR TA, %d p, with q: %d, o: %d, is: %f\n\n",
                processes.size(),
                timeQuantum,
                overhead,
                (float) elapsedTimeSum / processes.size());
    }
}
