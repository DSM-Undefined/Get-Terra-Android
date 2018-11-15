package undefined.dsm.getterra.model;

public class ListModle {
    String list;
    boolean isOur;
    int type;

    public ListModle(String list, boolean isOur, int type) {
        this.list = list;
        this.isOur = isOur;
        this.type = type;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public boolean isOur() {
        return isOur;
    }

    public void setOur(boolean our) {
        isOur = our;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
