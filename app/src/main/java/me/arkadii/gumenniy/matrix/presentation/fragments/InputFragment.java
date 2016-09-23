package me.arkadii.gumenniy.matrix.presentation.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import me.arkadii.gumenniy.matrix.R;
import me.arkadii.gumenniy.matrix.presentation.listeners.OnTextChangeListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {


    private EditText rowsView;
    private EditText colsView;
    private EditText rowStartPositionView;
    private EditText colStartPositionView;
    @Nullable
    private OnUserInputListener inputListener;
    private OnTextChangeListener textChangeListener = new OnTextChangeListener() {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String rows = rowsView.getText().toString();
            String cols = colsView.getText().toString();
            String startRow = rowStartPositionView.getText().toString();
            String startCol = colStartPositionView.getText().toString();
            if (inputListener != null) {
                inputListener.onUserInput(rows, cols, startRow, startCol);
            }
        }
    };

    public InputFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnUserInputListener) {
            inputListener = (OnUserInputListener) context;
        }
    }

    @Override
    public void onDetach() {
        inputListener = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        rowsView = (EditText) view.findViewById(R.id.et_rows);
        colsView = (EditText) view.findViewById(R.id.et_cols);
        rowStartPositionView = (EditText) view.findViewById(R.id.et_row_position);
        colStartPositionView = (EditText) view.findViewById(R.id.et_col_position);

        rowsView.addTextChangedListener(textChangeListener);
        colsView.addTextChangedListener(textChangeListener);
        rowStartPositionView.addTextChangedListener(textChangeListener);
        colStartPositionView.addTextChangedListener(textChangeListener);
        return view;
    }

    public interface OnUserInputListener {
        void onUserInput(String rows, String cols, String startRow, String startCol);
    }

}
