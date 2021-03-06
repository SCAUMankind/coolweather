package model;

import java.util.ArrayList;
import java.util.List;

import db.CoolWeatherOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {
	public static final String DB_NAME="cool_weather";
	public static final int VERSION=1;
	private static CoolWeatherDB coolWeatherDB;
	private SQLiteDatabase db;
	private CoolWeatherDB(Context context){
		CoolWeatherOpenHelper dbHelper=new CoolWeatherOpenHelper(context,DB_NAME,null,VERSION);
		db=dbHelper.getWritableDatabase();
	}
	public synchronized static CoolWeatherDB getInstance(Context context){
		if(coolWeatherDB==null){
			coolWeatherDB=new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}
	public void saveProvince(Province province){
		if(province!=null){
			ContentValues values=new ContentValues();
			values.put("province_name",province.getProvinceName());
			values.put("province_code",province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}
	public List<Province> loadProvinces(){
		List<Province> list=new ArrayList<Province>();
		Cursor cursor=db.query("Province", null,  null,  null,  null,  null,  null);
		if(cursor.moveToFirst()){
			do{
				Province province =new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("Id")));
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("privince_code")));
				list.add(province);
			}while(cursor.moveToNext());
		}
		return list;
	}
	public void saveCity(City city){
		if(city!=null){
			ContentValues values=new ContentValues();
			values.put("city_name",city.getCityName());
			values.put("city_code",city.getCityCode());
			
		}
	}
}
