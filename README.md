# Books

Great Books Program Algorithm
Written By: Langston Hughes
Created On: 1 October 2017

1. BookTester Class
	A. Main: this method creates an object that is of the Book class to use the methods of the Book class
2. Book Class (extends Record)
  A. openFile: allows the user to input all book records stored in the database file
     - displays the list of data files the user can choose from
     - database file: library.dat
  B. inputBook: enters the books into an ArrayList of type Record
      - uses a while not end of file loop to fill the array
      - for every iteration of the loop, call the numBooks method
  C. numBooks: counts the number of books in the ArrayList
  D. bookSort: sorts the book ArrayList
      - uses a selection sort to sort the books
  E. bookMenu: displays a menu where the user can select an option
      - 1. Display all the book records
      - 2. Search for a book by title
      - 3. Exit the program
  F. bookSearch: searches the ArrayList for a book specified by the user
  G. displayRecords: displays all book records
     - uses a for loop
     - calls the print record method
  H. printRecord - prints out a book record based on the record number
3. Record Class: Book class accesses this class to store and get records
    - Holds the titles, authors, copyright dates, price, and genre of each book
    - uses a different method to store each part of the record
    - uses a different method to get each part of the record
