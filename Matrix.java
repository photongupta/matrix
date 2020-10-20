class Matrix {
  private int[][] matrix;
  private int rows;
  private int columns;

  public Matrix(int numOfRows, int numOfCols) {
    this.rows = numOfRows;
    this.columns = numOfCols;
    this.matrix = new int[numOfRows][numOfCols;];
  }

  public static Matrix copyValueOf(int[][] array) {
    int numOfRows = array.length;
    int numOfCols = array[0].length;
    Matrix m = new Matrix(numOfRows, numOfCols);
    for (int rowIndex = 0; rowIndex < numOfRows; rowIndex++) {
      System.arraycopy(array[rowIndex], 0, m.matrix[rowIndex], 0, numOfCols);
    }
    return m;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < columns; col++) {
        sb.append(getElement(row, col)).append(" ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  private boolean haveSameDimensions(Matrix other) {
    return rows == other.rows && columns == other.columns;
  }

  public boolean equals(Matrix other) {
    if (!haveSameDimensions(other)) return false;
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < columns; col++) {
        if (getElement(row, col) != other.getElement(row, col)) return false;
      }
    }
    return true;
  }

  private int getElement(int row, int col) {
    return matrix[row][col];
  }

  private int setElement(int row, int col, int num) {
    return matrix[row][col] = num;
  }

  public Matrix add(Matrix other) {
    if (!haveSameDimensions(other)) return null;
    Matrix result = new Matrix(rows, columns);
    for (int rowId = 0; rowId < rows; rowId++) {
      for (int colId = 0; colId < columns; colId++) {
        int sum = getElement(rowId, colId) + other.getElement(rowId, colId);
        result.setElement(rowId, colId, sum);
      }
    }
    return result;
  }

  public Matrix subtract(Matrix other) {
    if (!haveSameDimensions(other)) return null;
    Matrix result = new Matrix(rows, columns);
    for (int rowId = 0; rowId < rows; rowId++) {
      for (int colId = 0; colId < columns; colId++) {
        int diff = getElement(rowId, colId) - other.getElement(rowId, colId);
        result.setElement(rowId, colId, diff);
      }
    }
    return result;
  }

  public Matrix multiply(Matrix other) {
    Matrix result = new Matrix(rows, other.columns);

    for (int rowId1 = 0; rowId1 < rows; rowId1++) {
      for (int colId2 = 0; colId2 < other.columns; colId2++) {
        int sum = 0;
        for (int rowId2 = 0; rowId2 < Math.min(other.rows, columns); rowId2++) {
          sum += getElement(rowId1, rowId2) * other.getElement(rowId2, colId2);
        }
        result.setElement(rowId1, colId2, sum);
      }
    }
    return result;
  }

  private Matrix createSubMatrix(int columnNumber) {
    Matrix subMatrix = new Matrix(rows - 1, columns - 1);
    for (int i = 1; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        int row = i - 1;
        int col = j > columnNumber ? j - 1 : j;
        if (j != columnNumber) {
          subMatrix.setElement(row, col, getElement(i, j));
        }
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
