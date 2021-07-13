import java.util.*;
import java.io.*;
public class io {
    public static void Clear() {
        try {
              FileWriter fwOb = new FileWriter("Results.txt", false);
              PrintWriter pwOb = new PrintWriter(fwOb, false);
              pwOb.flush();
              pwOb.close();
              fwOb.close();
        } catch (IOException e) {
        }
  }
  public static String input(){
      Scanner myObj = new Scanner(System.in);
      String Filenames = myObj.nextLine();
      myObj.close();
      return Filenames;

  }
  public static void PrintStats(String filename, int Stats[]) {
    try {
          FileWriter fWriter = new FileWriter("Results.txt", true);
          BufferedWriter br1 = new BufferedWriter(fWriter);
          int i = 0;
          br1.write(filename);
          br1.newLine();
          br1.write("                         Hits                            Miss");
          br1.newLine();
          br1.newLine();
          br1.write("L1                      " + Stats[i++] + "                           " + Stats[i++]);
          br1.newLine();
          br1.write("L2                      " + Stats[i++] + "                              " + Stats[i++]);
          br1.newLine();
          br1.write("L2 Partial              " + Stats[i] + "                             N.A");
          br1.newLine();
          br1.newLine();
          br1.newLine();
          br1.newLine();
          br1.newLine();
          br1.close();
    } catch (IOException e) {
          System.out.println(e);
        }
    }
    
}