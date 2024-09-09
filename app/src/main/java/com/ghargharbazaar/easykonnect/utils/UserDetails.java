package com.ghargharbazaar.easykonnect.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class UserDetails {

    private SharedPreferences mSharedPreferences;
    public static final String userinfo = "userinfo_x";
    public static final String SPLASH_DATA = "dashboard_data";
    public static final String store_id = "store_id";
    public static final String store_name = "store_name";
    public static final String membership_id = "membership_id";
    public static final String store_distance = "store_distance";
    public static final String userid = "userid";
    public static final String history = "history";

    public static final String storeLatLong = "storeLatLong";

    public static final String bearer_token = "bearer_token";

    public static final String membership_name = "membership_name";

    public static final String otp = "otp";
    public static final String mobile = "mobile";

    public static final String name = "name";

    public static final String email = "email";

    public static final String status = "status";

    public static final String radius = "radius";

    public static final String referral = "referral";

    public static final String used_code = "used_code";

    public static final String isActive = "isActive";

    public static final String address = "address";
    public static final String idcounters_login = "idcounters_login";
    public static final String idcounter = "idcounter";
    public static final String open_balance = "open_balance";
    public static final String close_balance = "close_balance";
    public static final String open_cash_detail = "open_cash_detail";
    public static final String od_1 = "od_1";
    public static final String od_2 = "od_2";
    public static final String od_5 = "od_5";
    public static final String od_10 = "od_10";
    public static final String od_20 = "od_20";
    public static final String od_50 = "od_50";
    public static final String od_100 = "od_100";
    public static final String od_200 = "od_200";
    public static final String od_500 = "od_500";
    public static final String od_2000 = "od_2000";
    public static final String cd_1 = "cd_1";
    public static final String cd_2 = "cd_2";
    public static final String cd_5 = "cd_5";
    public static final String cd_10 = "cd_10";
    public static final String cd_20 = "cd_20";
    public static final String cd_50 = "cd_50";
    public static final String cd_100 = "cd_100";
    public static final String cd_200 = "cd_200";
    public static final String cd_500 = "cd_500";
    public static final String cd_2000 = "cd_2000";
    private Context mContext;

    public UserDetails(Context mContext) {
        this.mContext = mContext;
    }


    public void setSplashData(String splashData) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(SPLASH_DATA, splashData);
        editor.apply();
    }

    public String getSplashData() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(SPLASH_DATA, "");
    }

    public void setStore_id(String stid) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(store_id, stid);
        editor.apply();
    }

    public String getStore_id() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(store_id, "");
    }


    public void setName(String names) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(name, names);
        editor.apply();
    }

    public String getName() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(name, "");
    }


    public void setEmail(String emails) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(email, emails);
        editor.apply();
    }

    public String getEmail() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(email, "");
    }

    public void setIsActive(String isActive1) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(isActive, isActive1);
        editor.apply();
    }

    public String getIsActive() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(isActive, "0");
    }

    public void setStatus(String status1) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(status, status1);
        editor.apply();
    }

    public String getStatus() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(status, "");
    }

    public void setStore_name(String stname) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(store_name, stname);
        editor.apply();
    }

    public String getStore_name() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(store_name, "");
    }


    public void setStore_distance(String store_distances) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(store_distance, store_distances);
        editor.apply();
    }

    public String getStore_distance() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(store_distance, "");
    }

    public void setMembership_id(String membership_ids) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(membership_id, membership_ids);
        editor.apply();
    }

    public String getMembership_id() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(membership_id, "");
    }


    public void setMembershipName(String mem_name) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(membership_name, mem_name);
        editor.apply();
    }

    public String getMembershipName() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(membership_name, "");
    }


    public void setUserid(String userids) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(userid, userids);
        editor.apply();
    }

    public String getUserid() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(userid, "0");
    }

    public void setHistory(String hist) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(history, hist);
        editor.apply();
    }

    public String getHistory() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(history, "0");
    }

    public void setOtp(String otps) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(otp, otps);
        editor.apply();
    }

    public String getOtp() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(otp, "0");
    }

    public void setMobile(String mobiles) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(mobile, mobiles);
        editor.apply();
    }

    public String getRadius() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(radius, "0");
    }

    public void setRadius(String radius1) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(radius, radius1);
        editor.apply();
    }

    public String getStoreLatLong() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(storeLatLong, "0");
    }

    public void setStoreLatLong(String storeLatLong1) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(storeLatLong, storeLatLong1);
        editor.apply();
    }

    public String getMobile() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(mobile, "0");
    }

    public String getReferral() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(referral, "");
    }

    public void setReferral(String refs) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(referral, refs);
        editor.apply();
    }

    public String getUsed_code() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(used_code, "0");
    }

    public void setUsed_code(String ucode) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(used_code, ucode);
        editor.apply();
    }

    public void setBearer_token(String btoken) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(bearer_token, btoken);
        editor.apply();
    }

    public String getBearer_token() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(bearer_token, "0");
    }


    public void setIdcounters_login(String icl) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(idcounters_login, icl);
        editor.apply();
    }

    public String getIdcounters_login() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(idcounters_login, "0");
    }


    public void setIdcounter(String icounter) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(idcounter, icounter);
        editor.apply();
    }

    public String getIdcounter() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(idcounter, "0");
    }

    public void setOpen_balance(String obal) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(open_balance, obal);
        editor.apply();
    }

    public String getOpen_balance() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(open_balance, "0");
    }

    public void setClose_balance(String cbal) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(close_balance, cbal);
        editor.apply();
    }

    public String getClose_balance() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(close_balance, "0");
    }


    public void setAddress(String add) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(address, add);
        editor.apply();
    }

    public String getAddress() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(address, "0");
    }

    public void setOpen_cash_detail(String ocld) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(open_cash_detail, ocld);
        editor.apply();
    }

    public String getOpen_cash_detail() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(open_cash_detail, "0");
    }

    public void setOd_1(String od1) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(od_1, od1);
        editor.apply();
    }

    public String getOd_1() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(od_1, "0");
    }

    public void setOd_2(String od2) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(od_2, od2);
        editor.apply();
    }

    public String getOd_2() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(od_2, "0");
    }

    public void setOd_5(String od5) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(od_5, od5);
        editor.apply();
    }

    public String getOd_5() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(od_5, "0");
    }

    public void setOd_10(String od10) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(od_10, od10);
        editor.apply();
    }

    public String getOd_10() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(od_10, "0");
    }

    public void setOd_20(String od20) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(od_20, od20);
        editor.apply();
    }

    public String getOd_20() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(od_20, "0");
    }

    public void setOd_50(String od50) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(od_50, od50);
        editor.apply();
    }

    public String getOd_50() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(od_50, "0");
    }

    public void setOd_100(String od100) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(od_100, od100);
        editor.apply();
    }

    public String getOd_100() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(od_100, "0");
    }

    public void setOd_200(String od200) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(od_200, od200);
        editor.apply();
    }

    public String getOd_200() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(od_200, "0");
    }

    public void setOd_500(String od500) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(od_500, od500);
        editor.apply();
    }

    public String getOd_500() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(od_500, "0");
    }

    public void setOd_2000(String od2000) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(od_2000, od2000);
        editor.apply();
    }

    public String getOd_2000() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(od_2000, "0");
    }


    public void setCd_1(String cd1) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(cd_1, cd1);
        editor.apply();
    }

    public String getCd_1() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(cd_1, "0");
    }

    public void setCd_2(String cd2) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(cd_2, cd2);
        editor.apply();
    }

    public String getCd_2() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(cd_2, "0");
    }

    public void setCd_5(String cd5) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(cd_5, cd5);
        editor.apply();
    }

    public String getCd_5() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(cd_5, "0");
    }

    public void setCd_10(String cd10) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(cd_10, cd10);
        editor.apply();
    }

    public String getCd_10() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(cd_10, "0");
    }

    public void setCd_20(String cd20) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(cd_20, cd20);
        editor.apply();
    }

    public String getCd_20() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(cd_20, "0");
    }

    public void setCd_50(String cd50) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(cd_50, cd50);
        editor.apply();
    }

    public String getCd_50() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(cd_50, "0");
    }

    public void setCd_100(String cd100) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(cd_100, cd100);
        editor.apply();
    }

    public String getCd_100() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(cd_100, "0");
    }

    public void setCd_200(String cd200) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(cd_200, cd200);
        editor.apply();
    }

    public String getCd_200() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(cd_200, "0");
    }

    public void setCd_500(String cd500) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(cd_500, cd500);
        editor.apply();
    }

    public String getCd_500() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(cd_500, "0");
    }

    public void setCd_2000(String cd2000) {
        mSharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(cd_2000, cd2000);
        editor.apply();
    }

    public String getCd_2000() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        return sharedPreferences.getString(cd_2000, "0");
    }

    public void clearAll() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(userinfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
