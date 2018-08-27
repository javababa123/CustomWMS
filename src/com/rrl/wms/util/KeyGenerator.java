package com.rrl.wms.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class KeyGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SessionImplementor session, Object arg1) throws HibernateException {

        Serializable result = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        connection = session.connection();
        try {
            statement = connection.createStatement();
            try {
                resultSet = statement.executeQuery("select RFS_TABLE_SEQ.nextval from dual");
                if(resultSet.next()) {
                    String nextValue = resultSet.getString(1);
                    result = sdf.format(date) + nextValue;
                    System.out.println("Custom generated Sequence value : "+result);
                    return result;
                }
            } catch(Exception ex) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

