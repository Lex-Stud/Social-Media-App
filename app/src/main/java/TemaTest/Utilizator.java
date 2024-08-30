package TemaTest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class Utilizator {
    
    private String nume;
    private String parola;
    private ArrayList<Postare> postari = new ArrayList<Postare>();
    private ArrayList<String> prieteni = new ArrayList<>();
    private ArrayList<Integer> post_liked = new ArrayList<>();
    private int numar_urmaritori;
    private int numar_likeuri;
    
    Utilizator(String nume, String parola) {
        this.nume = nume;
        this.parola = parola;
    }

    public void setNumarLikeuri(int nr) {
        numar_likeuri = nr;
    }

    public String getNume() {
        return nume;
    }

    public ArrayList<String> getPrieteni() {
        return prieteni;
    }

    public String getParola() {
        return parola;  
    }

    public void incNrUrmaritori() {
        numar_urmaritori++;
    }

    public int getNumarUrmaritori() {
        return numar_urmaritori;
    }

    public void addPostare(Postare postare) {
        this.postari.add(postare);
    }

    public boolean findID(int id) {  // verific daca exista id-ul in lista de postari
        for (int i = 0; i < postari.size(); i++) {
            if (postari.get(i).getId() == id)
                return true;
        }
        return false;
    }

    public void add_COMMENT_IN_VEC(int id, Comentariu comentariu) {
        for (int i = 0; i < postari.size(); i++)
            if (postari.get(i).getId() == id)
            postari.get(i).addComentariu(comentariu); 
    }

    public int getNrLikePostari() {
        int sum = 0;
        for (int i = 0; i < postari.size(); i++) {
        sum += postari.get(i).getNumarLikeuri();
        }
        return sum;
    }

    public int getNrLikeComentarii(String nume) {
        int sum = 0;
        for (int i = 0; i < postari.size(); i++) {
        sum += postari.get(i).getNumarLikeuriComentariiUtilzator(nume);
        }
        return sum;
    }

    public int getNumarLikeuri() {
        return numar_likeuri;
    }

    public void deletePostare(int id) {
        for (int i = 0; i < postari.size(); i++) {
            if (postari.get(i).getId() == id) {
                postari.remove(i);
                return;
            }
        }
    }

    public void afisPrietenii() {
        System.out.print("{ 'status' : 'ok', 'message' : [ ");
        for (int i = 0; i < prieteni.size() - 1; i++) {
            System.out.print("'");
            System.out.print(prieteni.get(i));
            System.out.print("', ");
        }
        System.out.print("'" + prieteni.get(prieteni.size() - 1) + "'");
        System.out.print("]}");
    }

    public String afisUrmaritori(String utilizator) {
        if (prieteni.contains(utilizator))
        return nume;
        return null;
    }

    public void addPrieten(String prieten) {
        prieteni.add(prieten);
    }

    public void deletePrieten(String prieten) {
        for (int i = 0; i < prieteni.size(); i++) {
            if (prieteni.get(i).equals(prieten)) {
                prieteni.remove(i);
                return;
            }
        }
    }

    public boolean findPrieten(String prieten) {
        for (int i = 0; i < prieteni.size(); i++) {
            if (prieteni.get(i).equals(prieten))
                return true;
        }
        return false;
    }

    public boolean addPostLike(int id, String nume) {
        for (int i = 0; i < postari.size(); i++) {
            if (postari.get(i).getId() == id) {
                
                if (postari.get(i).findLikeUserPost(nume) == true) {
                    System.out.println("{ 'status' : 'error', 'message' : 'The post identifier to like was not valid'}");
                    return false;
                }
                postari.get(i).increaseNumarLikeuri(nume);
                return true;
            }
        }
        return false;
    }

    public boolean deletePostLiked(int id, String nume) {
        for (int i = 0; i < postari.size(); i++) {
            if (postari.get(i).getId() == id) {
                
                if (postari.get(i).findLikeUserPost(nume) == false) {
                    System.out.println("{ 'status' : 'error', 'message' : 'The post identifier to unlike was not valid'}");
                    return false;
                }
                postari.get(i).decreaseNumarLikeuri(nume);
                return true;
            }
        }
        return false;
    }

    public String exist_id_comment(int id) {
        for (int i = 0; i < postari.size(); i++) {
            if (postari.get(i).find_id_Comment(id) != null) {
                return getNume() + " " + postari.get(i).find_id_Comment(id);
            }
        }
        return null;
    }

    public void removeComment(int id) {
        for (int i = 0; i < postari.size(); i++) {
            if (postari.get(i).find_id_Comment(id) != null) {
                postari.get(i).deleteComment(id);
                return;
            }
        }
    }

    public boolean addLike(int id, String nume) {
        for (int i = 0; i < postari.size(); i++) {
            if (postari.get(i).find_id_Comment(id) != null) {
                postari.get(i).addLike(id, nume);
                return true;
            }
        }
        return false;
    }

    public boolean removeLike(int id, String nume) {
        for (int i = 0; i < postari.size(); i++) {
            if (postari.get(i).getId() == id) {
                postari.get(i).removeLike(id, nume);
                return true;
            }
        }
        return false;
    }

    public boolean findUserLike(int id, String nume) {
        for (int i = 0; i < postari.size(); i++) {
            if (postari.get(i).getId() == id)
                return postari.get(i).findUserLike(id, nume);
        }
        return false;
    }

    public void afisPostari() {  // sortare dupa data
        if (postari.size() == 0) {
            System.out.print("{ 'status' : 'error', 'message' : 'no posts'}");
            return;
        }

        Collections.sort(postari, new Comparator<Postare>() {
            @Override
            public int compare(Postare p1, Postare p2) {
                return p2.getCurrentDateAsString().compareTo(p1.getCurrentDateAsString());
            }
        });

        StringBuilder sb = new StringBuilder();
        sb.append("{ 'status' : 'ok', 'message' : [");
        for (int i = postari.size() - 1; i >= 0; i--) {
            sb.append("{'post_id' : '").append(postari.get(i).getId()).append("', 'post_text' : '").append(postari.get(i).getText()).append("', 'post_date' : '").append(postari.get(i).getCurrentDateAsString()).append("'}");
            if (i > 0) {
                sb.append(",");
            }
        }
        sb.append("]}");
        System.out.print(sb.toString());
    }

    public void afisPostare(int id) {
        for (int i = 0; i < postari.size(); i++) {
            if (postari.get(i).getId() == id) {
                System.out.print("{ 'status' : 'ok', 'message' : [");
                System.out.print("{'post_text' : '");
                System.out.print(postari.get(i).getText());
                System.out.print("', 'post_date' : '");
                System.out.print(postari.get(i).getCurrentDateAsString() + "',");
                System.out.print("'username' : '" + nume + "',");
                System.out.print(" 'number_of_likes' : '" + postari.get(i).getNumarLikeuri() + "',");
                System.out.print("'comments' : [{");
                postari.get(i).afisComentarii();
                System.out.print("");
                System.out.print("}");
                System.out.print("] }] }");
                return;
            }
        }
        System.out.print("{ 'status' : 'error', 'message' : 'no post with this id'}");
    }

    public void afisPostariPrieteni() {
        int i = 0;
        for (i = postari.size() - 1; i >= 0; i--) {
            System.out.print("{'post_id' : '");
            System.out.print(postari.get(i).getId());
            System.out.print("', 'post_text' : '");
            System.out.print(postari.get(i).getText());
            System.out.print("', 'post_date' : '");
            System.out.print(postari.get(i).getCurrentDateAsString() + "',");
            System.out.print("'username' : '" + postari.get(i).getAutor() + "'");
            System.out.print("}");
            if (i > 0) {
                System.out.print(",");
            }
        }
    }
    
    public ArrayList<Postare> get_top_5_postari_like() {
        ArrayList<Postare> top_5 = new ArrayList<>();

        Collections.sort(postari, new Comparator<Postare>() {
            @Override
            public int compare(Postare p1, Postare p2) {
                return Integer.compare(p2.getNumarLikeuri(), p1.getNumarLikeuri());
            }
        });

        int i = 0;
        while (i < postari.size() && i < 5) {
            top_5.add(postari.get(i));
            i++;
        }
        return top_5;
    }


    public ArrayList<Postare> get_top_5_postari_comment() {
        ArrayList<Postare> top_5 = new ArrayList<>();

        Collections.sort(postari, new Comparator<Postare>() {
            @Override
            public int compare(Postare p1, Postare p2) {
                return Integer.compare(p2.getNumarComentarii(), p1.getNumarComentarii());
            }
        });

        int i = 0;
        while (i < postari.size() && i < 5) {
            top_5.add(postari.get(i));
            i++;
        }
        return top_5;
    }
}
