package me.arkadii.gumenniy.matrix.presentation.fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import me.arkadii.gumenniy.matrix.R;
import me.arkadii.gumenniy.matrix.presentation.adapters.MatrixAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatrixFragment extends Fragment {
    public static final int MAX_SIZE = 10;
    public static final int LAYOUT_MEASURE_DELAY = 10;


    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private MatrixAdapter adapter;
    private int[][] matrix;
    private ViewTreeObserver.OnGlobalLayoutListener listener;

    public MatrixFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_matrix, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_matrix);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        ViewTreeObserver viewTreeObserver = recyclerView.getViewTreeObserver();
        listener = new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
                } else {
                    recyclerView.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
                }
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (adapter == null) {
                            Log.e("grid", "" + recyclerView.getMeasuredHeight() + " " + recyclerView.getLayoutParams().height + " " + recyclerView.getHeight());
                            adapter = new MatrixAdapter(getActivity(), layoutManager, recyclerView.getHeight());
                            recyclerView.setAdapter(adapter);
                            if (matrix != null) {
                                adapter.setMatrix(matrix);
                                matrix = null;
                            }
                        }
                    }
                }, LAYOUT_MEASURE_DELAY);
            }
        };

        viewTreeObserver.addOnGlobalLayoutListener(listener);

        return view;
    }

    public void updateMatrix(int[][] matrix) {
        if (adapter != null) {
            adapter.setMatrix(matrix);
        } else {
            this.matrix = matrix;
        }
    }

}
