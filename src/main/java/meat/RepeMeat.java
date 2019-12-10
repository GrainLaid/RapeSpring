package meat;
//оновной класс для приколюх!
public class RepeMeat implements Meat {
    @RepeatScreamRandom(min = 1, max = 10)
    private int repeat;

    private String scream;

    public void setScream(String scream) {
        this.scream = scream;
    }

    @Override
    public void sayWhatAreYouDoing() {
        for (int i = 0; i < repeat; i++) {
            System.out.println(" Scream = " + scream);
        }
    }
}