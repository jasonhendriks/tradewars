package ca.hendriks.tradewars.graph;

import ca.hendriks.tradewars.solarsystem.Sector;
import org.jgrapht.Graph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.function.Function;

public class MutableSectorGraph extends ImmutableSectorGraph implements SectorGraph {

    private static final Logger LOGGER = LoggerFactory.getLogger(MutableSectorGraph.class);

    public MutableSectorGraph(final Graph<Sector, WarpLane> graph) {
        super(graph);
    }

    public void removeEdgesFromSector(final Function<Sector, Set<WarpLane>> edgeQueryFn, final Sector sector, final int edgesToLeave) {
        Set<WarpLane> mutableEdges = edgeQueryFn.apply(sector);
        while (mutableEdges.size() > edgesToLeave) {
            final WarpLane edge = mutableEdges.iterator()
                    .next();
            LOGGER.debug("Removing {} from {}", edge, sector);
            graph().removeEdge(edge);
            mutableEdges = edgeQueryFn.apply(sector);
        }
        LOGGER.debug("Remaining edges on {} are: {}", sector, mutableEdges);
    }

    public void linkInBothDirections(final Sector originatingSector, final Sector targetSector) {
        link(originatingSector, targetSector);
        link(targetSector, originatingSector);
    }

    public void link(final Sector originatingSector, final Sector targetSector) {
        LOGGER.debug("Linking {} to {}", originatingSector, targetSector);
        graph().addEdge(originatingSector, targetSector);
        LOGGER.debug("Paths on {} are {}", originatingSector, graph().edgesOf(originatingSector));
        LOGGER.debug("Paths on {} are {}", targetSector, graph().edgesOf(targetSector));
    }

    public SectorGraph immutable() {
        return new ImmutableSectorGraph(graph());
    }

}
