package com.example.assign03_cruddypizza;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;
import android.widget.Toast;

// doing a good job here makes the development simplistic. Important.
public class DBAdapter { // class definition
    // all the components for the table you want to use/access as constants
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_ADDRESS = "address";

    public static final String KEY_PHONE_NUMBER = "phoneNumber";

    public static final String KEY_TOPPING_ONE = "toppingOne";

    public static final String KEY_TOPPING_TWO = "toppingTwo";

    public static final String KEY_TOPPING_THREE = "toppingThree";

    public static final String KEY_PIZZA_SIZE = "pizzaSize";
    public static final String KEY_CREATED_AT = "createdAt";
    public static final String KEY_UPDATED_AT = "updatedAt";
    public static final String TAG = "DBAdapter"; // If an exception is thrown, it can display this tag so we know where it happened

    private static final String DATABASE_NAME = "PizzaDatabase"; // database name
    private static final String DATABASE_TABLE = "customers"; // its table
    private static final int DATABASE_VERSION = 2; // can update the version on the fly

    // create db statements with sql lite
    private static final String DATABASE_CREATE =
            "create table customers(_id integer primary key autoincrement,"
                    + "name text not null," +
                    "address text default '125 Main St' not null, " +
                    "phoneNumber text not null, " +
                    "toppingOne text default 'CHEESE' not null, " +
                    "toppingTwo text default 'CHEESE' not null, " +
                    "toppingThree text default 'CHEESE' not null, " +
                    "pizzaSize text default 'S' not null, " +
                    "createdAt text default CURRENT_TIMESTAMP not null, " +
                    "updatedAt text default CURRENT_TIMESTAMP not null);";


    private final Context context;
    private DatabaseHelper DBHelper; // declaring the property
    private SQLiteDatabase db; // SQL databse Object

    // constructor for DBAdapter class
    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context); // creating instance of the helper class
    }

    // working with the helper

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) // handing over our database
        {
            try{
                db.execSQL(DATABASE_CREATE); // handing it our database create statement
            }catch(SQLException e){ // will use SQL Exception in most cases then put another generic catch
                e.printStackTrace();
            }
        }//end method onCreate

        // can upgrade database at runtime
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
        {
            Log.w(TAG,"Upgrade database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }//end method onUpgrade
    }

    //open the database
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase(); // establishes the database
        return this; // returning the adapter  class
    }

    //close the database
    public void close()
    {
        DBHelper.close();
    } // after creating the open and close, now comes the customization of the database

    //insert a customer into the database
//    public long insertCustomer(String pizzaSize, String phoneNum, String address, String toppingThree, String name, String toppingOne, String toppingTwo)
    public void insertCustomer(String name, String address, String phoneNum, String pizzaSize, String toppingOne, String toppingTwo, String toppingThree)
    {
        ContentValues initialValues = new ContentValues(); // another key value pair tool. Created the object to execute for us
        if (name == null){
            name = "John Doe";
            initialValues.put(KEY_NAME, name);
        } else {
            initialValues.put(KEY_NAME, name);
        }

        if (address == null){
            address = "NO ADDRESS";
            initialValues.put(KEY_ADDRESS, address);
        } else {
            initialValues.put(KEY_ADDRESS, address);
        }

        if (phoneNum == null){
            phoneNum = "NO PHONE NUM";
            initialValues.put(KEY_PHONE_NUMBER, phoneNum);
        } else {
            initialValues.put(KEY_PHONE_NUMBER, phoneNum);
        }
        initialValues.put(KEY_PIZZA_SIZE, pizzaSize);
        initialValues.put(KEY_TOPPING_ONE, toppingOne);
        initialValues.put(KEY_TOPPING_TWO, toppingTwo);
        initialValues.put(KEY_TOPPING_THREE, toppingThree);
        long result = db.insert(DATABASE_TABLE, null, initialValues);

        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    //delete a particular contact
    void deleteCustomer(String rowId)
    {
        long result =  db.delete(DATABASE_TABLE,KEY_ROWID + "= " + rowId,null);

        if (result == -1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
        }
    }

    //retrieve all the contacts
    public Cursor getAllCustomers() // result sets are called Cursors
    {
        return db.query(DATABASE_TABLE,new String[]{KEY_ROWID,KEY_NAME},null,null,null,null,null);
    }

    //retrieve a single contact
    public Cursor getCustomer(long rowId) throws SQLException
    {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                KEY_NAME},KEY_ROWID + "=" + rowId,null,null,null,null,null);
        if(mCursor != null) // if it gives us a cursor
        {
            mCursor.moveToFirst(); // points to the first record returned
        }
        return mCursor; // the
    }

    //updates a contact
    void updateCustomer(int rowId,String name,String address, String phoneNum, String pizzaSize, String toppingOne, String toppingTwo, String toppingThree )
    {
        ContentValues cval = new ContentValues();
        cval.put(KEY_NAME, name);
        if(name.isEmpty() || name == null) {

        }
        cval.put(KEY_ADDRESS, address);
        cval.put(KEY_PHONE_NUMBER, phoneNum);
        cval.put(KEY_PIZZA_SIZE, pizzaSize);
        cval.put(KEY_TOPPING_ONE, toppingOne);
        cval.put(KEY_TOPPING_TWO, toppingTwo);
        cval.put(KEY_TOPPING_THREE, toppingThree);
        long result = db.update(DATABASE_TABLE, cval, KEY_ROWID + "=" + rowId,null);

        if (result == -1){
            Toast.makeText(context, "Failed Update", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_LONG).show();
        }
    }

//    get everything from customer table
    public Cursor getEverythingFromCustomer(){
        String query = "SELECT * from " + DATABASE_TABLE;
        DBAdapter myDB = this.open();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);

        }
        return cursor;
    }

}//end class DBAdapter
