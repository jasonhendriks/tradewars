package ca.hendriks.tradewars.bigbang;

import ca.hendriks.tradewars.graph.MutableSectorGraph;
import ca.hendriks.tradewars.solarsystem.MutableSectorMap;
import ca.hendriks.tradewars.solarsystem.Sector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

record OneWarpWarpLanesShaper(int oneWayWarpLanes, MutableSectorMap sectors,
                              MutableSectorGraph sectorGraph) {

    private static final Logger LOGGER = LoggerFactory.getLogger(OneWarpWarpLanesShaper.class);

    public void buildOneWayWarpLanes() {
        LOGGER.info("Building {} one-way Warp Lanes", oneWayWarpLanes);
        final MutableSectorMap managedSectors = sectors.createManagedSectorView();
        for (int x = 1; x < oneWayWarpLanes / 2; x++) {
            final Sector sector = managedSectors.findRandomSector();
            LOGGER.debug("Warp Lanes on sector (before pruning) {}: {}", sector, sectorGraph().edgesOf(sector));
            sectorGraph.removeEdgesFromSector(sectorGraph::incomingEdgesOf, sector, 1);
            LOGGER.debug("Warp Lanes on sector (after pruning) {}: {}", sector, sectorGraph().edgesOf(sector));
            managedSectors.remove(sector);
        }
        for (int x = 1; x < oneWayWarpLanes / 2; x++) {
            final Sector sector = managedSectors.findRandomSector();
            LOGGER.debug("Warp Lanes on sector (before pruning) {}: {}", sector, sectorGraph().edgesOf(sector));
            sectorGraph.removeEdgesFromSector(sectorGraph::outgoingEdgesOf, sector, 1);
            LOGGER.debug("Warp Lanes on sector (after pruning) {}: {}", sector, sectorGraph().edgesOf(sector));
            managedSectors.remove(sector);
        }
    }

}
