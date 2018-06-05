package pcs.libraryservices;

import java.util.Date;

/**
 *
 * @author keith
 *
 * { "_id" : ObjectId("5b064bb72193c81383633243"), "Title" : "Investdiva's guide
 * to making money in Forex : how anyone can profit in the world's largest
 * currency market / Kiana Danial", "Author" : "Danial, Kiana", "Call number" :
 * "332.45 DAN", "Item id" : NumberLong("34000092359504"), "Item type"
 * :"NONFICTION", "Status" : "CHECKEDOUT", "Language" : "", "Age" : "ADULT",
 * "Checkout Library" : "BSQ", "Date" : NumberLong("201805092352") }
 *
 */
public class Book {
 
    private String title;
    private String author;
    private String callNumber;
    private Long itemId;
    private String itemType;
    private String status;
    private String language;
    private String age;
    private String checkoutLibrary;
    private Long   date;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book:-");
        sb.append("title:");
        sb.append(getTitle());
        sb.append(",author:");
        sb.append(getAuthor());
        sb.append(",Status:");
        sb.append(getStatus());
        sb.append(",checkoutLibrary:");
        sb.append(getCheckoutLibrary());

        return sb.toString();
    }

    /**
     * @return the callNumber
     */
    public String getCallNumber() {
        return callNumber;
    }

    /**
     * @param callNumber the callNumber to set
     */
    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    /**
     * @return the itemId
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the itemType
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * @param itemType the itemType to set
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return the date
     */
    public Long getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Long date) {
        this.date = date;
    }
    /**
     * @return the Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param Title the Title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the Author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param Author the Author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the checkoutLibrary
     */
    public String getCheckoutLibrary() {
        return checkoutLibrary;
    }

    /**
     * @param checkoutLibrary the checkoutLibrary to set
     */
    public void setCheckoutLibrary(String checkoutLibrary) {
        this.checkoutLibrary = checkoutLibrary;
    }

}
