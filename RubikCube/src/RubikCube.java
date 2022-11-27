public interface RubikCube {

    // Rotate the front face of the cube anticlockwise.
    public void F_l();

    // Rotate the front face of the cube clockwise.
    public void F_r();

    // Rotate the back face of the cube anticlockwise.
    public void B_l();

    // Rotate the back face of the cube clockwise.
    public void B_r();

    // Rotate the top face of the cube anticlockwise.
    public void U_l();

    // Rotate the top face of the cube clockwise.
    public void U_r();

    // Rotate the bottom face of the cube anticlockwise.
    public void D_l();

    // Rotate the bottom face of the cube clockwise.
    public void D_r();

    // Rotate the left face of the cube anticlockwise.
    public void L_l();

    // Rotate the left face of the cube clockwise.
    public void L_r();

    // Rotate the right face of the cube anticlockwise.
    public void R_l();

    // Rotate the right face of the cube clockwise.
    public void R_r();

    // Print the cube to the console.
    public void printCube();
}
