package interfaces;

public interface Floor {

    int getSpacesCount();

    double getSquaresCount();

    int getRoomsCount();

    Space[] getSpaceArray();

    Space getSpace(int index);

    void setSpace(int index, Space space);

    void addSpace(int index, Space space);

    void deleteSpace(int index);

    Space getBestSpace();

}
