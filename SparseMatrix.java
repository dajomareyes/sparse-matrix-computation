// package matrixcomputation;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class SparseMatrix {

  // implement all pre-defined methods below and add your own methods as needed.
  private Node[] rowHeads;
  private Node[] colHeads;
  private int size;

  public SparseMatrix(Node[] r, Node[] c) {
    rowHeads = r;
    colHeads = c;
    for(Node node : r) size += 1;
  }

  public static SparseMatrix initializeByInput(File file) {
    int row, col, value;
    SparseMatrix result = null;
    int size = 0;
    Node[] rowHeads = null;
    Node[] colHeads = null;

    try {
      BufferedReader reader = new BufferedReader(new FileReader(file));

      // get matrix size which would be the first line
      String line = "";
      line = reader.readLine();
      size = Integer.parseInt(line);

      rowHeads = createRowHeaders(size);
      colHeads = createColHeaders(size);

      while((line = reader.readLine()) != null){
        String[] elements = line.replaceAll("\\s+", " ").split(" ");
        row = Integer.parseInt(elements[0]) - 1;
        col = Integer.parseInt(elements[1]) - 1;
        value = Integer.parseInt(elements[2]);
        insert(rowHeads, colHeads, value, row, col);
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    result = new SparseMatrix(rowHeads, colHeads);
    return result;
  }

  // parameter n --> given matrix size n
  public static SparseMatrix[] initializeByFormula(int n) {
    SparseMatrix[] result = new SparseMatrix[3];

    result[0] = implementFormulaB(n);
    result[1] = implementFormulaC(n);
    result[2] = implementFormulaD(n);

    return result;
  }

  // implement this method to be able to print the matrix into the console.
  public void print() {
    for(Node row : rowHeads){
      System.out.print("|");
      int counter = 0;
      while(counter < size) {
        if(row.rowLink.col == counter){
          System.out.print(" " + (int) row.rowLink.value + " ");
          row = row.rowLink;
          counter++;
        } else {
          System.out.print(" " + 0 + " ");
          counter++;
        }
      }


      System.out.println("|");
    }
  }

  //parameter m --> another sparse matrix m
  public SparseMatrix add(SparseMatrix m) {
    SparseMatrix result = null;

    Node[] rowHeaders = createRowHeaders(m.getSize());
    Node[] colHeaders = createColHeaders(m.getSize());

    for(int i=0; i < m.getSize(); i++)
    addNodes(rowHeaders, colHeaders, m.rowHeads[i].rowLink, rowHeads[i].rowLink);

    result = new SparseMatrix(rowHeaders, colHeaders);
    return result;
  }

  public static void addNodes(Node[] rowHead, Node[] colHead, Node x, Node y) {
    if(x.col == -1 && y.col == -1)
    return;
    else if(x.col == y.col) {
      insert(rowHead, colHead, x.value + y.value, x.row, x.col);
      addNodes(rowHead, colHead, x.rowLink, y.rowLink);
      return;
    } else if(x.col != -1 && y.col == -1) {
      insert(rowHead, colHead, x.value, x.row, x.col);
      addNodes(rowHead, colHead, x.rowLink, y);
      return;
    } else if(y.col != -1 && x.col == -1) {
      insert(rowHead, colHead, y.value, y.row, y.col);
      addNodes(rowHead, colHead, x, y.rowLink);
      return;
    } else if(x.col < y.col && x.col != -1) {
      insert(rowHead, colHead, x.value, x.row, x.col);
      addNodes(rowHead, colHead, x.rowLink, y);
      return;
    } else if(y.col < x.col && y.col != -1) {
      insert(rowHead, colHead, y.value, y.row, y.col);
      addNodes(rowHead, colHead, x, y.rowLink);
      return;
    }
  }

  //parameter m --> another sparse matrix m
  public SparseMatrix subtract(SparseMatrix m) {
    SparseMatrix result = null;

    Node[] rowHeaders = createRowHeaders(m.getSize());
    Node[] colHeaders = createColHeaders(m.getSize());

    for(int i=0; i < m.getSize(); i++)
    subtractNodes(rowHeaders, colHeaders, rowHeads[i].rowLink, m.rowHeads[i].rowLink);

    result = new SparseMatrix(rowHeaders, colHeaders);

    return result;
  }

  public static void subtractNodes(Node[] rowHead, Node[] colHead, Node x, Node y) {
    if(x.col == -1 && y.col == -1)
    return;
    else if(x.col == y.col) {
      insert(rowHead, colHead, x.value + (-1*y.value), x.row, x.col);
      subtractNodes(rowHead, colHead, x.rowLink, y.rowLink);
      return;
    } else if(x.col != -1 && y.col == -1) {
      insert(rowHead, colHead, x.value, x.row, x.col);
      subtractNodes(rowHead, colHead, x.rowLink, y);
      return;
    } else if(y.col != -1 && x.col == -1) {
      insert(rowHead, colHead, (-1*y.value), y.row, y.col);
      subtractNodes(rowHead, colHead, x, y.rowLink);
      return;
    } else if(x.col < y.col && x.col != -1) {
      insert(rowHead, colHead, x.value, x.row, x.col);
      subtractNodes(rowHead, colHead, x.rowLink, y);
      return;
    } else if(y.col < x.col && y.col != -1) {
      insert(rowHead, colHead, (-1*y.value), y.row, y.col);
      subtractNodes(rowHead, colHead, x, y.rowLink);
      return;
    }
  }


  // integer a
  public SparseMatrix scalarMultiply(int a) {
    SparseMatrix result = null;

    Node[] rowHeaders = createRowHeaders(size);
    Node[] colHeaders = createColHeaders(size);

    for(Node row : rowHeads){
      scalarNode(rowHeaders, colHeaders, row.rowLink, a);
    }

    result = new SparseMatrix(rowHeaders, colHeaders);

    return result;
  }

  //parameter m --> another sparse matrix m
  public SparseMatrix matrixMultiply(SparseMatrix m) {
    SparseMatrix result = null;

    Node[] rows = createRowHeaders(size);
    Node[] cols = createColHeaders(size);

    //System.out.println(size);

    for(int i=0; i < size; i++){
      for(int j=0; j < size; j++) {
        dotProductNodes(rows, cols, rowHeads[i].rowLink, m.colHeads[j].colLink);
      }
    }

    result = new SparseMatrix(rows, cols);

    return result;
  }

  //integer c
  public SparseMatrix power(int c) {
    SparseMatrix result = createAllOneMatrix(size);

    SparseMatrix pow = performPower(result, new SparseMatrix(rowHeads, colHeads), c >> 1);
    if ((c & 1) == 0)
    return pow;
    else
    return pow.matrixMultiply(new SparseMatrix(rowHeads, colHeads));
  }

  //transpose matrix itself
  public SparseMatrix transpose() {
    SparseMatrix result = new SparseMatrix(createRowHeaders(size), createColHeaders(size));

    for(int i = 0; i < size; i++){
      Node curr = rowHeads[i].rowLink;
      while (curr.col != -1) {
        insert(result.rowHeads, result.colHeads, curr.value, curr.col, curr.row);
        curr = curr.rowLink;
      }
    }

    return result;
  }

  public SparseMatrix performPower(SparseMatrix last, SparseMatrix next, int bitInt) {
    SparseMatrix multi = simpleMulti(next);
    if(bitInt == 0){
      return last;
    } else if((bitInt & 1) != 0) {
      return performPower(last.matrixMultiply(multi), multi, bitInt >> 1);
    } else {
      return performPower(last, multi, bitInt >> 1);
    }
  }

  public SparseMatrix simpleMulti(SparseMatrix m) {
    return m.matrixMultiply(m);
  }

  public static void scalarNode(Node[] rowHead, Node[] colHead, Node start, int scalar) {
    if(start.col == -1){
      return;
    } else {
      insert(rowHead, colHead, start.value * scalar, start.row, start.col);
      scalarNode(rowHead, colHead, start.rowLink, scalar);
    }

  }

  public static void dotProductNodes(Node[] rowHeads, Node[] colHeads, Node rowNode, Node colNode) {

    double total = 0;

    int initRow = rowNode.row;
    int initCol = colNode.col;

    if(initRow == -1 || initCol == -1) { // meaning a row or column of all zeros this will return zeros fro that entire row or column
      return;
    }

    while (rowNode.col != -1 && colNode.row != -1) {
      if(rowNode.col == colNode.row) {
        total += rowNode.value * colNode.value;
        rowNode = rowNode.rowLink;
        colNode = colNode.colLink;
      } else if (rowNode.col > colNode.row && colNode.row != -1) {
        colNode = colNode.colLink;
      } else if (rowNode.col < colNode.row && rowNode.col != -1) {
        rowNode = rowNode.rowLink;
      }
    }

    //System.out.println(initRow + " " + initCol);
    insert(rowHeads, colHeads, total, initRow, initCol);

    return;
  }

  public static SparseMatrix createAllOneMatrix(int n){
    SparseMatrix result = new SparseMatrix(createRowHeaders(n), createColHeaders(n));
    for (int i=0; i<n; i++) insert(result.rowHeads, result.colHeads, 1, i, i);
    return result;
  }

  public static SparseMatrix implementFormulaB(int n) {
    SparseMatrix matrix = null;

    Node[] rowHeads = createRowHeaders(n);
    Node[] colHeads = createColHeaders(n);

    for(int i=1; i<=n; i++) {
      for(int j=1; j<=n; j++){
        if(i == j)
        insert(rowHeads,colHeads, i, i-1, j-1);
      }
    }

    matrix = new SparseMatrix(rowHeads, colHeads);

    return matrix;
  }

  public static SparseMatrix implementFormulaC(int n) {
    SparseMatrix matrix = null;

    Node[] rowHeads = createRowHeaders(n);
    Node[] colHeads = createColHeaders(n);

    for(int i=1; i<=n; i++)
    for(int j=1; j<=n; j++)
    if(i == (j+1)%n)
    insert(rowHeads,colHeads, -2*(j)-(i), i-1, j-1);

    matrix = new SparseMatrix(rowHeads, colHeads);

    return matrix;
  }

  public static SparseMatrix implementFormulaD(int n) {
    SparseMatrix matrix = null;

    Node[] rowHeads = createRowHeaders(n);
    Node[] colHeads = createColHeaders(n);

    for(int i=1; i<=n; i++){
      for(int j=1; j<=n; j++){
        if(i%2 != 0 && j%2 != 0 && j != 3)
        insert(rowHeads,colHeads,i+j, i-1, j-1);
        else if(j == 3)
        insert(rowHeads,colHeads,-i, i-1, j-1);
      }
    }

    matrix = new SparseMatrix(rowHeads, colHeads);

    return matrix;
  }

  public static SparseMatrix twoByTwoMatrix() {
    SparseMatrix matrix = null;

    Node[] rowHeads = createRowHeaders(2);
    Node[] colHeads = createColHeaders(2);

    insert(rowHeads,colHeads,2,0,1);
    insert(rowHeads,colHeads,3,1,0);
    insert(rowHeads,colHeads,4,1,1);

    matrix = new SparseMatrix(rowHeads,colHeads);
    return matrix;
  }

  public static Node[] createRowHeaders(int n) {
    Node[] rowHeads = new Node[n];
    for(int i=0; i<n; i++) {
      rowHeads[i] = new Node(-1,-1,-1);
      rowHeads[i].rowLink = rowHeads[i];
    }
    return rowHeads;
  }

  public static Node[] createColHeaders(int n) {
    Node[] colHeads = new Node[n];
    for(int i=0; i<n; i++) {
      colHeads[i] = new Node(-1,-1,-1);
      colHeads[i].colLink = colHeads[i];
    }
    return colHeads;
  }

  public static void insert(Node[] rowHead, Node[] colHead, double value, int row, int col) {

    Node rowNode = rowHead[row];
    Node colNode = colHead[col];

    Node insertedNode = new Node(value, row, col);
    Node temp = null;

    while(rowNode.rowLink.col != -1 && rowNode.rowLink.col < col)
    rowNode = rowNode.rowLink;
    temp = rowNode.rowLink;
    rowNode.rowLink = insertedNode;
    insertedNode.rowLink = temp;

    while(colNode.colLink.row != -1 && colNode.colLink.row < row )
    colNode = colNode.colLink;
    temp = colNode.colLink;
    colNode.colLink = insertedNode;
    insertedNode.colLink = temp;

  }

  public static void followNode(Node node){
    if(node.col == -1)
    return;
    System.out.println("x: " + node.value + " col: " + node.col + " row: " + node.row);
    followNode(node.rowLink);
  }

  public int getSize(){ return size; }

  public void setSize(int i) { this.size = i; }

}
