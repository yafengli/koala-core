package mybatis;

/**
 * User: phoenixup
 * Date: 2010-7-7
 * Time: 14:10:23
 */
public class Book {
    private Long id;
    private String name;
    private String isbn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
