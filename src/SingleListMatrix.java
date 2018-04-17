public class SingleListMatrix implements SparseInterface {

    //For all time complexities within n is the size of the matrix (n=5 if the matrix is 5x5)

    public class MatrixNode{
        int data;
        int row;
        int col;
        MatrixNode next;

        public MatrixNode(int row, int col, int data){
            this.data = data;
            this.row = row;
            this.col = col;
            this.next = null;
        }
    }

    int numRows;
    int numCols;
    MatrixNode[] heads;

    SingleListMatrix(int numRows, int numCols){
        this.setSize(numRows, numCols);
    }

    @Override
    public void clear() {
        heads = new MatrixNode[this.numRows];
    }

    @Override
    public void setSize(int rows, int cols) {
        this.numCols = cols;
        this.numRows = rows;
        this.clear();
    }

    //O(n) operation since we get the row in O(1) time and traverse at most O(n) elements with an O(1) insertion
    @Override
    public void addElement(int row, int col, int data) {

        if(0 > row && 0 > col && row > numRows && col > numCols){
            throw new IndexOutOfBoundsException();
        }

        //Overwriting 0 to a position implies removing it, since we are not storing 0 values.
        if(data == 0){
            removeElement(row, col);
            return;
        }

        //Head of the row that we want to add to.
        MatrixNode curr = this.heads[row];
        MatrixNode prev = null;

        MatrixNode newNode = new MatrixNode(row, col, data);

        //If the row is empty
        if(heads[row] == null){
            heads[row] = newNode;
            return;
        }

        //Go until the end or find the correct column to insert behind.
        while(curr != null && curr.col < col){
            //Keep prev one behind curr and moves curr
            prev = curr;
            curr = curr.next;
        }

        //We have reached the end of the list, so we would need to just add to the end.
        if(curr == null){
            prev.next = newNode;
            return;
        }

        //If we reach this point curr must exist
        if(prev != null){

            //Overwrite data if the node exists.
            if(curr.col == col){
                curr.data = data;
            }
            //Else we can add in the newNode.
            else {
                newNode.next = prev.next;
                prev.next = newNode;
            }

            return;
        }

        //If we reach this point curr is not null and prev must be null
        //This implies we are either overwriting the first node
        //Or we are inserting at the first node
        if(curr.col == col){
            curr.data = data;
            return;
        }
        newNode.next = curr;
        heads[row] = newNode;
    }


    //Time complexity the same as add for the same reasons, though in this cases it is an O(1) removal.
    @Override
    public void removeElement(int row, int col) {
        if(0 > row && 0 > col && row > numRows && col > numCols){
            throw new IndexOutOfBoundsException();
        }

        //Head of the row we want to remove from.
        MatrixNode curr = this.heads[row];
        MatrixNode prev = null;

        if(heads[row] == null){
            return;
        }

        //Go until the end or find the correct column to delete.
        while(curr != null && curr.col != col){
            //Keep prev one behind curr and move curr
            prev = curr;
            curr = curr.next;
        }

        //If we might need to remove the head of the list.
        if(curr != null && prev == null){
            //Remove the first node in the list if it is the column that we want to remove.
            if(curr.col == col){
                heads[row] = heads[row].next;
            }
            return;
        }

        //Remove from some internal position in the list.
        if(curr != null){
            prev.next = curr.next;
            return;
        }

        //At this point we can assume that curr is null, implying that the specified node does not exist.
    }

    //O(n) operation, we get row in O(1) time and then traverse at most n elements to get the appropriate one.
    @Override
    public int getElement(int row, int col) {

        //If the list is empty we don't need to do anything.
        if(heads[row] == null){
            return 0;
        }

        MatrixNode curr = heads[row];

        //Go until the end or find the correct column to return.
        while(curr != null && curr.col != col){
            curr = curr.next;
        }

        if(curr != null){
            return curr.data;
        }

        return 0;
    }

    @Override
    public int getNumRows() {
        return this.numRows;
    }


    @Override
    public int getNumCols() {
        return this.numCols;
    }

    @Override
    public SparseInterface addMatrices(SparseInterface matrixToAdd) {
        if(this.numRows != matrixToAdd.getNumRows() && this.numCols != matrixToAdd.getNumCols()){
            return null;
        }

        SparseInterface sumMatrix = new SingleListMatrix(this.numRows, this.numCols);

        int myElement = 0;
        int newElement = 0;

        for(int i = 0; i < this.numRows; i++){
            for(int j = 0; j < this.numCols; j++){
                myElement = this.getElement(i,j);
                newElement = matrixToAdd.getElement(i,j);
                if(myElement + newElement != 0){
                    sumMatrix.addElement(i, j, myElement + newElement);
                }
            }
        }

        return sumMatrix;
    }

    @Override
    public SparseInterface multiplyMatrices(SparseInterface matrixToMultiply) {
        if (this.numCols != matrixToMultiply.getNumRows()) {
            return null;
        }

        SparseInterface productMatrix = new SingleListMatrix(this.numRows, matrixToMultiply.getNumCols());

        int elementSum;

        for(int i = 0; i < this.numRows; i++){
            for(int j = 0; j < matrixToMultiply.getNumCols(); j++){
                elementSum = 0;

                for(int k = 0; k < this.numCols; k++){
                    elementSum += this.getElement(i, k) * matrixToMultiply.getElement(k, j);
                }

                if(elementSum != 0) {
                    productMatrix.addElement(i, j, elementSum);
                }
            }
        }

        return productMatrix;
    }


    //O(n^2) since in the worst case we traverse a full matrix.
    @Override
    public String toString() {
        MatrixNode curr = null;
        StringBuilder matrix = new StringBuilder();
        for(int i = 0; i < this.numRows; i++){
            curr = heads[i];

            //Because we added nodes in printing order, a simple traversal of each row gives us the appropriate string
            while(curr != null){
                matrix.append(curr.row + " " + curr.col + " " + curr.data + "\n");
                curr = curr.next;
            }
        }

        return matrix.toString();
    }

}
