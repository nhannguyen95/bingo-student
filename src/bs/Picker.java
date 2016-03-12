package bs;

import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.io.*;

/**
 * Created by apple on 3/3/16.
 */
public class Picker {

    private String name1, name2;
    private int topic;
    private int numTopic;
    private String[] studentList;
    private String res1, res2;

    public Picker(String filepath, int numTopic) throws IOException {
        this.studentList = getContentFile(filepath).split("\n");
        this.numTopic = numTopic;
        this.name1 = this.name2 = null;
        this.res1 = this.res2 = null;
    }

    private int getId(String name) {

        for(String student : studentList) {
            int id = Integer.parseInt(student.substring(0, student.indexOf('.')));

            if (student.endsWith(name))
                return id;
        }

        return -1;
    }

    private String getStudent(int id) {

        for(String student : studentList) {
            if (id == Integer.parseInt(student.substring(0, student.indexOf('.'))))
                return student;
        }

        return "";
    }

    public int run1() {

        int id1 = getId(name1);
        if (id1 == -1) return -1;

        res1 = "You: " + getStudent(id1) + "\n";
        res1 = res1 + "Topic: " + this.topic + "\n";
        res1 = res1 + "You can make group with:" + "\n\n";

        for(int id2 = 1; id2 < studentList.length; id2++) {
            if (id2 == id1) continue;
            for(int id3 = id2 + 1; id3 <= studentList.length; id3++) {
                if (id3 == id1) continue;

                if (((id1+id2+id3)%numTopic + 1) == topic) {
                    res1 = res1 + getStudent(id2)
                            + "\n" + getStudent(id3)
                            + "\n\n";
                }
            }
        }

        return 0;
    }

    public int run2() {

        int id1 = getId(name1);
        int id2 = getId(name2);
        if (id1 == -1 || id2 == -1)
            return -1;

        res2 = "You:\n" + getStudent(id1) + "\n" + getStudent(id2)  + "\n";
        res2 = res2 + "You can group with:\n\n";

        for(int tp = 1; tp <= numTopic; tp++) {

            res2 = res2 + "Topic: " + tp + "\n";

            for(int id3 = 1; id3 <= studentList.length; id3++) {
                if (id3 == id1 || id3 == id2) continue;
                if (((id1+id2+id3)%numTopic + 1)==tp) {
                    res2 = res2 + getStudent(id3) + "\n";
                }
            }
            res2 = res2 + "\n";
        }

        return 0;
    }

    public void setName1(String name) {
        this.name1 = name.trim().toLowerCase();
    }

    public void setName2(String name) {
        this.name2 = name.trim().toLowerCase();
    }

    public void setTopic(int topic) {
        this.topic = topic;
    }

    public String getRes1() {
        return this.res1;
    }

    public String getRes2() {
        return this.res2;
    }

    private String getContentFile(String filepath) throws IOException {

        InputStream i = getClass().getResourceAsStream(filepath);

        BufferedReader r = new BufferedReader(new InputStreamReader(i));

        String line, content = "";
        while((line = r.readLine()) != null) {
            content = content + line + "\n";
        }
        i.close();

        return content.trim();
    }

}
