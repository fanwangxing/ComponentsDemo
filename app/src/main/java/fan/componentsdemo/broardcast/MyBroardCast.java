package fan.componentsdemo.broardcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/4/9.
 */

public class MyBroardCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,intent.getExtras().getString("board"),Toast.LENGTH_SHORT).show();
    }
}
