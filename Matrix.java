class Matrix {
  private static final char ADD = '+';
  private static final char SUBTRACT = '-';
  private static final char MULTIPLY = '*';

  private int[][] matrix;
  private int rows;
  private int columns;

  public Matrix() {
    this.rows = 3;
    this.columns = 3;
    this.matrix = new int[3][3];
  }

  public Matrix(int row, int col) {
    this.rows = row;
    this.columns = col;
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

  private int getElement(int row, int col) {
    return matrix[row][col];
  }

  private int setElement(int row, int col, int num) {
    return matrix[row][col] = num;
  }

  private void insertRow(int rowIndex, int[] row) {
    this.matrix[rowIndex] = row;
  }

  public void print() {
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < columns; col++) {
        System.out.print(getElement(row, col) + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  private int calculate(Matrix other, int row, int col, char action) {
    int num1 = 0, num2 = 0;

    switch (action) {
      case ADD:
        num1 = getElement(row, col);
        num2 = other.getElement(row, col);
        return num1 + num2;
      case SUBTRACT:
        num1 = getElement(row, col);
        num2 = other.getElement(row, col);
        return num1 - num2;
      case MULTIPLY:
        for (int i = 0; i < Math.min(other.rows, columns); i++) {
          num1 += getElement(row, i) * other.getElement(i, col);
        }
        return num1;
      default:
        return 0;
    }
  }

  private int[] getRow(Matrix other, int rowNumber, char action) {
    int[] row = new int[other.columns];
    for (int colNumber = 0; colNumber < other.columns; colNumber++) {
      row[colNumber] = calculate(other, rowNumber, colNumber, action);
    }
    return row;
  }

  private Matrix performAction(Matrix other, char action) {
    Matrix result = new Matrix(rows, other.columns);
    for (int rowNumber = 0; rowNumber < rows; rowNumber++) {
      result.insertRow(rowNumber, getRow(other, rowNumber, action));
    }
    return result;
  }

  private boolean haveSameDimensions(Matrix other) {
    return rows == other.rows && columns == other.columns;
  }

  public Matrix add(Matrix other) {
    if (!haveSameDimensions(other)) return null;
    return performAction(other, ADD);
  }

  public Matrix subtract(Matrix other) {
    if (!haveSameDimensions(other)) return null;
    return performAction(other, SUBTRACT);
  }

  public Matrix multiply(Matrix other) {
    return performAction(other, MULTIPLY);
  }

  private Matrix createSubMatrix(int col) {
    Matrix subMatrix = new Matrix(rows - 1, columns - 1);
    for (int i = 1; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (j < col) subMatrix.setElement(i - 1, j, getElement(i, j));
        if (j > col) subMatrix.setElement(i - 1, j - 1, getElement(i, j));
      }
    }
    return subMatrix;
  }

  public int calculateDeterminant() {
    if (rows == 1) {
      return matrix[0][0];
    }

    if (rows == 2) {
      return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    int determinant = 0;
    int sign = 1;

    for (int col = 0; col < columns; col++) {
      Matrix subMatrix = createSubMatrix(col);
      determinant += sign * matrix[0][col] * subMatrix.calculateDeterminant();
      sign = -sign;
    }

    return determinant;
  }
}
