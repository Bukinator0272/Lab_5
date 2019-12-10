package interfaces;

public interface Building {

    int getFloorsCount();

    int getSpacesCount();

    double getSquaresCount();

    int getRoomsCount();

    Floor[] getFloorsArray();

    Floor getFloor(int Id);

    void updateFloor(int Id, Floor newFloor);

    Space getSpace(int Id);

    void updateSpace(int Id, Space newSpace);

    void addSpace(int Id, Space newSpace);

    void deleteSpace(int Id);

    Space getBestSpace();

    Space[] getSpaceArraySorted();

}
