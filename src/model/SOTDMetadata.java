package model;

public class SOTDMetadata implements Metadata {

    private int level;
    private double stars;

    public SOTDMetadata(int level, double stars) {
        this.level = level;
        this.stars = stars;
    }

    @Override
    public String toPrintable() {
        return "{Level: " + this.level + ", Stars: " + stars + "}";
    }
}
