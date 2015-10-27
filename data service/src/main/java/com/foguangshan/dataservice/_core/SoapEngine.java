package com.foguangshan.dataservice._core;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.List;

/**
 * Created by MacBookPro on 4/8/15.
 */
public class SoapEngine  implements Runnable {

    private Thread mThread;

    private String mOperationName;
    private String mSoapUrl;
    private List<PropertyInfo> mPropertyInfo;
    private String mResult;
    public SoapEngine(){
        mOperationName = "HelloWorld";
        mSoapUrl = "http://clientsbox.com/ws/sanbaotemple.asmx";
    }
    public SoapEngine(String soapUrl){
        mOperationName = "";
        mSoapUrl =  soapUrl;
        mPropertyInfo = null;
    }
    public SoapEngine( String soapUrl,String operationName,List<PropertyInfo> propertyInfo){
        mOperationName = operationName;
        mSoapUrl =  soapUrl;
        mPropertyInfo = propertyInfo;
    }

    @Override
    public void run() {
        mResult = getWebService();
    }
    public void setOperationName(String name){
        mOperationName = name;
    }
    public void setPropertyInfo(List<PropertyInfo> propertyInfo){
        mPropertyInfo = propertyInfo;
    }
    public String getResult (){

        if (mThread == null)
        {
            mThread = new Thread (this, mOperationName);
            mThread.start ();

            try {
                mThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return mResult;
    }


    public String getWebService () {
        String SOAP_ACTION = "http://tempuri.org/" + mOperationName;

        String OPERATION_NAME = mOperationName;

        String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

        String SOAP_ADDRESS = mSoapUrl;

        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);

        if (mPropertyInfo != null){
            for(int i = 0; i < mPropertyInfo.size(); i ++){
                request.addProperty(mPropertyInfo.get(i));
            }
        }

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTION, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }
}
