package ca.hendriks.tradewars.solarsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

public class ImmutableSectorMap implements SectorMap {

    private final Map<Integer, Sector> sectorCache;
    private final Random random;

    public ImmutableSectorMap(final Random random, final int size) {
        this.random = random;
        this.sectorCache = new HashMap<>(size);
    }

    public ImmutableSectorMap(final Random random, final Map<Integer, Sector> sectorCache) {
        this.random = random;
        this.sectorCache = sectorCache;
    }

    protected Map<Integer, Sector> sectorCache() {
        return sectorCache;
    }

    protected Random random() {
        return random;
    }

    @Override
    public Sector findSector(final int id) {
        final Sector sector = sectorCache.get(id);
        if (sector == null) {
            throw new SectorNotFoundException("Sector %d does not exist".formatted(id));
        }
        return sector;
    }

    @Override
    public Sector findRandomSector() {
        return new ArrayList<>(sectorCache.values()).get(random.nextInt(sectorCache.size()));
    }

    @Override
    public void forEach(final Consumer<Sector> action) {
        sectorCache.values()
                .forEach(action);
    }

    @Override
    public int size() {
        return sectorCache.size();
    }

}
