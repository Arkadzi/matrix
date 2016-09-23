package me.arkadii.gumenniy.matrix.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.arkadii.gumenniy.matrix.R;

/**
 * Created by sebastian on 21.09.16.
 */
public class MatrixAdapter extends RecyclerView.Adapter<MatrixAdapter.MatrixViewHolder> {
    private final List<Integer> numbers = new ArrayList<>();
    private final LayoutInflater inflater;
    private final int margin;
    private GridLayoutManager layoutManager;
    private int gridHeight;
    private int itemHeight;

    public MatrixAdapter(Context context, GridLayoutManager layoutManager, int gridHeight) {
        this.layoutManager = layoutManager;
        this.gridHeight = gridHeight;
        margin = context.getResources().getDimensionPixelSize(R.dimen.matrix_margin);
        inflater = LayoutInflater.from(context);

    }
    @Override
    public MatrixViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView view = (TextView) inflater.inflate(R.layout.item_matrix, parent, false);

        return new MatrixViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MatrixViewHolder holder, int position) {
        holder.itemView.setText(String.valueOf(numbers.get(position)));
        holder.itemView.getLayoutParams().height = itemHeight;
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    public void setMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            Log.e("adapter", Arrays.toString(ints));
        }
        int previousSize = numbers.size();
        numbers.clear();
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                numbers.add(anInt);
            }
        }
        int currentSize = numbers.size();
        int spanCount = currentSize / matrix.length;
        itemHeight = (gridHeight - 2 * matrix.length * margin) / matrix.length;
        layoutManager.setSpanCount(spanCount);
        if (previousSize == 0) {
            notifyDataSetChanged();
        } else if (previousSize < currentSize) {
            notifyItemRangeChanged(0, previousSize);
            notifyItemRangeInserted(previousSize, currentSize - previousSize);
        } else {
            notifyItemRangeChanged(0, currentSize);
            if (previousSize > currentSize) {
                notifyItemRangeRemoved(currentSize, previousSize - currentSize);
            }
        }
    }

    static class MatrixViewHolder extends RecyclerView.ViewHolder {

        private final TextView itemView;

        public MatrixViewHolder(TextView itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
