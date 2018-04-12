package fan.componentsdemo.broardcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fan.componentsdemo.R;

public class BroardCastActivity extends AppCompatActivity implements View.OnClickListener{
    private Button sendbroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broard_cast);
        sendbroadcast = (Button)findViewById(R.id.sendbroadcast);
        sendbroadcast.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sendbroadcast:
                Intent boardIntent = new Intent();
                boardIntent.setAction("TESTBOARD");
                boardIntent.putExtra("board","自定义广播数据");
                sendBroadcast(boardIntent);
                break;
        }
    }
}
