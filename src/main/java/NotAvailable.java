/**
 * Class that represent not available exeception.
 */
class NotAvailable extends Exception {

    protected int id;

    public NotAvailable(int id) {
        this.id = id;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Room " + id + " not Available!";
    }
}
