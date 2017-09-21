package fileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class FileReader {

    private ArrayList<String> lines;
    private int lineCounter;

    public abstract boolean isValid(String line);

    public FileReader(String path) throws IOException {

        this.lines = new ArrayList<>();
        this.lineCounter = 0;

        File file = new File(path);

        java.io.FileReader fileReader = new java.io.FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if(isValid(line)) this.lines.add(line);
        }

        bufferedReader.close();
    }

    public String getNextLine() throws ReadFileFirstException {

        if(this.lines != null) {
            if(lineCounter >= this.lines.size()) return null;
            else return lines.get(lineCounter++);
        }
        else throw new ReadFileFirstException();
    }
}
