package com.ghargharbazaar.easykonnect.welcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.ghargharbazaar.easykonnect.R;
import com.ghargharbazaar.easykonnect.model.CounterModel;
import com.ghargharbazaar.easykonnect.model.LastLoginModel;
import com.ghargharbazaar.easykonnect.utils.Config;
import com.ghargharbazaar.easykonnect.utils.ProgressDialog;
import com.ghargharbazaar.easykonnect.utils.UserDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CloseCounterActivity extends AppCompatActivity {

    LinearLayout progress_layout, counter_layout, no_products;
    TextView select_counter;
    UserDetails userDetails;
    CounterModel counterModel;
    ProgressDialog pd;
    CardView open_counter, cancel;
    ImageView back_button;
    EditText rupee_1, rupee_2, rupee_5, rupee_10, rupee_20, rupee_50, rupee_100, rupee_200, rupee_500, rupee_2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_counter);
        userDetails = new UserDetails(getApplicationContext());
        counterModel = null;
        select_counter = (TextView) findViewById(R.id.select_counter);
        open_counter = (CardView) findViewById(R.id.open_counter);
        back_button = (ImageView) findViewById(R.id.back_button);
        cancel = (CardView) findViewById(R.id.cancel);
        no_products = (LinearLayout) findViewById(R.id.no_products);
        counter_layout = (LinearLayout) findViewById(R.id.counter_layout);
        progress_layout = (LinearLayout) findViewById(R.id.progress_layout);
        rupee_1 = (EditText) findViewById(R.id.rupee_1);
        rupee_2 = (EditText) findViewById(R.id.rupee_2);
        rupee_5 = (EditText) findViewById(R.id.rupee_5);
        rupee_10 = (EditText) findViewById(R.id.rupee_10);
        rupee_20 = (EditText) findViewById(R.id.rupee_20);
        rupee_50 = (EditText) findViewById(R.id.rupee_50);
        rupee_100 = (EditText) findViewById(R.id.rupee_100);
        rupee_200 = (EditText) findViewById(R.id.rupee_200);
        rupee_500 = (EditText) findViewById(R.id.rupee_500);
        rupee_2000 = (EditText) findViewById(R.id.rupee_2000);
        getCounter();
        open_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counterModel == null) {
                    Toast.makeText(getApplicationContext(), "No Counter Selected", Toast.LENGTH_SHORT).show();
                } else {
                    createJSON();
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }

    public void getCounter() {
        progress_layout.setVisibility(View.VISIBLE);
        no_products.setVisibility(View.GONE);
        counter_layout.setVisibility(View.GONE);
        RequestQueue queue = Volley.newRequestQueue(CloseCounterActivity.this);
        StringRequest sr = new StringRequest(Request.Method.GET, Config.GET_COUNTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progress_layout.setVisibility(View.GONE);
                System.out.println("Response  " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray data = jsonObject.getJSONArray("data");
                    if (data.length() > 0) {
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject object = data.getJSONObject(i);
                            if (userDetails.getIdcounter().trim().equalsIgnoreCase(object.getString("idcounter").trim())) {
                                JSONObject last_login = object.getJSONObject("last_login");
                                LastLoginModel lastLoginModel = new LastLoginModel(last_login.getString("idcounters_login"), last_login.getString("idcounter"), last_login.getString("idstaff"), last_login.getString("open_balance"), last_login.getString("close_balance"), last_login.getString("open_cash_detail"), last_login.getString("close_cash_detail"), last_login.getString("online_payments"), last_login.getString("od_1"), last_login.getString("od_2"), last_login.getString("od_5"), last_login.getString("od_10"), last_login.getString("od_20"), last_login.getString("od_50"), last_login.getString("od_100"), last_login.getString("od_200"), last_login.getString("od_500"), last_login.getString("od_2000"), last_login.getString("cd_1"), last_login.getString("cd_2"), last_login.getString("cd_5"), last_login.getString("cd_10"), last_login.getString("cd_20"), last_login.getString("cd_50"), last_login.getString("cd_100"), last_login.getString("cd_200"), last_login.getString("cd_500"), last_login.getString("cd_2000"));
                                counterModel = new CounterModel(object.getString("idcounter"), object.getString("idstore_warehouse"), object.getString("name"), object.getString("live_status"), object.getString("created_at"), object.getString("updated_at"), object.getString("created_by"), object.getString("updated_by"), object.getString("status"), lastLoginModel);
                            }
                        }
                        if (counterModel == null) {
                            no_products.setVisibility(View.VISIBLE);
                            counter_layout.setVisibility(View.GONE);
                        } else {
                            no_products.setVisibility(View.GONE);
                            counter_layout.setVisibility(View.VISIBLE);
                            setCounter();
                        }
                    } else {
                        no_products.setVisibility(View.VISIBLE);
                        counter_layout.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    no_products.setVisibility(View.VISIBLE);
                    counter_layout.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress_layout.setVisibility(View.GONE);
                no_products.setVisibility(View.VISIBLE);
                counter_layout.setVisibility(View.GONE);
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

    public void setCounter() {
        select_counter.setText(counterModel.getName());
        rupee_1.setText("" + getValue(counterModel.getLastLoginModels().getCd_1()));
        rupee_2.setText("" + getValue(counterModel.getLastLoginModels().getCd_2()));
        rupee_5.setText("" + getValue(counterModel.getLastLoginModels().getCd_5()));
        rupee_10.setText("" + getValue(counterModel.getLastLoginModels().getCd_10()));
        rupee_20.setText("" + getValue(counterModel.getLastLoginModels().getCd_20()));
        rupee_50.setText("" + getValue(counterModel.getLastLoginModels().getCd_50()));
        rupee_100.setText("" + getValue(counterModel.getLastLoginModels().getCd_100()));
        rupee_200.setText("" + getValue(counterModel.getLastLoginModels().getCd_200()));
        rupee_500.setText("" + getValue(counterModel.getLastLoginModels().getCd_500()));
        rupee_2000.setText("" + getValue(counterModel.getLastLoginModels().getCd_2000()));
    }

    public int getValue(String ss) {
        try {
            int i = Integer.parseInt(ss);
            return i;
        } catch (Exception e) {
            return 0;
        }
    }

    public void createJSON() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idcounter", counterModel.getIdcounter());
            jsonObject.put("idstore_warehouse", counterModel.getIdstore_warehouse());
            JSONObject cashDet = new JSONObject();
            cashDet.put("n1", "" + getValue(rupee_1.getText().toString().trim()));
            cashDet.put("n2", "" + getValue(rupee_2.getText().toString().trim()));
            cashDet.put("n5", "" + getValue(rupee_5.getText().toString().trim()));
            cashDet.put("n10", "" + getValue(rupee_10.getText().toString().trim()));
            cashDet.put("n20", "" + getValue(rupee_20.getText().toString().trim()));
            cashDet.put("n50", "" + getValue(rupee_50.getText().toString().trim()));
            cashDet.put("n100", "" + getValue(rupee_100.getText().toString().trim()));
            cashDet.put("n200", "" + getValue(rupee_200.getText().toString().trim()));
            cashDet.put("n500", "" + getValue(rupee_500.getText().toString().trim()));
            cashDet.put("n2000", "" + getValue(rupee_2000.getText().toString().trim()));
            jsonObject.put("cashDet", cashDet);
            jsonObject.put("total", "" + getTotal());
            closeCounter(jsonObject);
        } catch (Exception e) {
        }
    }

    public int getTotal() {
        int total = 0;
        total = getValue(rupee_1.getText().toString().trim()) + (getValue(rupee_2.getText().toString().trim()) * 2) + (getValue(rupee_5.getText().toString().trim()) * 5) + (getValue(rupee_10.getText().toString().trim()) * 10) + (getValue(rupee_20.getText().toString().trim()) * 20) + (getValue(rupee_50.getText().toString().trim()) * 50) + (getValue(rupee_100.getText().toString().trim()) * 100) + (getValue(rupee_200.getText().toString().trim()) * 200) + (getValue(rupee_500.getText().toString().trim()) * 500) + (getValue(rupee_2000.getText().toString().trim()) * 2000);
        return total;
    }

    public void closeCounter(JSONObject valueObject) {
        pd = ProgressDialog.show(CloseCounterActivity.this, "Please Wait...");
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.POST, Config.CLOSE_COUNTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("statusCode").equalsIgnoreCase("0")) {
                        JSONObject object = jsonObject.getJSONObject("data");
                        userDetails.setIsActive("1");
                        userDetails.setIdcounters_login(object.getString("idcounters_login"));
                        userDetails.setIdcounter(object.getString("idcounter"));
                        userDetails.setUserid(object.getString("idstaff"));
                        userDetails.setOpen_balance(object.getString("open_balance"));
                        userDetails.setClose_balance(object.getString("close_balance"));
                        userDetails.setOpen_cash_detail(object.getString("open_cash_detail"));
                        userDetails.setOd_1(object.getString("od_1"));
                        userDetails.setOd_2(object.getString("od_2"));
                        userDetails.setOd_5(object.getString("od_5"));
                        userDetails.setOd_10(object.getString("od_10"));
                        userDetails.setOd_20(object.getString("od_20"));
                        userDetails.setOd_50(object.getString("od_50"));
                        userDetails.setOd_100(object.getString("od_100"));
                        userDetails.setOd_200(object.getString("od_200"));
                        userDetails.setOd_500(object.getString("od_500"));
                        userDetails.setOd_2000(object.getString("od_2000"));
                        userDetails.setCd_1(object.getString("cd_1"));
                        userDetails.setCd_2(object.getString("cd_2"));
                        userDetails.setCd_5(object.getString("cd_5"));
                        userDetails.setCd_10(object.getString("cd_10"));
                        userDetails.setCd_20(object.getString("cd_20"));
                        userDetails.setCd_50(object.getString("cd_50"));
                        userDetails.setCd_100(object.getString("cd_100"));
                        userDetails.setCd_200(object.getString("cd_200"));
                        userDetails.setCd_500(object.getString("cd_500"));
                        userDetails.setCd_2000(object.getString("cd_2000"));
                        userDetails.setIdcounter("0");
                        Intent returnIntent = new Intent();
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "You can not login this counter", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "You can not login this counter", Toast.LENGTH_SHORT).show();
                }
                System.out.println("Response  " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "You can not login this counter", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + userDetails.getBearer_token());
                return params;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return valueObject.toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        sr.setRetryPolicy(new DefaultRetryPolicy(120000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();

    }
}