package lingchuang.myprojwork_01_introbbg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends Activity {
    Intent intent;
    Context context;
    ImageButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_dice:
                intent.setClass(context, Activity_dice.class);
                startActivity(intent);
                break;
            case R.id.btn_shop:
                intent.setClass(context,Activity_sarchby_shopVmap.class);
                startActivity(intent);
                break;
            case R.id.btn_record:
                intent.setClass(context,Activity_record.class);
                startActivity(intent);
                break;
            case R.id.btn_rank:
                intent.setClass(context,Activity_bgg.class);
                startActivity(intent);
                break;
            case R.id.btn_ptt:
                intent.setClass(context, Activity_PTT.class);
                startActivity(intent);
                break;
            case R.id.btn_time:
                intent.setClass(context, Activity_timeVcount.class);
                startActivity(intent);
                break;
            case R.id.btn_two:
                intent.setClass(context, Activity_2ppl.class);
                startActivity(intent);
                break;
            case R.id.btn_four:
                intent.setClass(context, Activity_4ppl.class);
                startActivity(intent);
                break;
            case R.id.btn_six:
                intent.setClass(context, Activity_6ppl.class);
                startActivity(intent);
                break;

        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent=new Intent();
        context=this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
