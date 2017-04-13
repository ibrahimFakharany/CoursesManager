package exampleoncreatingfixedfragment.example.com.tuts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exampleoncreatingfixedfragment.example.com.tuts.Model.Course;
import exampleoncreatingfixedfragment.example.com.tuts.Model.Timeline;
import exampleoncreatingfixedfragment.example.com.tuts.RecyclerViewTimeline.AdapterTimeline;

public class CourseDetails extends AppCompatActivity {
    ImageView imgHeader;
    TextView value_name_of_course, value_instructor_of_course,
            value_price_of_course;
    Button rollHandler;
    List<Integer> listOfCoursesUserHave;
    List<Timeline> timelineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        Intent intent = getIntent();
        final int courseID = intent.getIntExtra("course_id", 1);
        final int user_id = intent.getIntExtra("user_id", 1);
        final String flag = intent.getStringExtra("flag");
        Course course = new Course(
                courseID,
                intent.getStringExtra("course_name"),
                intent.getStringExtra("instructor"),
                intent.getStringExtra("price"),
                intent.getStringExtra("image_header"),
                user_id);
        //fetch timeline
        String url = "http://192.168.1.5/getSpecificCourseTimeline.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject root = new JSONObject(response);
                            int state = root.getInt("success");
                            if (state == 1) {
                                System.out.println("success = 1");
                                JSONArray array = root.getJSONArray("message");
                                Timeline timeline = null;
                                timelineList = new ArrayList<>();
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject getObjI = array.getJSONObject(i);
                                    timeline = new Timeline(getObjI.getString("day"), getObjI.getString("from"), getObjI.getString("to"));
                                    timelineList.add(timeline);
                                }
                                AdapterTimeline adapterTimeline = new AdapterTimeline(timelineList, getApplicationContext());
                                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_time_line);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                recyclerView.setAdapter(adapterTimeline);
                            } else {
                                System.out.println("success = zero");
                                timelineList = new ArrayList<>();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("course_id", Integer.toString(courseID));
                return map;
            }
        };
        Singleton.getInstance(getApplicationContext()).addRequestQue(stringRequest);
        rollHandler = (Button) findViewById(R.id.reserve_course);
        value_name_of_course = (TextView) findViewById(R.id.value_name_of_course);
        value_instructor_of_course = (TextView) findViewById(R.id.value_instructor_of_course);
        value_price_of_course = (TextView) findViewById(R.id.value_price_of_course);
        imgHeader = (ImageView) findViewById(R.id.header_image_course);
        System.out.println("header ya 25wanaaaa : " + course.getImgHeader());
        Picasso.with(this).load("http://192.168.1.5/tuts/" + course.getImgHeader() + ".png").into(imgHeader);
        value_name_of_course.setText(course.getCourseName());
        value_instructor_of_course.setText(course.getInstructor());
        value_price_of_course.setText(course.getPrice());
        // handle if i in all tab or mine tab
        if (flag.equals("all")) {
            rollHandler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url2 = "http://192.168.1.5/checkUserCourse.php";
                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject root = new JSONObject(response);
                                int state = root.getInt("success");
                                if (state == 1) {
                                    System.out.println("success = one");
                                    Toast.makeText(getApplicationContext(), "already Enrolled", Toast.LENGTH_SHORT).show();
                                } else {
                                    System.out.println("success = zero");
                                    insertUserCourse(user_id, courseID);

                                }
                            } catch (JSONException ex) {
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> map1 = new HashMap<>();
                            map1.put("user_id", Integer.toString(user_id));
                            map1.put("course_id", Integer.toString(courseID));
                            return map1;
                        }
                    };
                    Singleton.getInstance(getApplicationContext()).addRequestQue(stringRequest1);
                }
            });
        } else {
            rollHandler.setText("UNROLL");
            rollHandler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url2 = "http://192.168.1.5/UNRollcourse.php";
                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            rollHandler.setEnabled(false);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> map1 = new HashMap<>();
                            map1.put("user_id", Integer.toString(user_id));
                            map1.put("course_id", Integer.toString(courseID));
                            return map1;
                        }
                    };
                    Singleton.getInstance(getApplicationContext()).addRequestQue(stringRequest1);
                }
            });

        }


    }

    public void insertUserCourse(final int user_id, final int course_id) {
        String url3 = "http://192.168.1.5/insertUserCourse.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //
                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map1 = new HashMap<>();
                map1.put("user_id", Integer.toString(user_id));
                map1.put("course_id", Integer.toString(course_id));

                return map1;
            }
        };
        Singleton.getInstance(getApplicationContext()).addRequestQue(stringRequest);
    }
}
