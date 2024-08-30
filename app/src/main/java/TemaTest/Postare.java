package TemaTest;
import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.google.common.base.Strings;
import java.time.LocalDate;

public class Postare {
    private int numarLikeuri;
    private String autor;
    private String text;
    private int id;
    private String currentDateAsString = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    private ArrayList<Comentariu> comentarii = new ArrayList<>();
    private ArrayList<String> like_pers = new ArrayList<>();

    public Postare(String text, int id, String autor) {
        this.text = text;
        this.id = id;
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }
    public String getCurrentDateAsString() {
        return currentDateAsString;
    }

    public int getNumarLikeuri() {
        return like_pers.size();
    }   

    public void increaseNumarLikeuri(String nume) {
        this.like_pers.add(nume);
    }
    
    public void decreaseNumarLikeuri(String nume) {
        for (int i = 0; i < like_pers.size(); i++) {
            if (like_pers.get(i).equals(nume)) {
                like_pers.remove(i);
                return;
            }
        }
    }

    public boolean findLikeUserPost(String nume) {
        for (int i = 0; i < like_pers.size(); i++) {
            if (like_pers.get(i).equals(nume))
                return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    
    public void addComentariu(Comentariu comentariu) {
        this.comentarii.add(comentariu);
    }

    public String find_id_Comment(int id) {
        for (int i = 0; i < comentarii.size(); i++) {
            if (comentarii.get(i).getId() == id)
                return comentarii.get(i).getAuror();
        }
        return null;
    }

    public boolean addLike(int id, String nume) {
        for (int i = 0; i < comentarii.size(); i++) {
            if (comentarii.get(i).getId() == id) {
                comentarii.get(i).addLike(nume);
                return true;
            }
        }
        return false;
    }

    public boolean removeLike(int id, String nume) {
        for (int i = 0; i < comentarii.size(); i++) {
            if (comentarii.get(i).getId() == id) {
                comentarii.get(i).removeLike(nume);
                return true;
            }
        }
        return false;
    }

    public boolean findUserLike(int id, String nume) {
        for (int i = 0; i < comentarii.size(); i++) {
            if (comentarii.get(i).getId() == id)
                return comentarii.get(i).findUserLike(nume);
        }
        return false;
    }

    public void deleteComment(int id) {
        for (int i = 0; i < comentarii.size(); i++) {
            if (comentarii.get(i).getId() == id) {
                comentarii.remove(i);
                return;
            }
        }
    }

    public void afisComentarii() {
        for (int i = 0; i < comentarii.size(); i++) {
            System.out.print("'comment_id' : '" + comentarii.get(i).getId() + "'" + ", 'comment_text' : '"
                    + comentarii.get(i).getText() + "'" + ", 'comment_date' : '" +
                     comentarii.get(i).getCurrentDateAsString() + "'" + ", 'username' : '" + 
                     comentarii.get(i).getAuror() + "'" + ", 'number_of_likes' : '" +
                      comentarii.get(i).getNumarLikeuri() + "'");
        }
    }

    public int getNumarComentarii() {
        return comentarii.size();
    }

    public int getNumarLikeuriComentarii() {
        int sum = 0;
        for (int i = 0; i < comentarii.size(); i++) {
            sum += comentarii.get(i).getNumarLikeuri();
        }
        return sum;
    }

    public int getNumarLikeuriComentariiUtilzator(String nume) {
        int sum = 0;
        for (int i = 0; i < comentarii.size(); i++) {
         if (comentarii.get(i).getAuror().equals(nume))
            sum += comentarii.get(i).getNumarLikeuri();
        }
        return sum;
    }
}   
