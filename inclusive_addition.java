import java.util.*;
import java.io.*;
public class inclusive_addition {
    public static void add_in_l2(DM l1_cache, DM l2_cache, DM res_cache, String data, String Address) {

        String rem_tag = l2_cache.get_tag(Address);
        if (rem_tag != "") {

              int idx = l2_cache.get_index(Address);
              int l2_indexBits = (int) (Math.log(Residue.l2_sets) / Math.log(2));
              String l2_idx = Integer.toBinaryString(idx);
              int zeros = l2_indexBits - l2_idx.length();
              if (zeros > 0) {
                    String z = "0";
                    for (int q = 0; q < zeros - 1; q++) {
                          z = z.concat("0");
                    }
                    l2_idx = z.concat(l2_idx);
              }
              String rem_address = rem_tag.concat(l2_idx);
              if (res_cache.is_hit(rem_address)) {
                    res_cache.Evict(rem_address);
              }
              if (l1_cache.is_hit(rem_address)) {
                    l1_cache.Evict(rem_address);
              }
        }
        l2_cache.add_Data(Address, data);
  }
}