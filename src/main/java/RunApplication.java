import buildings.Dwelling;
import buildings.DwellingFloor;
import buildings.Flat;

public class RunApplication {

    public static void main(String[] args) {
        Flat flat1 = new Flat();
        Flat flat2 = new Flat(100);
        Flat flat3 = new Flat(45, 5);
        Flat[] flats = {flat1, flat2, flat3};
        DwellingFloor dwellingFloor = new DwellingFloor(flats);

        for (int i = 0; i < dwellingFloor.getFlatsCount(); i++) {
            System.out.println(dwellingFloor.getFlats()[i].toString());
        }

        DwellingFloor[] dwellingFloors = {dwellingFloor};
        Dwelling dwelling = new Dwelling(dwellingFloors);


        for (int i = 0; i < dwellingFloor.getFlatsCount(); i++) {
            System.out.println(dwelling.getFlatArraySortedByFloorId(0)[i].toString());
        }
    }
}

