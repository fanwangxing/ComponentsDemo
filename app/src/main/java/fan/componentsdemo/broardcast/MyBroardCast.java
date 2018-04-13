package fan.componentsdemo.broardcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/4/9.
 */

public class MyBroardCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"MyBoardCast"+intent.getExtras().getString("board"),Toast.LENGTH_SHORT).show();
        Log.e("test","MyBoardCast:"+intent.getExtras().getString("board"));
        Bundle bundle = new Bundle();
        bundle.putString("fan","fanwangxing");
        setResultExtras(bundle);
//        abortBroadcast(); 等级低的将不接收广播
    }
}
