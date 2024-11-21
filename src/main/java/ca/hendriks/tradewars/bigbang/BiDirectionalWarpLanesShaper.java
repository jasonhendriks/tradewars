package ca.hendriks.tradewars.bigbang;

import ca.hendriks.tradewars.graph.MutableSectorGraph;
import ca.hendriks.tradewars.graph.WarpLane;
import ca.hendriks.tradewars.solarsystem.Sector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Set;

record BiDirectionalWarpLanesShaper(MutableSectorGraph sectorGraph) {

    private static final Logger LOGGER = LoggerFactory.getLogger(BiDirectionalWarpLanesShaper.class);

    public void makeAllOneWayWarpsIntoTwoWayWarps() {
        LOGGER.info("Converting all Warp Lanes to bi-directional");
        sectorGraph().forEach(sector -> {
            boolean allEdgesAreBidirectional = false;
            while (!allEdgesAreBidirectional) {
                final Set<WarpLane> WarpLanes = sectorGraph().edgesOf(sector);
                final Iterator<WarpLane> iterator = WarpLanes.iterator();
                modifyOneWarpLaneAtATime(iterator);
                if (theLastEdgeHasBeenModified(iterator)) {
                    allEdgesAreBidirectional = true;
                }
            }
        });
    }

    private boolean theLastEdgeHasBeenModified(final Iterator<WarpLane> iterator) {
        return !iterator.hasNext();
    }

    private void modifyOneWarpLaneAtATime(final Iterator<WarpLane> iterator) {
        while (iterator.hasNext()) {
            final WarpLane edge = iterator.next();
            final Sector source = sectorGraph().getEdgeSource(edge);
            final Sector target = sectorGraph().getEdgeTarget(edge);
            if (!sectorGraph().containsEdge(target, source)) {
                sectorGraph().link(target, source);
                break;
            }
        }
    }

}
