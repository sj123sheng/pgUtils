package sj.generated.mapper;

import org.apache.ibatis.annotations.Param;
import sj.generated.model.ResActorLadderMatch;

public interface ResActorLadderMatchMapper {
    String deleteByPrimaryKey(@Param("actorId") Integer actorId, @Param("seasonId") Integer seasonId);

    String insert(ResActorLadderMatch record);

    ResActorLadderMatch selectByPrimaryKey(@Param("actorId") Integer actorId, @Param("seasonId") Integer seasonId);

    String updateByPrimaryKey(ResActorLadderMatch record);
}