class NotAvailable extends Exception {

    private int id;

    public NotAvailable(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Room " + id + " not Available!";
    }
}
