package ca.hendriks.tradewars.graph;

import ca.hendriks.tradewars.solarsystem.Sector;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

class ImmutableSectorGraph implements SectorGraph {

    private final Graph<Sector, WarpLane> graph;

    public ImmutableSectorGraph(final Graph<Sector, WarpLane> graph) {
        this.graph = graph;
    }

    protected Graph<Sector, WarpLane> graph() {
        return graph;
    }

    @Override
    public Optional<SectorPath> findShortestPath(final Sector source, final Sector sink) {
        final DijkstraShortestPath<Sector, WarpLane> shortestPath = new DijkstraShortestPath<>(graph);
        final GraphPath<Sector, WarpLane> path = shortestPath.getPath(source, sink);
        if (path == null) {
            return Optional.empty();
        }
        return Optional.of(new SectorPath(path));
    }

    @Override
    public Set<WarpLane> edgesOf(Sector targetSector) {
        return graph.edgesOf(targetSector);
    }

    @Override
    public Set<WarpLane> incomingEdgesOf(final Sector targetSector) {
        return graph.incomingEdgesOf(targetSector);
    }

    @Override
    public Set<WarpLane> outgoingEdgesOf(final Sector sector) {
        return graph().outgoingEdgesOf(sector);
    }

    @Override
    public void forEach(final Consumer<Sector> action) {
        graph.iterables()
                .vertices()
                .forEach(action);
    }

    @Override
    public Sector getEdgeSource(final WarpLane edge) {
        return graph().getEdgeSource(edge);
    }

    @Override
    public Sector getEdgeTarget(final WarpLane edge) {
        return graph().getEdgeTarget(edge);
    }

    @Override
    public boolean containsEdge(final Sector target, final Sector source) {
        return graph().containsEdge(target, source);
    }

}
