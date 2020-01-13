package buildings;

import exceptions.InvalidRoomsCountException;
import exceptions.SpaceIndexOutOfBoundsException;
import interfaces.Floor;
import interfaces.Space;

import java.io.Serializable;

public class DwellingFloor implements Floor, Serializable {

    private Flat[] flats;

    public DwellingFloor(int flatsCount) {
        if ((flatsCount < 0)) {
            throw new InvalidRoomsCountException();
        }
        this.flats = new Flat[flatsCount];
        for (int i = 0; i < flatsCount; i++) {
            flats[i] = new Flat();
        }
    }

    public DwellingFloor(Flat[] flats) {
        this.flats = flats;
    }

    public int getSpacesCount() {
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

    public Space[] getSpaceArray() {
        return flats;
    }

    public Space getSpace(int Id) {
        if ((Id >= flats.length) || (Id < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        return flats[Id];
    }

    public void updateSpace(int Id, Space newSpace) {
        if ((Id >= flats.length) || (Id < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        this.flats[Id] = (Flat) newSpace;
    }

    public void addSpace(int Id, Space newSpace) {
        if ((Id > flats.length) || (Id < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Flat[] newFlats = new Flat[flats.length + 1];
        for (int i = 0; i < newFlats.length; i++) {
            if (i == Id) {
                newFlats[Id] = (Flat) newSpace;
            } else if (i < Id) {
                newFlats[i] = flats[i];
            } else {
                newFlats[i] = flats[i - 1];
            }
        }
        flats = newFlats;
    }

    public void deleteSpace(int Id) {
        if ((Id >= flats.length) || (Id < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Flat[] newFlats = new Flat[flats.length - 1];
        for (int i = 0; i < flats.length - 1; i++) {
            if (i < Id) {
                newFlats[i] = flats[i];
            } else {
                newFlats[i] = flats[i + 1];
            }
        }
        flats = newFlats;
    }

    public Space getBestSpace() {
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
