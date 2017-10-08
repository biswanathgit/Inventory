package biwanath.com.inventory.ui.productview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import biwanath.com.inventory.R;
import biwanath.com.inventory.data.productrepository.Product;

/**
 * Created by Biswanath Maity on 10/9/2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ItemViewHolder> {
    private List<Product> productList;
    private Context context;

    public ProductListAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final Product item = productList.get(position);
        holder.itemName.setText(item.getProductName());
        holder.itemDec.setText(item.getDescription());
        holder.itemPrice.setText("Rs. "+ item.getProductPrice());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public TextView itemPrice;
        public TextView itemDec;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.txt_product_name);
            itemDec = itemView.findViewById(R.id.txt_product_description);
            itemPrice = itemView.findViewById(R.id.txt_product_price);
        }
    }
}
