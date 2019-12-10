package meat;

import meat.hellSpring.HellInSpring;
import meat.postProxy.PostProxy;
import meat.profiling.Profiling;
import meat.repeat.RepeatScreamRandom;

import javax.annotation.PostConstruct;

//оновной класс для приколюх!
@Profiling
//аннотация будет добавлять логику уже в существующие обьекты! Как? Да хер бы я понимал чё этот пост процессор бина там делает!
//короче это будет прокси класс.

// если убрать данную анатацию код будет работать как играньше, данная анатация подменяет бины класса другими.
@HellInSpring(hellNewImpl = RepeMeatWow.class)

public class RepeMeat implements Meat {
    @RepeatScreamRandom(min = 1, max = 10)
    private int repeat;

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    private String scream;
//Обучаем сприн двухфазовому конструктору
    @PostConstruct
    public void init(){
        System.out.println("Конструктор второй фазы");
        System.out.println(repeat);
    }

    public void setScream(String scream) {
        this.scream = scream;
    }

    public RepeMeat() {
        System.out.println("Конструктор первой фазы");
    }

    @Override
    @PostProxy
    //тут магия когда всё уже собранно и настроенно но я всё равно хочу менть уже готовое!
    public void sayWhatAreYouDoing() {
        System.out.println("конструктор третей фазы, лезу в прокси меняю там всё как хочу");
        for (int i = 0; i < repeat; i++) {
            System.out.println(" Scream = " + scream);
        }
    }
}