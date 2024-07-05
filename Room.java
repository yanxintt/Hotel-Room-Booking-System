package BookingInformation;

public class Room {
	private int VIP;
	private int deluxe;
	private int standard;
	public boolean checkroom(String room_type) {//to check the availability of the room
		
		if(room_type=="VIP"&& VIP>0) {
			return true;
		}
		else if(room_type=="Deluxe"&& deluxe>0) {
			return true;
		}else if(room_type=="Standard"&& standard >0){
			return true;
		}else {
			return false;
		}
	}
	private int[] roomCounts = new int[3]; // Index 0: VIP, Index 1: Deluxe, Index 2: Standard
    
    public Room(int VIP, int Deluxe, int Standard) {
        roomCounts[0] = VIP;
        roomCounts[1] = Deluxe;
        roomCounts[2] = Standard;
    }
	public int getVIP() {
		return VIP;
	}
	public int getDeluxe() {
		return deluxe;
	}
	public int getStandard() {
		return standard;
	}
	public void bookVIP(int VIP) {
		this.VIP-=VIP;
	}public void bookDeluxe(int deluxe) {
		this.deluxe-=deluxe;
	}public void bookStandard(int regular) {
		this.standard-=standard;
	}
	
	

}
