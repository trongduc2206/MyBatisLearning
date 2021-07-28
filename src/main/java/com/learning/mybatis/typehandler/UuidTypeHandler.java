package com.learning.mybatis.typehandler;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.io.InputStream;
import java.sql.*;
import java.util.UUID;

@MappedJdbcTypes(JdbcType.OTHER)
@MappedTypes(UUID.class)
public class UuidTypeHandler extends BaseTypeHandler<UUID>{
    static UUID toUUID(byte[] bytes) {
        if (bytes.length != 16) {
            throw new IllegalArgumentException();
        }
        int i = 0;
        long msl = 0;
        for (; i < 8; i++) {
            msl = (msl << 8) | (bytes[i] & 0xFF);
        }
        long lsl = 0;
        for (; i < 16; i++) {
            lsl = (lsl << 8) | (bytes[i] & 0xFF);
        }
        return new UUID(msl, lsl);
    }

        @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType) throws SQLException {
              ps.setString(i, parameter.toString());
            }

            @Override
    public UUID getNullableResult(ResultSet rs, String columnName) throws SQLException {
              byte[] value = rs.getBytes(columnName);
              if (value != null) {
                    return toUUID(value);
                  }
              return null;
            }

            @Override
    public UUID getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
              String value = rs.getString(columnIndex);
              if (value != null) {
                    return UUID.fromString(value);
                  }
              return null;
            }

            @Override
    public UUID getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
              String value = cs.getString(columnIndex);
              if (value != null) {
                    return UUID.fromString(value);
                 }
             return null;
           }
}
