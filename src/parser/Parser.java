package parser;

import model.DimensionNoSquareNumberException;
import model.Grid;

public interface Parser {

    void parseString(String raw) throws InvalidInputException, DimensionNoSquareNumberException, NotYetImplementedException;
    Grid getPuzzle();
    Grid getSolution() throws SolutionUnavailableException;
}
