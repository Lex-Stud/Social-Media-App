package TemaTest;

import com.google.common.base.Strings;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Comentariu {
    private String text;
    private String autor;
    private int id;
    private ArrayList<String> like_pers = new ArrayList<>();
    private String currentDateAsString = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    public Comentariu(String text, int id, String autor) {
        this.text = text;
        this.id = id;
        this.autor = autor;
    }

    public int getNumarLikeuri() {
        return like_pers.size();
    }

    public String getAuror() {
        return autor;
    }

    public boolean findUserLike(String nume) {
        for (int i = 0; i < like_pers.size(); i++) {
            if (like_pers.get(i).equals(nume))
                return true;
        }
        return false;
    }

    public void addLike(String nume) {
        like_pers.add(nume);
    }

    public void removeLike(String nume) {
        like_pers.remove(nume);
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getCurrentDateAsString() {
        return currentDateAsString;
    }
}
