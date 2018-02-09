import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BillItem {

	private float price;
	private String paid_by;
	private List<String> attendees = new ArrayList<String>();

	public BillItem(String row){

		List<String> data = Arrays.asList(row.split(" "));
		setPrice(Float.parseFloat(data.get(0)));
		setPaid_by(data.get(1).toLowerCase());
		//Parse the list of attendees
		for(String attendee: Arrays.asList(data.get(2).split(","))){
			attendees.add(attendee.toLowerCase());
		}
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getPaid_by() {
		return paid_by;
	}

	public void setPaid_by(String paid_by) {
		this.paid_by = paid_by;
	}
	
	public List<String> getAttendees() {
		return attendees;
	}

}
