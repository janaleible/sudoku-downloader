package downloader;

import java.io.IOException;

public interface Downloader {

    void downloadAndWriteToDir(String path, int numberOfFiles) throws IOException;
}
