package parser;

import model.DimensionNoSquareNumberException;
import model.Grid;
import model.SOTDMetadata;

public class SOTDParser implements Parser {

    private Grid puzzle;

    @Override
    public void parseString(String raw) throws InvalidInputException, DimensionNoSquareNumberException, NotYetImplementedException {

        int puzzlePosition = raw.indexOf("PUZZLE=") + "PUZZLE=".length();
        String puzzleString = raw.substring(puzzlePosition, puzzlePosition + 81);

        Parser parser = new KaggleParser();
        parser.parseString(puzzleString + ", " + puzzleString);

        this.puzzle = parser.getPuzzle();

        int level = 0;
        double starRating = 0;

        switch (raw.charAt(raw.indexOf("LEVEL=") + "LEVEL=".length())) {
            case 'E': level = 1; break; // easy
            case 'M': level = 2; break; // medium
            case 'H': level = 3; break; // hard
            case 'A': level = 4; break; // absurd
        }

        starRating = 6 - // 6 = max number of stars
                (raw.split("star_blank", -1).length - 1) //subtract blank stars
                - 0.5 * (raw.split("star_half", -1).length - 1); //subtract half stars

        this.puzzle.setMetadata(
            new SOTDMetadata(level, starRating)
        );
    }

    @Override
    public Grid getPuzzle() {
        return this.puzzle;
    }

    @Override
    public Grid getSolution() throws SolutionUnavailableException {
        throw new SolutionUnavailableException();
    }
}
