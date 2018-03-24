public class CorrectMatrix implements SparseInterface {

    private RowNode head;
    private int size;

    private class ColNode{
        int colNum;
        ColNode next;
        int data;
    }

    private class RowNode{
        int rowNum;
        ColNode col;
        RowNode next;
    }

    public CorrectMatrix(){
        size = 5;
        head = null;
    }

    public boolean inBounds(int row, int col){
        if(row < 0 || col < 0 || row > size || col > size){
            return false;
        }
        return true;
    }

    public CorrectMatrix(int size){
        this.size = size;
        head = null;
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public void setSize(int size) {
        this.clear();
        this.size = size;
    }

    @Override
    public void addElement(int row, int col, int data) {

        if(!inBounds(row, col)){
            //Throw an error
            throw new IndexOutOfBoundsException();
        }

        RowNode currRow = this.head;
        RowNode trailRow = null;
        //iterate to correct row
        while (currRow != null){
            if(currRow.rowNum > row){
                break;
            }
            trailRow = currRow;
            currRow = currRow.next;
        }

        RowNode temp;
        //Case that we are effectively inserting a new row at head
        if(trailRow == null){
            if(data == 0){
                return;
            }
            trailRow = new RowNode();
            trailRow.rowNum = row;
            trailRow.next = currRow;
            temp = trailRow;
            this.head = temp;
        }
        //Inserting to a defined row
        else if(trailRow.rowNum == row){
            temp = trailRow;
        }
        //Inserting a new row in the middle of a list
        else{
            if(data == 0){
                return;
            }
            temp = new RowNode();
            temp.rowNum = row;
            temp.next = currRow;
            trailRow.next = temp;
        }

        //Insert at appropriate column of the row
        ColNode currCol = temp.col;
        ColNode trailCol = null;

        //Iterate to corrrect column
        while (currCol != null){
            if(currCol.colNum > col){
                break;
            }
            trailCol = currCol;
            currCol = currCol.next;
        }

        //Insert at "Head" of column list
        if(trailCol == null){
            if(data == 0){
                return;
            }
            trailCol = new ColNode();
            trailCol.colNum = col;
            trailCol.data = data;
            trailCol.next = currCol;
            temp.col = trailCol;
        }
        //Inserting to a defined row
        else if(trailCol.colNum == col){
            trailCol.data = data;
            if(data == 0){
                this.removeElement(row, col);
                return;
            }
        }
        //Inserting a new row in the middle of a list
        else{
            if(data == 0){
                return;
            }
            ColNode tempCol = new ColNode();
            tempCol.colNum = col;
            tempCol.data = data;
            tempCol.next = currCol;
            trailCol.next = tempCol;
        }
    }

    @Override
    public void removeElement(int row, int col) {



        if(!inBounds(row, col)){
            //Throw an error
            throw new IndexOutOfBoundsException();
        }

        RowNode currRow = this.head;
        RowNode trailRow = null;
        //iterate to correct row
        while (currRow != null){
            if(currRow.rowNum >= row){
                break;
            }
            trailRow = currRow;
            currRow = currRow.next;
        }

        //row does not exist in list
        if(currRow == null || currRow.rowNum != row){
            return;
        }

        ColNode currCol = currRow.col;
        ColNode trailCol = null;

        //iterate to appropriate column
        while (currCol != null){
            if(currCol.colNum >= col){
                break;
            }
            trailCol = currCol;
            currCol = currCol.next;
        }

        //column does not exist in list
        if(currCol == null || currCol.colNum != col){
            return;
        }

        //Remove the column
        if(trailCol != null){
            trailCol.next = currCol.next;
            //Thanks garbage collection
            return;
        }

        //We remove the "head" column list, and thus must remove the row node along with it.
        currRow.col = currRow.col.next;
        if(currRow.col == null) {
            if(trailRow == null){
                head = head.next;
                return;
            }
            trailRow.next = currRow.next;
        }
        //Thanks garbage collection
    }

    @Override
    public int getElement(int row, int col) {
        if(!inBounds(row, col)){
            //Throw an error
            throw new IndexOutOfBoundsException();
        }

        RowNode currRow = this.head;
        ColNode currCol;

        int element = 0;

        while (currRow != null && currRow.rowNum <= row) {
            currCol = currRow.col;
            while (currCol != null && currRow.rowNum == row && currCol.colNum <= col) {
                if(currCol.colNum == col){
                    element = currCol.data;
                }
                currCol = currCol.next;
            }
            currRow = currRow.next;
        }

        return element;
    }

    @Override
    public int determinant() {
        RowNode currRow = this.head;
        if(this.size < 3){
            return this.simpleDet();
        }

        //if any row is missing (entirely 0) then the det is 0
        int rowCount = 0;
        while(currRow != null){
            rowCount++;
            currRow = currRow.next;
        }
        if(rowCount < this.size){
            return 0;
        }

        int sum = 0;
        ColNode currCol = this.head.col;
        while(currCol != null){
            int neg = (currCol.colNum%2 == 0) ? 1:-1;
            sum += neg*(currCol.data)*minor(this.head.rowNum, currCol.colNum).determinant();
            currCol = currCol.next;
        }

        return sum;
    }

    private int simpleDet(){
        RowNode currRow = this.head;
        ColNode currCol;

        //Singular entry
        if(size == 1){
            return (currRow == null) ? 0:currRow.col.data;
        }

        //Standard 2x2 evaluation, kind of hacky atm but it's what this implementation sort of requires
        int a, b, c, d;
        a = b = c = d = 0;
        while (currRow != null) {
            currCol = currRow.col;
            while (currCol != null) {
                if(currRow.rowNum == 0) {
                    switch (currCol.colNum){
                        case 0: a = currCol.data;
                        break;
                        case 1: b = currCol.data;
                    }
                }
                else {
                    switch (currCol.colNum){
                        case 0: c = currCol.data;
                            break;
                        case 1: d = currCol.data;
                    }
                }
                currCol = currCol.next;
            }
            currRow = currRow.next;
        }


        return (a*d) - (b*c);
    }

    public SparseInterface minor(int row, int col){
        CorrectMatrix minorMatrix = new CorrectMatrix(this.size-1);
        RowNode currRow = this.head;
        ColNode currCol;

        while (currRow != null){
            currCol = currRow.col;
            while (currCol != null){
                if(currCol.colNum < col && currRow.rowNum < row){
                    minorMatrix.addElement(currRow.rowNum, currCol.colNum, currCol.data);
                }
                if(currCol.colNum > col && currRow.rowNum < row){
                    minorMatrix.addElement(currRow.rowNum, currCol.colNum-1, currCol.data);
                }
                if(currCol.colNum < col && currRow.rowNum > row){
                    minorMatrix.addElement(currRow.rowNum-1, currCol.colNum, currCol.data);
                }
                if(currCol.colNum > col && currRow.rowNum > row){
                    minorMatrix.addElement(currRow.rowNum-1, currCol.colNum-1, currCol.data);
                }

                currCol = currCol.next;
            }
            currRow = currRow.next;
        }

        return minorMatrix;
    }

    public String print() {
        StringBuilder matrix = new StringBuilder(size*size*4);
        RowNode currRow = this.head;
        ColNode currCol = null;

        int dataWrite;

        for(int i = 0; i < size; i++){
            if(currRow != null && currRow.rowNum == i){
                currCol = currRow.col;
            }
            for(int j = 0; j < size; j++){
                dataWrite = 0;
                if(currCol != null && currCol.colNum == j){
                    dataWrite = currCol.data;
                    currCol = currCol.next;
                }
                matrix.append(dataWrite);
                matrix.append(' ');
            }
            matrix.append('\n');
            if(currRow != null && currRow.rowNum == i){
                currRow = currRow.next;
            }
        }

        return matrix.toString();
    }

    @Override
    public String toString() {
        RowNode currRow = head;
        ColNode currCol = null;
        int row = 0;
        int col;

        StringBuilder myMatrix = new StringBuilder();

        while(currRow != null){
            row = currRow.rowNum;
            currCol = currRow.col;

            while (currCol != null) {
                col = currCol.colNum;
                myMatrix.append(row + " " + col + " " + currCol.data + "\n");
                currCol = currCol.next;
            }

            currRow = currRow.next;
        }

        return myMatrix.toString();
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public SparseInterface addMatrices(SparseInterface matrixToAdd) {
        return null;
    }

    @Override
    public SparseInterface multiplyMatrices(SparseInterface matrixToMultiply) {
        return null;
    }
}

