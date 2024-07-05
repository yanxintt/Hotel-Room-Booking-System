package BookingInformation;

public class User { //act as a datatype for waiting list to use
	private String name;
	private String member_type;
	private boolean excl_reward;
	
	public String getName() {
	    if (name == null || name.isEmpty() || containsNumeric(name)) {
	        throw new IllegalStateException("Name is null, empty, or contains numeric characters");
	    } else {
	        return name;
	    }
	}
	
	private boolean containsNumeric(String str) {
	    for (char c : str.toCharArray()) {
	        if (Character.isDigit(c)) {
	            return true;
	        }
	    }
	    return false;
	}
	public String getMemberType() {
	    if (member_type == null) {	 
	    	throw new IllegalStateException();
	    }	    
	    if (!member_type.equals("VIP") && !member_type.equals("Member") && !member_type.equals("nonMember")) {
	    	throw new IllegalStateException();
	    }	
	    return member_type;
	}

	public boolean getExclReward() {
		return excl_reward;
	}
	public User(String name,String member_type) {
		this.name=name;
		this.member_type=member_type;
		excl_reward=false;
	}
	public void setExclReward(int active) {
		if(active==0) {
			excl_reward=false;
		}else if(active==1) {
			excl_reward=true;
		}
		else {
	        throw new IllegalArgumentException("Invalid value for active. It should be 0 or 1.");
	    }
	}

}
