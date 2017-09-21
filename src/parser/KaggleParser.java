package parser;

import model.DimensionNoSquareNumberException;
import model.Grid;
import model.IncompleteLineException;

public class KaggleParser implements Parser {

    private Grid puzzle;
    private Grid solution;

    @Override
    public void parseString(String raw) throws InvalidInputException, DimensionNoSquareNumberException, NotYetImplementedException {

        String parts[] = raw.split(", ");
        String puzzle = parts[0];
        String solution = parts[1];

        if(puzzle.length() != solution.length())
            throw new InvalidInputException();

        int dimension = (int)Math.sqrt(Math.sqrt(puzzle.length()));

        if(Math.pow(dimension, 4) != puzzle.length())
            throw new DimensionNoSquareNumberException();

        if(dimension > 3)
            throw new NotYetImplementedException();

        try{
            this.puzzle = new Grid(this.stringToArray(puzzle, dimension));
            this.solution = new Grid(this.stringToArray(solution, dimension));
        } catch(IncompleteLineException e) {}
    }

    private int[][] stringToArray(String string, int dimension) {

        int[][] grid = new int[dimension * dimension][dimension * dimension];

        for(int i = 0; i < dimension * dimension; i++) {
            for(int j = 0; j < dimension * dimension; j++) {
                grid[i][j] = -1 + Character.getNumericValue(string.charAt(i * dimension * dimension + j));
            }
        }

        return grid;
    }

    @Override
    public Grid getPuzzle() {
        return this.puzzle;
    }

    @Override
    public Grid getSolution() {
        return this.solution;
    }
}
