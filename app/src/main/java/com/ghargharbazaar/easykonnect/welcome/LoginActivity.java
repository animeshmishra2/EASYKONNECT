package com.ghargharbazaar.easykonnect.welcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ghargharbazaar.easykonnect.R;
import com.ghargharbazaar.easykonnect.utils.Config;
import com.ghargharbazaar.easykonnect.utils.ProgressDialog;
import com.ghargharbazaar.easykonnect.utils.UserDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    CardView login;
    ProgressDialog pd;
    UserDetails userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDetails = new UserDetails(getApplicationContext());
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (CardView) findViewById(R.id.login);
        if (userDetails.getIsActive().equalsIgnoreCase("1")) {
            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(intent);
            finish();
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().trim().length() < 1) {
                    Toast.makeText(getApplicationContext(), "Enter Valid Email", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().trim().length() < 1) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser();
                }
            }
        });
    }

    public void loginUser() {
        pd = ProgressDialog.show(LoginActivity.this, "Please Wait...");
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        StringRequest sr = new StringRequest(Request.Method.POST, Config.LOGIN_USER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                System.out.println("Response  " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("statusCode").equalsIgnoreCase("0")) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        if (data.getString("is_store").equalsIgnoreCase("1")) {
                            userDetails.setBearer_token(data.getString("access_token"));
                            userDetails.setStore_id(data.getString("idwarehouse"));
                            userDetails.setStore_name(data.getString("sw_name"));
                            userDetails.setAddress(data.getString("sw_address"));
                            userDetails.setName(data.getString("name"));
                            userDetails.setStore_distance(data.getString("is_store"));
                            JSONArray counter_detail = data.getJSONArray("counter_detail");
                            if (counter_detail.length() > 0) {
                                JSONObject object = counter_detail.getJSONObject(0);
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
                                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                userDetails.setIsActive("1");
                                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "These are not store credentials", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Login Credentials", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error in Payload", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Network Connection Error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("email", email.getText().toString().trim());
                    jsonObject.put("password", password.getText().toString().trim());
                    jsonObject.put("remember_me", true);
                } catch (Exception e) {

                }
                return jsonObject.toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        queue.add(sr);
    }

}