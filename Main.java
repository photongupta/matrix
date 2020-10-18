class Matrix {
  private static final char ADD = '+';
  private static final char SUBTRACT = '-';
  private static final char MULTIPLY = '*';

  private int[][] matrix;

  public Matrix() {
    this.matrix = new int[3][3];
  }

  public Matrix(int row, int col) {
    this.matrix = new int[row][col];
  }

  public static Matrix copyValueOf(int[][] array) {
    Matrix newMatrix = new Matrix(array.length, array[0].length);
    for (int rowIndex = 0; rowIndex < array.length; rowIndex++) {
      int[] row = new int[array[rowIndex].length];
      System.arraycopy(array[rowIndex], 0, row, 0, row.length);
      newMatrix.insertRow(rowIndex, row);
    }
    return newMatrix;
  }

  private int numOfRows() {
    return matrix.length;
  }

  private int numOfCols() {
    return matrix[0].length;
  }

  private int getElement(int row, int col) {
    return matrix[row][col];
  }

  private void insertRow(int rowIndex, int[] row) {
    this.matrix[rowIndex] = row;
  }

  public void print() {
    for (int row = 0; row < numOfRows(); row++) {
      for (int col = 0; col < numOfCols(); col++) {
        System.out.print(getElement(row, col) + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  private int calculate(int num1, int num2, char action) {
    switch (action) {
      case ADD:
        return num1 + num2;
      case SUBTRACT:
        return num1 - num2;
      default:
        return 0;
    }
  }

  private int[] getRow(Matrix other, int rowNumber, char action) {
    int[] row = new int[numOfCols()];
    for (int colNumber = 0; colNumber < numOfCols(); colNumber++) {
      int num1 = getElement(rowNumber, colNumber);
      int num2 = other.getElement(rowNumber, colNumber);
      row[colNumber] = calculate(num1, num2, action);
    }
    return row;
  }

  public int[] getRow(Matrix other, int rowNumber) {
    int[] temp = new int[other.numOfCols()];
    for (int i = 0; i < other.numOfCols(); i++) {
      int product = 0;
      for (int j = 0; j < Math.min(other.numOfRows(), numOfCols()); j++) {
        product += matrix[rowNumber][j] * other.getElement(j, i);
      }
      temp[i] = product;
    }
    return temp;
  }

  private Matrix performAction(Matrix other, char action) {
    Matrix result = new Matrix(numOfRows(), numOfCols());
    for (int rowNumber = 0; rowNumber < numOfRows(); rowNumber++) {
      switch (action) {
        case MULTIPLY:
          result.insertRow(rowNumber, getRow(other, rowNumber));
          break;
        default:
          result.insertRow(rowNumber, getRow(other, rowNumber, action));
          break;
      }
    }
    return result;
  }

  public Matrix add(Matrix other) {
    return performAction(other, ADD);
  }

  public Matrix subtract(Matrix other) {
    return performAction(other, SUBTRACT);
  }

  public Matrix multiply(Matrix other) {
    return performAction(other, MULTIPLY);
  }
}

public class Main {

  public static void main(String[] args) {
    // Matrix defaultMatrix = new Matrix(4, 4);
    // defaultMatrix.print();
    int[][] sample1 = { { 1, 2, 3 }, { 1, 2, 5 }, { 5, 2, 1 } };
    Matrix matrix1 = Matrix.copyValueOf(sample1);
    matrix1.print();
    int[][] sample2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix2 = Matrix.copyValueOf(sample2);
    matrix2.print();
    Matrix sum = matrix1.add(matrix2);
    sum.print();
    Matrix difference = matrix1.subtract(matrix2);
    difference.print();
    Matrix product = matrix1.multiply(matrix2);
    product.print();
  }
}
