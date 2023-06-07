package com.example.kakeibo.dao;

import com.example.kakeibo.entity.Bp;
import com.example.kakeibo.entity.BpInsert;
import com.example.kakeibo.entity.BpUpdate;
import com.example.kakeibo.record.BpRecord;
import com.example.kakeibo.record.MonthBp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PgBpDao implements BpDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<BpRecord> findAll() {
        return jdbcTemplate.query("SELECT bp.id id, date, outin outIn, category , amount, explain " +
                        "FROM bp " +
                        "LEFT JOIN categories c " +
                        "ON bp.category_id = c.id " +
                        "ORDER BY date DESC " +
                        "limit 100",
                new DataClassRowMapper<>(BpRecord.class));
    }

    @Override
    public Bp findById(int rowId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", rowId);
        var list = jdbcTemplate.query("SELECT bp.id, date, outin, category, amount, explain " +
                "FROM bp " +
                "LEFT JOIN categories c " +
                "ON bp.category_id = c.id " +
                "WHERE bp.id = :id ", param, new DataClassRowMapper<>(Bp.class));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int insert(BpInsert bpInsert) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        java.sql.Date date = java.sql.Date.valueOf(bpInsert.getDate());

        Integer categoryId;
        if(bpInsert.getCategoryId() == 0){
            categoryId = null;
        }else{
            categoryId = bpInsert.getCategoryId();
        }

        param.addValue("date", date);
        param.addValue("outIn", bpInsert.getOutIn());
        param.addValue("categoryId", categoryId);
        param.addValue("amount", bpInsert.getAmount());
        param.addValue("explain", bpInsert.getExplain());

        return jdbcTemplate.update("INSERT " +
                "INTO bp(date, outin, category_id, amount, explain) " +
                "VALUES(:date, :outIn, :categoryId, :amount, :explain)", param);
    }

    @Override
    public List<MonthBp> monthBp() {
        return jdbcTemplate.query("SELECT to_char(date, 'YYYY-MM') AS date, " +
                        "SUM(CASE WHEN outin = '収入' THEN amount ELSE 0 END) AS income, " +
                        "SUM(CASE WHEN outin = '支出' THEN amount ELSE 0 END) AS expense, " +
                        "SUM(CASE WHEN outin = '収入' THEN amount ELSE -amount END) AS difference " +
                        "FROM BP " +
                        "GROUP BY to_char(date, 'YYYY-MM') " +
                        "ORDER BY to_char(date, 'YYYY-MM')",
                new DataClassRowMapper<>(MonthBp.class));
    }

    @Override
    public List<MonthBp> monthBpCategory(Integer[] categories) {
    System.out.println("dao");

        for(int element : categories){
            if(element == 0){
                var result = jdbcTemplate.query("SELECT to_char(date, 'YYYY-MM') AS date, " +
                                "SUM(CASE WHEN outin = '収入' THEN amount ELSE 0 END) AS income, " +
                                "SUM(CASE WHEN outin = '支出' THEN amount ELSE 0 END) AS expense, " +
                                "SUM(CASE WHEN outin = '収入' THEN amount ELSE -amount END) AS difference " +
                                "FROM BP " +
                                "GROUP BY to_char(date, 'YYYY-MM') " +
                                "ORDER BY to_char(date, 'YYYY-MM')",
                        new DataClassRowMapper<>(MonthBp.class));
                for(var str : result){
                    System.out.println(str);
                }
                return result;
            }
        }


        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("categories", categories);

        String query = "SELECT to_char(date, 'YYYY-MM') AS date, " +
                "SUM(CASE WHEN outin = '収入' THEN amount ELSE 0 END) AS income, " +
                "SUM(CASE WHEN outin = '支出' THEN amount ELSE 0 END) AS expense, " +
                "SUM(CASE WHEN outin = '収入' THEN amount ELSE -amount END) AS difference " +
                "FROM BP " +
                "WHERE category_id = ANY(:categories) OR outin = '収入' " +
                "GROUP BY to_char(date, 'YYYY-MM') " +
                "ORDER BY to_char(date, 'YYYY-MM')";
        List<MonthBp> result = jdbcTemplate.query(query, param, new DataClassRowMapper<>(MonthBp.class));

        for(var str : result){
            System.out.println(str);
        }
        return result;
    }

    @Override
    public int delete(int id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);

        return jdbcTemplate.update("DELETE " +
                "FROM bp " +
                "WHERE id = :id", param);
    }

    @Override
    public int categoryId(int id) {
        String sql = "SELECT category_id " +
                "FROM bp " +
                "WHERE id = :id";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);

        var categoryId = jdbcTemplate.queryForObject(sql, param, Integer.class);
        if(categoryId == null){
            categoryId = 0;
        }
        return categoryId;
    }

    @Override
    public int update(BpUpdate bp) {
        java.sql.Date date = java.sql.Date.valueOf(bp.getDate());
        MapSqlParameterSource param = new MapSqlParameterSource();

        Integer categoryId;
        if(bp.getCategoryId() == 0){
            categoryId = null;
        }else{
            categoryId = bp.getCategoryId();
        }



        param.addValue("id", bp.getId());
        param.addValue("date", date);
        param.addValue("outIn", bp.getOutIn());
        param.addValue("categoryId", categoryId);
        param.addValue("amount", bp.getAmount());
        param.addValue("explain", bp.getExplain());

        System.out.println(bp.getId());
        System.out.println(date);
        System.out.println(bp.getOutIn());
        System.out.println(categoryId);
        System.out.println(bp.getAmount());
        System.out.println(bp.getExplain());

        return jdbcTemplate.update("UPDATE " +
                "bp " +
                "SET date = :date " +
                ", outin = :outIn " +
                ", category_id = :categoryId " +
                ", amount = :amount " +
                ", explain = :explain " +
                "WHERE id = :id", param);
    }




}
