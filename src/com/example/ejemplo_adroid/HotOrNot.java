package com.example.ejemplo_adroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HotOrNot {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "person_name";
	public static final String KEY_HOTNESS = "person_hotness";
	
	private static final String DATABASE_NAME = "HotOrNotBD";
	private static final String DATABASE_TABLE = "peopleTable";
	private static final int DATABASE_VERSION= 1;
	private DbHelper ourHelper;
	private Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	/*helper para manejo d SQLite*/
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		//esto se llama por primerabes cuando se crea la BD
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(		//ejecuta una consulta en la base de datos
					"CREATE TABLE "+DATABASE_TABLE+" ("+
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
					KEY_NAME+" TEXT NOT NULL, "+
					KEY_HOTNESS+ " TEXT NOT NULL "+
					")"
					);
		}

		//se llama cuando se hace una nueva version de la base de datos
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
			onCreate(db);
		}	
	}
	
	public HotOrNot(Context context){
		ourContext = context;
	}
	
	//abre el archivo de base de datos, sino existe lo crea.
	public HotOrNot open() throws SQLException{
		ourHelper = new DbHelper(ourContext);	//se crea un objeto helper
		ourDatabase = ourHelper.getWritableDatabase();	//se obtien un objeto que adminsitra la base de datos
		return this;
	}
	
	public void close(){
		ourHelper.close();	//se llama cuando cierra la consulta a la base de datos
	}
	
	//crea una nueva entrada en la base de datos
	public long createEntry(String name, String hotness) throws SQLException {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();	//objeto de adminstras pares(clave, valor)
		cv.put(KEY_NAME, name);
		cv.put(KEY_HOTNESS, hotness);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);	//inserta la entrada en la tabla DATABASE_TABLE
	}

	public String getData() throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_HOTNESS};
		//cursor para recorrer las filas obtenidas
		Cursor c = ourDatabase.query(		//reliza un query simple a la BD
				DATABASE_TABLE,	//SELECT columns FROM DATABASE_TABLE
				columns,		//columnas a seleccionar
				null,			//columnas del WHERE
				null,			//valores a filtrar del WHERE
				null,			//GROUP BY
				null,			//HAVING
				null			//ORDER BY
				);
		String result = "";
		int irow = c.getColumnIndex(KEY_ROWID);//se obtine el indicede columna en la columna indicada
		int iname = c.getColumnIndex(KEY_NAME);
		int ihotness = c.getColumnIndex(KEY_HOTNESS);
		
		//se recorren las filas obtenidas
		for( c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			//se obtine el dato dependiendo el tipo qu ese quiera
			result = result + c.getString(irow)+" "+c.getString(iname)+" "+c.getString(ihotness)+"\n";
		}
		return result;
	}

	public String getName(long l) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_HOTNESS};
		Cursor c = ourDatabase.query(DATABASE_TABLE,
				columns,
				KEY_ROWID + "=" + l, 
				null, null, null, null);
		
		if( c != null){
			c.moveToFirst();//se mueve a la primera fila retornada
			String name = c.getString(1);//obtine el valor de la primera columna
			return name;
		}
		return null;
	}

	public String getHotness(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_HOTNESS};
		Cursor c = ourDatabase.query(DATABASE_TABLE,
				columns,
				KEY_ROWID + "=" + l, 
				null, null, null, null);
		
		if( c != null){
			c.moveToFirst();//se mueve a la primera fila retornada
			String hotness = c.getString(2);//obtine el valor de la primera columna
			return hotness;
		}
		return null;
	}

	public void updateEntry(long lRow, String mName, String mHotness) throws SQLException{
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME, mName);
		cvUpdate.put(KEY_HOTNESS, mHotness);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID+"="+lRow, null);
		
	}

	public void deleteEntry(long lRow1) throws SQLException{
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID+"="+lRow1, null);
	}
}
