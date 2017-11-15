package sj.generated.mapper;

import sj.generated.model.CatchDollRecord;

public interface CatchDollRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CatchDollRecord record);

    CatchDollRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(CatchDollRecord record);
}