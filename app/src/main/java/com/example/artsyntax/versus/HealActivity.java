package com.example.artsyntax.versus;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class HealActivity extends ActionBarActivity {
    private Button button_heal;
    private ImageButton menu_setting;
    private ImageButton menu_radar;
    private ImageButton menu_profile;
    private TextView textView_status;

    int godhp=12;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            if (v == button_heal) {
                textView_status.setText(":  "+(++godhp)+"/50 \n:  7");
                if(godhp>=50)
                {
                    godhp=50;
                    textView_status.setText(":  50/50 \n:  7");
                }
            }

            if (v == menu_radar) {
                Intent intent=new Intent(HealActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            else if (v == menu_setting) {
                Intent intent=new Intent(HealActivity.this,SettingActivity.class);
                startActivity(intent);
                finish();
            }
            else if (v == menu_profile) {
                Intent intent=new Intent(HealActivity.this,ProfileActivity.class);
                startActivity(intent);
                finish();
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heal);


        button_heal = (Button) findViewById(R.id.button_heal);
        button_heal.setOnClickListener(clickListener);

        menu_setting = (ImageButton) findViewById(R.id.menu_setting);
        menu_setting.setOnClickListener(clickListener);

        menu_radar = (ImageButton) findViewById(R.id.menu_radar);
        menu_radar.setOnClickListener(clickListener);

        menu_profile = (ImageButton) findViewById(R.id.menu_profile);
        menu_profile.setOnClickListener(clickListener);

        textView_status = (TextView) findViewById(R.id.textView_status);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_heal, menu);
        return true;
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
