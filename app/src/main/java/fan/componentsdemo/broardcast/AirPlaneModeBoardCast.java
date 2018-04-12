package fan.componentsdemo.broardcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/4/9.
 */

public class AirPlaneModeBoardCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"收到系统飞行模式广播",Toast.LENGTH_SHORT).show();
    }
}
