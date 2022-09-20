public class BattleScene {
    public BattleScene() {
    }

    public void fight(FantasyCharacter hero, FantasyCharacter monster, Realm.FightCallback fightCallback) {
        Runnable runnable = () -> {
            int turn = 1;
            boolean isFightEnded = false;

            while(!isFightEnded) {
                System.out.println("----���: " + turn + "----");
                if (turn++ % 2 != 0) {
                    isFightEnded = this.makeHit(monster, hero, fightCallback);
                } else {
                    isFightEnded = this.makeHit(hero, monster, fightCallback);
                }

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException var7) {
                    var7.printStackTrace();
                }
            }

        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private Boolean makeHit(FantasyCharacter defender, FantasyCharacter attacker, Realm.FightCallback fightCallback) {
        int hit = attacker.attack();
        int defenderHealth = defender.getHealthPoints() - hit;
        if (hit != 0) {
            System.out.println(String.format("%s ����� ���� � %d ������!", attacker.getName(), hit));
            System.out.println(String.format("� %s �������� %d ������ ��������...", defender.getName(), defenderHealth));
        } else {
            System.out.println(String.format("%s �����������!", attacker.getName()));
        }

        if (defenderHealth <= 0 && defender instanceof Hero) {
            System.out.println("��������, �� ���� � ���...");
            fightCallback.fightLost();
            return true;
        } else if (defenderHealth <= 0) {
            System.out.println(String.format("���� ��������! �� ��������� %d ���� � %d ������", defender.getXp(), defender.getGold()));
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setGold(attacker.getGold() + defender.getGold());
            fightCallback.fightWin();
            return true;
        } else {
            defender.setHealthPoints(defenderHealth);
            return false;
        }
    }
}

