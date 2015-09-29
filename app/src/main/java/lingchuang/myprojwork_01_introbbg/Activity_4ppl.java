package lingchuang.myprojwork_01_introbbg;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by user on 2015/8/26.
 */
public class Activity_4ppl extends Activity {

    Gallery g1;
    TextView tv_n,tv_ty,tv_ti;
    String[] namelist,
            typelist,
            timelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games_4ppl);
        g1=(Gallery)findViewById(R.id.gallery_4ppl);

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
                ImageView pic = new ImageView(Activity_4ppl.this);
                pic.setImageResource(imgs[i]);
                pic.setScaleType(ImageView.ScaleType.FIT_XY);
                return pic;
            }

            //宣告圖片陣列
            private Integer[] imgs = {
                    R.drawable.cv,
                    R.drawable.rummikub,
                    R.drawable.carcassonne
            };
        });
        namelist=getResources().getStringArray(R.array.namelist_4ppl);
        typelist=getResources().getStringArray(R.array.typelist_4ppl);
        timelist=getResources().getStringArray(R.array.timelist_4ppl);
        tv_n=(TextView)findViewById(R.id.tv_4ppl_name);
        tv_ti=(TextView)findViewById(R.id.tv_4ppl_time);
        tv_ty=(TextView)findViewById(R.id.tv_4ppl_Type);
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