package TestBookingInformation;
import junitparams.JUnitParamsRunner; 
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test; 
import org.junit.runner.RunWith;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import BookingInformation.WaitingList;
import BookingInformation.User;

@RunWith(JUnitParamsRunner.class) 
public class TestWaitingList {
	private WaitingList wt;

    @Before
    public void setUp() {
        wt = new WaitingList();
    }

    @Test
    public void testAddWaitingVIP() {
        User user1 = new User("Rong", "VIP");
        wt.addWaiting(user1);
        List<User> expected = new ArrayList<>();
        expected.add(user1);
        assertEquals(expected, wt.getVIP());
    }

    @Test
    public void testAddWaitingMember() {
        User user1 = new User("Alvin", "Member");
        wt.addWaiting(user1);
        List<User> expected = new ArrayList<>();
        expected.add(user1);
        assertEquals(expected, wt.getDeluxe());
    }

    @Test
    public void testAddWaitingNonMember() {
        User user1 = new User("Ali", "nonMember");
        wt.addWaiting(user1);
        List<User> expected = new ArrayList<>();
        expected.add(user1);
        assertEquals(expected, wt.getStandard());
    }

    @Test
    public void testRemoveWaitingVIP() {
        User user1 = new User("Abu", "VIP");
        wt.addWaiting(user1);
        wt.removeWaiting(user1);
        assertEquals(new ArrayList<>(), wt.getVIP());
    }

    @Test
    public void testRemoveWaitingMember() {
        User user1 = new User("Ahmad", "Member");
        wt.addWaiting(user1);
        wt.removeWaiting(user1);
        assertEquals(new ArrayList<>(), wt.getDeluxe());
    }

    @Test
    public void testRemoveWaitingNonMember() {
        User user1 = new User("Yan", "nonMember");
        wt.addWaiting(user1);
        wt.removeWaiting(user1);
        assertEquals(new ArrayList<>(), wt.getStandard());
    }

    
	
    /*
     * @Test
	@Parameters({})
	public void testAddWaitingMember( List<User>Input,List<User> ER) {
		for (int i=0;i<Input.size();i++) {
			wt.addWaiting(Input.get(i));
		}
		User[]eR=(User[]) ER.toArray();
		User[]AR=(User[]) wt.getDeluxe().toArray();
		assertArrayEquals(eR,AR);
	}
	@Test
	@Parameters({})
	public void testAddWaitingNonMember( List<User>Input,List<User> ER) {
		for (int i=0;i<Input.size();i++) {
			wt.addWaiting(Input.get(i));
		}
		User[]eR=(User[]) ER.toArray();
		User[]AR=(User[]) wt.getStandard().toArray();
		assertArrayEquals(eR,AR);
	}
	
	
	
	
	@Test
	@Parameters({})
	public void testGetWaiting(String memberType, String name) {
     
       wt = new WaitingList();
        User user = new User(name, memberType);
        wt.addWaiting(user);
        
       
        User retrievedUser = wt.getWaiting(user);
        
     
        assertEquals(user, retrievedUser);
    }
	
	public void testRemoveWaitingVIP(List<User>Input,User user,User[] updatedList) {
		wt=new WaitingList();
		for (int i=0;i<Input.size();i++) {
			wt.addWaiting(Input.get(i));
		}
		wt.removeWaiting(user);
		assertEquals(wt.getVIP(),updatedList);
	}
	@Test
	@Parameters({})
	public void testRemoveWaitingDeluxe(List<User>Input,User user,User[] updatedList) {
		wt=new WaitingList();
		for (int i=0;i<Input.size();i++) {
			wt.addWaiting(Input.get(i));
		}
		wt.removeWaiting(user);
		assertEquals(wt.getDeluxe(),updatedList);
	}
	@Test
	@Parameters({})
	public void testRemoveWaitingRegular(List<User>Input,User user,User[] updatedList) {
		wt=new WaitingList();
		for (int i=0;i<Input.size();i++) {
			wt.addWaiting(Input.get(i));
		}
		wt.removeWaiting(user);
		assertEquals(wt.getStandard(),updatedList);
	}
     * */



}
