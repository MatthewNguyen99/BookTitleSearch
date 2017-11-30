class Book{
    private String title;
    Book(String pTitle){
        this.title = pTitle;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString(){
        return "\n  " + title;
    }
}
