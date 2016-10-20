package com.app.xang.robot.robotfns;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class RobotMainActivity extends AppCompatActivity {


    private static final String ROBOT_UP = "1";
    private static final String ROBOT_STOP = "5";
    private static final String ROBOT_DOWN = "2";
    private static final String ROBOT_LEFT = "3";
    private static final String ROBOT_RIGHT = "4";
    private static final String ROBOT_HAND_TAKE_BALL = "6";
    private static final String ROBOT_HAND_STOP = "0";
    private static final String ROBOT_HAND_TAKE_OUT_BALL = "7";
    private static final String ROBOT_HAND_UP = "8";
    private static final String ROBOT_HAND_DOWN = "9";

    @BindView(R.id.txtstatus)
    TextView txtstatus;
    @BindView(R.id.btnconnect)
    Button btnconnect;

    private static final int ENABLE_BLUETOOTH_CODE = 1000;
    private BluetoothSPP bt;

    private boolean statbtnclick = true; // True is Connect | False id DisConnect

    private boolean statconnectbutton = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot_main);
         ButterKnife.bind(this);

        bt=new BluetoothSPP(this);

        if (!bt.isBluetoothAvailable()){

            AlertDialog.Builder aBuilder=new AlertDialog.Builder(this)
                    .setTitle("Robot Bluetooth")
                    .setMessage("Your device not support bluetooth")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();

                        }
                    });

            aBuilder.show();

        } //check support bluetooth


    }


    @Override
    protected void onResume() {
        super.onResume();

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            @Override
            public void onDeviceConnected(String name, String address) {

                txtstatus.setBackgroundColor(Color.GREEN);
                txtstatus.setText("Connecting to Robot ... ");
                btnconnect.setText("Disconnect Robot");
            }

            @Override
            public void onDeviceDisconnected() {
                txtstatus.setBackgroundColor(Color.YELLOW);
                txtstatus.setText("Disconnecting from Robot ... ");
                btnconnect.setText("Connect Robot");
            }

            @Override
            public void onDeviceConnectionFailed() {
                txtstatus.setBackgroundColor(Color.RED);
                txtstatus.setText("Failed Connect to Robot ... ");
                btnconnect.setText("Connect Robot");
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == RESULT_OK)
                bt.connect(data);
        }

    }


    @OnTouch(R.id.btnup)
    public boolean robotup(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (statbtnclick){
                SendCommand(ROBOT_UP);
                statbtnclick = false;
            }

        } else if (event.getAction() == MotionEvent.ACTION_UP){

            SendCommand(ROBOT_STOP);
            statbtnclick =true;
            }

        return true;
    }


    @OnTouch(R.id.btndown)
    public boolean robotdown(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (statbtnclick){
                SendCommand(ROBOT_DOWN);
                statbtnclick = false;
            }

        } else if (event.getAction() == MotionEvent.ACTION_UP){

            SendCommand(ROBOT_STOP);
            statbtnclick =true;
        }

        return true;
    }


    @OnTouch(R.id.btnleft)
    public boolean robotleft(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (statbtnclick){
                SendCommand(ROBOT_LEFT);
                statbtnclick = false;
            }

        } else if (event.getAction() == MotionEvent.ACTION_UP){

            SendCommand(ROBOT_STOP);
            statbtnclick =true;
        }

        return true;
    }


    @OnTouch(R.id.btnright)
    public boolean robotright(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (statbtnclick){
                SendCommand(ROBOT_RIGHT);
                statbtnclick = false;
            }

        } else if (event.getAction() == MotionEvent.ACTION_UP){

            SendCommand(ROBOT_STOP);
            statbtnclick =true;
        }

        return true;
    }


    @OnTouch(R.id.btnmultiply)
    public boolean robot_hand_take_ball(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (statbtnclick){
                SendCommand(ROBOT_HAND_TAKE_BALL);
                statbtnclick = false;
            }

        } else if (event.getAction() == MotionEvent.ACTION_UP){

            SendCommand(ROBOT_HAND_STOP);
            statbtnclick =true;
        }

        return true;
    }



    @OnTouch(R.id.btntriangle)
    public boolean robot_hand_take_out_ball(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (statbtnclick){
                SendCommand(ROBOT_HAND_TAKE_OUT_BALL);
                statbtnclick = false;
            }

        } else if (event.getAction() == MotionEvent.ACTION_UP){

            SendCommand(ROBOT_HAND_STOP);
            statbtnclick =true;
        }

        return true;
    }

    @OnTouch(R.id.btnrectangle)
    public boolean robot_hand_up(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (statbtnclick){
                SendCommand(ROBOT_HAND_UP);
                statbtnclick = false;
            }

        } else if (event.getAction() == MotionEvent.ACTION_UP){

            SendCommand(ROBOT_HAND_STOP);
            statbtnclick =true;
        }

        return true;
    }


    @OnTouch(R.id.btncircles)
    public boolean robot_hand_down(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (statbtnclick){
                SendCommand(ROBOT_HAND_DOWN);
                statbtnclick = false;
            }

        } else if (event.getAction() == MotionEvent.ACTION_UP){

            SendCommand(ROBOT_HAND_STOP);
            statbtnclick =true;
        }

        return true;
    }
    


    public void SendCommand(String cmd){
        bt.send(cmd,true);
    }


    @OnClick(R.id.btnconnect)
    void connectbluetooth(View view){

        if (statconnectbutton){

            if (!bt.isBluetoothEnabled()){
                Intent intentbluetooth=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intentbluetooth,ENABLE_BLUETOOTH_CODE);
            } else {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
                HandleBluetooth();
            }

            statconnectbutton = false;

        }else {

            bt.disconnect();
            statconnectbutton = true;
        }



    } //btn connect bluetooth click

    private void HandleBluetooth() {

        Intent intent = new Intent(getApplicationContext(), DeviceList.class);
        startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bt.stopService();
    }



} // class bluetooth
