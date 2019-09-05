package com.svc32.common.svc32Utils.file;

import com.svc32.common.svc32Utils.map.M2MMap;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.svc32.common.svc32Utils.file.FileFunctions.*;


public class TestSameFiles {
    private static final String pathDisc = "D:\\";
    private static final String pathDir1 = "D:\\Doc";
    private static final String pathDir2 = "C:\\Users\\Serge\\Downloads";

    @Test
    public void testSameFiles() {
        File dir2 = new File(pathDir2);
        List<File> fileList2 = new ArrayList();
        fillFileList(dir2, fileList2);
//        System.out.println(fileListToString(fileList2));

        String fileName = dir2.getName();
        String fileDir = dir2.getParent();
        System.out.println(
                "fileName = " + fileName
                        + "\nfileDir  = " + fileDir
        );
        File disc = new File(pathDisc);
        System.out.println("File of disc exists   = " + disc.exists());
        System.out.println("disc is directory     = " + disc.isDirectory());
        System.out.println("disc is file          = " + disc.isFile());

        FilePlacements fp = new FilePlacements(dir2);
        fp.fillMapFilesInDirs();
        M2MMap map = fp.getMapFilesInDirs();
        System.out.println("map.size              = " + map.getSize());
        System.out.println("maxValuesetCount      = " + map.getMaxValuesetCount());
        System.out.println("noUniqueValuesetCount = " + map.getNoUniqueValuesetCount());
        List<Map.Entry> listFilesInDirs = fp.getListFilesInDirs();
        for (Map.Entry entry : listFilesInDirs) {
            Set<String> set = (Set) entry.getValue();
            if (set.size() > 1)
                System.out.println(entry);
        }
    }
}
