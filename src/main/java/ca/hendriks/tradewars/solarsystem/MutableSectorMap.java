package ca.hendriks.tradewars.solarsystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MutableSectorMap extends ImmutableSectorMap {

    public MutableSectorMap(final Random random, final int size) {
        super(random, size);
    }

    public MutableSectorMap(final Random random, final Map<Integer, Sector> sectorCache) {
        super(random, sectorCache);
    }

    @Override
    public Sector findSector(final int id) {
        return sectorCache().computeIfAbsent(id, Sector::new);
    }

    public Sector createNext() {
        final int next = size() + 1;
        return findSector(next);
    }

    public MutableSectorMap createManagedSectorView() {
        final Map<Integer, Sector> managedSectorMap = new HashMap<>(sectorCache());
        managedSectorMap.remove(1);
        managedSectorMap.remove(2);
        managedSectorMap.remove(3);
        managedSectorMap.remove(4);
        managedSectorMap.remove(5);
        managedSectorMap.remove(6);
        managedSectorMap.remove(7);
        return new MutableSectorMap(random(), managedSectorMap);
    }

    public void remove(final Sector sector) {
        sectorCache().remove(sector.id());
    }

    public ImmutableSectorMap immutable() {
        return new ImmutableSectorMap(random(), sectorCache());
    }

}
