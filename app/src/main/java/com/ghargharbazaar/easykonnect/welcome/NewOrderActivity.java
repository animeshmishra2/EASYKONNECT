package com.ghargharbazaar.easykonnect.welcome;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.ghargharbazaar.easykonnect.R;
import com.ghargharbazaar.easykonnect.base.ScanActivity;
import com.ghargharbazaar.easykonnect.model.BatchModel;
import com.ghargharbazaar.easykonnect.model.GSTModel;
import com.ghargharbazaar.easykonnect.model.PackageModel;
import com.ghargharbazaar.easykonnect.model.ProductMembershipModel;
import com.ghargharbazaar.easykonnect.model.ProductModel;
import com.ghargharbazaar.easykonnect.model.TaggedProductsModel;
import com.ghargharbazaar.easykonnect.model.TriggeredProductModel;
import com.ghargharbazaar.easykonnect.utils.Config;
import com.ghargharbazaar.easykonnect.utils.ProgressDialog;
import com.ghargharbazaar.easykonnect.utils.UserDetails;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NewOrderActivity extends AppCompatActivity {

    CardView search_card;
    LinearLayout barcode_lay;
    ImageView back_button;
    EditText search_edit;
    UserDetails userDetails;
    ProgressDialog pd;
    ArrayList<ProductModel> productModels;
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    LinearLayout no_products;

    //Order Layout
    LinearLayout use_package, pkg_lay, use_coupon, coupon_lay, use_discount, discount_lay, cashback_layout, order_layout, hold_order, empty_cart, place_order, mobile_layout, detail_layout;
    TextView cashback, remove_pkg, remove_coupon, discount_name, remove_discount, mrp_total, cgst, sgst, discount, extra_discount, grand_total, user_name, membership, locked_wallet, redeem_wallet, remove_customer, save_customer;

    EditText mobile_number;
    LinearLayout desc_layout;
    String user_id = "";
    String membership_id = "";
    ArrayList<PackageModel> packageModels;
    ArrayList<TaggedProductsModel> packageApplyModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        productModels = new ArrayList<>();
        packageApplyModel = new ArrayList<>();
        userDetails = new UserDetails(getApplicationContext());
        initializeVars();
        user_id = "";
        membership_id = "";
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        no_products = (LinearLayout) findViewById(R.id.no_products);
        barcode_lay = (LinearLayout) findViewById(R.id.barcode_lay);
        search_edit = (EditText) findViewById(R.id.search_edit);
        search_card = (CardView) findViewById(R.id.search_card);
        back_button = (ImageView) findViewById(R.id.back_button);
        search_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("SM", productModels);
                intent.putExtras(bundle);
                startActivityForResult(intent, 25);
            }
        });
        search_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("SM", productModels);
                intent.putExtras(bundle);
                startActivityForResult(intent, 25);
            }
        });
        barcode_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanBarcode();
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        empty_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productModels.clear();
                user_id = "";
                membership_id = "";
                calculatePrice();
            }
        });
        save_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mobile_number.getText().toString().trim().length() == 10) {
                    getCustomer(mobile_number.getText().toString().trim());
                } else {
                    Toast.makeText(getApplicationContext(), "Enter Valid Mobile", Toast.LENGTH_SHORT).show();
                }
            }
        });
        remove_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_id = "";
                membership_id = "";
                mobile_number.setText("");
                detail_layout.setVisibility(View.GONE);
                mobile_layout.setVisibility(View.VISIBLE);
                calculatePrice();
            }
        });
        packageModels = new ArrayList<>();
        getActivePackage();
        productAdapter = new ProductAdapter();
        recyclerView.setAdapter(productAdapter);
        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PrintBillActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ST", generateBillContent());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    public void initializeVars() {
        desc_layout = (LinearLayout) findViewById(R.id.desc_layout);
        use_package = (LinearLayout) findViewById(R.id.use_package);
        cashback_layout = (LinearLayout) findViewById(R.id.cashback_layout);
        cashback = (TextView) findViewById(R.id.cashback);
        pkg_lay = (LinearLayout) findViewById(R.id.pkg_lay);
        use_coupon = (LinearLayout) findViewById(R.id.use_coupon);
        coupon_lay = (LinearLayout) findViewById(R.id.coupon_lay);
        use_discount = (LinearLayout) findViewById(R.id.use_discount);
        discount_lay = (LinearLayout) findViewById(R.id.discount_lay);
        order_layout = (LinearLayout) findViewById(R.id.order_layout);
        hold_order = (LinearLayout) findViewById(R.id.hold_order);
        empty_cart = (LinearLayout) findViewById(R.id.empty_cart);
        place_order = (LinearLayout) findViewById(R.id.place_order);
        mobile_layout = (LinearLayout) findViewById(R.id.mobile_layout);
        detail_layout = (LinearLayout) findViewById(R.id.detail_layout);
        remove_pkg = (TextView) findViewById(R.id.remove_pkg);
        remove_coupon = (TextView) findViewById(R.id.remove_coupon);
        discount_name = (TextView) findViewById(R.id.discount_name);
        remove_discount = (TextView) findViewById(R.id.remove_discount);
        mrp_total = (TextView) findViewById(R.id.mrp_total);
        cgst = (TextView) findViewById(R.id.cgst);
        sgst = (TextView) findViewById(R.id.sgst);
        discount = (TextView) findViewById(R.id.discount);
        extra_discount = (TextView) findViewById(R.id.extra_discount);
        grand_total = (TextView) findViewById(R.id.grand_total);
        user_name = (TextView) findViewById(R.id.user_name);
        membership = (TextView) findViewById(R.id.membership);
        locked_wallet = (TextView) findViewById(R.id.locked_wallet);
        redeem_wallet = (TextView) findViewById(R.id.redeem_wallet);
        remove_customer = (TextView) findViewById(R.id.remove_customer);
        save_customer = (TextView) findViewById(R.id.save_customer);
        mobile_number = (EditText) findViewById(R.id.mobile_number);
        use_coupon.setVisibility(View.VISIBLE);
        pkg_lay.setVisibility(View.GONE);
        use_package.setVisibility(View.VISIBLE);
        coupon_lay.setVisibility(View.GONE);
        use_discount.setVisibility(View.VISIBLE);
        discount_lay.setVisibility(View.GONE);
        mobile_layout.setVisibility(View.VISIBLE);
        detail_layout.setVisibility(View.GONE);
        desc_layout.setVisibility(View.GONE);
        order_layout.setVisibility(View.GONE);
    }

    public void scanBarcode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume Up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(ScanActivity.class);
        barcodeLauncher.launch(options);
    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() == null) {
            Toast.makeText(NewOrderActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
        } else {
            searchProduct(result.getContents().trim());
        }
    });

    public void searchProduct(String barcode) {
        pd = ProgressDialog.show(NewOrderActivity.this, "Please Wait...");
        RequestQueue queue = Volley.newRequestQueue(NewOrderActivity.this);
        StringRequest sr = new StringRequest(Request.Method.GET, Config.SEARCH_PRODUCT + barcode + "/0/1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                System.out.println("Response  " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("statusCode").equalsIgnoreCase("0")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        if (jsonArray.length() > 0) {
                            JSONObject object = jsonArray.getJSONObject(0);
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
                            int pp = 0;
                            for (int i = 0; i < productModels.size(); i++) {
                                if (object.getString("idproduct_master").trim().equalsIgnoreCase(productModels.get(i).getIdproduct_master().trim())) {
                                    pp = 1;
                                    if (parsePrice(object.getString("quantity")) > productModels.get(i).getSel()) {
                                        productModels.get(i).setSel(productModels.get(i).getSel() + 1);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Quantity Exhausted", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            if (pp == 0) {
                                if (parsePrice(object.getString("quantity")) > 0) {
                                    productModels.add(new ProductModel(object.getString("idbrand"), object.getString("brand"), object.getString("idproduct_master"), object.getString("idcategory"), object.getString("category"), object.getString("idsub_category"), object.getString("scategory"), object.getString("idsub_sub_category"), object.getString("sscategory"), object.getString("prod_name"), object.getString("description"), object.getString("barcode"), object.getString("hsn"), object.getString("sgst"), object.getString("cgst"), object.getString("igst"), object.getString("status"), object.getString("quantity"), object.getString("idinventory"), object.getString("selling_price"), object.getString("purchase_price"), object.getString("mrp"), object.getString("product"), object.getString("copartner"), object.getString("land"), object.getString("discount"), object.getString("instant_discount_percent"), object.getString("listing_type"), object.getString("origListType"), object.getString("sellingPriceForInstantDisc"), batchModels, productMembershipModels, batch_sel, object.getString("instant"), 1));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Quantity Exhausted", Toast.LENGTH_SHORT).show();
                                }
                            }
                            productAdapter.notifyDataSetChanged();
                        }
                        if (productModels.size() > 0) {
                            no_products.setVisibility(View.GONE);
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
                    no_products.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                calculatePrice();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), "Network Connection Error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
                no_products.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                calculatePrice();
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

    public void getActivePackage() {
        pd = ProgressDialog.show(NewOrderActivity.this, "Please Wait...");
        RequestQueue queue = Volley.newRequestQueue(NewOrderActivity.this);
        StringRequest sr = new StringRequest(Request.Method.GET, Config.ACTIVE_PACKAGE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                System.out.println("Response  " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    packageModels.clear();
                    if (jsonObject.getString("statusCode").equalsIgnoreCase("0")) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        packageModels.clear();
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject object = data.getJSONObject(i);
                            JSONArray tagged_prod = object.getJSONArray("tagged_prod");
                            ArrayList<TaggedProductsModel> taggedProductsModels = new ArrayList<>();
                            for (int j = 0; i < tagged_prod.length(); i++) {
                                JSONObject tagged = tagged_prod.getJSONObject(j);
                                taggedProductsModels.add(new TaggedProductsModel(tagged.getString("idpackage_prod_list"), tagged.getString("package_item_qty"), tagged.getString("idbrand"), tagged.getString("idproduct_master"), tagged.getString("idcategory"), tagged.getString("idsub_category"), tagged.getString("idsub_sub_category"), tagged.getString("prod_name"), tagged.getString("description"), tagged.getString("barcode"), tagged.getString("hsn"), tagged.getString("sgst"), tagged.getString("cgst"), tagged.getString("is_veg"), tagged.getString("status"), tagged.getString("quantity"), tagged.getString("idinventory"), tagged.getString("selling_price"), tagged.getString("mrp"), tagged.getString("discount"), tagged.getString("idproduct_batch")));
                            }
                            JSONArray trigger_prod = object.getJSONArray("trigger_prod");
                            ArrayList<TriggeredProductModel> triggeredProductModels = new ArrayList<>();
                            for (int j = 0; i < trigger_prod.length(); i++) {
                                JSONObject triggered = trigger_prod.getJSONObject(j);
                                triggeredProductModels.add(new TriggeredProductModel(triggered.getString("idpackage_prod_list"), triggered.getString("package_item_qty"), triggered.getString("idbrand"), triggered.getString("idproduct_master"), triggered.getString("idcategory"), triggered.getString("idsub_category"), triggered.getString("idsub_sub_category"), triggered.getString("prod_name"), triggered.getString("description"), triggered.getString("barcode"), triggered.getString("hsn"), triggered.getString("sgst"), triggered.getString("cgst"), triggered.getString("is_veg"), triggered.getString("status"), triggered.getString("quantity"), triggered.getString("idinventory"), triggered.getString("selling_price"), triggered.getString("mrp"), triggered.getString("discount"), triggered.getString("idproduct_batch")));
                            }
                            packageModels.add(new PackageModel(object.getString("idpackage"), object.getString("idpackage_master"), object.getString("idstore_warehouse"), object.getString("name"), object.getString("applicable_on"), object.getString("frequency"), object.getString("base_trigger_amount"), object.getString("additional_tag_amount"), object.getString("bypass_make_gen"), object.getString("valid_from"), object.getString("valid_till"), object.getString("created_at"), object.getString("updated_at"), object.getString("created_by"), object.getString("updated_by"), object.getString("status"), taggedProductsModels, triggeredProductModels));
                        }
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                calculatePrice();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                calculatePrice();
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


    public void getCustomer(String mobile) {
        pd = ProgressDialog.show(NewOrderActivity.this, "Please Wait...");
        RequestQueue queue = Volley.newRequestQueue(NewOrderActivity.this);
        StringRequest sr = new StringRequest(Request.Method.GET, Config.GET_CUSTOMER + mobile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                System.out.println("Response  " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("statusCode").equalsIgnoreCase("0")) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        user_id = data.getString("idcustomer");
                        membership_id = data.getString("idmembership_plan");
                        user_name.setText(data.getString("name"));
                        membership.setText(data.getString("membership_type"));
                        locked_wallet.setText(data.getString("wallet_balance"));
                        redeem_wallet.setText(data.getString("redeemWallet"));
                        mobile_layout.setVisibility(View.GONE);
                        detail_layout.setVisibility(View.VISIBLE);
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                calculatePrice();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                calculatePrice();
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
                productHolder.product_cashback.setText("Product\n\u20b9" + getCashback(productModels.get(i).getInstant(), productModels.get(i).getProduct()));
                productHolder.copartner_cashback.setText("CoPartner\n\u20b9" + getCashback(productModels.get(i).getInstant(), productModels.get(i).getCopartner()));
                productHolder.land_cashback.setText("Land\n\u20b9" + getCashback(productModels.get(i).getInstant(), productModels.get(i).getLand()));

                productHolder.add_progress.setVisibility(View.INVISIBLE);
                Glide.with(getApplicationContext()).load(Config.PRODUCT_IMG_URL + productModels.get(i).getBarcode() + ".jpg").placeholder(R.drawable.grocery_pack).dontAnimate().into(productHolder.image);
                productHolder.q_txt.setText("" + productModels.get(i).getSel());
                productHolder.add_txt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (parsePrice(productModels.get(i).getQuantity()) > 0) {
                            productModels.get(i).setSel(1);
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(getApplicationContext(), "Quantity Exhausted", Toast.LENGTH_SHORT).show();
                        }
                        calculatePrice();
                    }
                });
                productHolder.plls.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (parsePrice(productModels.get(i).getQuantity()) > productModels.get(i).getSel()) {
                            productModels.get(i).setSel(productModels.get(i).getSel() + 1);
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(getApplicationContext(), "Quantity Exhausted", Toast.LENGTH_SHORT).show();
                        }
                        calculatePrice();
                    }
                });
                productHolder.mins.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (productModels.get(i).getSel() > 0) {
                            productModels.get(i).setSel(productModels.get(i).getSel() - 1);
                            if (productModels.get(i).getSel() == 0) {
                                productModels.remove(i);
                            }
                            notifyDataSetChanged();
                        }
                        calculatePrice();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 25 && resultCode == RESULT_OK) {
            productModels.clear();
            System.out.println("Animesh " + ((ArrayList<ProductModel>) data.getExtras().getSerializable("SM")).size());
            productModels.addAll((ArrayList<ProductModel>) data.getExtras().getSerializable("SM"));
            productAdapter.notifyDataSetChanged();
            if (productModels.size() > 0) {
                no_products.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            } else {
                no_products.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
            calculatePrice();
        }
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

    public float parsePrice(String s) {
        try {
            float n = Float.parseFloat(s);
            return n;
        } catch (Exception e) {
            return 0;
        }
    }

    public void calculatePrice() {
        if (productModels.size() > 0) {
            desc_layout.setVisibility(View.VISIBLE);
            order_layout.setVisibility(View.VISIBLE);
            no_products.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            float mrp_val = 0;
            float cgst_val = 0;
            float sgst_val = 0;
            float discount_val = 0;
            float extra_disc_val = 0;
            float grand_total_val = 0;
            float cashback_val = 0;
            for (int i = 0; i < productModels.size(); i++) {
                mrp_val = mrp_val + (productModels.get(i).getSel() * parsePrice(productModels.get(i).getMrp()));
                float price_without_gst = (parsePrice(productModels.get(i).getInstant())) * (100 / (100 + (parsePrice(productModels.get(i).getCgst()) + parsePrice(productModels.get(i).getSgst()))));
                float current_cgst = (parsePrice(productModels.get(i).getInstant())) - price_without_gst;
                sgst_val = sgst_val + (productModels.get(i).getSel() * (current_cgst / 2));
                cgst_val = cgst_val + (productModels.get(i).getSel() * (current_cgst / 2));
                discount_val = discount_val + ((productModels.get(i).getSel() * parsePrice(productModels.get(i).getMrp())) - (productModels.get(i).getSel() * parsePrice(productModels.get(i).getInstant())));
                extra_disc_val = extra_disc_val + 0;
                if (findMembership(membership_id) == 2) {
                    cashback_val = cashback_val + productModels.get(i).getSel() * (parsePrice(productModels.get(i).getInstant()) - parsePrice(productModels.get(i).getProduct()));
                } else if (findMembership(membership_id) == 3) {
                    cashback_val = cashback_val + productModels.get(i).getSel() * (parsePrice(productModels.get(i).getInstant()) - parsePrice(productModels.get(i).getLand()));
                } else if (findMembership(membership_id) == 4) {
                    cashback_val = cashback_val + productModels.get(i).getSel() * (parsePrice(productModels.get(i).getInstant()) - parsePrice(productModels.get(i).getCopartner()));
                } else {
                    cashback_val = cashback_val + productModels.get(i).getSel() * (parsePrice(productModels.get(i).getInstant()) - parsePrice(productModels.get(i).getInstant()));
                }
            }
            grand_total_val = mrp_val - discount_val - extra_disc_val;
            DecimalFormat df = new DecimalFormat("#.00");
            mrp_total.setText("\u20b9" + Float.valueOf(df.format(mrp_val)));
            cgst.setText("\u20b9" + Float.valueOf(df.format(cgst_val)));
            sgst.setText("\u20b9" + Float.valueOf(df.format(sgst_val)));
            discount.setText("\u20b9" + Float.valueOf(df.format(discount_val)));
            extra_discount.setText("\u20b9" + Float.valueOf(df.format(extra_disc_val)));
            cashback.setText("\u20b9" + Float.valueOf(df.format(cashback_val)));
            grand_total.setText("\u20b9" + Float.valueOf(df.format(grand_total_val)));
            if (findMembership(membership_id) > 1) {
                cashback_layout.setVisibility(View.VISIBLE);
            } else {
                cashback_layout.setVisibility(View.GONE);
            }
        } else {
            desc_layout.setVisibility(View.GONE);
            order_layout.setVisibility(View.GONE);
            no_products.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    public int findMembership(String mm_id) {
        try {
            int n = Integer.parseInt(mm_id);
            return n;
        } catch (Exception e) {
            return 0;
        }
    }

    private String generateBillContent() {
        // Generate dynamic bill content here
        StringBuilder billContentBuilder = new StringBuilder();
        billContentBuilder.append("          GST INVOICE           ");
        billContentBuilder.append("\n");
        billContentBuilder.append("        GHAR GHAR BAZAAR        ");
        billContentBuilder.append("\n");
        billContentBuilder.append(padString(userDetails.getStore_name(), 31));
        billContentBuilder.append("\n");
        billContentBuilder.append(padString(userDetails.getAddress(), 31));
        billContentBuilder.append("\n");
        billContentBuilder.append(padString("GSTIN : 09AAICG0011C1ZB", 31));
        billContentBuilder.append("\n");
        billContentBuilder.append("\n");
        billContentBuilder.append("-------------------------------");
        billContentBuilder.append("\n");
        billContentBuilder.append("Customer : " + user_name.getText().toString().trim());
        billContentBuilder.append("\n");
        billContentBuilder.append("Bill: 2/1689   Date: " + getDates());
        billContentBuilder.append("\n");
        billContentBuilder.append("User: " + padString(userDetails.getName(), 8) + " Time: " + getTimes());
        billContentBuilder.append("\n");
        billContentBuilder.append("-------------------------------");
        billContentBuilder.append("\n");
        billContentBuilder.append("MRP   QTY   RATE   DISC   FINAL");
        billContentBuilder.append("\n");
        billContentBuilder.append("-------------------------------");
        billContentBuilder.append("\n");
        float zero_gst = 0, five_gst = 0, tw_gst = 0, ei_gst = 0;
        float zero_tax = 0, five_tax = 0, tw_tax = 0, ei_tax = 0;
        float zero_tot = 0, five_tot = 0, tw_tot = 0, ei_tot = 0;
        int tqt = 0;
        float mrp_total = 0;
        float pay_total = 0;
        DecimalFormat df = new DecimalFormat("#.00");
        for (int i = 0; i < productModels.size(); i++) {
            String product_name = truncate(productModels.get(i).getProd_name());
            String mrp = productModels.get(i).getMrp();
            String sp = productModels.get(i).getInstant();
            String dis = "" + (productModels.get(i).getSel() * (parsePrice(productModels.get(i).getMrp()) - parsePrice(productModels.get(i).getInstant())));
            String qty = "" + productModels.get(i).getSel();
            dis = "" + Float.valueOf(df.format(parsePrice(dis)));
            billContentBuilder.append(product_name);
            mrp_total = mrp_total + productModels.get(i).getSel() * parsePrice(productModels.get(i).getMrp());
            pay_total = pay_total + productModels.get(i).getSel() * parsePrice(productModels.get(i).getInstant());
            tqt = tqt + productModels.get(i).getSel();
            float price_with_gst = productModels.get(i).getSel() * (parsePrice(productModels.get(i).getInstant()));
            float price_without_gst = productModels.get(i).getSel() * (parsePrice(productModels.get(i).getInstant())) * (100 / (100 + (parsePrice(productModels.get(i).getCgst()) + parsePrice(productModels.get(i).getSgst()))));
            float gst_s = price_with_gst - price_without_gst;
            if (finGST(productModels.get(i).getCgst()) == 0) {
                zero_gst = zero_gst + gst_s;
                zero_tax = zero_tax + price_without_gst;
                zero_tot = zero_tot + (zero_gst / 2);
            } else if (finGST(productModels.get(i).getCgst()) == 2.5) {
                five_gst = five_gst + gst_s;
                five_tax = five_tax + price_without_gst;
                five_tot = five_tot + (five_gst / 2);
            } else if (finGST(productModels.get(i).getCgst()) == 6) {
                tw_gst = tw_gst + gst_s;
                tw_tax = tw_tax + price_without_gst;
                tw_tot = tw_tot + (tw_gst / 2);
            } else if (finGST(productModels.get(i).getCgst()) == 9) {
                ei_gst = ei_gst + gst_s;
                ei_tax = ei_tax + price_without_gst;
                ei_tot = ei_tot + (ei_gst / 2);
            }
            billContentBuilder.append("\n");
            billContentBuilder.append(padStringLast(mrp, 6) + padStringLast(qty, 6) + padStringLast(sp, 7) + padStringLast(dis, 7) + padStringLast("" + getFinal(sp, qty), 6));
            billContentBuilder.append("\n");
            billContentBuilder.append("-------------------------------");
            billContentBuilder.append("\n");
        }

        zero_gst = Float.valueOf(df.format(zero_gst));
        zero_tot = Float.valueOf(df.format(zero_tot));
        zero_tax = Float.valueOf(df.format(zero_tax));
        five_gst = Float.valueOf(df.format(five_gst));
        five_tot = Float.valueOf(df.format(five_tot));
        five_tax = Float.valueOf(df.format(five_tax));
        tw_gst = Float.valueOf(df.format(tw_gst));
        tw_tot = Float.valueOf(df.format(tw_tot));
        tw_tax = Float.valueOf(df.format(tw_tax));
        ei_gst = Float.valueOf(df.format(ei_gst));
        ei_tot = Float.valueOf(df.format(ei_tot));
        ei_tax = Float.valueOf(df.format(ei_tax));

        billContentBuilder.append("-------------------------------");
        billContentBuilder.append("\n");
        billContentBuilder.append(padStringLast("Total Items: " + productModels.size(), 16) + padStringStart(" Total Qty: " + tqt, 15));
        billContentBuilder.append("\n");
        billContentBuilder.append("-------------------------------");
        billContentBuilder.append("\n");
        billContentBuilder.append(padStringLast("Above prices are incl. of tax", 31));
        billContentBuilder.append("\n");
        billContentBuilder.append("-------------------------------");
        billContentBuilder.append("\n");
        billContentBuilder.append("GST% Taxable CGST   SGST  Total");
        billContentBuilder.append("\n");
        if (zero_tax > 0) {
            billContentBuilder.append(padStringLast("0%", 5) + padStringLast("" + zero_tax, 8) + padStringLast("" + Float.valueOf(df.format(((zero_gst / 2)))), 7) + padStringLast("" + Float.valueOf(df.format(((zero_gst / 2)))), 6) + padStringLast("" + zero_gst, 5));
            billContentBuilder.append("\n");
        }
        if (five_tax > 0) {
            billContentBuilder.append(padStringLast("5%", 5) + padStringLast("" + five_tax, 8) + padStringLast("" + Float.valueOf(df.format(((five_gst / 2)))), 7) + padStringLast("" + Float.valueOf(df.format(((five_gst / 2)))), 6) + padStringLast("" + five_gst, 5));
            billContentBuilder.append("\n");
        }
        if (tw_tax > 0) {
            billContentBuilder.append(padStringLast("12%", 5) + padStringLast("" + tw_tax, 8) + padStringLast("" + Float.valueOf(df.format(((tw_gst / 2)))), 7) + padStringLast("" + Float.valueOf(df.format(((tw_gst / 2)))), 6) + padStringLast("" + tw_gst, 5));
            billContentBuilder.append("\n");
        }
        if (ei_tax > 0) {
            billContentBuilder.append(padStringLast("18%", 5) + padStringLast("" + ei_tax, 8) + padStringLast("" + Float.valueOf(df.format(((ei_gst / 2)))), 7) + padStringLast("" + Float.valueOf(df.format(((ei_gst / 2)))), 6) + padStringLast("" + ei_gst, 5));
            billContentBuilder.append("\n");
        }
        billContentBuilder.append("-------------------------------");
        billContentBuilder.append("\n");
        billContentBuilder.append("Tot. " + padStringLast("" + Float.valueOf(df.format((zero_tax + ei_tax + five_tax + tw_tax))), 8) + padStringLast("" + Float.valueOf(df.format((zero_gst + tw_gst + five_gst + ei_gst) / 2)), 7) + padStringLast("" + Float.valueOf(df.format((zero_gst + tw_gst + five_gst + ei_gst) / 2)), 6) + padStringLast("" + Float.valueOf(df.format((zero_gst + tw_gst + five_gst + ei_gst))), 5));
        billContentBuilder.append("\n");
        billContentBuilder.append("-------------------------------");
        billContentBuilder.append("\n");
        billContentBuilder.append(padStringStart("MRP Total : " + mrp_total, 31));
        billContentBuilder.append("\n");
        billContentBuilder.append(padStringStart("Net Total : " + pay_total, 31));
        billContentBuilder.append("\n");
        billContentBuilder.append(padStringStart("Round Off : " + Float.valueOf(df.format((((int) (pay_total)) - pay_total))), 31));
        billContentBuilder.append("\n");
        billContentBuilder.append("-------------------------------");
        billContentBuilder.append("\n");
        billContentBuilder.append("\n");
        billContentBuilder.append(padStringStart("You Pay : " + (((int) (pay_total))), 31));
        billContentBuilder.append("\n");
        billContentBuilder.append("-------------------------------");
        billContentBuilder.append("\n");
        billContentBuilder.append(convertNumberToWords((int) pay_total));
        billContentBuilder.append("\n");
        billContentBuilder.append("-------------------------------");
        billContentBuilder.append("\n");
        billContentBuilder.append(padStringLast("Saved on bill " + (mrp_total - (int) pay_total) + " (" + findPercentage((mrp_total), (pay_total)) + "%)", 31));
        billContentBuilder.append("\n");
        billContentBuilder.append("-------------------------------");
        billContentBuilder.append("\n");
        billContentBuilder.append("E & O.E Subject to Lucknow Jurisdiction");
        billContentBuilder.append("\n");
        billContentBuilder.append("-------------------------------");
        billContentBuilder.append("\n");
        billContentBuilder.append(padString("Thank You !! Visit Again !!", 31));
        billContentBuilder.append("\n");
        billContentBuilder.append("\n");
        billContentBuilder.append("\n");
        return billContentBuilder.toString();
    }

    public float getFinal(String price, String qty) {
        try {
            float f = Float.parseFloat(price);
            int q = Integer.parseInt(qty);
            return f * q;
        } catch (Exception e) {
            return 0;
        }
    }

    public float finGST(String gst) {
        try {
            float f = Float.parseFloat(gst);
            return f;
        } catch (Exception e) {
            return 0;
        }
    }

    public String truncate(String input) {
        if (input.length() > 31) {
            return input.substring(0, 31);
        } else {
            return input;
        }
    }


    public static String padString(String originalString, int targetLength) {
        int originalLength = originalString.length();
        if (originalLength > targetLength) {
            return originalString.substring(0, targetLength);
        } else if (originalLength < targetLength) {
            int spacesToAdd = targetLength - originalLength;
            int spacesToAddAtStart = spacesToAdd / 2;
            int spacesToAddAtEnd = spacesToAdd - spacesToAddAtStart;
            StringBuilder paddedStringBuilder = new StringBuilder();
            for (int i = 0; i < spacesToAddAtStart; i++) {
                paddedStringBuilder.append(" ");
            }
            paddedStringBuilder.append(originalString);
            for (int i = 0; i < spacesToAddAtEnd; i++) {
                paddedStringBuilder.append(" ");
            }
            return paddedStringBuilder.toString();
        } else {
            return originalString;
        }
    }

    public static String padStringLast(String originalString, int targetLength) {
        int originalLength = originalString.length();
        if (originalLength > targetLength) {
            return originalString.substring(0, targetLength);
        } else if (originalLength < targetLength) {
            int spacesToAdd = targetLength - originalLength;
            StringBuilder paddedStringBuilder = new StringBuilder();
            paddedStringBuilder.append(originalString);
            for (int i = 0; i < spacesToAdd; i++) {
                paddedStringBuilder.append(" ");
            }
            return paddedStringBuilder.toString();
        } else {
            return originalString;
        }
    }

    public static String padStringStart(String originalString, int targetLength) {
        int originalLength = originalString.length();
        if (originalLength > targetLength) {
            return originalString.substring(0, targetLength);
        } else if (originalLength < targetLength) {
            int spacesToAdd = targetLength - originalLength;
            StringBuilder paddedStringBuilder = new StringBuilder();
            for (int i = 0; i < spacesToAdd; i++) {
                paddedStringBuilder.append(" ");
            }
            paddedStringBuilder.append(originalString);
            return paddedStringBuilder.toString();
        } else {
            return originalString;
        }
    }

    public String getDates() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public String getTimes() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        String formattedDate = sdf.format(date);
        return formattedDate.toUpperCase();
    }

    public int findPercentage(float mrp, float paid) {
        float d = ((mrp - paid) / mrp);
        return (int) (d * 100);
    }

    public String convertNumberToWords(int number) {
        String[] units = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] teens = {"", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

        if (number == 0) {
            return "zero";
        }
        String words = "";
        if (number >= 1000) {
            words += convertNumberToWords(number / 1000) + " thousand ";
            number %= 1000;
        }
        if (number >= 100) {
            words += convertNumberToWords(number / 100) + " hundred ";
            number %= 100;
        }
        if (number > 0) {
            if (words.length() != 0) {
                words += "and ";
            }
            if (number < 10) {
                words += units[number];
            } else if (number < 20) {
                words += teens[number - 10];
            } else {
                words += tens[number / 10];
                if (number % 10 > 0) {
                    words += " " + units[number % 10];
                }
            }
        }
        return words;
    }

    public void applyGeneralPackage() {
        for (int i = 0; i < packageModels.size(); i++) {
            if (packageModels.get(i).getBypass_make_gen().equalsIgnoreCase("1")) {
                for (int j = 0; j < productModels.size(); j++) {
                    String pid = productModels.get(j).getIdproduct_master();
                    int qty = productModels.get(j).getSel();
                    for (int k = 0; k < packageModels.get(i).getTriggeredProductModels().size(); k++) {
                        if (pid.equalsIgnoreCase(packageModels.get(i).getTriggeredProductModels().get(k).getIdproduct_master())) {
                            if (qty > parsePrice(packageModels.get(i).getTriggeredProductModels().get(k).getPackage_item_qty())) {

                            }
                        }
                    }
                }
            }
        }
    }

    public void setPackageItems(int qty, ArrayList<TaggedProductsModel> taggedProductsModels) {
        for (int i = 0; i < packageApplyModel.size(); i++) {
            for (int j = 0; j < taggedProductsModels.size(); j++) {
                if (packageApplyModel.get(i).getIdproduct_master().equalsIgnoreCase(taggedProductsModels.get(j).getIdproduct_master())) {

                }
            }
        }
    }
}