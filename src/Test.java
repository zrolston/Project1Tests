//public class Test {
//
//    /*
//        This is a small set of testing code to make sure you are implementing our P1SparseInterface.
//        You should be getting all "true" for the output
//
//        This is NOT the full extent of our testing, but passing these cases are essential to
//        passing our full tests.
//    */
//
//    public static void main(String[] args){
//        P1SparseInterface myTest = new SingleListMatrix(5);
//
//        myTest.addElement(0, 0, 16);
//
//        myTest.addElement(0, 1, 4);
//
//        myTest.removeElement(0,1);
//
//        String correctString = "0 0 16\n";
//
//        System.out.println("toString is correct: " + correctString.equals(myTest.toString()));
//
//        myTest.setSize(3);
//
//        System.out.println("Size is 3: " + (myTest.getSize() == 3));
//
//        correctString = "";
//
//        System.out.println("toString is correct: " + correctString.equals(myTest.toString()));
//
//        myTest.addElement(2,2,4);
//
//        myTest.addElement(1,0,-3);
//
//        correctString = "1 0 -3\n2 2 4\n";
//
//        System.out.println("toString is correct: " + correctString.equals(myTest.toString()));
//
//        System.out.println("The determinant is 0: " + (myTest.determinant() == 0));
//
//        P1SparseInterface myMinor = myTest.minor(1,1);
//
//        System.out.println("The (1,1) element of the minor is 4: " + (myMinor.getElement(1, 1) == 4));
//
//        myTest.clear();
//
//        //Additional tests start here.
//
//        myTest.addElement(0, 0, 0);
//
//        correctString = "";
//
//        //Because we are not storing 0 values in the matrix the toString should reflect an "empty" (all 0) matrix.
//        System.out.println("toString is correct: " + correctString.equals(myTest.toString()));
//
//        myTest.addElement(0, 1, 3);
//
//        myTest.addElement(0, 1, 0);
//
//        correctString = "";
//
//        //Note that adding 0 to the matrix overwrites the data at that position to 0 as defined in the interface description
//        //Because we are not storing 0 values, we can remove the element at that position.
//        System.out.println("toString is correct: " + correctString.equals(myTest.toString()));
//
//        myTest.addElement(0, 0, 16);
//        myTest.addElement(0, 1, 4);
//        myTest.addElement(1, 1, 9);
//        myTest.addElement(2, 2, 7);
//
//        System.out.println("The determinant is 1008: " + (myTest.determinant() == 1008));
//
//    }
//}