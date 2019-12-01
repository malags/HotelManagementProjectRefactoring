class NotAvailable extends Exception {

    protected int id;

    public NotAvailable(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Room " + id + " not Available!";
    }
}
