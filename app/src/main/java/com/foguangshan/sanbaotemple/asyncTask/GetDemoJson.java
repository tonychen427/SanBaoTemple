package com.foguangshan.sanbaotemple.asyncTask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;


import com.foguangshan.businessprovider.IBookProvider;
import com.foguangshan.sanbaotemple.CallSoap;
import com.foguangshan.sanbaotemple._core.DaggerSanBaoTempleComponent;
import com.foguangshan.sanbaotemple._core.SanBaoTempleModule;

import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by MacBookPro on 3/16/15.
 */
public class GetDemoJson extends AsyncTask<String, Integer, String> {

    @Inject
    IBookProvider _bookProvider;
    Context mContext;

    public GetDemoJson (Context context){
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //actionBar.setProgressBarVisibility(View.VISIBLE);
        DaggerSanBaoTempleComponent.builder()
                .sanBaoTempleModule(new SanBaoTempleModule())
                .build()
                .initializeGetDemoJson(this);
    }

//    @Override
//    protected String doInBackground(String... params) {
//        return _bookProvider.getJsonProvider(params[0]);
//    }
    @Override
    protected String doInBackground(String... params) {
       return _bookProvider.getJsonProvider(params[0]);
   }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //actionBar.setProgressBarVisibility(View.GONE);
        //JsonResult = result;
        // Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        String data = result.replace("anyType", "").replace("=",":").replace(";",",");

        try{
            JSONObject mainObject = new JSONObject(data.toString());
            JSONObject uniObject = mainObject.getJSONObject("SanBaoTempleBooks");
            String  uniName = uniObject.getJSONObject("bookTitle").toString();
            String uniURL = uniObject.getJSONObject("bookImageUrl").toString();

        }catch (Exception e){
            String Error = e.toString();
        }

        Toast.makeText(mContext, data , Toast.LENGTH_LONG).show();
    }
}