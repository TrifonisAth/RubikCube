package tests;

import cube.Cube;

import static org.junit.jupiter.api.Assertions.*;

class TestCubeMovements {

    @org.junit.jupiter.api.Test
    void isSolved() {
        Cube cube =  new  Cube();
        // Single moves test cases.
        assertTrue(cube.isSolved());
        cube.F_l();
        assertFalse(cube.isSolved());
        cube.F_r();
        assertTrue(cube.isSolved());
        cube.B_l();
        assertFalse(cube.isSolved());
        cube.B_r();
        assertTrue(cube.isSolved());
        cube.U_l();
        assertFalse(cube.isSolved());
        cube.U_r();
        assertTrue(cube.isSolved());
        cube.D_l();
        assertFalse(cube.isSolved());
        cube.D_r();
        assertTrue(cube.isSolved());
        cube.L_l();
        assertFalse(cube.isSolved());
        cube.L_r();
        assertTrue(cube.isSolved());
        cube.R_l();
        assertFalse(cube.isSolved());
        cube.R_r();
        assertTrue(cube.isSolved());
        // Double moves test cases.
       cube.F_l();
         cube.F_l();
        assertFalse(cube.isSolved());
        cube.F_r();
        cube.F_r();
        assertTrue(cube.isSolved());
        for (int i = 0; i < 2; i++){
            cube.B_l();
        }
        assertFalse(cube.isSolved());
        cube.B_r();
        cube.B_r();
        assertTrue(cube.isSolved());
        for (int i = 0; i < 2; i++){
            cube.U_l();
        }
        assertFalse(cube.isSolved());
        cube.U_r();
        cube.U_r();
        assertTrue(cube.isSolved());
        for (int i = 0; i < 2; i++){
            cube.D_l();
        }
        assertFalse(cube.isSolved());
        cube.D_r();
        cube.D_r();
        assertTrue(cube.isSolved());
        for (int i = 0; i < 2; i++){
            cube.L_l();
        }
        assertFalse(cube.isSolved());
        cube.L_r();
        cube.L_r();
        assertTrue(cube.isSolved());
        for (int i = 0; i < 2; i++){
            cube.R_l();
        }
        assertFalse(cube.isSolved());
        cube.R_r();
        cube.R_r();
        assertTrue(cube.isSolved());
    }

    @org.junit.jupiter.api.Test
    void f_l() {
        // Rotate the front face of the cube anticlockwise.
        Cube cube =  new  Cube();
        cube.F_l();
        assertFalse(cube.isSolved());
        cube.F_l();
        assertFalse(cube.isSolved());
        cube.F_l();
        assertFalse(cube.isSolved());
        cube.F_l();
        assertTrue(cube.isSolved());

    }

    @org.junit.jupiter.api.Test
    void f_r() {
        // Rotate the front face of the cube clockwise.
        Cube cube =  new  Cube();
        cube.F_r();
        assertFalse(cube.isSolved());
        cube.F_r();
        assertFalse(cube.isSolved());
        cube.F_r();
        assertFalse(cube.isSolved());
        cube.F_r();
        assertTrue(cube.isSolved());
    }

    @org.junit.jupiter.api.Test
    void b_l() {
        // Rotate the back face of the cube anticlockwise.
        Cube cube =  new  Cube();
        cube.B_l();
        assertFalse(cube.isSolved());
        cube.B_l();
        assertFalse(cube.isSolved());
        cube.B_l();
        assertFalse(cube.isSolved());
        cube.B_l();
        assertTrue(cube.isSolved());
    }

    @org.junit.jupiter.api.Test
    void b_r() {
        // Rotate the back face of the cube clockwise.
        Cube cube =  new  Cube();
        cube.B_r();
        assertFalse(cube.isSolved());
        cube.B_r();
        assertFalse(cube.isSolved());
        cube.B_r();
        assertFalse(cube.isSolved());
        cube.B_r();
        assertTrue(cube.isSolved());
    }

    @org.junit.jupiter.api.Test
    void u_l() {
        // Rotate the up face of the cube anticlockwise.
        Cube cube =  new  Cube();
        cube.U_l();
        assertFalse(cube.isSolved());
        cube.U_l();
        assertFalse(cube.isSolved());
        cube.U_l();
        assertFalse(cube.isSolved());
        cube.U_l();
        assertTrue(cube.isSolved());
    }

    @org.junit.jupiter.api.Test
    void u_r() {
        // Rotate the up face of the cube clockwise.
        Cube cube =  new  Cube();
        cube.U_r();
        assertFalse(cube.isSolved());
        cube.U_r();
        assertFalse(cube.isSolved());
        cube.U_r();
        assertFalse(cube.isSolved());
        cube.U_r();
        assertTrue(cube.isSolved());
    }

    @org.junit.jupiter.api.Test
    void d_l() {
        // Rotate the down face of the cube anticlockwise.
        Cube cube =  new  Cube();
        cube.D_l();
        assertFalse(cube.isSolved());
        cube.D_l();
        assertFalse(cube.isSolved());
        cube.D_l();
        assertFalse(cube.isSolved());
        cube.D_l();
        assertTrue(cube.isSolved());
    }

    @org.junit.jupiter.api.Test
    void d_r() {
        // Rotate the down face of the cube clockwise.
        Cube cube =  new  Cube();
        cube.D_r();
        assertFalse(cube.isSolved());
        cube.D_r();
        assertFalse(cube.isSolved());
        cube.D_r();
        assertFalse(cube.isSolved());
        cube.D_r();
        assertTrue(cube.isSolved());
    }

    @org.junit.jupiter.api.Test
    void l_l() {
        // Rotate the left face of the cube anticlockwise.
        Cube cube =  new  Cube();
        cube.L_l();
        assertFalse(cube.isSolved());
        cube.L_l();
        assertFalse(cube.isSolved());
        cube.L_l();
        assertFalse(cube.isSolved());
        cube.L_l();
        assertTrue(cube.isSolved());
    }

    @org.junit.jupiter.api.Test
    void l_r() {
        // Rotate the left face of the cube clockwise.
        Cube cube =  new  Cube();
        cube.L_r();
        assertFalse(cube.isSolved());
        cube.L_r();
        assertFalse(cube.isSolved());
        cube.L_r();
        assertFalse(cube.isSolved());
        cube.L_r();
        assertTrue(cube.isSolved());
    }

    @org.junit.jupiter.api.Test
    void r_l() {
        // Rotate the right face of the cube anticlockwise.
        Cube cube =  new  Cube();
        cube.R_l();
        assertFalse(cube.isSolved());
        cube.R_l();
        assertFalse(cube.isSolved());
        cube.R_l();
        assertFalse(cube.isSolved());
        cube.R_l();
        assertTrue(cube.isSolved());
    }

    @org.junit.jupiter.api.Test
    void r_r() {
        // Rotate the right face of the cube clockwise.
        Cube cube =  new  Cube();
        cube.R_r();
        assertFalse(cube.isSolved());
        cube.R_r();
        assertFalse(cube.isSolved());
        cube.R_r();
        assertFalse(cube.isSolved());
        cube.R_r();
        assertTrue(cube.isSolved());
    }

    @org.junit.jupiter.api.Test
    void printCube() {
        Cube cube =  new  Cube();
        cube.printCube();
        // Rotate 4 times from every face and print the cube.
        System.out.println("Front face rotated 4 times anticlockwise.");
        for (int i = 0; i < 4; i++){
            cube.F_l();
            cube.printCube();
        }
        assertTrue(cube.isSolved());
        System.out.println("Front face rotated 4 times clockwise.");
        for (int i = 0; i < 4; i++){
            cube.F_r();
            cube.printCube();
        }
        assertTrue(cube.isSolved());
        System.out.println("Back face rotated 4 times anticlockwise.");
        for (int i = 0; i < 4; i++){
            cube.B_l();
            cube.printCube();
        }
        assertTrue(cube.isSolved());
        System.out.println("Back face rotated 4 times clockwise.");
        for (int i = 0; i < 4; i++){
            cube.B_r();
            cube.printCube();
        }
        assertTrue(cube.isSolved());
        System.out.println("Top face rotated 4 times anticlockwise.");
        for (int i = 0; i < 4; i++){
            cube.U_l();
            cube.printCube();
        }
        assertTrue(cube.isSolved());
        System.out.println("Top face rotated 4 times clockwise.");
        for (int i = 0; i < 4; i++){
            cube.U_r();
            cube.printCube();
        }
        assertTrue(cube.isSolved());
        System.out.println("Bottom face rotated 4 times anticlockwise.");
        for (int i = 0; i < 4; i++){
            cube.D_l();
            cube.printCube();
        }
        assertTrue(cube.isSolved());
        System.out.println("Bottom face rotated 4 times clockwise.");
        for (int i = 0; i < 4; i++){
            cube.D_r();
            cube.printCube();
        }
        assertTrue(cube.isSolved());
        System.out.println("Left face rotated 4 times anticlockwise.");
        for (int i = 0; i < 4; i++){
            cube.L_l();
            cube.printCube();
        }
        assertTrue(cube.isSolved());
        System.out.println("Left face rotated 4 times clockwise.");
        for (int i = 0; i < 4; i++){
            cube.L_r();
            cube.printCube();
        }
        assertTrue(cube.isSolved());
        System.out.println("Right face rotated 4 times anticlockwise.");
        for (int i = 0; i < 4; i++){
            cube.R_l();
            cube.printCube();
        }
        assertTrue(cube.isSolved());
        System.out.println("Right face rotated 4 times clockwise.");
        for (int i = 0; i < 4; i++){
            cube.R_r();
            cube.printCube();
        }
        assertTrue(cube.isSolved());
    }
}