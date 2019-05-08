package com.example.testattval;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class KSoapClient extends AsyncTask<String, Void, Void> {

  Context context;
  TextView tv;

  private final String NAMESPACE = "http://www.WebXml.com.cn/";
  private final String URL = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx";
  private final String SOAP_ACTION = "http://WebXml.com.cn/getMobileCodeInfo";
  private final String METHOD_NAME = "getMobileCodeInfo";

  private static String fahren;

  public KSoapClient(Context context, TextView tv) {
    this.context = context;
    this.tv = tv;
  }

  @Override
  protected Void doInBackground(String... params) {
    getFahrenheit();
    return null;
  }

  @Override
  protected void onPostExecute(Void result) {
    tv.setText(fahren);
  }

  public void getFahrenheit() {
    // Create request
    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
    request.addProperty("mobileCode", "1582393");
    request.addProperty("userID", "");

    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
        SoapEnvelope.VER11);
    envelope.dotNet = true;
    // Set output SOAP object
    envelope.setOutputSoapObject(request);
    // Create HTTP call object
    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
    androidHttpTransport.debug = true;

    try {
      // Invole web service
      androidHttpTransport.call(SOAP_ACTION, envelope);
      // Get the response
      Object r = envelope.getResponse();
      //SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
      // Assign it to fahren static variable
      fahren = r.toString();

      String requestDump = androidHttpTransport.requestDump;
      String responseDump = androidHttpTransport.responseDump;

      Log.i("", "Request: " + requestDump);
      Log.i("", "Response: " + responseDump);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
