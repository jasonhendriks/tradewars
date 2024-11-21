package ca.hendriks.tradewars.solarsystem;

import java.util.function.Consumer;

public interface SectorMap {

    Sector findSector(int id);

    default Sector sectorOne() {
        return findSector(1);
    }

    Sector findRandomSector();

    void forEach(Consumer<Sector> action);

    int size();

}
