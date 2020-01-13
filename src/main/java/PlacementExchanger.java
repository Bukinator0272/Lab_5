import exceptions.InexchangeableFloorsException;
import exceptions.InexchangeableSpacesException;
import exceptions.SpaceIndexOutOfBoundsException;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

public class PlacementExchanger {

    public static boolean compareSpaces(Space space1, Space space2) {
        return space1.getSquare() == space2.getSquare() && space1.getRoomCount() == space2.getRoomCount();
    }

    public static boolean compareFloors(Floor floor1, Floor floor2) {
        return floor1.getSquaresCount() == floor2.getSquaresCount() && floor1.getSpacesCount() == floor2.getSpacesCount();
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException {
        if (((index1 >= floor1.getSpacesCount()) || (index1 < 0)) || ((index2 >= floor2.getSpacesCount()) || (index2 < 0))) {
            throw new SpaceIndexOutOfBoundsException();
        }
        if (!compareSpaces(floor1.getSpace(index1), floor2.getSpace(index2))) {
            throw new InexchangeableSpacesException();
        }
        Space tempSpace = floor1.getSpace(index1);
        floor1.updateSpace(index1, floor2.getSpace(index2));
        floor2.updateSpace(index2, tempSpace);
    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InexchangeableFloorsException {
        if (((index1 > building1.getFloorsCount()) || (index1 < 0)) || ((index2 > building2.getFloorsCount()) || (index2 < 0))) {
            throw new SpaceIndexOutOfBoundsException();
        }
        if (!compareFloors(building1.getFloor(index1), building2.getFloor(index2))) {
            throw new InexchangeableFloorsException();
        }
        Floor tempFloor = building1.getFloor(index1);
        building1.updateFloor(index1, building2.getFloor(index2));
        building2.updateFloor(index2, tempFloor);
    }
}
