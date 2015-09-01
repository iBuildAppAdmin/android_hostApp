package com.ibuildapp.module.replacement;

import android.webkit.WebView;
import android.widget.ImageView;
import com.appbuilder.sdk.android.AppBuilderModule;
import com.appbuilder.sdk.android.Widget;

public class replacement extends AppBuilderModule
{
    
    Widget widget = null;
    
    /** Called when the activity is first created. */
    @Override
    public void create()
    {
        setContentView(R.layout.replacement_main);
        
        widget = (Widget)getIntent().getExtras().getSerializable("Widget");
        
        if(widget == null){
            return;
        }
        
        if(widget.getPluginXmlData().length() == 0){
            return;
        }
        
        ImageView imageView = 
                (ImageView)findViewById(R.id.replacement_image_view);
        imageView.setImageResource(R.drawable.replacement_logo);
        
        WebView webView = (WebView)findViewById(R.id.replacement_web_view);
        
        EntityParser parser = new EntityParser(widget.getPluginXmlData());
        
        String htmlData = parser.parse();
        
        webView.loadDataWithBaseURL(null, htmlData, 
                "text/html", "utf-8", null);
    }
}
