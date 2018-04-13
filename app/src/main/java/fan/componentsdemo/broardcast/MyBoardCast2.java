package fan.componentsdemo.broardcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBoardCast2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context,"MyBoardCast2"+intent.getExtras().getString("board"),Toast.LENGTH_SHORT).show();
        Log.e("test","MyBoardCast2:"+intent.getExtras().getString("board"));
        Log.e("test","MyBoardCast2:"+getResultExtras(true).getString("fan"));
    }
}
