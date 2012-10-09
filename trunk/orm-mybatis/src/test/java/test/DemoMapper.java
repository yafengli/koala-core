package test;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DemoMapper {
    @Select("select * from changelog")
    public List<Changelog> selectAll();

    @Select("SELECT * FROM changelog WHERE id=#{id}")
    public Changelog selectSingle(@Param("id") Long id);

    public void insert(Changelog changelog);
}

