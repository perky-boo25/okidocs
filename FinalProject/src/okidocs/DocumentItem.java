package okidocs;

/**
 * Represents a file in the downloadable list.
 */
public class DocumentItem {
    private final String title;
    private final String url;

    public DocumentItem(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() { return title; }
    public String getUrl() { return url; }

    @Override
    public String toString() {
        return title;
    }
}
