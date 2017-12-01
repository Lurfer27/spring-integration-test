package data.app.model;

import javax.annotation.PostConstruct;

public class ToData {

    private Integer id;
    private String value;
    private OddOrEven type;
    private boolean handled;

    public ToData(Integer id) {
        this.id = id;
        this.handled = false;
        setValues();
    }

    public Integer getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public OddOrEven getType() {
        return type;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    private void setValues() {
        switch (this.id) {
            case 1:
                this.value = "One";
                this.type = OddOrEven.ODD;
                break;
            case 2:
                this.value = "Two";
                this.type = OddOrEven.EVEN;
                break;
            case 3:
                this.value = "Three";
                this.type = OddOrEven.ODD;
                break;
            case 4:
                this.value = "Four";
                this.type = OddOrEven.EVEN;
                break;
            case 5:
                this.value = "Five";
                this.type = OddOrEven.ODD;
                break;
            case 6:
                this.value = "Six";
                this.type = OddOrEven.EVEN;
                break;
            case 7:
                this.value = "Seven";
                this.type = OddOrEven.ODD;
                break;
            case 8:
                this.value = "Eight";
                this.type = OddOrEven.EVEN;
                break;
            case 9:
                this.value = "Nine";
                this.type = OddOrEven.ODD;
                break;
            case 10:
                this.value = "Ten";
                this.type = OddOrEven.EVEN;
                break;
            default:
                this.value = String.valueOf(this.id);
                this.type = OddOrEven.UNDETERMINED;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Id:");
        builder.append(this.id);
        builder.append(" Value:");
        builder.append(this.value);
        if (this.handled) {
            builder.append(" ** HANDLED **");

        }
        return builder.toString();
    }
}
