package t6;

/**
 * Created by Sergey Chudakov
 * [svc32.simai@gmail.com] on 11/22/2016.
 */
public class MathFunctions {
    public static void addToInt(int x, int amountToAdd) {
        x = x + amountToAdd;
    }

    public static void addToInteger(Integer x, int amountToAdd) {
        x = x + amountToAdd;
    }

    public void addToIntegerRT(Integer x, int amountToAdd) {
        x = x + amountToAdd;
    }

    public static void main(String[] args) {
        int a = 15;
        int b = 10;
        MathFunctions.addToInt(a, b);
        System.out.println(a);

        Integer aa = 15;
        MathFunctions.addToInteger(aa, b);
        System.out.println(aa);

        (new MathFunctions()).run();
    }

    public void run() {
        Integer aa = new Integer(15);
        int b = 10;
        addToIntegerRT(aa, b);
        System.out.println("run: " + aa);
    }
}
