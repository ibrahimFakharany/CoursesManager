package exampleoncreatingfixedfragment.example.com.tuts.RecyclerViewCustom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import exampleoncreatingfixedfragment.example.com.tuts.Model.Course;
import exampleoncreatingfixedfragment.example.com.tuts.R;

/**
 * Created by 450 G1 on 21/03/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    private List<Course> imgsList = new ArrayList<>();
    private LayoutInflater inflater;
    private Context cc;
    private OnItemClickListener listener;

    public Adapter(List<Course> list,  Context c, OnItemClickListener onItemClickListener) {
        System.out.println("adapter constructor");
        this.inflater = LayoutInflater.from(c);
        imgsList = list;
        this.cc = c;
        if(imgsList == null){
            System.out.println("list is null");
        }
        this.listener = onItemClickListener;

    }
    public void setList(List<Course> list){
        System.out.println("setList Method");
        this.imgsList = list;
        notifyItemChanged(0,imgsList.size());
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("on create view holder");
        View v = inflater.inflate(R.layout.rv_item_icon, parent, false);
        return new Holder(v);
    }



    @Override
    public void onBindViewHolder(Holder holder, int position) {
        System.out.println("on bind view holder");
        try {
             /*Glide.with(cc).load("http://192.168.1.5/"+imgsList.get(position)
            .getImgIcon()+".png").into(holder.imgViewIcon);*/
            System.out.println("images icon    "+imgsList.get(position).getImgIcon());
            Picasso.with(cc).load("http://192.168.1.5/tuts/"+imgsList.get(position)
                    .getImgIcon()+".png")
                    .into(holder.imgViewIcon);

          holder.bind(imgsList.get(position), listener);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public int getItemCount() {
        System.out.println(imgsList.size());
        return imgsList.size();
    }

    // holder
    class Holder extends RecyclerView.ViewHolder {
        ImageView imgViewIcon;
        View frameLayout;

        public Holder(View itemView) {
            super(itemView);
            imgViewIcon = (ImageView) itemView.findViewById(R.id.image_icon);
            frameLayout = itemView.findViewById(R.id.img_icon_container);
        }
        public void bind(final Course courseItem, final OnItemClickListener onItemClickListener){
            imgViewIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(courseItem);
                }
            });
        }
    }

}

