package buildings;

import exceptions.FloorIndexOutOfBoundsException;
import exceptions.InvalidRoomsCountException;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

import java.io.Serializable;
import java.util.Arrays;

public class Dwelling implements Building, Serializable, Cloneable {

    private DwellingFloor[] dwellingFloors;

    public Dwelling(int floorsCount, int[] flatsCount) {
        this.dwellingFloors = new DwellingFloor[floorsCount];
        for (int i = 0; i < floorsCount; i++) {
            this.dwellingFloors[i] = new DwellingFloor(flatsCount[i]);
        }
    }

    public Dwelling(DwellingFloor[] dwellingFloors) {
        this.dwellingFloors = dwellingFloors;
    }

    public int getFloorsCount() {
        return dwellingFloors.length;
    }

    public int getSpacesCount() {
        int count = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            count += dwellingFloors[i].getSpacesCount();
        }
        return count;
    }

    public double getSquaresCount() {
        int count = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            count += dwellingFloors[i].getSpacesCount();
        }
        return count;
    }

    public int getRoomsCount() {
        int count = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            count += dwellingFloors[i].getSpacesCount();
        }
        return count;
    }

    public Floor[] getFloorsArray() {
        return dwellingFloors;
    }

    public Floor getFloor(int Id) {
        if ((Id >= dwellingFloors.length) || (Id < 0)) {
            throw new FloorIndexOutOfBoundsException();
        }
        return dwellingFloors[Id];
    }

    public void setFloor(int Id, Floor newFloor) {
        if ((Id >= dwellingFloors.length) || (Id < 0)) {
            throw new FloorIndexOutOfBoundsException();
        }
        this.dwellingFloors[Id] = (DwellingFloor) newFloor;
    }

    public Space getSpace(int Id) {
        if ((Id >= dwellingFloors.length) || (Id < 0)) {
            throw new InvalidRoomsCountException();
        }
        int count = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            count += dwellingFloors[i].getSpacesCount();
            if (count >= Id) {
                return dwellingFloors[i].getSpace(count - Id - 1);
            }
        }
        return null;
    }

    public void updateSpace(int Id, Space newSpace) {
        if ((Id >= dwellingFloors.length) || (Id < 0)) {
            throw new InvalidRoomsCountException();
        }
        int count = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            count += dwellingFloors[i].getSpacesCount();
            if (count >= Id) {
                dwellingFloors[i].setSpace(count - Id - 1, newSpace);
            }
        }
    }

    public void addSpace(int Id, Space newSpace) {
        if ((Id > dwellingFloors.length) || (Id < 0)) {
            throw new InvalidRoomsCountException();
        }
        int count = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            count += dwellingFloors[i].getSpacesCount();
            if (count >= Id) {
                dwellingFloors[i].addSpace(count - Id - 1, newSpace);
            }
        }
    }

    public void deleteSpace(int Id) {
        if ((Id >= dwellingFloors.length) || (Id < 0)) {
            throw new InvalidRoomsCountException();
        }
        int count = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            count += dwellingFloors[i].getSpacesCount();
            if (count >= Id) {
                dwellingFloors[i].deleteSpace(count - Id - 1);
            }
        }
    }

    public Space getBestSpace() {
        Space bestFlat = dwellingFloors[0].getBestSpace();
        for (int i = 1; i < dwellingFloors.length; i++) {
            Flat tempFlat = (Flat) getBestSpace();
            if (tempFlat.getSquare() > bestFlat.getSquare()) {
                bestFlat = tempFlat;
            }
        }
        return bestFlat;
    }

    public Space[] getSpaceArraySorted() {
        Space[] flats = new Space[getSpacesCount()];
        int floorNum = 0, i = 0;
        for (; i < dwellingFloors[floorNum].getSpacesCount(); i++) {
            flats[i] = dwellingFloors[floorNum].getSpaceArray()[i];
            if (i == dwellingFloors[floorNum].getSpacesCount()) {
                floorNum++;
                i = 0;
            }
        }
        Space temp;
        int min;
        for (int k = 0; k < getSpacesCount() - 1; k++) {
            min = k;
            for (int j = k + 1; j < getSpacesCount(); j++) {
                if (flats[j].getSquare() > flats[min].getSquare()) {
                    min = j;
                }
            }
            temp = flats[min];
            flats[min] = flats[k];
            flats[k] = temp;
        }
        return flats;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dwelling (").append(getFloorsCount()).append(", ");
        for (int i = 0; i < getFloorsCount(); i++) {
            if (i > 0)
                stringBuilder.append(", ");
            stringBuilder.append(dwellingFloors[i].toString());
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
        Dwelling dwelling = (Dwelling) o;
        return Arrays.equals(dwellingFloors, dwelling.dwellingFloors);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(dwellingFloors);
        return result;
    }

//    @Override
//    public Object clone() throws CloneNotSupportedException {
//        Building building = (Building) super.clone();
//        for (int i = 0; i < building.getFloorsCount(); i++) {
//            building.setFloor(i, (Floor) building.getFloor(i).clone());
//            for (int j = 0; j < building.getFloor(i).getSpacesCount(); i++) {
//                building.getFloor(i).setSpace(j, (Space) building.getSpace(j).clone());
//            }
//        }
//        return building;
//    }

}
