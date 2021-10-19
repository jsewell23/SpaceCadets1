package com.jacobsewell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter the username: ");

    String url = "https://www.ecs.soton.ac.uk/people/" + sc.next() + "#";
    URL address = new URL(url);

    String output = getName(address);

    System.out.println(((output.equals("")) ? "User not found" : output));
  }

  public static String getName(URL address) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(address.openStream()));
      String data;
      while ((data = in.readLine()) != null) {
        if (data.contains("property=\"name\"")) {
          data =
              data.substring(
                  data.indexOf("\"name\">") + 7, data.indexOf("<em property=\"honorificSuffix\">"));
          return data;
        }
      }
      in.close();
    } catch (Exception e) {
      System.out.println("An error occured");
    }
    return "";
  }
}
