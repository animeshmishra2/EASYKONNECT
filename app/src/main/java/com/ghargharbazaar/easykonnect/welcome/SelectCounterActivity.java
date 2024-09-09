package com.ghargharbazaar.easykonnect.welcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ghargharbazaar.easykonnect.R;
import com.ghargharbazaar.easykonnect.model.BatchModel;
import com.ghargharbazaar.easykonnect.model.CounterModel;
import com.ghargharbazaar.easykonnect.model.LastLoginModel;
import com.ghargharbazaar.easykonnect.model.ProductMembershipModel;
import com.ghargharbazaar.easykonnect.model.ProductModel;
import com.ghargharbazaar.easykonnect.utils.Config;
import com.ghargharbazaar.easykonnect.utils.UserDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectCounterActivity extends AppCompatActivity {

    ImageView back_button;
    LinearLayout no_products, progress_layout;
    RecyclerView recyclerView;
    UserDetails userDetails;
    ArrayList<CounterModel> counterModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_counter);
        counterModels = new ArrayList<>();
        userDetails = new UserDetails(getApplicationContext());
        back_button = (ImageView) findViewById(R.id.back_button);
        no_products = (LinearLayout) findViewById(R.id.no_products);
        progress_layout = (LinearLayout) findViewById(R.id.progress_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        getCounter();
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getCounter() {
        progress_layout.setVisibility(View.VISIBLE);
        no_products.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        RequestQueue queue = Volley.newRequestQueue(SelectCounterActivity.this);
        StringRequest sr = new StringRequest(Request.Method.GET, Config.GET_COUNTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progress_layout.setVisibility(View.GONE);
                System.out.println("Response  " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray data = jsonObject.getJSONArray("data");
                    if (data.length() > 0) {
                        counterModels.clear();
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject object = data.getJSONObject(i);
                            JSONObject last_login = object.getJSONObject("last_login");
                            LastLoginModel lastLoginModel = new LastLoginModel(last_login.getString("idcounters_login"), last_login.getString("idcounter"), last_login.getString("idstaff"), last_login.getString("open_balance"), last_login.getString("close_balance"), last_login.getString("open_cash_detail"), last_login.getString("close_cash_detail"), last_login.getString("online_payments"), last_login.getString("od_1"), last_login.getString("od_2"), last_login.getString("od_5"), last_login.getString("od_10"), last_login.getString("od_20"), last_login.getString("od_50"), last_login.getString("od_100"), last_login.getString("od_200"), last_login.getString("od_500"), last_login.getString("od_2000"), last_login.getString("cd_1"), last_login.getString("cd_2"), last_login.getString("cd_5"), last_login.getString("cd_10"), last_login.getString("cd_20"), last_login.getString("cd_50"), last_login.getString("cd_100"), last_login.getString("cd_200"), last_login.getString("cd_500"), last_login.getString("cd_2000"));
                            counterModels.add(new CounterModel(object.getString("idcounter"), object.getString("idstore_warehouse"), object.getString("name"), object.getString("live_status"), object.getString("created_at"), object.getString("updated_at"), object.getString("created_by"), object.getString("updated_by"), object.getString("status"), lastLoginModel));
                        }
                        no_products.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(new CounterAdapter());
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

    class CounterAdapter extends RecyclerView.Adapter<CounterAdapter.TypeHolder> {


        @Override
        public int getItemCount() {
            return counterModels.size();

        }

        @Override
        public void onBindViewHolder(CounterAdapter.TypeHolder holder, @SuppressLint("RecyclerView") final int position) {
            holder.pr_image.setImageResource(R.drawable.counter_orders);
            holder.prod_name.setText(counterModels.get(position).getName());
            holder.connect_device.setText("Select");
            holder.prod_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("SM", counterModels.get(position));
                    intent.putExtras(bundle);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            });
        }

        @Override
        public CounterAdapter.TypeHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());
            ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.device_row, viewGroup, false);
            CounterAdapter.TypeHolder listHolder = new CounterAdapter.TypeHolder(mainGroup);
            return listHolder;
        }

        public class TypeHolder extends RecyclerView.ViewHolder {

            TextView prod_name, connect_device;
            ImageView pr_image;
            LinearLayout prod_layout;

            public TypeHolder(View view) {
                super(view);
                prod_layout = (LinearLayout) view.findViewById(R.id.prod_layout);
                prod_name = (TextView) view.findViewById(R.id.prod_name);
                connect_device = (TextView) view.findViewById(R.id.connect_device);
                pr_image = (ImageView) view.findViewById(R.id.pr_image);
            }
        }
    }


}