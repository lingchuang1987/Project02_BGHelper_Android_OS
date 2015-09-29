package lingchuang.myprojwork_01_introbbg;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ray.C on 2015/8/31.
 */
public class sub_Recorder extends Activity_record {
    Chronometer chronometer;
    Button bt_cancel,bt_okTosave;
    ImageButton imbt_rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recorder_sub);
        context=this;
        bt_okTosave=(Button)findViewById(R.id.bt_rec_OK);
        imbt_rec=(ImageButton)findViewById(R.id.imbt_rec);
        chronometer=(Chronometer)findViewById(R.id.chronometer);
        chronometer.setFormat("%s");
        bt_cancel=(Button)findViewById(R.id.bt_rec_cancel);

        imbt_rec.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        StartMyRecorder();
                        String initial="00:00";
                        if(!chronometer.getText().equals(initial))
                            chronometer.setBase(SystemClock.elapsedRealtime());
                        chronometer.start();
                        Toast.makeText(context,"Path:"+recfile.getAbsolutePath().toString(),Toast.LENGTH_LONG).show();
                        break;
                    case MotionEvent.ACTION_UP:
                        StopMyRecorder();
                        chronometer.stop();
                        break;
                }

                return true;
            }
        });






        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recfile.delete();
                Intent itt2 = getIntent();
                setResult(9, itt2);
                finish();
            }
        });

        bt_okTosave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText et = new EditText(context);
                new AlertDialog.Builder(context)

                        .setMessage(R.string.plsEnter)
                        .setView(et)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String newItem = et.getText().toString();
                                for (int i = 0; i < adapter.getCount(); i++) {
                                    if (newItem.equals(adapter.getItem(i))) {
                                        Toast.makeText(context, "Error!Name exist!!", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                }
                                if (!newItem.equals("")) {

                                    //�ɮץt�s �ɦW= spinner���� +.amr
                                    //File newfile = new File(filepath + position + ".amr");
                                    //recfile.renameTo(newfile);
                                    String newPath=recfile.getAbsolutePath().toString();

                                    //�^��W�@��
                                    Intent itt2 = getIntent();
                                    Bundle b=new Bundle();
                                    b.putString("newitem",newItem);
                                    b.putString("newpath",newPath);
                                    itt2.putExtras(b);
                                    setResult(RESULT_OK, itt2);
                                    finish();
                                } else {
                                    Toast.makeText(context, "Error!Name=null", Toast.LENGTH_LONG).show();
                                    return;
                                }

                            }
                        })
                        .create().show();

            }
        });

    }

    protected void StartMyRecorder(){

        try {
            recfile=File.createTempFile("raw",".amr", Environment.getExternalStorageDirectory());
        } catch (IOException e) {
            e.printStackTrace();
        }

        theRecorder=new MediaRecorder();
        theRecorder.setAudioSource(MediaRecorder.getAudioSourceMax());
        theRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        theRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        theRecorder.setOutputFile(recfile.getAbsolutePath());
        try {
            theRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Record file buffering.\nPlease try later.", Toast.LENGTH_LONG).show();
        }
        theRecorder.start();
    }

    protected void StopMyRecorder(){
        if(theRecorder!=null){
            theRecorder.stop();
            theRecorder.release();
            theRecorder=null;
        }
    }


}
