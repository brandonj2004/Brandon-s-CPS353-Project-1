package apiproject.processes;

public enum Status {
    COMPLETE(true),
    INCOMPLETE(false);

    private boolean complete;

    private Status(boolean complete) {
        this.complete = complete;
    }

    public boolean complete() {
        return complete;
    }
}

