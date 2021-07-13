import java.util.*;
import java.io.*;
public class Residue {
      public static final int l1_sets = 64;
      public static final int l2_sets = 512;
      public static final int DataSizeinbyte = 32;   
      public static final String DIR_path = "trace_files/"; 
      public static void main(String[] args) {
            try {
                  io.Clear();
                  String Filenames = io.input();
                  String[] files = Filenames.split(" ");
                  int numOfFiles = Integer.parseInt(files[0]);
                  if(numOfFiles - files.length != -1 )
                  {
                        System.out.println("Please enter the correct file number");
                        return;
                  }
                  for (int i = 1; i < numOfFiles + 1; i++) {
                        Map<String, String> memory = new HashMap<String, String>();
                        String p = DIR_path + files[i];
                        int l1_cachehits = 0, l1_cachemiss = 0, l2_cachemiss = 0, l2_cachehits = 0, l2_partialhit = 0;
                        DM l1_cache = new DM(l1_sets * DataSizeinbyte * 8, DataSizeinbyte * 8);
                        DM l2_cache = new DM(l2_sets * (DataSizeinbyte / 2) * 8, (DataSizeinbyte / 2) * 8);
                        Encoding_Cache e = new Encoding_Cache(l2_sets, DataSizeinbyte * 2);
                        DM residue_cache = new DM(l2_sets / 4 * (DataSizeinbyte / 2) * 8, (DataSizeinbyte / 2) * 8);

                        File file = new File(p);
                        FileReader fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);
                        String line;
                        while ((line = br.readLine()) != null) {
                              String[] tokens = line.split(" ");
                              String Address = tokens[0].substring(2, 10);
                              String data = tokens[1].substring(2);
                              Address = convert(Address);
                              memory.put(Address, data);

                              if (l1_cache.is_hit(Address)) {

                                    l1_cachehits++;
                              } else {
                                    l1_cachemiss++;
                                    if (residue_cache.is_hit(Address)) {
                                          l2_cachehits++;
                                          String d = e.decompress(Address, l2_cache.get_Data(Address))
                                                      + residue_cache.get_Data(Address);
                                          if (data.equals(d) == false) {
                                                System.out.println("NOT=");
                                                break;
                                          }

                                    } else {
                                          if (l2_cache.is_hit(Address)) {

                                                if ((e.is_CompleteinL2(Address) == 1)) {
                                                      l2_cachehits++;
                                                      String d = e.decompress(Address, l2_cache.get_Data(Address))
                                                                  + residue_cache.get_Data(Address);
                                                      if (data.equals(d) == false) {
                                                            System.out.println("NOT=");
                                                            break;
                                                      }
                                                } else
                                                      l2_partialhit++;
                                          } else {
                                                l2_cachemiss++;

                                                l1_cache.add_Data(Address, data);

                                                String encode[] = e.compress(Address, data);

                                                String compressed_data = encode[0];

                                                String residue_data = encode[1];
                                                inclusive_addition.add_in_l2(l1_cache, l2_cache, residue_cache, compressed_data,
                                                                  Address);
                                                residue_cache.add_Data(Address, residue_data);

                                          }
                                    }
                              }
                        }

                        int Stats[] = { l1_cachehits, l1_cachemiss, l2_cachehits, l2_cachemiss, l2_partialhit };
                        io.PrintStats(files[i], Stats);
                  }
            } catch (IOException e) {
                  System.out.println(e + "File Not present please enter the correct file name");
            }
      }



 
      public static String convert(String s) {
            long num = Long.parseLong(s, 16);
            String binary = Long.toBinaryString(num);
            binary = ("00000000000000000000000000000000" + binary).substring(binary.length());
            return binary;
      }

      
}
