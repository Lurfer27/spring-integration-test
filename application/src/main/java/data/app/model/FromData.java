package data.app.model;

public class FromData {

    private int id;

    public FromData(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "\"Id:" + this.id + "\"";
    }
}
