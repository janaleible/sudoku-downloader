package encoder;

import model.Grid;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class EwipondEncoder implements Encoder {

    @Override
    public void writeToFile(String filePath, Grid sudoku) throws UnsupportedEncodingException, FileNotFoundException {

        StringBuilder encoding = new StringBuilder();

        for(int rowNumber = 0; rowNumber < sudoku.getDimension() * sudoku.getDimension(); rowNumber++) {

            int[] row = sudoku.getRow(rowNumber);
            for(int value: row) {
                int entry = value + 1;

                if(entry == 0) encoding.append('.');
                else encoding.append(entry);
            }

            encoding.append('\n');
        }

        PrintWriter writer = new PrintWriter(filePath, "UTF-8");
        writer.print(encoding.toString());
        writer.close();
    }
}
