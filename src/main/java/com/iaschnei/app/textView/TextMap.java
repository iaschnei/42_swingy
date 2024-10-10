package com.iaschnei.app.textView;

import com.iaschnei.app.model.WriteRead;

/**
 * TextStartup
 */
public class TextMap {

  public static void display_map(WriteRead save_reader, int map_size) throws Exception {

    String map = save_reader.get_json_entry("Map");

    for (int i = 0; i < map.length(); i += map_size) {
      String map_line = map.substring(i, Math.min(i + map_size, map.length()));


      for (int index = 0; index < map_line.length(); index++) {
        System.out.print(map_line.charAt(index));
        System.out.print(" ");
      }
      System.out.print("\n");
    }
  }
}
