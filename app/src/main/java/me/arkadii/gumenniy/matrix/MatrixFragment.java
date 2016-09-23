package me.arkadii.gumenniy.matrix;


import android.graphics.drawable.LayerDrawable;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class MatrixFragment extends Fragment {
    public static final int MAX_SIZE = 10;


    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private MatrixAdapter adapter;
    private int[][] matrix;

    public MatrixFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_matrix, container, false);
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_matrix);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                if (adapter == null) {
                    adapter = new MatrixAdapter(getActivity(), layoutManager, recyclerView.getHeight());
                    recyclerView.setAdapter(adapter);
                    if (matrix != null) {
                        adapter.setMatrix(matrix);
                        matrix = null;
                    }
                }
            }

        });

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
