package source;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abj
 */
import com.itextpdf.text.PageSize;
import java.util.ArrayList;
import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.PdfPCell;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.util.Calendar;
import java.text.DateFormatSymbols;


public class Print {
    
final String[] months = {"Januar", 
                        "Febuar", "Marts", "April", 
                        "Maj", "Juni", "Juli", "August", 
                        "September", "Oktober", "November", 
                        "December"};
    
    ArrayList<Boat> boatInfo;
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private PdfPTable table;
    private Document d;
    
    
    public Print(){
  
    }
    
    public void printResult(ArrayList<Boat> boatInfo){
        try{
             // Create file 
            FileWriter fstream = new FileWriter("Resultat.txt");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("#Nummer \t #Navn \t #Skipper \t #Tid 1 \t #Tid 2 \t #Placering \n");
            for(Boat b: boatInfo){
              out.write(b.getNumber() + "\t " + b.getName() + "\t " + b.getCaptain() + "\t " + b.getTime(0) + "\t " + b.getTime(1) + "\t " + b.getTimeDiff() + "\t " + b.getPlacement() + "\n");   
            }
            //Close the output stream
             out.close();
         }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
           }
   }

   private String getMonthandYear(){
      Calendar cal = Calendar.getInstance();
      String currentYear = Integer.toString(cal.get(Calendar.YEAR));
      String currentMonth = getMonthInWord(cal.get(Calendar.MONTH));
      System.out.println(currentMonth);
      return currentMonth + " " + currentYear; 

   }
   
   private String getMonthInWord(int numberOfMonth){
       System.out.println(numberOfMonth);
       return months[numberOfMonth];
   }
   public void printPdf(ArrayList<Boat> boatInfo){
       try{
       Document d = new Document(PageSize.A4, 50 , 50 , 50 , 50);
       
                PdfWriter pw = PdfWriter.getInstance(d, new FileOutputStream("Resultat.pdf"));
                d.open();
                Paragraph p = new Paragraph("Kapsejlads " + getMonthandYear(), catFont);
                p.setAlignment(Element.ALIGN_CENTER);
                d.add(p);
                d.add(Chunk.NEWLINE);
                
                
                table = new PdfPTable(7);
                
                PdfPCell r1 = new PdfPCell(new Phrase("Nummer"));
                r1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(r1);
                
                PdfPCell r2 = new PdfPCell(new Phrase("Navn"));
                r2.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(r2);
                
                PdfPCell r3 = new PdfPCell(new Phrase("Skipper"));
                r3.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(r3);
                
                PdfPCell r4 = new PdfPCell(new Phrase("Tid 1"));
                r4.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(r4);
                
                PdfPCell r5 = new PdfPCell(new Phrase("Tid 2"));
                r5.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(r5);
                
                PdfPCell r6 = new PdfPCell(new Phrase("Tidsforskel"));
                r6.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                table.addCell(r6);
                
                PdfPCell r7 = new PdfPCell(new Phrase("Placering"));
                r7.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(r7);
                
                
                
                for(int i = 0; i < boatInfo.size(); i++){
                    Boat b = boatInfo.get(i);
                    addCell(Integer.toString(b.getNumber()));
                    addCell(b.getName());
                    addCell(b.getCaptain());
                    addCell(Double.toString(b.getTime(0)));
                    addCell(Double.toString(b.getTime(1)));
                    addCell(Double.toString(b.getTimeDiff()));
                    addCell(Integer.toString(b.getPlacement()));
                    
                }
                table.setWidthPercentage(100);
                
                d.add(table);
                d.close();
                
                
                
                
                
                
                
          }
         catch (Exception e) {
             System.out.println(e + "FEJL E");
            }
       
       
   }
   
  
    private void addCell(String s){
        PdfPCell cell = new PdfPCell(new Phrase(s));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
   } 
   
   public void printRegs(ArrayList<Boat> boats){
       try{
            FileWriter fstream = new FileWriter("Registreringer.txt");
            BufferedWriter out = new BufferedWriter(fstream);
            for(Boat b: boats){
              out.write("#" + b.getNumber() + " #" + b.getName() + " #" + b.getCaptain() + "\n");   
            }
            out.close();
       }
       catch(Exception e){
           StackTraceElement[] s = e.getStackTrace();
           for(StackTraceElement esd : s){
               System.out.println(esd);
           }
  
       }
   }
   
   public ArrayList<Boat> readFile(){
         ArrayList<Boat> boats = new ArrayList<Boat>();
         try{
         FileInputStream fis = new FileInputStream("Registreringer.txt");
         DataInputStream dis = new DataInputStream(fis);
         BufferedReader br = new BufferedReader(new InputStreamReader(dis));
         String line;
         while((line = br.readLine()) != null){
            String[] strings = line.split("#");
            int number =  Integer.parseInt(strings[1].trim());
            String name = strings[2];
            String captain = strings[3];
            boats.add(new Boat(number, name, captain));
            }
         }
         catch(Exception e){
           StackTraceElement[] s = e.getStackTrace();
           for(StackTraceElement esd : s){
               System.out.println(esd);
           }
         }
         return boats;
       
   }
}
