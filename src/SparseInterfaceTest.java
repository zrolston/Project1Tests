import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SparseInterfaceTest {
    public SparseInterface myTest = new SparseMatrix();

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
    }

    @Test
    public void testSetSize() {
        myTest.setSize(3);
        int ans = myTest.getSize();
        assertEquals(3, ans);
    }

    @Test
    public void testAddElementHeadOfFirstRow() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 4);
        int result = myTest.getElement(0, 0);
        assertEquals(4, result);
        assertEquals("0 0 4\n", myTest.toString());
    }

    @Test
    public void testAddElementHeadOfMiddleRow() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 3);
        myTest.addElement(2,1, 5);

        myTest.addElement(1, 1, 2);
        myTest.addElement(1, 2, 1);
        myTest.addElement(1, 0, 4);

        int result = myTest.getElement(1, 0);
        assertEquals(4, result);
    }

    @Test
    public void testAddElementMiddleOfMiddleRow() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 3);
        myTest.addElement(2,1, 5);

        myTest.addElement(1, 0, 2);
        myTest.addElement(1, 2, 1);
        myTest.addElement(1, 1, 4);

        int result = myTest.getElement(1, 1);
        assertEquals(4, result);
    }

    @Test
    public void testAddElementEndOfMiddleRow() {
        myTest.setSize(3);
        myTest.addElement(0, 0, 3);
        myTest.addElement(2,1, 5);

        myTest.addElement(1, 0, 2);
        myTest.addElement(1, 1, 1);
        myTest.addElement(1, 2, 4);

        int result = myTest.getElement(1, 2);
        assertEquals(4, result);
    }

    @Test
    public void testAddElementEndOfLastRow() {
        myTest.setSize(2);
        myTest.addElement(0, 0, 1);
        myTest.addElement(1,0,3);
        myTest.addElement(1,1,4);

        int result = myTest.getElement(1, 1);
        assertEquals(4, result);
    }

    @Test
    public void removeElement() {
        myTest.addElement(2, 2, 4);
        myTest.addElement(1, 0, -3);

        myTest.removeElement(1, 0);
        assertEquals(0, myTest.getElement(1, 0));
    }

    @Test
    public void testGetElement() {
        myTest.addElement(2, 2, 4);
        myTest.addElement(1, 0, -3);

        assertEquals(4, myTest.getElement(2, 2));
        assertEquals(-3, myTest.getElement(1, 0));
    }

    @Test
    public void testDeterminant() {
        myTest.setSize(2);
        int[][] matrix = {
                {2, 3},
                {1, 6}
        };
        this.addTwoDMatrix(matrix);
        assertEquals(9, myTest.determinant());
    }

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
        assertEquals(6, minor.getElement(0, 0));
        assertEquals(4, minor.getElement(0, 1));
        assertEquals(4, minor.getElement(1, 0));
        assertEquals(3, minor.getElement(1, 1));
    }

    @Test
    public void testToString() {
        myTest.setSize(2);
        int[][] matrix = {
                {2, 3},
                {1, 6}
        };
        this.addTwoDMatrix(matrix);
        String ans = "0 0 2\n0 1 3\n1 0 1\n1 1 6\n";
        assertTrue(ans.equals(myTest.toString()));
    }

    @Test
    public void testGetSize() {
        myTest.setSize(9);
        assertEquals(9, myTest.getSize());
    }

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

    }

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
    }

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
    }

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
    }

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
     }

     //TODO add edge case test
        
    public void addTwoDMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                myTest.addElement(i, j, matrix[i][j]);
            }
        }
    }
}