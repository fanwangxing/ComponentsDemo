package fan.componentsdemo.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import fan.componentsdemo.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private Intent bindIntent;
    private Intent bindMessengerIntent;
    private Intent bindBinderIntent;
    private Button start;
    private Button stop;
    private Button bind_binder;
    private Button unbind_binder;
    private Button bind_messenger;
    private Button unbind_messenger;
    private Button getdata;
    private Button senddata;
    private TextView show;
    private MyBindBinderService myBindService;
    private Messenger messenger;
    Messenger clientMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MyBindMessengerService.SERVICE_TO_CLIENT:
                    show.setText(msg.getData().getString("data"));
                    Log.i("test", "receiver message from service:" + msg.getData().getString("data"));
                    break;
            }
        }
    });

    private ServiceConnection messengerServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            messenger = new Messenger(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            messenger = null;
        }
    };
   private ServiceConnection binderServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //Binder通讯方式
            MyBindBinderService.LocalBinder localBinder = (MyBindBinderService.LocalBinder) iBinder;
            myBindService = localBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            myBindService = null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initView();
        setLisener();
        initData();
    }

    private void initView() {
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        bind_binder = (Button) findViewById(R.id.bind_binder);
        unbind_binder = (Button) findViewById(R.id.unbind_binder);
        bind_messenger = (Button) findViewById(R.id.bind_messenger);
        unbind_messenger = (Button) findViewById(R.id.unbind_messenger);
        getdata = (Button) findViewById(R.id.getdata);
        senddata = (Button) findViewById(R.id.senddata);
        show = (TextView) findViewById(R.id.show);
    }

    private void setLisener() {

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        bind_binder.setOnClickListener(this);
        unbind_binder.setOnClickListener(this);
        bind_messenger.setOnClickListener(this);
        unbind_messenger.setOnClickListener(this);
        getdata.setOnClickListener(this);
        senddata.setOnClickListener(this);
    }

    private void initData() {
    }


    private void bindBinderService() {
        bindBinderIntent = new Intent();
        bindBinderIntent.setClass(this, MyBindBinderService.class);
        bindService(bindBinderIntent, binderServiceConnection, Service.BIND_AUTO_CREATE);

    }
    private void bindMessengerService() {
        bindMessengerIntent = new Intent();
        bindMessengerIntent.setClass(this, MyBindMessengerService.class);
        bindService(bindMessengerIntent, messengerServiceConnection, Service.BIND_AUTO_CREATE);

    }
    private void startService() {
        intent = new Intent();
        intent.setClass(this, Myservice.class);
        intent.putExtra("name", "fan");
        startService(intent);

    }

    @Override
    protected void onDestroy() {
        stopService(intent);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.start:
                startService();
                Toast.makeText(this,"启动服务",Toast.LENGTH_SHORT).show();
                break;
            case R.id.stop:
                stopService(intent);
                Toast.makeText(this,"停止服务",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bind_binder:
                bindBinderService();
                Toast.makeText(this,"启动Binder绑定服务",Toast.LENGTH_SHORT).show();
                break;
            case R.id.unbind_binder:
                unbindService(binderServiceConnection);
                stopService(bindBinderIntent);
                Toast.makeText(this,"解除Binder绑定",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bind_messenger:
                bindMessengerService();
                Toast.makeText(this,"启动Messenger绑定服务",Toast.LENGTH_SHORT).show();
                break;
            case R.id.unbind_messenger:
                unbindService(messengerServiceConnection);
                stopService(bindMessengerIntent);
                Toast.makeText(this,"解除Messenger绑定",Toast.LENGTH_SHORT).show();
                break;
            case R.id.getdata:
                //Binder通讯方式
                show.setText(myBindService.getName());
                break;
            case R.id.senddata:
                Message msg = Message.obtain();
                msg.what = MyBindMessengerService.CLIENT_TO_SERVICE;
                msg.obj = "发送一条数据";
                msg.replyTo = clientMessenger;
                try {
                    messenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

}
