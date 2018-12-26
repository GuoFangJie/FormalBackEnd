package com.gugu.guguuser.controller.vo;

import com.gugu.gugumodel.entity.RoundEntity;
import com.gugu.gugumodel.entity.RoundScoreEntity;
import com.gugu.gugumodel.entity.TeamEntity;
import com.gugu.gugumodel.entity.TeamScoreInRoundEntity;

import java.util.ArrayList;

/**某一轮次下所有的小组以及所有的成绩
 * @author ljy
 */
public class RoundTeamsScoreMessageVO {

   //round id和次序
   Long roundId;
   Byte roundSerial;
   //参与该轮次的所有小组详细成绩
   ArrayList<TeamScoreInRoundEntity> teamScoreInRoundEntities;

   public Long getRoundId() {
      return roundId;
   }

   public void setRoundId(Long roundId) {
      this.roundId = roundId;
   }

   public Byte getRoundSerial() {
      return roundSerial;
   }

   public void setRoundSerial(Byte roundSerial) {
      this.roundSerial = roundSerial;
   }

   public ArrayList<TeamScoreInRoundEntity> getTeamScoreInRoundEntities() {
      return teamScoreInRoundEntities;
   }

   public void setTeamScoreInRoundEntities(ArrayList<TeamScoreInRoundEntity> teamScoreInRoundEntities) {
      this.teamScoreInRoundEntities = teamScoreInRoundEntities;
   }
}
