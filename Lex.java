//Kyler Kiminki
//kkiminki
//cmps101
//Lex.java
//Lexigraphically orders the lines from

import java.util.Scanner;
import static java.lang.System.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

class Lex{

   //Main function
   public static void main(String [] args)throws IOException{
      if(args.length != 2){
         throw new RuntimeException(
            "USAGE_ERROR: java Lex [input_file] [output file]");
      }
      int count = getCount(args[0]);
      String [] SArray = new String[count];
      String [] copy = new String[count];
      SArray = getArray(args[0], count);
      copy = getArray(args[0], count);
      List list = new List();
      list = Sort(copy);
      output(list, SArray, args[1]);
   }

   //Gets the number of lines in a file
   static int getCount(String File)throws IOException{
      BufferedReader in = new BufferedReader(
         new FileReader(File));
      int i = 0;
      String line;
      while(true){
        i++;
        line = in.readLine();
        if(line == null)
           break;
      }
      return i;
   }

   //Puts the contents of the file into a string array
   static String[] getArray(String File, int count)throws IOException{
      BufferedReader in = new BufferedReader(
         new FileReader(File));
      String [] SArray = new String[count];
      for (int i = 0; i < count; i++){
         SArray[i] = in.readLine();
      }
      return SArray;
   }
   
   //Sorts the array and puts the proper indicies in lexigraphic
   //order into the list
   static List Sort(String[] S){
      List list = new List();
      int [] order = new int[S.length];
      int j;
      list.append(0);
      list.moveFront();
      for(int i =1; i < S.length - 1; i++){
         list.moveFront();
         while(S[list.get()].compareTo(S[i]) < 0){
            list.moveNext();
            if(list.index() == -1)
               break;
         }
         if(list.index() == -1){
            list.append(i);
         }else{
            list.insertBefore(i);
         }
      }
      return list;
   }
   
   //Writes the result of the sorted list into the output file
   static void output(List L, String[] S, String FileName)throws IOException{
      File file = new File(FileName);
      FileOutputStream fos = new FileOutputStream(file);
      PrintStream ps = new PrintStream(fos);
      PrintStream console = System.out;
      System.setOut(ps);
      L.moveFront();
      for(int i = 0; i < L.length(); i++){
         System.out.println(S[L.get()]);
         L.moveNext();
      }
      System.setOut(console); 
   }
}
