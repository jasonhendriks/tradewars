package ca.hendriks.tradewars.graph;

import ca.hendriks.tradewars.solarsystem.Sector;
import org.jgrapht.GraphPath;

import java.util.List;

public record SectorPath(GraphPath<Sector, WarpLane> path) {

    public List<Sector> getVertexList() {
        return path.getVertexList();
    }

}
