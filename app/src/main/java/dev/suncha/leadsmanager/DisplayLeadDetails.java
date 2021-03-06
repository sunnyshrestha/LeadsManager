package dev.suncha.leadsmanager;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import org.apache.http.protocol.HTTP;

import java.util.List;

public class DisplayLeadDetails extends AppCompatActivity {

    TextView organisation_name, office_address, office_phone, website, person_name, designation, mobile, email, product_discussed, meeting_date, followup_date;
    Button contact, sendEmail;
    FragmentManager fragmentManager = getSupportFragmentManager();
    private DatabaseHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_lead_details);
        mHelper = new DatabaseHelper(DisplayLeadDetails.this);

        organisation_name = (TextView) findViewById(R.id.out_organisation_name);
        office_address = (TextView) findViewById(R.id.out_organisation_address);
        office_phone = (TextView) findViewById(R.id.out_organisation_phone);
        website = (TextView) findViewById(R.id.et_website);
        person_name = (TextView) findViewById(R.id.out_person_name);
        designation = (TextView) findViewById(R.id.out_designation);
        mobile = (TextView) findViewById(R.id.out_mobile_no);
        email = (TextView) findViewById(R.id.out_email);
        product_discussed = (TextView) findViewById(R.id.out_product);
        meeting_date = (TextView) findViewById(R.id.output_of_meetingdate);
        followup_date = (TextView) findViewById(R.id.output_of_followupdate);
        contact = (Button) findViewById(R.id.callormessage);
        sendEmail = (Button) findViewById(R.id.emailLeadButton);

        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.button_animation);

        Intent intent = getIntent();
        int entryId = intent.getIntExtra("key", -1);
        Log.v("Received entryId",String.valueOf(entryId));

        setupToolbar();

        organisation_name.setText(mHelper.getLead(entryId+1).getCompany_name());
        office_address.setText(mHelper.getLead(entryId+1).getCompany_address());
        office_phone.setText(mHelper.getLead(entryId+1).getCompany_phone());
        website.setText(mHelper.getLead(entryId+1).getCompany_web());
        person_name.setText(mHelper.getLead(entryId+1).getPerson_name());
        designation.setText(mHelper.getLead(entryId+1).getPerson_designation());
        mobile.setText(mHelper.getLead(entryId+1).getPerson_mobile());
        email.setText(mHelper.getLead(entryId+1).getPerson_email());
        product_discussed.setText(mHelper.getLead(entryId+1).getDiscussion_and_remarks());
        meeting_date.setText(mHelper.getLead(entryId+1).getMeeting_date());
        followup_date.setText(mHelper.getLead(entryId+1).getFollowup_date());

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                contactLead(mobile.getText().toString());
            }
        });

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        if (width > height) {
            mobile.getLayoutParams().width = width * 4 / 5;
            email.getLayoutParams().width = width * 4 / 5;
        } else {
            mobile.getLayoutParams().width = width * 2 / 3;
            email.getLayoutParams().width = width * 2 / 3;
        }

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                String emailId = email.getText().toString();
                if (emailId.length() == 0)
                    Toast.makeText(getApplicationContext(), R.string.noemail, Toast.LENGTH_SHORT).show();
                else
                    emailLead(emailId);
            }
        });
    }

    public void emailLead(String leadEmailId) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{leadEmailId});

        PackageManager packageManager = getPackageManager();
        List activities = packageManager.queryIntentActivities(emailIntent, PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafe = activities.size() > 0;
        if (isIntentSafe)
            startActivity(Intent.createChooser(emailIntent, ""));
        else
            Toast.makeText(getApplicationContext(), R.string.no_package, Toast.LENGTH_SHORT).show();
    }

    public void contactLead(String phone) {
        if (phone.length() == 0) {
            Toast.makeText(getApplicationContext(), R.string.no_number, Toast.LENGTH_SHORT).show();
        } else {
            //call intent to make a call
            contactLeadDialogFragment contactLead = new contactLeadDialogFragment();
            Bundle args = new Bundle();
            args.putString("phone_number", phone);
            contactLead.setArguments(args);

            contactLead.show(fragmentManager, "CONTACT LEAD");
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.edit:
                Intent editIntent = new Intent(getApplicationContext(), EditActivity.class);
                editIntent.putExtra("key", getIntent().getIntExtra("key", -1)+1);
                startActivity(editIntent);
                return true;
            default:

        }
        return super.onOptionsItemSelected(item);
    }
}
