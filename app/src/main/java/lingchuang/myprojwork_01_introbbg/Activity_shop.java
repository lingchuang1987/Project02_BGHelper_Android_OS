package lingchuang.myprojwork_01_introbbg;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by user on 2015/8/26.
 */
public class Activity_shop extends Activity{
    Spinner spinner_city,spinner_type;
    String[] readCity,readType;
    ArrayAdapter<String> arrayAdapter_city,arrayAdapter_type;
    EditText et_name;
    String city,type,name;
    Context context;
    AdapterView.OnItemSelectedListener listener;

    /* MyJson_bgshop(key)方法, 參考 key 值
    * shoplist.txt:[name],[shopType],[locate],[address],[TEL],[homepage],[facebook]
    *
    * R檔 string-array, 參考 key值:[shop_typelist],[shop_citylist]
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_main);
        context=this;

        spinner_city=(Spinner)findViewById(R.id.spinner_city);
        readCity=getResources().getStringArray(R.array.shop_citylist);
        arrayAdapter_city=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,readCity);
        spinner_city.setAdapter(arrayAdapter_city);

        spinner_type=(Spinner)findViewById(R.id.spinner_type);
        readType=getResources().getStringArray(R.array.shop_typelist);
        arrayAdapter_type=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,readType);
        spinner_type.setAdapter(arrayAdapter_type);

//        listener=new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        }
    }

    public String[] MyJson_bgshop(String key){
        String[] result_strarr = new String[0];
        try{
            InputStream is1=getAssets().open("shoplist.txt");
            //用inputstream開啟讀取asset內檔案
            int is1length=is1.available();
            byte[] byteReader=new byte[is1length];
            //取得此時的inputstream大小, new一個資料陣列去接收
            is1.read(byteReader);
            String finaltxt=EncodingUtils.getString(byteReader, "UTF-8");
            //key point!! 因為是txt檔案,故最後使用String去儲存,
            //byte-->String 型態間 請務必使用 EncodingUtils.getString(byte[] xxx,"Encode碼 ex:big5 or UTF-8")
            JSONObject jsonObject;
            JSONArray jsonArray=new JSONArray(finaltxt);
            //JsonArray 自己有能力解讀 String 切出自己要的 JsonObj.元件串
            //故記得一定要用 Json陣列 來接 String 檔
            result_strarr=new String[jsonArray.length()];
            for(int i=0;i<jsonArray.length();i++){
                jsonObject=jsonArray.getJSONObject(i);//依序一個個取出切好的 JsonObj.
                result_strarr[i]=jsonObject.getString(key);//一個個存入 String[]
            }
        }catch (IOException e){
            Toast.makeText(context,"catch 1: "+e.toString(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }catch(JSONException je){
            Toast.makeText(context,"catch 2: "+je.toString(),Toast.LENGTH_LONG).show();
            je.printStackTrace();
        }
        return result_strarr;
    }

}

//
//        readStrData=MyJson_twcity("locate");
//        arrayAdapter=new ArrayAdapter<String>(context,android.R.layout.simple_expandable_list_item_1,readStrData);
//        listView_tw.setAdapter(arrayAdapter);
//
//        listView_tw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//                /*
//                int j=0;
//                for(int i=0;i<shop_loclist.length;i++){
//                    if(shop_loclist[i].toString().equals(readStrData[position].toString())){
//                        j++;
//                    }
//                }
//                shoplist_sub =new String[j];
//                int k=0;
//                for(int i=0;i<shop_loclist.length;i++){
//                    if(shop_loclist[i].toString().equals(readStrData[position].toString())){
//                        shoplist_sub[k]=shop_namelist[i];
//                        k++;
//                    }
//                }
//                for(int i=0;i<shoplist_sub.length;i++){
//                    tv12.append(shoplist_sub[i]+"\n");
//                }*/
//
//            }
//        });