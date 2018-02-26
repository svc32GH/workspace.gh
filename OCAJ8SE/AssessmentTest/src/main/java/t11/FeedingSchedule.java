package t11;

/**
 * Created by Sergey Chudakov
 * [svc32.simai@gmail.com] on 11/26/2016.
 */
public class FeedingSchedule {
    public static void main(String[] args) {
        int x = 5, j = 0;
        OUTER: for(int i=0; i<3; )
            INNER: do {
                i++; x++;
                if(x > 10) break INNER;
                x += 4;
                j++;
            } while(j <= 2);
        System.out.println(x);
//        int y = 0;
//        System.out.println("y = " + y);
//        y += 1;
//        System.out.println("y = " + y);
//        y += 1;
//        System.out.println("y = " + y);
    }
}
