//package matrixcomputation;

import java.io.File;


public class MatrixComputation {


  public static void main(String[] args) {


    // Do not change the structure of this file.
    // Only fill up the missing matrices from the project test cases and print out them.

    // Testcase Step 1
    SparseMatrix matrixA = SparseMatrix.initializeByInput(new File("input.txt"));

    SparseMatrix[] matrixArray = SparseMatrix.initializeByFormula(matrixA.getSize());

    SparseMatrix matrixB = matrixArray[0];
    SparseMatrix matrixC = matrixArray[1];
    SparseMatrix matrixD = matrixArray[2];

    System.out.println("Matrix A: ");
    matrixA.print();
    System.out.println("Matrix B: ");
    matrixB.print();
    System.out.println("Matrix C: ");
    matrixC.print();
    System.out.println("Matrix D: ");
    matrixD.print();

    System.out.println("=============================1. Matrix E = B + D: ");
    SparseMatrix matrixE = matrixB.add(matrixD);
    matrixE.print();

    System.out.println("=============================2. Matrix F = D - C: ");
    SparseMatrix matrixF = matrixD.subtract(matrixC);
    matrixF.print();

    System.out.println("=============================3. Matrix G = A - B: ");
    SparseMatrix matrixG = matrixA.subtract(matrixB);
    matrixG.print();

    System.out.println("=============================4. Matrix H = B - C: ");
    SparseMatrix matrixH = matrixB.subtract(matrixC);
    matrixH.print();

    System.out.println("=============================5. Matrix I = E - F: ");
    SparseMatrix matrixI = matrixE.subtract(matrixF);
    matrixI.print();

    System.out.println("=============================6. Matrix J = A - E: ");
    SparseMatrix matrixJ = matrixA.subtract(matrixE);
    matrixJ.print();

    System.out.println("=============================7. Matrix K = A + H: ");
    SparseMatrix matrixK = matrixA.add(matrixH);
    matrixK.print();

    System.out.println("=============================8. Matrix L = K - J: ");
    SparseMatrix matrixL = matrixK.subtract(matrixJ);
    matrixL.print();

    System.out.println("=============================9. Matrix M = J + A: ");
    SparseMatrix matrixM = matrixJ.subtract(matrixA);
    matrixM.print();

    System.out.println("=============================10. Matrix N = M - I: ");
    SparseMatrix matrixN = matrixM.subtract(matrixI);
    matrixN.print();

    // Testcase Step 3
    System.out.println("=============================11. Matrix O = 5 * B: ");
    SparseMatrix matrixO = matrixB.scalarMultiply(5);
    matrixO.print();

    System.out.println("=============================12. Matrix P = A * B: ");
    SparseMatrix matrixP = matrixA.matrixMultiply(matrixB);
    matrixP.print();

    System.out.println("=============================13. Matrix Q = D ^ 5: ");
    SparseMatrix matrixQ = matrixD.power(5);
    matrixQ.print();

    System.out.println("=============================14. Matrix R = B * D: ");
    SparseMatrix matrixR = matrixB.matrixMultiply(matrixD);
    matrixR.print();

    System.out.println("=============================15. Matrix S = 8 * C: ");
    SparseMatrix matrixS = matrixC.scalarMultiply(8);
    matrixS.print();

    System.out.println("=============================16. Matrix T = E * G: ");
    SparseMatrix matrixT = matrixE.matrixMultiply(matrixG);
    matrixT.print();

    System.out.println("=============================17. Matrix U = G * E: ");
    SparseMatrix matrixU = matrixG.matrixMultiply(matrixE);
    matrixU.print();

    System.out.println("=============================18. Matrix V = C ^ 8: ");
    SparseMatrix matrixV = matrixC.power(8);
    matrixV.print();

    System.out.println("=============================19. Matrix W = H * J: ");
    SparseMatrix matrixW = matrixH.matrixMultiply(matrixJ);
    matrixW.print();

    System.out.println("=============================20. Matrix X = 2 * M: ");
    SparseMatrix matrixX = matrixM.scalarMultiply(2);
    matrixX.print();

    System.out.println("=============================21. Matrix Y = B ^ 10: ");
    SparseMatrix matrixY = matrixB.power(10);
    matrixY.print();

    System.out.println("=============================22. Matrix Z = M * Y: ");
    SparseMatrix matrixZ = matrixM.matrixMultiply(matrixT);
    matrixZ.print();

    System.out.println("=============================23. Matrix AA = transpose(F): ");
    SparseMatrix matrixAA = matrixF.transpose();
    matrixAA.print();

    System.out.println("=============================24. Matrix AB = transpose(E): ");
    SparseMatrix matrixAB = matrixE.transpose();
    matrixAB.print();

    System.out.println("=============================25. Matrix AC = transpose(V): ");
    SparseMatrix matrixAC = matrixV.transpose();
    matrixAC.print();

    System.out.println("=============================26. Matrix AD = transpose(L): ");
    SparseMatrix matrixAD = matrixL.transpose();
    matrixAD.print();
    
  }
}
