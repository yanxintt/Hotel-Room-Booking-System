package BookingInformation;

public class Booking {
    private WaitingList wtList;
    private Room room;

    public Booking(WaitingList wtList) {
        this.wtList = wtList;
    }

    public Booking(WaitingList wtList, Room room) {
        this.wtList = wtList;
        this.room = room;
    }

    public void initializeRoom(int VIP, int Deluxe, int Regular) {
        room = new Room(VIP, Deluxe, Regular);
    }

    public void setBooking(User user, int numberOfRoom) {
        Printer pr = new Printer();
        if (numberOfRoom <= 0) {
            System.out.println("Invalid number of rooms.");
            return;
        }

        for (int i = 0; i < numberOfRoom; i++) {
            if (user.getMemberType().equals("VIP")) {
                if (room.checkroom("VIP")) {
                    room.bookVIP(1);
                    pr.printInfo(user.getName(), user.getMemberType(), "VIP");
                } else if (room.checkroom("Deluxe")) {
                    room.bookDeluxe(1);
                    pr.printInfo(user.getName(), user.getMemberType(), "Deluxe");
                } else if (room.checkroom("Regular")) {
                    room.bookStandard(1);
                    pr.printInfo(user.getName(), user.getMemberType(), "Regular");
                } else {
                    wtList.addWaiting(user);
                    System.out.println("Your room is currently not available. You have been added to the waiting list.");
                }
            } else if (user.getMemberType().equals("member")) {
                if (room.checkroom("VIP") && user.getExclReward()) {
                    room.bookVIP(1);
                    user.setExclReward(0);
                    pr.printInfo(user.getName(), user.getMemberType(), "VIP");
                } else if (room.checkroom("Deluxe")) {
                    room.bookDeluxe(1);
                    pr.printInfo(user.getName(), user.getMemberType(), "Deluxe");
                } else if (room.checkroom("Regular")) {
                    room.bookStandard(1);
                    pr.printInfo(user.getName(), user.getMemberType(), "Regular");
                } else {
                    wtList.addWaiting(user);
                    System.out.println("Your room is currently not available. You have been added to the waiting list.");
                }
            } else if (user.getMemberType().equals("normal")) {
                if (room.checkroom("Deluxe")) {
                    room.bookDeluxe(1);
                    pr.printInfo(user.getName(), user.getMemberType(), "Deluxe");
                } else if (room.checkroom("Regular")) {
                    room.bookStandard(1);
                    pr.printInfo(user.getName(), user.getMemberType(), "Regular");
                } else {
                    wtList.addWaiting(user);
                    System.out.println("Your room is currently not available. You have been added to the waiting list.");
                }
            }
        }
    }

    public void cancelBooking(User user) {
        wtList.removeWaiting(user);
    }
}
