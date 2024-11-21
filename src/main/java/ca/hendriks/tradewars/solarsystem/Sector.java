package ca.hendriks.tradewars.solarsystem;

public record Sector(Integer id) implements Comparable<Sector> {

    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public int compareTo(final Sector that) {
        return this.id()
                .compareTo(that.id());
    }

}
