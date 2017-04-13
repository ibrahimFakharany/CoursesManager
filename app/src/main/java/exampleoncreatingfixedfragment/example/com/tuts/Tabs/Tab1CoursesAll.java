package exampleoncreatingfixedfragment.example.com.tuts.Tabs;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.*;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import exampleoncreatingfixedfragment.example.com.tuts.CourseDetails;
import exampleoncreatingfixedfragment.example.com.tuts.Model.Course;
import exampleoncreatingfixedfragment.example.com.tuts.R;
import exampleoncreatingfixedfragment.example.com.tuts.RecyclerViewCustom.*;
import exampleoncreatingfixedfragment.example.com.tuts.Singleton;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import exampleoncreatingfixedfragment.example.com.tuts.Model.Course;

/**
 * Created by 450 G1 on 21/03/2017.
 */

public class Tab1CoursesAll extends Fragment{
    List<Course> imagesList;
    RecyclerView recyclerView;
    exampleoncreatingfixedfragment.example.com.tuts.RecyclerViewCustom.Adapter adapter;
    Context thiscontext;
    int userID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1_courses_all, container, false);
        thiscontext = container.getContext();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), Utiles.getNoOfColumns(thiscontext));
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_all_courses);
        Intent intnt = getActivity().getIntent();
        userID = intnt.getIntExtra("userID",1);
        recyclerView.setLayoutManager(gridLayoutManager);
        String url = "http://192.168.1.5/getAllCourses.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("onResponse method");
                            imagesList = new ArrayList<>();
                            JSONObject respon = new JSONObject(response);
                            JSONArray message = respon.getJSONArray("message");
                            JSONObject arrayObjectI;
                            for (int i = 0; i < message.length(); i++) {
                                arrayObjectI = message.getJSONObject(i);
                                imagesList.add(new Course(arrayObjectI.getInt("course_id"),
                                        arrayObjectI.getString("course_name"),
                                        arrayObjectI.getString("instructor"),
                                        arrayObjectI.getString("price"),
                                        arrayObjectI.getString("img_header"),
                                        arrayObjectI.getString("img_icon")));
                            }

                            for (int i = 0; i < imagesList.size(); i++) {
                                System.out.println("id " + imagesList.get(i).getCourseId() + " icon " + imagesList.get(i).getImgIcon());
                            }


                            adapter = new exampleoncreatingfixedfragment.example.com.tuts.RecyclerViewCustom.Adapter(imagesList, getActivity(), new OnItemClickListener() {
                                @Override
                                public void onClick(Course item) {
                                    Intent intent = new Intent(getActivity(), CourseDetails.class);
                                    intent.putExtra("course_id",item.getCourseId());
                                    intent.putExtra("course_name",item.getCourseName());
                                    intent.putExtra("instructor",item.getInstructor());
                                    intent.putExtra("price",item.getPrice());
                                    intent.putExtra("image_header",item.getImgHeader());
                                    intent.putExtra("user_id",userID);
                                    intent.putExtra("flag", "all");
                                    startActivity(intent);
                                }
                            });
                            recyclerView.setAdapter(adapter);
                        } catch (Exception ex) {
                            System.out.println("error in fill list of Images ");

                            ex.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Singleton.getInstance(getActivity().getApplicationContext()).addRequestQue(stringRequest);
        return rootView;
    }



}
