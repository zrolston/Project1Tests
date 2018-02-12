public class SingleListMatrix {

    public static class MyNode{
        int data;
        int row;
        int col;
        MyNode next;

        public MyNode(){
            data = 1;
            row = 6;
            col = 9;
            next = null;
        }
    }

    public static void main(String[] args){
        MyNode[] test = new MyNode[10000000];

        /*
        for(int i = 0; i < test.length; i++){
            test[i] = new MyNode();
        }
        */
    }
}
