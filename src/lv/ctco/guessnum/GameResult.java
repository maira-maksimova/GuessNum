package lv.ctco.guessnum;

/**
 * Created by maira.maksimova01 on 11/13/2018.
 */
public class GameResult {
    String name;
    int triesCount;
    long duration;

    public void print() {
        System.out.printf("%s %d %.2f sec\n", this.name, this.triesCount, this.duration / 1000.0); //%s - string %d = int %f decimal
    }
}


