package pcs.libraryservices;

import java.util.List;

/**
 *
 * @author keith
 */
public class ListBooksResponse {

    private long totalRecordCount;
    private List<Book> bookList;

    public ListBooksResponse(long totalRecordCount, List<Book> bookList) {
        this.totalRecordCount = totalRecordCount;
        this.bookList = bookList;
    }

    /**
     * @return the totalRecordCount
     */
    public long getTotalRecordCount() {
        return totalRecordCount;
    }

    /**
     * @param totalRecordCount the totalRecordCount to set
     */
    public void setTotalRecordCount(long totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    /**
     * @return the bookList
     */
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * @param bookList the bookList to set
     */
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
