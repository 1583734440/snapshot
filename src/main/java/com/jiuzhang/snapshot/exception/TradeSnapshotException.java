package com.jiuzhang.snapshot.exception;

/**
 * @Description TODO
 * @Date 2021/2/28 14:49
 * @Author FU
 */
public class TradeSnapshotException extends RuntimeException {
    private IError error;

    public TradeSnapshotException() {
        super();
    }

    public TradeSnapshotException(IError error) {
        super(error.getErrorMessage());
        this.error = error;
    }

    public IError getError() {
        return error;
    }
}
