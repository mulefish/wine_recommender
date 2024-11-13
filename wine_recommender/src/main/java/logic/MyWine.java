package logic;

class MyWine {
    private int id;
    private String type;
    private String variety;
    private int year;
    private String region;
    private int price;
    private String topNote;
    private String bottomNote;

    public MyWine(int id, String type, String variety, int year, String region, int price, String topnote, String bottomnote) {
        this.id = id;
        this.type = type;
        this.variety = variety;
        this.year = year;
        this.region = region;
        this.price = price;
        this.topNote = topnote;
        this.bottomNote = bottomnote;
    }

    public String getString() {
        String s = String.format("%d %s %s %s %s %s", id, type, variety, region, topNote, bottomNote);
        return s;
    }
}