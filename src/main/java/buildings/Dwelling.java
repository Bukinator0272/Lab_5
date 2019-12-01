package buildings;

public class Dwelling {

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

    public int getFlatsCount() {
        int count = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            count += dwellingFloors[i].getFlatsCount();
        }
        return count;
    }

    public int getSquaresCount() {
        int count = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            count += dwellingFloors[i].getFlatsCount();
        }
        return count;
    }

    public int getRoomsCount() {
        int count = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            count += dwellingFloors[i].getFlatsCount();
        }
        return count;
    }

    public DwellingFloor[] getDwellingFloors() {
        return dwellingFloors;
    }

    public DwellingFloor getFloorById(int Id) {
        if ((Id >= dwellingFloors.length) || (Id < 0)) {
            System.out.println("Id out of bounds");
            return null;
        }
        return dwellingFloors[Id];
    }

    public void updateFloor(int Id, DwellingFloor newDwellingFloor) {
        if ((Id >= dwellingFloors.length) || (Id < 0)) {
            System.out.println("Id out of bounds");
            return;
        }
        this.dwellingFloors[Id] = newDwellingFloor;
    }

    public Flat getFlatById(int Id) {
        if ((Id >= getFlatsCount()) || (Id < 0)) {
            System.out.println("Id out of bounds");
            return null;
        }
        int count = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            count += dwellingFloors[i].getFlatsCount();
            if (count >= Id) {
                return dwellingFloors[i].getFlatById(count - Id - 1);
            }
        }
        return null;
    }

    public void updateFlatById(int Id, Flat newFlat) {
        if ((Id >= getFlatsCount()) || (Id < 0)) {
            System.out.println("Id out of bounds");
        }
        int count = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            count += dwellingFloors[i].getFlatsCount();
            if (count >= Id) {
                dwellingFloors[i].updateFlatById(count - Id - 1, newFlat);
            }
        }
    }

    public void addFlat(int Id, Flat newFlat) {
        if ((Id > getFlatsCount()) || (Id < 0)) {
            System.out.println("Id out of bounds");
        }
        int count = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            count += dwellingFloors[i].getFlatsCount();
            if (count >= Id) {
                dwellingFloors[i].addFlat(count - Id - 1, newFlat);
            }
        }
    }

    public void deleteFlatById(int Id) {
        if ((Id >= getFlatsCount()) || (Id < 0)) {
            System.out.println("Id out of bounds");
        }
        int count = 0;
        for (int i = 0; i < dwellingFloors.length; i++) {
            count += dwellingFloors[i].getFlatsCount();
            if (count >= Id) {
                dwellingFloors[i].deleteFlatById(count - Id - 1);
            }
        }
    }

    public Flat getBestSpace() {
        Flat bestFlat = dwellingFloors[0].getBestSpace();
        for (int i = 1; i < dwellingFloors.length; i++) {
            Flat tempFlat = getBestSpace();
            if (tempFlat.getSquare() > bestFlat.getSquare()) {
                bestFlat = tempFlat;
            }
        }
        return bestFlat;
    }

    public Flat[] getFlatArraySortedByFloorId(int Id) {
        Flat temp;
        int min;
        for (int i = 0; i < dwellingFloors[Id].getFlatsCount() - 1; i++) {
            min = i;
            for (int j = i + 1; j < dwellingFloors[Id].getFlatsCount(); j++) {
                if (dwellingFloors[Id].getFlats()[j].getSquare() < dwellingFloors[Id].getFlats()[min].getSquare()) {
                    min = j;
                }
            }
            temp = dwellingFloors[Id].getFlats()[min];
            dwellingFloors[Id].getFlats()[min] = dwellingFloors[Id].getFlats()[i];
            dwellingFloors[Id].getFlats()[i] = temp;
        }
        return dwellingFloors[Id].getFlats();
    }
}
