package lingchuang.myprojwork_01_introbbg;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Ray.C on 2015/8/27.
 *
 * 未完成!to be continue.
 */
public class sub_shoplist extends ListActivity {


    Bundle bundlesub=new Bundle();
    ArrayAdapter<String> arrayAdapter_sub;
    Context context;
    int[] req_idssub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;

    }
}
