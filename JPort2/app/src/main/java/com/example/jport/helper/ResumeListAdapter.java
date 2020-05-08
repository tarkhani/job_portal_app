package com.example.jport.helper;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.jport.R;
import java.util.ArrayList;



public class ResumeListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> DEGREE ;
    ArrayList<String> MAJOR ;
    ArrayList<String> COUNTRY ;
    ArrayList<String> PROVINENCE ;
    ArrayList<String> YEARS ;
    ArrayList<String> SALARY ;
    ArrayList<String> PHONE ;
    ArrayList<String> DESCRIP ;
    ArrayList<String> ID ;
    ArrayList<String> NAME ;



    public ResumeListAdapter(
            Context context2,

            ArrayList<String> degree,
            ArrayList<String> major,
            ArrayList<String> Country,
            ArrayList<String> provinence,
            ArrayList<String> years,
            ArrayList<String> salary,
            ArrayList<String> phone,
            ArrayList<String> descrip,
            ArrayList<String> id,
            ArrayList<String> name

    )
    {

        this.DEGREE=degree;
        this.MAJOR=major;
        this.COUNTRY=Country;
        this.PROVINENCE=provinence;
        this.YEARS=years;
        this.SALARY=salary;
        this.PHONE=phone;
        this.DESCRIP=descrip;
        this.ID=id;
        this.NAME=name;
        this.context=context2;


    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            child = layoutInflater.inflate(R.layout.resume_item, null);

            holder = new Holder();

            holder.Degree_TextView = (TextView) child.findViewById(R.id.textViewDegree);
            holder.Major_TextView = (TextView) child.findViewById(R.id.textViewMajor);
            holder.CountryTextView = (TextView) child.findViewById(R.id.textViewRCountry);
            holder.Provinence_TextView = (TextView) child.findViewById(R.id.textViewRProvince);
            holder.Year_TextView = (TextView) child.findViewById(R.id.textViewRRequiredYear);
            holder.SalaryTextView = (TextView) child.findViewById(R.id.textViewRSalary);
            holder.Phone_TextView = (TextView) child.findViewById(R.id.textViewRPhone);
            holder.Des_TextView = (TextView) child.findViewById(R.id.textViewRDescription);
            holder.Name_TextView = (TextView) child.findViewById(R.id.textViewName);


            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.Degree_TextView.setText(DEGREE.get(position));
        holder.Major_TextView.setText(MAJOR.get(position));
        holder.CountryTextView.setText(COUNTRY.get(position));
        holder.Provinence_TextView.setText(PROVINENCE.get(position));
        holder.SalaryTextView.setText(SALARY.get(position));
        holder.Des_TextView.setText(DESCRIP.get(position));
        holder.Phone_TextView.setText(PHONE.get(position));
        holder.Year_TextView.setText(YEARS.get(position));
        holder.Name_TextView.setText(NAME.get(position));

        return child;
    }

    public class Holder {

        TextView Degree_TextView;
        TextView Major_TextView;
        TextView CountryTextView;
        TextView Provinence_TextView;
        TextView SalaryTextView;
        TextView Des_TextView;
        TextView Phone_TextView;
        TextView Year_TextView;
        TextView Name_TextView;

    }

}
