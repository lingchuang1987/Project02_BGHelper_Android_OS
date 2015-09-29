package lingchuang.myprojwork_01_introbbg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Ray.C on 2015/9/10.
 */
public class Activity_sarchby_shopVmap extends Activity{
    Button bt_map,bt_filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharch_by_witch);
        bt_map=(Button)findViewById(R.id.bt_srh_map);
        bt_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(Activity_sarchby_shopVmap.this,Activity_Map.class);
                startActivity(intent);
            }
        });
        bt_filter=(Button)findViewById(R.id.bt_srh_filter);
        bt_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(Activity_sarchby_shopVmap.this,Activity_shop.class);
                startActivity(intent);
            }
        });
    }
}
