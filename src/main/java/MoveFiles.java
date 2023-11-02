import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
public class MoveFiles {

    /**
     * Instructions:
     * publish
     * releaseClient
     * make public
     * releaseClient again
     * make public again
     * MoveFiles
     * push hosting files
     */
    static String[] FILE_NAMES = {"http-api", "polish", "rlawt", "runelite-api", "runelite-jshell", "runescape-api", "runelite-client"};
    static String SNAPSHOT = "4.2.5";
    public static void main(String[] args) throws IOException {
        String[] extensions = {"jar"};
        Collection<File> files = FileUtils.listFiles(new File(System.getProperty("user.dir") + "/unstable/"), extensions, true);
        files.forEach(file -> {
            for (String fileName : FILE_NAMES) {
                if (file.getName().contains(fileName) && file.getName().contains(SNAPSHOT)) {
                    try {
                        FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "/stable/" + file.getName()), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

}
