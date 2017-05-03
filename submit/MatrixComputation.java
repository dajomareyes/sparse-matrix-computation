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

        /* Phase 2
        // Testcase Step 3
        System.out.println("11. Matrix O = 5 * B: ");
        SparseMatrix matrixO = matrixB.scalarMultiply(5);
        matrixO.print();
        //follow the format above and fill up to 22. Matrix Z = M * T

        //Optional
        System.out.println("23. Matrix AA = transpose(F): ");
        SparseMatrix matrixAA = matrixF.transpose();
        matrixAA.print();
        //follow the format above and fill up to 26. Matrix AD = transpose(L)
        */
    }
}
