package good_algorithms;

/**
 * Advanced Problem: Network packet processing simulation
 * We strongly recommend you start solving advanced problems only when you are done with the basic problems (for some
 * advanced problems, algorithms are not covered in the video lectures and require additional ideas to be solved;
 * for some other advanced problems, algorithms are covered in the lectures, but implementing them is a more challenging
 * task than for other problems).
 *
 *
 * Problem Introduction
 * In this problem you will implement a program to simulate the processing of network packets.
 * Problem Description
 * Task. You are given a series of incoming network packets, and your task is to simulate their processing. Packets
 * arrive in some order. For each packet number 𝑖, you know the time when it arrived 𝐴𝑖 and the time it takes the
 * processor to process it 𝑃𝑖 (both in milliseconds). There is only one processor, and it processes the incoming
 * packets in the order of their arrival. If the processor started to process some packet, it doesn't interrupt or stop
 * until it finishes the processing of this packet, and the processing of packet 𝑖 takes exactly 𝑃𝑖 milliseconds.
 * The computer processing the packets has a network buffer of fixed size 𝑆. When packets ar- rive, they are stored in
 * the buffer before being processed. However, if the buffer is full when a packet arrives (there are 𝑆 packets which
 * have arrived before this packet, and the computer hasn’t finished processing any of them), it is dropped and won’t
 * be processed at all. If several packets arrive at the same time, they are first all stored in the buffer (some of
 * them may be dropped because of that — those which are described later in the input). The computer processes the
 * packets in the order of their arrival, and it starts processing the next available packet from the buffer as soon as
 * it finishes processing the previous one. If at some point the computer is not busy, and there are no packets in the
 * buffer, the computer just waits for the next packet to arrive. Note that a packet leaves the buffer and frees the
 * space in the buffer as soon as the computer finishes processing it.
 *
 */


import java.util.ArrayList;
import java.util.Scanner;

class Request {
    Request(int arrivalTime, int processTime) {
        this.arrivalTime = arrivalTime;
        this.processTime = processTime;
    }

    int arrivalTime;
    int processTime;
}

class Response {
    Response(boolean dropped, int startTime) {
        this.dropped = dropped;
        this.startTime = startTime;
    }

    boolean dropped;
    int startTime;
}

class Buffer {
    Buffer(int size) {
        this.size = size;
        this.finishTime = new ArrayList<>();
    }

    Response Process(Request request) {
        while (!finishTime.isEmpty() && finishTime.get(0) <= request.arrivalTime) {
            finishTime.remove(0);
        }

        if (finishTime.size() == size) {
            return new Response(true, -1);
        }

        int currentTime = finishTime.isEmpty() ? request.arrivalTime : finishTime.get(finishTime.size() - 1);
        finishTime.add(currentTime + request.processTime);
        return new Response(false, currentTime);
    }

    private int size;
    private ArrayList<Integer> finishTime;
}

class processPackages {
    private static ArrayList<Request> ReadQueries(Scanner scanner) {
        int requests_count = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<>();
        for (int i = 0; i < requests_count; ++i) {
            int arrival_time = scanner.nextInt();
            int process_time = scanner.nextInt();
            requests.add(new Request(arrival_time, process_time));
        }
        return requests;
    }

    private static ArrayList<Response> ProcessRequests(ArrayList<Request> requests, Buffer buffer) {
        ArrayList<Response> responses = new ArrayList<>();
        for (int i = 0; i < requests.size(); ++i) {
            responses.add(buffer.Process(requests.get(i)));
        }
        return responses;
    }

    private static void PrintResponses(ArrayList<Response> responses) {
        for (Response response : responses) {
            if (response.dropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.startTime);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int buffer_max_size = scanner.nextInt();
        Buffer buffer = new Buffer(buffer_max_size);

        ArrayList<Request> requests = ReadQueries(scanner);
        ArrayList<Response> responses = ProcessRequests(requests, buffer);
        PrintResponses(responses);
    }
}
