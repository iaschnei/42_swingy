package com.iaschnei.app.model;

import java.io.FileWriter;
import java.io.File;
import java.io.RandomAccessFile;
//import org.json.JSONObject;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.util.Scanner;

/**
 * WriteRead
 * Utility class used to write and read to/from the text file
 */
public class WriteRead {

  File        saves_file;
  FileWriter  writer;
  Scanner     reader;
  JSONObject  json = new JSONObject();
  String      save_file_name;

  // Will create the file if it doesn't exist yet
  public void open_saves_file(String save_file_name) throws Exception{
    writer = new FileWriter(save_file_name);
    this.save_file_name = save_file_name;
    writer.close();
  }

  public void close_saves_file() throws Exception {
    writer.close();
  }

  public String get_current_data() throws Exception {
    saves_file = new File(save_file_name);
    reader = new Scanner(saves_file);

    String data = new String();

    while (reader.hasNextLine()) {
      data += reader.nextLine();
    }

    reader.close();
    return (data);
  }

  public void add_json_entry(String key, String content) throws Exception{
    erase_saves_file();
    writer = new FileWriter(save_file_name, true);
    json.put(key, content);
    writer.write(json.toString());
    writer.close();
  }

  public String get_json_entry(String key) throws Exception{

    String current_data = get_current_data();

    if (current_data == null || current_data.isEmpty()) {
        throw new Exception("Current data is null or empty");
    }

    Object  file_data = JSONValue.parse(current_data);

    if (file_data == null) {
        throw new Exception("Failed to parse JSON: " + current_data);
    }

    JSONObject decode_file_data = (JSONObject) file_data;

    String value = (String)decode_file_data.get(key);

    return (value);
  }

  public void remove_json_entry(String key) throws Exception {
    String current_data = get_current_data();

    Object  file_data = JSONValue.parse(current_data);

    JSONObject new_file_data = (JSONObject) file_data;

    new_file_data.remove(key);

    erase_saves_file();

    writer.write(new_file_data.toString());
  }

  public void replace_json_entry(String key, String new_content) throws Exception {
    remove_json_entry(key);
    add_json_entry(key, new_content);
  }

  public void erase_saves_file() throws Exception {
    RandomAccessFile file = new RandomAccessFile(save_file_name, "rw");
    file.setLength(0);
    file.close();
  }
}
