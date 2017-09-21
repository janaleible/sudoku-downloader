package fileReader;

import java.io.IOException;

public class SOTDFileReader extends FileReader {

    public SOTDFileReader(String path) throws IOException {
        super(path);
    }

    @Override

    public boolean isValid(String line) {

        return (
            line.contains("PUZZLE=")
            && line.contains("LEVEL=")
        );
    }
}
