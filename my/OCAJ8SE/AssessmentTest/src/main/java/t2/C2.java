package t2;

/**
 * Created by Sergey Chudakov
 * [svc32.simai@gmail.com] on 11/21/2016.
 */
public class C2 {
    public static void main(String[] args) {
        (new C2()).run();
    }

    public void run() {
        String s1 = "Java";
        String s2 = "Java";
        StringBuffer sb1 = new StringBuffer();
        sb1.append("Ja").append("va");
        System.out.println(s1 == s2);                   // true
        System.out.println(s1.equals(s2));              // true
        System.out.println(sb1.toString() == s1);       // false
        System.out.println(sb1.toString().equals(s1));  // true
    }

}
