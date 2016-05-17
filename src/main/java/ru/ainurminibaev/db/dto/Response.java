package ru.ainurminibaev.db.dto;

/**
 * Created by ainurminibaev on 15.05.16.
 */
public class Response {
    private Object result;
    private Object error;

    public Response(Object result, Object error) {
        this.result = result;
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
