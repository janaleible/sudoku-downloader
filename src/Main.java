import downloader.Downloader;
import downloader.SOTDDownloader;
import encoder.Encoder;
import encoder.EwipondEncoder;
import fileReader.FileReader;
import fileReader.SOTDFileReader;
import model.Grid;
import parser.Parser;
import parser.SOTDParser;

import java.io.File;
import java.io.PrintWriter;

public class Main {

    private static final String baseDir = "puzzles" + File.separator;
    private static final String rawDir = baseDir + "raw" + File.separator;
    private static final String encodedDir = baseDir + "parsed" + File.separator;
    private static final String metaFile = encodedDir + "_meta.txt";

    public static void main(String[] args) {

        Downloader downloader;
        FileReader reader;
        Parser parser = new SOTDParser();
        Encoder encoder = new EwipondEncoder();

        try{

            System.out.println("Starting download");

            downloader = new SOTDDownloader();
            downloader.downloadAndWriteToDir(rawDir, 48);

            String line;
            StringBuilder metadata = new StringBuilder();

            for(File file: (new File(rawDir)).listFiles()) {

                System.out.println("Parsing " + file.getName());

                reader = new SOTDFileReader(file.getPath());

                while((line = reader.getNextLine()) != null) {
                    parser.parseString(line);
                    Grid puzzle = parser.getPuzzle();
                    encoder.writeToFile(
                        encodedDir + puzzle.getID() + ".cnf",
                        puzzle
                    );
                    metadata.append(puzzle.getID() + ": " + puzzle.getPrintableMetadata() + ",\n");
                }
            }

            PrintWriter writer = new PrintWriter(metaFile, "UTF-8");
            writer.write(metadata.toString());
            writer.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
