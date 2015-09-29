package lingchuang.myprojwork_01_introbbg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Ray.C on 2015/8/28.
 */
public class Activity_record extends Activity {
    File recfile;
    MediaRecorder theRecorder;
    MediaPlayer thePlayer;
    String filepath= Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator;
    Context context;
    Spinner spinner;
    Button bt_new;
    String[] spDefList={"阿瓦隆:抵抗組織","一夜狼人"};
    ArrayList<String> splist=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    int selected_No;
    String[] filepaths=new String[99];
    ImageButton bt_play,bt_pause,bt_stop;
//    SeekBar seekBar;
//    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recorder_main);
        context=this;
        thePlayer=new MediaPlayer();
//        seekBar=(SeekBar)findViewById(R.id.seekBar2);
//        seekBar.setEnabled(false);
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                thePlayer.seekTo(thePlayer.getDuration()*seekBar.getProgress()/seekBar.getMax());
//            }
//        });

        spinner=(Spinner)findViewById(R.id.spinner);
        for(int i=0;i<spDefList.length;i++) {
            splist.add(spDefList[i]);
        }
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item,splist);

        spinner.setPrompt("please select:");
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                thePlayer.release();
                selected_No = position;
                Toast.makeText(context,"Track"+(selected_No+1)+" is ready!",Toast.LENGTH_LONG).show();
                if (selected_No == 0) {
                    thePlayer = MediaPlayer.create(context, R.raw.test01_dami_awaron);
                } else if (selected_No == 1) {
                    thePlayer = MediaPlayer.create(context, R.raw.test02_dami_onwm);
                } else {
                    File therecfile = new File(filepaths[selected_No]);
                    Uri uri = Uri.fromFile(therecfile);
                    thePlayer = MediaPlayer.create(context, uri);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        bt_pause=(ImageButton)findViewById(R.id.new_pause);
        bt_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thePlayer != null)
                    thePlayer.pause();
            }
        });
        bt_stop=(ImageButton)findViewById(R.id.new_stop);
        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thePlayer!=null)
                {
                    thePlayer.stop();
                    thePlayer.seekTo(0);
                }

            }
        });
        bt_play=(ImageButton)findViewById(R.id.new_play);
        bt_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPlayer();
            }
        });

        bt_new=(Button)findViewById(R.id.bt_rec_newFile);
        bt_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, sub_Recorder.class);
                startActivityForResult(intent, 9);
            }
        });


    }





//    Runnable updatesb=new Runnable() {
//        @Override
//        public void run() {
//            if(thePlayer!=null)
//            seekBar.setProgress(thePlayer.getCurrentPosition());
//            handler.postDelayed(updatesb,1000);
//        }
//    };


    protected void MyPlayer(){
        if (thePlayer.isPlaying())
            thePlayer.reset();

        thePlayer.start();
//        seekBar.setMax(thePlayer.getDuration());
//        seekBar.setEnabled(true);
//        handler.post(updatesb);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 9:
                String newitem;
                Bundle bundle;
                bundle = data.getExtras();
                newitem=bundle.getString("newitem");
                splist.add(newitem);
                int position = adapter.getPosition(newitem);
                spinner.setSelection(position);
                String newpath=bundle.getString("newpath");
                filepaths[position]=newpath;
        }
    }

}
