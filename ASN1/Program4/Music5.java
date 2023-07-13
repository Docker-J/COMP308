package Program4;

interface Instrument {
    int VALUE = 5;

    void adjust();
}

interface Playable {
    void play(Note n);
}

abstract class Print {
    abstract void print(String s);
}

class Wind extends Print implements Instrument, Playable {
    public void play(Note n) {
        print(this + ".play() " + n);
    }

    public String toString() {
        return "Wind";
    }

    public void adjust() {
        print(this + ".adjust()");
    }

    @Override
    public void print(String s) {
        System.out.println(s);
    }
}

class Percussion extends Print implements Instrument, Playable {
    public void play(Note n) {
        print(this + ".play() " + n);
    }

    public String toString() {
        return "Percussion";
    }

    public void adjust() {
        print(this + ".adjust()");
    }

    @Override
    public void print(String s) {
        System.out.println(s);
    }
}

class Stringed extends Print implements Instrument, Playable {
    public void play(Note n) {
        print(this + ".play() " + n);
    }

    public String toString() {
        return "Stringed";
    }

    public void adjust() {
        print(this + ".adjust()");
    }

    @Override
    public void print(String s) {
        System.out.println(s);
    }
}

class Brass extends Wind {
    public String toString() {
        return "Brass";
    }
}

class Woodwind extends Wind {
    public String toString() {
        return "Woodwind";
    }
}

public class Music5 {
    static void tune(Playable p) {
        p.play(Note.MIDDLE_C);
    }

    static void tuneAll(Playable[] e) {
        for(Playable p : e) {
            tune(p);
        }
    }

    public static void main(String[] args) {
        Playable[] orchestra = {
            new Wind(), new Percussion(), new Stringed(), new Brass(), new Woodwind()
        };
        tuneAll(orchestra);
    }
}
