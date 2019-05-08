package com.example.testattval;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

  TextView tv;
  Button button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tv = findViewById(R.id.printer);

    button = findViewById(R.id.buttonCallService);

    button.setOnClickListener(new OnClickListener() {

      public void onClick(View arg0) {
        KSoapClient soap = new KSoapClient(getBaseContext(), tv);
        soap.execute();
      }
    });
  }
}
