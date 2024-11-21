package ca.hendriks.tradewars.bigbang;

import ca.hendriks.tradewars.graph.MutableSectorGraph;
import ca.hendriks.tradewars.solarsystem.MutableSectorMap;
import ca.hendriks.tradewars.solarsystem.SolarSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class BigBang {

    private static final Logger LOGGER = LoggerFactory.getLogger(BigBang.class);

    private static final double DEFAULT_ONE_WAY_SECTOR_PERCENT = 0.05;

    private Integer maxSectors = 1000;
    private Long seed = 16309L;
    private Integer oneWayWarpLanes;

    /**
     * <a href="https://www.reddit.com/r/Tradewars/comments/ztyejd/universe_generation/">Tradewars Universe Generation</a>
     */
    public SolarSystem generate() {

        final Random random = new Random(seed);
        LOGGER.info("Creating Solar System of size {} with Seed {}", maxSectors, seed);
        final MutableSectorMap sectors = new MutableSectorMap(random, maxSectors);

        final MutableSectorGraph sectorGraph = new SectorGraphGenerator(random, sectors, maxSectors).generate();

        new BiDirectionalWarpLanesShaper(sectorGraph).makeAllOneWayWarpsIntoTwoWayWarps();

        final SectorOneReshaper sectorOneReshaper = new SectorOneReshaper(sectors, sectorGraph);
        sectorOneReshaper.removeAllEdgesFromSectorOne();
        sectorOneReshaper.linkSectorOneToFirstSevenSectors();

        int sanitizedOneWayWarpLanesTotal = oneWayWarpLanes != null ? oneWayWarpLanes : (int) (maxSectors * DEFAULT_ONE_WAY_SECTOR_PERCENT);
        new OneWarpWarpLanesShaper(sanitizedOneWayWarpLanesTotal, sectors, sectorGraph).buildOneWayWarpLanes();

        new UnreachableSectorsReshaper(sectors, sectorGraph).linkUnreachableSectors();

        return new SolarSystem(sectors.immutable(), sectorGraph.immutable());
    }

    public BigBang maxSectors(int total) {
        this.maxSectors = total;
        return this;
    }

    public BigBang oneWayWarpLanes(final int total) {
        this.oneWayWarpLanes = total;
        return this;
    }

    public BigBang seed(final long seed) {
        this.seed = seed;
        return this;
    }

}
