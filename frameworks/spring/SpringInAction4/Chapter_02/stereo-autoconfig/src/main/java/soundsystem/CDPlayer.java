// package label
package soundsystem;

// import annotations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// @Component indicates the class is a bean to be managed by spring
@Component
public class CDPlayer implements MediaPlayer {
  private CompactDisc cd;

  // @Autowired asks Spring to inject the correct dependency
  @Autowired
  public CDPlayer(CompactDisc cd) {
    this.cd = cd;
  }

  public void play() {
    cd.play();
  }

}
