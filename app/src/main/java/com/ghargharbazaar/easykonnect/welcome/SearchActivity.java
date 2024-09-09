package com.ghargharbazaar.easykonnect.welcome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.ghargharbazaar.easykonnect.R;
import com.ghargharbazaar.easykonnect.model.BatchModel;
import com.ghargharbazaar.easykonnect.model.ProductMembershipModel;
import com.ghargharbazaar.easykonnect.model.ProductModel;
import com.ghargharbazaar.easykonnect.utils.Config;
import com.ghargharbazaar.easykonnect.utils.ProgressDialog;
import com.ghargharbazaar.easykonnect.utils.UserDetails;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {

    EditText search_edit;
    ImageView back_button;
    ArrayList<ProductModel> productModels;
    ArrayList<ProductModel> selectedProductModels;
    RecyclerView recyclerView;
    UserDetails userDetails;
    LinearLayout no_products, progress_layout;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        userDetails = new UserDetails(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        progress_layout = (LinearLayout) findViewById(R.id.progress_layout);
        no_products = (LinearLayout) findViewById(R.id.no_products);
        search_edit = (EditText) findViewById(R.id.search_edit);
        back_button = (ImageView) findViewById(R.id.back_button);
        selectedProductModels = new ArrayList<>();
        productModels = new ArrayList<>();
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("SM", selectedProductModels);
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        no_products.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        progress_layout.setVisibility(View.GONE);
        selectedProductModels = (ArrayList<ProductModel>) getIntent().getExtras().getSerializable("SM");
        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (search_edit.getText().toString().trim().length() < 1) {
                    productModels.clear();
                } else {
                    searchProduct(search_edit.getText().toString().trim());
                }
            }
        });
        productAdapter = new ProductAdapter();
        recyclerView.setAdapter(productAdapter);
    }

    public void searchProduct(String key) {
        progress_layout.setVisibility(View.VISIBLE);
        no_products.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
        StringRequest sr = new StringRequest(Request.Method.GET, Config.SEARCH_PRODUCT + key, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progress_layout.setVisibility(View.GONE);
                System.out.println("Response  " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("statusCode").equalsIgnoreCase("0")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        productModels.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            JSONArray batches = object.getJSONArray("batches");
                            ArrayList<BatchModel> batchModels = new ArrayList<>();
                            for (int j = 0; j < batches.length(); j++) {
                                JSONObject batch = batches.getJSONObject(j);
                                batchModels.add(new BatchModel(batch.getString("idproduct_batch"), batch.getString("idproduct_master"), batch.getString("idstore_warehouse"), batch.getString("name"), batch.getString("purchase_price"), batch.getString("selling_price"), batch.getString("mrp"), batch.getString("product"), batch.getString("copartner"), batch.getString("land"), batch.getString("discount"), batch.getString("quantity"), batch.getString("expiry"), batch.getString("created_at"), batch.getString("updated_at"), batch.getString("created_by"), batch.getString("updated_by"), batch.getString("status"), batch.getString("instant")));
                            }
                            BatchModel batch_sel = null;
                            if (object.has("selected_batch") && !object.isNull("selected_batch")) {
                                JSONObject batch = object.getJSONObject("selected_batch");
                                batch_sel = new BatchModel(batch.getString("idproduct_batch"), batch.getString("idproduct_master"), batch.getString("idstore_warehouse"), batch.getString("name"), batch.getString("purchase_price"), batch.getString("selling_price"), batch.getString("mrp"), batch.getString("product"), batch.getString("copartner"), batch.getString("land"), batch.getString("discount"), batch.getString("quantity"), batch.getString("expiry"), batch.getString("created_at"), batch.getString("updated_at"), batch.getString("created_by"), batch.getString("updated_by"), batch.getString("status"), batch.getString("instant"));
                            }
                            JSONArray member_price = object.getJSONArray("member_price");
                            ArrayList<ProductMembershipModel> productMembershipModels = new ArrayList<>();
                            for (int k = 0; k < member_price.length(); k++) {
                                JSONObject member = member_price.getJSONObject(k);
                                productMembershipModels.add(new ProductMembershipModel(member.getString("name"), member.getString("price"), member.getString("instant_discount"), member.getString("type")));
                            }
                            int s = 0;
                            if (selectedProductModels.size() > 0) {
                                for (int l = 0; l < selectedProductModels.size(); l++) {
                                    if (selectedProductModels.get(l).getIdproduct_master().equalsIgnoreCase(object.getString("idproduct_master"))) {
                                        s = selectedProductModels.get(l).getSel();
                                    }
                                }
                            }
                            productModels.add(new ProductModel(object.getString("idbrand"), object.getString("brand"), object.getString("idproduct_master"), object.getString("idcategory"), object.getString("category"), object.getString("idsub_category"), object.getString("scategory"), object.getString("idsub_sub_category"), object.getString("sscategory"), object.getString("prod_name"), object.getString("description"), object.getString("barcode"), object.getString("hsn"), object.getString("sgst"), object.getString("cgst"), object.getString("igst"), object.getString("status"), object.getString("quantity"), object.getString("idinventory"), object.getString("selling_price"), object.getString("purchase_price"), object.getString("mrp"), object.getString("product"), object.getString("copartner"), object.getString("land"), object.getString("discount"), object.getString("instant_discount_percent"), object.getString("listing_type"), object.getString("origListType"), object.getString("sellingPriceForInstantDisc"), batchModels, productMembershipModels, batch_sel, object.getString("instant"), s));
                        }

                        if (productModels.size() > 0) {
                            no_products.setVisibility(View.GONE);
                            productAdapter.notifyDataSetChanged();
                            recyclerView.setVisibility(View.VISIBLE);
                        } else {
                            no_products.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }

                    } else {
                        no_products.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    progress_layout.setVisibility(View.GONE);
                    no_products.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress_layout.setVisibility(View.GONE);
                no_products.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                //Toast.makeText(getApplicationContext(), "Network Connection Error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + userDetails.getBearer_token());
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        queue.add(sr);
    }

    class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {


        @NonNull
        @Override
        public ProductAdapter.ProductHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_row, viewGroup, false);
            return new ProductAdapter.ProductHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductAdapter.ProductHolder productHolder, @SuppressLint("RecyclerView") final int i) {
            if (productModels.get(i).getIdproduct_master().equalsIgnoreCase("-1")) {
                productHolder.product_card.setVisibility(View.GONE);
                productHolder.progress_bar.setVisibility(View.VISIBLE);
            } else {
                productHolder.product_card.setVisibility(View.VISIBLE);
                productHolder.progress_bar.setVisibility(View.GONE);
                productHolder.title.setText(productModels.get(i).getProd_name().trim());
                productHolder.cutprice.setText("\u20b9" + productModels.get(i).getMrp());
                productHolder.price.setText("\u20b9" + productModels.get(i).getInstant());
                if (parsePrice(productModels.get(i).getMrp()) <= parsePrice(productModels.get(i).getInstant())) {
                    productHolder.cutprice.setVisibility(View.INVISIBLE);
                    productHolder.discountlayout.setVisibility(View.GONE);
                } else {
                    productHolder.discountlayout.setVisibility(View.VISIBLE);
                    productHolder.discount.setText(getDiscountPrice(productModels.get(i).getMrp(), productModels.get(i).getInstant()) + "%\nOFF");
                    productHolder.cutprice.setVisibility(View.VISIBLE);
                }
                productHolder.product_cashback.setText("Product\n\u20b9" + getCashback(productModels.get(i).getInstant(), productModels.get(i).getProduct()));
                productHolder.copartner_cashback.setText("CoPartner\n\u20b9" + getCashback(productModels.get(i).getInstant(), productModels.get(i).getCopartner()));
                productHolder.land_cashback.setText("Land\n\u20b9" + getCashback(productModels.get(i).getInstant(), productModels.get(i).getLand()));

                productHolder.cutprice.setPaintFlags(productHolder.cutprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                if (parsePrice(productModels.get(i).getQuantity()) <= 0) {
                    productHolder.outofstock.setVisibility(View.VISIBLE);
                } else {
                    productHolder.outofstock.setVisibility(View.GONE);
                }
                if (productModels.get(i).getSel() == 0) {
                    productHolder.q_lay.setVisibility(View.INVISIBLE);
                    productHolder.add_txt.setVisibility(View.VISIBLE);
                } else {
                    productHolder.q_lay.setVisibility(View.VISIBLE);
                    productHolder.q_txt.setVisibility(View.VISIBLE);
                    productHolder.q_txt.setText("" + productModels.get(i).getSel());
                    productHolder.add_txt.setVisibility(View.INVISIBLE);
                }
                productHolder.add_progress.setVisibility(View.INVISIBLE);
                Glide.with(getApplicationContext()).load(Config.PRODUCT_IMG_URL + productModels.get(i).getBarcode() + ".jpg").placeholder(R.drawable.grocery_pack).dontAnimate().into(productHolder.image);
                productHolder.q_txt.setText("" + productModels.get(i).getSel());
                productHolder.add_txt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (parsePrice(productModels.get(i).getQuantity()) > 0) {
                            if (productModels.get(i).getSel() == 0) {
                                selectedProductModels.add(productModels.get(i));
                            }
                            productModels.get(i).setSel(1);
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(getApplicationContext(), "Quantity Exhausted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                productHolder.plls.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (parsePrice(productModels.get(i).getQuantity()) > productModels.get(i).getSel()) {
                            int qty = productModels.get(i).getSel() + 1;
                            productModels.get(i).setSel(qty);
                            notifyDataSetChanged();
                            if (selectedProductModels.size() > 0) {
                                for (int l = 0; l < selectedProductModels.size(); l++) {
                                    if (selectedProductModels.get(l).getIdproduct_master().equalsIgnoreCase(productModels.get(i).getIdproduct_master())) {
                                        selectedProductModels.get(l).setSel(qty);
                                    }
                                }
                            }
                            System.out.println("Position2 " + productModels.get(i).getSel());
                        } else {
                            Toast.makeText(getApplicationContext(), "Quantity Exhausted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                productHolder.mins.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (productModels.get(i).getSel() > 0) {
                            int qty = productModels.get(i).getSel() - 1;
                            productModels.get(i).setSel(qty);
                            notifyDataSetChanged();
                            if (selectedProductModels.size() > 0) {
                                for (int l = 0; l < selectedProductModels.size(); l++) {
                                    if (selectedProductModels.get(l).getIdproduct_master().equalsIgnoreCase(productModels.get(i).getIdproduct_master())) {
                                        if (productModels.get(i).getSel() > 1) {
                                            selectedProductModels.get(l).setSel(qty);
                                        } else {
                                            selectedProductModels.remove(l);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
                if (parsePrice(productModels.get(i).getQuantity()) > 0) {
                    productHolder.product_card.setAlpha(1.0f);
                    productHolder.cart_lay.setVisibility(View.VISIBLE);
                } else {
                    productHolder.product_card.setAlpha(0.5f);
                    productHolder.cart_lay.setVisibility(View.INVISIBLE);
                }
            }
        }

        @Override
        public int getItemCount() {
            return productModels.size();
        }

        public class ProductHolder extends RecyclerView.ViewHolder {

            ImageView image;
            TextView discount, outofstock, title, price, cutprice, q_txt, product_cashback, copartner_cashback, land_cashback;
            FrameLayout discountlayout;
            LinearLayout q_lay, add_txt;
            ImageView mins, plls;
            RelativeLayout cart_lay;
            ProgressBar add_progress, progress_bar;
            CardView product_card;

            public ProductHolder(@NonNull View itemView) {
                super(itemView);
                cart_lay = (RelativeLayout) itemView.findViewById(R.id.cart_lay);
                image = (ImageView) itemView.findViewById(R.id.image);
                product_card = (CardView) itemView.findViewById(R.id.product_card);
                discount = (TextView) itemView.findViewById(R.id.discount);
                outofstock = (TextView) itemView.findViewById(R.id.outofstock);
                copartner_cashback = (TextView) itemView.findViewById(R.id.copartner_cashback);
                land_cashback = (TextView) itemView.findViewById(R.id.land_cashback);
                product_cashback = (TextView) itemView.findViewById(R.id.product_cashback);
                q_txt = (TextView) itemView.findViewById(R.id.q_txt);
                title = (TextView) itemView.findViewById(R.id.title);
                discountlayout = (FrameLayout) itemView.findViewById(R.id.discountlayout);
                price = (TextView) itemView.findViewById(R.id.price);
                cutprice = (TextView) itemView.findViewById(R.id.cutprice);
                mins = (ImageView) itemView.findViewById(R.id.mins);
                plls = (ImageView) itemView.findViewById(R.id.plls);
                q_lay = (LinearLayout) itemView.findViewById(R.id.q_lay);
                add_txt = (LinearLayout) itemView.findViewById(R.id.add_txt);
                add_progress = (ProgressBar) itemView.findViewById(R.id.add_progress);
                progress_bar = (ProgressBar) itemView.findViewById(R.id.progress_bar);
            }
        }

        public float parsePrice(String s) {
            try {
                float n = Float.parseFloat(s);
                return n;
            } catch (Exception e) {
                return 0;
            }
        }

        public int getDiscountPrice(String mrp, String sell_price) {
            try {
                float f = Float.parseFloat(mrp);
                float n = Float.parseFloat(sell_price);
                int per = (int) (((f - n) / f) * 100);
                return per;
            } catch (Exception e) {
                return 0;
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("SM", selectedProductModels);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public float getCashback(String s, String s1) {
        try {
            float n = Float.parseFloat(s);
            float n1 = Float.parseFloat(s1);
            float y1 = n - n1;
            DecimalFormat df = new DecimalFormat("#.00");
            y1 = Float.valueOf(df.format(y1));
            return y1 > 0 ? y1 : 0;
        } catch (Exception e) {
            return 0;
        }
    }
}