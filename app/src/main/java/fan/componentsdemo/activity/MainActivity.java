package fan.componentsdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.idescout.sql.SqlScoutServer;

import fan.componentsdemo.R;
import fan.componentsdemo.broardcast.BroardCastActivity;
import fan.componentsdemo.contentprovider.ContentProviderActivity;
import fan.componentsdemo.service.ServiceActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button activityBt;
    private Button serviceBt;
    private Button broaddcastBt;
    private Button contentproviderBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SqlScoutServer.create(this,getPackageName());
        initView();
        setLisener();
    }

    private void initView() {
        activityBt = (Button)findViewById(R.id.activity_bt);
        serviceBt = (Button)findViewById(R.id.service_bt);
        broaddcastBt = (Button)findViewById(R.id.broadcast_bt);
        contentproviderBt = (Button)findViewById(R.id.contentprovider_bt);
    }
    private void setLisener() {
        activityBt.setOnClickListener(this);
        serviceBt.setOnClickListener(this);
        broaddcastBt.setOnClickListener(this);
        contentproviderBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_bt:
                break;
            case R.id.service_bt:
                startActivity(new Intent(MainActivity.this, ServiceActivity.class));
                break;
            case R.id.broadcast_bt:
                startActivity(new Intent(MainActivity.this, BroardCastActivity.class));
                break;
            case R.id.contentprovider_bt:
                startActivity(new Intent(MainActivity.this, ContentProviderActivity.class));
                break;
        }
    }
}
