import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


class SparseInterfaceTest {
    public SparseInterface myTest = new SparseMatrix();

    @Test
    public void clear() {
    }

    @Test
    public void setSize() {
        myTest.setSize(3);
        int ans = myTest.getSize();
        assertEquals(3, ans);
    }

    @Test
    public void addElement() {
        myTest.addElement(0, 1, 4);
        String ans = myTest.toString();
        assertTrue(ans.equals((ans)));
    }

    @Test
    public void removeElement() {
        myTest.addElement(2,2,4);
        myTest.addElement(1,0,-3);

        myTest.removeElement(1, 0);

        String result = myTest.toString();
        String ans = "2 2 4\n";
        assertTrue(ans.equals(result));
    }

    @Test
    public void getElement() {
        myTest.addElement(2,2,4);
        myTest.addElement(1,0,-3);

        assertEquals(4, myTest.getElement(2,2));
        assertEquals(-3, myTest.getElement(1, 0));
    }

    @Test
    public void determinant() {
        myTest.setSize(2);
        myTest.addElement(0,0,2);
        myTest.addElement(0,1,3);
        myTest.addElement(1,0,1);
        myTest.addElement(1,1,6);

        System.out.print(myTest.toString());

        assertEquals(9, myTest.determinant());
    }

    @Test
    public void minor() {
        myTest.setSize(3);
        myTest.addElement(0,0,2);
        myTest.addElement(0,1,3);
        myTest.addElement(0,2,2);
        myTest.addElement(1,0,1);
        myTest.addElement(1,1,6);
        myTest.addElement(1,2,4);
        myTest.addElement(2,0,1);
        myTest.addElement(2,1,4);
        myTest.addElement(2,2,3);

        SparseInterface minor;
        minor = myTest.minor(0,0);
        assertEquals(6, minor.getElement(0,0));
        assertEquals(4, minor.getElement(0,1));
        assertEquals(4, minor.getElement(1,0));
        assertEquals(3, minor.getElement(1,1));
        }

    @Test
    public void toStringTest() {

    }

    @Test
    public void getSize() {
        myTest.setSize(9);
        assertEquals(9, myTest.getSize());
    }

}