package phoneBillCalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class PhoneBillCalculator {

	public static Set<String> findDuplicates(List<String> listContainingDuplicates) {
		final Set<String> setToReturn = new TreeSet<String>();
		final Set<String> set1 = new TreeSet<String>();
		for (String yourInt : listContainingDuplicates) {
			if (!set1.add(yourInt)) {
				setToReturn.add(yourInt);
			}
		}
		return setToReturn;
	}

	public static void main(String[] args) throws FileNotFoundException {

		double cost = 0;
		double totalCost = 0;
		double addedCost = 0;
		int remainingMinutes = 0;
		int noOfSeconds1, noOfSeconds2, difference, differenceInminutes = 0;

		String higherNumber = null;
		String[] splittedInput, startTimeSplit, endTimeSplit, s1, s2 = null;
		String startTime, EndTime, phoneNumber = null;
		Scanner sc = new Scanner(new File("C:\\Users\\142958\\OneDrive - Garrett Advancing Motion\\Documents\\PhoneBillCalculator\\generated_sample.CSV"));  
		sc.useDelimiter(",");   //sets the delimiter pattern  
		List<String> inputs = new ArrayList<>();
		while (sc.hasNext())  //returns a boolean value  
		{  
			inputs.add(sc.nextLine());
		//System.out.print(sc.next());  //find and returns the next complete token from this scanner  
		}  
		List<String> phoneNumbers = new ArrayList<>();
		List<String> dummy = new ArrayList<>();
		
		for (String input : inputs) {
			splittedInput = input.split(",");
			phoneNumber = splittedInput[0];
			phoneNumbers.add(phoneNumber);
		}
		Set<String> duplicatePh = findDuplicates(phoneNumbers);
		if(duplicatePh.size()>1) {
			int size = duplicatePh.size();
			
			for(String str : duplicatePh) {
				dummy.add(str);
			}
			higherNumber = dummy.get(size-1);
		}
		System.out.println(higherNumber);

		for (String input : inputs) {
			remainingMinutes = 0;
			splittedInput = input.split(",");
			phoneNumber = splittedInput[0];
			startTime = splittedInput[1];
			EndTime = splittedInput[2];
			System.out.println("Phone Number "+phoneNumber);
			if (phoneNumber.equals(higherNumber)) {
				totalCost+=0;
			} else {
				startTimeSplit = startTime.split(" ");
				endTimeSplit = EndTime.split(" ");

				s1 = startTimeSplit[1].split(":");
				s2 = endTimeSplit[1].split(":");
				if(s1[0].equals("00")) {
					s1[0] = "24";
				}
				if(s2[0].equals("00")) {
					s2[0] = "24";
				}

				noOfSeconds1 = Integer.parseInt(s1[0]) * 60 * 60 + Integer.parseInt(s1[1]) * 60
						+ Integer.parseInt(s1[2]);
				noOfSeconds2 = Integer.parseInt(s2[0]) * 60 * 60 + Integer.parseInt(s2[1]) * 60
						+ Integer.parseInt(s2[2]);

				difference = noOfSeconds2 - noOfSeconds1;
				differenceInminutes = difference / 60;

				if (Integer.parseInt(s1[0]) >= 8 && Integer.parseInt(s1[0]) < 16) {
					cost = 1;
				} else {
					cost = 0.5;
				}

				if (differenceInminutes > 5) {
					remainingMinutes = differenceInminutes - 5;
					differenceInminutes = differenceInminutes - remainingMinutes;
					addedCost = 0.2;
				}
			}

			totalCost += differenceInminutes * cost + remainingMinutes * addedCost;
			System.out.println("TotalCost "+totalCost);
		}
		System.out.println(totalCost);
	}

}
