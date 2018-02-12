public class Test {

    /*
        This is a small set of testing code to make sure you are implementing our SparseInterface.
        You should be getting all "true" for the output

        This is NOT the full extent of our testing, but passing these cases are essential to
        passing our full tests.
    */

    public static void main(String[] args){
        SparseInterface myTest = new SparseMatrix();

        System.out.println("Size is 5: " + (myTest.getSize() == 5));

        myTest.addElement(0, 1, 4);

        String correctString = "0 1 4\n";

        System.out.println("Your toString is correct: " + correctString.equals(myTest.toString()));

        myTest.setSize(3);

        System.out.println("Size is be 3: " + (myTest.getSize() == 3));

        correctString = "";

        System.out.println("Your toString is correct: " + correctString.equals(myTest.toString()));

        myTest.addElement(2,2,4);

        myTest.addElement(1,0,-3);

        correctString = "1 0 -3\n2 2 4\n";

        System.out.println("Your toString is correct: " + correctString.equals(myTest.toString()));

        System.out.println("The correct determinant is 0: " + (myTest.determinant() == 0));

        SparseInterface myMinor = myTest.minor(1,1);

        System.out.println("The (1,1) element of the minor is 4: " + (myMinor.getElement(1, 1) == 4));

        System.out.println("myTest at 1,1 is 0: " + (myTest.getElement(1,1) == 0));

        myTest.clear();
    }
}
