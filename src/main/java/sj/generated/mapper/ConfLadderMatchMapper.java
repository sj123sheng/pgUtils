package sj.generated.mapper;

import sj.generated.model.ConfLadderMatch;

public interface ConfLadderMatchMapper {
    String deleteByPrimaryKey(Integer seasonId);

    Integer insert(ConfLadderMatch record);

    ConfLadderMatch selectByPrimaryKey(Integer seasonId);

    String updateByPrimaryKey(ConfLadderMatch record);
}