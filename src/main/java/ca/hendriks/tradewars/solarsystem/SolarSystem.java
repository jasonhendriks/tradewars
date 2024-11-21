package ca.hendriks.tradewars.solarsystem;

import ca.hendriks.tradewars.graph.SectorGraph;
import ca.hendriks.tradewars.graph.WarpLane;

import java.util.List;
import java.util.Set;

public class SolarSystem {

    private final SectorMap sectorMap;
    private final SectorGraph sectorGraph;

    public SolarSystem(final ImmutableSectorMap sectorMap, final SectorGraph sectorGraph) {
        this.sectorMap = sectorMap;
        this.sectorGraph = sectorGraph;
    }

    public int size() {
        return sectorMap.size();
    }

    public Sector findSector(final int id) {
        return sectorMap.findSector(id);
    }

    public List<Sector> findSectorsLeadingAway(final Sector sector) {
        final Set<WarpLane> warpLanes = sectorGraph.outgoingEdgesOf(sector);
        return warpLanes.stream()
                .map(sectorGraph::getEdgeTarget)
                .toList();
    }

    public List<Sector> findSectorsLeadingTo(final Sector sector) {
        final Set<WarpLane> warpLanes = sectorGraph.incomingEdgesOf(sector);
        return warpLanes.stream()
                .map(sectorGraph::getEdgeSource)
                .toList();
    }

}
