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

public class MyBindBinderService extends Service {
    private LocalBinder localBinder = new LocalBinder();
    private String name = "凡望星";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public class LocalBinder extends Binder {
        MyBindBinderService getService(){
            return MyBindBinderService.this;
        }
    }
    public String getName(){

        return name;
    }
}
