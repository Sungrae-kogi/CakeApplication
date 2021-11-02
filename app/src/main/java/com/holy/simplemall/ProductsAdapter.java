package com.holy.simplemall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

//Adapter 구현
public class ProductsAdapter extends ListAdapter<Product, ProductsAdapter.ProductViewHolder> {

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        // 케이크 이름 텍스트 뷰, 이미지 뷰
        private final TextView textViewTitle;
        private final ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            this.textViewTitle = itemView.findViewById(R.id.textViewProductTitle);
            this.imageView = itemView.findViewById(R.id.imageViewProduct);
        }

        public void bind(Product product) {
            // 아이템 뷰에 케이크 정보 입력
            textViewTitle.setText(product.getTitle());
            imageView.setImageResource(product.getResource());
        }
    }

    public ProductsAdapter() {
        super(new DiffUtilCallback());
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    static class DiffUtilCallback extends DiffUtil.ItemCallback<Product> {

        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals(newItem);
        }
    }

}