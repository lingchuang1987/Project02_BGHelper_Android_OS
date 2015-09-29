package lingchuang.myprojwork_01_introbbg;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by user on 2015/8/26.
 */
public class Activity_2ppl extends Activity {

    Gallery g1;
    TextView tv_n,tv_ty,tv_ti;
    String[] namelist,
            typelist,
            timelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games_2ppl);
        g1=(Gallery)findViewById(R.id.gallery_2ppl);

        g1.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return imgs.length;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return imgs[i];
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                ImageView pic = new ImageView(Activity_2ppl.this);
                pic.setImageResource(imgs[i]);
                pic.setScaleType(ImageView.ScaleType.FIT_XY);
                return pic;
            }

            //宣告圖片陣列
            private Integer[] imgs = {
                    R.drawable.mrjack,
                    R.drawable.hanabi,
                    R.drawable.jambo
            };
        });
        namelist=getResources().getStringArray(R.array.namelist_2ppl);
        typelist=getResources().getStringArray(R.array.typelist_2ppl);
        timelist=getResources().getStringArray(R.array.timelist_2ppl);
        tv_n=(TextView)findViewById(R.id.tv_2ppl_name);
        tv_ti=(TextView)findViewById(R.id.tv_2ppl_time);
        tv_ty=(TextView)findViewById(R.id.tv_2ppl_Type);
        g1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv_n.setText(namelist[i]);
                tv_ti.setText(timelist[i]);
                tv_ty.setText(typelist[i]);
            }
        });
    }
}