import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey Chudakov [svc32.simai@gmail.com] on 7/8/2017.
 **/
public class Main {
    public static final String path1 = "D:\\ws.gh\\my\\common\\ComparePaths\\src\\test\\resources\\F1";
    public static final String path2 = "D:\\ws.gh\\my\\common\\ComparePaths\\src\\test\\resources\\F2";

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        System.out.println("*** ComparePaths.Main.run() ***");
        List<String> list1;
        List<String> list2;
        List<String> listRes;

        List<String> fnList = getListFileNames(new File(path1));
        removePrefix(path1, fnList);
        printList(fnList);
        list1 = fnList;

        fnList = getListFileNames(new File(path2));
        removePrefix(path2, fnList);
        printList(fnList);
        list2 = fnList;

        list1.retainAll(list2);
        printList(list1);
    }

    public void printList(List<String> list){
        System.out.println("");
        for (String fName : list)
            System.out.println(fName);
    }

    public List<String> getListFileNames(File path) {
        List<String> listFiles = new ArrayList();
        List<String> listDirs = new ArrayList();
        listFiles.add(path.getAbsolutePath());

        for (File file : path.listFiles())
            if (file.isFile())
                listFiles.add(file.getAbsolutePath());
            else
                listDirs.addAll(getListFileNames(file));

        listFiles.addAll(listDirs);

        return listFiles;
    }

    public void removePrefix(String prefix, List<String> list) {
        int len = prefix.length();
        for (int i=0; i<list.size(); i++) {
            String name = list.get(i);
            if (name.startsWith(prefix))
                name = "\\" + name.substring(len);
            list.set(i, name);
        }
    }
}
