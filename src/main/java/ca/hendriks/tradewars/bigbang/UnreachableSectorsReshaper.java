package ca.hendriks.tradewars.bigbang;

import ca.hendriks.tradewars.graph.MutableSectorGraph;
import ca.hendriks.tradewars.graph.SectorPath;
import ca.hendriks.tradewars.solarsystem.MutableSectorMap;
import ca.hendriks.tradewars.solarsystem.Sector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Function;

record UnreachableSectorsReshaper(MutableSectorMap sectors, MutableSectorGraph sectorGraph) {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnreachableSectorsReshaper.class);

    public void linkUnreachableSectors() {
        LOGGER.info("Linking Orphan Sectors");
        logAllPathsFromSectorOneToEverySector();
        logAllPathsEverySectorToSectorOne();
    }

    private void logAllPathsFromSectorOneToEverySector() {
        logAllPaths(targetSector -> {
                    final Optional<SectorPath> optionalPath = sectorGraph.findShortestPath(sectors.sectorOne(), targetSector);
                    optionalPath.ifPresent(path -> LOGGER.debug("path from 1 to {}: {}", targetSector, path.getVertexList()));
                    return optionalPath;
                },
                (targetSector, randomSector) -> sectorGraph.link(randomSector, targetSector));
    }

    private void logAllPathsEverySectorToSectorOne() {
        logAllPaths(targetSector -> {
                    final Optional<SectorPath> optionalPath = sectorGraph.findShortestPath(targetSector, sectors.sectorOne());
                    optionalPath.ifPresent(path -> LOGGER.debug("path from {} to 1: {}", targetSector, path.getVertexList()));
                    return optionalPath;
                },
                sectorGraph::link);
    }

    private void logAllPaths(final Function<Sector, Optional<SectorPath>> fn, final BiConsumer<Sector, Sector> linkOrphanSectorFn) {
        final Map<Integer, AtomicInteger> stats = new HashMap<>();
        sectors.forEach(sector -> {
            final SectorPath path = findPathOrLinkOrphans(fn, linkOrphanSectorFn, sector);
            final List<Sector> vertexList = path.getVertexList();
            final int totalVertices = vertexList.size();
            if (stats.containsKey(totalVertices)) {
                final AtomicInteger atomicInteger = stats.get(totalVertices);
                atomicInteger.getAndIncrement();
            } else {
                stats.put(totalVertices, new AtomicInteger(1));
            }
        });
    }

    private SectorPath findPathOrLinkOrphans(final Function<Sector, Optional<SectorPath>> fn, final BiConsumer<Sector, Sector> linkOrphanSectorFn, final Sector x) {
        Optional<SectorPath> optionalPath = fn.apply(x);
        while (optionalPath.isEmpty()) {
            linkSectorWithRandomOtherSector(x, linkOrphanSectorFn);
            optionalPath = fn.apply(x);
        }
        return optionalPath.get();
    }

    private void linkSectorWithRandomOtherSector(final Sector targetSector, final BiConsumer<Sector, Sector> linkOrphanSectorFn) {
        final Sector randomSector = sectors.findRandomSector();
        linkOrphanSectorFn.accept(targetSector, randomSector);
    }

}
