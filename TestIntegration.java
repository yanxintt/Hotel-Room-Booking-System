package TestBookingInformation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.anyString;

import BookingInformation.Booking;
import BookingInformation.Room;
import BookingInformation.User;
import BookingInformation.WaitingList;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


@RunWith(JUnitParamsRunner.class)
public class TestIntegration {

	@Mock
    private Room room;
	
	private newBooking nb;
	Information dm = new dummy();
   
	private Object[] paramVIPInsufficientRooms() {
	    return new Object[] {
	        new Object[] {3, new int[]{0, 2, 0}, new int[]{0, 0, 0}, new String[]{"Alvin"}},
	        new Object[] {2, new int[]{0, 1, 0}, new int[]{0, 0, 0}, new String[]{"Rong"}},
	        new Object[] {1, new int[]{0, 0, 0}, new int[]{0, 0, 0}, new String[]{"Siong"}},
	    };
	}

	@Test
	@Parameters(method="paramVIPInsufficientRooms")
	public void testBookingVIPAndWaitingList(int numberOfRooms, int[] initialRoomCounts, int[] totalRoomNo, String[] expectedWaitingList) {	   
	    dm.setRoom(initialRoomCounts[0], initialRoomCounts[1], initialRoomCounts[2]);
	    nb = new newBooking(dm);
	    User user = new User(expectedWaitingList[0], "VIP");
	    nb.setBooking(user, numberOfRooms);
	    assertArrayEquals(totalRoomNo, ((dummy) dm).getRemainingRoomCounts());
	    List<User> waitingList = ((dummy) dm).getUser();
	    assertEquals(expectedWaitingList.length, waitingList.size());
	    for (int i = 0; i < expectedWaitingList.length; i++) {
	        assertEquals(expectedWaitingList[i], waitingList.get(i).getName());
	    }
	}
	private Object[] paramInsufficientRoomsMember() {
	    return new Object[] {
	        new Object[] {true, 2, new int[]{1, 0, 0}, new int[]{0, 0, 0}, new String[]{"Sien"}},
	        new Object[] {false, 2, new int[]{2, 0, 0}, new int[]{2, 0, 0}, new String[]{"Alvin"}},
	        new Object[] {true, 1, new int[]{0, 0, 0}, new int[]{0, 0, 0}, new String[]{"Siong"}},
	        new Object[] {false, 1, new int[]{2, 0, 0}, new int[]{2, 0, 0}, new String[]{"Rong"}}
	    };
	}

	@Test
	@Parameters(method="paramInsufficientRoomsMember")
	public void testBookingMemberAndWaitingList(boolean hasExclusiveReward, int numberOfRooms, int[] initialRoomCounts, int[] totalRoomNo, String[] expectedWaitingList) {
	    dm.setRoom(initialRoomCounts[0], initialRoomCounts[1], initialRoomCounts[2]);
	    nb = new newBooking(dm);
	    User user = new User(expectedWaitingList[0], "Member");   
	    user.setExclReward(hasExclusiveReward ? 1 : 0);
	    nb.setBooking(user, numberOfRooms);
	    assertArrayEquals(totalRoomNo, ((dummy) dm).getRemainingRoomCounts());
	    List<User> waitingList = ((dummy) dm).getUser();
	    assertEquals(expectedWaitingList.length, waitingList.size());
	    for (int i = 0; i < expectedWaitingList.length; i++) {
	        assertEquals(expectedWaitingList[i], waitingList.get(i).getName());
	    }
	}
	private Object[] paramNonMemberInsufficientRooms() {
	    return new Object[] {
	        new Object[] {1, new int[]{3, 2, 0}, new int[]{3, 2, 0}, new String[]{"Alvin"}},
	        new Object[] {1, new int[]{0, 2, 0}, new int[]{0, 2, 0}, new String[]{"Rong"}},
	        new Object[] {1, new int[]{0, 0, 0}, new int[]{0, 0, 0}, new String[]{"Siong"}},
	    };
	}

	@Test
	@Parameters(method="paramNonMemberInsufficientRooms")
	public void testBookingNonMemberAndWaitingList(int numberOfRooms, int[] initialRoomCounts, int[] totalRoomNo, String[] expectedWaitingList) {
	    dm.setRoom(initialRoomCounts[0], initialRoomCounts[1], initialRoomCounts[2]);
	    nb = new newBooking(dm);
	    User user = new User(expectedWaitingList[0], "nonMember");
	    nb.setBooking(user, numberOfRooms);
	    assertArrayEquals(totalRoomNo, ((dummy) dm).getRemainingRoomCounts());
	    List<User> waitingList = ((dummy) dm).getUser();
	    assertEquals(expectedWaitingList.length, waitingList.size());
	    for (int i = 0; i < expectedWaitingList.length; i++) {
	        assertEquals(expectedWaitingList[i], waitingList.get(i).getName());
	    }
	} 
   
}