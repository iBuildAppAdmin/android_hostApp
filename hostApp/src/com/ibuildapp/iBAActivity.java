package com.ibuildapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.appbuilder.sdk.android.Utils;
import com.appbuilder.sdk.android.Widget;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class iBAActivity extends Activity
{
    EditText etMimeType;
    EditText etXmlData;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        etMimeType = (EditText)findViewById(com.ibuildapp.R.id.et_mime);
        String classs = getResources().getString(R.string.package_name);
        etMimeType.setText(classs);;
        
        etXmlData = (EditText)findViewById(com.ibuildapp.R.id.et_xml_data);
        String builtInXml = "";
        try{
            InputStream is = getResources().openRawResource(R.raw.module_data);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int flag = is.read();
            while(flag != -1){
                baos.write(flag);
                flag = is.read();
            }
            builtInXml = baos.toString();
        }catch(IOException iOEx){
            Log.e("", "");
        }
        etXmlData.setText(builtInXml);
        
        Button btnLaunch = (Button)findViewById(com.ibuildapp.R.id.btn_launch);
        btnLaunch.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                Widget widget = new Widget();
                widget.setAppName("My app");
                widget.setBackgroundColor("White");
                widget.setCachePath(Environment.getExternalStorageDirectory() + "/ibuildappcache");
                widget.setDateFormat(1);
                widget.setOrder(0);
                String xmlData = etXmlData.getText().toString();
                widget.setPluginXmlData(Utils.toBase64(xmlData));
                widget.setTitle("My app");
                
                Intent it = new Intent();
		//it.setAction(Intent.ACTION_GET_CONTENT);
		//it.setType(etMimeType.getText().toString());
		it.putExtra("Widget", widget);
                
                //ComponentName cm = it.resolveActivity(getPackageManager());
                String className = etMimeType.getText().toString();
                
                Class class1 = null;
                try{
                    class1 = Class.forName(className);
                }catch(ClassNotFoundException cNFEx){
                    Log.e("", "");
                }

                it.setClass(iBAActivity.this, class1);
                
                boolean isExtends = false;

                try{
                    Class class2 = class1.getSuperclass();
                    
                    while(true){

                        if(class2.getName().equals("com.appbuilder.sdk.android.AppBuilderModule") 
                                || class1.getName().equals("com.ibuildapp.romanblack.CallPlugin.CallPlugin")
                                || class2.getName().equals("com.google.android.maps.MapActivity")){
                            isExtends = true;                        
                            break;
                        }else if(class2.getName().equals("java.lang.Object")){
                            break;
                        }
                            
                        class2 = class2.getSuperclass();

                    }
                }catch(NullPointerException nPEx){
                }
                
                if(isExtends){
                    startActivity(it);
                }else{
                    Toast.makeText(iBAActivity.this, "Your Activity should extend AppBuilderModule", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
