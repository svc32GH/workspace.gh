package t8;

/**
 * Created by Sergey Chudakov
 * [svc32.simai@gmail.com] on 11/22/2016.
 */
public class Deer {
    public Deer() { System.out.print("Deer"); }
    public Deer(int age) { System.out.print("DeerAge"); }
    private boolean hasHorns() { return false; }

    public static void main(String[] args) {
        Deer deer = new Reindeer(5);
        System.out.print("," + deer.hasHorns());
        System.out.print("," + ((Reindeer) deer).hasHorns());
    }
}
