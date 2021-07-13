import java.util.*;
import java.io.*;
public class DM_row {
    private int valid_bit;
    private String tag, data;

    DM_row() // ConAddressuctor.
    {
          valid_bit = 0; // Set valid_bit to 0 initially.
          data = "";
          tag = "";
    }

    // Getters for valid_bit and tag.
    int getValid_bit() {
          return valid_bit;
    }

    void setValid_bit(int v) {
          valid_bit = v;
    }

    String getTag() {
          return tag;
    }

    void set_tag(String t) {
          if (valid_bit == 0) // Only happens for first access.
                valid_bit = 1;

          tag = t;
    }

    void setData(String d) {
          data = d;
    }

    String getData() {
          return data;
    }
    
}