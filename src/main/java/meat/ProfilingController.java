package meat;
//Класс для флага
public class ProfilingController implements ProfilingControllerMBean {
    //флаг установил вручную чтобы постоянно не лазить и не запускать МБин
    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
