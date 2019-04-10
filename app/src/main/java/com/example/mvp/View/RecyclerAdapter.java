package com.example.mvp.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvp.Model.DataItem;
import com.example.mvp.Presenter.IPresenter;
import com.example.mvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private IPresenter presenter;

    RecyclerAdapter(IPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        DataItem item = presenter.getItem(position);
        viewHolder.field.setText(item.getField());
        viewHolder.description.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return presenter.getItemCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.field)
        TextView field;
        @BindView(R.id.description)
        TextView description;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
