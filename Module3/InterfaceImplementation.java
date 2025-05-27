interface Playable {
    void play(); // cite: 49
}

class Guitar implements Playable {
    @Override
    public void play() {
        System.out.println("Guitar plays a melody."); // cite: 50
    }
}

class Piano implements Playable {
    @Override
    public void play() {
        System.out.println("Piano plays a chord."); // cite: 50
    }
}

public class InterfaceImplementation {
    public static void main(String[] args) {
        Playable guitar = new Guitar(); // cite: 50
        Playable piano = new Piano(); // cite: 50

        guitar.play(); // cite: 50
        piano.play(); // cite: 50
    }
}