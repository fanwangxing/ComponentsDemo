package fan.componentsdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyBindMessengerService extends Service {
    static final int CLIENT_TO_SERVICE = 1;
    static final int SERVICE_TO_CLIENT = 2;
    private String name = "凡望星";
    public MyBindMessengerService() {
    }
    Messenger messenger = new Messenger(new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case CLIENT_TO_SERVICE:
                    Log.e("test","myservice getdata:"+msg.obj);
                    Messenger messenger = msg.replyTo;
                    Message message = Message.obtain();
                    message.what = SERVICE_TO_CLIENT;
                    Bundle bundle = new Bundle();
                    bundle.putString("data","收到："+msg.obj);
                    message.setData(bundle);
                    try {
                        messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    });
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

}
