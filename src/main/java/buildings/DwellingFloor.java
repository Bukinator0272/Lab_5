package buildings;

import exceptions.InvalidRoomsCountException;
import exceptions.SpaceIndexOutOfBoundsException;
import interfaces.Floor;
import interfaces.Space;

import java.io.Serializable;
import java.util.Arrays;

public class DwellingFloor implements Floor, Serializable, Cloneable {

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

    public void setSpace(int Id, Space newSpace) {
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DwellingFloor (").append(getSpacesCount()).append(", ");
        for (int i = 0; i < flats.length; i++) {
            if (i > 0)
                stringBuilder.append(", ");
            stringBuilder.append(flats[i].toString());
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DwellingFloor that = (DwellingFloor) o;
        return Arrays.equals(flats, that.flats);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(flats);
        return result;
    }

//    @Override
//    public Object clone() throws CloneNotSupportedException {
//        Floor floor = (Floor) super.clone();
//        for (int i = 0; i < floor.getSpacesCount(); i++) {
//            floor.setSpace(i, (Space) floor.getSpace(i).clone());
//        }
//        return floor;
//    }

}
