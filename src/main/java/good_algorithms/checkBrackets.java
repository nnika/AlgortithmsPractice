package good_algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

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


class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    private char type;
    int position;
}

class checkBrackets {
    private void balance(String text){
        Deque<Bracket> stack = new LinkedList<>();
        int pos = 0;
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);
            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
                Bracket b = new Bracket(next, position + 1);
                stack.push(b);
            }

            if (next == ')' || next == ']' || next == '}') {
                if (stack.isEmpty()) {
                    pos = position + 1;
                    break;
                }
                Bracket bracket = stack.pop();
                if (!bracket.Match(next)) {
                    pos = position + 1;
                    break;
                }
            }
        }
        if(pos == 0 && stack.isEmpty()) {
            System.out.println("Success");
        } else {
            if(pos == 0){
                while (stack.size() > 1){
                    stack.pop();
                }
                pos = stack.peek().position;
            }
            System.out.println(pos);
        }

    }

    public static void main(String[] args) throws IOException {
        InputStreamReader inputStream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStream);
        String text = reader.readLine();
        checkBrackets bb = new checkBrackets();
        bb.balance(text);
    }
}
