package buildings;

public class DwellingFloor {

    private Flat[] flats;

    public DwellingFloor(int flatsCount) {
        this.flats = new Flat[flatsCount];
        for (int i = 0; i < flatsCount; i++) {
            flats[i] = new Flat();
        }
    }

    public DwellingFloor(Flat[] flats) {
        this.flats = flats;
    }

    public int getFlatsCount() {
        return flats.length;
    }

    public double getSquaresCount() {
        double count = 0;
        for (int i = 0; i < flats.length; i++) {
            count += flats[i].getSquare();
        }
        return count;
    }

    public int getRoomsCount() {
        int count = 0;
        for (int i = 0; i < flats.length; i++) {
            count += flats[i].getRoomCount();
        }
        return count;
    }

    public Flat[] getFlats() {
        return flats;
    }

    public Flat getFlatById (int Id) {
        if ((Id >= flats.length) || (Id < 0)) {
            System.out.println("Id out of bounds");
            return null;
        }
        return flats[Id];
    }

    public void updateFlatById (int Id, Flat newFlat) {
        if ((Id >= flats.length) || (Id < 0)) {
            System.out.println("Id out of bounds");
            return;
        }
        this.flats[Id] = newFlat;
    }

    public void addFlat(int Id, Flat newFlat) {
        if ((Id > flats.length) || (Id < 0)) {
            System.out.println("Id out of bounds");
            return;
        }
        Flat[] newFlats = new Flat[flats.length + 1];
        for (int i = 0; i < newFlats.length; i++) {
            if (i == Id) {
                newFlats[Id] = newFlat;
            } else if (i < Id) {
                newFlats[i] = flats[i];
            } else {
                newFlats[i] = flats[i - 1];
            }
        }
        flats = newFlats;
    }

    public void deleteFlatById (int Id) {
        if ((Id >= flats.length) || (Id < 0)) {
            System.out.println("Id out of bounds");
            return;
        }
        Flat[] newFlats = new Flat[flats.length - 1];
        for (int i = 0; i < flats.length - 1; i++) {
            if ( i < Id ) {
                newFlats[i] = flats[i];
            } else {
                newFlats[i] = flats[i+1];
            }
        }
        flats = newFlats;
    }

    public Flat getBestSpace() {
        double bestSpace = 0;
        Flat flatBestSpace = null;
        for (int i = 0; i < flats.length; i++) {
            if (flats[i].getSquare() > bestSpace) {
                bestSpace = flats[i].getSquare();
                flatBestSpace = flats[i];
            }
        }
        return flatBestSpace;
    }

}
