import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@lombok.Getter
@lombok.Setter
@lombok.RequiredArgsConstructor
@lombok.EqualsAndHashCode(of = {"number", "text"})
@lombok.ToString(exclude = "strList")
public class EveryThing {
    private boolean flag;
    private final int number;
    private final String text;
    private List<String> strList;
 
    public List<String> getStrList() {
        if (strList == null) {
            strList = new ArrayList<>(128);
        }
        return Collections.unmodifiableList(strList);
    }
}
