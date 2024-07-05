 package BookingInformation;
import java.util.*;
public class WaitingList {
	private List<User> VIP = new ArrayList<>();
    private List<User> member = new ArrayList<>();
    private List<User> nonMember = new ArrayList<>();

    public void addWaiting(User user) {
        switch (user.getMemberType()) {
            case "VIP":
                VIP.add(user);
                break;
            case "Member":
                member.add(user);
                break;
            default:
                nonMember.add(user);
                break;
        }
    }

    public User getWaiting(User user) {
        List<User> userList = null;
        switch (user.getMemberType()) {
            case "VIP":
                userList = VIP;
                break;
            case "Member":
                userList = member;
                break;
            default:
                userList = nonMember;
                break;
        }

        if (userList != null) {
            for (User data : userList) {
                if (data.getName().equals(user.getName())) {
                    return data;
                }
            }
        }

        return null;
    }
    
	public void removeWaiting(User user) {
		if (user.getMemberType().equals("VIP")) {
			for (int i=0;i<VIP.size();i++) {
			 User data=VIP.get(i);
			 if (data.getName()==user.getName()) {
				 VIP.remove(i);
			 }
		}
		}
		else if (user.getMemberType().equals("Member")) {
			for (int i=0;i<member.size();i++) {
			 User data=member.get(i);
			 if (data.getName()==user.getName()) {
				member.remove(i);
			 }
		}
		}else {
			for (int i=0;i<nonMember.size();i++) {
				 User data=nonMember.get(i);
				 if (data.getName()==user.getName()) {
					 nonMember.remove(i);
				 }
			}
			}
	}
	

	
	public List <User> getVIP() {
		return VIP;
	}
	public List <User>  getDeluxe() {
		return member;
	}
	public List <User> getStandard() {
		return nonMember;
	}
	

}
