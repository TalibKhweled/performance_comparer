package comparer;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Process> processes = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Enter triples: process id, time in ms, and priority:\n" +
                "For example:\n" +
                "1 12 0\n" +
                "3 9 1\n" +
                "2 99 9\n" +
                "Process 1 needs 12 ms and has priority 0, very high,\n" +
                "process 3 needs 9 ms and has priority 1.\n" +
                "and so on...");

        parseInput();
        processFCFS();
        processHPF();
        processRR();
    }

    private static void parseInput() {
        Scanner scanner = new Scanner(System.in);

        String input;
        while (!(input = scanner.nextLine()).equals("")) {
            int[] inputs = new int[3];
            int i = 0;
            for (String partial : input.split(" ")) {
                if (i >= 3)
                    throw new RuntimeException("Malformed input");
                inputs[i++] = Integer.parseInt(partial);
            }
            processes.add(new Process(inputs[0], inputs[1], inputs[2]));
        }
    }

    private static void processFCFS() {
        System.out.println("Process list in FCFS order as entered:");
        for (Process p : processes)
            System.out.println(p);
        System.out.println("End of list.\n");

        Statistics FCFSStatistics = FCFSScheduler.simulate(processes);

        for (Process p : processes)
            System.out.printf("fcfs wait of p%d = %d\n", p.id, p.waitTime);
        System.out.printf("Average wait for the %d procs = %.1f\n", processes.size(), FCFSStatistics.averageWait);

        for (Process p : processes)
            System.out.printf("fcfs turn-around time for p%d = %d\n", p.id, p.turnAroundTime);
        System.out.printf("average turn-around time for %d procs = %.1f\n", processes.size(), FCFSStatistics.averageTurnaround);

        System.out.printf("fcfs throughput for %d procs = %.5f procs/ms\n", processes.size(), FCFSStatistics.throughput);
        System.out.println("<><> end FCFS <><>\n");
    }

    private static void processHPF() {
        ArrayList<Process> hpfProcesses = new ArrayList<>();
        System.out.println("Process list in HPF order:");
        for (Process p : processes)
            hpfProcesses.add(new Process(p.id, p.time, p.priority));

        Statistics HPFStatistics = HPFScheduler.simulate(hpfProcesses);
        for (Process p : hpfProcesses)
            System.out.println(p);
        System.out.println("End of list.\n");

        for (Process p : hpfProcesses)
            System.out.printf("hpf wait of p%d = %d\n", p.id, p.waitTime);
        System.out.printf("Average wait for the %d procs = %.1f\n", hpfProcesses.size(), HPFStatistics.averageWait);

        for (Process p : hpfProcesses)
            System.out.printf("hpf turn-around time for p%d = %d\n", p.id, p.turnAroundTime);
        System.out.printf("average turn-around time for %d procs = %.1f\n", hpfProcesses.size(), HPFStatistics.averageTurnaround);

        System.out.printf("hpf throughput for %d procs = %.5f procs/ms\n", hpfProcesses.size(), HPFStatistics.throughput);
        System.out.println("<><> end HPF<><>\n");
    }

    private static void processRR() {
        System.out.println("Process the list for RR in the order entered:");
        for (Process p : processes)
            System.out.println(p);
        System.out.println("End of list.\n");
        for (int q = 1; q <= 5; ++q) {
            for (int o = 0; o <= q; ++o) {
                System.out.printf("preemptive RR schedule, quantum = %d overhead = %d\n", q, o);
                RRScheduler.simulate(processes, q, o);
            }
        }
    }
}
