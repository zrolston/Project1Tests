import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RedoTest {
    public SparseInterface firstMatrix = new SingleListMatrix(5);
    public SparseInterface secondMatrix = new SingleListMatrix(5);
    public SparseInterface expectedResult = new SingleListMatrix(5);

    //Score:  5
    @Test
    public void testMemory() {

        firstMatrix.setSize(10000000);
        firstMatrix.addElement(1, 1, 5);
        firstMatrix.determinant();

        if (0 == firstMatrix.determinant())
            System.out.println(5);

        assertEquals(0, firstMatrix.determinant());
    }


    //Score:  1
    @Test
    public void testClear() {
        firstMatrix.setSize(2);
        int[][] matrix = {
                {2, 3},
                {1, 6}
        };

        int[][] matrixOfZeros = {

                {0, 0},
                {0, 0}
        };
        this.add2DMatrix(matrix, firstMatrix);
        firstMatrix.clear();

        if (0 == firstMatrix.getElement(0, 0) && 0 == firstMatrix.getElement(0, 1)
                && 0 == firstMatrix.getElement(1, 0) && 0 == firstMatrix.getElement(1, 1))
            System.out.println(1);

        assertEquals(0, firstMatrix.getElement(0, 0));
        assertEquals(0, firstMatrix.getElement(0, 1));
        assertEquals(0, firstMatrix.getElement(1, 0));
        assertEquals(0, firstMatrix.getElement(1, 1));
    }

    //Score:  1
    @Test
    public void testSetSize() {
        firstMatrix.addElement(0, 0, 4);
        firstMatrix.setSize(3);

        int ans = firstMatrix.getSize();
        if (3 == ans && "".equals(firstMatrix.toString()))
            System.out.println(1);
        assertEquals(3, ans);
        assertEquals("", firstMatrix.toString());
    }

    //Score:  2
    @Test
    public void testAddOverwrite() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 15);
        firstMatrix.addElement(0, 0, 4);
        int result = firstMatrix.getElement(0, 0);

        if (4 == result && "0 0 4\n".equals(firstMatrix.toString()))
            System.out.println(2);
        assertEquals(4, result);
        assertEquals("0 0 4\n", firstMatrix.toString());
    }

    //Score:  2
    @Test
    public void testAddZero() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 15);
        firstMatrix.addElement(0, 0, 0);
        int result = firstMatrix.getElement(0, 0);

        if ("".equals(firstMatrix.toString()))
            System.out.println(2);
        assertEquals(0, result);
        assertEquals("", firstMatrix.toString());
    }

    //Score:  1
    @Test
    public void testAddElementHeadOfFirstRowGet() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 4);
        int result = firstMatrix.getElement(0, 0);

        if (4 == result && "0 0 4\n".equals(firstMatrix.toString()))
            System.out.println(1);
        assertEquals(4, result);
        assertEquals("0 0 4\n", firstMatrix.toString());
    }

    //Score:  1
    @Test
    public void testAddElementHeadOfMiddleRowGet() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 3);
        firstMatrix.addElement(2, 1, 5);

        firstMatrix.addElement(1, 1, 2);
        firstMatrix.addElement(1, 2, 1);
        firstMatrix.addElement(1, 0, 4);

        int result = firstMatrix.getElement(1, 0);
        if (4 == result)
            System.out.println(1);
        assertEquals(4, result);
    }

    //Score:  1
    @Test
    public void testAddElementMiddleOfMiddleRowGet() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 3);
        firstMatrix.addElement(2, 1, 5);

        firstMatrix.addElement(1, 0, 2);
        firstMatrix.addElement(1, 2, 1);
        firstMatrix.addElement(1, 1, 4);

        int result = firstMatrix.getElement(1, 1);
        if (4 == result)
            System.out.println(1);
        assertEquals(4, result);
    }

    //Score:  1
    @Test
    public void testAddElementEndOfMiddleRowGet() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 3);
        firstMatrix.addElement(2, 1, 5);

        firstMatrix.addElement(1, 0, 2);
        firstMatrix.addElement(1, 1, 1);
        firstMatrix.addElement(1, 2, 4);

        int result = firstMatrix.getElement(1, 2);
        if (4 == result)
            System.out.println(1);
        assertEquals(4, result);
    }

    //Score:  1
    @Test
    public void testAddElementEndOfLastRowGet() {
        firstMatrix.setSize(2);
        firstMatrix.addElement(0, 0, 1);
        firstMatrix.addElement(1, 0, 3);
        firstMatrix.addElement(1, 1, 4);

        int result = firstMatrix.getElement(1, 1);
        if (4 == result)
            System.out.println(1);
        assertEquals(4, result);
    }

    //Score:  1
    @Test
    public void testAddElementHeadOfFirstRowString() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 4);

        if ("0 0 4\n".equals(firstMatrix.toString()))
            System.out.println(1);
        assertEquals("0 0 4\n", firstMatrix.toString());
    }

    //Score:  1
    @Test
    public void testAddElementHeadOfMiddleRowString() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 3);
        firstMatrix.addElement(2, 1, 5);

        firstMatrix.addElement(1, 1, 2);
        firstMatrix.addElement(1, 2, 1);
        firstMatrix.addElement(1, 0, 4);

        if ("0 0 3\n1 0 4\n1 1 2\n1 2 1\n2 1 5\n".equals(firstMatrix.toString()))
            System.out.println(1);

        assertEquals("0 0 3\n1 0 4\n1 1 2\n1 2 1\n2 1 5\n", firstMatrix.toString());
    }

    //Score:  1
    @Test
    public void testAddElementMiddleOfMiddleRowString() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 3);
        firstMatrix.addElement(2, 1, 5);

        firstMatrix.addElement(1, 0, 2);
        firstMatrix.addElement(1, 2, 1);
        firstMatrix.addElement(1, 1, 4);

        if ("0 0 3\n1 0 2\n1 1 4\n1 2 1\n2 1 5\n".equals(firstMatrix.toString()))
            System.out.println(1);
        assertEquals("0 0 3\n1 0 2\n1 1 4\n1 2 1\n2 1 5\n", firstMatrix.toString());
    }

    //Score:  1
    @Test
    public void testAddElementEndOfMiddleRowString() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 3);
        firstMatrix.addElement(2, 1, 5);

        firstMatrix.addElement(1, 0, 2);
        firstMatrix.addElement(1, 1, 1);
        firstMatrix.addElement(1, 2, 4);

        if ("0 0 3\n1 0 2\n1 1 1\n1 2 4\n2 1 5\n".equals(firstMatrix.toString()))
            System.out.println(1);
        assertEquals("0 0 3\n1 0 2\n1 1 1\n1 2 4\n2 1 5\n", firstMatrix.toString());
    }

    //Score:  1
    @Test
    public void testAddElementEndOfLastRowString() {
        firstMatrix.setSize(2);
        firstMatrix.addElement(0, 0, 1);
        firstMatrix.addElement(1, 0, 3);
        firstMatrix.addElement(1, 1, 4);

        if ("0 0 1\n1 0 3\n1 1 4\n".equals(firstMatrix.toString()))
            System.out.println(1);
        assertEquals("0 0 1\n1 0 3\n1 1 4\n", firstMatrix.toString());
    }

    //Score:  1
    @Test
    public void testRemoveElementHeadOfFirstRowGet() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 4);
        firstMatrix.addElement(0, 1, 5);

        firstMatrix.removeElement(0, 0);
        int result = firstMatrix.getElement(0, 0);
        if (0 == result)
            System.out.println(1);
        assertEquals(0, result);
    }

    //Score:  1
    @Test
    public void testRemoveLastElementInMatrixGet() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 4);

        firstMatrix.removeElement(0, 0);
        int result = firstMatrix.getElement(0, 0);
        if (0 == result)
            System.out.println(1);
        assertEquals(0, result);
    }

    //Score:  1
    @Test
    public void testRemoveElementHeadOfMiddleRowGet() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 3);
        firstMatrix.addElement(2, 1, 5);

        firstMatrix.addElement(1, 1, 2);
        firstMatrix.addElement(1, 2, 1);
        firstMatrix.addElement(1, 0, 4);

        firstMatrix.removeElement(1, 0);

        int result = firstMatrix.getElement(1, 0);
        if (0 == result)
            System.out.println(1);
        assertEquals(0, result);
    }

    //Score:  1
    @Test
    public void testRemoveElementMiddleOfMiddleRowGet() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 3);
        firstMatrix.addElement(2, 1, 5);

        firstMatrix.addElement(1, 0, 2);
        firstMatrix.addElement(1, 2, 1);
        firstMatrix.addElement(1, 1, 4);

        firstMatrix.removeElement(1, 1);

        int result = firstMatrix.getElement(1, 1);
        if (0 == result)
            System.out.println(1);
        assertEquals(0, result);
    }

    //Score:  1
    @Test
    public void testRemoveElementEndOfMiddleRowGet() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 3);
        firstMatrix.addElement(2, 1, 5);

        firstMatrix.addElement(1, 0, 2);
        firstMatrix.addElement(1, 1, 1);
        firstMatrix.addElement(1, 2, 4);

        firstMatrix.removeElement(1, 2);

        int result = firstMatrix.getElement(1, 2);
        if (0 == result)
            System.out.println(1);
        assertEquals(0, result);
    }

    //Score:  1
    @Test
    public void testRemoveElementEndOfLastRowGet() {
        firstMatrix.setSize(2);
        firstMatrix.addElement(0, 0, 1);
        firstMatrix.addElement(1, 0, 3);
        firstMatrix.addElement(1, 1, 4);

        firstMatrix.removeElement(1, 1);
        int result = firstMatrix.getElement(1, 1);
        if (0 == result)
            System.out.println(1);
        assertEquals(0, result);
    }

    //Score:  1
    @Test
    public void testRemoveElementHeadOfFirstRowString() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 4);
        firstMatrix.addElement(0, 1, 5);

        firstMatrix.removeElement(0, 0);
        String result = firstMatrix.toString();
        if ("0 1 5\n".equals(result))
            System.out.println(1);
        assertEquals("0 1 5\n", result);
    }

    //Score:  1
    @Test
    public void testRemoveLastElementInMatrixString() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 4);

        firstMatrix.removeElement(0, 0);
        if ("".equals(firstMatrix.toString()))
            System.out.println(1);
        assertEquals("", firstMatrix.toString());
    }

    //Score:  1
    @Test
    public void testRemoveElementHeadOfMiddleRowString() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 3);
        firstMatrix.addElement(2, 1, 5);

        firstMatrix.addElement(1, 1, 2);
        firstMatrix.addElement(1, 2, 1);
        firstMatrix.addElement(1, 0, 4);

        firstMatrix.removeElement(1, 0);

        if ("0 0 3\n1 1 2\n1 2 1\n2 1 5\n".equals(firstMatrix.toString()))
            System.out.println(1);
        assertEquals("0 0 3\n1 1 2\n1 2 1\n2 1 5\n", firstMatrix.toString());
    }

    //Score:  1
    @Test
    public void testRemoveElementMiddleOfMiddleRowString() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 3);
        firstMatrix.addElement(2, 1, 5);

        firstMatrix.addElement(1, 0, 2);
        firstMatrix.addElement(1, 2, 1);
        firstMatrix.addElement(1, 1, 4);

        firstMatrix.removeElement(1, 1);

        if ("0 0 3\n1 0 2\n1 2 1\n2 1 5\n".equals(firstMatrix.toString()))
            System.out.println(1);
        assertEquals("0 0 3\n1 0 2\n1 2 1\n2 1 5\n", firstMatrix.toString());
    }

    //Score:  1
    @Test
    public void testRemoveElementEndOfMiddleRowString() {
        firstMatrix.setSize(3);
        firstMatrix.addElement(0, 0, 3);
        firstMatrix.addElement(2, 1, 5);

        firstMatrix.addElement(1, 0, 2);
        firstMatrix.addElement(1, 1, 1);
        firstMatrix.addElement(1, 2, 4);

        firstMatrix.removeElement(1, 2);

        if ("0 0 3\n1 0 2\n1 1 1\n2 1 5\n".equals(firstMatrix.toString()))
            System.out.println(1);
        assertEquals("0 0 3\n1 0 2\n1 1 1\n2 1 5\n", firstMatrix.toString());
    }

    //Score:  1
    @Test
    public void testRemoveElementEndOfLastRowString() {
        firstMatrix.setSize(2);
        firstMatrix.addElement(0, 0, 1);
        firstMatrix.addElement(1, 0, 3);
        firstMatrix.addElement(1, 1, 4);

        firstMatrix.removeElement(1, 1);

        if ("0 0 1\n1 0 3\n".equals(firstMatrix.toString()))
            System.out.println(1);
        assertEquals("0 0 1\n1 0 3\n", firstMatrix.toString());
    }


    //Score:  1
    @Test
    public void testGetExistingElement() {
        firstMatrix.addElement(2, 2, 4);
        firstMatrix.addElement(1, 0, -3);

        if (4 == firstMatrix.getElement(2, 2) && -3 == firstMatrix.getElement(1, 0))
            System.out.println(1);
        assertEquals(4, firstMatrix.getElement(2, 2));
        assertEquals(-3, firstMatrix.getElement(1, 0));
    }

    //Score: 2
    @Test
    public void testGetNonExistantElement() {
        firstMatrix.addElement(2, 2, 4);
        firstMatrix.addElement(1, 0, -3);

        if (0 == firstMatrix.getElement(1, 1))
            System.out.println(2);
        assertEquals(0, firstMatrix.getElement(1, 1));
    }

    //Score:  1
    @Test
    public void testGetSize() {
        firstMatrix.setSize(9);

        if (9 == firstMatrix.getSize())
            System.out.println(1);
        assertEquals(9, firstMatrix.getSize());
    }

/*
    //Score:  3
    @Test
    public void testSize() {
        firstMatrix.setSize(3);
        int[][] matrix = {
                {2,1,3},
                {1,2,4},
                {2,3,2}
        };
        this.add2DMatrix(matrix, firstMatrix);

        secondMatrix.setSize(2);
        int[][] matrix2 = {
                {0,2},
                {9,3}
        };
        this.add2DMatrix(matrix2, secondMatrix);

        if(firstMatrix.addMatrices(secondMatrix) == null)
            System.out.println(3);
        assertEquals(null, firstMatrix.addMatrices(secondMatrix));
    }

    //Score:  5
    @Test
    public void testAddMatrix3x3() {
        firstMatrix.setSize(3);
        int[][] matrix = {
                {0, 1, 4},
                {2, 9, 0},
                {2, 2, 3}
        };
        this.add2DMatrix(matrix, firstMatrix);


        secondMatrix.setSize(3);
        int[][] matrix2 = {
                {3, 0, 2},
                {9, 1, 3},
                {0, 0, 3}
        };
        this.add2DMatrix(matrix2, secondMatrix);

        expectedResult.setSize(3);
        int[][] matrix3 = {
                {3, 1, 6},
                {11, 10, 3},
                {2, 2, 6}
        };
        this.add2DMatrix(matrix3, expectedResult);


        SparseInterface result = firstMatrix.addMatrices(secondMatrix);
        if(result.toString().equals(expectedResult.toString()))
            System.out.println(5);
        assertEquals(result.toString(), expectedResult.toString());
    }

    //Score: 5
    @Test
    public void testAddMatrix4x4() {
        firstMatrix.setSize(4);
        int[][] matrix = {
                {0,7,8,0},
                {2,8,6,5},
                {8,5,7,9},
                {7,7,8,9},

        };
        this.add2DMatrix(matrix, firstMatrix);


        secondMatrix.setSize(4);
        int[][] matrix2 = {
                {0,8,5,4},
                {6,8,9,0},
                {8,6,4,3},
                {6,0,9,6},
        };
        this.add2DMatrix(matrix2, secondMatrix);

        expectedResult.setSize(4);
        int[][] matrix3 = {
                {0,15,13,4},
                {8,16,15,5},
                {16,11,11,12},
                {13,7,17,15},
        };
        this.add2DMatrix(matrix3, expectedResult);


        SparseInterface result = firstMatrix.addMatrices(secondMatrix);
        if(result.toString().equals(expectedResult.toString()))
            System.out.println(5);
        assertEquals(result.toString(), expectedResult.toString());
    }

    //Score:  5
    @Test
    public void testAddMatrix5x5() {
        firstMatrix.setSize(5);
        int[][] matrix = {
                {1,2,4,5,2},
                {4,6,2,4,5},
                {1,4,5,5,6},
                {9,5,5,3,4},
                {1,2,4,3,7},

        };
        this.add2DMatrix(matrix, firstMatrix);

        secondMatrix.setSize(5);
        int[][] matrix2 = {
                {-1,-2,-4,-5,-2},
                {-4,-6,-2,-4,-5},
                {-1,-4,-5,-5,-6},
                {-9,-5,-5,-3,-4},
                {-1,-2,-4,-3,-7},
        };
        this.add2DMatrix(matrix2, secondMatrix);

        expectedResult.setSize(5);
        int[][] matrix3 = {
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
        };
        this.add2DMatrix(matrix3, expectedResult);


        SparseInterface result = firstMatrix.addMatrices(secondMatrix);
        if(result.toString().equals(expectedResult.toString()))
            System.out.println(5);
        assertEquals(result.toString(), expectedResult.toString());
    }

    //Score:  5
    @Test
    public void testMultiply() {
        firstMatrix.setSize(4);
        int[][] matrix = {
                {8,8,6,9},
                {7,8,9,0},
                {8,6,4,6},
                {7,5,8,7}
        };
        this.add2DMatrix(matrix, firstMatrix);

        secondMatrix.setSize(4);
        int[][] matrix2 = {
                {0,9,7,6},
                {4,7,9,9},
                {7,5,6,7},
                {6,8,8,9}
        };
        this.add2DMatrix(matrix2, secondMatrix);

        expectedResult.setSize(4);
        int[][] matrix3 = {
                {128,230,236,243},
                {95,164,175,177},
                {88,182,182,184},
                {118,194,198,206}
        };
        this.add2DMatrix(matrix3, expectedResult);
        SparseInterface result = firstMatrix.multiplyMatrices(secondMatrix);
        if(result.toString().equals(expectedResult.toString()))
            System.out.println(5);
        assertEquals(result.toString(), expectedResult.toString());
    }

    //Score:  5
    @Test
    public void testMultiply2() {
        firstMatrix.setSize(4);
        int[][] matrix = {
                {22,12,2,1},
                {4,3,12,3},
                {53,2,3,2},
                {4,2,1,33}
        };
        this.add2DMatrix(matrix, firstMatrix);


        secondMatrix.setSize(4);
        int[][] matrix2 = {
                {0,2,12,1},
                {44,3,22,1},
                {3,23,6,3},
                {9,4,52,3}
        };
        this.add2DMatrix(matrix2, secondMatrix);

        expectedResult.setSize(4);
        int[][] matrix3 = {
                {543,130,592,43},
                {195,305,342,52},
                {115,189,802,70},
                {388,169,1814,108}
        };
        this.add2DMatrix(matrix3, expectedResult);

        SparseInterface result = firstMatrix.multiplyMatrices(secondMatrix);
        if(result.toString().equals(expectedResult.toString()))
            System.out.println(5);
        assertEquals(result.toString(), expectedResult.toString());
    }

    //Score:  5
    @Test
    public void testMultiply3() {
        firstMatrix.setSize(3);
        int[][] matrix = {
                {2,1,9},
                {8,7,6},
                {4,6,7},
        };
        this.add2DMatrix(matrix, firstMatrix);


        secondMatrix.setSize(3);
        int[][] matrix2 = {
                {0,2,3},
                {9,3,6},
                {4,1,3},
        };
        this.add2DMatrix(matrix2, secondMatrix);

        expectedResult.setSize(3);
        int[][] matrix3 = {
                {45,16,39},
                {87,43,84},
                {82,33,69},
        };
        this.add2DMatrix(matrix3, expectedResult);

        SparseInterface result = firstMatrix.multiplyMatrices(secondMatrix);
        if(result.toString().equals(expectedResult.toString()))
            System.out.println(5);
        assertEquals(result.toString(), expectedResult.toString());
    }

*/


    public void add2DMatrix(int[][] matrix, SparseInterface sparseMatrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                sparseMatrix.addElement(i, j, matrix[i][j]);
            }
        }
    }
}
