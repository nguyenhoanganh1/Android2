package com.project.myapplication.adaper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.project.myapplication.R;
import com.project.myapplication.model.ProductModel;
import com.squareup.picasso.Picasso;

public class ProductAdapter extends ArrayAdapter<ProductModel.Data> {

    Activity context;
    int resource;

    public ProductAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = (Activity) context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View view = layoutInflater.inflate(this.resource,null);


        TextView txtProductName = view.findViewById(R.id.txtName);
        ImageView imgProduct = view.findViewById(R.id.imageView);
        TextView txtProductGia = view.findViewById(R.id.txtPrice);

        ProductModel.Data productModel = getItem(position);
        Picasso.get().load(productModel.getImage()).into(imgProduct);
        txtProductName.setText(productModel.getName());
        txtProductGia.setText(productModel.getUnitPrice());
        return view;
    }


}
