package model.common;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.Date;

import oracle.jbo.server.DBTransaction;
import oracle.jbo.server.SequenceImpl;

public class ModelUtil {

    public static BigDecimal getSequenceNextVal(DBTransaction transaction) {
        SequenceImpl seq = new SequenceImpl("ID_SEQ", transaction);
        return seq.getSequenceNumber().bigDecimalValue();
    }

    public static Timestamp getCurrentDate() {
        Date today = new Date();
        return new Timestamp(today.getTime());
    }
}
