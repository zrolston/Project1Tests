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

    int size;
    MatrixNode[] heads;

    SingleListMatrix(){
        this.setSize(5);
    }

    SingleListMatrix(int i){
        this.setSize(i);
    }

    @Override
    public void clear() {
        heads = new MatrixNode[this.size];
    }

    @Override
    public void setSize(int size) {
        this.size = size;
        this.clear();
    }

    //O(n) operation since we get the row in O(1) time and traverse at most O(n) elements with an O(1) insertion
    @Override
    public void addElement(int row, int col, int data) {

        if(0 > row && 0 > col && row > size && col > size){
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
        if(0 > row && 0 > col && row > size && col > size){
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

    //O(n!) due to the recursive nature of the function as well as the call to minor.
    @Override
    public int determinant() {
        if(this.size == 0){
            return 0;
        }
        if(this.size == 1){
            return this.getElement(0,0);
        }

        int sum = 0;
        MatrixNode curr = heads[0];

        //We only need to iterate over a single row
        //In this case we choose the 0th row because it will always exist in a matrix of size 1 or more
        //Since 0 values don't matter we can just iterate over the stored nodes.
        while (curr != null){
            sum += (int)Math.pow(-1, curr.row + curr.col) * curr.data * this.minor(curr.row, curr.col).determinant();
            curr = curr.next;
        }

        return sum;
    }

    //O(n^3) for reasons explained within the method.
    @Override
    public SparseInterface minor(int row, int col) {
        MatrixNode curr;

        int newRow = 0;
        int newCol = 0;
        int data = 0;

        SingleListMatrix minor = new SingleListMatrix(this.size-1);

        for(int i = 0; i < this.size; i++){
            curr = heads[i];
            while(curr != null){
                //If it falls on the deleted row or column then skip it.
                if(curr.row == row || curr.col == col){
                    curr = curr.next;
                    continue;
                }

                newRow = curr.row;
                newCol = curr.col;
                data = curr.data;

                //Adjust coordinate for new matrix.
                if(newRow > row){
                    newRow--;
                }
                if(newCol > col){
                    newCol--;
                }

                //Add is an O(n) operation, because it is called at most n^2 times this
                //makes the minor method O(n^3)
                minor.addElement(newRow, newCol, data);
                curr = curr.next;
            }
        }

        return minor;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public SparseInterface addMatrices(SparseInterface matrixToAdd) {
        if(this.size != matrixToAdd.getSize()){
            return null;
        }

        SparseInterface sumMatrix = new SingleListMatrix(this.size);

        int myElement = 0;
        int newElement = 0;

        for(int i = 0; i < this.size; i++){
            for(int j = 0; j < this.size; j++){
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
        if (this.size != matrixToMultiply.getSize()) {
            return null;
        }

        SparseInterface productMatrix = new SingleListMatrix(this.size);

        int elementSum;

        for(int i = 0; i < this.size; i++){
            for(int j = 0; j < matrixToMultiply.getSize(); j++){
                elementSum = 0;

                for(int k = 0; k < this.size; k++){
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
        for(int i = 0; i < this.size; i++){
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
