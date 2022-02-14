package hu.petrik.muzeumfrontendjavafx;

public class ErrorApi {
    private String uzenet;

    public ErrorApi(String uzenet) {
        this.uzenet = uzenet;
    }

    public String getUzenet() {
        return uzenet;
    }

    public void setUzenet(String uzenet) {
        this.uzenet = uzenet;
    }
}
