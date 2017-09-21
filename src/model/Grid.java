package model;

import java.util.ArrayList;
import java.util.UUID;

public class Grid {

    private UUID id;
    private int dimension;
    private int[][] entries;
    private Metadata metadata;

    public Grid(int[][] entries) throws IncompleteLineException, DimensionNoSquareNumberException {

        this.id = UUID.randomUUID();

        this.dimension = (int)Math.sqrt(entries.length);
        if(this.dimension * this.dimension != entries.length)
            throw new DimensionNoSquareNumberException();

        for (int[] line: entries) {
            if(Math.sqrt(line.length) != dimension) {
                throw new IncompleteLineException();
            }
        }

        this.entries = entries;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String getPrintableMetadata() {
        if(metadata != null) return this.metadata.toPrintable();
        else return null;
    }

    public UUID getID() {
        return this.id;
    }

    public int[] getRow(int index) {

        if(!(index < dimension * dimension))
            throw new IndexOutOfBoundsException();

        return entries[index];
    }

    public int[] getColumn(int index) {

        if(!(index < dimension * dimension))
            throw new IndexOutOfBoundsException();

        ArrayList<Integer> column = new ArrayList<>();

        for (int[] row: entries) {
            column.add(row[index]);
        }

        // return int[] rather than Integer[]
        return column.stream().mapToInt(i -> i).toArray();
    }

    public int[][] getGrid() {
        return entries;
    }

    public int getDimension() {
        return dimension;
    }
}
