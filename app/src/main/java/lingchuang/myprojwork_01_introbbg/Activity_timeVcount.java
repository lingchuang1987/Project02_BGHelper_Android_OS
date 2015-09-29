package lingchuang.myprojwork_01_introbbg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Ray.C on 2015/9/9.
 */
public class Activity_timeVcount extends Activity {
    Chronometer chronometer;
    Switch theSwitch;
    EditText et_tVc;
    Long temptime=0l;
    MediaPlayer myAlarm;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tool_timer);
        et_tVc=(EditText)findViewById(R.id.et_tVc_no1);
        listView=(ListView)findViewById(R.id.listview_tVc);
        bt_no1=(Button)findViewById(R.id.bt_tVc_no1);
        bt_no2=(Button)findViewById(R.id.bt_tVc_no2);
        bt_no3=(Button)findViewById(R.id.bt_tVc_no3);
        tv_theunit=(TextView)findViewById(R.id.tv_theUnit);
        chronometer=(Chronometer)findViewById(R.id.chronometer_tVc);
        chronometer.setFormat("%s");
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                String time = chronometer.getText().toString();
                if (time.equals(et_tVc.getText().toString())) {
                    RingMyAlarm();
                    new AlertDialog.Builder(Activity_timeVcount.this)
                            .setTitle("計時器" + et_tVc.getText().toString())
                            .setMessage("Time's up!!!")
                            .setIcon(R.drawable.oops_mark)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    StopMyAlarm();
                                }
                            })
                            .create().show();
                }
            }
        });
        theSwitch=(Switch)findViewById(R.id.switch_tVc);
        theSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ChangeMyModule(isChecked);
            }
        });
        theSwitch.setChecked(false);


    }

    ArrayAdapter<String> arrayAdapter;
    String[] lists_forchoose;
    Button bt_no1,bt_no2,bt_no3;
    TextView tv_theunit;

    public void ChangeMyModule(boolean isChecked){
        if(isChecked){
            //count
            lists_forchoose=getResources().getStringArray(R.array.count_chooseList);
            bt_no1.setText("\n+ 1 \n");
            bt_no2.setText("set");
            bt_no3.setText("\n- 1 \n");
            tv_theunit.setText("回合制");
            et_tVc.setText("05");
            chronometer.setText("00");
        }else {
            //timer
            lists_forchoose=getResources().getStringArray(R.array.time_chooseList);
            bt_no1.setText("開始\nstart");
            bt_no2.setText("暫停\nPause");
            bt_no3.setText("重新\nReset");
            tv_theunit.setText("mm：ss");
            et_tVc.setText("00:30");
            chronometer.setText("00:00");
        }
        arrayAdapter=new ArrayAdapter<String>(Activity_timeVcount.this,android.R.layout.simple_list_item_checked,lists_forchoose);
        listView.setAdapter(arrayAdapter);
    }
    public void RingMyAlarm(){
        myAlarm=new MediaPlayer();
        myAlarm=MediaPlayer.create(Activity_timeVcount.this,R.raw.alarm_ringtone);
        try {
            myAlarm.seekTo(0);
            myAlarm.stop();
            myAlarm.prepare();
            myAlarm.setVolume(10, 10);
            myAlarm.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void StopMyAlarm(){
        myAlarm.stop();
        myAlarm.release();
    }

    public void onclick(View v){
        if(theSwitch.isChecked()){
        //counter
            switch (v.getId()){
                case R.id.bt_tVc_no1:
                    break;
                case R.id.bt_tVc_no2:
                    break;
                case R.id.bt_tVc_no3:
                    break;
            }
        }else {
        //timer
            switch (v.getId()) {
                case R.id.bt_tVc_no1:
                    chronometer.setBase(SystemClock.elapsedRealtime()+temptime);
                    chronometer.start();
                    Toast.makeText(Activity_timeVcount.this,"start",Toast.LENGTH_LONG).show();
                    break;
                case R.id.bt_tVc_no2:
                    temptime=chronometer.getBase()- SystemClock.elapsedRealtime();
                    chronometer.stop();
                    break;
                case R.id.bt_tVc_no3:
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    temptime=0l;
                    break;
            }
        }
    }
}
