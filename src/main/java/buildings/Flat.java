package buildings;

import exceptions.InvalidRoomsCountException;
import exceptions.InvalidSpaceAreaException;
import interfaces.Space;

import java.io.Serializable;

public class Flat implements Space, Serializable, Cloneable {

    private double square;
    private int roomCount;

    private static int ROOMS_DEFAULT = 2;
    private static double SQUARE_DEFAULT = 50;

    public Flat() {
        this.square = SQUARE_DEFAULT;
        this.roomCount = ROOMS_DEFAULT;
    }

    public Flat(double square) {
        this.square = square;
        this.roomCount = ROOMS_DEFAULT;
    }

    public Flat(double square, int roomCount) {
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

    @Override
    public String toString() {
        return "Flat (" + roomCount + ", " + square + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Flat flat = (Flat) o;
        return Double.compare(flat.square, square) == 0 && roomCount == flat.roomCount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(square);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + roomCount;
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
