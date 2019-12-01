package buildings;

public class Flat {

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

    public Flat(double square, int rooms) {
        this.square = square;
        this.roomCount = rooms;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "square=" + square +
                ", roomCount=" + roomCount +
                '}';
    }
}
