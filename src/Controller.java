import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
/*
 * TO DO: 
 *
Printe til PDF
Eksekvebar fil


 */
public class Controller{
	
	int boats;
	public ArrayList<Boat> boatList;
	Registrering reg;
        NewTimeWindow tw;
        String[] boatNames;
        Print print;
        
	public Controller(){
		
		boatList = new ArrayList<Boat>();
                reg = new Registrering(this);
                reg.setVisible(true);
                print = new Print();
                
	}
	
	public void createBoats(int boats){
		for(int i = 0; i < boats; i++){
			//Boat b = createBoat(gui.boatId(0), gui.getName(i), gui.getCaptain(i));
			//boatList.add(b);
		}
	}
	
	public void createBoat(int number, String name, String captain){
		Boat boat = new Boat(number, name, captain);
		boatList.add(boat);
	}
	
        public void closeReg(boolean b){
            if(b)
                printRegs();
            reg.dispose();
            openTimeWindow();
        }
        
        public void printRegs(){
           print.printRegs(boatList);
        }
        public void openTimeWindow(){
            parseRegs();
            insertBoatNames();
            tw = new NewTimeWindow(boatNames, this);
            tw.setVisible(true);
        }
        
        public void insertBoatNames(){
            boatNames = new String[boatList.size()];
            int count = 0;
            for(Boat b: boatList){
                String tmpName = b.getName();
                String tmpNumber = Integer.toString(b.getNumber());
                String s = tmpNumber + " - " + tmpName;
                boatNames[count]= s;
                count++;               
                
            }
        }
        
        public boolean isTimeRunning(int i){
            Boat b = boatList.get(i);
            return b.isTimeRunning();
        }
        
        public boolean TimeOnBoat(int i){
            if(boatList.get(i).isTimeRunning()){
                String s = boatList.get(i).stopTime();
                return false;
            }
            else
                boatList.get(i).startTime();
                return true;
        }
        
        public void printTime(){
            String s = checkBoats();
            findWinner(boatList);
            print.printPdf(boatList);
            
        }
        
        public void findWinner(ArrayList<Boat> boats){
            for(Boat b : boats){
                b.findTimeDiff();
            }
            Collections.sort(boats);
            int count = 1;
             for(int i = 0; i < boats.size() ; i++){
                Boat b = boats.get(i);
                b.setPlacement(count++);
                System.out.println(b.getNumber() + " Name: " + b.getName() + " TimeDiff: " + b.getTimeDiff() + " time1: " + b.getTime(0) + " time2: " + b.getTime(1) + " Placering: " + b.getPlacement());
            }
        }
        
        public void findWinner(){
            findWinner(boatList);
        }
        
        public String[][] convertBoatList(){
            String[][] boatArr = new String[boatList.size()][5];
            int count = 0;
            for(Boat b : boatList){
                    boatArr[count][0] =  Integer.toString(b.getNumber());
                    boatArr[count][1] = b.getName();
                    boatArr[count][2] = b.getCaptain();
                    if(b.getTime(0) == 0.000847329)
                        boatArr[count][3] = "Ikke startet";
                    else
                        boatArr[count][3] =  Double.toString(b.getTime(0));
                    
                    if(b.getTime(1) == 0.000847329)
                        boatArr[count][4] = "Ikke startet";
                    else
                        boatArr[count][4] =  Double.toString(b.getTime(1));
               
                    count++;
                }
            return boatArr;
        }
        
        public String checkBoats(){
            for(Boat boat : boatList){
                boolean b = timeOnBoat(boat);
                if(!b){
                    String s = "Du har ikke startet og stoppet tiden på alle både";
                    return s;
                }
           }
           return "Alle tider stoppet";
        }
        
        private boolean timeOnBoat(Boat boat){
            double time1;
            double time2;
            try{
            time1 = boat.getTime(0);
            }
            catch(IndexOutOfBoundsException e){
                time1 = 9999;
            }
            try{
                time2 = boat.getTime(0);
            }
            catch(IndexOutOfBoundsException e){
                time2 = 9999;
            }
                
            if(time1 == 0.0 || time2 == 0.0)
                return false;
            else
                return true;
           
        }
        
        private void parseRegs(){
           boatList = print.readFile();
        }
        
       
	public static void main(String[] args) {
		Controller ct = new Controller();
	}

}
