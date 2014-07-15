package source;


import java.util.ArrayList;


public class Boat implements Comparable<Boat>{

	private int number, placement;
	private String name, captain;
	private ArrayList<Stopwatch> swList;
	private ArrayList<Double> timeList;
	private int stopCount, first;
	private double timeDiff;
        public boolean timeIsRunning, foundTimeDiff;
        private boolean firstLap;
	
	
	public Boat(int number, String name, String captain){
		this.number = number;
		this.name = name;
		this.captain = captain;
		swList = new ArrayList<Stopwatch>();
		timeList = new ArrayList<Double>(2);
		stopCount = 0;
                timeIsRunning = false;
                firstLap = true;
                foundTimeDiff = false;
                first = 0;
	}
	
	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getCaptain() {
		return captain;
	}
        
        public double getTimeDiff(){
            return timeDiff;
        }
        
        public double getTime(int i){
            if(timeList.isEmpty())
                return 0.000847329;
            else
                return timeList.get(i);
       
        }
        
        public boolean isTimeRunning(){
            return timeIsRunning;
        }
        
        public boolean firstLap(){
            return firstLap;
        }
        
        public void setPlacement(int i){
            placement = i;
        }
        
        public int getPlacement(){
            return placement;
        }
        
        public boolean getFoundTimeDiff(){
            return foundTimeDiff;
        }

	@Override
	public int compareTo(Boat boat) {
		double timeDiff = boat.findTimeDiff();
		if(this.timeDiff > timeDiff)
			return 1;
		if(this.timeDiff < timeDiff)
			return -1;
		
		else 
			return 0;
	}
	
	public void startTime(){
		Stopwatch sw = new Stopwatch();
		swList.add(sw);
                timeIsRunning = true;
                    if(first == 0)
                        firstLap = true;
                    else
                        firstLap = false;
                
                    first = 1;
	}
	
	public String stopTime(){
		Stopwatch sw = swList.get(stopCount);
                double elapsedTime = sw.elapsedTime();
                System.out.println("TIME : " + elapsedTime);
		double time = roundTwoDecimals(elapsedTime);
		timeList.add(time);
		stopCount++;
                timeIsRunning = false;
                String s = "";
                return s;
	}
        
        private double roundTwoDecimals(double d) {
            double minutes = d/60;
            d = (double) Math.round(minutes*1000)/1000;
            System.out.println(minutes);
            /*
            if(minutes < 1)
                minutes = 0;
            else{
                minutes = Math.floor(minutes);
                System.out.println("minutes :" + minutes);
            } 
            
            double seconds = d%60;
            System.out.println("seconds :" + seconds);
            seconds = Math.round(seconds);
            seconds = seconds/100;
            
            d = minutes + seconds;
            
            System.out.println("Minutes :" + minutes + " Seconds : " + seconds);
            */
            System.out.println("Time " + d);
           
          
           return d;
           
}
        
	
	public double findTimeDiff(){
		Double time1 = timeList.get(0);
		Double time2 = timeList.get(1);
		if(time1 == null || time2 == null){
			timeDiff = 99999;
			return timeDiff;
		}
		timeDiff = Math.abs(time1-time2);
                System.out.println(timeDiff);
                System.out.println(timeDiff*1000);
                timeDiff = timeDiff * 1000;
                System.out.println(Math.round(timeDiff));
                timeDiff = Math.round(timeDiff);
                System.out.println(timeDiff/1000);
                timeDiff = timeDiff/1000;
                //timeDiff = (Math.round(timeDiff*10000))/10000;
                System.out.println(timeDiff);
                foundTimeDiff = true;
		return timeDiff;
	}

}
