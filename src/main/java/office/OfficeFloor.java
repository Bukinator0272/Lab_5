package office;

import exceptions.InvalidRoomsCountException;
import exceptions.SpaceIndexOutOfBoundsException;
import interfaces.Floor;
import interfaces.Space;

public class OfficeFloor implements Floor {

    private Node head;

    private static class Node {
        Node next;
        Office office;
    }

    private OfficeFloor() {
        head = new Node();
        head.next = head;
    }

    public OfficeFloor(int officeCount) {
        this();
        if ((officeCount < 0)) {
            throw new InvalidRoomsCountException();
        }

        Node current = head;
        for (int i = 0; i < officeCount; i++) {
            Node node = new Node();
            node.office = new Office();
            current.next = node;
            current = node;
        }
        current.next = head.next;
    }

    public OfficeFloor(Office[] offices) {
        this();
        Node current = head;
        for (int i = 0; i < offices.length; i++) {
            Node node = new Node();
            node.office = offices[i];
            current.next = node;
            current = node;
        }
        current.next = head.next;
    }

    private Node getNodeByIndex(int index) {
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    private void addNodeAtIndex(Node node, int index) {
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        node.next = temp.next;
        temp.next = node;
    }

    private void deleteNodeByIndex(int index) {
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }

    public int getSpacesCount() {
        Node current = head;
        int count = 0;
        do {
            current = current.next;
            count++;
        } while (current.next != head.next);
        return count;
    }

    public double getSquaresCount() {
        double count = 0;
        Node current = head;
        do {
            current = current.next;
            count += current.office.getSquare();
        } while (current.next != head.next);
        return count;
    }

    public int getRoomsCount() {
        int result = 0;
        Node current = head;
        do {
            current = current.next;
            result += current.office.getRoomCount();
        } while (current.next != head.next);
        return result;
    }

    public Space[] getSpaceArray() {
        Office[] offices = new Office[getSpacesCount()];
        Node current = head;
        for (int i = 0; i < getSpacesCount(); i++) {
            current = current.next;
            offices[i] = current.office;
        }
        return offices;
    }

    public Space getSpace(int index) {
        if ((index >= getSpacesCount()) || (index < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        return getNodeByIndex(index).office;
    }

    public void updateSpace(int index, Space newSpace) {
        if ((index >= getSpacesCount()) || (index < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        getNodeByIndex(index).office = (Office) newSpace;
    }

    public void addSpace(int index, Space newSpace) {
        if ((index >= getSpacesCount()) || (index < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Node node = new Node();
        node.office = (Office) newSpace;
        addNodeAtIndex(node, index);
    }

    public void deleteSpace(int index) {
        if ((index >= getSpacesCount()) || (index < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        deleteNodeByIndex(index);
    }

    public Space getBestSpace() {
        double bestSpace = 0;
        Office officeBestSpace = null;
        Node current = head;
        for (int i = 0; i <= getSpacesCount(); i++) {
            current = current.next;
            if (current.office.getSquare() > bestSpace) {
                bestSpace = current.office.getSquare();
                officeBestSpace = current.office;
            }
        }
        return officeBestSpace;
    }
}
