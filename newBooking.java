package TestBookingInformation;

import java.util.ArrayList;
import java.util.List;

import BookingInformation.User;

interface Information{
	public boolean checkroom(String roomtype);
	public void bookVIP(int numRoom);
	public void bookDeluxe(int numRoom);
	public void bookStandard(int numRoom);
	public void printInfo(String name,String memberType,String roomType);
	public void setRoom(int VIP,int Deluxe,int Regular);
	public void addWaiting(User user);
	public void removeWaiting(User user);
	
}
class dummy implements Information {
    private int[] roomCounts = new int[3]; // Index 0: VIP, Index 1: Deluxe, Index 2: Regular
    private List<User> UserList=new ArrayList<User>();

    @Override
    public boolean checkroom(String roomType) {
        switch (roomType) {
            case "VIP":
                return roomCounts[0] > 0;
            case "Deluxe":
                return roomCounts[1] > 0;
            case "Standard":
                return roomCounts[2] > 0;
            default:
                return false;
        }
    }

    @Override
    public void bookVIP(int numRooms) {
        roomCounts[0] -= numRooms;
    }

    @Override
    public void bookDeluxe(int numRooms) {
        roomCounts[1] -= numRooms;
    }

    @Override
    public void bookStandard(int numRooms) {
        roomCounts[2] -= numRooms;
    }

    @Override
    public void printInfo(String name, String memberType, String roomType) {
        System.out.println("Name: " + name);
        System.out.println("Member Type: " + memberType);
        System.out.println("Room Type: " + roomType);
    }

    @Override
    public void setRoom(int VIP, int Deluxe, int Standard) {
        roomCounts[0] = VIP;
        roomCounts[1] = Deluxe;
        roomCounts[2] = Standard;
    }

    @Override
    public void addWaiting(User user) {
    	UserList.add(user);
		
	}
	public List <User> getUser() {
		return UserList;
	}
	public void setUserList(List<User> UserList) {
		this.UserList=UserList;
	}

    @Override
    public void removeWaiting(User user) {
    	for (int i=0;i<UserList.size();i++) {
			if(UserList.get(i).getName().equals(user.getName())){
				UserList.remove(i);
			}
		}
    }

    
    public int[] getRemainingRoomCounts() {
        return roomCounts;
    }
}
public class newBooking {
	Information information;

	public newBooking (Information information) {
		this.information=information;
	}
	public void setRoom (int VIP, int Deluxe,int Standard) {
		information.setRoom(VIP, Deluxe, Standard);
	}
	
	public void setBooking(User user,int numberOfRoom) {
		if (information == null) {
            System.out.println("Rooms are not initialized. Please initialize rooms.");
            return;
        }

        if (numberOfRoom <= 0 || numberOfRoom > 3) {
        	throw new IllegalArgumentException("Invalid number of rooms. Please select 1 to 3 rooms.");
        }
        if (user.getMemberType().equals("VIP") && numberOfRoom > 3) {
            throw new IllegalArgumentException("VIP members can book up to 3 rooms only.");
        }
        
        if (user.getMemberType().equals("Member") && numberOfRoom > 2) {
            throw new IllegalArgumentException("Members can book up to 2 rooms only.");
        }
        
        if (user.getMemberType().equals("nonMember") && numberOfRoom > 1) {
            throw new IllegalArgumentException("Non-members can book up to 1 room only.");
        }
		
		if ((numberOfRoom<=3 &&user.getMemberType().equals("VIP")) ||(numberOfRoom<=2 &&user.getMemberType().equals("Member"))||(numberOfRoom<=1 &&user.getMemberType().equals("nonMember"))  ) {//check validity of the numberOfRoom, maybe can do it in other function?
			
			// Book the requested number of rooms based on member type
		    if (user.getMemberType().equals("VIP")) {
		        bookRoomsForVIP(user, numberOfRoom);
		    } else if (user.getMemberType().equals("Member")) {
		        bookRoomsForMember(user, numberOfRoom);
		    } else if (user.getMemberType().equals("nonMember")) {
		        bookRoomsForNonMember(user, numberOfRoom);
		    }
		}}	

		// Method to book rooms for VIP member
		private void bookRoomsForVIP(User user, int numberOfRoom) {
		    for (int i = 0; i < numberOfRoom; i++) {
		        if (information.checkroom("VIP")) {
		            information.bookVIP(1);
		            information.printInfo(user.getName(), user.getMemberType(), "VIP");
		        } else if (information.checkroom("Deluxe")) {
		            information.bookDeluxe(1);
		            information.printInfo(user.getName(), user.getMemberType(), "Deluxe");
		        } else if (information.checkroom("Standard")) {
		            information.bookStandard(1);
		            information.printInfo(user.getName(), user.getMemberType(), "Standard");
		        } else {
		            information.addWaiting(user);
		            System.out.println("Your room is currently not available, we will add you to a waiting list first");
		            break;
		        }
		    }
		}

		// Method to book rooms for normal member
		private void bookRoomsForMember(User user, int numberOfRoom) {
		    for (int i = 0; i < numberOfRoom; i++) {
		        if (!information.checkroom("Deluxe") && information.checkroom("VIP") && user.getExclReward()) {
		            information.bookVIP(1);
		            user.setExclReward(0);
		            information.printInfo(user.getName(), user.getMemberType(), "VIP");
		        } else if (information.checkroom("Deluxe")) {
		            information.bookDeluxe(1);
		            information.printInfo(user.getName(), user.getMemberType(), "Deluxe");
		        } else if (information.checkroom("Standard")) {
		            information.bookStandard(1);
		            information.printInfo(user.getName(), user.getMemberType(), "Standard");
		        } else {
		            information.addWaiting(user);
		            System.out.println("Your room is currently not available, we will add you to a waiting list first");
		            break;
		        }
		    }
		}

		// Method to book rooms for non-member
		private void bookRoomsForNonMember(User user, int numberOfRoom) {
		    for (int i = 0; i < numberOfRoom; i++) {
		        if (information.checkroom("Standard")) {
		            information.bookStandard(1);
		            information.printInfo(user.getName(), user.getMemberType(), "Standard");
		        } else {
		            information.addWaiting(user);
		            System.out.println("Your room is currently not available, we will add you to a waiting list first");
		            break;
		        }
		    }
		}
		public void cancelBooking(User user, String bookedRoomType) {
		    information.removeWaiting(user);

		    int[] roomCounts = ((dummy) information).getRemainingRoomCounts();
		    switch (bookedRoomType) {
		        case "VIP":
		            roomCounts[0]++;
		            break;
		        case "Deluxe":
		            roomCounts[1]++;
		            break;
		        case "Standard":
		            roomCounts[2]++;
		            break;
		        default:
		            break;
		    }
		    information.setRoom(roomCounts[0], roomCounts[1], roomCounts[2]);
		}

}
