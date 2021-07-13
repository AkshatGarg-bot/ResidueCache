import java.util.*;
import java.io.*;
public class Encoding_Cache {
    private int Encode[][];
      private int size_2;
      private int index_bits, offset;

      Encoding_Cache(int cache_size, int n) {
            index_bits = (int) (Math.log(cache_size) / Math.log(2)); // calculate number of index bits
            this.size_2 = n / 2;
            this.offset = (int) (Math.log(size_2 / 2) / Math.log(2));
            Encode = new int[cache_size][size_2 + 1]; // 1-> if compressed 0-> if not compressed
      }

      String[] compress(String Address, String data) {
            int index = get_index(Address);

            int n = data.length();
            String comp_data = "";
            int i = 0, j = 0;
            int count = 0;
            for (i = 0, j = 0; i < n - 1 && j < size_2; i++, j++) {
                  comp_data += data.charAt(i);
                  if (data.charAt(i) == data.charAt(i + 1)) {
                        Encode[index][j] = 1;
                        i++;
                        count++;
                  } else {
                        Encode[index][j] = 0;
                  }
            }
            String[] p = new String[2];
            p[0] = comp_data;
            p[1] = data.substring(count + size_2);

            if (count == size_2) // all 1s
            {
                  Encode[index][size_2] = 1;
            } else {
                  Encode[index][size_2] = 0;
            }
            return p;
      }

      int is_CompleteinL2(String Address) {
            int index = get_index(Address);

            return Encode[index][size_2];
      }

      void set_CompleteinL2(String Address, int x) {
            int index = get_index(Address);
            Encode[index][size_2] = x;
      }

      int get_index(String Address) {
            int index = Integer.parseInt(
                        Address.substring(32 - index_bits - offset, 32 - index_bits - offset + index_bits), 2);
            return index;
      }

      String decompress(String Address, String comp_data) {
            int index = get_index(Address);

            String decom_data = "";
            for (int i = 0; i < size_2; i++) {
                  decom_data += comp_data.charAt(i);
                  if (Encode[index][i] == 1)
                        decom_data += comp_data.charAt(i);
            }
            return decom_data;
      }
}