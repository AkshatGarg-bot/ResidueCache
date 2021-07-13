import java.util.*;
import java.io.*;
public class DM {
    private int Size, Block_size, rows, index_bits, tag_bits;
      // private
      public DM_row[] cache; // Array of DM_rows

      DM(int size, int block_size) {

            this.Size = size;

            this.Block_size = block_size;

            rows = (Size / Block_size); // calculate number of rows

            index_bits = (int) (Math.log(rows) / Math.log(2)); // calculate number of index bits

            tag_bits = 32 - index_bits - (int) (Math.log(block_size / 8) / Math.log(2)); // calculate number of tag
                                                                                         // bits.
            cache = new DM_row[rows]; // Size of "cache array" will be same as number of rows.

            for (int i = 0; i < rows; i++)
                  cache[i] = new DM_row();
      }

      // Access at the given address
      boolean is_hit(String Address) // Returns true on a Hit , otherwise returns false.
      {
            String tag = Address.substring(0, tag_bits); // get the tag.
            int index = get_index(Address);
            boolean is_hit; // set to true on a hit otherwise set to false.

            if (cache[index].getValid_bit() == 1 && cache[index].getTag().equals(tag))
                  is_hit = true;
            else
                  is_hit = false;

            return is_hit;
      }

      void Evict(String Address) {
            int index = get_index(Address);
            cache[index].setValid_bit(0);
            cache[index].setData("");
      }

      void add_Data(String Address, String data) {
            String tag = Address.substring(0, tag_bits); // get the tag.
            int index = get_index(Address);
            cache[index].set_tag(tag);
            cache[index].setData(data);
      }

      String get_Data(String Address) {
            int index = get_index(Address);
            return cache[index].getData();
      }

      String get_tag(String Address) {
            int index = get_index(Address);
            return cache[index].getTag();
      }

      int get_index(String Address) {
            int index = Integer.parseInt(Address.substring(tag_bits, tag_bits + index_bits), 2);
            return index;
      }
    
}