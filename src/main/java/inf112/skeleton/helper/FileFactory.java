package inf112.skeleton.helper;

import java.io.File;
import java.io.FilenameFilter;

public interface FileFactory {
    File createFile(String path);
    File[] listFiles(File directory, FilenameFilter filter);
}