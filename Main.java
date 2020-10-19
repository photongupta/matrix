public class Main {

  public static void main(String[] args) {
    Matrix defaultMatrix = new Matrix();
    System.out.println("Default-Matrix :");
    defaultMatrix.print();

    int[][] sample1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix1 = Matrix.copyValueOf(sample1);
    System.out.println("Matrix1 :");
    matrix1.print();

    int[][] sample2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix2 = Matrix.copyValueOf(sample2);
    System.out.println("Matrix2 :");
    matrix2.print();

    Matrix sum = matrix1.add(matrix2);
    if (sum != null) {
      System.out.println("Addition :");
      sum.print();
    }

    Matrix difference = matrix1.subtract(matrix2);
    if (difference != null) {
      System.out.println("Subtraction :");
      difference.print();
    }

    Matrix product = matrix1.multiply(matrix2);
    System.out.println("Product :");
    product.print();

    int determinant = matrix1.calculateDeterminant();
    System.out.println("Determinant : " + determinant);
  }
}
