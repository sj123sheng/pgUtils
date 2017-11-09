package sj.generated.mapper;

import sj.generated.model.SongjmTest;

public interface SongjmTestMapper {
    int deleteByPrimaryKey(Integer colId);

    int insert(SongjmTest record);

    SongjmTest selectByPrimaryKey(Integer colId);

    int updateByPrimaryKey(SongjmTest record);
}