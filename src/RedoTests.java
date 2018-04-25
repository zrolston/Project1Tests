import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;


class RedoTest {
    public SparseInterface firstMatrix = new SingleListMatrix(5, 5);
    public SparseInterface secondMatrix = new SingleListMatrix(5, 5);
    public SparseInterface expectedResult = new SingleListMatrix(5, 5);


    //Score:  1
    @Test
    public void testClear() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(2,2);
            firstMatrix.clear();
            int[][] matrix = {
                    {2, 3},
                    {1, 6}
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
        });

    }

    //Score:  2
    @Test
    public void testAddOverwrite() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();
            firstMatrix.addElement(0, 0, 15);
            firstMatrix.addElement(0, 0, 4);
            int result = firstMatrix.getElement(0, 0);

            if (4 == result && "0 0 4\n".equals(firstMatrix.toString()))
                System.out.println(2);
            assertEquals(4, result);
            assertEquals("0 0 4\n", firstMatrix.toString());
        });
    }

    //Score:  2
    @Test
    public void testAddZero() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();
            firstMatrix.addElement(0, 0, 15);
            firstMatrix.addElement(0, 0, 0);
            int result = firstMatrix.getElement(0, 0);

            if ("".equals(firstMatrix.toString()))
                System.out.println(2);
            assertEquals(0, result);
            assertEquals("", firstMatrix.toString());
        });
    }

    //Score:  1
    @Test
    public void testAddElementHeadOfFirstRowGet() {

        firstMatrix.setSize(3,3);
        firstMatrix.clear();

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
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 3);
            firstMatrix.addElement(2, 1, 5);

            firstMatrix.addElement(1, 1, 2);
            firstMatrix.addElement(1, 2, 1);
            firstMatrix.addElement(1, 0, 4);

            int result = firstMatrix.getElement(1, 0);
            if (4 == result)
                System.out.println(1);
            assertEquals(4, result);
        });

    }

    //Score:  1
    @Test
    public void testAddElementMiddleOfMiddleRowGet() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 3);
            firstMatrix.addElement(2, 1, 5);

            firstMatrix.addElement(1, 0, 2);
            firstMatrix.addElement(1, 2, 1);
            firstMatrix.addElement(1, 1, 4);

            int result = firstMatrix.getElement(1, 1);
            if (4 == result)
                System.out.println(1);
            assertEquals(4, result);
        });
    }

    //Score:  1
    @Test
    public void testAddElementEndOfMiddleRowGet() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 3);
            firstMatrix.addElement(2, 1, 5);

            firstMatrix.addElement(1, 0, 2);
            firstMatrix.addElement(1, 1, 1);
            firstMatrix.addElement(1, 2, 4);

            int result = firstMatrix.getElement(1, 2);
            if (4 == result)
                System.out.println(1);
            assertEquals(4, result);
        });

    }

    //Score:  1
    @Test
    public void testAddElementEndOfLastRowGet() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(2,2);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 1);
            firstMatrix.addElement(1, 0, 3);
            firstMatrix.addElement(1, 1, 4);

            int result = firstMatrix.getElement(1, 1);
            if (4 == result)
                System.out.println(1);
            assertEquals(4, result);
        });
    }

    //Score:  1
    @Test
    public void testAddElementHeadOfFirstRowString() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 4);

            if ("0 0 4\n".equals(firstMatrix.toString()))
                System.out.println(1);
            assertEquals("0 0 4\n", firstMatrix.toString());
        });

    }

    //Score:  1
    @Test
    public void testAddElementHeadOfMiddleRowString() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 3);
            firstMatrix.addElement(2, 1, 5);

            firstMatrix.addElement(1, 1, 2);
            firstMatrix.addElement(1, 2, 1);
            firstMatrix.addElement(1, 0, 4);

            if ("0 0 3\n1 0 4\n1 1 2\n1 2 1\n2 1 5\n".equals(firstMatrix.toString()))
                System.out.println(1);

            assertEquals("0 0 3\n1 0 4\n1 1 2\n1 2 1\n2 1 5\n", firstMatrix.toString());
        });
    }

    //Score:  1
    @Test
    public void testAddElementMiddleOfMiddleRowString() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 3);
            firstMatrix.addElement(2, 1, 5);

            firstMatrix.addElement(1, 0, 2);
            firstMatrix.addElement(1, 2, 1);
            firstMatrix.addElement(1, 1, 4);

            if ("0 0 3\n1 0 2\n1 1 4\n1 2 1\n2 1 5\n".equals(firstMatrix.toString()))
                System.out.println(1);
            assertEquals("0 0 3\n1 0 2\n1 1 4\n1 2 1\n2 1 5\n", firstMatrix.toString());
        });
    }

    //Score:  1
    @Test
    public void testAddElementEndOfMiddleRowString() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 3);
            firstMatrix.addElement(2, 1, 5);

            firstMatrix.addElement(1, 0, 2);
            firstMatrix.addElement(1, 1, 1);
            firstMatrix.addElement(1, 2, 4);

            if ("0 0 3\n1 0 2\n1 1 1\n1 2 4\n2 1 5\n".equals(firstMatrix.toString()))
                System.out.println(1);
            assertEquals("0 0 3\n1 0 2\n1 1 1\n1 2 4\n2 1 5\n", firstMatrix.toString());
        });
    }

    //Score:  1
    @Test
    public void testAddElementEndOfLastRowString() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(2,2);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 1);
            firstMatrix.addElement(1, 0, 3);
            firstMatrix.addElement(1, 1, 4);

            if ("0 0 1\n1 0 3\n1 1 4\n".equals(firstMatrix.toString()))
                System.out.println(1);
            assertEquals("0 0 1\n1 0 3\n1 1 4\n", firstMatrix.toString());
        });
    }

    //Score:  1
    @Test
    public void testRemoveElementHeadOfFirstRowGet() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 4);
            firstMatrix.addElement(0, 1, 5);

            firstMatrix.removeElement(0, 0);
            int result = firstMatrix.getElement(0, 0);
            if (0 == result)
                System.out.println(1);
            assertEquals(0, result);
        });
    }

    //Score:  1
    @Test
    public void testRemoveLastElementInMatrixGet() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 4);

            firstMatrix.removeElement(0, 0);
            int result = firstMatrix.getElement(0, 0);
            if (0 == result)
                System.out.println(1);
            assertEquals(0, result);
        });
    }

    //Score:  1
    @Test
    public void testRemoveElementHeadOfMiddleRowGet() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

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
        });
    }

    //Score:  1
    @Test
    public void testRemoveElementMiddleOfMiddleRowGet() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

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
        });
    }

    //Score:  1
    @Test
    public void testRemoveElementEndOfMiddleRowGet() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

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
        });
    }

    //Score:  1
    @Test
    public void testRemoveElementEndOfLastRowGet() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(2,2);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 1);
            firstMatrix.addElement(1, 0, 3);
            firstMatrix.addElement(1, 1, 4);

            firstMatrix.removeElement(1, 1);
            int result = firstMatrix.getElement(1, 1);
            if (0 == result)
                System.out.println(1);
            assertEquals(0, result);
        });
    }

    //Score:  1
    @Test
    public void testRemoveElementHeadOfFirstRowString() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 4);
            firstMatrix.addElement(0, 1, 5);

            firstMatrix.removeElement(0, 0);
            String result = firstMatrix.toString();
            if ("0 1 5\n".equals(result))
                System.out.println(1);
            assertEquals("0 1 5\n", result);
        });
    }

    //Score:  1
    @Test
    public void testRemoveLastElementInMatrixString() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 4);

            firstMatrix.removeElement(0, 0);
            if ("".equals(firstMatrix.toString()))
                System.out.println(1);
            assertEquals("", firstMatrix.toString());
        });
    }

    //Score:  1
    @Test
    public void testRemoveElementHeadOfMiddleRowString() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 3);
            firstMatrix.addElement(2, 1, 5);

            firstMatrix.addElement(1, 1, 2);
            firstMatrix.addElement(1, 2, 1);
            firstMatrix.addElement(1, 0, 4);

            firstMatrix.removeElement(1, 0);

            if ("0 0 3\n1 1 2\n1 2 1\n2 1 5\n".equals(firstMatrix.toString()))
                System.out.println(1);
            assertEquals("0 0 3\n1 1 2\n1 2 1\n2 1 5\n", firstMatrix.toString());
        });
    }

    //Score:  1
    @Test
    public void testRemoveElementMiddleOfMiddleRowString() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 3);
            firstMatrix.addElement(2, 1, 5);

            firstMatrix.addElement(1, 0, 2);
            firstMatrix.addElement(1, 2, 1);
            firstMatrix.addElement(1, 1, 4);

            firstMatrix.removeElement(1, 1);

            if ("0 0 3\n1 0 2\n1 2 1\n2 1 5\n".equals(firstMatrix.toString()))
                System.out.println(1);
            assertEquals("0 0 3\n1 0 2\n1 2 1\n2 1 5\n", firstMatrix.toString());
        });
    }

    //Score:  1
    @Test
    public void testRemoveElementEndOfMiddleRowString() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 3);
            firstMatrix.addElement(2, 1, 5);

            firstMatrix.addElement(1, 0, 2);
            firstMatrix.addElement(1, 1, 1);
            firstMatrix.addElement(1, 2, 4);

            firstMatrix.removeElement(1, 2);

            if ("0 0 3\n1 0 2\n1 1 1\n2 1 5\n".equals(firstMatrix.toString()))
                System.out.println(1);
            assertEquals("0 0 3\n1 0 2\n1 1 1\n2 1 5\n", firstMatrix.toString());
        });

    }

    //Score:  1
    @Test
    public void testRemoveElementEndOfLastRowString() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(2,2);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 1);
            firstMatrix.addElement(1, 0, 3);
            firstMatrix.addElement(1, 1, 4);

            firstMatrix.removeElement(1, 1);

            if ("0 0 1\n1 0 3\n".equals(firstMatrix.toString()))
                System.out.println(1);
            assertEquals("0 0 1\n1 0 3\n", firstMatrix.toString());
        });
    }


    //Score:  1
    @Test
    public void testGetExistingElement() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,4);
            firstMatrix.clear();

            firstMatrix.addElement(2, 2, 4);
            firstMatrix.addElement(1, 0, -3);

            if (4 == firstMatrix.getElement(2, 2) && -3 == firstMatrix.getElement(1, 0))
                System.out.println(1);
            assertEquals(4, firstMatrix.getElement(2, 2));
            assertEquals(-3, firstMatrix.getElement(1, 0));
        });
    }

    //Score: 2
    @Test
    public void testGetNonExistentElement() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,4);
            firstMatrix.clear();

            firstMatrix.addElement(2, 2, 4);
            firstMatrix.addElement(1, 0, -3);

            if (0 == firstMatrix.getElement(1, 1))
                System.out.println(2);
            assertEquals(0, firstMatrix.getElement(1, 1));
        });
    }


    //Score:  1
    @Test
    public void testNumRow() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(9, 6);
            firstMatrix.clear();

            if (9 == firstMatrix.getNumRows())
                System.out.println(1);
            assertEquals(9, firstMatrix.getNumRows());
        });
    }

    //Score:  1
    @Test
    public void testNumCols() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(9, 6);
            firstMatrix.clear();

            if (9 == firstMatrix.getNumRows())
                System.out.println(1);
            assertEquals(6, firstMatrix.getNumCols());
        });
    }



    //Score:  3
    @Test
    public void testSize() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

            int[][] matrix = {
                    {2,1,3},
                    {1,2,4},
                    {2,3,2}
            };
            this.add2DMatrix(matrix, firstMatrix);

            secondMatrix.setSize(2,2);
            int[][] matrix2 = {
                    {0,2},
                    {9,3}
            };
            this.add2DMatrix(matrix2, secondMatrix);

            if(firstMatrix.addMatrices(secondMatrix) == null)
                System.out.println(3);
            assertEquals(null, firstMatrix.addMatrices(secondMatrix));
        });
    }

    //Score:  5
    @Test
    public void testAddMatrix3x3() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

            int[][] matrix = {
                    {0, 1, 4},
                    {2, 9, 0},
                    {2, 2, 3}
            };
            this.add2DMatrix(matrix, firstMatrix);


            secondMatrix.setSize(3,3);
            secondMatrix.clear();

            int[][] matrix2 = {
                    {3, 0, 2},
                    {9, 1, 3},
                    {0, 0, 3}
            };
            this.add2DMatrix(matrix2, secondMatrix);

            expectedResult.setSize(3,3);
            expectedResult.clear();

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
        });
    }

    //Score: 5
    @Test
    public void testAddMatrix4x4() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(4,4);
            firstMatrix.clear();

            int[][] matrix = {
                    {0,7,8,0},
                    {2,8,6,5},
                    {8,5,7,9},
                    {7,7,8,9},

            };
            this.add2DMatrix(matrix, firstMatrix);


            secondMatrix.setSize(4,4);
            secondMatrix.clear();

            int[][] matrix2 = {
                    {0,8,5,4},
                    {6,8,9,0},
                    {8,6,4,3},
                    {6,0,9,6},
            };
            this.add2DMatrix(matrix2, secondMatrix);

            expectedResult.setSize(4,4);
            expectedResult.clear();

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
        });
    }

    //Score:  5
    @Test
    public void testAddMatrix5x5() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(5,5);
            firstMatrix.clear();

            int[][] matrix = {
                    {1,2,4,5,2},
                    {4,6,2,4,5},
                    {1,4,5,5,6},
                    {9,5,5,3,4},
                    {1,2,4,3,7},

            };
            this.add2DMatrix(matrix, firstMatrix);

            secondMatrix.setSize(5,5);
            secondMatrix.clear();
            int[][] matrix2 = {
                    {-1,-2,-4,-5,-2},
                    {-4,-6,-2,-4,-5},
                    {-1,-4,-5,-5,-6},
                    {-9,-5,-5,-3,-4},
                    {-1,-2,-4,-3,-7},
            };
            this.add2DMatrix(matrix2, secondMatrix);

            expectedResult.setSize(5,5);
            expectedResult.clear();
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
        });
    }

    //Score:  5
    @Test
    public void testMultiply() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(4,4);
            firstMatrix.clear();
            int[][] matrix = {
                    {8,8,6,9},
                    {7,8,9,0},
                    {8,6,4,6},
                    {7,5,8,7}
            };
            this.add2DMatrix(matrix, firstMatrix);

            secondMatrix.setSize(4,4);
            secondMatrix.clear();
            int[][] matrix2 = {
                    {0,9,7,6},
                    {4,7,9,9},
                    {7,5,6,7},
                    {6,8,8,9}
            };
            this.add2DMatrix(matrix2, secondMatrix);

            expectedResult.setSize(4,4);
            expectedResult.clear();

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
        });
    }

    //Score:  5
    @Test
    public void testMultiply2() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(4,4);
            firstMatrix.clear();

            int[][] matrix = {
                    {22,12,2,1},
                    {4,3,12,3},
                    {53,2,3,2},
                    {4,2,1,33}
            };
            this.add2DMatrix(matrix, firstMatrix);


            secondMatrix.setSize(4,4);
            secondMatrix.clear();

            int[][] matrix2 = {
                    {0,2,12,1},
                    {44,3,22,1},
                    {3,23,6,3},
                    {9,4,52,3}
            };
            this.add2DMatrix(matrix2, secondMatrix);

            expectedResult.setSize(4,4);
            expectedResult.clear();

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
        });
    }

    //Score:  5
    @Test
    public void testMultiply3() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(3,3);
            firstMatrix.clear();

            int[][] matrix = {
                    {2,1,9},
                    {8,7,6},
                    {4,6,7},
            };
            this.add2DMatrix(matrix, firstMatrix);


            secondMatrix.setSize(3,3);
            secondMatrix.clear();

            int[][] matrix2 = {
                    {0,2,3},
                    {9,3,6},
                    {4,1,3},
            };
            this.add2DMatrix(matrix2, secondMatrix);

            expectedResult.setSize(3,3);
            expectedResult.clear();

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
        });
    }

    //Score:  5
    @Test
    public void testMultiply4() {
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            firstMatrix.setSize(2,4);
            firstMatrix.clear();

            firstMatrix.addElement(0, 0, 2);
            firstMatrix.addElement(0, 1, 27);
            firstMatrix.addElement(0, 2, 8);
            firstMatrix.addElement(0, 3, 5);
            firstMatrix.addElement(1, 0, 0);
            firstMatrix.addElement(1, 1, 6);
            firstMatrix.addElement(1, 2, 4);
            firstMatrix.addElement(1, 3, 7);



            secondMatrix.setSize(4,3);
            secondMatrix.clear();

            secondMatrix.addElement(0, 0, 7);
            secondMatrix.addElement(0, 1, 8);
            secondMatrix.addElement(0, 2, 6);
            secondMatrix.addElement(1, 0, 4);
            secondMatrix.addElement(1, 1, 7);
            secondMatrix.addElement(1, 2, 9);
            secondMatrix.addElement(2, 0, 5);
            secondMatrix.addElement(2, 1, 43);
            secondMatrix.addElement(2, 2, 8);
            secondMatrix.addElement(3, 0, 0);
            secondMatrix.addElement(3, 1, 8);
            secondMatrix.addElement(3, 2, 5);


            expectedResult.setSize(2,3);
            expectedResult.clear();

            expectedResult.addElement(0,0, 162);
            expectedResult.addElement(0,1, 589);
            expectedResult.addElement(0,2, 344);
            expectedResult.addElement(1,0, 44);
            expectedResult.addElement(1,1, 270);
            expectedResult.addElement(1,2, 121);

            SparseInterface result = firstMatrix.multiplyMatrices(secondMatrix);
            //if(result.toString().equals(expectedResult.toString()))
            // System.out.println(5);
            assertEquals(result.toString(), expectedResult.toString());
        });

    }





        public void add2DMatrix(int[][] matrix, SparseInterface sparseMatrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                sparseMatrix.addElement(i, j, matrix[i][j]);
            }
        }
    }
}
