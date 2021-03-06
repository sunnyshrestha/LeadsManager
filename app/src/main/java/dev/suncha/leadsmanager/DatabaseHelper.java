package dev.suncha.leadsmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Sunny on 7/30/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    //Lead table name
    static final String TABLE_LEADS = "leads";

    //Lead Table Columns Names
    static final String KEY_ID = "id";
    static final String KEY_COMPANY_NAME = "companyname";
    static final String KEY_COMPANY_ADDRESS = "companyaddress";
    static final String KEY_COMPANY_PHONE = "companyphone";
    static final String KEY_COMPANY_WEBSITE = "companywebsite";
    static final String KEY_PERSON_NAME = "personname";
    static final String KEY_DESIGNATION = "designation";
    static final String KEY_PERSON_PHONE = "personphone";
    static final String KEY_PERSON_EMAIL = "personemail";
    static final String KEY_DISCUSSION_AND_REMARKS = "discussionandremarks";
    static final String KEY_MEETING_DATE = "meetingdate";
    static final String KEY_FOLLOWUP_DATE = "followupdate";
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "myleads";

    private final ArrayList<Lead> leads_list = new ArrayList<Lead>();

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_LEADS_TABLE = "CREATE TABLE " + TABLE_LEADS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_COMPANY_NAME + " TEXT,"
                + KEY_COMPANY_ADDRESS + " TEXT,"
                + KEY_COMPANY_PHONE + " TEXT,"
                + KEY_COMPANY_WEBSITE + " TEXT,"
                + KEY_PERSON_NAME + " TEXT,"
                + KEY_DESIGNATION + " TEXT,"
                + KEY_PERSON_PHONE + " TEXT,"
                + KEY_PERSON_EMAIL + " TEXT,"
                + KEY_DISCUSSION_AND_REMARKS + " TEXT,"
                + KEY_MEETING_DATE + " TEXT,"
                + KEY_FOLLOWUP_DATE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_LEADS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LEADS);
        onCreate(sqLiteDatabase);
    }

    public void addLead(Lead lead) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COMPANY_NAME, lead.getCompany_name());
        values.put(KEY_COMPANY_ADDRESS, lead.getCompany_address());
        values.put(KEY_COMPANY_PHONE, lead.getCompany_phone());
        values.put(KEY_COMPANY_WEBSITE, lead.getCompany_web());
        values.put(KEY_PERSON_NAME, lead.getPerson_name());
        values.put(KEY_DESIGNATION, lead.getPerson_designation());
        values.put(KEY_PERSON_PHONE, lead.getPerson_mobile());
        values.put(KEY_PERSON_EMAIL, lead.getPerson_email());
        values.put(KEY_DISCUSSION_AND_REMARKS, lead.getDiscussion_and_remarks());
        values.put(KEY_MEETING_DATE, lead.getMeeting_date());
        values.put(KEY_FOLLOWUP_DATE, lead.getFollowup_date());

        //Inserting Row
        db.insert(TABLE_LEADS, null, values);
        db.close();
    }


    //Getting a single lead
    public Lead getLead(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_LEADS,
                new String[]{KEY_ID,
                        KEY_COMPANY_NAME, KEY_COMPANY_ADDRESS, KEY_COMPANY_PHONE,
                        KEY_COMPANY_WEBSITE, KEY_PERSON_NAME, KEY_DESIGNATION,
                        KEY_PERSON_PHONE, KEY_PERSON_EMAIL, KEY_DISCUSSION_AND_REMARKS,
                        KEY_MEETING_DATE, KEY_FOLLOWUP_DATE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Lead lead = new Lead(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10),
                cursor.getString(11));

        //Return lead
        return lead;
    }

    //Getting all leads
    public ArrayList<Lead> getAllLeads() {
        try {
            leads_list.clear();

            String selectQuery = "SELECT * FROM " + TABLE_LEADS;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            //Looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Lead lead = new Lead();
                    lead.setId(Integer.parseInt(cursor.getString(0)));
                    lead.setCompany_name(cursor.getString(1));
                    lead.setCompany_address(cursor.getString(2));
                    lead.setCompany_phone(cursor.getString(3));
                    lead.setCompany_web(cursor.getString(4));
                    lead.setPerson_name(cursor.getString(5));
                    lead.setPerson_designation(cursor.getString(6));
                    lead.setPerson_mobile(cursor.getString(7));
                    lead.setPerson_email(cursor.getString(8));
                    lead.setDiscussion_and_remarks(cursor.getString(9));
                    lead.setMeeting_date(cursor.getString(10));
                    lead.setFollowup_date(cursor.getString(11));

                    //Adding lead to list
                    leads_list.add(lead);
                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();
        } catch (Exception e) {
            Log.e("getallLeads", "" + e);
        }
        //Return lead list
        return leads_list;
    }



    //Getting leads count
    public int getLeadsCount() {
        String countQuery = "SELECT * FROM " + TABLE_LEADS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        //return count
        return count;
    }
    //This method accepts Lead class object as parameter

    public int updateLead(Lead lead) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COMPANY_NAME, lead.getCompany_name());
        values.put(KEY_COMPANY_ADDRESS, lead.getCompany_address());
        values.put(KEY_COMPANY_PHONE, lead.getCompany_phone());
        values.put(KEY_COMPANY_WEBSITE, lead.getCompany_web());
        values.put(KEY_PERSON_NAME, lead.getPerson_name());
        values.put(KEY_DESIGNATION, lead.getPerson_designation());
        values.put(KEY_PERSON_PHONE, lead.getPerson_mobile());
        values.put(KEY_PERSON_EMAIL, lead.getPerson_email());
        values.put(KEY_DISCUSSION_AND_REMARKS, lead.getDiscussion_and_remarks());
        values.put(KEY_MEETING_DATE, lead.getMeeting_date());
        values.put(KEY_FOLLOWUP_DATE, lead.getFollowup_date());

        //updating row
        return db.update(TABLE_LEADS, values,
                KEY_ID + "=?",
                new String[]{String.valueOf(lead.getId())});
    }

    //deleteLead will delete single lead from database
    public void deleteLead(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LEADS, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public int colIndex(int whoseColIndex) {
        String countQuery = "SELECT * FROM " + TABLE_LEADS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToPosition(whoseColIndex);
        int index = cursor.getInt(cursor.getColumnIndex("id"));
        return index;
    }
}
