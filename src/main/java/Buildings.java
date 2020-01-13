import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

import java.io.*;

public class Buildings {

    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(out);
        dataOutputStream.writeInt(building.getFloorsCount());
        for (int i = 0; i < building.getFloorsCount(); i++) {
            dataOutputStream.writeInt(building.getFloor(i).getSpacesCount());
            for (int j = 0; j < building.getFloor(i).getSpacesCount(); j++) {
                dataOutputStream.writeInt(building.getFloor(i).getSpace(j).getRoomCount());
                dataOutputStream.writeDouble(building.getFloor(i).getSpace(j).getSquare());
            }
        }
        dataOutputStream.close();
    }

    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(in);
        Floor[] floors = new Floor[dataInputStream.readInt()];
        Building building = null;
        for (int i = 0; i < floors.length; i++) {
            Space[] spaces = new Space[dataInputStream.readInt()];
            for (int j = 0; j < spaces.length; j++) {
                spaces[j].setRoomCount(dataInputStream.readInt());
                spaces[j].setSquare(dataInputStream.readDouble());
                floors[i].setSpace(j, spaces[j]);
            }
            building.setFloor(i, floors[i]);
        }
        dataInputStream.close();
        return building;
    }

    public static void writeBuilding(Building building, Writer out) {
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.print(building.getFloorsCount() + " ");
        for (int i = 0; i < building.getFloorsCount(); i++) {
            printWriter.print(building.getFloor(i).getSpacesCount() + " ");
            for (int j = 0; j < building.getFloor(i).getSpacesCount(); j++) {
                printWriter.print(building.getFloor(i).getSpace(j).getRoomCount() + " ");
                printWriter.print(building.getFloor(i).getSpace(j).getSquare() + " ");
            }
        }
        printWriter.close();
    }

    public static Building readBuilding(Reader in) throws IOException {
        StreamTokenizer streamTokenizer = new StreamTokenizer(in);
        Floor[] floors = new Floor[streamTokenizer.nextToken()];
        Building building = null;
        for (int i = 0; i < floors.length; i++) {
            Space[] spaces = new Space[streamTokenizer.nextToken()];
            for (int j = 0; j < spaces.length; j++) {
                spaces[j].setRoomCount(streamTokenizer.nextToken());
                spaces[j].setSquare(streamTokenizer.nextToken());
                floors[i].setSpace(j, spaces[j]);
            }
            building.setFloor(i, floors[i]);
        }
        return building;
    }

    public static void serializeBuilding(Building building, OutputStream out) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(building);
        objectOutputStream.close();
    }

    public static Building deserializeBuilding(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(in);
        Building building = (Building) objectInputStream.readObject();
        objectInputStream.close();
        return building;
    }

    public static void writeBuildingFormat(Building building, Writer out) {

    }
}
