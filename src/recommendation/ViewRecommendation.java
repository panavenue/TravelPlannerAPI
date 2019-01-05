package recommendation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import entity.User;
import entity.Views;

public class ViewRecommendation {

	static class ListHourPair{
		double hoursRemain;
		List<Views> list;
		ListHourPair(double hoursRemain, List<Views> list){
			this.hoursRemain = hoursRemain;
			this.list = list;
		}
	}
	
	public static List<List<Views>> generatePath(User u, List<Views> listFromFront){
		int hoursPerDay = u.getHoursPerDay();
		int numberOfDays = u.getNumberOfDays();
		
		PriorityQueue<Views> pq = new PriorityQueue<Views>(new Comparator<Views>() {
			@Override
			public int compare(Views a, Views b) {
				if(a.getRate() == b.getRate()) {
					return 0;
				} else {
					return (int)(b.getRate() - a.getRate());
				}
			}
		});
		// add views from front-end to pq
		for(int i = 0; i < listFromFront.size(); i++) {
			if(listFromFront.get(i).getHoursNeeded() <= hoursPerDay) {
				pq.offer(listFromFront.get(i));
			}
		}
		List<ListHourPair> pairList = new ArrayList<>();
		// initialize pair list with hoursPerDay and empty List
		for(int i = 0; i < numberOfDays; i++) {
			pairList.add(new ListHourPair(hoursPerDay, new ArrayList<Views>()));
		}
		int indexOfPair = 0;
		// check availability and assign views to each day
		while(indexOfPair < pairList.size() && !pq.isEmpty()) {
			Views cur = pq.peek();
			if(cur.getHoursNeeded() <= pairList.get(indexOfPair).hoursRemain) {
				pairList.get(indexOfPair).list.add(pq.poll());
			} else {
				indexOfPair++;
			}
		}
		indexOfPair = 0;
		if(pq.isEmpty()) {
			return formResult(pairList);
		} else {
			// do the process again to fill more views
			while(indexOfPair < pairList.size() && !pq.isEmpty()) {
				Views cur = pq.peek();
				if(cur.getHoursNeeded() <= pairList.get(indexOfPair).hoursRemain) {
					pairList.get(indexOfPair).list.add(pq.poll());
				} else {
					indexOfPair++;
				}
			}
			return formResult(pairList);
		}
	}
	private static List<List<Views>> formResult(List<ListHourPair> pairList){
		List<List<Views>> res = new ArrayList<>();
		for(int i = 0; i < pairList.size(); i++) {
			if(pairList.get(i).list.size() != 0) {
				res.add(pairList.get(i).list);
			}
		}
		return res;
	}
}
