public class Main {

  public static void main(String[] args) {
    int[][] sample1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix1 = Matrix.copyValueOf(sample1);
    System.out.println("Matrix1 :");
    System.out.println(matrix1);

    int[][] sample2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    Matrix matrix2 = Matrix.copyValueOf(sample2);
    System.out.println("Matrix2 :");
    System.out.println(matrix2);
    Matrix sum = matrix1.add(matrix2);
    if (sum != null) {
      System.out.println("Addition :");
      System.out.println(sum);
    }

    Matrix difference = matrix1.subtract(matrix2);
    if (difference != null) {
      System.out.println("Subtraction :");
      System.out.println(difference);
    }

    Matrix product = matrix1.multiply(matrix2);
    System.out.println("Product :");
    System.out.println(product);

    int determinant = matrix1.calculateDeterminant();
    System.out.println("Determinant : " + determinant);
  }
}
