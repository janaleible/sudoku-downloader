package encoder;

import model.Grid;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface Encoder {

    public void writeToFile(String filePath, Grid sudoku) throws UnsupportedEncodingException, FileNotFoundException;
}
