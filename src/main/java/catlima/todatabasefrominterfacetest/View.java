package catlima.todatabasefrominterfacetest;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public enum View {

    HOME("main-view.fxml"),
    SEARCH("search-view.fxml");

    private final String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}
