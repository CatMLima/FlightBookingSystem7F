package catlima.todatabasefrominterfacetest;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public enum View {
    SEARCH("search-view.fxml"),

    BOOKING("booking-view.fxml"),

    FLIGHT("flight-control.fxml");

    private final String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}
