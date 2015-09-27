package com.example.artsyntax.versus;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class SettingActivity extends ActionBarActivity {
    private ImageButton menu_profile;
    private ImageButton menu_radar;
    private Button contact_button;



    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            if (v == menu_radar) {
                Intent intent=new Intent(SettingActivity.this,MainActivity.class);
                startActivity(intent);

            }
            else if (v == menu_profile) {
                Intent intent=new Intent(SettingActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        menu_profile = (ImageButton) findViewById(R.id.menu_profile);
        menu_radar = (ImageButton) findViewById(R.id.menu_radar);
        menu_profile.setOnClickListener(clickListener);
        menu_radar.setOnClickListener(clickListener);


        contact_button = (Button) findViewById(R.id.contact_button); // Retrieve the button from the XML file
        contact_button.setOnClickListener(new View.OnClickListener() {  //Add a listener for when the button is pressed
            @Override
            public void onClick(View v) {
                openFanpage();
            }
        });
    }

    protected void openFanpage() {
        String url = "https://www.facebook.com/pages/Versus/1584000208544288?ref=hl"; // You could have this at the top of the class as a constant, or pass it in as a method variable, if you wish to send to multiple websites
        Intent i = new Intent(Intent.ACTION_VIEW); // Create a new intent - stating you want to 'view something'
        i.setData(Uri.parse(url));  // Add the url data (allowing android to realise you want to open the browser)
        startActivity(i); // Go go go!
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
