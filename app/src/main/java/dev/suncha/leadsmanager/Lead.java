package dev.suncha.leadsmanager;

/**
 * Created by Sunny on 7/30/2015.
 */
public class Lead {
    int id;
    String company_name;
    String company_address;
    String company_phone;
    String company_web;
    String person_name;
    String person_designation;
    String person_mobile;
    String person_email;
    String meeting_date;
    String followup_date;
    String discussion_and_remarks;

    public Lead() {

    }


    public Lead(String company_name, String company_address, String company_phone, String company_web, String person_name, String person_designation, String person_mobile, String person_email, String meeting_date, String followup_date, String discussion_and_remarks) {
        this.company_name = company_name;
        this.company_address = company_address;
        this.company_phone = company_phone;
        this.company_web = company_web;
        this.person_name = person_name;
        this.person_designation = person_designation;
        this.person_mobile = person_mobile;
        this.person_email = person_email;
        this.meeting_date = meeting_date;
        this.followup_date = followup_date;
        this.discussion_and_remarks = discussion_and_remarks;
    }


    public Lead(int id, String company_name, String company_address, String company_phone, String company_web, String person_name, String person_designation, String person_mobile, String person_email, String meeting_date, String followup_date, String discussion_and_remarks) {
        this.id = id;
        this.company_name = company_name;
        this.company_address = company_address;
        this.company_phone = company_phone;
        this.company_web = company_web;
        this.person_name = person_name;
        this.person_designation = person_designation;
        this.person_mobile = person_mobile;
        this.person_email = person_email;
        this.meeting_date = meeting_date;
        this.followup_date = followup_date;
        this.discussion_and_remarks = discussion_and_remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getCompany_phone() {
        return company_phone;
    }

    public void setCompany_phone(String company_phone) {
        this.company_phone = company_phone;
    }

    public String getCompany_web() {
        return company_web;
    }

    public void setCompany_web(String company_web) {
        this.company_web = company_web;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_designation() {
        return person_designation;
    }

    public void setPerson_designation(String person_designation) {
        this.person_designation = person_designation;
    }

    public String getPerson_mobile() {
        return person_mobile;
    }

    public void setPerson_mobile(String person_mobile) {
        this.person_mobile = person_mobile;
    }

    public String getPerson_email() {
        return person_email;
    }

    public void setPerson_email(String person_email) {
        this.person_email = person_email;
    }

    public String getMeeting_date() {
        return meeting_date;
    }

    public void setMeeting_date(String meeting_date) {
        this.meeting_date = meeting_date;
    }

    public String getFollowup_date() {
        return followup_date;
    }

    public void setFollowup_date(String followup_date) {
        this.followup_date = followup_date;
    }

    public String getDiscussion_and_remarks() {
        return discussion_and_remarks;
    }

    public void setDiscussion_and_remarks(String discussion_and_remarks) {
        this.discussion_and_remarks = discussion_and_remarks;
    }
}
