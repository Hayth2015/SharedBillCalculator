import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Calculator {

	private List<BillItem> billItems = new ArrayList<BillItem>();
	private float sharedAmount;
	private List<Map<String, Map<String, String>>> payout = new ArrayList<Map<String, Map<String, String>>>();
	Map<String, String> debtorDetails = new HashMap<String, String>();

	public  Calculator(List<String> bills) {

		for(String bill_item:bills){

			billItems.add(new BillItem(bill_item));
		}
		System.out.println("There is " + billItems.size() + " bill items in total");
	}

	public void printBill() {

		//Map that contains a full list of who owes how much to whom
		//First Map is the list of creditors and their corresponding bill details
		//Second map contains the details of the attendees/debtors and their amount
		calculate();

		//System.out.println("Payout size: " + getPayout().size());
		System.out.println("***********************************");
		System.out.println("***********************************");

		for(Map<String,Map<String,String>> creditor:getPayout()){
			String creditorName = capitalize(creditor.keySet().toArray()[0].toString());
			Map<String,String> debtors = new HashMap<String, String>();
			System.out.println("****Creditor name: " + creditorName + "****");
			debtors = creditor.get(creditorName.toLowerCase());
			//System.out.println(debtors);
			for(Entry<String, String> debtor:debtors.entrySet()){

				String amount = debtor.getValue();
				
				Float amountFloat = Float.parseFloat(amount);
				DecimalFormat df = new DecimalFormat("0.00");
				df.setMaximumFractionDigits(2);
				String debtorName = capitalize(debtor.getKey());
				amount = df.format(amountFloat);

				System.out.println(debtorName + " pays " + creditorName + " " + amount);
			}
		}
	}

	private void calculate() {

		List<Map<String, Map<String, String>>> payoutConstruction = new ArrayList<Map<String, Map<String, String>>>();
		Map<String, Map<String, String>> newBillItemRecord = null;
		Map<String, String> tempDebtorDetails = new HashMap<String, String>();
		String paidBy = null;
		//Calculate and return a Map that contains a list of creditors 
		//with their corresponding debtors and amounts associated
		//Map<String, Map<String, String>>

		//Create the map from the defined 
		//private List<BillItem> billItems
		//int i = 0;
		for(BillItem billItem: billItems) {
			newBillItemRecord = new HashMap<String, Map<String, String>>();
			debtorDetails = new HashMap<String, String>();
			
			//i++;
			//Calculate amount per attendee
			setSharedAmount(billItem.getPrice() / billItem.getAttendees().size());
			//System.out.println("Bill item number: " + i + ", details:");
			//System.out.println("Total invoice: " + billItem.getPrice());
			//System.out.println("Paid by: " + billItem.getPaid_by());
			//System.out.println("Attendees: " + billItem.getAttendees().size());
			for(String attendee:billItem.getAttendees()) {
				//Check if the attendee is different the one who paid the bill
				if(attendee.toLowerCase() != billItem.getPaid_by().toLowerCase()
						&& !attendee.toLowerCase().equalsIgnoreCase(billItem.getPaid_by().toLowerCase())) {
					//Check if the key is repeated, then increment the value by the sharedAmount, Case if one invites another then he has to pay an  additional share
					if(debtorDetails.containsKey(attendee)) {
						debtorDetails.put(attendee, Float.toString(getSharedAmount()*2));
					} else {
						debtorDetails.put(attendee, Float.toString(getSharedAmount()));
					}
				}
				//System.out.println("Attendees: " + debtorDetails.toString());
			}
			
			tempDebtorDetails = debtorDetails;
			paidBy = billItem.getPaid_by();
			//System.out.println("Attendees with amount to be paid: " + debtorDetails.toString());

			//newBillItemRecord.clear();
			newBillItemRecord.put(paidBy, tempDebtorDetails);
			//System.out.println("***" + newBillItemRecord.toString());
			payoutConstruction.add(newBillItemRecord);
			//System.out.println("***" + payoutConstruction.toString());

			//System.out.println("********************************");
		}

		setPayout(payoutConstruction);

	}


	private String capitalize(final String line) {
		return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}

	public float getSharedAmount() {
		return sharedAmount;
	}

	public void setSharedAmount(float sharedAmount) {
		this.sharedAmount = sharedAmount;
	}

	public List<Map<String,Map<String,String>>> getPayout() {
		return payout;
	}

	public void setPayout(List<Map<String,Map<String,String>>> payout) {
		this.payout = payout;
	}



}
