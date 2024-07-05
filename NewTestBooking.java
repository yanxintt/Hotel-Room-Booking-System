package TestBookingInformation;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;

import BookingInformation.FileUtils;
import BookingInformation.User;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class NewTestBooking {
	private newBooking nb;
	Information dm = new dummy();
	
	private Object[] paramVIPUser() {
		// VIP members plan to book room
	    return new Object[] {    		
	        new Object[] {3, new int[]{3, 3, 3}, 0, new int[]{0, 3, 3}},
	        new Object[] {3, new int[]{0, 3, 3}, 0, new int[]{0, 0, 3}},
	        new Object[] {3, new int[]{0, 0, 3}, 0, new int[]{0, 0, 0}},
	        new Object[] {3, new int[]{0, 0, 0}, 0, new int[]{0, 0, 0}},
	        new Object[] {3, new int[]{1, 3, 3}, 0, new int[]{0, 1, 3}},
	        new Object[] {3, new int[]{1, 0, 3}, 0, new int[]{0, 0, 1}},
	        new Object[] {3, new int[]{1, 1, 3}, 0, new int[]{0, 0, 2}},
	        new Object[] {3, new int[]{2, 1, 3}, 0, new int[]{0, 0, 3}},
	        new Object[] {3, new int[]{0, 2, 1}, 0, new int[]{0, 0, 0}},
	        new Object[] {3, new int[]{0, 1, 3}, 0, new int[]{0, 0, 1}},
	        new Object[] {3, new int[]{2, 0, 3}, 0, new int[]{0, 0, 2}},
	        new Object[] {3, new int[]{1, 1, 0}, 0, new int[]{0, 0, 0}},
	        new Object[] {3, new int[]{2, 0, 0}, 0, new int[]{0, 0, 0}},
	        new Object[] {3, new int[]{0, 1, 1}, 0, new int[]{0, 0, 0}},
	        //new Object[] {3, new int[]{0, 1, 0}, 0, new int[]{0, 0, 0}},
	        new Object[] {2, new int[]{3, 3, 3}, 0, new int[]{1, 3, 3}},
	        new Object[] {2, new int[]{0, 3, 3}, 0, new int[]{0, 1, 3}},
	        new Object[] {2, new int[]{0, 0, 3}, 0, new int[]{0, 0, 1}},     
	        new Object[] {2, new int[]{1, 3, 3}, 0, new int[]{0, 2, 3}},
	        new Object[] {2, new int[]{1, 0, 3}, 0, new int[]{0, 0, 2}},
	        new Object[] {2, new int[]{0, 1, 3}, 0, new int[]{0, 0, 2}},
	        new Object[] {2, new int[]{0, 1, 0}, 0, new int[]{0, 0, 0}},
	        new Object[] {1, new int[]{3, 3, 3}, 0, new int[]{2, 3, 3}},
	        new Object[] {1, new int[]{0, 3, 3}, 0, new int[]{0, 2, 3}},
	        new Object[] {1, new int[]{0, 0, 3}, 0, new int[]{0, 0, 2}},
	        new Object[] {1, new int[]{0, 0, 0}, 0, new int[]{0, 0, 0}}
	    };
	}

	@Test
	@Parameters(method="paramVIPUser")
	public void testSetBookingVIP(int numberOfRoom, int[] totalRoomNo, int excl, int[] er) {
		dm.setRoom(totalRoomNo[0], totalRoomNo[1], totalRoomNo[2]);
	    User user = new User("Alvin", "VIP");
	    user.setExclReward(excl);
	    nb = new newBooking(dm);
	    nb.setBooking(user, numberOfRoom);
	    assertArrayEquals(er, ((dummy) dm).getRemainingRoomCounts());
	}
	
	private Object[] paramInvalidVIPBooking() {
	    return new Object[] {
	        new Object[] {0, new int[]{3, 3, 3}},
	        new Object[] {4, new int[]{3, 3, 3}}
	    };
	}

	@Test(expected = IllegalArgumentException.class)
	@Parameters(method="paramInvalidVIPBooking")
	public void testSetBookingVIPInvalid(int numberOfRoom, int[] totalRoomNo) {
	    dm.setRoom(totalRoomNo[0], totalRoomNo[1], totalRoomNo[2]);
	    User user = new User("Alvin", "VIP");
	    nb = new newBooking(dm);
	    nb.setBooking(user, numberOfRoom);
	}
	
	public List<Object[]> paramMemberBooking() throws IOException {
        return FileUtils.readTestDataFromFile("D:/LEARNING/Y2S2/AsgmTestingFinal/Asgm/paramMemberBooking.txt");
	}
	
	@Test
	@Parameters(method="paramMemberBooking")
	public void testSetBookingMember(boolean hasExclusiveReward, int numberOfRoom, int[] totalRoomNo, int[] er) {
	    dm.setRoom(totalRoomNo[0], totalRoomNo[1], totalRoomNo[2]);
	    User user = new User("Rong", "Member");
	    user.setExclReward(hasExclusiveReward ? 1 : 0);
	    nb = new newBooking(dm);
	    nb.setBooking(user, numberOfRoom);
	    assertArrayEquals(er, ((dummy) dm).getRemainingRoomCounts());
	}
	
	private Object[] paramInvalidMemberBooking() {
	    return new Object[] {
	        new Object[] {0, new int[]{3, 3, 3}},
	        new Object[] {3, new int[]{3, 3, 3}}
	    };
	}

	@Test(expected = IllegalArgumentException.class)
	@Parameters(method="paramInvalidMemberBooking")
	public void testSetBookingMemberInvalid(int numberOfRoom, int[] totalRoomNo) {
	    dm.setRoom(totalRoomNo[0], totalRoomNo[1], totalRoomNo[2]);
	    User user = new User("Rong", "Member");
	    nb = new newBooking(dm);
	    nb.setBooking(user, numberOfRoom);
	}
	
	private Object[] paramNonMemberBooking() {
	    // Non member plan to book
	    return new Object[] {
	        new Object[] {1, new int[]{1, 1, 1}, new int[]{1, 1, 0}}, 
	        new Object[] {1, new int[]{0, 0, 1}, new int[]{0, 0, 0}}, 
	        new Object[] {1, new int[]{0, 1, 0}, new int[]{0, 1, 0}},
	        new Object[] {1, new int[]{0, 0, 0}, new int[]{0, 0, 0}},
	        new Object[] {1, new int[]{3, 3, 0}, new int[]{3, 3, 0}},
	        new Object[] {1, new int[]{3, 3, 3}, new int[]{3, 3, 2}}
	    };
	}

	@Test
	@Parameters(method="paramNonMemberBooking")
	public void testSetBookingNonMember(int numberOfRoom, int[] totalRoomNo, int[] er) {
	    dm.setRoom(totalRoomNo[0], totalRoomNo[1], totalRoomNo[2]);
	    User user = new User("Siong", "nonMember");
	    nb = new newBooking(dm);
	    nb.setBooking(user, numberOfRoom);
	    assertArrayEquals(er, ((dummy) dm).getRemainingRoomCounts());
	}
	
	private Object[] paramInvalidNonMemberBooking() {
	    return new Object[] {
	        new Object[] {0, new int[]{3, 3, 3}},
	        new Object[] {2, new int[]{3, 3, 3}}
	    };
	}

	@Test(expected = IllegalArgumentException.class)
	@Parameters(method="paramInvalidNonMemberBooking")
	public void testSetBookingNonMemberInvalid(int numberOfRoom, int[] totalRoomNo) {	    
		dm.setRoom(totalRoomNo[0], totalRoomNo[1], totalRoomNo[2]);
	    User user = new User("Siong", "nonMember");
	    nb = new newBooking(dm);
	    nb.setBooking(user, numberOfRoom);
	}
	
	private Object[] paramCancelVIPBooking() {
        return new Object[] {
        	//cancel VIP room, so VIP room will increase by one, Deluxe and Standard Room are booked
            new Object[] {"VIP", 3, new int[]{1, 2, 0}, new int[]{1, 0, 0}}, 
            new Object[] {"Deluxe", 2, new int[]{0, 1, 0}, new int[]{0, 1, 0}},
            new Object[] {"Standard", 1, new int[]{0, 0, 1}, new int[]{0, 0, 1}}
        };
    }
	
    @Test
    @Parameters(method="paramCancelVIPBooking")
    public void testCancelVIPBooking(String bookedRoomType, int numberOfRooms, int[] initialRoomCounts, int[] expectedRoomCounts) {
        dm.setRoom(initialRoomCounts[0], initialRoomCounts[1], initialRoomCounts[2]);
        nb = new newBooking(dm);
        User user = new User("Rong", "VIP"); 
        nb.setBooking(user, numberOfRooms);
        nb.cancelBooking(user, bookedRoomType);
        assertEquals(0, ((dummy) dm).getUser().size());
        assertArrayEquals(expectedRoomCounts, ((dummy) dm).getRemainingRoomCounts());
    }	
    
    private Object[] paramCancelMemberBooking() {
        return new Object[] {
        	//Member has reward, can book VIP, he want to cancel 1 VIP room ady booked
        	//The deluxe not cancelled so the existing room count will decrease by 1
            new Object[] {true, "VIP", 2, new int[]{1, 1, 0}, new int[]{1, 0, 0}},
            new Object[] {true, "Deluxe", 2, new int[]{1, 1, 0}, new int[]{0, 1, 0}},  //not cancel VIP room
            //not enough room to book, book 1 deluxe, left 1 room want to book, add into waiting list
            //Then will cancel the room ady booked and also remove from waiting list
            new Object[] {false, "Deluxe", 2, new int[]{1, 1, 0}, new int[]{1, 1, 0}}, 
            new Object[] {false, "Standard", 1, new int[]{0, 0, 1}, new int[]{0, 0, 1}}
        };
    }
	
    @Test
    @Parameters(method="paramCancelMemberBooking")
    public void testCancelMemberBooking(boolean hasExclusiveReward, String bookedRoomType, int numberOfRooms, int[] initialRoomCounts, int[] expectedRoomCounts) {
        dm.setRoom(initialRoomCounts[0], initialRoomCounts[1], initialRoomCounts[2]);
        nb = new newBooking(dm);
        User user = new User("Rong", "Member");         
	    user.setExclReward(hasExclusiveReward ? 1 : 0);
        nb.setBooking(user, numberOfRooms);
        nb.cancelBooking(user, bookedRoomType);
        assertEquals(0, ((dummy) dm).getUser().size());
        assertArrayEquals(expectedRoomCounts, ((dummy) dm).getRemainingRoomCounts());
    }	
    
    private Object[] paramCancelNonMemberBooking() {
        return new Object[] {
            new Object[] {"Standard", 1, new int[]{1, 0, 1}, new int[]{1, 0, 1}},
            new Object[] {"Standard", 1, new int[]{0, 0, 1}, new int[]{0, 0, 1}},  
            
        };
    }
	
    @Test
    @Parameters(method="paramCancelNonMemberBooking")
    public void testCancelNonMemberBooking(String bookedRoomType, int numberOfRooms, int[] initialRoomCounts, int[] expectedRoomCounts) {
        dm.setRoom(initialRoomCounts[0], initialRoomCounts[1], initialRoomCounts[2]);
        nb = new newBooking(dm);
        User user = new User("Rong", "nonMember"); 
        nb.setBooking(user, numberOfRooms);
        nb.cancelBooking(user, bookedRoomType);
        assertEquals(0, ((dummy) dm).getUser().size());
        assertArrayEquals(expectedRoomCounts, ((dummy) dm).getRemainingRoomCounts());
    }	
    
    
	
}
