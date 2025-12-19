package okidocs;


public class DocumentItem {
    // Displays name of the Document and its download URL
    private final String title;
    private final String url;

    public DocumentItem(String title, String url) {
        this.title = title;
        this.url = url;
    }
    // Getters
    public String getTitle() { return title; }
    public String getUrl() { return url; }

    @Override
    public String toString() {
        return title;
    }
}
