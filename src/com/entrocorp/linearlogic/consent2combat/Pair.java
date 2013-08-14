package com.entrocorp.linearlogic.consent2combat;

public class Pair<X, Y> {

    private X x;
    private Y y;

    public Pair(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Pair))
            return false;
        Pair<?, ?> compare = (Pair<?, ?>) other;
        return x == compare.x && y == compare.y;
    }

    public X getX() {
        return x;
    }

    public void setX(X x) {
        this.x = x;
    }

    public Y getY() {
        return y;
    }

    public void setY(Y y) {
        this.y = y;
    }
}
