package t7;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey Chudakov
 * [svc32.simai@gmail.com] on 11/22/2016.
 */
public class T7 {
    public static void main(String[] args) {
        int[] array = {6,9,8};
        List<Integer> list = new ArrayList<Integer>();
        list.add(array[0]);
        list.add(array[2]);
        list.set(1,array[1]);
        list.remove(0);
        System.out.println(list);
    }
}
