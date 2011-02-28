package org.koala.spring.support;

import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.Types;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author YaFengLi
 */
@Deprecated
public class SqlCreatorUtils extends StatementCreatorUtils {

    private String patternEx;

    public String getPatternEx() {
        return patternEx;
    }

    public void setPatternEx(String patternEx) {
        this.patternEx = patternEx;
    }

    public String createSql(String sql, SqlParameterSource sps) {
        String sqlToUse = sql;

        Pattern pattern = Pattern.compile(this.getPatternEx());
        Matcher matcher = pattern.matcher(sql);
        boolean found = false;
        while ((found = matcher.find())) {
            String str = matcher.group();
            String name = str.substring(1);
            Integer sqltype = sps.getSqlType(name);
            if (Types.INTEGER == sqltype || Types.DOUBLE == sqltype || Types.NUMERIC == sqltype || Types.FLOAT == sqltype) {
                sqlToUse = sqlToUse.replaceAll(str, String.valueOf(sps.getValue(name)));
            } else {
                sqlToUse = sqlToUse.replaceAll(str, String.format("'%s'", sps.getValue(name)));
            }
        }
        return sqlToUse;
    }
}
