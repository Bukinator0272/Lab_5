package office;

import exceptions.FloorIndexOutOfBoundsException;
import exceptions.SpaceIndexOutOfBoundsException;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

public class OfficeBuilding implements Building {

    private static class Node {
        Node next;
        Node previous;
        OfficeFloor officeFloor;
    }

    private Node head;

    private OfficeBuilding() {
        head = new Node();
        head.next = head;
        head.previous = head;
    }

    public OfficeBuilding(int size, int[] officesCountOnFloor) {
        this();
        Node current = head;
        for (int i = 0; i < size; i++) {
            Node node = new Node();
            node.officeFloor = new OfficeFloor(officesCountOnFloor[i]);
            current.next = node;
            current.next.previous = current;
            current = current.next;
        }
        current.next = head.next;
        head.next.previous = current;
    }

    public OfficeBuilding(OfficeFloor[] officeFloors) {
        this();
        Node current = head;
        for (int i = 0; i < officeFloors.length; i++) {
            Node x = new Node();
            current.next = x;
            x.previous = current;
            x.officeFloor = officeFloors[i];
        }
        current.next = head.next;
        head.next.previous = current;
    }

    private Node getNode(int index) {
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    private void addNode(Node node, int index) {
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.next.previous = node;
        node.next = temp.next;
        temp.next = node;
        node.previous = temp;
    }

    public void removeNode(int index) {
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        temp.next.previous = temp;
    }

    public int getFloorsCount() {
        int result = 0;
        Node current = head;
        do {
            current = current.next;
            result++;
        } while (current.next != head.next);
        return result;
    }

    public int getSpacesCount() {
        int result = 0;
        Node current = head;
        do {
            current = current.next;
            result += current.officeFloor.getSpacesCount();
        } while (current.next != head.next);
        return result;
    }

    public double getSquaresCount() {
        double result = 0;
        Node current = head;
        do {
            current = current.next;
            result += current.officeFloor.getSquaresCount();
        } while (current.next != head.next);
        return result;
    }

    public int getRoomsCount() {
        int result = 0;
        Node current = head;
        do {
            current = current.next;
            result += current.officeFloor.getRoomsCount();
        } while (current.next != head.next);
        return result;
    }

    public Floor[] getFloorsArray() {
        OfficeFloor[] officeFloors = new OfficeFloor[getFloorsCount()];
        Node current = head;
        for (int i = 0; i < getFloorsCount(); i++) {
            current = current.next;
            officeFloors[i] = current.officeFloor;
        }
        return officeFloors;
    }

    public Floor getFloor(int index) {
        if ((index >= getFloorsCount()) || (index < 0)) {
            throw new FloorIndexOutOfBoundsException();
        }
        return getNode(index).officeFloor;
    }

    public void updateFloor(int index, Floor newFloor) {
        if ((index >= getFloorsCount()) || (index < 0)) {
            throw new FloorIndexOutOfBoundsException();
        }
        getNode(index).officeFloor = (OfficeFloor) newFloor;
    }

    public Space getSpace(int index) {
        if ((index >= getSpacesCount()) || (index < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Node current = head;
        int sum = 0;
        for (int i = 0; i < getFloorsCount(); i++) {
            current = current.next;
            sum += current.officeFloor.getSpacesCount();
            if (sum >= index) {
                int indexOnFloor = current.officeFloor.getSpacesCount() - (sum - index);
                return current.officeFloor.getSpace(indexOnFloor);
            }
        }
        return null;
    }

    public void updateSpace(int index, Space newSpace) {
        if ((index >= getSpacesCount()) || (index < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Node current = head;
        int sum = 0;
        for (int i = 0; i < getFloorsCount(); i++) {
            current = current.next;
            sum += current.officeFloor.getSpacesCount();
            if (sum >= index) {
                int indexOnFloor = current.officeFloor.getSpacesCount() - (sum - index);
                current.officeFloor.updateSpace(indexOnFloor, newSpace);
            }
        }
    }

    public void addSpace(int index, Space newSpace) {
        if ((index > getSpacesCount()) || (index < -1)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Node current = head;
        int sum = 0;
        for (int i = 0; i < getFloorsCount(); i++) {
            current = current.next;
            sum += current.officeFloor.getSpacesCount();
            if (sum >= index) {
                int indexOnFloor = current.officeFloor.getSpacesCount() - (sum - index);
                current.officeFloor.addSpace(indexOnFloor, newSpace);
            }
        }
    }

    public void deleteSpace(int index) {
        if ((index >= getSpacesCount()) || (index < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Node current = head;
        int sum = 0;
        for (int i = 0; i < getFloorsCount(); i++) {
            current = current.next;
            sum += current.officeFloor.getSpacesCount();
            if (sum >= index) {
                int indexOnFloor = current.officeFloor.getSpacesCount() - (sum - index);
                current.officeFloor.deleteSpace(indexOnFloor);
            }
        }
    }

    public Space getBestSpace() {
        if (head.next == head.previous) {
            return null;
        }
        Node current = head.next;
        Space bestOffice = current.officeFloor.getBestSpace();
        for (int i = 1; i < getFloorsCount(); i++) {
            current = current.next;
            if (bestOffice.getSquare() < current.officeFloor.getBestSpace().getSquare()) {
                bestOffice = current.officeFloor.getBestSpace();
            }
        }
        return bestOffice;
    }

    public Space[] getSpaceArraySorted() {
        Space[] offices = new Space[getSpacesCount()];
        int count = 0;
        Floor[] floors = getFloorsArray();
        for (int i = 0; i < getFloorsCount(); i++) {
            for (int j = 0; j < floors[i].getSpacesCount(); j++) {
                offices[count] = floors[i].getSpaceArray()[j];
                count++;
            }
        }
        Space temp;
        int min;
        for (int k = 0; k < getSpacesCount() - 1; k++) {
            min = k;
            for (int j = k + 1; j < getSpacesCount(); j++) {
                if (offices[j].getSquare() > offices[min].getSquare()) {
                    min = j;
                }
            }
            temp = offices[min];
            offices[min] = offices[k];
            offices[k] = temp;
        }
        return offices;
    }

}
