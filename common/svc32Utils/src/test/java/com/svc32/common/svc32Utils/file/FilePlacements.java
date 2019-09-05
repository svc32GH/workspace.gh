package com.svc32.common.svc32Utils.file;

import com.svc32.common.svc32Utils.map.M2MMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static com.svc32.common.svc32Utils.file.FileFunctions.fillFileList;

public class FilePlacements {
    private List<File> dirSearchList = new ArrayList();
    private M2MMap mapFilesInDirs = new M2MMap();

    public FilePlacements() {
    }

    public FilePlacements(File file) {
        try {
            addDirSearch(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addDirSearch(File file) throws FileNotFoundException {
        if (!file.exists())
            throw new FileNotFoundException();
        if (!file.isDirectory())
            throw new FileNotFoundException("Can not find directory: "
                    + file.getAbsolutePath()
                    + "\nProbably this is not a directory."
            );
        dirSearchList.add(file);
    }

    private List<File> getFilesInDirs() {
        List<File> fileList = new ArrayList();
        for (File file : dirSearchList) {
            fillFileList(file, fileList);
        }
        return fileList;
    }

    public void fillMapFilesInDirs() {
        this.mapFilesInDirs.clear();
        List <File> fileList = getFilesInDirs();
        for (File file : fileList) {
            String fileName = file.getName();
            String fileDir = file.getParent();
            mapFilesInDirs.put(fileName, fileDir);
        }
    }

    public M2MMap getMapFilesInDirs() {
        return this.mapFilesInDirs;
    }

    public List<Map.Entry> getListFilesInDirs() {
        List<Map.Entry> list = new ArrayList(this.mapFilesInDirs.entrySet());
        Collections.sort(list, new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        Map.Entry e1 = (Map.Entry) o1;
                        Map.Entry e2 = (Map.Entry) o2;
                        Set set1 = (Set) e1.getValue();
                        Set set2 = (Set) e2.getValue();
                        String v1 = (String) set1.iterator().next();
                        String v2 = (String) set2.iterator().next();
                        return v1.compareTo(v2);
                    }
                }
        );
        return list;
    }
}
