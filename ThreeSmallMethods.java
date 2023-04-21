public class ThreeSmallMethods {

  public boolean isYoung(int age) {
    /* Checks if the parameter age of type int is less than or equal to 30 and greater than 0.
    If yes , it returns true.
    Else returns False
     */
    return age <= 30 && age >= 0;

  }

  public boolean isManInSweden(String personalNumber){

     /* if-else statement checks if the length of the personalNumber is 10*/

    if(personalNumber.length() != 10){
      return false;
    }
    /* if the personalNumber is equal to 10 , then it checks if the second last character is odd or even*/
    else{

      // Gets the 9th character of the string.
      //Converts the character to integer.
      /* if the second last character is even , returns false.
      * Else it returns true */
      return Integer.parseInt(String.valueOf(personalNumber.charAt(8))) % 2 != 0;
    }

  }

  public int firstTwoDigitInteger(int[] numbers) {

/* The method checks if the given array has two-digit numbers.
It would return the first two-digit number .
 */
    for (int number : numbers) {
      if (number > 9 && number < 100) {
        return number;
        //It returns the first Two-digit number and exits the function.
      }
    }
    //If the array does not contain any Two-digit number, the method returns -1.
    return -1;
  }
}
