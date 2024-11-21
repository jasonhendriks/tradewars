package ca.hendriks.tradewars.bigbang;

import ca.hendriks.tradewars.graph.MutableSectorGraph;
import ca.hendriks.tradewars.solarsystem.MutableSectorMap;
import ca.hendriks.tradewars.solarsystem.Sector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

record SectorOneReshaper(MutableSectorMap sectors, MutableSectorGraph graph) {

    private static final Logger LOGGER = LoggerFactory.getLogger(SectorOneReshaper.class);

    void removeAllEdgesFromSectorOne() {
        LOGGER.info("Removing all edges from Sector One");
        final Sector sectorOne = sectors.sectorOne();
        graph.removeEdgesFromSector(graph::edgesOf, sectorOne, 0);
    }

    void linkSectorOneToFirstSevenSectors() {
        LOGGER.info("Linking Sector One to Sectors Two through Seven");
        final Sector originatingSector = sectors.sectorOne();
        for (int x = 2; x <= 7; x++) {
            final Sector targetSector = sectors.findSector(x);
            graph.linkInBothDirections(originatingSector, targetSector);
        }
    }

}
