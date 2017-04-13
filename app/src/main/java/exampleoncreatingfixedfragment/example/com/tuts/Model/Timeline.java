package exampleoncreatingfixedfragment.example.com.tuts.Model;

/**
 * Created by 450 G1 on 22/03/2017.
 */

public class Timeline {
    private String day;
    private String from;
    private String to;

    public Timeline(String day, String from, String to) {
        this.day = day;
        this.from = from;
        this.to = to;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
