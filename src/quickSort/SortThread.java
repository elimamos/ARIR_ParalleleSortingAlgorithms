package quickSort;

import java.util.ArrayList;
import java.util.Random;
/*
    Automatic generated threads quicksort
*/
public class SortThread extends Thread {

	private final ArrayList<Double> data;
	private final ArrayList<Double> sorted;

	public SortThread(ArrayList<Double> data) {
		super();
		this.data = data;
		sorted=new ArrayList<>();
	}
	public ArrayList<Double> getSorted(){
		return sorted;
	}
	



	@Override
	public void run() {

		//System.out.println(data.size());
		
		
		if(data.size()==1){
			
			sorted.add(data.get(0));
			//System.out.println(data.get(0));
		}else{

			Double pivot = getPivot();

			ArrayList<Double> smaller = new ArrayList<>();
			ArrayList<Double> bigger = new ArrayList<>();


			for (Double d:data){
				if(d<pivot){
					smaller.add(d);
				}
				else{
					bigger.add(d);
				}
			}
			
			
			SortThread sThread=null;
			SortThread bThread=null;

			
			
			try {
				if (smaller.size()>0){
					sThread =new SortThread(smaller);
					sThread.start();
				}
				
				if(bigger.size()>0){
					bThread =new SortThread(bigger);
					bThread.start();
				}
				
				if(sThread!=null){
					sThread.join();
					sorted.addAll(sThread.getSorted());
                                        
				}
				if(bThread!=null){
					bThread.join();
					sorted.addAll(bThread.getSorted());
				}
				
				
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}




	}
	private Double getPivot(){
		int i = new Random().nextInt(data.size());
		return data.get(i);


	}



}
