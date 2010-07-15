package jdbc;

/**
 * User: phoenixup
 * Date: 2010-7-15
 * Time: 15:07:36
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
