package TestBookingInformation;
import junitparams.JUnitParamsRunner; 
import junitparams.Parameters; 
import org.junit.Test; 
import org.junit.runner.RunWith; 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import BookingInformation.User;

@RunWith(JUnitParamsRunner.class) 
public class TestUser {
	User us;
	
	@Test
	@Parameters({
		"John, VIP",
	    "Alice, Member",
	    "Bob, nonMember"
	})
	public void testGetName(String name,String member_type) {
		us =new User(name,member_type);
		String ER=name;
		String AR=us.getName();
		assertEquals(ER,AR);
		assertNotNull(ER);
	}
	
	@Test(expected = IllegalStateException.class)
    @Parameters({"", "Alice_123", "John123"})
    public void testGetNameInvalid(String name) {
        us = new User(name, "VIP");
        us.getName();
    }
	
	@Test
	@Parameters({
		"John, VIP",
	    "Alice, Member",
	    "Bob, nonMember"
	})
	public void testGetMemberType(String name,String member_type) {
		us =new User(name,member_type);
		String ER=member_type;
		String AR=us.getMemberType();
		assertEquals(ER,AR);
		assertNotNull(ER);
	}
	
	@Test(expected = IllegalStateException.class)
    @Parameters({"normal", "null"})
    public void testGetMemberTypeInvalid(String member_type) {
        us = new User("Ali", member_type);
        us.getMemberType();
    }
	
	@Test
	@Parameters({
		"John, VIP, true, 1",
	    "Alice, Normal, false, 0"
	})
	public void testGetExclReward(String name,String member_type,boolean ER,int ExclReward) {
		us =new User(name,member_type);
		us.setExclReward(ExclReward);
		boolean AR=us.getExclReward();
		assertEquals(ER,AR);
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters({"-1", "2", "5"})
	public void testSetExclRewardInvalid(int ExclReward) {
		us = new User("John", "VIP");
	    us.setExclReward(ExclReward);
	}

	
	


}
