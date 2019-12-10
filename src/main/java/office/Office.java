package office;

import exceptions.InvalidRoomsCountException;
import exceptions.InvalidSpaceAreaException;
import interfaces.Space;

public class Office implements Space {

    private double square;
    private int roomCount;

    private static int ROOMS_DEFAULT = 1;
    private static double SQUARE_DEFAULT = 250;

    public Office() {
        this.square = SQUARE_DEFAULT;
        this.roomCount = ROOMS_DEFAULT;
    }

    public Office(double square) {
        this.square = square;
        this.roomCount = ROOMS_DEFAULT;
    }

    public Office(double square, int roomCount) {
        if (square <= 0) {
            throw new InvalidSpaceAreaException();
        }
        if (roomCount <= 0) {
            throw new InvalidRoomsCountException();
        }
        this.square = square;
        this.roomCount = roomCount;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        if (square <= 0) {
            throw new InvalidSpaceAreaException();
        }
        this.square = square;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        if (roomCount <= 0) {
            throw new InvalidRoomsCountException();
        }
        this.roomCount = roomCount;
    }

}
