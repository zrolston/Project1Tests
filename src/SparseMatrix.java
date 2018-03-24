import java.util.*;


class determinantSolver{

    // put into determinantSolver to allow passing of ArrayList<ArrayList<matrixNode>>
    // recursively finds determinant by taking the determinate of each minor
    // minors top row deleted, all columns in that row
    // recursively stops when down to a 2x2 base case matrix, then calls getTwoDeterminant
    // int sum is carried back up through the recursions
    public int getDeterminant(ArrayList<ArrayList<matrixNode>> listToSolve){
        int listSize = listToSolve.size();
        int sum = 0;

        // this will hold minors matrixList of elements/ nodes
        ArrayList<ArrayList<matrixNode>> smallList = new ArrayList<ArrayList<matrixNode>>();

        // top row needed to see which columns are to be deleted when creating minors
        ArrayList<matrixNode> topRow = listToSolve.get(0);
        int rowSize = topRow.size();

        // base case, hopefully nobody needs to find the determinant of a 1x1
        if (listSize == 2){
            return getTwoDeterminant(listToSolve);
        }

        // counter keeps track of which element in row we are at
        // i will keep track of which column we are at
        // these will not be the same since the 0s in the sparse matrix
        // will not have an element/ node representing them
        int counter = 0;

        for (int i = 0; i < listSize; i++){
            // makes sure we dont go out of bounds in row
            if (counter < rowSize){
                // we will multiply determinant by this nodes value
                matrixNode currentNode = topRow.get(counter);

                // will skip if i has not caught up to currentNode yet
                if (i == currentNode.returnColumn()){
                    // creates minor as ArrayList<ArrayList<matrixNode>>
                    smallList = createListMinor(listToSolve, i);

                    // recursive calling on one of the minors
                    int toAdd = getDeterminant(smallList);
                    // recursive function above ^^^^

                    int multiplier = currentNode.returnValue();
                    counter ++;

                    // we use math, toAdd comes from recursive functions
                    // if statements decide if what we are adding is positive or negativ
                    // stems from -1^i from determinant algorithm
                    if (i % 2 == 0){
                        sum = sum + (multiplier * toAdd);
                    }
                    else{
                        sum = sum + ((-1) * multiplier * toAdd);
                    }
                }
            }

        }
        // return value after for loop has gone through all columns of its matrix
        return sum;
    }

    // this is base case of 2x2 matrix
    // a b
    // c d
    // solves using equation determinant = ad -bc
    public int getTwoDeterminant(ArrayList<ArrayList<matrixNode>> twoList){

        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;

        ArrayList<matrixNode> inner = twoList.get(0);

        // checks both elements and uses if statements to place values
        // into variables a and b
        // if no values found, int will stay as 0
        for (int i = 0; i < inner.size(); i++){
            matrixNode currentNode = inner.get(i);
            if (currentNode.returnColumn() == 0){
                a = currentNode.returnValue();
            }
            else{
                b = currentNode.returnValue();
            }
        }

        inner = twoList.get(1);
        // gets the second row and again uses if statements to place values into
        // c and d
        for (int i = 0; i < inner.size(); i++){
            matrixNode currentNode = inner.get(i);
            if (currentNode.returnColumn() == 0){
                c = currentNode.returnValue();
            }
            else{
                d = currentNode.returnValue();
            }
        }

        // implementation of equation to find determinant
        int determinant = (a * d) - (b * c);
        return determinant;
    }

    // creates matrix of minor using Arraylist of ArrayList of matrixNode representation
    // takes in ArrayList<ArrayList<matrixNode>> representing current matrix
    // adds only valid columns and rows to a new ArrayList<ArrayList<matrixNode>> matrix
    // by default removes top row, only takes in column to delete
    public ArrayList<ArrayList<matrixNode>> createListMinor(ArrayList<ArrayList<matrixNode>> bigList,
                                                            int columnToDelete){

        // new list for minor matrix
        ArrayList<ArrayList<matrixNode>> minorList = new ArrayList<ArrayList<matrixNode>>();
        // inner represents each row in current matrix bigList
        ArrayList<matrixNode> inner = new ArrayList<matrixNode>();
        // what we will add from inner to new matrix
        ArrayList<matrixNode> addToMinor = new ArrayList<matrixNode>();


        int bigListSize = bigList.size();

        // goes through all rows except first, since by default we are deleting top row
        for (int i = 1; i < bigListSize; i++){
            inner = bigList.get(i);
            int innerSize = inner.size();
            addToMinor = new ArrayList<matrixNode>();

            // goes through all elements of row, adds to new list if does not have deleteColumn
            for (int j = 0; j < innerSize; j++){
                matrixNode currentNode = inner.get(j);
                if (currentNode.returnColumn() != columnToDelete){

                    // create new node because we may change its column and row upwards
                    matrixNode maybeChangedNode = new matrixNode(currentNode.returnRow() - 1,
                            currentNode.returnColumn(), currentNode.returnValue());

                    if(currentNode.returnColumn() > columnToDelete){
                        // moves column left if it is after delted column
                        maybeChangedNode.subtractOneFromColumn();
                    }
                    // add to this rows list
                    addToMinor.add(maybeChangedNode);
                }
            }
            // add row to matrix
            minorList.add(addToMinor);
        }
        // returns ArrayList<ArrayList<matrixNode>> tp represent matrix
        return minorList;
    }


}

class SparseMatrix implements SparseInterface{

    // this is how we will store our matrix, each list is a row in matrix
    // matrix node holds row column and value ints
    ArrayList<ArrayList<matrixNode>> matrixList = new ArrayList<ArrayList<matrixNode>>();

    // matrix must be set to a positive number before calculations start
    int matrixSize = 0;


    // default arrayList clear
    public void clear(){
        matrixList.clear();
    }



    public void setSize(int size){
        clear();
        matrixSize = size;
        ArrayList<matrixNode> inner = new ArrayList<matrixNode>();

        // adds matrixSize # of rows to ArrayList matrixList
        // these inner rows all start as empty array list
        for (int i = 0; i < matrixSize; i++){
            matrixList.add(inner);
            // reference to different object each time
            inner = new ArrayList<matrixNode>();
        }
    }

    public int getSize(){
        return matrixSize;
    }

    public int getElement(int row, int col){
        // checks if query is in bounds
        if (row < 0 || col < 0 || row >= matrixSize || col >= matrixSize){
            System.out.println("Row or Columns are out of Bounds");
            System.out.println("Matrix may not be set yet");
            System.out.println("Matrix Size is: " + matrixSize);
            throw new IndexOutOfBoundsException();
        }

        ArrayList<matrixNode> inner = new ArrayList<matrixNode>();
        inner = matrixList.get(row);
        int innerSize = inner.size();
        // go through row and check if coordinates of element matches search
        for (int i = 0; i < innerSize; i++){
            matrixNode currentNode = inner.get(i);
            if (currentNode.returnColumn() == col){
                return(currentNode.returnValue());
            }
        }

        // if nothing found return 0
        return 0;
    }

    public void removeElement(int row, int col){
        // makes sure deletion is in bounds
        if (row < 0 || col < 0 || row >= matrixSize || col >= matrixSize){
            System.out.println("Row or Columns are out of Bounds");
            System.out.println("Matrix may not be set yet");
            System.out.println("Matrix Size is: " + matrixSize);
            throw new IndexOutOfBoundsException();
        }

        ArrayList<matrixNode> inner = new ArrayList<matrixNode>();
        inner = matrixList.get(row);
        int innerSize = inner.size();

        // searches through list for index of matrixNode,
        // uses arrayList default remove from row list
        for (int i = 0; i < innerSize; i++){
            matrixNode currentNode = inner.get(i);
            if (currentNode.returnColumn() == col){
                inner.remove(i);
            }
        }

        // sets row to new row with changes
        matrixList.set(row, inner);
    }


    // creates matrixNode object with correct info, adds to certain coordinate
    public void addElement(int row, int col, int data){
        // checking if inputs are within bounds
        // must set size before adding elements
        if (row < 0 || col < 0 || row >= matrixSize || col >= matrixSize){
            System.out.println("Row or Columns are out of Bounds");
            System.out.println("Matrix may not be set yet");
            System.out.println("Matrix Size is: " + matrixSize);
            throw new IndexOutOfBoundsException();
        }

        // create matrixNode object
        // will add to correct spot in predefined row of arrayList
        matrixNode newNode = new matrixNode(row, col, data);
        ArrayList<matrixNode> inner = new ArrayList<matrixNode>();
        inner = matrixList.get(row);

        // if list is empty, just add node
        if (inner.size() == 0){
            inner.add(newNode);
        }
        else{
            boolean nodeAdded = false;
            // search if that row and column already exists
            for(int i = 0; i < inner.size(); i++){
                matrixNode currentNode = inner.get(i);
                // if currentNode is greater then newNode, add newNode here
                if (currentNode.returnColumn() > col){
                    inner.add(i, newNode);
                    nodeAdded = true;
                    break;
                }
                // if adding to occupied space, we replace the node
                if (currentNode.returnColumn() == col){
                    inner.set(i, newNode);
                    nodeAdded = true;
                    break;
                }
            }
            // if newNode is currently last in row, we add it to the end
            if (nodeAdded == false){
                inner.add(newNode);
            }
        }
        // replace old row with new row
        matrixList.set(row, inner);
    }

    public ArrayList<ArrayList<matrixNode>> returnArrayList(){
        return matrixList;
    }

    // extra method I created to keep track of correct minor formation
    // prints matrix out in square
    // only checks sparse matrix for elements and prints 0 elsewhere
    public void printArrayList(ArrayList<ArrayList<matrixNode>> arrayListToPrint){

        int matrixSize = arrayListToPrint.size();
        // gets rows one at a time
        for (int i = 0; i < matrixSize; i ++){
            ArrayList<matrixNode> smallPrintList = arrayListToPrint.get(i);
            int listCounter = 0;
            int smallPrintSize = smallPrintList.size();

            // if spot where node should be is empty, print 0 instead
            // this is due to sparse matrix
            for (int j = 0; j < matrixSize; j++){
                if (listCounter >= smallPrintSize){
                    System.out.print("0 ");
                }
                else {
                    matrixNode nodeToPrint = smallPrintList.get(listCounter);
                    // if we match counter with current column, print element
                    if (j == nodeToPrint.returnColumn() && listCounter != smallPrintSize){
                        System.out.print(nodeToPrint.returnValue() + " ");
                        listCounter = listCounter + 1;
                    }
                    else{
                        System.out.print("0 ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // prints as row column data \n
    public String toString(){
        String listString = "";
        ArrayList<matrixNode> inner = new ArrayList<matrixNode>();

        // searches through each row for elements, if finds a node
        // adds its row column and value to the string
        for (int i = 0; i < matrixSize; i++){
            inner = matrixList.get(i);
            int innerSize = inner.size();
            if (inner.size() > 0){
                for (int j = 0; j < innerSize; j++){
                    matrixNode currentNode = inner.get(j);
                    int stringRow = currentNode.returnRow();
                    int stringColumn = currentNode.returnColumn();
                    int stringValue = currentNode.returnValue();
                    listString = listString + stringRow + " " + stringColumn
                            + " " + stringValue + "\n";

                }
            }
        }
        return listString;
    }

    // Recursive method. Located in determinantSolver.java
    public int determinant(){
        determinantSolver calcObject = new determinantSolver();
        int sum = calcObject.getDeterminant(matrixList);
        return sum;
    }


    // creates a minor matrixList in a SparseInterface object
    public SparseInterface minor(int row, int col){
        SparseMatrix minorMatrix = new SparseMatrix();
        int minorSize = matrixSize - 1;
        minorMatrix.setSize(minorSize);

        // will represent each row in matrixList
        ArrayList<matrixNode> inner = new ArrayList<matrixNode>();
        // if row or column not to be deleted, will add current inner node to addToMinor
        ArrayList<matrixNode> addToMinor = new ArrayList<matrixNode>();

        int bigListSize = matrixSize;

        // goes through all rows, skip adding row that is deleted row
        for (int i = 0; i < bigListSize; i++){
            if (i != col){
                inner = matrixList.get(i);
                int innerSize = inner.size();
                addToMinor = new ArrayList<matrixNode>();

                for (int j = 0; j < innerSize; j++){
                    matrixNode currentNode = inner.get(j);
                    int currentRow = currentNode.returnRow();
                    int currentColumn = currentNode.returnColumn();
                    int currentValue = currentNode.returnValue();
                    // will add to list if not the deleted column
                    if (currentColumn != col){
                        // will realign col and row to match new matrix
                        if (currentColumn > col){
                            currentColumn --;
                        }
                        if (currentRow > row){
                            currentRow --;
                        }
                        // adds to minor
                        minorMatrix.addElement(currentRow, currentColumn, currentValue);
                    }
                }

            }
        }

        return minorMatrix;
    }

    public SparseInterface addMatrices(SparseInterface matrixToAdd) {
        return null;
    }
    public SparseInterface multiplyMatrices(SparseInterface matrixToMultiply) {
        return null;
    }


}

class matrixNode
{
    // basic component of matrix
    public int row;
    public int column;
    public int value;

    matrixNode(int newRow, int newColumn, int newValue){
        this.row = newRow;
        this.column = newColumn;
        this.value = newValue;
    }

    public void changeRow(int newRow){
        this.row = newRow;
    }

    public void changeColumn(int newColumn){
        this.column = newColumn;
    }

    public int returnRow(){
        return this.row;
    }

    public void subtractOneFromColumn(){
        this.column = column -1;
    }

    public int returnColumn(){
        return this.column;
    }
    public int returnValue(){
        return this.value;
    }

    public void printInfo(){
        System.out.println("Row: " + row + "\nColumn: " + column + "\nValue: " + value);
    }

}
