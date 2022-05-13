package cl.uchile.dcc.citricliquid.model.Character;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class WildUnitTest {
   private final static String PLAYER_NAME = "Suguri";
   private Player suguri;
   private final static String WILD_NAME = "Chicken";
   private WildUnit chicken;
   private final static String WILD_NAME_2 = "Seagull";
   private WildUnit seagull;
   private final static String BOSS_NAME = "Store Manager";
   private BossUnit storemanager;

   @BeforeEach
   public void setUp() {
      suguri = getSuguri();
      chicken = getChicken();
      storemanager = getStoremanager();
   }
   private Player getSuguri(){
      return new Player(PLAYER_NAME, 4, 1, -1, 2);
   }
   private WildUnit getChicken(){
      return new WildUnit(WILD_NAME, 5, 3, -1, 1);
   }
   private BossUnit getStoremanager(){
      return new BossUnit(BOSS_NAME,8,3,2,-1);
   }
   private WildUnit getSeagull(){
      return new WildUnit(WILD_NAME_2,3,1,-1,-1);
   }
   @Test
   public void copyTest() {
      final var expectedchicken = getChicken();
      final var actualchicken = chicken.copy();
      // Checks that the copied player have the same parameters as the original
      Assertions.assertEquals(expectedchicken, actualchicken);
      // Checks that the copied player doesn't reference the same object
      Assertions.assertNotSame(expectedchicken, actualchicken);
   }
   @RepeatedTest(100)
   public void attackTest(){
      long seed = new Random().nextLong();
      int r = new Random(seed).nextInt(6)+1;
      chicken.setSeed(seed);
      int expectedattack = chicken.getAtk() + r;
      Assertions.assertEquals(expectedattack,chicken.attack());
   }
   @RepeatedTest(100)
   public void defendTest(){
      long seed = new Random().nextLong();
      int r = new Random(seed).nextInt(6)+1;
      suguri.setSeed(seed);
      chicken.setSeed(seed);
      int atk = suguri.attack();
      int defend = Math.max(atk-chicken.getDef()-r,1);
      int expectedHP = Math.max(chicken.getCurrentHp()-defend,0);
      chicken.defend(atk);
      Assertions.assertEquals(expectedHP,chicken.getCurrentHp());
   }
   @RepeatedTest(100)
   public void evadeTest(){
      long seed = new Random().nextLong();
      long seed2 = new Random().nextLong();
      int r = new Random(seed).nextInt(6)+1;
      chicken.setSeed(seed);
      storemanager.setSeed(seed2);
      int chickenHP = chicken.getCurrentHp();
      int atk = storemanager.attack();
      int evd = chicken.getEvd() + r;
      chicken.evade(atk);
      if (atk >= evd){
         int expectedHP = Math.max(0,(chickenHP-atk));
         Assertions.assertEquals(expectedHP,chicken.getCurrentHp());
      }
      else {
         Assertions.assertEquals(chickenHP,chicken.getCurrentHp());
      }
   }
}
