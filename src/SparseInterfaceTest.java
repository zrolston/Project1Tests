import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SparseInterfaceTest {
    public SparseInterface myTest = new SingleListMatrix(5);
    public int score;


    //Score:  5
    @Test
    public void testMemory() {

        myTest.setSize(10000000);
        myTest.addElement(1, 1, 5);
        myTest.determinant();
        assertEquals(0, myTest.determinant());

        if( 0 == myTest.determinant())
            System.out.println(5);
    }


    //Score:  1
    @Test
    public void testClear() {
        myTest.setSize(2);
        int[][] matrix = {
                {2, 3},
                {1, 6}
        };

        int[][] matrixOfZeros = {

                {0, 0},
                {0, 0}
        };
        this.addTwoDMatrix(matrix);

        myTest.clear();
        assertEquals(0, myTest.getElement(0, 0));
        assertEquals(0, myTest.getElement(0, 1));
        assertEquals(0, myTest.getElement(1, 0));
        assertEquals(0, myTest.getElement(1, 1));
        if( 0 == myTest.getElement(0,0) && 0 == myTest.getElement(0,1)
                && 0 == myTest.getElement(1,0) && 0 == myTest.getElement(1,1))
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testSetSize() {
        myTest.addElement(0,0, 4);
        myTest.setSize(3);

        int ans = myTest.getSize();
        assertEquals(3, ans);
        assertEquals("", myTest.toString());
        if( 3 == ans && "".equals(myTest.toString()))
            System.out.println(1);
    }

    //Score:  2
    @Test
    public void testAddOverwrite() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 15);
        myTest.addElement(0,0,4);
        int result = myTest.getElement(0, 0);
        assertEquals(4, result);
        assertEquals("0 0 4\n", myTest.toString());

        if(4 == result && "0 0 4\n".equals(myTest.toString()))
            System.out.println(2);
    }

    //Score:  2
    @Test
    public void testAddZero() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 15);
        myTest.addElement(0,0,0);
        int result = myTest.getElement(0, 0);
   //     assertEquals(0, result);
        assertEquals("", myTest.toString());
        if("".equals(myTest.toString()))
            System.out.println(2);
    }

    //Score:  1
    @Test
    public void testAddElementHeadOfFirstRowGet() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 4);
        int result = myTest.getElement(0, 0);
        assertEquals(4, result);
        assertEquals("0 0 4\n", myTest.toString());
        if(4 == result && "0 0 4\n".equals(myTest.toString()))
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testAddElementHeadOfMiddleRowGet() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 3);
        myTest.addElement(2,1, 5);

        myTest.addElement(1, 1, 2);
        myTest.addElement(1, 2, 1);
        myTest.addElement(1, 0, 4);

        int result = myTest.getElement(1, 0);
        assertEquals(4, result);
        if(4 == result)
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testAddElementMiddleOfMiddleRowGet() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 3);
        myTest.addElement(2,1, 5);

        myTest.addElement(1, 0, 2);
        myTest.addElement(1, 2, 1);
        myTest.addElement(1, 1, 4);

        int result = myTest.getElement(1, 1);
        assertEquals(4, result);
        if(4 == result)
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testAddElementEndOfMiddleRowGet() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 3);
        myTest.addElement(2,1, 5);

        myTest.addElement(1, 0, 2);
        myTest.addElement(1, 1, 1);
        myTest.addElement(1, 2, 4);

        int result = myTest.getElement(1, 2);
        assertEquals(4, result);
        if(4 == result)
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testAddElementEndOfLastRowGet() {
        myTest.setSize(2);
        myTest.addElement(0, 0, 1);
        myTest.addElement(1,0,3);
        myTest.addElement(1,1,4);

        int result = myTest.getElement(1, 1);
        assertEquals(4, result);
        if(4 == result)
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testAddElementHeadOfFirstRowString() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 4);

        assertEquals("0 0 4\n", myTest.toString());
        if("0 0 4\n".equals(myTest.toString()))
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testAddElementHeadOfMiddleRowString() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 3);
        myTest.addElement(2,1, 5);

        myTest.addElement(1, 1, 2);
        myTest.addElement(1, 2, 1);
        myTest.addElement(1, 0, 4);


        assertEquals("0 0 3\n1 0 4\n1 1 2\n1 2 1\n2 1 5\n", myTest.toString());
        if("0 0 3\n1 0 4\n1 1 2\n1 2 1\n2 1 5\n".equals(myTest.toString()))
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testAddElementMiddleOfMiddleRowString() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 3);
        myTest.addElement(2,1, 5);

        myTest.addElement(1, 0, 2);
        myTest.addElement(1, 2, 1);
        myTest.addElement(1, 1, 4);

        assertEquals("0 0 3\n1 0 2\n1 1 4\n1 2 1\n2 1 5\n", myTest.toString());
        if("0 0 3\n1 0 2\n1 1 4\n1 2 1\n2 1 5\n".equals(myTest.toString()))
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testAddElementEndOfMiddleRowString() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 3);
        myTest.addElement(2,1, 5);

        myTest.addElement(1, 0, 2);
        myTest.addElement(1, 1, 1);
        myTest.addElement(1, 2, 4);

        assertEquals("0 0 3\n1 0 2\n1 1 1\n1 2 4\n2 1 5\n", myTest.toString());
        if("0 0 3\n1 0 2\n1 1 1\n1 2 4\n2 1 5\n".equals(myTest.toString()))
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testAddElementEndOfLastRowString() {
        myTest.setSize(2);
        myTest.addElement(0, 0, 1);
        myTest.addElement(1,0,3);
        myTest.addElement(1,1,4);

        assertEquals("0 0 1\n1 0 3\n1 1 4\n", myTest.toString());
        if("0 0 1\n1 0 3\n1 1 4\n".equals(myTest.toString()))
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testRemoveElementHeadOfFirstRowGet() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 4);
        myTest.addElement(0, 1, 5);

        myTest.removeElement(0, 0);
        int result = myTest.getElement(0, 0);
        assertEquals(0, result);
        if(0 == result)
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testRemoveLastElementInMatrixGet() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 4);

        myTest.removeElement(0, 0);
        int result = myTest.getElement(0, 0);
        assertEquals(0, result);
        if(0 == result)
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testRemoveElementHeadOfMiddleRowGet() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 3);
        myTest.addElement(2,1, 5);

        myTest.addElement(1, 1, 2);
        myTest.addElement(1, 2, 1);
        myTest.addElement(1, 0, 4);

        myTest.removeElement(1, 0);

        int result = myTest.getElement(1, 0);
        assertEquals(0, result);
        if(0 == result)
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testRemoveElementMiddleOfMiddleRowGet() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 3);
        myTest.addElement(2,1, 5);

        myTest.addElement(1, 0, 2);
        myTest.addElement(1, 2, 1);
        myTest.addElement(1, 1, 4);

        myTest.removeElement(1, 1);

        int result = myTest.getElement(1, 1);
        assertEquals(0, result);
        if( 0 == result)
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testRemoveElementEndOfMiddleRowGet() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 3);
        myTest.addElement(2,1, 5);

        myTest.addElement(1, 0, 2);
        myTest.addElement(1, 1, 1);
        myTest.addElement(1, 2, 4);

        myTest.removeElement(1, 2);

        int result = myTest.getElement(1, 2);
        assertEquals(0, result);
        if( 0 == result)
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testRemoveElementEndOfLastRowGet() {
        myTest.setSize(2);
        myTest.addElement(0, 0, 1);
        myTest.addElement(1,0,3);
        myTest.addElement(1,1,4);

        myTest.removeElement(1,1);
        int result = myTest.getElement(1, 1);
        assertEquals(0, result);
        if( 0 == result)
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testRemoveElementHeadOfFirstRowString() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 4);
        myTest.addElement(0, 1, 5);

        myTest.removeElement(0, 0);
        String result = myTest.toString();
        assertEquals("0 1 5\n", result);
        if("0 1 5\n".equals(result))
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testRemoveLastElementInMatrixString() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 4);

        myTest.removeElement(0, 0);
        assertEquals("", myTest.toString());
        if("".equals(myTest.toString()))
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testRemoveElementHeadOfMiddleRowString() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 3);
        myTest.addElement(2,1, 5);

        myTest.addElement(1, 1, 2);
        myTest.addElement(1, 2, 1);
        myTest.addElement(1, 0, 4);

        myTest.removeElement(1, 0);

        assertEquals("0 0 3\n1 1 2\n1 2 1\n2 1 5\n", myTest.toString());
        if("0 0 3\n1 1 2\n1 2 1\n2 1 5\n".equals(myTest.toString()))
            System.out.println(1);
    }

    //Score:  1
    @Test
    public void testRemoveElementMiddleOfMiddleRowString() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 3);
        myTest.addElement(2,1, 5);

        myTest.addElement(1, 0, 2);
        myTest.addElement(1, 2, 1);
        myTest.addElement(1, 1, 4);

        myTest.removeElement(1, 1);

        assertEquals("0 0 3\n1 0 2\n1 2 1\n2 1 5\n", myTest.toString());
        if("0 0 3\n1 0 2\n1 2 1\n2 1 5\n".equals(myTest.toString()))
            System.out.println(1);


    }

    //Score:  1
    @Test
    public void testRemoveElementEndOfMiddleRowString() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 3);
        myTest.addElement(2,1, 5);

        myTest.addElement(1, 0, 2);
        myTest.addElement(1, 1, 1);
        myTest.addElement(1, 2, 4);

        myTest.removeElement(1, 2);

        assertEquals("0 0 3\n1 0 2\n1 1 1\n2 1 5\n", myTest.toString());
        if("0 0 3\n1 0 2\n1 1 1\n2 1 5\n".equals(myTest.toString()))
            System.out.println(1);

    }

    //Score:  1
    @Test
    public void testRemoveElementEndOfLastRowString() {
        myTest.setSize(2);
        myTest.addElement(0, 0, 1);
        myTest.addElement(1,0,3);
        myTest.addElement(1,1,4);

        myTest.removeElement(1,1);

        assertEquals("0 0 1\n1 0 3\n", myTest.toString());
        if("0 0 1\n1 0 3\n".equals(myTest.toString()))
            System.out.println(1);
    }



    //Score:  1
    @Test
    public void testGetExistingElement() {
        myTest.addElement(2, 2, 4);
        myTest.addElement(1, 0, -3);

        assertEquals(4, myTest.getElement(2, 2));
        assertEquals(-3, myTest.getElement(1, 0));
        if( 4 == myTest.getElement(2,2) && -3 == myTest.getElement(1,0))
            System.out.println(1);
    }

    //Score: 2
    @Test
    public void testGetNonExistantElement() {
        myTest.addElement(2, 2, 4);
        myTest.addElement(1, 0, -3);

        assertEquals(0, myTest.getElement(1, 1));
        if(0 == myTest.getElement(1,1))
            System.out.println(2);
    }

    //Score:  5
    @Test
    public void testBaseDeterminant() {
        myTest.setSize(2);
        int[][] matrix = {
                {2, 3},
                {1, 6}
        };
        this.addTwoDMatrix(matrix);
        assertEquals(9, myTest.determinant());
        if(9 == myTest.determinant())
            System.out.println(5);
    }

    //Score:  3
    @Test
    public void testMinor() {
        myTest.setSize(3);
        int[][] matrix = {
                {2, 3, 2},
                {1, 6, 4},
                {1, 4, 3}
        };
        this.addTwoDMatrix(matrix);
        SparseInterface minor;
        minor = myTest.minor(0, 0);
        assertEquals(2, minor.getSize());
        assertEquals(6, minor.getElement(0, 0));
        assertEquals(4, minor.getElement(0, 1));
        assertEquals(4, minor.getElement(1, 0));
        assertEquals(3, minor.getElement(1, 1));

        myTest.removeElement(0,0);
        assertEquals(6, minor.getElement(0, 0));

        if(2 == minor.getSize() && 6 == minor.getElement(0,0) && 4 == minor.getElement(0,1) &&
                4 == minor.getElement(1,0) && 3== minor.getElement(1,1))
            System.out.println(3);
    }


    //Score:  1
    @Test
    public void testGetSize() {
        myTest.setSize(9);
        assertEquals(9, myTest.getSize());
        if( 9 == myTest.getSize())
            System.out.println(1);
    }

    //Score:  5
    @Test
    public void testDeterminantOne() {
        myTest.setSize(3);
        int[][] matrix = {
                {3, 2, 4},
                {0, 3, 2},
                {7, 0, 6}
        };
        this.addTwoDMatrix(matrix);
        assertEquals(-2, myTest.determinant());
        if(5 == myTest.determinant())
            System.out.println(5);

    }

    //Score:  5
    @Test
    public void testDeterminantTwo() {
        myTest.setSize(4);
        int[][] matrix = {
                {7, 8, 4, 5},
                {0, 0, 1, 6},
                {2, 0, 9, 5},
                {2, 0, 7, 0}
        };
        this.addTwoDMatrix(matrix);
        assertEquals(112, myTest.determinant());
        if(112 == myTest.determinant())
            System.out.println(5);
    }

    //Score: 5
    @Test
    public void testDeterminantThree() {
        myTest.setSize(5);
        int[][] matrix = {
                {1, 2, 8, 3, 0},
                {7, 2, 0, 1, 6},
                {0, 1, 4, 0, 5},
                {6, 1, 9, 7, 4},
                {9, 0, 1, 1, 3}
        };
        this.addTwoDMatrix(matrix);
        assertEquals(3822, myTest.determinant());
        if(3822 == myTest.determinant())
            System.out.println(5);
    }

    //Score:  5
    @Test
    public void testDeterminantFour() {
        myTest.setSize(6);
        int[][] matrix = {
                {0, 2, 3, 1, 2, 3},
                {3, 8, 6, 8, 0, 0},
                {7 ,3, 2, 1, 2, 1},
                {3, 2, 1, 2, 4 ,2},
                {6, 3, 1, 7, 3, 0},
                {0, 0, 1, 2, 5, 7}
        };
        this.addTwoDMatrix(matrix);
        assertEquals(3456, myTest.determinant());
        if(3456 == myTest.determinant())
            System.out.println(5);
    }

    //Score:  5
     @Test
     public void testDeterminantFive() {
        myTest.setSize(7);
        int[][] matrix = {
                {5, 5, 6, 1, 2, 4, 6},
                {0, 9, 9, 0, 3, 2, 1},
                {2, 1, 3, 4, 0, 6, 2},
                {1, 2, 1, 2, 3, 1, 1},
                {3, 4, 5, 1, 2, 0, 1},
                {0, 0, 1, 2, 3, 1, 2},
                {1, 2, 3, 5, 0, 1, 0}
        };
        this.addTwoDMatrix(matrix);
        assertEquals(20475, myTest.determinant());
        if(20475 == myTest.determinant())
            System.out.println(5);
     }



    public void addTwoDMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                myTest.addElement(i, j, matrix[i][j]);
            }
        }
    }
}