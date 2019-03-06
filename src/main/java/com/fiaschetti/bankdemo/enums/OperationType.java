package com.fiaschetti.bankdemo.enums;

public enum OperationType {

    DEPOSIT(Types.DEPOSIT_OPERATION),
    WITDRAWAL(Types.WITDRAWAL_OPERATION),
    TRANSFER(Types.TRANSFER_OPERATION);

    private String value;

    OperationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static class Types {
        public static final String DEPOSIT_OPERATION = "DO";
        public static final String WITDRAWAL_OPERATION = "WO";
        public static final String TRANSFER_OPERATION = "TO";
    }
}
