import java.util.*;
import java.io.*;

public class BookTester
{
  //arraylist that will store the record elements that are in the file
  private static ArrayList<LibraryBook> record = new ArrayList<LibraryBook>();

  public static void main(String[] args)
  {
    //Post: This is the main method of the program
    //This method will call allow the user to enter the name of a file to use in their program
    //The program will enter and sort those values into an arraylist
    //The program brings up the main menu
    
    Scanner input = new Scanner(System.in);
    
    //Choose ".dat" a file to use for the program
    clearScreen();
    openFile();
    System.out.print("Which file would you like to use:");
    String user1 = input.nextLine();
    
    //Store and sort the values into an arraylist. Bring up the main menu
    clearScreen();
    int numBooks = inputBook(user1);
    bookSort(numBooks);
    menu(numBooks);
  }
  
  public static void menu(int numBooks)
  {
      //Post: This method prints out the main menu and prompts the user to choose an option
      
      Scanner input = new Scanner(System.in);
      boolean valid = false;
      int number = numBooks;
  
      System.out.println("");
      System.out.println("Welcome to the Great Books Program!");
      System.out.println("--------------------------");
      System.out.println("| 1. Display All Records |");
      System.out.println("| 2. Search for a Title  |");
      System.out.println("| 3. Exit the program    |");
      System.out.println("--------------------------");
      System.out.println("");
      System.out.print("Please select an option above: ");
      
      //This loop will continue as long as the inupt the user enters is NOT valid
      while(valid == false)
      {
          int menuSel = input.nextInt();
          
          if(menuSel == 1)
          {
              //calls displayRecords method
              displayRecords(number);
              valid = true;
          }
          else if(menuSel == 2)
          {
              //calls bookSearch method
              bookSearch(number);
              valid = true;
          }
          else if (menuSel == 3)
          {
              //Exits the program;
              clearScreen();
              System.out.println("Thank you for using the Great Books Program!!! Have a nice Day!!!");
              System.out.println("");
              valid = true;
          }
          else
          {
              //Invalid input
              System.out.println("Invalid input. Please try again: ");
          }
      }
  }
  
  public static void displayRecords(int numBooks)
  {
      //Post: this method uses a loop to display all  of the records one at a time and prompts the user for input to continue the loop
  
      int number = numBooks;
      Scanner input = new Scanner(System.in);
      
      for(int i = 0;i<number;i++)
      {
          clearScreen();
          printRecord(i);    //calls the printRecord method
          System.out.println("Press \"enter\" to continue. Press M to go back to the menu...");
          String enter = input.nextLine();
          if ((enter.compareTo("M")) == 0 || (enter.compareTo("m")) == 0)
              break;
      }
      clearScreen();
      menu(number);
  }
  
  public static void printRecord(int position)
  {
      //Post: This method prints a record
  
      System.out.println("Book Record #" + (position+1));
      System.out.println("--------------------------------------");
      System.out.println("Title: " + (record.get(position).getTitle()));
      System.out.println("Author: " + (record.get(position).getAuthor()));
      System.out.println("Copyright: " + (record.get(position).getCopyright()));
      System.out.println("Price: " + (record.get(position).getPrice()));
      System.out.println("Genre: " + (record.get(position).getGenre()));
      System.out.println("--------------------------------------");
  }
  
  public static void bookSearch(int numBooks)
  {
      //Post: This method searches for a book and prints it to the screen
  
      int number = numBooks;
      boolean found = false;
      Scanner input = new Scanner(System.in);
      ArrayList<LibraryBook> record2 = new ArrayList<LibraryBook>();
      
      clearScreen();

      System.out.print("Enter the title of the book to search: ");
      String bookEnter = input.nextLine();
      String authorEnter = "n/a";
      int crightEnter = 0000;
      double priceEnter = 0.00;
      String genreEnter = "n/a";
      record2.add(new LibraryBook(bookEnter, authorEnter, crightEnter, priceEnter, genreEnter));
      
      //This loop will continue as long as: a) input is invalid  b) the user want to return to the menu
      while(found == false)
      {
          //This is the search loop
          for(int i = 0;i<number;i++)
          {
              //Print the record if it is found
              if((record.get(i).getTitle().compareTo(record2.get(0).getTitle())) == 0)
              {
                  found = true;
                  clearScreen();
                  printRecord(i);
                  System.out.println("Press \"enter\" to return to menu...");
                  String enter = input.nextLine();
                  break;
              }
          }
          
          //If record is not found, input is invalid
          if(found == false)
          {
              System.out.println("Input is invalid. Press \"enter\" to continue. Press M to go back to the menu...");
              String enter = input.nextLine();
              if ((enter.compareTo("M")) == 0 || (enter.compareTo("m")) == 0)
                  break;
          }
      }
      clearScreen();
      menu(number);
  }
  
  public static void openFile()
  {
      //Post: This method lists the ".dat" files in our directory
  
      //Get all files from directory
      File curDir = new File(".");
      String[] fileNames = curDir.list();
      ArrayList<String> data = new ArrayList<String>();

      //Find files which may have data. (aka, are in the .dat format)
      for(String s:fileNames)
      {
          if(s.endsWith(".dat"))
              data.add(s);
      }
      
      for(int i = 0;i<data.size();i++)
          System.out.print(data.get(i) + "\t");
      System.out.println("");
  }
  
  public static int inputBook(String user)
  {
      //Post: This method fills the arraylist with the record elements and returns the number of books we have
  
      int numBooks = 0;
      try
      {
        Scanner sc = new Scanner(new File(user));
        
        //This loop will continue as long as there is content in the file used for the program
        while(sc.hasNext())
        {
           Scanner sc2 = new Scanner(sc.nextLine()).useDelimiter(";");
           String title = sc2.next();
           String author = sc2.next();
           int copyright = sc2.nextInt();
           double price = sc2.nextDouble();
           String genre = sc2.next();
           record.add(new LibraryBook(title, author, copyright, price, genre));
           numBooks++;
        }
      }
      catch(IOException e)
      {
        System.out.println(e.getMessage());
      }
    
    return numBooks;
  }

  public static void bookSort(int numBooks)
  {
      //Post: This method sorts the arraylist
      
      int min;
      for(int i = 0;i<numBooks-1;i++)
      {
          min = i;
          for(int j = (min+1); j <numBooks;j++)
          {
              if((record.get(j).getTitle().compareTo(record.get(min).getTitle())) < 0)
                min = j;
          }
          
          if(min != i)
          {
            LibraryBook temp = record.get(i);
            record.set(i, record.get(min));
            record.set(min, temp);
          }
      }
  }
  
  private static void clearScreen()
  {
      //Post: This method clears the screen
      
      System.out.println("\u001b[H\u001b[2J");
  }
}