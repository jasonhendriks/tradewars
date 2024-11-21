package ca.hendriks.tradewars.graph;

import ca.hendriks.tradewars.solarsystem.Sector;

import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public interface SectorGraph {

    Optional<SectorPath> findShortestPath(Sector source, Sector sink);

    Set<WarpLane> edgesOf(Sector targetSector);

    Set<WarpLane> incomingEdgesOf(Sector targetSector);

    Set<WarpLane> outgoingEdgesOf(Sector sector);

    void forEach(Consumer<Sector> action);

    Sector getEdgeSource(WarpLane edge);

    Sector getEdgeTarget(WarpLane edge);

    boolean containsEdge(Sector target, Sector source);

}
