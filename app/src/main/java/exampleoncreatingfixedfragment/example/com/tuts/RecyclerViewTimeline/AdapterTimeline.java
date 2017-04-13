package exampleoncreatingfixedfragment.example.com.tuts.RecyclerViewTimeline;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import exampleoncreatingfixedfragment.example.com.tuts.Model.Timeline;
import exampleoncreatingfixedfragment.example.com.tuts.R;

/**
 * Created by 450 G1 on 22/03/2017.
 */


public class AdapterTimeline extends RecyclerView.Adapter<AdapterTimeline.Holder> {
    List<Timeline> list;
    Context c ;
    LayoutInflater layoutInflater;
    public AdapterTimeline(List<Timeline> list, Context c) {
        layoutInflater  = LayoutInflater.from(c);
        this.list = list;
        this.c = c;

    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.rv_timline_item, parent, false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(AdapterTimeline.Holder holder, int position) {
        holder.DayTxt.setText(list.get(position).getDay());
        holder.fromTxt.setText(list.get(position).getFrom());
        holder.toTxt.setText(list.get(position).getTo());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView DayTxt;
        TextView fromTxt;
        TextView toTxt;
        public Holder(View itemView) {
            super(itemView);
            DayTxt = (TextView) itemView.findViewById(R.id.day_value);
            fromTxt = (TextView) itemView.findViewById(R.id.from_value);
            toTxt = (TextView) itemView.findViewById(R.id.to_value);

        }
    }

}
