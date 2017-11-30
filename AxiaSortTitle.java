/**
 * Created by matthewnguyen99 on 2017-11-29.
 */

import java.util.*;

public class AxiaSortTitle {
    /*
     * Method to save all books in to TreeMap
     * Use TreeMap for automatic sorting by alphabetical order
     * @param bookArrayList: all books
     * @return treeMapBook : the TreeMap that contains all books.
     *                          - Title of book is the value of map
     *                          - Partial name of title is the key of map
     */
    public TreeMap<String, Set> saveBookToHashMap(ArrayList<Book> bookArrayList){
        Set<String> listOfBookChaining = new TreeSet<>();
        TreeMap<String, Set > treeMapBook = new TreeMap<>();
        ArrayList<String> partialNameInTitle;
        for(int index = 0; index <bookArrayList.size(); index++){
            partialNameInTitle = getPartialNameInTitle(bookArrayList.get(index).getTitle());
            for(String eachNamePart: partialNameInTitle){
                Book currentBook = bookArrayList.get(index);
                boolean nameKeyFound = treeMapBook.containsKey(eachNamePart.toLowerCase());

                if(!nameKeyFound){
                    Set<String> newBookName = new TreeSet<>();
                    newBookName.add(currentBook.getTitle());
                    treeMapBook.put(eachNamePart.toLowerCase(), newBookName);
                }
                else{
                    listOfBookChaining = treeMapBook.get(eachNamePart.toLowerCase());
                    listOfBookChaining.add(bookArrayList.get(index).getTitle());
                    treeMapBook.put(eachNamePart.toLowerCase(), listOfBookChaining );
                }
            }
        }
        return treeMapBook;
    }
    /*
     * Method to get partial name of Title
     * example          : Abnormal Zebra Book 1
     * partial names are: Abnormal, Zebra, Book, 1
     * A partial name will be used as input for searching
     *
     * @param title               : the whole title of a book
     * @return partialNameInTitle : list which contains each string in the title
     */
    public ArrayList<String> getPartialNameInTitle(String title){
        ArrayList<String> partialNameInTitle = new ArrayList<>();
        StringTokenizer partialName = new StringTokenizer(title," ");
        while(partialName.hasMoreTokens()){
            partialNameInTitle.add(partialName.nextToken());
        }
        return partialNameInTitle;
    }
    /*
     * Method to get inputs from user
     * User can enter the whole title or a part of title
     */
    public void getInputForSearching(TreeMap <String, Set> treeMapBook, ArrayList<Book> bookArrayList){
        System.out.println("\nPlease enter your search. (Enter q to quit): ");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String input = (scanner.nextLine().toLowerCase().trim());
            System.out.println("input: "+input);

            if(input.equals("q")) break;

            if(treeMapBook.containsKey(input.toLowerCase())){
                System.out.println("You have " +  treeMapBook.get(input).size() + " books: \n" + treeMapBook.get(input));
            }
            else{
                boolean found = false;
                ArrayList<String> result = new ArrayList<>();
                for(int i = 0; i<bookArrayList.size(); i++){
                    String fullTitle = bookArrayList.get(i).getTitle();
                    if(fullTitle.toLowerCase().contains(input.toLowerCase())){
                        result.add(fullTitle);
                        found = true;
                    }
                }
                System.out.println("(Multi-name) You have " +  result.size() + " books: \n" + result);
                if(!found){
                    System.out.println("Book not found, search again. (Enter q to quit)");
                }
            }
        }
    }

    public static void main (String [] args){
        Book b1 = new Book("Abnormal Zebra Book 1");
        Book b2 = new Book("Harry Potter Book 2");
        Book b3 = new Book("Zebra Sahara Book 3");
        Book b4 = new Book("Blue Zebra Is In Sahara Book 4");
        Book b5 = new Book("This Is Abnormal Book 5");
        Book b6 = new Book("Potter Abnormal Blue Book 6");

        ArrayList<Book> bookArrayList = new ArrayList<>();
        bookArrayList.add(b1);
        bookArrayList.add(b2);
        bookArrayList.add(b3);
        bookArrayList.add(b4);
        bookArrayList.add(b5);
        bookArrayList.add(b6);

        System.out.println("All your books: " + bookArrayList);

        AxiaSortTitle axiaInstance = new AxiaSortTitle();
        TreeMap <String, Set> treeMapBook = axiaInstance.saveBookToHashMap(bookArrayList);

        //take user input
        axiaInstance.getInputForSearching(treeMapBook, bookArrayList);
    }
}       
