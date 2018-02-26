package t4;

/**
 * Created by Sergey Chudakov
 * [svc32.simai@gmail.com] on 11/21/2016.
 */
public class FeedingSchedule {

    public static void main(String[] args) {
        boolean keepGoing = true;
        int count = 0;
        int x = 3;
        while (count++ < 3) {
            int y = (1 + 2 * count) % 3;
            switch(y) {
                default:
                case 0:
                    x -= 1;
                    break;
                case 1:
                    x += 5;
            }
        }
        System.out.println(x);
        System.out.println("count = " + count);

        int c = 0;
        while(c++ < 3) {
        }
        System.out.println("c     = " + c);

        int d = 0;
        while(++d < 3) {
        }
        System.out.println("d     = " + d);

    }
}
