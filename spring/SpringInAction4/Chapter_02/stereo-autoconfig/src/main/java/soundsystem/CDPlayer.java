// package label 
package soundsystem;

// import annotations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// @Component indicates the class is a bean to be managed by spring
@Component
public class CDPlayer implements MediaPlayer {
  private CompactDisc cd;

  // @Autowired inticates that the appropriate created bean (e.g. by component scanning)
  @Autowired
  public CDPlayer(CompactDisc cd) {
    this.cd = cd;
  }

  public void play() {
    cd.play();
  }

}
