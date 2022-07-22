package lecture0721.simplebookesearch.vo;

public class BookVo { // VO,DTO 역할
    public BookVo(){

    }
    public BookVo(String bisbn, String btitle, String bdate, int bprice) {
        this.bisbn = bisbn;
        this.btitle = btitle;
        this.bdate = bdate;
        this.bprice = bprice;
    }

    @Override
    public String toString() {
        return getBisbn() + getBtitle() + getBdate() + getBprice();
    }

    private String bisbn;
    private String btitle;
    private String bdate;
    private int bpage;
    private int bprice;
    private String bauthor;

    public String getBisbn() {
        return bisbn;
    }

    public void setBisbn(String bisbn) {
        this.bisbn = bisbn;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public int getBpage() {
        return bpage;
    }

    public void setBpage(int bpage) {
        this.bpage = bpage;
    }

    public int getBprice() {
        return bprice;
    }

    public void setBprice(int bprice) {
        this.bprice = bprice;
    }

    public String getBauthor() {
        return bauthor;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }
}
