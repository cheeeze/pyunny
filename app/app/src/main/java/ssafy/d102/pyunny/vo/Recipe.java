package ssafy.d102.pyunny.vo;

public class Recipe {
    private int id;
    private int user_id;
    private String ingredient;
    private String date;
    private String title;
    private String content;
    private String writer;
    private String main_image;

    public Recipe(int id, int user_id, String ingredient, String date, String title, String content, String writer, String main_image) {
        this.id = id;
        this.user_id = user_id;
        this.ingredient = ingredient;
        this.date = date;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.main_image = main_image;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", ingredient='" + ingredient + '\'' +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", main_image='" + main_image + '\'' +
                '}';
    }
}
