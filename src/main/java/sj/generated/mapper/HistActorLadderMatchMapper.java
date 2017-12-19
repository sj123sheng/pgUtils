package sj.generated.mapper;

import sj.generated.model.HistActorLadderMatch;

public interface HistActorLadderMatchMapper {
    String deleteByPrimaryKey(Integer ladderMatchRecordId);

    Integer insert(HistActorLadderMatch record);

    HistActorLadderMatch selectByPrimaryKey(Integer ladderMatchRecordId);

    String updateByPrimaryKey(HistActorLadderMatch record);
}