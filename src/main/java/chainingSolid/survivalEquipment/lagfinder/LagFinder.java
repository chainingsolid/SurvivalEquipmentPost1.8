package chainingSolid.survivalEquipment.lagfinder;

import java.util.ArrayList;

public class LagFinder implements Runnable{
	
	
	public Thread trackedThread;
	
	public long startTime;
	
	public static long runningTime = 100000;
	public static long samplingRate = 100;
	
	public ArrayList<String> samples = new ArrayList<String>();
	
	public LagFinder(Thread thread){
		trackedThread = thread;
	}
	
	
	@Override
	public void run() {
		startTime = System.currentTimeMillis();
		while(startTime + runningTime < System.currentTimeMillis()){
			StackTraceElement[] stackElements= trackedThread.getStackTrace();
			String data = "";
			for(StackTraceElement element : stackElements){
				data = data + "class: "+element.getClassName() + "\nmethod:"+element.getMethodName();
			}
			samples.add(data);
			try {
				Thread.sleep(samplingRate);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("sample data");
		for(String info : samples){
			System.out.println(info);
		}
	}
	
	
	
	
}
