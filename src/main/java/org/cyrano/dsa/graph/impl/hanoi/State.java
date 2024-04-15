package org.cyrano.dsa.graph.impl.hanoi;

public class State {

    private static final int M = 0xFF;

    private int i;

    void set(int p, int v) {
        //@f:0
        i &= ~(M << 8 * p);
        i |=  (v << 8 * p);
        //@f:1
    }

    int get(int p) {
        int r = i & (M << 8 * p);

        r >>>= 8 * p;

        return r;
    }

    public static void get(State state) {
        System.out.println("0: " + state.get(0));
        System.out.println("1: " + state.get(1));
        System.out.println("2: " + state.get(2));
        System.out.println("3: " + state.get(3));
    }

    public static void set(State state, int _0, int _1, int _2, int _3) {
        state.set(0, _0);
        state.set(1, _1);
        state.set(2, _2);
        state.set(3, _3);
    }

    public static void main(String[] args) {
        State state = new State();
        get(state);
        set(state, 0, 1, 2, 3);
        get(state);
        set(state, 4, 3, 2, 1);
        get(state);
    }
}
