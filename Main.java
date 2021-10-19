package com.jacobsewell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter the username: ");
    //Takes the user's input and concatenates it to form a URL
    String url = "https://www.ecs.soton.ac.uk/people/" + sc.next() + "#";

    URL address = new URL(url);

    String output = getName(address);
    //If an empty string is returned the user is informed that the user has not been found.
    System.out.println(((output.equals("")) ? "User not found" : output));
  }

  public static String getName(URL address) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(address.openStream()));
      String data;
      //Reads in the data from the webpage
      while ((data = in.readLine()) != null) {
        //This string appears just before the person's name. I use this to find the string that contains the name.
        if (data.contains("property=\"name\"")) {
          //I use the indexes of strings that appear around the person's name to create a substring which only contains the person's name
          data =
              data.substring(
                  data.indexOf("\"name\">") + 7, data.indexOf("<em property=\"honorificSuffix\">"));
          return data;
        }
      }
      in.close();
    } catch (Exception e) {
      //Catches any errors that may occur
      System.out.println("An error occured");
    }
    //If the name is not found an empty string is returned
    return "";
  }
}
