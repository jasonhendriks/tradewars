package ca.hendriks.tradewars.bigbang;

import ca.hendriks.tradewars.graph.MutableSectorGraph;
import ca.hendriks.tradewars.graph.WarpLane;
import ca.hendriks.tradewars.solarsystem.MutableSectorMap;
import ca.hendriks.tradewars.solarsystem.Sector;
import org.jgrapht.Graph;
import org.jgrapht.generate.WattsStrogatzGraphGenerator;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.function.Supplier;

/**
 * <a href="https://en.wikipedia.org/wiki/Watts–Strogatz_model">Watts–Strogatz model</a>
 */
record SectorGraphGenerator(Random random, MutableSectorMap sectors, int maxSectors) {

    private static final Logger LOGGER = LoggerFactory.getLogger(SectorGraphGenerator.class);

    public MutableSectorGraph generate() {
        final Supplier<Sector> sectorSupplier = sectors()::createNext;
        final Graph<Sector, WarpLane> graph = new SimpleDirectedGraph<>(sectorSupplier, WarpLane::new, false);
        new WattsStrogatzGraphGenerator<Sector, WarpLane>(maxSectors(), 4, 0.5, false, random()).generateGraph(graph);
        LOGGER.info("Create system of {} sectors", maxSectors());
        return new MutableSectorGraph(graph);
    }

}
