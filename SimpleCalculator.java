package se.salt.jfs.simplecalculator;

public class SimpleCalculator {

    // ADD TWO NUMBERS.
    public int add(int a, int b)   {
        return a+b;

    }

    // ADD ARRAY OF ELEMENTS ...UPTO 5 NUMBERS

    public int addAll(int ... integers) throws IllegalArgumentException  {
        if(integers.length == 0) {
            throw new IllegalArgumentException("Empty array is not allowed");
        }
        if(integers.length > 5){
            throw new IllegalArgumentException("Only 5 numbers are supported");
        }

        int sumResult=0;
        for(int number:integers) {
            sumResult += number;
        }
        return sumResult;
    }
    // MULTIPLY 2 NUMBERS.
    public int multiply(int a, int b)   {
        return a*b;
    }

     // MULTIPLY AN ARRAY OF ELEMENTS.... UPTO 5 NUMBERS.
    public int multiplyAll(int ... integers) throws IllegalArgumentException   {

        if(integers.length == 0) {
            throw new IllegalArgumentException("Empty array is not allowed");
        }
        if(integers.length > 5){
            throw new IllegalArgumentException("Only 5 numbers are supported");
        }

        int productResult=1;
        for(int number:integers){
            productResult *= number;
        }
        return productResult;





    }

    //DIVIDE 2 NUMBERS.

    public int divide(int a, int b) throws IllegalArgumentException   {
        if(b == 0){
            throw new IllegalArgumentException("Division with 0 not allowed");
        }
        return a/b;
    }

    // SUBTRACT 2 NUMBERS.
     public int subtract(int a,int b){
        return a-b;
     }

}
