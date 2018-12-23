package com.example.ritamartiniano.travelmemoir.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ritamartiniano.travelmemoir.Journal;
import com.example.ritamartiniano.travelmemoir.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.MyViewHolder> {
    public Context context;
    public List<Journal> journal_List;

    public  class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView journal_Name, journalDescription,journal_Location, journal_date;
        public MyViewHolder(@NonNull View view)
        {
          super(view);

          journal_Name = view.findViewById(R.id.journalnameID);
          journalDescription = view.findViewById(R.id.description_ID);
          journal_Location = view.findViewById(R.id.locationID);
          journal_date = view.findViewById(R.id.dateofTravelID);
        }
    }

    public JournalAdapter(Context c, ArrayList<Journal> journals)
    {
        context = c;
        journal_List = journals;
    }
    @Override
    public JournalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
     return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.journal_item,parent,false));

    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
       holder.journal_Name.setText(journal_List.get(position).getName());
       holder.journalDescription.setText(journal_List.get(position).getDescription());
       holder.journal_Location.setText(journal_List.get(position).getLocation());
       holder.journal_date.setText(journal_List.get(position).getDateoftravel());
    }
    @Override
    public int getItemCount()
    {
        return journal_List.size();
    }
}
