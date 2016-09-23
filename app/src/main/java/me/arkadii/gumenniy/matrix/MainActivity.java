package me.arkadii.gumenniy.matrix;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements InputFragment.OnUserInputListener {

    private InputFragment inputFragment;
    private MatrixFragment matrixFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        inputFragment = (InputFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        if (inputFragment == null) {
            inputFragment = new InputFragment();
            showFragment(fragmentManager, R.id.fragment_container,  inputFragment);
        }

        matrixFragment = (MatrixFragment) fragmentManager.findFragmentById(R.id.fragment_container_2);
        if (matrixFragment == null) {
            matrixFragment = new MatrixFragment();
            showFragment(fragmentManager, R.id.fragment_container_2, matrixFragment);
        }
    }

    @Override
    protected void onDestroy() {
        inputFragment = null;
        matrixFragment = null;
        super.onDestroy();
    }

    private void showFragment(FragmentManager fragmentManager, int containerId, Fragment fragment) {
        fragmentManager.beginTransaction()
                .add(containerId, fragment)
                .commit();
    }

    @Override
    public void onUserInput(String rows, String cols, String startRow, String startCol) {
//        Log.e("activity", rows + " " + cols + " " + startRow + " " + startCol);
        try {
            int rowsCount = Integer.parseInt(rows);
            int colsCount = Integer.parseInt(cols);
            int startRowPosition = Integer.parseInt(startRow) - 1;
            int startColPosition = Integer.parseInt(startCol) - 1;

            if (rowsCount > MatrixFragment.MAX_SIZE) rowsCount = MatrixFragment.MAX_SIZE;
            if (colsCount > MatrixFragment.MAX_SIZE) colsCount = MatrixFragment.MAX_SIZE;

            if (rowsCount > 0 && colsCount > 0 &&
                    startRowPosition < rowsCount && startColPosition < colsCount &&
                    startRowPosition > -1 && startColPosition > -1) {

                int[][] matrix = Algorithm.calculateMatrix(colsCount, rowsCount, startColPosition, startRowPosition);
                matrixFragment.updateMatrix(matrix);
            }
        } catch (RuntimeException e) {
            Log.e("activity", String.valueOf(e));
        }
    }
}
