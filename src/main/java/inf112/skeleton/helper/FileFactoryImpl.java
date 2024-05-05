package inf112.skeleton.helper;

import java.io.File;
import java.io.FilenameFilter;

public class FileFactoryImpl implements FileFactory {
    public File createFile(String path) {
        return new File(path);
    }

    public File[] listFiles(File directory, FilenameFilter filter) {
        return directory.listFiles(filter);
    }
}
