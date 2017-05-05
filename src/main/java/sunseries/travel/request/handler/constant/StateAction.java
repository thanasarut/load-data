package sunseries.travel.request.handler.constant;

/**
 * Created by pea.chiwa on 2/23/17.
 */
public enum StateAction {
    CONFIRM("confirm"),
    GUARANTEE("guarantee"),
    CANCEL("cancel"),
    REJECT("reject"),
    WAIT_FOR_CONFIRM("wait_for_confirm");

    private final String text;

    private StateAction(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
