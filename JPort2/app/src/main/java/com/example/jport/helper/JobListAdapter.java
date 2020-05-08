package com.example.jport.helper;
import com.example.jport.R;

/**
 * Created by Juned on 1/23/2017.
 */

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class JobListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> COMPANY ;
    ArrayList<String> TITLE ;
    ArrayList<String> COUNTRY ;
    ArrayList<String> PROVINENCE ;
    ArrayList<String> YEARS ;
    ArrayList<String> SALARY ;
    ArrayList<String> PHONE ;
    ArrayList<String> DESCRIP ;
    ArrayList<String> ID ;



    public JobListAdapter(
            Context context2,

            ArrayList<String> Company,
            ArrayList<String> Title,
            ArrayList<String> Country,
            ArrayList<String> provinence,
            ArrayList<String> years,
            ArrayList<String> salary,
            ArrayList<String> phone,
            ArrayList<String> descrip,
            ArrayList<String> id


    )
    {

      this.COMPANY=Company;
      this.TITLE=Title;
      this.COUNTRY=Country;
      this.PROVINENCE=provinence;
      this.YEARS=years;
      this.SALARY=salary;
      this.PHONE=phone;
      this.DESCRIP=descrip;
      this.ID=id;
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

            child = layoutInflater.inflate(R.layout.job_item, null);

            holder = new Holder();

            holder.Company_TextView = (TextView) child.findViewById(R.id.textViewCompany);
            holder.Title_TextView = (TextView) child.findViewById(R.id.textViewTitle);
            holder.CountryTextView = (TextView) child.findViewById(R.id.textViewCountry);
            holder.Provinence_TextView = (TextView) child.findViewById(R.id.textViewProvince);
            holder.Year_TextView = (TextView) child.findViewById(R.id.textViewRequiredYear);
            holder.SalaryTextView = (TextView) child.findViewById(R.id.textViewSalary);
            holder.Phone_TextView = (TextView) child.findViewById(R.id.textViewPhone);
            holder.Des_TextView = (TextView) child.findViewById(R.id.textViewDescription);


            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.Company_TextView.setText(COMPANY.get(position));
        holder.Title_TextView.setText(TITLE.get(position));
        holder.CountryTextView.setText(COUNTRY.get(position));
        holder.Provinence_TextView.setText(PROVINENCE.get(position));
        holder.SalaryTextView.setText(SALARY.get(position));
        holder.Des_TextView.setText(DESCRIP.get(position));
        holder.Phone_TextView.setText(PHONE.get(position));
        holder.Year_TextView.setText(YEARS.get(position));

        return child;
    }

    public class Holder {

        TextView Company_TextView;
        TextView Title_TextView;
        TextView CountryTextView;
        TextView Provinence_TextView;
        TextView SalaryTextView;
        TextView Des_TextView;
        TextView Phone_TextView;
        TextView Year_TextView;

    }

}