import ch.usi.si.codelounge.jsicko.Contract;

/**
 * Class that represent not available exeception.
 */
class NotAvailable extends Exception implements Contract {

    protected int id;

    @Invariant
    @Pure
    protected boolean room_has_valid_number(){
        return this.id >= 1 && this.id <= 60;
    }

    public NotAvailable(int id) {
        this.id = id;
    }

    /**
     * @return
     */
    @Override
    @Pure
    public String toString() {
        return "Room " + id + " not Available!";
    }


}
