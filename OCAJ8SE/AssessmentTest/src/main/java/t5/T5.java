package t5;

/**
 * Created by Sergey Chudakov
 * [svc32.simai@gmail.com] on 11/22/2016.
 */
public class T5 {
    public static void main(String[] args) {
        System.out.print("a");
        try {
            System.out.print("b");
            throw new IllegalArgumentException();
        } catch (RuntimeException e) {
            System.out.print("c");
        } finally {
            System.out.print("d");
        }
        System.out.print("e");
    }
}
